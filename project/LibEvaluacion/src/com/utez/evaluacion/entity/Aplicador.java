/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "aplicador")
@NamedQueries({
    @NamedQuery(name = "Aplicador.findAll", query = "SELECT a FROM Aplicador a")})
public class Aplicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAplicador")
    private Integer claveAplicador;
    @Column(name = "nombreAplicador")
    private String nombreAplicador;
    @Column(name = "RFCAplicador")
    private String rFCAplicador;
    @Column(name = "CURPAplicador")
    private String cURPAplicador;
    @Column(name = "telefonoAplicador")
    private String telefonoAplicador;
    @Lob
    @Column(name = "fotoAplicador")
    private byte[] fotoAplicador;
    @Column(name = "telefonoAplicador2")
    private String telefonoAplicador2;
    @Column(name = "direccionAplicador")
    private String direccionAplicador;
    @Column(name = "edoBaja")
    private Integer edoBaja;

    public Aplicador() {
    }

    public Aplicador(Integer claveAplicador) {
        this.claveAplicador = claveAplicador;
    }

    public Integer getClaveAplicador() {
        return claveAplicador;
    }

    public void setClaveAplicador(Integer claveAplicador) {
        this.claveAplicador = claveAplicador;
    }

    public String getNombreAplicador() {
        return nombreAplicador;
    }

    public void setNombreAplicador(String nombreAplicador) {
        this.nombreAplicador = nombreAplicador;
    }

    public String getRFCAplicador() {
        return rFCAplicador;
    }

    public void setRFCAplicador(String rFCAplicador) {
        this.rFCAplicador = rFCAplicador;
    }

    public String getCURPAplicador() {
        return cURPAplicador;
    }

    public void setCURPAplicador(String cURPAplicador) {
        this.cURPAplicador = cURPAplicador;
    }

    public String getTelefonoAplicador() {
        return telefonoAplicador;
    }

    public void setTelefonoAplicador(String telefonoAplicador) {
        this.telefonoAplicador = telefonoAplicador;
    }

    public byte[] getFotoAplicador() {
        return fotoAplicador;
    }

    public void setFotoAplicador(byte[] fotoAplicador) {
        this.fotoAplicador = fotoAplicador;
    }

    public String getTelefonoAplicador2() {
        return telefonoAplicador2;
    }

    public void setTelefonoAplicador2(String telefonoAplicador2) {
        this.telefonoAplicador2 = telefonoAplicador2;
    }

    public String getDireccionAplicador() {
        return direccionAplicador;
    }

    public void setDireccionAplicador(String direccionAplicador) {
        this.direccionAplicador = direccionAplicador;
    }

    public Integer getEdoBaja() {
        return edoBaja;
    }

    public void setEdoBaja(Integer edoBaja) {
        this.edoBaja = edoBaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAplicador != null ? claveAplicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicador)) {
            return false;
        }
        Aplicador other = (Aplicador) object;
        if ((this.claveAplicador == null && other.claveAplicador != null) || (this.claveAplicador != null && !this.claveAplicador.equals(other.claveAplicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Aplicador[ claveAplicador=" + claveAplicador + " ]";
    }
    
}
