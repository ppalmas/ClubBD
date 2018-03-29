/**
 * ********************************************************************
 * Class rechercheManagerImpl
 * Gestion des recherches
 *********************************************************************
 */
package Managers;

import Database.Recherche;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Gestion des recherches
 * @author Paola
 */
public class RechercheManagerImpl implements RechercheManager {

    private EntityManagerFactory emf;
    private static RechercheManagerImpl theRechercheManager;

    private RechercheManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    /**
     *
     * @return
     */
    public static RechercheManager getInstance() {
        if (theRechercheManager == null) {
            theRechercheManager = new RechercheManagerImpl();
        }
        return theRechercheManager;
    }

    /**
     * Insertion d'un élément de recherche (sauvegarde)
     *
     * @param texte
     * @param serie vaut true si le texte est une série, false sinon
     */
    @Override
    public void insert(String texte, String serie) {
//Création de l'objet Recherche
        Recherche m = new Recherche();

        m.setSerie(texte);
        m.setTitre(texte);

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        m.setDateRecherche(stamp);
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

}
