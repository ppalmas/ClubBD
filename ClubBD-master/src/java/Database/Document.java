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
@Table(name = "document", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d")
    , @NamedQuery(name = "Document.findByIdDocument", query = "SELECT d FROM Document d WHERE d.idDocument = :idDocument")
    , @NamedQuery(name = "Document.findByTitre", query = "SELECT d FROM Document d WHERE d.titre = :titre")
    , @NamedQuery(name = "Document.findByCote", query = "SELECT d FROM Document d WHERE d.cote = :cote")
    , @NamedQuery(name = "Document.findByNumero", query = "SELECT d FROM Document d WHERE d.numero = :numero")
    , @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description")
    , @NamedQuery(name = "Document.findByCommentaire", query = "SELECT d FROM Document d WHERE d.commentaire = :commentaire")
    , @NamedQuery(name = "Document.findByImageDocument", query = "SELECT d FROM Document d WHERE d.imageDocument = :imageDocument")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_document")
    private Integer idDocument;
    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @Column(name = "cote")
    private String cote;
    @Column(name = "numero")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "commentaire")
    private String commentaire;
    @Basic(optional = false)
    @Column(name = "image_document")
    private String imageDocument;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocument")
    private Collection<Emprunt> empruntCollection;
    @JoinColumn(name = "id_etat", referencedColumnName = "id_etat")
    @ManyToOne(optional = false)
    private Etat idEtat;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne
    private Serie idSerie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocument")
    private Collection<Blacklist> blacklistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocument")
    private Collection<Createurdocument> createurdocumentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocument")
    private Collection<Genredocument> genredocumentCollection;

    public Document() {
    }

    public Document(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public Document(Integer idDocument, String titre, String cote, String description, String commentaire, String imageDocument) {
        this.idDocument = idDocument;
        this.titre = titre;
        this.cote = cote;
        this.description = description;
        this.commentaire = commentaire;
        this.imageDocument = imageDocument;
    }

    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCote() {
        return cote;
    }

    public void setCote(String cote) {
        this.cote = cote;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getImageDocument() {
        return imageDocument;
    }

    public void setImageDocument(String imageDocument) {
        this.imageDocument = imageDocument;
    }

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection() {
        return empruntCollection;
    }

    public void setEmpruntCollection(Collection<Emprunt> empruntCollection) {
        this.empruntCollection = empruntCollection;
    }

    public Etat getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(Etat idEtat) {
        this.idEtat = idEtat;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    @XmlTransient
    public Collection<Blacklist> getBlacklistCollection() {
        return blacklistCollection;
    }

    public void setBlacklistCollection(Collection<Blacklist> blacklistCollection) {
        this.blacklistCollection = blacklistCollection;
    }

    @XmlTransient
    public Collection<Createurdocument> getCreateurdocumentCollection() {
        return createurdocumentCollection;
    }

    public void setCreateurdocumentCollection(Collection<Createurdocument> createurdocumentCollection) {
        this.createurdocumentCollection = createurdocumentCollection;
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
        hash += (idDocument != null ? idDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.idDocument == null && other.idDocument != null) || (this.idDocument != null && !this.idDocument.equals(other.idDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Document[ idDocument=" + idDocument + " ]";
    }
    
}
