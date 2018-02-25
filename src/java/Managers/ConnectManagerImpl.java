/**
 * ********************************************************************
 * Class ConnectManagementImpl
 * Gestion des connexions
 *********************************************************************
 */
package Managers;

import Database.Connect;
import Database.Membre;
import Util.PasswordHash;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConnectManagerImpl implements ConnectManager {

    private EntityManagerFactory emf;
    private static ConnectManagerImpl theConnectManager;

    private ConnectManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static ConnectManager getInstance() {
        if (theConnectManager == null) {
            theConnectManager = new ConnectManagerImpl();
        }
        return theConnectManager;
    }

    /**
     * Méthode pour creer un identifiant de connexion : chaîne de 100 caractères
     * tirés aléatoirement
     *
     * @return Identifiant de connexion
     */
    private static String createID() {
        String id = "";
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int x = 0; x < 100; x++) {
            int i = (int) Math.floor(Math.random() * 62);
            id += chars.charAt(i);
        }
        return id;
    }

    /**
     * Génération d'une chaîne de caractère qui correspond à la date actuelle
     *
     * @return Date actuelle
     */
    private static String now() {
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        return formater.format(date);
    }

    /**
     * Récupérer une connexion par son id
     *
     * @param id Identifiant de la connexion
     * @return Connexion
     */
    @Override
    public Connect getByConnectId(String id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM Connect c WHERE c.idConnect =:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Connect) l.get(0);
    }

    /**
     * Déconnexion d'un utilisateur
     *
     * @param m Membre à connecter
     */
    @Override
    public void deconnect(Membre m) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM Connect c WHERE c.idMembre =:membre");
        q.setParameter("membre", m);
        List l = q.getResultList();
        //Delete de la connexion
        if (!l.isEmpty()) {
            Connect c = (Connect) l.get(0);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    /**
     * Création d'une connexion pour un utilisateur
     *
     * @param m Membre à connecter
     * @return Identifiant de connexion
     */
    @Override
    public String createConnection(Membre m) {
        //Création de la connexion
        String idco = createID();
        String lastAction = now();
        Connect c = new Connect(idco, lastAction, m);
        //Insertion
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        return idco;
    }

    /**
     * Mise à jour d'une connexion : mise à jour de la date de la dernière
     * action sur le site
     *
     * @param c Connexion
     */
    @Override
    public void updateConnection(Connect c) {
        //Mise à jout de la date
        c.setConnectLastAction(now());
        //Update dans la base de données
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    /**
     * Connexion d'une personne : mise à jour de sa connexion si celle-ci existe
     * ou création de la connexion sinon
     *
     * @param m Membre
     * @return Identifiant de connexion
     */
    @Override
    public String connect(Membre m) {
        //On récupère la connexion de la personne
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM Connect c WHERE c.idMembre =:membre");
        q.setParameter("membre", m);
        List l = q.getResultList();

        //Si elle n'existe pas, on la crée
        if (l.isEmpty()) {
            return createConnection(m);
            //Sinon, on la met à jour
        } else {
            Connect c = (Connect) l.get(0);
            updateConnection(c);
            return c.getIdConnect();
        }
    }

    /**
     * Déconnexion des utilisateurs qui n'ont pas été actifs sur le site depuis
     * plus de 1h
     */
    @Override
    public void checkConnection() {
        EntityManager em = emf.createEntityManager();

        //Selection des connexions
        Query queryProductsByName = em.createNamedQuery("Connect.findAll", Connect.class);
        List l = queryProductsByName.getResultList();

        //Test des connexions
        for (int i = 0; i < l.size(); i++) {
            Connect c = (Connect) l.get(i);
            if (isOld(c)) {
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
        }
    }

    /**
     * Test si une connexion est obsolète ou non
     *
     * @param Connect Connexion
     * @return Booleen : lastAction > now - 1h
     */
    private static boolean isOld(Connect c) {
        Date date1;
        Date date2;
        boolean isold = false;
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy H:mm:ss");
            date1 = formatter.parse(c.getConnectLastAction());
            date2 = formatter.parse(now());

            //Diff en milliseconds
            long diff = -date1.getTime() + date2.getTime();
            isold = (diff > 3600000);

        } catch (ParseException e) {
        }
        return isold;
    }

    /**
     * Vérifier si un membre de la base de données correspond aux paramètres
     * donnés : email et mot de passe
     *
     * @param email Adresse email de la personne
     * @param mdp Mot de passe de la personne
     * @return Booléen : est-ce que cette personne existe ou non? Vrai si oui, faux sinon
     */
    
    @Override
    public boolean identifierValidation(String email, String mdp) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Membre p WHERE  p.email=:email");
        q.setParameter("email", email);
        List l = q.getResultList();

        if (l.isEmpty()) {
            return false;
        } else {
            return PasswordHash.match(mdp, ((Membre) l.get(0)).getMdp());
        }
    }
}
