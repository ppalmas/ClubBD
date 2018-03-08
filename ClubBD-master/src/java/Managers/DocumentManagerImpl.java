/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Createur;
import Database.Createurdocument;
import Database.Document;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Victouf
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
