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
public class SuspensionPlantelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_suspension")
    private long idSuspension;
    @Basic(optional = false)
    @Column(name = "id_plantel")
    private long idPlantel;

    public SuspensionPlantelPK() {
    }

    public SuspensionPlantelPK(long idSuspension, long idPlantel) {
        this.idSuspension = idSuspension;
        this.idPlantel = idPlantel;
    }

    public long getIdSuspension() {
        return idSuspension;
    }

    public void setIdSuspension(long idSuspension) {
        this.idSuspension = idSuspension;
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
        hash += (int) idSuspension;
        hash += (int) idPlantel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuspensionPlantelPK)) {
            return false;
        }
        SuspensionPlantelPK other = (SuspensionPlantelPK) object;
        if (this.idSuspension != other.idSuspension) {
            return false;
        }
        if (this.idPlantel != other.idPlantel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SuspensionPlantelPK[ idSuspension=" + idSuspension + ", idPlantel=" + idPlantel + " ]";
    }
    
}
