/**
 * ********************************************************************
 * Interface SérieManager
 *********************************************************************
 */
package Managers;

/**
 *
 * @author Victouf
 */



import Database.Serie;
import java.util.List;

/**
 * Interface SérieManager
 * @author Utilisateur
 */
public interface SerieManager {
    
    /**
     * Recherche des documents par un mot clé
     * @return liste de documents
     */
    
    public List<Serie> findSerie();

    /**
     *
     * @param titre
     * @return
     */
    public Boolean exist(String titre);

    /**
     *
     * @param seriename
     * @param seriedesc
     */
    public void insert(String seriename,String seriedesc);

    /**
     *
     * @param seriename
     * @return
     */
    public List<Serie> findSerie(String seriename);

    /**
     *
     * @param idserie
     * @param seriename
     * @param seriedesc
     * @param complet
     */
    public void update(String idserie, String seriename, String seriedesc, String complet);
}

