/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

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

    public static DocumentManager getInstance() {
        if (theDocumentManager == null) {
            theDocumentManager = new DocumentManagerImpl();
        }
        return theDocumentManager;
    }

}
