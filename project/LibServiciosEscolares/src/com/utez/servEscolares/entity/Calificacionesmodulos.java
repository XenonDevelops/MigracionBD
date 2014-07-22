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
@Table(name = "calificacionesmodulos")
@NamedQueries({
    @NamedQuery(name = "Calificacionesmodulos.findAll", query = "SELECT c FROM Calificacionesmodulos c")})
public class Calificacionesmodulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "numEvento")
    private Integer numEvento;
    @Column(name = "claveModulo")
    private Integer claveModulo;
    @Column(name = "numControl")
    private Integer numControl;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCalificacionesmodulos")
    private Integer claveCalificacionesmodulos;

    public Calificacionesmodulos() {
    }

    public Calificacionesmodulos(Integer claveCalificacionesmodulos) {
        this.claveCalificacionesmodulos = claveCalificacionesmodulos;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public Integer getClaveModulo() {
        return claveModulo;
    }

    public void setClaveModulo(Integer claveModulo) {
        this.claveModulo = claveModulo;
    }

    public Integer getNumControl() {
        return numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getClaveCalificacionesmodulos() {
        return claveCalificacionesmodulos;
    }

    public void setClaveCalificacionesmodulos(Integer claveCalificacionesmodulos) {
        this.claveCalificacionesmodulos = claveCalificacionesmodulos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCalificacionesmodulos != null ? claveCalificacionesmodulos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacionesmodulos)) {
            return false;
        }
        Calificacionesmodulos other = (Calificacionesmodulos) object;
        if ((this.claveCalificacionesmodulos == null && other.claveCalificacionesmodulos != null) || (this.claveCalificacionesmodulos != null && !this.claveCalificacionesmodulos.equals(other.claveCalificacionesmodulos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Calificacionesmodulos[ claveCalificacionesmodulos=" + claveCalificacionesmodulos + " ]";
    }
    
}
