/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Serie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
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
System.out.println("aaaa4");
            l = q.getResultList();
            System.out.println("aaaa5");
        } catch (Exception e) {
            System.out.println("erreur syntaxe requete // ");
        }

    
    return l ;
}


}
