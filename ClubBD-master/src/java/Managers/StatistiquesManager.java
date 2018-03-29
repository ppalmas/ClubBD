/**
 * ********************************************************************
 * Interface StatistiquesManager
 *********************************************************************
 */
package Managers;

import Database.Recherche;
import Util.CoupleStats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Interface StatistiquesManager
 * @author centrale
 */
public interface StatistiquesManager {

    /**
     *
     * @return
     */
    public Collection<Recherche> tout();

    /**
     *
     * @return
     */
    public ArrayList<CoupleStats> stats();
    
    /**
     *
     * @return
     */
    public int getCrit();

    /**
     *
     * @return
     */
    public int getNb_recherche();
}
