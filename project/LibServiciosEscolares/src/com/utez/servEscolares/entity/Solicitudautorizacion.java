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
@Table(name = "solicitudautorizacion")
@NamedQueries({
    @NamedQuery(name = "Solicitudautorizacion.findAll", query = "SELECT s FROM Solicitudautorizacion s")})
public class Solicitudautorizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clavesolicitud")
    private Integer clavesolicitud;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "opcion")
    private String opcion;
    @Column(name = "asesortitular")
    private Integer asesortitular;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "tema")
    private String tema;
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "fechaautorizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaautorizacion;
    @Column(name = "nombrePosgrado")
    private String nombrePosgrado;
    @Column(name = "universidadOferta")
    private String universidadOferta;

    public Solicitudautorizacion() {
    }

    public Solicitudautorizacion(Integer clavesolicitud) {
        this.clavesolicitud = clavesolicitud;
    }

    public Solicitudautorizacion(Integer clavesolicitud, Date fecha, Date fechaautorizacion) {
        this.clavesolicitud = clavesolicitud;
        this.fecha = fecha;
        this.fechaautorizacion = fechaautorizacion;
    }

    public Integer getClavesolicitud() {
        return clavesolicitud;
    }

    public void setClavesolicitud(Integer clavesolicitud) {
        this.clavesolicitud = clavesolicitud;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Integer getAsesortitular() {
        return asesortitular;
    }

    public void setAsesortitular(Integer asesortitular) {
        this.asesortitular = asesortitular;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFechaautorizacion() {
        return fechaautorizacion;
    }

    public void setFechaautorizacion(Date fechaautorizacion) {
        this.fechaautorizacion = fechaautorizacion;
    }

    public String getNombrePosgrado() {
        return nombrePosgrado;
    }

    public void setNombrePosgrado(String nombrePosgrado) {
        this.nombrePosgrado = nombrePosgrado;
    }

    public String getUniversidadOferta() {
        return universidadOferta;
    }

    public void setUniversidadOferta(String universidadOferta) {
        this.universidadOferta = universidadOferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavesolicitud != null ? clavesolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudautorizacion)) {
            return false;
        }
        Solicitudautorizacion other = (Solicitudautorizacion) object;
        if ((this.clavesolicitud == null && other.clavesolicitud != null) || (this.clavesolicitud != null && !this.clavesolicitud.equals(other.clavesolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Solicitudautorizacion[ clavesolicitud=" + clavesolicitud + " ]";
    }
    
}
