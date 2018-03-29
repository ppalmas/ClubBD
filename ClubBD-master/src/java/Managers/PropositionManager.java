/**
 * ********************************************************************
 * Interface PropositionManager
 *********************************************************************
 */
package Managers;

import Database.Proposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Interface PropositionManager
 * @author centrale
 */
public interface PropositionManager {

    /**
     *
     * @return
     */
    public List<Proposition> propositions();
    
    /**
     *
     * @param titre
     * @param commentaire
     */
    public void insert(String titre, String commentaire);

    /**
     *
     * @return
     */
    public int getNb_propositions();
}
