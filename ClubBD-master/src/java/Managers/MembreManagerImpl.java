/**
 * ********************************************************************
 * Class MembreManagementImpl
 * Gestion des membres
 *********************************************************************
 */

package Managers;

import Database.Membre;
import Database.Statut;
import Util.PasswordHash;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

 
public class MembreManagerImpl implements MembreManager {
    
    
  

    private EntityManagerFactory emf;
    private static MembreManagerImpl theMembreManager;

    private MembreManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static MembreManager getInstance() {
        if (theMembreManager == null) {
            theMembreManager = new MembreManagerImpl();
        }
        return theMembreManager;
    }

    /**
     * Retrouver un membre par son identifiant de connexion TODO table connect
     */
    
    /**
     * Retrouver un membre par son identifiant de connexion
     *
     * @param id Identifiant de connexion
     * @return Personne
     */
    @Override
    public Membre findMembre(String id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c.idMembre FROM Connect c WHERE  c.idConnect=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Membre) l.get(0);
    }


    /**
     * Retrouver une personne par son email
     *
     * @param email Adresse email
     * @return Personne
     */
    @Override
    public Membre findMembreByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.email=:email");
        q.setParameter("email", email);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Membre) l.get(0);
    }

     /**
     * Renvoyer la liste de tous les membres
     *
     * @return liste de Personnes
     */
    @Override
    public List<Membre> listMembre(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m");
        List l = q.getResultList();
        return l;
    }
    
    /**
     * Renvoyer la liste de tous les membres ayant cette adresse mail
     * @param email recherché
     * @return liste de Personnes
     */
    @Override
    public List<Membre> listMembreByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.email=:email");
        q.setParameter("email", email);
        List l = q.getResultList();
        return l;
    }
    
    /**
     * Renvoyer la liste de tous les membres ayant ce nom
     * @param nom recherché
     * @return liste de Personnes
     */
    @Override
    public List<Membre> listMembreByNom(String nom) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.nom=:nom");
        q.setParameter("nom", nom);
        List l = q.getResultList();
        return l;
    }
    
    /**
     * Renvoyer la liste de tous les membres ayant ce prenom
     * @param prenom recherché
     * @return liste de Personnes
     */
    @Override
    public List<Membre> listMembreByPrenom(String prenom) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.prenom=:prenom");
        q.setParameter("prenom", prenom);
        List l = q.getResultList();
        return l;
    }
    
    /**
     * Renvoyer la liste de tous les membres ayant ce prenom et ce nom
     * @param prenom recherché
     * @param nom recherché
     * @return liste de Personnes
     */
    @Override
    public List<Membre> listMembreByPrenomNom(String prenom, String nom) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Membre m WHERE  m.prenom=:prenom AND m.nom=:nom");
        q.setParameter("prenom", prenom);
        q.setParameter("nom", nom);
        List l = q.getResultList();
        return l;
    }
    
    /**
     * Inscription d'un membre
     *
     * @param email Adresse email
     * @param mdp Mot de passe
     * @param name Nom
     * @param firstname Prénom
     * @param idStatut Statut
     */
    @Override
    public void insert(String email, String mdp, String name, String firstname, Statut idStatut) {
        //Création de l'objet personne
        Membre m = new Membre();
        m.setEmail(email);
        m.setPrenom(firstname);
        m.setNom(name);
        m.setMdp(PasswordHash.hash(mdp));
        m.setIdStatut(idStatut);
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    /**
     * Mise à jour des informations d'un membre
     *
     * @param m Membre
     * @param name Nom
     * @param firstname Prénom
     * @param email Adresse Email
     */
    @Override
    public void updateInfos (Membre m, String name, String firstname, String email) {
        //Mise à jour des infos
        m.setEmail(email);
        m.setPrenom(firstname);
        m.setNom(name);
        //Update dans la base de données
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }
    
    /**
     * Mise à jour du mot de passe d'une personne
     *
     * @param m Membre
     * @param mdp Mot de passe
     */
    @Override
    public void updateMdp(Membre m, String mdp) {
        //Mise à jour des infos
        m.setMdp(mdp); // .setPersonPassword(PasswordHash.hash(mdp));
        //Update dans la base de données
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }    
}
