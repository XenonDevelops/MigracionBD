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
@Table(name = "reporteservicio")
@NamedQueries({
    @NamedQuery(name = "Reporteservicio.findAll", query = "SELECT r FROM Reporteservicio r")})
public class Reporteservicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "numReporte")
    private Integer numReporte;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveReporteservicio")
    private Integer claveReporteservicio;

    public Reporteservicio() {
    }

    public Reporteservicio(Integer claveReporteservicio) {
        this.claveReporteservicio = claveReporteservicio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getNumReporte() {
        return numReporte;
    }

    public void setNumReporte(Integer numReporte) {
        this.numReporte = numReporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getClaveReporteservicio() {
        return claveReporteservicio;
    }

    public void setClaveReporteservicio(Integer claveReporteservicio) {
        this.claveReporteservicio = claveReporteservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveReporteservicio != null ? claveReporteservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporteservicio)) {
            return false;
        }
        Reporteservicio other = (Reporteservicio) object;
        if ((this.claveReporteservicio == null && other.claveReporteservicio != null) || (this.claveReporteservicio != null && !this.claveReporteservicio.equals(other.claveReporteservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Reporteservicio[ claveReporteservicio=" + claveReporteservicio + " ]";
    }
    
}
