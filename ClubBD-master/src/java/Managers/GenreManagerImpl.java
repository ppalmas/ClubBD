
package Managers;

import Database.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

 
public class GenreManagerImpl implements GenreManager {
    
    
  

    private EntityManagerFactory emf;
    private static GenreManagerImpl theGenreManager;

    private GenreManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static GenreManager getInstance() {
        if (theGenreManager == null) {
            theGenreManager = new GenreManagerImpl();
        }
        return theGenreManager;
    }

    
    @Override
    public void insert(String nom) {
        //Création de l'objet createur
        Genre c = new Genre();
        c.setNomGenre(nom);
        
        
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    @Override
    public List<Genre> findGenre(){
        EntityManager em = emf.createEntityManager();
       
        Query q = em.createQuery("SELECT c FROM Genre c");
        
         List<Genre> l = q.getResultList();
         
         return l;
    }
    
}
