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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author centrale
 */
@Entity
@Table(name = "une", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Une.findAll", query = "SELECT u FROM Une u")
    , @NamedQuery(name = "Une.findByIdUne", query = "SELECT u FROM Une u WHERE u.idUne = :idUne")
    , @NamedQuery(name = "Une.findByNomUne", query = "SELECT u FROM Une u WHERE u.nomUne = :nomUne")
    , @NamedQuery(name = "Une.findByContenuUne", query = "SELECT u FROM Une u WHERE u.contenuUne = :contenuUne")})
public class Une implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_une")
    private Integer idUne;
    @Basic(optional = false)
    @Column(name = "nom_une")
    private String nomUne;
    @Basic(optional = false)
    @Column(name = "contenu_une")
    private String contenuUne;

    public Une() {
    }

    public Une(Integer idUne) {
        this.idUne = idUne;
    }

    public Une(Integer idUne, String nomUne, String contenuUne) {
        this.idUne = idUne;
        this.nomUne = nomUne;
        this.contenuUne = contenuUne;
    }

    public Integer getIdUne() {
        return idUne;
    }

    public void setIdUne(Integer idUne) {
        this.idUne = idUne;
    }

    public String getNomUne() {
        return nomUne;
    }

    public void setNomUne(String nomUne) {
        this.nomUne = nomUne;
    }

    public String getContenuUne() {
        return contenuUne;
    }

    public void setContenuUne(String contenuUne) {
        this.contenuUne = contenuUne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUne != null ? idUne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Une)) {
            return false;
        }
        Une other = (Une) object;
        if ((this.idUne == null && other.idUne != null) || (this.idUne != null && !this.idUne.equals(other.idUne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Une[ idUne=" + idUne + " ]";
    }
    
}
