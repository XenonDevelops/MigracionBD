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
@Table(name = "opcionesestudio")
@NamedQueries({
    @NamedQuery(name = "Opcionesestudio.findAll", query = "SELECT o FROM Opcionesestudio o")})
public class Opcionesestudio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claveopcest")
    private String claveopcest;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "nombreOpcion")
    private String nombreOpcion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion")
    private String descripcion;

    public Opcionesestudio() {
    }

    public Opcionesestudio(String claveopcest) {
        this.claveopcest = claveopcest;
    }

    public String getClaveopcest() {
        return claveopcest;
    }

    public void setClaveopcest(String claveopcest) {
        this.claveopcest = claveopcest;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (claveopcest != null ? claveopcest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcionesestudio)) {
            return false;
        }
        Opcionesestudio other = (Opcionesestudio) object;
        if ((this.claveopcest == null && other.claveopcest != null) || (this.claveopcest != null && !this.claveopcest.equals(other.claveopcest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Opcionesestudio[ claveopcest=" + claveopcest + " ]";
    }
    
}
