/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "datosocupacionaleslic")
@NamedQueries({
    @NamedQuery(name = "Datosocupacionaleslic.findAll", query = "SELECT d FROM Datosocupacionaleslic d")})
public class Datosocupacionaleslic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "tipoInstitucion")
    private String tipoInstitucion;
    @Column(name = "nombreInstitucion")
    private String nombreInstitucion;
    @Column(name = "direccionInst")
    private String direccionInst;
    @Column(name = "telefonoInst")
    private String telefonoInst;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "estudiosSon")
    private String estudiosSon;
    @Column(name = "estudiosPermiten")
    private String estudiosPermiten;
    @Column(name = "trabaja")
    private String trabaja;

    public Datosocupacionaleslic() {
    }

    public Datosocupacionaleslic(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setTipoInstitucion(String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDireccionInst() {
        return direccionInst;
    }

    public void setDireccionInst(String direccionInst) {
        this.direccionInst = direccionInst;
    }

    public String getTelefonoInst() {
        return telefonoInst;
    }

    public void setTelefonoInst(String telefonoInst) {
        this.telefonoInst = telefonoInst;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstudiosSon() {
        return estudiosSon;
    }

    public void setEstudiosSon(String estudiosSon) {
        this.estudiosSon = estudiosSon;
    }

    public String getEstudiosPermiten() {
        return estudiosPermiten;
    }

    public void setEstudiosPermiten(String estudiosPermiten) {
        this.estudiosPermiten = estudiosPermiten;
    }

    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
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
        if (!(object instanceof Datosocupacionaleslic)) {
            return false;
        }
        Datosocupacionaleslic other = (Datosocupacionaleslic) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosocupacionaleslic[ matricula=" + matricula + " ]";
    }
    
}
