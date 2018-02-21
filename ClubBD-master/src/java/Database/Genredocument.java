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
@Table(name = "genredocument", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genredocument.findAll", query = "SELECT g FROM Genredocument g")
    , @NamedQuery(name = "Genredocument.findByIdGenredocument", query = "SELECT g FROM Genredocument g WHERE g.idGenredocument = :idGenredocument")})
public class Genredocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genredocument")
    private Integer idGenredocument;
    @JoinColumn(name = "id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document idDocument;
    @JoinColumn(name = "id_genre", referencedColumnName = "id_genre")
    @ManyToOne(optional = false)
    private Genre idGenre;

    public Genredocument() {
    }

    public Genredocument(Integer idGenredocument) {
        this.idGenredocument = idGenredocument;
    }

    public Integer getIdGenredocument() {
        return idGenredocument;
    }

    public void setIdGenredocument(Integer idGenredocument) {
        this.idGenredocument = idGenredocument;
    }

    public Document getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Document idDocument) {
        this.idDocument = idDocument;
    }

    public Genre getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Genre idGenre) {
        this.idGenre = idGenre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenredocument != null ? idGenredocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genredocument)) {
            return false;
        }
        Genredocument other = (Genredocument) object;
        if ((this.idGenredocument == null && other.idGenredocument != null) || (this.idGenredocument != null && !this.idGenredocument.equals(other.idGenredocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Genredocument[ idGenredocument=" + idGenredocument + " ]";
    }
    
}
