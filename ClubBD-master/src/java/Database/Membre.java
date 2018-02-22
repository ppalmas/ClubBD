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
@Table(name = "membre", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m")
    , @NamedQuery(name = "Membre.findByIdMembre", query = "SELECT m FROM Membre m WHERE m.idMembre = :idMembre")
    , @NamedQuery(name = "Membre.findByNom", query = "SELECT m FROM Membre m WHERE m.nom = :nom")
    , @NamedQuery(name = "Membre.findByPrenom", query = "SELECT m FROM Membre m WHERE m.prenom = :prenom")
    , @NamedQuery(name = "Membre.findByEmail", query = "SELECT m FROM Membre m WHERE m.email = :email")
    , @NamedQuery(name = "Membre.findByMdp", query = "SELECT m FROM Membre m WHERE m.mdp = :mdp")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_membre")
    private Integer idMembre;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "mdp")
    private String mdp;
    @JoinColumn(name = "id_statut", referencedColumnName = "id_statut")
    @ManyToOne(optional = false)
    private Statut idStatut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembre")
    private Collection<Emprunt> empruntCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStaff")
    private Collection<Emprunt> empruntCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembre")
    private Collection<Blacklist> blacklistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembre")
    private Collection<Vote> voteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMembre")
    private Collection<Connect> connectCollection;

    public Membre() {
    }

    public Membre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public Membre(Integer idMembre, String nom, String prenom, String email, String mdp) {
        this.idMembre = idMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Statut getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Statut idStatut) {
        this.idStatut = idStatut;
    }
    
    

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection() {
        return empruntCollection;
    }

    public void setEmpruntCollection(Collection<Emprunt> empruntCollection) {
        this.empruntCollection = empruntCollection;
    }

    @XmlTransient
    public Collection<Emprunt> getEmpruntCollection1() {
        return empruntCollection1;
    }

    public void setEmpruntCollection1(Collection<Emprunt> empruntCollection1) {
        this.empruntCollection1 = empruntCollection1;
    }

    @XmlTransient
    public Collection<Blacklist> getBlacklistCollection() {
        return blacklistCollection;
    }

    public void setBlacklistCollection(Collection<Blacklist> blacklistCollection) {
        this.blacklistCollection = blacklistCollection;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @XmlTransient
    public Collection<Connect> getConnectCollection() {
        return connectCollection;
    }

    public void setConnectCollection(Collection<Connect> connectCollection) {
        this.connectCollection = connectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMembre != null ? idMembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.idMembre == null && other.idMembre != null) || (this.idMembre != null && !this.idMembre.equals(other.idMembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Membre[ idMembre=" + idMembre + " ]";
    }
    
}
