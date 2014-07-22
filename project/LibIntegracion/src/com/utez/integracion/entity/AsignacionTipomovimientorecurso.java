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
@Table(name = "asignacion_tipomovimientorecurso")
@NamedQueries({
    @NamedQuery(name = "AsignacionTipomovimientorecurso.findAll", query = "SELECT a FROM AsignacionTipomovimientorecurso a")})
public class AsignacionTipomovimientorecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignacionTipomovimientorecursoPK asignacionTipomovimientorecursoPK;
    @JoinColumn(name = "id_tipomovimiento", referencedColumnName = "id_tipomovimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMovimiento tipoMovimiento;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RecursoHumano recursoHumano;

    public AsignacionTipomovimientorecurso() {
    }

    public AsignacionTipomovimientorecurso(AsignacionTipomovimientorecursoPK asignacionTipomovimientorecursoPK) {
        this.asignacionTipomovimientorecursoPK = asignacionTipomovimientorecursoPK;
    }

    public AsignacionTipomovimientorecurso(long idTipomovimiento, long idRecursohumano) {
        this.asignacionTipomovimientorecursoPK = new AsignacionTipomovimientorecursoPK(idTipomovimiento, idRecursohumano);
    }

    public AsignacionTipomovimientorecursoPK getAsignacionTipomovimientorecursoPK() {
        return asignacionTipomovimientorecursoPK;
    }

    public void setAsignacionTipomovimientorecursoPK(AsignacionTipomovimientorecursoPK asignacionTipomovimientorecursoPK) {
        this.asignacionTipomovimientorecursoPK = asignacionTipomovimientorecursoPK;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public RecursoHumano getRecursoHumano() {
        return recursoHumano;
    }

    public void setRecursoHumano(RecursoHumano recursoHumano) {
        this.recursoHumano = recursoHumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignacionTipomovimientorecursoPK != null ? asignacionTipomovimientorecursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionTipomovimientorecurso)) {
            return false;
        }
        AsignacionTipomovimientorecurso other = (AsignacionTipomovimientorecurso) object;
        if ((this.asignacionTipomovimientorecursoPK == null && other.asignacionTipomovimientorecursoPK != null) || (this.asignacionTipomovimientorecursoPK != null && !this.asignacionTipomovimientorecursoPK.equals(other.asignacionTipomovimientorecursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionTipomovimientorecurso[ asignacionTipomovimientorecursoPK=" + asignacionTipomovimientorecursoPK + " ]";
    }
    
}
