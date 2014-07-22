/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "entradamaterial")
@NamedQueries({
    @NamedQuery(name = "Entradamaterial.findAll", query = "SELECT e FROM Entradamaterial e")})
public class Entradamaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveEntrada")
    private Integer claveEntrada;
    @Basic(optional = false)
    @Column(name = "numOrden")
    private int numOrden;
    @Column(name = "claveMaterial")
    private Integer claveMaterial;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fechaEntrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;

    public Entradamaterial() {
    }

    public Entradamaterial(Integer claveEntrada) {
        this.claveEntrada = claveEntrada;
    }

    public Entradamaterial(Integer claveEntrada, int numOrden) {
        this.claveEntrada = claveEntrada;
        this.numOrden = numOrden;
    }

    public Integer getClaveEntrada() {
        return claveEntrada;
    }

    public void setClaveEntrada(Integer claveEntrada) {
        this.claveEntrada = claveEntrada;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public Integer getClaveMaterial() {
        return claveMaterial;
    }

    public void setClaveMaterial(Integer claveMaterial) {
        this.claveMaterial = claveMaterial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveEntrada != null ? claveEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entradamaterial)) {
            return false;
        }
        Entradamaterial other = (Entradamaterial) object;
        if ((this.claveEntrada == null && other.claveEntrada != null) || (this.claveEntrada != null && !this.claveEntrada.equals(other.claveEntrada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Entradamaterial[ claveEntrada=" + claveEntrada + " ]";
    }
    
}
