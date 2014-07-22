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
public class OrdentrabajoTitulacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tramitetitulacion")
    private long idTramitetitulacion;
    @Basic(optional = false)
    @Column(name = "id_ordentrabajo")
    private long idOrdentrabajo;

    public OrdentrabajoTitulacionPK() {
    }

    public OrdentrabajoTitulacionPK(long idTramitetitulacion, long idOrdentrabajo) {
        this.idTramitetitulacion = idTramitetitulacion;
        this.idOrdentrabajo = idOrdentrabajo;
    }

    public long getIdTramitetitulacion() {
        return idTramitetitulacion;
    }

    public void setIdTramitetitulacion(long idTramitetitulacion) {
        this.idTramitetitulacion = idTramitetitulacion;
    }

    public long getIdOrdentrabajo() {
        return idOrdentrabajo;
    }

    public void setIdOrdentrabajo(long idOrdentrabajo) {
        this.idOrdentrabajo = idOrdentrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTramitetitulacion;
        hash += (int) idOrdentrabajo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdentrabajoTitulacionPK)) {
            return false;
        }
        OrdentrabajoTitulacionPK other = (OrdentrabajoTitulacionPK) object;
        if (this.idTramitetitulacion != other.idTramitetitulacion) {
            return false;
        }
        if (this.idOrdentrabajo != other.idOrdentrabajo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.OrdentrabajoTitulacionPK[ idTramitetitulacion=" + idTramitetitulacion + ", idOrdentrabajo=" + idOrdentrabajo + " ]";
    }
    
}
