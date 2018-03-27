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
    public List<Document> findDocumentCr(ArrayList<String> criteres);
    /**
     * Récupérer l'id max des documents de la bdd (utilisé pour la suggestion de lecture)
     * @return 
     */
    public int getMaxId();
    
    public void insert(String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img,String cnp0,String cnp1,String cnp2,String cnp3,String cnp4,String genre);
    
    public void update(String iddoc, String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img, String cnp0, String cnp1, String cnp2,String cnp3,String cnp4, String genre);
    
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
    
    /**
     * Retourne un booléen ; vrai si le document en paramètre est disponible,
     * faux sinon (le champ dateRetourne est nul)
     * @param id du document
     * @return 
     */
    public boolean isAvailable(int id);
    /**
     * Recherche un document suivant un liste de critères au format string :
     *      *
     * @param criteres
     * @param b si b vaut true, l'utilisateur a complété le champ toute recherche (pas de critères)
     * @return
     */
    public List<Document> findDocumentSearch(ArrayList<String> criteres, Boolean b);
    
}

