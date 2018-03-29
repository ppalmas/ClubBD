/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author centrale
 */
@Entity
@Table(name = "emprunt", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e")
    , @NamedQuery(name = "Emprunt.findByIdEmprunt", query = "SELECT e FROM Emprunt e WHERE e.idEmprunt = :idEmprunt")
    , @NamedQuery(name = "Emprunt.findByDateReserve", query = "SELECT e FROM Emprunt e WHERE e.dateReserve = :dateReserve")
    , @NamedQuery(name = "Emprunt.findByDateEmprunt", query = "SELECT e FROM Emprunt e WHERE e.dateEmprunt = :dateEmprunt")
    , @NamedQuery(name = "Emprunt.findByDateRetour", query = "SELECT e FROM Emprunt e WHERE e.dateRetour = :dateRetour")
    , @NamedQuery(name = "Emprunt.findByDateRetourne", query = "SELECT e FROM Emprunt e WHERE e.dateRetourne = :dateRetourne")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emprunt")
    private Integer idEmprunt;
    @Basic(optional = false)
    @Column(name = "date_reserve")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReserve;
    @Basic(optional = false)
    @Column(name = "date_emprunt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmprunt;
    @Basic(optional = false)
    @Column(name = "date_retour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetour;
    @Column(name = "date_retourne")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetourne;
    @JoinColumn(name = "id_document", referencedColumnName = "id_document")
    @ManyToOne(optional = false)
    private Document idDocument;
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre")
    @ManyToOne(optional = false)
    private Membre idMembre;
    @JoinColumn(name = "id_staff", referencedColumnName = "id_membre")
    @ManyToOne(optional = false)
    private Membre idStaff;

    public Emprunt() {
    }

    public Emprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Emprunt(Integer idEmprunt, Date dateReserve, Date dateEmprunt, Date dateRetour) {
        this.idEmprunt = idEmprunt;
        this.dateReserve = dateReserve;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Date getDateReserve() {
        return dateReserve;
    }

    public void setDateReserve(Date dateReserve) {
        this.dateReserve = dateReserve;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Date getDateRetourne() {
        return dateRetourne;
    }

    public void setDateRetourne(Date dateRetourne) {
        this.dateRetourne = dateRetourne;
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

    public Membre getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(Membre idStaff) {
        this.idStaff = idStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprunt != null ? idEmprunt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.idEmprunt == null && other.idEmprunt != null) || (this.idEmprunt != null && !this.idEmprunt.equals(other.idEmprunt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Emprunt[ idEmprunt=" + idEmprunt + " ]";
    }
    
}
