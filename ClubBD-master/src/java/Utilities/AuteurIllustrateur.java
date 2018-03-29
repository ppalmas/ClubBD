/**
 * ********************************************************************
 * Class AuteurIllustrateur
 * Définition d'un type AuteurIllustrateur
 *********************************************************************
 */
package Utilities;

/**
 * Définition d'un type AuteurIllustrateur
 * @author Kevin
 */
public class AuteurIllustrateur {

    /**
     *
     */
    public String nom;

    /**
     *
     */
    public String prenom;

    /**
     *
     */
    public String poste;

    /**
     *
     * @param nom
     * @param prenom
     * @param poste
     */
    public AuteurIllustrateur(String nom, String prenom, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @param poste the poste to set
     */
    public void setPoste(String poste) {
        this.poste = poste;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the poste
     */
    public String getPoste() {
        return poste;
    }
    
}
