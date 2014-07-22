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
import javax.persistence.Lob;
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
@Table(name = "solicitudmateriac")
@NamedQueries({
    @NamedQuery(name = "Solicitudmateriac.findAll", query = "SELECT s FROM Solicitudmateriac s")})
public class Solicitudmateriac implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numSolicitud")
    private Integer numSolicitud;
    @Column(name = "numProgramacion")
    private Integer numProgramacion;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "fechaActual")
    @Temporal(TemporalType.DATE)
    private Date fechaActual;
    @Lob
    @Column(name = "caso")
    private String caso;
    @Lob
    @Column(name = "solucion")
    private String solucion;

    public Solicitudmateriac() {
    }

    public Solicitudmateriac(Integer numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public Integer getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(Integer numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public Integer getNumProgramacion() {
        return numProgramacion;
    }

    public void setNumProgramacion(Integer numProgramacion) {
        this.numProgramacion = numProgramacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSolicitud != null ? numSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudmateriac)) {
            return false;
        }
        Solicitudmateriac other = (Solicitudmateriac) object;
        if ((this.numSolicitud == null && other.numSolicitud != null) || (this.numSolicitud != null && !this.numSolicitud.equals(other.numSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Solicitudmateriac[ numSolicitud=" + numSolicitud + " ]";
    }
    
}
