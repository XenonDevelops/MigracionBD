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
@Table(name = "asignacion_examenimpreso")
@NamedQueries({
    @NamedQuery(name = "AsignacionExamenimpreso.findAll", query = "SELECT a FROM AsignacionExamenimpreso a")})
public class AsignacionExamenimpreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignacionExamenimpresoPK asignacionExamenimpresoPK;
    @JoinColumn(name = "id_fechaexamen", referencedColumnName = "id_fechaexamen", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FechaExamen fechaExamen;
    @JoinColumn(name = "id_examenimpreso", referencedColumnName = "id_examenimpreso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExamenImpreso examenImpreso;

    public AsignacionExamenimpreso() {
    }

    public AsignacionExamenimpreso(AsignacionExamenimpresoPK asignacionExamenimpresoPK) {
        this.asignacionExamenimpresoPK = asignacionExamenimpresoPK;
    }

    public AsignacionExamenimpreso(long idFechaexamen, long idExamenimpreso) {
        this.asignacionExamenimpresoPK = new AsignacionExamenimpresoPK(idFechaexamen, idExamenimpreso);
    }

    public AsignacionExamenimpresoPK getAsignacionExamenimpresoPK() {
        return asignacionExamenimpresoPK;
    }

    public void setAsignacionExamenimpresoPK(AsignacionExamenimpresoPK asignacionExamenimpresoPK) {
        this.asignacionExamenimpresoPK = asignacionExamenimpresoPK;
    }

    public FechaExamen getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(FechaExamen fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public ExamenImpreso getExamenImpreso() {
        return examenImpreso;
    }

    public void setExamenImpreso(ExamenImpreso examenImpreso) {
        this.examenImpreso = examenImpreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignacionExamenimpresoPK != null ? asignacionExamenimpresoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionExamenimpreso)) {
            return false;
        }
        AsignacionExamenimpreso other = (AsignacionExamenimpreso) object;
        if ((this.asignacionExamenimpresoPK == null && other.asignacionExamenimpresoPK != null) || (this.asignacionExamenimpresoPK != null && !this.asignacionExamenimpresoPK.equals(other.asignacionExamenimpresoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionExamenimpreso[ asignacionExamenimpresoPK=" + asignacionExamenimpresoPK + " ]";
    }
    
}
