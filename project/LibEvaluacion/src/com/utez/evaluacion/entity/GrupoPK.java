/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sergio
 */
@Embeddable
public class GrupoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Basic(optional = false)
    @Column(name = "clavePlantel")
    private int clavePlantel;

    public GrupoPK() {
    }

    public GrupoPK(String claveGrupo, int clavePlantel) {
        this.claveGrupo = claveGrupo;
        this.clavePlantel = clavePlantel;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public int getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(int clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveGrupo != null ? claveGrupo.hashCode() : 0);
        hash += (int) clavePlantel;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoPK)) {
            return false;
        }
        GrupoPK other = (GrupoPK) object;
        if ((this.claveGrupo == null && other.claveGrupo != null) || (this.claveGrupo != null && !this.claveGrupo.equals(other.claveGrupo))) {
            return false;
        }
        if (this.clavePlantel != other.clavePlantel) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.GrupoPK[ claveGrupo=" + claveGrupo + ", clavePlantel=" + clavePlantel + " ]";
    }
    
}
