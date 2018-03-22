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
import Util.CoupleStats;
import Util.Levenshtein;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Override
    public List<CoupleStats> stats() {
        
        EntityManager em = emf.createEntityManager();
        Query query;
        Query q = em.createQuery("SELECT r FROM Recherche r ORDER BY r.idRecherche DESC").setMaxResults(200);
        List<Recherche> l = q.getResultList();
        List<CoupleStats> chaine= new ArrayList<>();
        for (int i=0; i<l.size(); i++)
        {
            if (l.get(i).getSerie()!=null && !(l.get(i).getSerie().equals("")))
            {
                chaine.add(new CoupleStats(Levenshtein.condense(l.get(i).getSerie()),1));
            }
            else
            {
                if (l.get(i).getTitre()!=null && !(l.get(i).getTitre().equals("")));
                {
                    chaine.add(new CoupleStats(Levenshtein.condense(l.get(i).getTitre()),1));
                }
            }
        }
        return chaine;
    }
    
}
