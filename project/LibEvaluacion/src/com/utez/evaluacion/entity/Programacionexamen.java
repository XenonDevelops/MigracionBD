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
@Table(name = "programacionexamen")
@NamedQueries({
    @NamedQuery(name = "Programacionexamen.findAll", query = "SELECT p FROM Programacionexamen p")})
public class Programacionexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProExa")
    private Integer claveProExa;
    @Column(name = "clavePlantel")
    private Integer clavePlantel;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @JoinColumn(name = "claveExamen", referencedColumnName = "claveExamen")
    @ManyToOne
    private Examen claveExamen;

    public Programacionexamen() {
    }

    public Programacionexamen(Integer claveProExa) {
        this.claveProExa = claveProExa;
    }

    public Programacionexamen(Integer claveProExa, Date fechaInicio, Date fechaFin) {
        this.claveProExa = claveProExa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getClaveProExa() {
        return claveProExa;
    }

    public void setClaveProExa(Integer claveProExa) {
        this.claveProExa = claveProExa;
    }

    public Integer getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Examen getClaveExamen() {
        return claveExamen;
    }

    public void setClaveExamen(Examen claveExamen) {
        this.claveExamen = claveExamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveProExa != null ? claveProExa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionexamen)) {
            return false;
        }
        Programacionexamen other = (Programacionexamen) object;
        if ((this.claveProExa == null && other.claveProExa != null) || (this.claveProExa != null && !this.claveProExa.equals(other.claveProExa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Programacionexamen[ claveProExa=" + claveProExa + " ]";
    }
    
}
