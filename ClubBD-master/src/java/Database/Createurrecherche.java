/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author centrale
 */
@Entity
@Table(name = "createurrecherche", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Createurrecherche.findAll", query = "SELECT c FROM Createurrecherche c")
    , @NamedQuery(name = "Createurrecherche.findByIdCreateurrecherche", query = "SELECT c FROM Createurrecherche c WHERE c.idCreateurrecherche = :idCreateurrecherche")})
public class Createurrecherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_createurrecherche")
    private Integer idCreateurrecherche;
    @JoinColumn(name = "id_createur", referencedColumnName = "id_createur")
    @ManyToOne(optional = false)
    private Createur idCreateur;
    @JoinColumn(name = "id_recherche", referencedColumnName = "id_recherche")
    @ManyToOne(optional = false)
    private Recherche idRecherche;

    public Createurrecherche() {
    }

    public Createurrecherche(Integer idCreateurrecherche) {
        this.idCreateurrecherche = idCreateurrecherche;
    }

    public Integer getIdCreateurrecherche() {
        return idCreateurrecherche;
    }

    public void setIdCreateurrecherche(Integer idCreateurrecherche) {
        this.idCreateurrecherche = idCreateurrecherche;
    }

    public Createur getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Createur idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Recherche getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(Recherche idRecherche) {
        this.idRecherche = idRecherche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreateurrecherche != null ? idCreateurrecherche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Createurrecherche)) {
            return false;
        }
        Createurrecherche other = (Createurrecherche) object;
        if ((this.idCreateurrecherche == null && other.idCreateurrecherche != null) || (this.idCreateurrecherche != null && !this.idCreateurrecherche.equals(other.idCreateurrecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Createurrecherche[ idCreateurrecherche=" + idCreateurrecherche + " ]";
    }
    
}
