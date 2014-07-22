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
@Table(name = "seguimiento")
@NamedQueries({
    @NamedQuery(name = "Seguimiento.findAll", query = "SELECT s FROM Seguimiento s")})
public class Seguimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveSeguimiento")
    private Integer claveSeguimiento;
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "autor")
    private Integer autor;

    public Seguimiento() {
    }

    public Seguimiento(Integer claveSeguimiento) {
        this.claveSeguimiento = claveSeguimiento;
    }

    public Seguimiento(Integer claveSeguimiento, Date fecha) {
        this.claveSeguimiento = claveSeguimiento;
        this.fecha = fecha;
    }

    public Integer getClaveSeguimiento() {
        return claveSeguimiento;
    }

    public void setClaveSeguimiento(Integer claveSeguimiento) {
        this.claveSeguimiento = claveSeguimiento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveSeguimiento != null ? claveSeguimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguimiento)) {
            return false;
        }
        Seguimiento other = (Seguimiento) object;
        if ((this.claveSeguimiento == null && other.claveSeguimiento != null) || (this.claveSeguimiento != null && !this.claveSeguimiento.equals(other.claveSeguimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Seguimiento[ claveSeguimiento=" + claveSeguimiento + " ]";
    }
    
}
