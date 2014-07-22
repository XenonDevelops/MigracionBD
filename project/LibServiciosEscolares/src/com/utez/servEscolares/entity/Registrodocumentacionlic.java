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
@Table(name = "registrodocumentacionlic")
@NamedQueries({
    @NamedQuery(name = "Registrodocumentacionlic.findAll", query = "SELECT r FROM Registrodocumentacionlic r")})
public class Registrodocumentacionlic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "actaNac")
    private String actaNac;
    @Column(name = "copiaActuNac")
    private String copiaActuNac;
    @Column(name = "certPrepOrig")
    private String certPrepOrig;
    @Column(name = "copCertPrepOrig")
    private String copCertPrepOrig;
    @Column(name = "fotosInfantil")
    private String fotosInfantil;
    @Column(name = "copCertParLic")
    private String copCertParLic;
    @Column(name = "equivalencia")
    private String equivalencia;
    @Column(name = "copiaEquivalencia")
    private String copiaEquivalencia;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "fechaEmision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Lob
    @Column(name = "otros")
    private String otros;

    public Registrodocumentacionlic() {
    }

    public Registrodocumentacionlic(String matricula) {
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

    public String getCopiaActuNac() {
        return copiaActuNac;
    }

    public void setCopiaActuNac(String copiaActuNac) {
        this.copiaActuNac = copiaActuNac;
    }

    public String getCertPrepOrig() {
        return certPrepOrig;
    }

    public void setCertPrepOrig(String certPrepOrig) {
        this.certPrepOrig = certPrepOrig;
    }

    public String getCopCertPrepOrig() {
        return copCertPrepOrig;
    }

    public void setCopCertPrepOrig(String copCertPrepOrig) {
        this.copCertPrepOrig = copCertPrepOrig;
    }

    public String getFotosInfantil() {
        return fotosInfantil;
    }

    public void setFotosInfantil(String fotosInfantil) {
        this.fotosInfantil = fotosInfantil;
    }

    public String getCopCertParLic() {
        return copCertParLic;
    }

    public void setCopCertParLic(String copCertParLic) {
        this.copCertParLic = copCertParLic;
    }

    public String getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(String equivalencia) {
        this.equivalencia = equivalencia;
    }

    public String getCopiaEquivalencia() {
        return copiaEquivalencia;
    }

    public void setCopiaEquivalencia(String copiaEquivalencia) {
        this.copiaEquivalencia = copiaEquivalencia;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
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
        if (!(object instanceof Registrodocumentacionlic)) {
            return false;
        }
        Registrodocumentacionlic other = (Registrodocumentacionlic) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registrodocumentacionlic[ matricula=" + matricula + " ]";
    }
    
}
