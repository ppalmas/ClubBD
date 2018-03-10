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



import Database.Serie;
import java.util.List;


public interface SerieManager {
    
    /**
     * Recherche des documents par un mot clé
     * @param mot
     * @return liste de documents
     */
    
    public List<Serie> findSerie();
}

