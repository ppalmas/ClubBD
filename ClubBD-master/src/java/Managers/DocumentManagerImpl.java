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
     * dans cette liste, le premier élément est le titre, le second l'auteur, le troisième la cote
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
     * @return id maximum des documents de la base de données
     */
    @Override
    public int getMaxId(){
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
        System.out.println("1");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("2");
        Document d = new Document();
        System.out.println("3");
        d.setTitre(titre);
        d.setCote(cote);
        d.setCommentaire(comm);
        d.setDescription(desc);
        try{
        d.setNumero(Integer.parseInt(numero));}catch (Exception e){}
        d.setImageDocument(img);
        System.out.println("4");
        
        //pour l'etat
        
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));
        System.out.println("5");
        List l = q.getResultList();       
        System.out.println("6");
        d.setIdEtat((Etat) l.get(0));
        System.out.println("7");
        //pour la serie
        
        try{Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
        q2.setParameter("serie", serie);
        List l2 = q2.getResultList();       
        System.out.println("8");
        d.setIdSerie((Serie) l2.get(0));
        System.out.println("9");}
        catch (Exception e){}
        
        
        //Insertion
        
        
        em.persist(d);
        System.out.println("10");
        em.getTransaction().commit();
        System.out.println("11");
    }
    
    @Override
    public void update(String iddoc, String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img) {
        
        //on recupere le document avec l'id
        EntityManager em = emf.createEntityManager();
        Document d=em.find(Document.class, Integer.parseInt(iddoc));
        System.out.println(d);
        
        em.getTransaction().begin();
        
        d.setTitre(titre);
        System.out.println("1");
        d.setCote(cote);
        System.out.println("2");
        d.setCommentaire(comm);
        System.out.println("3");
        d.setDescription(desc);
        System.out.println("4");
        try{
        d.setNumero(Integer.parseInt(numero));}catch (Exception e){}
        System.out.println("5");
        d.setImageDocument(img);
        System.out.println("6");
        
        //pour l'etat
        
        Query q = em.createQuery("SELECT e FROM Etat e WHERE  e.idEtat=:etat");
        q.setParameter("etat", Integer.parseInt(etat));
        List l = q.getResultList();       
        
        d.setIdEtat((Etat) l.get(0));
        System.out.println("passe par là2");
        //pour la serie
        try{
        Query q2 = em.createQuery("SELECT s FROM Serie s WHERE  s.nomSerie=:serie");
        q2.setParameter("serie", serie);
        List l2 = q2.getResultList();       
        
        d.setIdSerie((Serie) l2.get(0));
        System.out.println("passe par là3");
        }catch(Exception e){}
        
        
      
        
        System.out.println("passe par là4");
        
        em.getTransaction().commit();
    }
    
    /**
     * Retourne la liste des créateurs associée à un document
     * @param id
     * @return 
     */
    public ArrayList<Createurdocument> findCreateur(int id){
        ArrayList<Createurdocument> cr = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT DISTINCT cd FROM Createurdocument cd JOIN Document d WHERE  d.idDocument=:id");
       //SELECT distinct l FROM Location l INNER JOIN  Favorite f ON (l.locationId = f.multimediaId.locationId.locationId) WHERE  f.personId=:p
        q.setParameter("id", id);
        List l = q.getResultList();
        for (Object o : l) {
            cr.add((Createurdocument) o);
        }
        return cr;
    }

}
