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
@Table(name = "connect", catalog = "clubbd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connect.findAll", query = "SELECT c FROM Connect c")
    , @NamedQuery(name = "Connect.findByIdConnect", query = "SELECT c FROM Connect c WHERE c.idConnect = :idConnect")
    , @NamedQuery(name = "Connect.findByConnectLastAction", query = "SELECT c FROM Connect c WHERE c.connectLastAction = :connectLastAction")})
public class Connect implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_connect")
    private String idConnect;
    @Basic(optional = false)
    @Column(name = "connect_last_action")
    private String connectLastAction;
    @JoinColumn(name = "id_membre", referencedColumnName = "id_membre")
    @ManyToOne(optional = false)
    private Membre idMembre;

    public Connect() {
    }

    public Connect(String idConnect) {
        this.idConnect = idConnect;
    }

    public Connect(String idConnect, String connectLastAction) {
        this.idConnect = idConnect;
        this.connectLastAction = connectLastAction;
    }

    public Connect(String idco, String lastAction, Membre m) {
        this.idConnect = idco;
        this.connectLastAction = lastAction;
        this.idMembre=m;
    }

    public String getIdConnect() {
        return idConnect;
    }

    public void setIdConnect(String idConnect) {
        this.idConnect = idConnect;
    }

    public String getConnectLastAction() {
        return connectLastAction;
    }

    public void setConnectLastAction(String connectLastAction) {
        this.connectLastAction = connectLastAction;
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
        hash += (idConnect != null ? idConnect.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connect)) {
            return false;
        }
        Connect other = (Connect) object;
        if ((this.idConnect == null && other.idConnect != null) || (this.idConnect != null && !this.idConnect.equals(other.idConnect))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Connect[ idConnect=" + idConnect + " ]";
    }
    
}
