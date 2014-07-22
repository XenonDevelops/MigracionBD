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
public class VacacionPlantelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_vacacion")
    private long idVacacion;
    @Basic(optional = false)
    @Column(name = "id_plantel")
    private long idPlantel;

    public VacacionPlantelPK() {
    }

    public VacacionPlantelPK(long idVacacion, long idPlantel) {
        this.idVacacion = idVacacion;
        this.idPlantel = idPlantel;
    }

    public long getIdVacacion() {
        return idVacacion;
    }

    public void setIdVacacion(long idVacacion) {
        this.idVacacion = idVacacion;
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
        hash += (int) idVacacion;
        hash += (int) idPlantel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacacionPlantelPK)) {
            return false;
        }
        VacacionPlantelPK other = (VacacionPlantelPK) object;
        if (this.idVacacion != other.idVacacion) {
            return false;
        }
        if (this.idPlantel != other.idPlantel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.VacacionPlantelPK[ idVacacion=" + idVacacion + ", idPlantel=" + idPlantel + " ]";
    }
    
}
