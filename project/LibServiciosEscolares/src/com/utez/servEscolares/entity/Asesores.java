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
@Table(name = "asesores")
@NamedQueries({
    @NamedQuery(name = "Asesores.findAll", query = "SELECT a FROM Asesores a")})
public class Asesores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "nombreComp")
    private String nombreComp;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "fechaTitulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTitulacion;
    @Column(name = "fechaCedula")
    @Temporal(TemporalType.DATE)
    private Date fechaCedula;
    @Column(name = "ultgradEst")
    private String ultgradEst;
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "telefonoAsesor")
    private String telefonoAsesor;
    @Column(name = "emailasesor")
    private String emailasesor;
    @Column(name = "numcedula")
    private String numcedula;

    public Asesores() {
    }

    public Asesores(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getNombreComp() {
        return nombreComp;
    }

    public void setNombreComp(String nombreComp) {
        this.nombreComp = nombreComp;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaTitulacion() {
        return fechaTitulacion;
    }

    public void setFechaTitulacion(Date fechaTitulacion) {
        this.fechaTitulacion = fechaTitulacion;
    }

    public Date getFechaCedula() {
        return fechaCedula;
    }

    public void setFechaCedula(Date fechaCedula) {
        this.fechaCedula = fechaCedula;
    }

    public String getUltgradEst() {
        return ultgradEst;
    }

    public void setUltgradEst(String ultgradEst) {
        this.ultgradEst = ultgradEst;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefonoAsesor() {
        return telefonoAsesor;
    }

    public void setTelefonoAsesor(String telefonoAsesor) {
        this.telefonoAsesor = telefonoAsesor;
    }

    public String getEmailasesor() {
        return emailasesor;
    }

    public void setEmailasesor(String emailasesor) {
        this.emailasesor = emailasesor;
    }

    public String getNumcedula() {
        return numcedula;
    }

    public void setNumcedula(String numcedula) {
        this.numcedula = numcedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsesor != null ? claveAsesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesores)) {
            return false;
        }
        Asesores other = (Asesores) object;
        if ((this.claveAsesor == null && other.claveAsesor != null) || (this.claveAsesor != null && !this.claveAsesor.equals(other.claveAsesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Asesores[ claveAsesor=" + claveAsesor + " ]";
    }
    
}
