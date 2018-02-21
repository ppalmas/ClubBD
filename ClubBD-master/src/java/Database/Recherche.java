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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recherche", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recherche.findAll", query = "SELECT r FROM Recherche r")
    , @NamedQuery(name = "Recherche.findByIdRecherche", query = "SELECT r FROM Recherche r WHERE r.idRecherche = :idRecherche")
    , @NamedQuery(name = "Recherche.findByTitre", query = "SELECT r FROM Recherche r WHERE r.titre = :titre")
    , @NamedQuery(name = "Recherche.findByNumero", query = "SELECT r FROM Recherche r WHERE r.numero = :numero")
    , @NamedQuery(name = "Recherche.findByCote", query = "SELECT r FROM Recherche r WHERE r.cote = :cote")})
public class Recherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recherche")
    private Integer idRecherche;
    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "cote")
    private String cote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecherche")
    private Collection<Createurrecherche> createurrechercheCollection;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne
    private Serie idSerie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRecherche")
    private Collection<Genrerecherche> genrerechercheCollection;

    public Recherche() {
    }

    public Recherche(Integer idRecherche) {
        this.idRecherche = idRecherche;
    }

    public Recherche(Integer idRecherche, String titre, int numero, String cote) {
        this.idRecherche = idRecherche;
        this.titre = titre;
        this.numero = numero;
        this.cote = cote;
    }

    public Integer getIdRecherche() {
        return idRecherche;
    }

    public void setIdRecherche(Integer idRecherche) {
        this.idRecherche = idRecherche;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    @XmlTransient
    public Collection<Createurrecherche> getCreateurrechercheCollection() {
        return createurrechercheCollection;
    }

    public void setCreateurrechercheCollection(Collection<Createurrecherche> createurrechercheCollection) {
        this.createurrechercheCollection = createurrechercheCollection;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    @XmlTransient
    public Collection<Genrerecherche> getGenrerechercheCollection() {
        return genrerechercheCollection;
    }

    public void setGenrerechercheCollection(Collection<Genrerecherche> genrerechercheCollection) {
        this.genrerechercheCollection = genrerechercheCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecherche != null ? idRecherche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recherche)) {
            return false;
        }
        Recherche other = (Recherche) object;
        if ((this.idRecherche == null && other.idRecherche != null) || (this.idRecherche != null && !this.idRecherche.equals(other.idRecherche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Recherche[ idRecherche=" + idRecherche + " ]";
    }
    
}
