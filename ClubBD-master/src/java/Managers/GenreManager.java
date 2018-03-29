/**
 * ********************************************************************
 * Interface GenreManager
 *********************************************************************
 */
package Managers;


import Database.Genre;
import java.util.List;

/**
 * Interface GenreManager
 * @author Paola
 */
public interface GenreManager {

    /**
     *
     * @param nom
     */
    public void insert(String nom);

    /**
     *
     * @return
     */
    public List<Genre> findGenre();
     
    
}
