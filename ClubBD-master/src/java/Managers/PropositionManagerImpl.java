/**
 * ********************************************************************
 * Class PropositionManagerImpl
 * Gestion des propositions
 *********************************************************************
 */
package Managers;

import Database.Proposition;
import Database.Recherche;
import Util.Couple;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Gestion des propositions
 * @author centrale
 */
public class PropositionManagerImpl implements PropositionManager{

    private EntityManagerFactory emf;
    private static PropositionManagerImpl thePropositionsManager;
    private final int nb_propositions = 50;
    
    private PropositionManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    /**
     *
     * @return
     */
    public static PropositionManager getInstance() {
        if (thePropositionsManager == null) {
            thePropositionsManager = new PropositionManagerImpl();
        }
        return thePropositionsManager;
    }
    
    @Override
    public List<Proposition> propositions() {
        EntityManager em = emf.createEntityManager();
        Query query;
        Query q = em.createQuery("SELECT p FROM Proposition p ORDER BY p.idProposition DESC").setMaxResults(nb_propositions);
        List<Proposition> l =  q.getResultList(); 
        return l;
    }

    
    /**
     * Méthode pour l'insertion d'une proposition dans la bdd
     * @param titre
     * @param commentaire 
     */
    @Override
    public void insert(String titre, String commentaire){
        Proposition p = new Proposition();

        p.setNomProposition(titre);
        p.setCommentaireProposition(commentaire);
        
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNb_propositions() {
        return nb_propositions;
    }
    
    
    
}
