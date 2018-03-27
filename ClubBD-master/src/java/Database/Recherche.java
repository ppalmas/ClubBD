/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author centrale
 */
@Entity
@Table(name = "recherche", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recherche.findAll", query = "SELECT r FROM Recherche r")
    , @NamedQuery(name = "Recherche.findByIdRecherche", query = "SELECT r FROM Recherche r WHERE r.idRecherche = :idRecherche")
    , @NamedQuery(name = "Recherche.findByTitre", query = "SELECT r FROM Recherche r WHERE r.titre = :titre")
    , @NamedQuery(name = "Recherche.findBySerie", query = "SELECT r FROM Recherche r WHERE r.serie = :serie")
    , @NamedQuery(name = "Recherche.findByDateRecherche", query = "SELECT r FROM Recherche r WHERE r.dateRecherche = :dateRecherche")})
public class Recherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recherche")
    private Integer idRecherche;
    @Column(name = "titre")
    private String titre;
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "date_recherche")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRecherche;

    public Recherche() {
    }

    public Recherche(Integer idRecherche) {
        this.idRecherche = idRecherche;
    }

    public Recherche(Integer idRecherche, Date dateRecherche) {
        this.idRecherche = idRecherche;
        this.dateRecherche = dateRecherche;
    }

    public Integer getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(Integer idRecherche) {
        this.idRecherche = idRecherche;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getDateRecherche() {
        return dateRecherche;
    }

    public void setDateRecherche(Date dateRecherche) {
        this.dateRecherche = dateRecherche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecherche != null ? idRecherche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recherche)) {
            return false;
        }
        Recherche other = (Recherche) object;
        if ((this.idRecherche == null && other.idRecherche != null) || (this.idRecherche != null && !this.idRecherche.equals(other.idRecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Recherche[ idRecherche=" + idRecherche + " ]";
    }
    
}
