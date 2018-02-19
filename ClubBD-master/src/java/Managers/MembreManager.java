/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Membre;
import Database.Statut;

/**
 *
 * @author Paola
 */
public interface MembreManager {
    /**
     * Recherche d'un membre par son email
     * @param email
     * @return 
     */
    public Membre findMembreByEmail(String email) ;
    
    /**
     * Insertion d'un nouveau membre
     * @param email
     * @param mdp
     * @param name
     * @param firstname 
     * @param idStatut
     */
    public void insert(String email, String mdp, String name, String firstname, Statut idStatut);
    
    /**
     * Modification des infos d'un membre
     * @param m membre
     * @param name
     * @param firstname
     * @param email 
     */
    public void updateInfos (Membre m, String name, String firstname, String email);
    
    /**
     * Mise à jour du mot de passe d'une personne
     *
     * @param m Membre
     * @param mdp Mot de passe
     */
    public void updateMdp(Membre m, String mdp);
    /**
     * Trouver un membre par son id de connexion
     * @param id de connexion
     * @return 
     */
    public Membre findMembre(String id) ;
}
