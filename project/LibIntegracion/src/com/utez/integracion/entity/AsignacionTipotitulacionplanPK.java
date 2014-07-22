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
public class AsignacionTipotitulacionplanPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_planestudio")
    private long idPlanestudio;
    @Basic(optional = false)
    @Column(name = "id_tipotitulacion")
    private long idTipotitulacion;

    public AsignacionTipotitulacionplanPK() {
    }

    public AsignacionTipotitulacionplanPK(long idPlanestudio, long idTipotitulacion) {
        this.idPlanestudio = idPlanestudio;
        this.idTipotitulacion = idTipotitulacion;
    }

    public long getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(long idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public long getIdTipotitulacion() {
        return idTipotitulacion;
    }

    public void setIdTipotitulacion(long idTipotitulacion) {
        this.idTipotitulacion = idTipotitulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPlanestudio;
        hash += (int) idTipotitulacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionTipotitulacionplanPK)) {
            return false;
        }
        AsignacionTipotitulacionplanPK other = (AsignacionTipotitulacionplanPK) object;
        if (this.idPlanestudio != other.idPlanestudio) {
            return false;
        }
        if (this.idTipotitulacion != other.idTipotitulacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionTipotitulacionplanPK[ idPlanestudio=" + idPlanestudio + ", idTipotitulacion=" + idTipotitulacion + " ]";
    }
    
}
