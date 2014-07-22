/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "administrativo_plantel")
@NamedQueries({
    @NamedQuery(name = "AdministrativoPlantel.findAll", query = "SELECT a FROM AdministrativoPlantel a")})
public class AdministrativoPlantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdministrativoPlantelPK administrativoPlantelPK;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plantel plantel;
    @JoinColumn(name = "id_administrativo", referencedColumnName = "id_administrativo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Administrativo administrativo;

    public AdministrativoPlantel() {
    }

    public AdministrativoPlantel(AdministrativoPlantelPK administrativoPlantelPK) {
        this.administrativoPlantelPK = administrativoPlantelPK;
    }

    public AdministrativoPlantel(long idAdministrativo, long idPlantel) {
        this.administrativoPlantelPK = new AdministrativoPlantelPK(idAdministrativo, idPlantel);
    }

    public AdministrativoPlantelPK getAdministrativoPlantelPK() {
        return administrativoPlantelPK;
    }

    public void setAdministrativoPlantelPK(AdministrativoPlantelPK administrativoPlantelPK) {
        this.administrativoPlantelPK = administrativoPlantelPK;
    }

    public Plantel getPlantel() {
        return plantel;
    }

    public void setPlantel(Plantel plantel) {
        this.plantel = plantel;
    }

    public Administrativo getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administrativoPlantelPK != null ? administrativoPlantelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministrativoPlantel)) {
            return false;
        }
        AdministrativoPlantel other = (AdministrativoPlantel) object;
        if ((this.administrativoPlantelPK == null && other.administrativoPlantelPK != null) || (this.administrativoPlantelPK != null && !this.administrativoPlantelPK.equals(other.administrativoPlantelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AdministrativoPlantel[ administrativoPlantelPK=" + administrativoPlantelPK + " ]";
    }
    
}
