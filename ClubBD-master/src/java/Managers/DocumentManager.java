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


import Database.Createurdocument;
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
    /**
     * Recherche de documents suivant une liste de critères (titre, auteur, cote)
     * @param criteres
     * @return 
     */
    public List<Document> findDocument(ArrayList<String> criteres);
    /**
     * Récupérer l'id max des documents de la bdd (utilisé pour la suggestion de lecture)
     * @return 
     */
    public int getMaxId();
    
    public void insert(String titre, String cote);
    
    /**
     * Trouver un document par son id
     * @param id
     * @return 
     */
    public Document findDocument(int id);
    /**
     * Retourne la liste des créateurs associée à un document
     * @param id
     * @return 
     */
    public ArrayList<Createurdocument> findCreateur(int id);
    
}

