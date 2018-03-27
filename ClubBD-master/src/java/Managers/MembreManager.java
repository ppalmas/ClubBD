/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Membre;
import Database.Statut;
import java.util.List;

/**
 *
 * @author Paola
 */
public interface MembreManager {

    /**
     * Recherche d'un membre par son email
     *
     * @param email
     * @return
     */
    public Membre findMembreByEmail(String email);

    public List<Membre> findMembre(String prenom, String nom);

    public void updateStat(Integer idstat, Integer idmembre);

    /**
     * Renvoyer la liste de tous les membres ayant cette adresse mail
     *
     * @param email recherché
     * @return liste de Personnes
     */
    public List<Membre> listMembreByEmail(String email);

    /**
     * Renvoyer la liste de tous les membres ayant ce nom
     *
     * @param nom recherché
     * @return liste de Personnes
     */
    public List<Membre> listMembreByNom(String nom);

    /**
     * Renvoyer la liste de tous les membres ayant ce prenom
     *
     * @param prenom recherché
     * @return liste de Personnes
     */
    public List<Membre> listMembreByPrenom(String prenom);

    /**
     * Renvoyer la liste de tous les membres ayant ce prenom et ce nom
     *
     * @param prenom recherché
     * @param nom recherché
     * @return liste de Personnes
     */
    public List<Membre> listMembreByPrenomNom(String prenom, String nom);

    /**
     * Insertion d'un nouveau membre
     *
     * @param email
     * @param mdp
     * @param name
     * @param firstname
     * @param idStatut
     */
    public void insert(String email, String mdp, String name, String firstname, Statut idStatut);

    /**
     * Renvoyer la liste de tous les membres
     *
     * @return liste de Personnes
     */
    public List<Membre> listMembre();

    /**
     * Modification des infos d'un membre
     *
     * @param m membre
     * @param name
     * @param firstname
     * @param email
     */
    public void updateInfos(Membre m, String name, String firstname, String email);

    /**
     * Mise à jour du mot de passe d'une personne
     *
     * @param m Membre
     * @param mdp Mot de passe
     */
    public void updateMdp(Membre m, String mdp);

    /**
     * Trouver un membre par son id de connexion
     *
     * @param id de connexion
     * @return
     */
    public Membre findMembre(String id);
}
