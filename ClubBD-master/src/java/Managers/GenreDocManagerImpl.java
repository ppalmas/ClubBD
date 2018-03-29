/**
 * ********************************************************************
 * Class GenreDocManagerImpl
 * Gestion des genre de documents
 *********************************************************************
 */
package Managers;

import Database.Document;
import Database.Genre;
import Database.Genredocument;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Gestion des genre de documents
 * @author Utilisateur
 */
public class GenreDocManagerImpl implements GenreDocManager {

    private EntityManagerFactory emf;
    private static GenreDocManagerImpl theGenreDocManager;

    private GenreDocManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    /**
     *
     * @return
     */
    public static GenreDocManager getInstance() {
        if (theGenreDocManager == null) {
            theGenreDocManager = new GenreDocManagerImpl();
        }
        return theGenreDocManager;
    }

    /**
     *
     * @param iddoc
     * @param nom
     */
    @Override
    public void insert(String iddoc, String nom) {
        //Création de l'objet createurdoc
        Genredocument c = new Genredocument();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //recherche docu
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.idDocument=:iddoc");
        q.setParameter("iddoc", Integer.parseInt(iddoc));
        List l = q.getResultList();

        
        c.setIdDocument((Document) l.get(0));

        //recherche createur
        Query q2 = em.createQuery("SELECT c FROM Genre c WHERE  UPPER(c.nomGenre) =UPPER(:nom)");
        q2.setParameter("nom", nom);
       
        
        List l2 = q2.getResultList();
        
        c.setIdGenre((Genre) l2.get(0));
        
        
        
        //Insertion
        
        
        em.persist(c);
        em.getTransaction().commit();
    }

}
