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
@Table(name = "asignacion_tipotitulacionplan")
@NamedQueries({
    @NamedQuery(name = "AsignacionTipotitulacionplan.findAll", query = "SELECT a FROM AsignacionTipotitulacionplan a")})
public class AsignacionTipotitulacionplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignacionTipotitulacionplanPK asignacionTipotitulacionplanPK;
    @JoinColumn(name = "id_tipotitulacion", referencedColumnName = "id_tipotitulacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoTitulacion tipoTitulacion;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlanEstudio planEstudio;

    public AsignacionTipotitulacionplan() {
    }

    public AsignacionTipotitulacionplan(AsignacionTipotitulacionplanPK asignacionTipotitulacionplanPK) {
        this.asignacionTipotitulacionplanPK = asignacionTipotitulacionplanPK;
    }

    public AsignacionTipotitulacionplan(long idPlanestudio, long idTipotitulacion) {
        this.asignacionTipotitulacionplanPK = new AsignacionTipotitulacionplanPK(idPlanestudio, idTipotitulacion);
    }

    public AsignacionTipotitulacionplanPK getAsignacionTipotitulacionplanPK() {
        return asignacionTipotitulacionplanPK;
    }

    public void setAsignacionTipotitulacionplanPK(AsignacionTipotitulacionplanPK asignacionTipotitulacionplanPK) {
        this.asignacionTipotitulacionplanPK = asignacionTipotitulacionplanPK;
    }

    public TipoTitulacion getTipoTitulacion() {
        return tipoTitulacion;
    }

    public void setTipoTitulacion(TipoTitulacion tipoTitulacion) {
        this.tipoTitulacion = tipoTitulacion;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public void setPlanEstudio(PlanEstudio planEstudio) {
        this.planEstudio = planEstudio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignacionTipotitulacionplanPK != null ? asignacionTipotitulacionplanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionTipotitulacionplan)) {
            return false;
        }
        AsignacionTipotitulacionplan other = (AsignacionTipotitulacionplan) object;
        if ((this.asignacionTipotitulacionplanPK == null && other.asignacionTipotitulacionplanPK != null) || (this.asignacionTipotitulacionplanPK != null && !this.asignacionTipotitulacionplanPK.equals(other.asignacionTipotitulacionplanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionTipotitulacionplan[ asignacionTipotitulacionplanPK=" + asignacionTipotitulacionplanPK + " ]";
    }
    
}
