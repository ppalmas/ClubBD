/**
 * ********************************************************************
 * Class MembreManagementImpl
 * Gestion des membres
 *********************************************************************
 */
package Managers;

import Database.Createur;
import Database.Createurdocument;
import Database.Document;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CreateurDocManagerImpl implements CreateurDocManager {

    private EntityManagerFactory emf;
    private static CreateurDocManagerImpl theCreateurDocManager;

    private CreateurDocManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static CreateurDocManager getInstance() {
        if (theCreateurDocManager == null) {
            theCreateurDocManager = new CreateurDocManagerImpl();
        }
        return theCreateurDocManager;
    }

    @Override
    public void insert(String iddoc, String nomcrea, String prenomcrea, String poste) {
        //Création de l'objet createurdoc
        Createurdocument c = new Createurdocument();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //recherche docu
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.idDocument=:iddoc");
        q.setParameter("iddoc", Integer.parseInt(iddoc));
        List l = q.getResultList();

        
        c.setIdDocument((Document) l.get(0));

        //recherche createur
        Query q2 = em.createQuery("SELECT c FROM Createur c WHERE  UPPER(c.nomCreateur) =UPPER(:nom) AND UPPER(c.prenomCreateur) =UPPER(:prenom)");
        q2.setParameter("nom", nomcrea);
        q2.setParameter("prenom", prenomcrea);
        
        List l2 = q2.getResultList();
        
        c.setIdCreateur((Createur) l2.get(0));
        
        c.setPoste(poste);
        
        //Insertion
        
        
        em.persist(c);
        em.getTransaction().commit();
    }

}
