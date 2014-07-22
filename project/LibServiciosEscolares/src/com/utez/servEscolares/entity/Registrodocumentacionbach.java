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
@Table(name = "registrodocumentacionbach")
@NamedQueries({
    @NamedQuery(name = "Registrodocumentacionbach.findAll", query = "SELECT r FROM Registrodocumentacionbach r")})
public class Registrodocumentacionbach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "actaNac")
    private String actaNac;
    @Column(name = "copActNac")
    private String copActNac;
    @Column(name = "certSec")
    private String certSec;
    @Column(name = "copCetSec")
    private String copCetSec;
    @Column(name = "fotografias")
    private String fotografias;
    @Lob
    @Column(name = "otros")
    private String otros;
    @Column(name = "fechaentrega")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    @Column(name = "fechaemision")
    @Temporal(TemporalType.DATE)
    private Date fechaemision;
    @Column(name = "certParcialBach")
    private String certParcialBach;
    @Column(name = "equivalencia")
    private String equivalencia;

    public Registrodocumentacionbach() {
    }

    public Registrodocumentacionbach(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getActaNac() {
        return actaNac;
    }

    public void setActaNac(String actaNac) {
        this.actaNac = actaNac;
    }

    public String getCopActNac() {
        return copActNac;
    }

    public void setCopActNac(String copActNac) {
        this.copActNac = copActNac;
    }

    public String getCertSec() {
        return certSec;
    }

    public void setCertSec(String certSec) {
        this.certSec = certSec;
    }

    public String getCopCetSec() {
        return copCetSec;
    }

    public void setCopCetSec(String copCetSec) {
        this.copCetSec = copCetSec;
    }

    public String getFotografias() {
        return fotografias;
    }

    public void setFotografias(String fotografias) {
        this.fotografias = fotografias;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public String getCertParcialBach() {
        return certParcialBach;
    }

    public void setCertParcialBach(String certParcialBach) {
        this.certParcialBach = certParcialBach;
    }

    public String getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(String equivalencia) {
        this.equivalencia = equivalencia;
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
        if (!(object instanceof Registrodocumentacionbach)) {
            return false;
        }
        Registrodocumentacionbach other = (Registrodocumentacionbach) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registrodocumentacionbach[ matricula=" + matricula + " ]";
    }
    
}
