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
@Table(name = "movimientos")
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m")})
public class Movimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveMovimiento")
    private Integer claveMovimiento;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;

    public Movimientos() {
    }

    public Movimientos(Integer claveMovimiento) {
        this.claveMovimiento = claveMovimiento;
    }

    public Movimientos(Integer claveMovimiento, String descripcion) {
        this.claveMovimiento = claveMovimiento;
        this.descripcion = descripcion;
    }

    public Integer getClaveMovimiento() {
        return claveMovimiento;
    }

    public void setClaveMovimiento(Integer claveMovimiento) {
        this.claveMovimiento = claveMovimiento;
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
        hash += (claveMovimiento != null ? claveMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.claveMovimiento == null && other.claveMovimiento != null) || (this.claveMovimiento != null && !this.claveMovimiento.equals(other.claveMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Movimientos[ claveMovimiento=" + claveMovimiento + " ]";
    }
    
}
