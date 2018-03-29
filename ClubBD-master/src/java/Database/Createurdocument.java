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
@Table(name = "createurdocument", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Createurdocument.findAll", query = "SELECT c FROM Createurdocument c")
    , @NamedQuery(name = "Createurdocument.findByIdCreateurdocument", query = "SELECT c FROM Createurdocument c WHERE c.idCreateurdocument = :idCreateurdocument")
    , @NamedQuery(name = "Createurdocument.findByPoste", query = "SELECT c FROM Createurdocument c WHERE c.poste = :poste")})
public class Createurdocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_createurdocument")
    private Integer idCreateurdocument;
    @Column(name = "poste")
    private String poste;
    @JoinColumn(name = "id_createur", referencedColumnName = "id_createur")
    @ManyToOne(optional = false)
    private Createur idCreateur;
    @JoinColumn(name = "id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document idDocument;

    public Createurdocument() {
    }

    public Createurdocument(Integer idCreateurdocument) {
        this.idCreateurdocument = idCreateurdocument;
    }

    public Integer getIdCreateurdocument() {
        return idCreateurdocument;
    }

    public void setIdCreateurdocument(Integer idCreateurdocument) {
        this.idCreateurdocument = idCreateurdocument;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Createur getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(Createur idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Document getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Document idDocument) {
        this.idDocument = idDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreateurdocument != null ? idCreateurdocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Createurdocument)) {
            return false;
        }
        Createurdocument other = (Createurdocument) object;
        if ((this.idCreateurdocument == null && other.idCreateurdocument != null) || (this.idCreateurdocument != null && !this.idCreateurdocument.equals(other.idCreateurdocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Createurdocument[ idCreateurdocument=" + idCreateurdocument + " ]";
    }
    
}
