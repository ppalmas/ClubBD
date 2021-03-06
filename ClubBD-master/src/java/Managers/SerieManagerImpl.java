/**
 * ********************************************************************
 * Class SerieManagerImpl
 * Gestion des séries
 *********************************************************************
 */
package Managers;

import Database.Serie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Gestion des séries
 * @author Arthus
 */
public class SerieManagerImpl implements SerieManager {

    private EntityManagerFactory emf;
    private static SerieManagerImpl theSerieManager;

    private SerieManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

@Override
    public Boolean exist(String titre){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Serie s WHERE UPPER(s.nomSerie) LIKE UPPER(:titre)");
        q.setParameter("titre", titre);
        
        if (q.getResultList().size() != 0){
            return true;
        }
        else {return false;}
        
    }    
    
    /**
     *
     * @return
     */
    public static SerieManager getInstance() {
        if (theSerieManager == null) {
            theSerieManager = new SerieManagerImpl();
        }
        return theSerieManager;
    }

    @Override
    public List<Serie> findSerie() {

        EntityManager em = emf.createEntityManager();

        List l = null;

        try {
            Query q = em.createQuery("SELECT s FROM Serie s");

            l = q.getResultList();

        } catch (Exception e) {
        }

        return l;
    }

    @Override
    public List<Serie> findSerie(String seriename) {

        EntityManager em = emf.createEntityManager();

        List l = null;

        try {
            Query q = em.createQuery("SELECT s FROM Serie s WHERE UPPER(s.nomSerie) LIKE UPPER(:seriename)");
            q.setParameter("seriename", "%" + seriename + "%");
            l = q.getResultList();

        } catch (Exception e) {
        }

        return l;
    }

    @Override
    public void insert(String seriename, String seriedesc) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Serie s = new Serie();
        s.setComplet(false);
        s.setNomSerie(seriename);
        s.setSerieDescription(seriedesc);

        em.persist(s);
        em.getTransaction().commit();

    }

    @Override
    public void update(String idserie, String seriename, String seriedesc, String complet) {

        //on recupere le document avec l'id
        EntityManager em = emf.createEntityManager();

        Serie s = em.find(Serie.class, Integer.parseInt(idserie));

        em.getTransaction().begin();

        if (Integer.parseInt(complet) == 1) {
            s.setComplet(true);
        } else {
            s.setComplet(false);
        }

        s.setNomSerie(seriename);

        try {
            s.setSerieDescription(seriedesc);
        } catch (Exception e) {
        }

        em.getTransaction().commit();
    }

}
