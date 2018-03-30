/**
 * ********************************************************************
 * Class CreateurManagerImpl
 * Gestion des createurs.
 *********************************************************************
 */

package Managers;

import Database.Createur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Gestion des createurs.
 * @author Utilisateur
 */
public class CreateurManagerImpl implements CreateurManager {
    
    
  

    private EntityManagerFactory emf;
    private static CreateurManagerImpl theCreateurManager;

    private CreateurManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    /**
     *
     * @return
     */
    public static CreateurManager getInstance() {
        if (theCreateurManager == null) {
            theCreateurManager = new CreateurManagerImpl();
        }
        return theCreateurManager;
    }

    /**
     *
     * @param nomcrea
     * @param prenomcrea
     */
    @Override
    public void insert(String nomcrea, String prenomcrea) {
        //Création de l'objet createur
        Createur c = new Createur();
        c.setNomCreateur(nomcrea);
        c.setPrenomCreateur(prenomcrea);
        
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

    /**
     *
     * @return
     */
    public List<Createur> findCreateur(){
        EntityManager em = emf.createEntityManager();
       
        Query q = em.createQuery("SELECT c FROM Createur c");
        
         List<Createur> l = q.getResultList();
         return l;
    }
    
}
