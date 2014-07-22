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
@Table(name = "fechasexam")
@NamedQueries({
    @NamedQuery(name = "Fechasexam.findAll", query = "SELECT f FROM Fechasexam f")})
public class Fechasexam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveCalendario")
    private Integer claveCalendario;
    @Column(name = "claveAsesoria")
    private Integer claveAsesoria;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tipoexamen")
    private String tipoexamen;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveFechasexam")
    private Integer claveFechasexam;

    public Fechasexam() {
    }

    public Fechasexam(Integer claveFechasexam) {
        this.claveFechasexam = claveFechasexam;
    }

    public Integer getClaveCalendario() {
        return claveCalendario;
    }

    public void setClaveCalendario(Integer claveCalendario) {
        this.claveCalendario = claveCalendario;
    }

    public Integer getClaveAsesoria() {
        return claveAsesoria;
    }

    public void setClaveAsesoria(Integer claveAsesoria) {
        this.claveAsesoria = claveAsesoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoexamen() {
        return tipoexamen;
    }

    public void setTipoexamen(String tipoexamen) {
        this.tipoexamen = tipoexamen;
    }

    public Integer getClaveFechasexam() {
        return claveFechasexam;
    }

    public void setClaveFechasexam(Integer claveFechasexam) {
        this.claveFechasexam = claveFechasexam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveFechasexam != null ? claveFechasexam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechasexam)) {
            return false;
        }
        Fechasexam other = (Fechasexam) object;
        if ((this.claveFechasexam == null && other.claveFechasexam != null) || (this.claveFechasexam != null && !this.claveFechasexam.equals(other.claveFechasexam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Fechasexam[ claveFechasexam=" + claveFechasexam + " ]";
    }
    
}
