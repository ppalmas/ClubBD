/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Createur;
import java.util.List;

/**
 *
 * @author Paola
 */
public interface CreateurManager {
    public void insert(String nomcrea, String prenomcrea);
     public List<Createur> findCreateur();
     
    
}
