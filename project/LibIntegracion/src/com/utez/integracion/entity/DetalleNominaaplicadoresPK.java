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
public class DetalleNominaaplicadoresPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_nominaaplicadores")
    private long idNominaaplicadores;
    @Basic(optional = false)
    @Column(name = "id_asignacionaplicador")
    private long idAsignacionaplicador;

    public DetalleNominaaplicadoresPK() {
    }

    public DetalleNominaaplicadoresPK(long idNominaaplicadores, long idAsignacionaplicador) {
        this.idNominaaplicadores = idNominaaplicadores;
        this.idAsignacionaplicador = idAsignacionaplicador;
    }

    public long getIdNominaaplicadores() {
        return idNominaaplicadores;
    }

    public void setIdNominaaplicadores(long idNominaaplicadores) {
        this.idNominaaplicadores = idNominaaplicadores;
    }

    public long getIdAsignacionaplicador() {
        return idAsignacionaplicador;
    }

    public void setIdAsignacionaplicador(long idAsignacionaplicador) {
        this.idAsignacionaplicador = idAsignacionaplicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idNominaaplicadores;
        hash += (int) idAsignacionaplicador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleNominaaplicadoresPK)) {
            return false;
        }
        DetalleNominaaplicadoresPK other = (DetalleNominaaplicadoresPK) object;
        if (this.idNominaaplicadores != other.idNominaaplicadores) {
            return false;
        }
        if (this.idAsignacionaplicador != other.idAsignacionaplicador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.DetalleNominaaplicadoresPK[ idNominaaplicadores=" + idNominaaplicadores + ", idAsignacionaplicador=" + idAsignacionaplicador + " ]";
    }
    
}
