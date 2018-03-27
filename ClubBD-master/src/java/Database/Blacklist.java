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
@Table(name = "blacklist", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blacklist.findAll", query = "SELECT b FROM Blacklist b")
    , @NamedQuery(name = "Blacklist.findByIdBlacklist", query = "SELECT b FROM Blacklist b WHERE b.idBlacklist = :idBlacklist")})
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_blacklist")
    private Integer idBlacklist;
    @JoinColumn(name = "id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document idDocument;
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre")
    @ManyToOne(optional = false)
    private Membre idMembre;

    public Blacklist() {
    }

    public Blacklist(Integer idBlacklist) {
        this.idBlacklist = idBlacklist;
    }

    public Integer getIdBlacklist() {
        return idBlacklist;
    }

    public void setIdBlacklist(Integer idBlacklist) {
        this.idBlacklist = idBlacklist;
    }

    public Document getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Document idDocument) {
        this.idDocument = idDocument;
    }

    public Membre getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Membre idMembre) {
        this.idMembre = idMembre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBlacklist != null ? idBlacklist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blacklist)) {
            return false;
        }
        Blacklist other = (Blacklist) object;
        if ((this.idBlacklist == null && other.idBlacklist != null) || (this.idBlacklist != null && !this.idBlacklist.equals(other.idBlacklist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Blacklist[ idBlacklist=" + idBlacklist + " ]";
    }
    
}
