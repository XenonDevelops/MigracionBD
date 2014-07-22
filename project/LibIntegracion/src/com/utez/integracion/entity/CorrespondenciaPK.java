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
public class CorrespondenciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private long idAsignatura;
    @Basic(optional = false)
    @Column(name = "id_asignaturacorrespondencia")
    private long idAsignaturacorrespondencia;

    public CorrespondenciaPK() {
    }

    public CorrespondenciaPK(long idAsignatura, long idAsignaturacorrespondencia) {
        this.idAsignatura = idAsignatura;
        this.idAsignaturacorrespondencia = idAsignaturacorrespondencia;
    }

    public long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public long getIdAsignaturacorrespondencia() {
        return idAsignaturacorrespondencia;
    }

    public void setIdAsignaturacorrespondencia(long idAsignaturacorrespondencia) {
        this.idAsignaturacorrespondencia = idAsignaturacorrespondencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAsignatura;
        hash += (int) idAsignaturacorrespondencia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorrespondenciaPK)) {
            return false;
        }
        CorrespondenciaPK other = (CorrespondenciaPK) object;
        if (this.idAsignatura != other.idAsignatura) {
            return false;
        }
        if (this.idAsignaturacorrespondencia != other.idAsignaturacorrespondencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CorrespondenciaPK[ idAsignatura=" + idAsignatura + ", idAsignaturacorrespondencia=" + idAsignaturacorrespondencia + " ]";
    }
    
}
