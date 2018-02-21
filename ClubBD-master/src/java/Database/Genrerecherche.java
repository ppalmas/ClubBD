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
@Table(name = "genrerecherche", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genrerecherche.findAll", query = "SELECT g FROM Genrerecherche g")
    , @NamedQuery(name = "Genrerecherche.findByIdGenrerecherche", query = "SELECT g FROM Genrerecherche g WHERE g.idGenrerecherche = :idGenrerecherche")})
public class Genrerecherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genrerecherche")
    private Integer idGenrerecherche;
    @JoinColumn(name = "id_genre", referencedColumnName = "id_genre")
    @ManyToOne(optional = false)
    private Genre idGenre;
    @JoinColumn(name = "id_recherche", referencedColumnName = "id_recherche")
    @ManyToOne(optional = false)
    private Recherche idRecherche;

    public Genrerecherche() {
    }

    public Genrerecherche(Integer idGenrerecherche) {
        this.idGenrerecherche = idGenrerecherche;
    }

    public Integer getIdGenrerecherche() {
        return idGenrerecherche;
    }

    public void setIdGenrerecherche(Integer idGenrerecherche) {
        this.idGenrerecherche = idGenrerecherche;
    }

    public Genre getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Genre idGenre) {
        this.idGenre = idGenre;
    }

    public Recherche getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(Recherche idRecherche) {
        this.idRecherche = idRecherche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenrerecherche != null ? idGenrerecherche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genrerecherche)) {
            return false;
        }
        Genrerecherche other = (Genrerecherche) object;
        if ((this.idGenrerecherche == null && other.idGenrerecherche != null) || (this.idGenrerecherche != null && !this.idGenrerecherche.equals(other.idGenrerecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Genrerecherche[ idGenrerecherche=" + idGenrerecherche + " ]";
    }
    
}
