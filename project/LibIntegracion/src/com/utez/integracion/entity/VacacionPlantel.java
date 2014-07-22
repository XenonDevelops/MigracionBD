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
@Table(name = "vacacion_plantel")
@NamedQueries({
    @NamedQuery(name = "VacacionPlantel.findAll", query = "SELECT v FROM VacacionPlantel v")})
public class VacacionPlantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VacacionPlantelPK vacacionPlantelPK;
    @JoinColumn(name = "id_vacacion", referencedColumnName = "id_vacacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vacacion vacacion;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plantel plantel;

    public VacacionPlantel() {
    }

    public VacacionPlantel(VacacionPlantelPK vacacionPlantelPK) {
        this.vacacionPlantelPK = vacacionPlantelPK;
    }

    public VacacionPlantel(long idVacacion, long idPlantel) {
        this.vacacionPlantelPK = new VacacionPlantelPK(idVacacion, idPlantel);
    }

    public VacacionPlantelPK getVacacionPlantelPK() {
        return vacacionPlantelPK;
    }

    public void setVacacionPlantelPK(VacacionPlantelPK vacacionPlantelPK) {
        this.vacacionPlantelPK = vacacionPlantelPK;
    }

    public Vacacion getVacacion() {
        return vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }

    public Plantel getPlantel() {
        return plantel;
    }

    public void setPlantel(Plantel plantel) {
        this.plantel = plantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacacionPlantelPK != null ? vacacionPlantelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacacionPlantel)) {
            return false;
        }
        VacacionPlantel other = (VacacionPlantel) object;
        if ((this.vacacionPlantelPK == null && other.vacacionPlantelPK != null) || (this.vacacionPlantelPK != null && !this.vacacionPlantelPK.equals(other.vacacionPlantelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.VacacionPlantel[ vacacionPlantelPK=" + vacacionPlantelPK + " ]";
    }
    
}
