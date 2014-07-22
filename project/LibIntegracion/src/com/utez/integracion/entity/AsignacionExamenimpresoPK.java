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
public class AsignacionExamenimpresoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_fechaexamen")
    private long idFechaexamen;
    @Basic(optional = false)
    @Column(name = "id_examenimpreso")
    private long idExamenimpreso;

    public AsignacionExamenimpresoPK() {
    }

    public AsignacionExamenimpresoPK(long idFechaexamen, long idExamenimpreso) {
        this.idFechaexamen = idFechaexamen;
        this.idExamenimpreso = idExamenimpreso;
    }

    public long getIdFechaexamen() {
        return idFechaexamen;
    }

    public void setIdFechaexamen(long idFechaexamen) {
        this.idFechaexamen = idFechaexamen;
    }

    public long getIdExamenimpreso() {
        return idExamenimpreso;
    }

    public void setIdExamenimpreso(long idExamenimpreso) {
        this.idExamenimpreso = idExamenimpreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFechaexamen;
        hash += (int) idExamenimpreso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionExamenimpresoPK)) {
            return false;
        }
        AsignacionExamenimpresoPK other = (AsignacionExamenimpresoPK) object;
        if (this.idFechaexamen != other.idFechaexamen) {
            return false;
        }
        if (this.idExamenimpreso != other.idExamenimpreso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionExamenimpresoPK[ idFechaexamen=" + idFechaexamen + ", idExamenimpreso=" + idExamenimpreso + " ]";
    }
    
}
