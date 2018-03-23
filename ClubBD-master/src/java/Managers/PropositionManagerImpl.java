/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Proposition;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Paola
 */
public class PropositionManagerImpl implements PropositionManager {
    private EntityManagerFactory emf;
    private static PropositionManagerImpl thePropositionManager;

    private PropositionManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static PropositionManager getInstance() {
        if (thePropositionManager == null) {
            thePropositionManager = new PropositionManagerImpl();
        }
        return thePropositionManager;
    }
    
    /**
     * Méthode pour l'insertion d'une proposition dans la bdd
     * @param titre
     * @param commentaire 
     */
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
    
}
