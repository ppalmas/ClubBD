/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Database.Recherche;
import java.util.Collection;
/**
 *
 * @author centrale
 */
public class StatistiquesManagerImpl implements StatistiquesManager {
    
    private EntityManagerFactory emf;
    private static StatistiquesManagerImpl theStatistiquesManager;

    private StatistiquesManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static StatistiquesManager getInstance() {
        if (theStatistiquesManager == null) {
            theStatistiquesManager = new StatistiquesManagerImpl();
        }
        return theStatistiquesManager;
    }

    @Override
    public Collection<Recherche> tout() {
        EntityManager em = emf.createEntityManager();
        Query queryProductsByName = em.createNamedQuery("Recherche.findAll", Recherche.class);
        Collection theList = queryProductsByName.getResultList();
        return theList;
    }
    
}
