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
@Table(name = "datosescbach")
@NamedQueries({
    @NamedQuery(name = "Datosescbach.findAll", query = "SELECT d FROM Datosescbach d")})
public class Datosescbach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "FechaFactura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Column(name = "noFactura")
    private Integer noFactura;
    @Column(name = "estudiosRealizados")
    private String estudiosRealizados;
    @Column(name = "lugarSecundariaCiudad")
    private String lugarSecundariaCiudad;
    @Column(name = "lugarSecundariaEdo")
    private String lugarSecundariaEdo;
    @Column(name = "estudiosSistAbierto")
    private String estudiosSistAbierto;
    @Column(name = "axoConcluye")
    private String axoConcluye;
    @Column(name = "hrsDispone")
    private String hrsDispone;
    @Lob
    @Column(name = "motivossuspestudios")
    private String motivossuspestudios;

    public Datosescbach() {
    }

    public Datosescbach(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Integer getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public String getEstudiosRealizados() {
        return estudiosRealizados;
    }

    public void setEstudiosRealizados(String estudiosRealizados) {
        this.estudiosRealizados = estudiosRealizados;
    }

    public String getLugarSecundariaCiudad() {
        return lugarSecundariaCiudad;
    }

    public void setLugarSecundariaCiudad(String lugarSecundariaCiudad) {
        this.lugarSecundariaCiudad = lugarSecundariaCiudad;
    }

    public String getLugarSecundariaEdo() {
        return lugarSecundariaEdo;
    }

    public void setLugarSecundariaEdo(String lugarSecundariaEdo) {
        this.lugarSecundariaEdo = lugarSecundariaEdo;
    }

    public String getEstudiosSistAbierto() {
        return estudiosSistAbierto;
    }

    public void setEstudiosSistAbierto(String estudiosSistAbierto) {
        this.estudiosSistAbierto = estudiosSistAbierto;
    }

    public String getAxoConcluye() {
        return axoConcluye;
    }

    public void setAxoConcluye(String axoConcluye) {
        this.axoConcluye = axoConcluye;
    }

    public String getHrsDispone() {
        return hrsDispone;
    }

    public void setHrsDispone(String hrsDispone) {
        this.hrsDispone = hrsDispone;
    }

    public String getMotivossuspestudios() {
        return motivossuspestudios;
    }

    public void setMotivossuspestudios(String motivossuspestudios) {
        this.motivossuspestudios = motivossuspestudios;
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
        if (!(object instanceof Datosescbach)) {
            return false;
        }
        Datosescbach other = (Datosescbach) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosescbach[ matricula=" + matricula + " ]";
    }
    
}
