/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Recherche;
import Util.CoupleStats;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 *
 * @author centrale
 */
public interface StatistiquesManager {
    public Collection<Recherche> tout();

    /**
     *
     * @return
     */
    public ArrayList<CoupleStats> stats();
    
    public int getCrit();

    public int getNb_recherche();
}
