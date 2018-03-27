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
@Table(name = "createur", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Createur.findAll", query = "SELECT c FROM Createur c")
    , @NamedQuery(name = "Createur.findByIdCreateur", query = "SELECT c FROM Createur c WHERE c.idCreateur = :idCreateur")
    , @NamedQuery(name = "Createur.findByNomCreateur", query = "SELECT c FROM Createur c WHERE c.nomCreateur = :nomCreateur")
    , @NamedQuery(name = "Createur.findByPrenomCreateur", query = "SELECT c FROM Createur c WHERE c.prenomCreateur = :prenomCreateur")})
public class Createur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_createur")
    private Integer idCreateur;
    @Basic(optional = false)
    @Column(name = "nom_createur")
    private String nomCreateur;
    @Basic(optional = false)
    @Column(name = "prenom_createur")
    private String prenomCreateur;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCreateur")
    private Collection<Createurdocument> createurdocumentCollection;

    public Createur() {
    }

    public Createur(Integer idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Createur(Integer idCreateur, String nomCreateur, String prenomCreateur) {
        this.idCreateur = idCreateur;
        this.nomCreateur = nomCreateur;
        this.prenomCreateur = prenomCreateur;
    }

    public Integer getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Integer idCreateur) {
        this.idCreateur = idCreateur;
    }

    public String getNomCreateur() {
        return nomCreateur;
    }

    public void setNomCreateur(String nomCreateur) {
        this.nomCreateur = nomCreateur;
    }

    public String getPrenomCreateur() {
        return prenomCreateur;
    }

    public void setPrenomCreateur(String prenomCreateur) {
        this.prenomCreateur = prenomCreateur;
    }

    

    @XmlTransient
    public Collection<Createurdocument> getCreateurdocumentCollection() {
        return createurdocumentCollection;
    }

    public void setCreateurdocumentCollection(Collection<Createurdocument> createurdocumentCollection) {
        this.createurdocumentCollection = createurdocumentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreateur != null ? idCreateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Createur)) {
            return false;
        }
        Createur other = (Createur) object;
        if ((this.idCreateur == null && other.idCreateur != null) || (this.idCreateur != null && !this.idCreateur.equals(other.idCreateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Createur[ idCreateur=" + idCreateur + " ]";
    }
    
}
