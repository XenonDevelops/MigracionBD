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
@Table(name = "datosocupacionalesbach")
@NamedQueries({
    @NamedQuery(name = "Datosocupacionalesbach.findAll", query = "SELECT d FROM Datosocupacionalesbach d")})
public class Datosocupacionalesbach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "trabaja")
    private String trabaja;
    @Column(name = "instLabora")
    private String instLabora;
    @Column(name = "nombreInst")
    private String nombreInst;
    @Column(name = "direccionInst")
    private String direccionInst;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "antiguedad")
    private String antiguedad;
    @Column(name = "contarPrepa")
    private String contarPrepa;
    @Column(name = "estudiosPermitan")
    private String estudiosPermitan;
    @Column(name = "decidioEstudiar")
    private String decidioEstudiar;
    @Column(name = "motivacion")
    private String motivacion;
    @Column(name = "telefonoInst")
    private String telefonoInst;

    public Datosocupacionalesbach() {
    }

    public Datosocupacionalesbach(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(String trabaja) {
        this.trabaja = trabaja;
    }

    public String getInstLabora() {
        return instLabora;
    }

    public void setInstLabora(String instLabora) {
        this.instLabora = instLabora;
    }

    public String getNombreInst() {
        return nombreInst;
    }

    public void setNombreInst(String nombreInst) {
        this.nombreInst = nombreInst;
    }

    public String getDireccionInst() {
        return direccionInst;
    }

    public void setDireccionInst(String direccionInst) {
        this.direccionInst = direccionInst;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getContarPrepa() {
        return contarPrepa;
    }

    public void setContarPrepa(String contarPrepa) {
        this.contarPrepa = contarPrepa;
    }

    public String getEstudiosPermitan() {
        return estudiosPermitan;
    }

    public void setEstudiosPermitan(String estudiosPermitan) {
        this.estudiosPermitan = estudiosPermitan;
    }

    public String getDecidioEstudiar() {
        return decidioEstudiar;
    }

    public void setDecidioEstudiar(String decidioEstudiar) {
        this.decidioEstudiar = decidioEstudiar;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }

    public String getTelefonoInst() {
        return telefonoInst;
    }

    public void setTelefonoInst(String telefonoInst) {
        this.telefonoInst = telefonoInst;
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
        if (!(object instanceof Datosocupacionalesbach)) {
            return false;
        }
        Datosocupacionalesbach other = (Datosocupacionalesbach) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosocupacionalesbach[ matricula=" + matricula + " ]";
    }
    
}
