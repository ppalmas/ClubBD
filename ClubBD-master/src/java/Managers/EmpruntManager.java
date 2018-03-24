/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Emprunt;
import Database.Membre;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public interface EmpruntManager {
    
    /**
     * Retourne la liste des emprunts associés à un membre
     *
     * @param id de co
     * @return
     */
    public ArrayList<Emprunt> findEmprunt(Membre id);    
}
