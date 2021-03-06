/**
 * ********************************************************************
 * Class StatistiquesManagerImpl
 * Gestion des statistiques
 *********************************************************************
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
import java.util.Collections;
import java.util.List;
/**
 * Gestion des statistiques
 * @author centrale
 */
public class StatistiquesManagerImpl implements StatistiquesManager {
    
    private EntityManagerFactory emf;
    private static StatistiquesManagerImpl theStatistiquesManager;
    private List<String> chaineModif;
    private List<String> chaine;
    private ArrayList<ArrayList<Integer>> regroup1;
    private ArrayList<ArrayList<Integer>> regroupFinal;
    private int crit=2;
    private int nb_recherche=400;

    private StatistiquesManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    /**
     *
     * @return
     */
    public static StatistiquesManager getInstance() {
        if (theStatistiquesManager == null) {
            theStatistiquesManager = new StatistiquesManagerImpl();
        }
        return theStatistiquesManager;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<Recherche> tout() {
        EntityManager em = emf.createEntityManager();
        Query queryProductsByName = em.createNamedQuery("Recherche.findAll", Recherche.class);
        Collection theList = queryProductsByName.getResultList();
        return theList;
    }

    @Override
    public ArrayList<CoupleStats> stats() {
        initListes();
        premRegroupement();
        RegroupementFinal();
        return sortirResults();
    }
    
    private void initListes()
    {
       EntityManager em = emf.createEntityManager();
        Query query;
        Query q = em.createQuery("SELECT r FROM Recherche r ORDER BY r.idRecherche DESC").setMaxResults(nb_recherche);
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
    
    private void premRegroupement(){
        regroup1 = new ArrayList<>();
        ArrayList<Integer> interm;
        boolean pasPresent;
        int i = 0;
        int j;
        while (i<chaineModif.size())
        {
            j=0;
            pasPresent=true;
            while (j<regroup1.size()&&pasPresent)
            {
                if (Levenshtein.distance(chaineModif.get(regroup1.get(j).get(0)), chaineModif.get(i))<=crit)
                {
                    pasPresent=false;
                    regroup1.get(j).add(i);
                }
                j++;
            }
            if (pasPresent)
            {
                interm = new ArrayList<>();
                interm.add(i);
                regroup1.add(interm);
            }
            i++;
        }
    }
    
    private ArrayList<CoupleStats> sortirResults(){
        ArrayList<CoupleStats> result = new ArrayList<>();
        for (ArrayList<Integer> l : regroupFinal)
        {
            result.add(new CoupleStats(chaine.get(l.get(0)),l.size()));
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    private void trouverOpti()
    {
        regroupFinal = new ArrayList<>();
        ArrayList<Integer> interm;
        int dMin, d, iMin;
         for (ArrayList<Integer> l : regroup1)
        {
            dMin=crit*nb_recherche;
            iMin=0;
            for (Integer i : l)
            {
                d=0;
                for (Integer k : l)
                {
                    d+=Levenshtein.distance(chaineModif.get(k), chaineModif.get(i));
                }
                if (d<dMin)
                {
                    dMin=d;
                    iMin=i;
                }
            }
            interm = new ArrayList<>();
            interm.add(iMin);
            regroupFinal.add(interm);
        } 
        int i = 0;
        int j=1;
        while (i<regroupFinal.size())
        {
            while (j<regroupFinal.size())
            {
                if (Levenshtein.distance(chaineModif.get(regroupFinal.get(i).get(0)), chaineModif.get(regroupFinal.get(j).get(0)))<=crit)
                {
                    regroupFinal.remove(j);
                }
                else {
                    j++;
                }
            }
            i++;
            j=i+1;
        }
    }
    
    private void RegroupementFinal(){
        trouverOpti();
        ArrayList<Integer> interm;
        boolean pasPresent;
        int i = 0;
        int j, indice, dist_indice;
        indice = 0;
        while (i<chaineModif.size())
        {
            j=0;
            pasPresent=true;
            dist_indice=crit+1;
            while (j<regroupFinal.size()&&pasPresent)
            {
                if (Levenshtein.distance(chaineModif.get(regroupFinal.get(j).get(0)), chaineModif.get(i))<=crit)
                {
                    pasPresent=false;
                    if(regroupFinal.get(j).get(0) != i&&(Levenshtein.distance(chaineModif.get(regroupFinal.get(j).get(0)), chaineModif.get(i))<dist_indice)){
                        indice=j;
                        dist_indice = Levenshtein.distance(chaineModif.get(regroupFinal.get(j).get(0)), chaineModif.get(i));
                    }
                }
                j++;
            }
            if (pasPresent)
            {
                interm = new ArrayList<>();
                interm.add(i);
                regroupFinal.add(interm);
            }
            else
            {
                regroupFinal.get(indice).add(i);
            }
            i++;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getCrit() {
        return crit;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNb_recherche() {
        return nb_recherche;
    }
    
    
}