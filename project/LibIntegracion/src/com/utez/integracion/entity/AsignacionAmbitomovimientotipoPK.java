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
public class AsignacionAmbitomovimientotipoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_ambitomovimiento")
    private long idAmbitomovimiento;
    @Basic(optional = false)
    @Column(name = "id_tipomovimiento")
    private long idTipomovimiento;

    public AsignacionAmbitomovimientotipoPK() {
    }

    public AsignacionAmbitomovimientotipoPK(long idAmbitomovimiento, long idTipomovimiento) {
        this.idAmbitomovimiento = idAmbitomovimiento;
        this.idTipomovimiento = idTipomovimiento;
    }

    public long getIdAmbitomovimiento() {
        return idAmbitomovimiento;
    }

    public void setIdAmbitomovimiento(long idAmbitomovimiento) {
        this.idAmbitomovimiento = idAmbitomovimiento;
    }

    public long getIdTipomovimiento() {
        return idTipomovimiento;
    }

    public void setIdTipomovimiento(long idTipomovimiento) {
        this.idTipomovimiento = idTipomovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAmbitomovimiento;
        hash += (int) idTipomovimiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAmbitomovimientotipoPK)) {
            return false;
        }
        AsignacionAmbitomovimientotipoPK other = (AsignacionAmbitomovimientotipoPK) object;
        if (this.idAmbitomovimiento != other.idAmbitomovimiento) {
            return false;
        }
        if (this.idTipomovimiento != other.idTipomovimiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAmbitomovimientotipoPK[ idAmbitomovimiento=" + idAmbitomovimiento + ", idTipomovimiento=" + idTipomovimiento + " ]";
    }
    
}
