/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "tipoexamen")
@NamedQueries({
    @NamedQuery(name = "Tipoexamen.findAll", query = "SELECT t FROM Tipoexamen t")})
public class Tipoexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveExamen")
    private Integer claveExamen;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public Tipoexamen() {
    }

    public Tipoexamen(Integer claveExamen) {
        this.claveExamen = claveExamen;
    }

    public Tipoexamen(Integer claveExamen, String descripcion) {
        this.claveExamen = claveExamen;
        this.descripcion = descripcion;
    }

    public Integer getClaveExamen() {
        return claveExamen;
    }

    public void setClaveExamen(Integer claveExamen) {
        this.claveExamen = claveExamen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveExamen != null ? claveExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoexamen)) {
            return false;
        }
        Tipoexamen other = (Tipoexamen) object;
        if ((this.claveExamen == null && other.claveExamen != null) || (this.claveExamen != null && !this.claveExamen.equals(other.claveExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Tipoexamen[ claveExamen=" + claveExamen + " ]";
    }
    
}
