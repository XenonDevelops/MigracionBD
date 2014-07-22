/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "entregaexamen")
@NamedQueries({
    @NamedQuery(name = "Entregaexamen.findAll", query = "SELECT e FROM Entregaexamen e")})
public class Entregaexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveEntrega")
    private Integer claveEntrega;
    @Column(name = "fechaRecibio")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibio;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "claveAplicacion", referencedColumnName = "claveAplicacion")
    @ManyToOne
    private Aplicacionexamen claveAplicacion;

    public Entregaexamen() {
    }

    public Entregaexamen(Integer claveEntrega) {
        this.claveEntrega = claveEntrega;
    }

    public Integer getClaveEntrega() {
        return claveEntrega;
    }

    public void setClaveEntrega(Integer claveEntrega) {
        this.claveEntrega = claveEntrega;
    }

    public Date getFechaRecibio() {
        return fechaRecibio;
    }

    public void setFechaRecibio(Date fechaRecibio) {
        this.fechaRecibio = fechaRecibio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Aplicacionexamen getClaveAplicacion() {
        return claveAplicacion;
    }

    public void setClaveAplicacion(Aplicacionexamen claveAplicacion) {
        this.claveAplicacion = claveAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveEntrega != null ? claveEntrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregaexamen)) {
            return false;
        }
        Entregaexamen other = (Entregaexamen) object;
        if ((this.claveEntrega == null && other.claveEntrega != null) || (this.claveEntrega != null && !this.claveEntrega.equals(other.claveEntrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Entregaexamen[ claveEntrega=" + claveEntrega + " ]";
    }
    
}
