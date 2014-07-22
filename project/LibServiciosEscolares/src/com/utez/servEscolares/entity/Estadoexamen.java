/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "estadoexamen")
@NamedQueries({
    @NamedQuery(name = "Estadoexamen.findAll", query = "SELECT e FROM Estadoexamen e")})
public class Estadoexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "numEvento")
    private Integer numEvento;
    @Column(name = "numControl")
    private Integer numControl;
    @Column(name = "situacion")
    private String situacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveEstadoexamen")
    private Integer claveEstadoexamen;

    public Estadoexamen() {
    }

    public Estadoexamen(Integer claveEstadoexamen) {
        this.claveEstadoexamen = claveEstadoexamen;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public Integer getNumControl() {
        return numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public Integer getClaveEstadoexamen() {
        return claveEstadoexamen;
    }

    public void setClaveEstadoexamen(Integer claveEstadoexamen) {
        this.claveEstadoexamen = claveEstadoexamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveEstadoexamen != null ? claveEstadoexamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadoexamen)) {
            return false;
        }
        Estadoexamen other = (Estadoexamen) object;
        if ((this.claveEstadoexamen == null && other.claveEstadoexamen != null) || (this.claveEstadoexamen != null && !this.claveEstadoexamen.equals(other.claveEstadoexamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Estadoexamen[ claveEstadoexamen=" + claveEstadoexamen + " ]";
    }
    
}
