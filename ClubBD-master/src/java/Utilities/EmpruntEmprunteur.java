/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author Kevin
 */
public class EmpruntEmprunteur {
    public Integer iddoc;
    public String titre;
    public String cote;
    public String date_reserve;
    public String date_emprunt;
    public String date_retour;
    public String date_retourne;
    public String nom;
    public String prenom;
    public String mail;

    public EmpruntEmprunteur(Integer iddoc, String titre, String cote, String date_reserve, String date_emprunt, String date_retour, String date_retourne, String mail, String nom, String prenom) {
        this.iddoc = iddoc;
        this.titre = titre;
        this.cote = cote;
        this.date_reserve = date_reserve;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
        this.date_retourne = date_retourne;
        this.mail = mail;
        this.prenom = prenom;
        this.nom = nom;
    }


    /**
     * @return the iddoc
     */
    public Integer getIddoc() {
        return iddoc;
    }

    /**
     * @param iddoc the iddoc to set
     */
    public void setIddoc(Integer iddoc) {
        this.iddoc = iddoc;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the cote
     */
    public String getCote() {
        return cote;
    }

    /**
     * @param cote the cote to set
     */
    public void setCote(String cote) {
        this.cote = cote;
    }

    /**
     * @return the date_reserve
     */
    public String getDate_reserve() {
        return date_reserve;
    }

    /**
     * @param date_reserve the date_reserve to set
     */
    public void setDate_reserve(String date_reserve) {
        this.date_reserve = date_reserve;
    }

    /**
     * @return the date_emprunt
     */
    public String getDate_emprunt() {
        return date_emprunt;
    }

    /**
     * @param date_emprunt the date_emprunt to set
     */
    public void setDate_emprunt(String date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    /**
     * @return the date_retour
     */
    public String getDate_retour() {
        return date_retour;
    }

    /**
     * @param date_retour the date_retour to set
     */
    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

    /**
     * @return the date_retourne
     */
    public String getDate_retourne() {
        return date_retourne;
    }

    /**
     * @param date_retourne the date_retourne to set
     */
    public void setDate_retourne(String date_retourne) {
        this.date_retourne = date_retourne;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}
