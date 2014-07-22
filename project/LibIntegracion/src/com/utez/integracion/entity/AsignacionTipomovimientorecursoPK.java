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
public class AsignacionTipomovimientorecursoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tipomovimiento")
    private long idTipomovimiento;
    @Basic(optional = false)
    @Column(name = "id_recursohumano")
    private long idRecursohumano;

    public AsignacionTipomovimientorecursoPK() {
    }

    public AsignacionTipomovimientorecursoPK(long idTipomovimiento, long idRecursohumano) {
        this.idTipomovimiento = idTipomovimiento;
        this.idRecursohumano = idRecursohumano;
    }

    public long getIdTipomovimiento() {
        return idTipomovimiento;
    }

    public void setIdTipomovimiento(long idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    public long getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(long idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipomovimiento;
        hash += (int) idRecursohumano;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionTipomovimientorecursoPK)) {
            return false;
        }
        AsignacionTipomovimientorecursoPK other = (AsignacionTipomovimientorecursoPK) object;
        if (this.idTipomovimiento != other.idTipomovimiento) {
            return false;
        }
        if (this.idRecursohumano != other.idRecursohumano) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionTipomovimientorecursoPK[ idTipomovimiento=" + idTipomovimiento + ", idRecursohumano=" + idRecursohumano + " ]";
    }
    
}
