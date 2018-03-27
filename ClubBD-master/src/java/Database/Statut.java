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
@Table(name = "statut", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statut.findAll", query = "SELECT s FROM Statut s")
    , @NamedQuery(name = "Statut.findByIdStatut", query = "SELECT s FROM Statut s WHERE s.idStatut = :idStatut")
    , @NamedQuery(name = "Statut.findByNomStatut", query = "SELECT s FROM Statut s WHERE s.nomStatut = :nomStatut")})
public class Statut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statut")
    private Integer idStatut;
    @Basic(optional = false)
    @Column(name = "nom_statut")
    private String nomStatut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStatut")
    private Collection<Membre> membreCollection;

    public Statut() {
    }

    public Statut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public Statut(Integer idStatut, String nomStatut) {
        this.idStatut = idStatut;
        this.nomStatut = nomStatut;
    }

    public Integer getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Integer idStatut) {
        this.idStatut = idStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    @XmlTransient
    public Collection<Membre> getMembreCollection() {
        return membreCollection;
    }

    public void setMembreCollection(Collection<Membre> membreCollection) {
        this.membreCollection = membreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatut != null ? idStatut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statut)) {
            return false;
        }
        Statut other = (Statut) object;
        if ((this.idStatut == null && other.idStatut != null) || (this.idStatut != null && !this.idStatut.equals(other.idStatut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Statut[ idStatut=" + idStatut + " ]";
    }
    
}
