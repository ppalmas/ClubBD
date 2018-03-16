/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Createurdocument;
import Database.Document;
import Database.Etat;
import Database.Serie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Arthus
 */
public class DocumentManagerImpl implements DocumentManager {

    private EntityManagerFactory emf;
    private static DocumentManagerImpl theDocumentManager;

    private DocumentManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ClubBDPU");
        }
    }

    public static DocumentManager getInstance() {
        if (theDocumentManager == null) {
            theDocumentManager = new DocumentManagerImpl();
        }
        return theDocumentManager;
    }

    /**
     * Recherche des documents par un mot clé
     *
     * @param mot
     * @return liste de documents
     */
    @Override
    public List<Document> findDocumentBy1WordTitle(String mot) {
        EntityManager em = emf.createEntityManager();
        mot = "%" + mot + "%";
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.titre LIKE :mot");
        q.setParameter("mot", mot);
        List l = q.getResultList();
        return l;
    }

    /**
     * Recherche un document suivant un liste de critères au format string :
     * dans cette liste, le premier élément est le titre, le second l'auteur, le
     * troisième la cote
     *
     * @param criteres
     * @return
     */
    @Override
    public List<Document> findDocument(ArrayList<String> criteres) {

        //recherche un document selon les criteres spécifiés
        EntityManager em = emf.createEntityManager();

        String SQL = "SELECT d FROM Document d WHERE ";
        List l = null;

        //structure de la liste critere : (0)titre (1)serie (2)cote
        if (criteres.get(0) != "" || criteres.get(1) != "" || criteres.get(2) != "") {
            //si titre

            if (criteres.get(0) != "") {
                //on recherche un document dont le titre comporte le critère

                SQL = SQL + " d.titre LIKE " + "'%" + criteres.get(0) + "%'";
            } else {
                SQL = SQL + " d.titre LIKE " + "'%'";
            }
            //si serie
            if (criteres.get(1) != "") {
                SQL = SQL + " AND " + "d.idSerie.nomSerie LIKE " + "'%" + criteres.get(1) + "%'";
            }

            //si cote
            if (criteres.get(2) != "") {
                SQL = SQL + " AND " + "d.cote LIKE " + "'%" + criteres.get(2) + "%'";
            }

            try {
                Query q = em.createQuery(SQL);

                l = q.getResultList();
            } catch (Exception e) {
                System.out.println("erreur syntaxe requete // " + SQL);
            }

        }
        return l;
    }

    /**
     * Renvoie l'id max des documents de la bdd
     *
     * @return id maximum des documents de la base de données
     */
    @Override
    public int getMaxId() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Document.findAll", Document.class);
        List l = q.getResultList();
        return (l.size() + 1);
    }

    /**
     * Retrouver un document par son id
     *
     * @param id id du document
     * @return Document correspondant
     */
    @Override
    public Document findDocument(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.idDocument=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty() ? null : (Document) l.get(0);
    }

    
    @Override
    public void insert(String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img) {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        

        Document d = new Document();
        d.setTitre(titre);
        d.setCote(cote);

        d.setCommentaire(comm);
        d.setDescription(desc);
        try{
        d.setNumero(Integer.parseInt(numero));}catch (Exception e){}
        d.setImageDocument(img);
        
        //pour l'etat
        
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));
        List l = q.getResultList();       
        d.setIdEtat((Etat) l.get(0));
        //pour la serie
        
        try{Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
        q2.setParameter("serie", serie);
        List l2 = q2.getResultList();       
        d.setIdSerie((Serie) l2.get(0));
        }
        catch (Exception e){}
        
        
        //Insertion
        
        

        em.persist(d);
        em.getTransaction().commit();
    }
    
    @Override
    public void update(String iddoc, String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img) {
        
        //on recupere le document avec l'id
        EntityManager em = emf.createEntityManager();
        Document d=em.find(Document.class, Integer.parseInt(iddoc));
        
        em.getTransaction().begin();
        
        d.setTitre(titre);
        d.setCote(cote);
        d.setCommentaire(comm);
        d.setDescription(desc);
        try{
        d.setNumero(Integer.parseInt(numero));}catch (Exception e){}
        d.setImageDocument(img);
        
        //pour l'etat
        
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));
        List l = q.getResultList();       
        
        d.setIdEtat((Etat) l.get(0));
        //pour la serie
        try{
        Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
        q2.setParameter("serie", serie);
        List l2 = q2.getResultList();       
        
        d.setIdSerie((Serie) l2.get(0));
        }catch(Exception e){}
        
        
      
        
        
        em.getTransaction().commit();
    }

    /**
     * Retourne la liste des créateurs associés à un document
     *
     * @param id du document
     * @return
     */
    public ArrayList<Createurdocument> findCreateur(int id) {
        ArrayList<Createurdocument> cr = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT cd FROM Createurdocument cd INNER JOIN Document d ON "
                + "(cd.idDocument.idDocument = d.idDocument) WHERE d.idDocument=:id");
        q.setParameter("id", id);
        List l = q.getResultList();
        for (Object o : l) {
            cr.add((Createurdocument) o);
        }
        return cr;
    }

    /**
     * Retourne un booléen ; vrai si le document en paramètre est disponible,
     * faux sinon : le champ dateRetourne est nul donc le document est emprunté, ou
     * le champ date réservation est non nul et alors le document est réservé
     *
     * @param id du document
     * @return
     */
    @Override
    public boolean isAvailable(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Emprunt e INNER JOIN Document d ON (e.idDocument.idDocument = d.idDocument)"
                + " WHERE  (d.idDocument=:id AND e.dateRetourne IS NULL) ");
//La condition date retourné nul suffit : si le document est réservé, il n'y a pas de date indiquée,
// si le document est emprunté, il n'y a pas de date non plus
        q.setParameter("id", id);
        List l = q.getResultList();
        return l.isEmpty();

    }

    /**
     * Recherche un document suivant un liste de critères au format string :
     *      *
     * @param criteres
     * @return
     */
    @Override
    public List<Document> findDocumentSearch(ArrayList<String> criteres) {

        //recherche un document selon les criteres spécifiés
        EntityManager em = emf.createEntityManager();

        String SQL = "SELECT d FROM Document d WHERE ";
        List l = null;

        //structure de la liste critere : (0)titre (1)serie (2)auteur (3)sujet OU (0) all
        if (criteres.size() == 1) {
            String all = criteres.get(0);
            SQL = SQL + " d.titre LIKE '%" + all + "%' OR d.auteur LIKE '%" + all + "%' OR d.serie LIKE '%" + all + "%' OR"
                    + " d.sujet LIKE '%" + all + "%'";
        } else if (criteres.size() == 0) {
            //renoi de toute la base
        } else { //renvoi par critères
            for (int i = 0; i < criteres.size(); i++) {
                //On récupère le critère i
                String[] list = criteres.get(i).split(":");
                // Si la 2eme case est vide, alors le champ inpu était vide
                if (list[1] != "") {
                    SQL = SQL + " d." + list[0] + " LIKE '%" + list[1] + "%'";
                }
            }

        }
        try {
            Query q = em.createQuery(SQL);
            l = q.getResultList();
        } catch (Exception e) {
            System.out.println("erreur syntaxe requete // " + SQL);
        }

        return l;
    }
}
