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
@Table(name = "genre", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g")
    , @NamedQuery(name = "Genre.findByIdGenre", query = "SELECT g FROM Genre g WHERE g.idGenre = :idGenre")
    , @NamedQuery(name = "Genre.findByNomGenre", query = "SELECT g FROM Genre g WHERE g.nomGenre = :nomGenre")})
public class Genre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genre")
    private Integer idGenre;
    @Basic(optional = false)
    @Column(name = "nom_genre")
    private String nomGenre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenre")
    private Collection<Genrerecherche> genrerechercheCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenre")
    private Collection<Genredocument> genredocumentCollection;

    public Genre() {
    }

    public Genre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public Genre(Integer idGenre, String nomGenre) {
        this.idGenre = idGenre;
        this.nomGenre = nomGenre;
    }

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    @XmlTransient
    public Collection<Genrerecherche> getGenrerechercheCollection() {
        return genrerechercheCollection;
    }

    public void setGenrerechercheCollection(Collection<Genrerecherche> genrerechercheCollection) {
        this.genrerechercheCollection = genrerechercheCollection;
    }

    @XmlTransient
    public Collection<Genredocument> getGenredocumentCollection() {
        return genredocumentCollection;
    }

    public void setGenredocumentCollection(Collection<Genredocument> genredocumentCollection) {
        this.genredocumentCollection = genredocumentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenre != null ? idGenre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.idGenre == null && other.idGenre != null) || (this.idGenre != null && !this.idGenre.equals(other.idGenre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Genre[ idGenre=" + idGenre + " ]";
    }
    
}
