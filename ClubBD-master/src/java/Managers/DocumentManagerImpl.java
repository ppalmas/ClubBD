/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Document;
import Database.Membre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Victouf
 */
public class DocumentManagerImpl implements DocumentManager{
    
    private EntityManagerFactory emf;
    private static DocumentManagerImpl theDocumentManager;
    
      /**
     * Recherche des documents par un mot clé
     * @param mot
     * @return liste de documents
     */
    @Override
    public List<Document> findDocumentBy1WordTitle(String mot)
    {
        EntityManager em = emf.createEntityManager();
        mot = "%"+mot+"%";
        Query q = em.createQuery("SELECT d FROM Document d WHERE  d.titre LIKE :mot");
        q.setParameter("mot", mot);
        List l = q.getResultList();
        return l;
    }
}
