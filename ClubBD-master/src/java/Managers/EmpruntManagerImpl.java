/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Emprunt;
import Database.Membre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Kevin
 */
public class EmpruntManagerImpl implements EmpruntManager{
    
    private EntityManagerFactory emf;
    private static EmpruntManagerImpl theEmpruntManager;
    
    private EmpruntManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static EmpruntManager getInstance() {
        if (theEmpruntManager == null) {
            theEmpruntManager = new EmpruntManagerImpl();
        }
        return theEmpruntManager;
    }
    
    /**
     * Retourne la liste des emprunts associés à un membre
     *
     * @param id de membre
     * @return
     */
    @Override
    public ArrayList<Emprunt> findEmprunt(Membre id) {
        ArrayList<Emprunt> emp = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e WHERE e.idMembre=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        for (Object o : l) {
            emp.add((Emprunt) o);
        }
        return emp;
    }
}
