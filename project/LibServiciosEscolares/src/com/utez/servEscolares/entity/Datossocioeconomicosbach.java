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
@Table(name = "datossocioeconomicosbach")
@NamedQueries({
    @NamedQuery(name = "Datossocioeconomicosbach.findAll", query = "SELECT d FROM Datossocioeconomicosbach d")})
public class Datossocioeconomicosbach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "personaDependiente")
    private String personaDependiente;
    @Column(name = "ingresoMesnual")
    private String ingresoMesnual;
    @Column(name = "casaActual")
    private String casaActual;
    @Column(name = "gastoesgresomensual")
    private String gastoesgresomensual;
    @Column(name = "comoEntro")
    private String comoEntro;
    @Column(name = "fechaActual")
    @Temporal(TemporalType.DATE)
    private Date fechaActual;

    public Datossocioeconomicosbach() {
    }

    public Datossocioeconomicosbach(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPersonaDependiente() {
        return personaDependiente;
    }

    public void setPersonaDependiente(String personaDependiente) {
        this.personaDependiente = personaDependiente;
    }

    public String getIngresoMesnual() {
        return ingresoMesnual;
    }

    public void setIngresoMesnual(String ingresoMesnual) {
        this.ingresoMesnual = ingresoMesnual;
    }

    public String getCasaActual() {
        return casaActual;
    }

    public void setCasaActual(String casaActual) {
        this.casaActual = casaActual;
    }

    public String getGastoesgresomensual() {
        return gastoesgresomensual;
    }

    public void setGastoesgresomensual(String gastoesgresomensual) {
        this.gastoesgresomensual = gastoesgresomensual;
    }

    public String getComoEntro() {
        return comoEntro;
    }

    public void setComoEntro(String comoEntro) {
        this.comoEntro = comoEntro;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datossocioeconomicosbach)) {
            return false;
        }
        Datossocioeconomicosbach other = (Datossocioeconomicosbach) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datossocioeconomicosbach[ matricula=" + matricula + " ]";
    }
    
}
