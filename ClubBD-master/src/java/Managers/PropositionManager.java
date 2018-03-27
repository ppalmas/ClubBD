/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Proposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author centrale
 */
public interface PropositionManager {

    /**
     *
     * @return
     */
    public List<Proposition> propositions();
    
    public void insert(String titre, String commentaire);
    public int getNb_propositions();
}
