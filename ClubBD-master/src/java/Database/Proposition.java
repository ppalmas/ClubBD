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
@Table(name = "proposition", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proposition.findAll", query = "SELECT p FROM Proposition p")
    , @NamedQuery(name = "Proposition.findByIdProposition", query = "SELECT p FROM Proposition p WHERE p.idProposition = :idProposition")
    , @NamedQuery(name = "Proposition.findByNomProposition", query = "SELECT p FROM Proposition p WHERE p.nomProposition = :nomProposition")
    , @NamedQuery(name = "Proposition.findByCommentaireProposition", query = "SELECT p FROM Proposition p WHERE p.commentaireProposition = :commentaireProposition")})
public class Proposition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proposition")
    private Integer idProposition;
    @Basic(optional = false)
    @Column(name = "nom_proposition")
    private String nomProposition;
    @Basic(optional = false)
    @Column(name = "commentaire_proposition")
    private String commentaireProposition;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProposition")
    private Collection<Vote> voteCollection;

    public Proposition() {
    }

    public Proposition(Integer idProposition) {
        this.idProposition = idProposition;
    }

    public Proposition(Integer idProposition, String nomProposition, String commentaireProposition) {
        this.idProposition = idProposition;
        this.nomProposition = nomProposition;
        this.commentaireProposition = commentaireProposition;
    }

    public Integer getIdProposition() {
        return idProposition;
    }

    public void setIdProposition(Integer idProposition) {
        this.idProposition = idProposition;
    }

    public String getNomProposition() {
        return nomProposition;
    }

    public void setNomProposition(String nomProposition) {
        this.nomProposition = nomProposition;
    }

    public String getCommentaireProposition() {
        return commentaireProposition;
    }

    public void setCommentaireProposition(String commentaireProposition) {
        this.commentaireProposition = commentaireProposition;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProposition != null ? idProposition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proposition)) {
            return false;
        }
        Proposition other = (Proposition) object;
        if ((this.idProposition == null && other.idProposition != null) || (this.idProposition != null && !this.idProposition.equals(other.idProposition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Proposition[ idProposition=" + idProposition + " ]";
    }
    
}
