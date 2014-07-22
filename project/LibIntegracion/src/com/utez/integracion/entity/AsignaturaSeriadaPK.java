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
public class AsignaturaSeriadaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private long idAsignatura;
    @Basic(optional = false)
    @Column(name = "id_asignaturaseriada")
    private long idAsignaturaseriada;

    public AsignaturaSeriadaPK() {
    }

    public AsignaturaSeriadaPK(long idAsignatura, long idAsignaturaseriada) {
        this.idAsignatura = idAsignatura;
        this.idAsignaturaseriada = idAsignaturaseriada;
    }

    public long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public long getIdAsignaturaseriada() {
        return idAsignaturaseriada;
    }

    public void setIdAsignaturaseriada(long idAsignaturaseriada) {
        this.idAsignaturaseriada = idAsignaturaseriada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAsignatura;
        hash += (int) idAsignaturaseriada;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaSeriadaPK)) {
            return false;
        }
        AsignaturaSeriadaPK other = (AsignaturaSeriadaPK) object;
        if (this.idAsignatura != other.idAsignatura) {
            return false;
        }
        if (this.idAsignaturaseriada != other.idAsignaturaseriada) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignaturaSeriadaPK[ idAsignatura=" + idAsignatura + ", idAsignaturaseriada=" + idAsignaturaseriada + " ]";
    }
    
}
