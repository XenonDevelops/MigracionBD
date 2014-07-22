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
@Table(name = "suspension_plantel")
@NamedQueries({
    @NamedQuery(name = "SuspensionPlantel.findAll", query = "SELECT s FROM SuspensionPlantel s")})
public class SuspensionPlantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SuspensionPlantelPK suspensionPlantelPK;
    @JoinColumn(name = "id_suspension", referencedColumnName = "id_suspensionlabores", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SuspensionLabores suspensionLabores;
    @JoinColumn(name = "id_plantel", referencedColumnName = "id_plantel", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Plantel plantel;

    public SuspensionPlantel() {
    }

    public SuspensionPlantel(SuspensionPlantelPK suspensionPlantelPK) {
        this.suspensionPlantelPK = suspensionPlantelPK;
    }

    public SuspensionPlantel(long idSuspension, long idPlantel) {
        this.suspensionPlantelPK = new SuspensionPlantelPK(idSuspension, idPlantel);
    }

    public SuspensionPlantelPK getSuspensionPlantelPK() {
        return suspensionPlantelPK;
    }

    public void setSuspensionPlantelPK(SuspensionPlantelPK suspensionPlantelPK) {
        this.suspensionPlantelPK = suspensionPlantelPK;
    }

    public SuspensionLabores getSuspensionLabores() {
        return suspensionLabores;
    }

    public void setSuspensionLabores(SuspensionLabores suspensionLabores) {
        this.suspensionLabores = suspensionLabores;
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
        hash += (suspensionPlantelPK != null ? suspensionPlantelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuspensionPlantel)) {
            return false;
        }
        SuspensionPlantel other = (SuspensionPlantel) object;
        if ((this.suspensionPlantelPK == null && other.suspensionPlantelPK != null) || (this.suspensionPlantelPK != null && !this.suspensionPlantelPK.equals(other.suspensionPlantelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.SuspensionPlantel[ suspensionPlantelPK=" + suspensionPlantelPK + " ]";
    }
    
}
