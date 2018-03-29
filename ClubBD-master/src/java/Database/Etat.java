/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author centrale
 */
@Entity
@Table(name = "etat", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etat.findAll", query = "SELECT e FROM Etat e")
    , @NamedQuery(name = "Etat.findByIdEtat", query = "SELECT e FROM Etat e WHERE e.idEtat = :idEtat")
    , @NamedQuery(name = "Etat.findByEtat", query = "SELECT e FROM Etat e WHERE e.etat = :etat")})
public class Etat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etat")
    private Integer idEtat;
    @Basic(optional = false)
    @Column(name = "etat")
    private String etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEtat")
    private Collection<Document> documentCollection;

    public Etat() {
    }

    public Etat(Integer idEtat) {
        this.idEtat = idEtat;
    }

    public Etat(Integer idEtat, String etat) {
        this.idEtat = idEtat;
        this.etat = etat;
    }

    public Integer getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Integer idEtat) {
        this.idEtat = idEtat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtat != null ? idEtat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etat)) {
            return false;
        }
        Etat other = (Etat) object;
        if ((this.idEtat == null && other.idEtat != null) || (this.idEtat != null && !this.idEtat.equals(other.idEtat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Etat[ idEtat=" + idEtat + " ]";
    }
    
}
