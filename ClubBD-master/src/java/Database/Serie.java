/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "serie", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
    , @NamedQuery(name = "Serie.findByIdSerie", query = "SELECT s FROM Serie s WHERE s.idSerie = :idSerie")
    , @NamedQuery(name = "Serie.findByNomSerie", query = "SELECT s FROM Serie s WHERE s.nomSerie = :nomSerie")
    , @NamedQuery(name = "Serie.findByComplet", query = "SELECT s FROM Serie s WHERE s.complet = :complet")
    , @NamedQuery(name = "Serie.findBySerieDescription", query = "SELECT s FROM Serie s WHERE s.serieDescription = :serieDescription")})
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_serie")
    private Integer idSerie;
    @Basic(optional = false)
    @Column(name = "nom_serie")
    private String nomSerie;
    @Basic(optional = false)
    @Column(name = "complet")
    private boolean complet;
    @Basic(optional = false)
    @Column(name = "serie_description")
    private String serieDescription;
    @OneToMany(mappedBy = "idSerie")
    private Collection<Document> documentCollection;

    public Serie() {
    }

    public Serie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Serie(Integer idSerie, String nomSerie, boolean complet, String serieDescription) {
        this.idSerie = idSerie;
        this.nomSerie = nomSerie;
        this.complet = complet;
        this.serieDescription = serieDescription;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public boolean getComplet() {
        return complet;
    }

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    public String getSerieDescription() {
        return serieDescription;
    }

    public void setSerieDescription(String serieDescription) {
        this.serieDescription = serieDescription;
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
        hash += (idSerie != null ? idSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.idSerie == null && other.idSerie != null) || (this.idSerie != null && !this.idSerie.equals(other.idSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Serie[ idSerie=" + idSerie + " ]";
    }
    
}
