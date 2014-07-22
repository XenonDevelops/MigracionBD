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
@Table(name = "registroeventos")
@NamedQueries({
    @NamedQuery(name = "Registroeventos.findAll", query = "SELECT r FROM Registroeventos r")})
public class Registroeventos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numEvento")
    private Integer numEvento;
    @Column(name = "nombreEvento")
    private String nombreEvento;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "tipoEvento")
    private String tipoEvento;
    @Column(name = "responsable")
    private String responsable;
    @Column(name = "cupo")
    private Integer cupo;
    @Column(name = "grupo")
    private String grupo;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "horaInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "horaFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;

    public Registroeventos() {
    }

    public Registroeventos(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEvento != null ? numEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroeventos)) {
            return false;
        }
        Registroeventos other = (Registroeventos) object;
        if ((this.numEvento == null && other.numEvento != null) || (this.numEvento != null && !this.numEvento.equals(other.numEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registroeventos[ numEvento=" + numEvento + " ]";
    }
    
}
