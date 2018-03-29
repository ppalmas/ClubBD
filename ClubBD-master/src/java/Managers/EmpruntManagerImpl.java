/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Document;
import Database.Emprunt;
import Database.Membre;
import Database.Serie;
import java.util.ArrayList;
import java.util.Date;
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
    
    /**
     * Retourne la liste des emprunts associés à un document
     *
     * @param idDoc de membre
     * @return
     */
    @Override
    public ArrayList<Emprunt> findEmpruntDoc(Document idDoc) {
        ArrayList<Emprunt> emp = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e WHERE e.idDocument=:id");
        q.setParameter("id", idDoc);
        List l = q.getResultList();
        for (Object o : l) {
            emp.add((Emprunt) o);
        }
        return emp;
    }    
    
    /**
     * Ajout d'une réservation
     * @param idmb idmembre
     * @param iddoc iddocument
     * @param date_reserve date de réservation
     */
    @Override
    public void insert(Integer idmb, Integer iddoc, Date date_reserve) {
        EntityManager em = emf.createEntityManager();

        //Création de l'objet personne
        Emprunt e = new Emprunt();
        try{
            Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.idMembre=:idMb");
            q.setParameter("idMb", idmb);
            List l = q.getResultList();
            e.setIdMembre((Membre) l.get(0));
        }
        catch (Exception e1){
            
        }

        try{
            Query q2 = em.createQuery("SELECT d FROM Document d WHERE  d.idDocument=:idDoc");
            q2.setParameter("idDoc", iddoc);
            List l2 = q2.getResultList();
            e.setIdDocument((Document) l2.get(0));
        }
        catch(Exception e2){
            
        }

        
        e.setDateReserve(date_reserve);
        e.setDateEmprunt(null);
        e.setDateRetour(null);
        e.setDateRetourne(null);

        e.setIdStaff(null);
        
        //Insertion
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    /**
     * Confirmation de l'emprunt
     * @param e
     * @param date_retour
     * @param date_emprunt
     * @param idstaff
     */
    @Override
    public void updateEmprunt (Emprunt e, Date date_retour, Date date_emprunt, Integer idstaff) {
        EntityManager em = emf.createEntityManager();
        //Mise à jour des infos
        e.setDateRetour(date_retour);
        e.setDateEmprunt(date_emprunt);
        
        //pour le staff ayant fait la validation
        try{
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.idMembre=:id");
        q.setParameter("id", idstaff);
        List l = q.getResultList();
        e.setIdStaff((Membre) l.get(0));
        }catch(Exception e1){
            
        }
        
        //Update dans la base de données
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    /**
     * Retour du document
     * @param e
     * @param date_retourne
     */
    @Override
    public void updateRetourne (Emprunt e, Date date_retourne){
        //Mise à jour des infos
        e.setDateRetourne(date_retourne);
        
        //Update dans la base de données
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();        
    }
    
    /**
     * Retrouver un emprunt par son id
     *
     * @param id id du document
     * @return Emprunt correspondant
     */
    @Override
    public Emprunt findEmpruntByID(Integer id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e WHERE  e.idEmprunt=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Emprunt) l.get(0);
    }


    /**
     * Retourne la liste des emprunts/réservations en cours
     * @return
     */
    @Override
    public ArrayList<Emprunt> findEmprunts() {
        ArrayList<Emprunt> emp = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e WHERE e.dateRetourne IS NULL");
        List l = q.getResultList();
        for (Object o : l) {
            emp.add((Emprunt) o);
        }
        return emp;
    }
}
