/**
 * ********************************************************************
 * Interface DocumentManager
 *********************************************************************
 */
package Managers;

/**
 *
 * @author Victouf
 */


import Database.Createurdocument;
import Database.Document;
import Database.Genredocument;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface DocumentManager
 * @author Utilisateur
 */
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
     * @param cote
     * @return 
     */
    
    public Boolean exist(String cote);

    /**
     *
     * @return
     */
    public int getMaxId();
    
    /**
     *
     * @param titre
     * @param cote
     * @param etat
     * @param serie
     * @param numero
     * @param desc
     * @param comm
     * @param img
     * @param cnp0
     * @param cnp1
     * @param cnp2
     * @param cnp3
     * @param cnp4
     * @param genre
     */
    public void insert(String titre, String cote, String etat, String serie, String numero, String desc, String comm, String img,String cnp0,String cnp1,String cnp2,String cnp3,String cnp4,String genre);
    
    /**
     *
     * @param iddoc
     * @param titre
     * @param cote
     * @param etat
     * @param serie
     * @param numero
     * @param desc
     * @param comm
     * @param img
     * @param cnp0
     * @param cnp1
     * @param cnp2
     * @param cnp3
     * @param cnp4
     * @param genre
     */
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
    
    /**
     * Retourne la liste des genres associés à un document
     *
     * @param id du document
     * @return
     */
    public ArrayList<Genredocument> findGenre(int id);    
    
}

