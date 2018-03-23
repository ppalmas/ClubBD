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
    private List<String> chaineModif;
    private List<String> chaine;

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
    public void stats() {
        initListes();
    }
    
    private void initListes()
    {
       EntityManager em = emf.createEntityManager();
        Query query;
        Query q = em.createQuery("SELECT r FROM Recherche r ORDER BY r.idRecherche DESC").setMaxResults(200);
        List<Recherche> l = q.getResultList();
        chaineModif= new ArrayList<>();
        chaine= new ArrayList<>();
        for (int i=0; i<l.size(); i++)
        {
            if (l.get(i).getSerie()!=null && !(l.get(i).getSerie().equals("")))
            {
                chaineModif.add(Levenshtein.condense(l.get(i).getSerie()));
                chaine.add(l.get(i).getSerie());
            }
            else
            {
                if (l.get(i).getTitre()!=null && !(l.get(i).getTitre().equals("")));
                {
                    chaineModif.add(Levenshtein.condense(l.get(i).getTitre()));
                    chaine.add(l.get(i).getTitre());
                }
            }
        }
    }
    
    private ArrayList<ArrayList<Integer>> premRegroupement(int crit){
        ArrayList<ArrayList<Integer>> regroup = new ArrayList<>();
        ArrayList<Integer> interm;
        boolean pasPresent;
        int i = 0;
        int j;
        while (i<chaineModif.size())
        {
            j=0;
            pasPresent=true;
            while (j<regroup.size()&&pasPresent)
            {
                if (Levenshtein.distance(chaineModif.get(regroup.get(j).get(0)), chaineModif.get(i))<=crit)
                {
                    pasPresent=false;
                    regroup.get(j).add(i);
                }
                j++;
            }
            if (pasPresent)
            {
                interm = new ArrayList<>();
                interm.add(i);
                regroup.add(interm);
            }
            i++;
        }         
        return regroup;
    }
}