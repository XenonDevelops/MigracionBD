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
@Table(name = "datosgeneraleslic")
@NamedQueries({
    @NamedQuery(name = "Datosgeneraleslic.findAll", query = "SELECT d FROM Datosgeneraleslic d")})
public class Datosgeneraleslic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "carAEstudiar")
    private String carAEstudiar;
    @Column(name = "decidioCar")
    private String decidioCar;
    @Column(name = "motivos")
    private String motivos;
    @Column(name = "personasDependen")
    private String personasDependen;
    @Column(name = "ingresoMensual")
    private String ingresoMensual;
    @Column(name = "casa")
    private String casa;
    @Column(name = "comoEntero")
    private String comoEntero;
    @Column(name = "fechaActual")
    @Temporal(TemporalType.DATE)
    private Date fechaActual;

    public Datosgeneraleslic() {
    }

    public Datosgeneraleslic(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarAEstudiar() {
        return carAEstudiar;
    }

    public void setCarAEstudiar(String carAEstudiar) {
        this.carAEstudiar = carAEstudiar;
    }

    public String getDecidioCar() {
        return decidioCar;
    }

    public void setDecidioCar(String decidioCar) {
        this.decidioCar = decidioCar;
    }

    public String getMotivos() {
        return motivos;
    }

    public void setMotivos(String motivos) {
        this.motivos = motivos;
    }

    public String getPersonasDependen() {
        return personasDependen;
    }

    public void setPersonasDependen(String personasDependen) {
        this.personasDependen = personasDependen;
    }

    public String getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(String ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getComoEntero() {
        return comoEntero;
    }

    public void setComoEntero(String comoEntero) {
        this.comoEntero = comoEntero;
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
        if (!(object instanceof Datosgeneraleslic)) {
            return false;
        }
        Datosgeneraleslic other = (Datosgeneraleslic) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosgeneraleslic[ matricula=" + matricula + " ]";
    }
    
}
