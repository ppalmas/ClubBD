/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

/**
 *
 * @author Paola
 */
public interface PropositionManager {
    /**
     * Méthode pour l'insertion d'une proposition dans la bdd
     * @param titre
     * @param commentaire 
     */
    public void insert(String titre, String commentaire);
    
}
