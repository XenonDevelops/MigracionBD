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
@Table(name = "aspirantes")
@NamedQueries({
    @NamedQuery(name = "Aspirantes.findAll", query = "SELECT a FROM Aspirantes a")})
public class Aspirantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAspirante")
    private Integer claveAspirante;
    @Column(name = "nombreAspirante")
    private String nombreAspirante;
    @Basic(optional = false)
    @Column(name = "telefonoAspirante")
    private String telefonoAspirante;
    @Column(name = "emailAspirante")
    private String emailAspirante;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "fechaContacto")
    @Temporal(TemporalType.DATE)
    private Date fechaContacto;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fechaSeguimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaSeguimiento;

    public Aspirantes() {
    }

    public Aspirantes(Integer claveAspirante) {
        this.claveAspirante = claveAspirante;
    }

    public Aspirantes(Integer claveAspirante, String telefonoAspirante) {
        this.claveAspirante = claveAspirante;
        this.telefonoAspirante = telefonoAspirante;
    }

    public Integer getClaveAspirante() {
        return claveAspirante;
    }

    public void setClaveAspirante(Integer claveAspirante) {
        this.claveAspirante = claveAspirante;
    }

    public String getNombreAspirante() {
        return nombreAspirante;
    }

    public void setNombreAspirante(String nombreAspirante) {
        this.nombreAspirante = nombreAspirante;
    }

    public String getTelefonoAspirante() {
        return telefonoAspirante;
    }

    public void setTelefonoAspirante(String telefonoAspirante) {
        this.telefonoAspirante = telefonoAspirante;
    }

    public String getEmailAspirante() {
        return emailAspirante;
    }

    public void setEmailAspirante(String emailAspirante) {
        this.emailAspirante = emailAspirante;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Date getFechaContacto() {
        return fechaContacto;
    }

    public void setFechaContacto(Date fechaContacto) {
        this.fechaContacto = fechaContacto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAspirante != null ? claveAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aspirantes)) {
            return false;
        }
        Aspirantes other = (Aspirantes) object;
        if ((this.claveAspirante == null && other.claveAspirante != null) || (this.claveAspirante != null && !this.claveAspirante.equals(other.claveAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Aspirantes[ claveAspirante=" + claveAspirante + " ]";
    }
    
}
