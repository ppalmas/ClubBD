/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

/**
 *
 * @author Victouf
 */


import Database.Document;
import java.util.ArrayList;
import java.util.List;


public interface DocumentManager {
    
    /**
     * Recherche des documents par un mot clé
     * @param mot
     * @return liste de documents
     */
    public List<Document> findDocumentBy1WordTitle(String mot) ;
    public List<Document> findDocument(ArrayList<String> criteres);}

