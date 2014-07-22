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
@Table(name = "detalle_nominaaplicadores")
@NamedQueries({
    @NamedQuery(name = "DetalleNominaaplicadores.findAll", query = "SELECT d FROM DetalleNominaaplicadores d")})
public class DetalleNominaaplicadores implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleNominaaplicadoresPK detalleNominaaplicadoresPK;
    @JoinColumn(name = "id_nominaaplicadores", referencedColumnName = "id_nominaaplicadores", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private NominaAplicadores nominaAplicadores;
    @JoinColumn(name = "id_asignacionaplicador", referencedColumnName = "id_asignacionaplicador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AsignacionAplicador asignacionAplicador;

    public DetalleNominaaplicadores() {
    }

    public DetalleNominaaplicadores(DetalleNominaaplicadoresPK detalleNominaaplicadoresPK) {
        this.detalleNominaaplicadoresPK = detalleNominaaplicadoresPK;
    }

    public DetalleNominaaplicadores(long idNominaaplicadores, long idAsignacionaplicador) {
        this.detalleNominaaplicadoresPK = new DetalleNominaaplicadoresPK(idNominaaplicadores, idAsignacionaplicador);
    }

    public DetalleNominaaplicadoresPK getDetalleNominaaplicadoresPK() {
        return detalleNominaaplicadoresPK;
    }

    public void setDetalleNominaaplicadoresPK(DetalleNominaaplicadoresPK detalleNominaaplicadoresPK) {
        this.detalleNominaaplicadoresPK = detalleNominaaplicadoresPK;
    }

    public NominaAplicadores getNominaAplicadores() {
        return nominaAplicadores;
    }

    public void setNominaAplicadores(NominaAplicadores nominaAplicadores) {
        this.nominaAplicadores = nominaAplicadores;
    }

    public AsignacionAplicador getAsignacionAplicador() {
        return asignacionAplicador;
    }

    public void setAsignacionAplicador(AsignacionAplicador asignacionAplicador) {
        this.asignacionAplicador = asignacionAplicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleNominaaplicadoresPK != null ? detalleNominaaplicadoresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleNominaaplicadores)) {
            return false;
        }
        DetalleNominaaplicadores other = (DetalleNominaaplicadores) object;
        if ((this.detalleNominaaplicadoresPK == null && other.detalleNominaaplicadoresPK != null) || (this.detalleNominaaplicadoresPK != null && !this.detalleNominaaplicadoresPK.equals(other.detalleNominaaplicadoresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.DetalleNominaaplicadores[ detalleNominaaplicadoresPK=" + detalleNominaaplicadoresPK + " ]";
    }
    
}
