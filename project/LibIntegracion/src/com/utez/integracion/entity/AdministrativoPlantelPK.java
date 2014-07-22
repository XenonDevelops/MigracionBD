/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sergio
 */
@Embeddable
public class AdministrativoPlantelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_administrativo")
    private long idAdministrativo;
    @Basic(optional = false)
    @Column(name = "id_plantel")
    private long idPlantel;

    public AdministrativoPlantelPK() {
    }

    public AdministrativoPlantelPK(long idAdministrativo, long idPlantel) {
        this.idAdministrativo = idAdministrativo;
        this.idPlantel = idPlantel;
    }

    public long getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public long getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(long idPlantel) {
        this.idPlantel = idPlantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAdministrativo;
        hash += (int) idPlantel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministrativoPlantelPK)) {
            return false;
        }
        AdministrativoPlantelPK other = (AdministrativoPlantelPK) object;
        if (this.idAdministrativo != other.idAdministrativo) {
            return false;
        }
        if (this.idPlantel != other.idPlantel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AdministrativoPlantelPK[ idAdministrativo=" + idAdministrativo + ", idPlantel=" + idPlantel + " ]";
    }
    
}
