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
@Table(name = "vote", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v")
    , @NamedQuery(name = "Vote.findByIdVote", query = "SELECT v FROM Vote v WHERE v.idVote = :idVote")})
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vote")
    private Integer idVote;
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre")
    @ManyToOne(optional = false)
    private Membre idMembre;
    @JoinColumn(name = "id_proposition", referencedColumnName = "id_proposition")
    @ManyToOne(optional = false)
    private Proposition idProposition;

    public Vote() {
    }

    public Vote(Integer idVote) {
        this.idVote = idVote;
    }

    public Integer getIdVote() {
        return idVote;
    }

    public void setIdVote(Integer idVote) {
        this.idVote = idVote;
    }

    public Membre getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Membre idMembre) {
        this.idMembre = idMembre;
    }

    public Proposition getIdProposition() {
        return idProposition;
    }

    public void setIdProposition(Proposition idProposition) {
        this.idProposition = idProposition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVote != null ? idVote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.idVote == null && other.idVote != null) || (this.idVote != null && !this.idVote.equals(other.idVote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Vote[ idVote=" + idVote + " ]";
    }
    
}
