/**
 * ********************************************************************
 * Interface CreateurManager
 *********************************************************************
 */
package Managers;

import Database.Createur;
import java.util.List;

/**
 * Interface CreateurManager
 * @author Paola
 */
public interface CreateurManager {

    /**
     *
     * @param nomcrea
     * @param prenomcrea
     */
    public void insert(String nomcrea, String prenomcrea);

    /**
     *
     * @return
     */
    public List<Createur> findCreateur();
     
    
}
