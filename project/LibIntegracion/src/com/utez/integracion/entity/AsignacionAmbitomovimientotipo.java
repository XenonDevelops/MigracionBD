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
@Table(name = "asignacion_ambitomovimientotipo")
@NamedQueries({
    @NamedQuery(name = "AsignacionAmbitomovimientotipo.findAll", query = "SELECT a FROM AsignacionAmbitomovimientotipo a")})
public class AsignacionAmbitomovimientotipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignacionAmbitomovimientotipoPK asignacionAmbitomovimientotipoPK;
    @JoinColumn(name = "id_tipomovimiento", referencedColumnName = "id_tipomovimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMovimiento tipoMovimiento;
    @JoinColumn(name = "id_ambitomovimiento", referencedColumnName = "id_tipoambitomovimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoAmbitomovimiento tipoAmbitomovimiento;

    public AsignacionAmbitomovimientotipo() {
    }

    public AsignacionAmbitomovimientotipo(AsignacionAmbitomovimientotipoPK asignacionAmbitomovimientotipoPK) {
        this.asignacionAmbitomovimientotipoPK = asignacionAmbitomovimientotipoPK;
    }

    public AsignacionAmbitomovimientotipo(long idAmbitomovimiento, long idTipomovimiento) {
        this.asignacionAmbitomovimientotipoPK = new AsignacionAmbitomovimientotipoPK(idAmbitomovimiento, idTipomovimiento);
    }

    public AsignacionAmbitomovimientotipoPK getAsignacionAmbitomovimientotipoPK() {
        return asignacionAmbitomovimientotipoPK;
    }

    public void setAsignacionAmbitomovimientotipoPK(AsignacionAmbitomovimientotipoPK asignacionAmbitomovimientotipoPK) {
        this.asignacionAmbitomovimientotipoPK = asignacionAmbitomovimientotipoPK;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public TipoAmbitomovimiento getTipoAmbitomovimiento() {
        return tipoAmbitomovimiento;
    }

    public void setTipoAmbitomovimiento(TipoAmbitomovimiento tipoAmbitomovimiento) {
        this.tipoAmbitomovimiento = tipoAmbitomovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignacionAmbitomovimientotipoPK != null ? asignacionAmbitomovimientotipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAmbitomovimientotipo)) {
            return false;
        }
        AsignacionAmbitomovimientotipo other = (AsignacionAmbitomovimientotipo) object;
        if ((this.asignacionAmbitomovimientotipoPK == null && other.asignacionAmbitomovimientotipoPK != null) || (this.asignacionAmbitomovimientotipoPK != null && !this.asignacionAmbitomovimientotipoPK.equals(other.asignacionAmbitomovimientotipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAmbitomovimientotipo[ asignacionAmbitomovimientotipoPK=" + asignacionAmbitomovimientotipoPK + " ]";
    }
    
}
