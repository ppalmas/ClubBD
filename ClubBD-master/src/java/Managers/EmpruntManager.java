/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Database.Document;
import Database.Emprunt;
import Database.Membre;
import java.util.ArrayList;
import java.util.Date;

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
    
    /**
     * Ajout d'une réservation
     * @param idmb idmembre
     * @param iddoc iddocument
     * @param date_reserve date de réservation
     */
    public void insert(Integer idmb, Integer iddoc, Date date_reserve);
    
    /**
     * Confirmation de l'emprunt
     * @param e
     * @param date_retour
     * @param date_emprunt
     * @param id_staff
     */
    public void updateEmprunt (Emprunt e, Date date_retour, Date date_emprunt, Integer id_staff);
    
    /**
     * Confirmation de l'emprunt
     * @param e
     * @param date_retourne
     */
    public void updateRetourne (Emprunt e, Date date_retourne);
    
    /**
     * Retourne la liste des emprunts associés à un document
     *
     * @param idDoc de membre
     * @return
     */
    public ArrayList<Emprunt> findEmpruntDoc(Document idDoc);
    
    /**
     * Retrouver un emprunt par son id
     *
     * @param id id du document
     * @return Emprunt correspondant
     */
    public Emprunt findEmpruntByID(Integer id);  
    
    /**
     * Retourne la liste des emprunts/réservations en cours
     * @return
     */
    public ArrayList<Emprunt> findEmprunts();  
}
