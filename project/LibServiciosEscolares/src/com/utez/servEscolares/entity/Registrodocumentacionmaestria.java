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
@Table(name = "registrodocumentacionmaestria")
@NamedQueries({
    @NamedQuery(name = "Registrodocumentacionmaestria.findAll", query = "SELECT r FROM Registrodocumentacionmaestria r")})
public class Registrodocumentacionmaestria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "actaNac")
    private String actaNac;
    @Column(name = "copiaActNac")
    private String copiaActNac;
    @Column(name = "certificadoLic")
    private String certificadoLic;
    @Column(name = "copiaCerLic")
    private String copiaCerLic;
    @Column(name = "fotos")
    private String fotos;
    @Column(name = "curriculum")
    private String curriculum;
    @Column(name = "cartaMotivos")
    private String cartaMotivos;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "fechaEmision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Lob
    @Column(name = "otros")
    private String otros;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRegistrodocumentacionmaestria")
    private Integer claveRegistrodocumentacionmaestria;

    public Registrodocumentacionmaestria() {
    }

    public Registrodocumentacionmaestria(Integer claveRegistrodocumentacionmaestria) {
        this.claveRegistrodocumentacionmaestria = claveRegistrodocumentacionmaestria;
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

    public String getCopiaActNac() {
        return copiaActNac;
    }

    public void setCopiaActNac(String copiaActNac) {
        this.copiaActNac = copiaActNac;
    }

    public String getCertificadoLic() {
        return certificadoLic;
    }

    public void setCertificadoLic(String certificadoLic) {
        this.certificadoLic = certificadoLic;
    }

    public String getCopiaCerLic() {
        return copiaCerLic;
    }

    public void setCopiaCerLic(String copiaCerLic) {
        this.copiaCerLic = copiaCerLic;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getCartaMotivos() {
        return cartaMotivos;
    }

    public void setCartaMotivos(String cartaMotivos) {
        this.cartaMotivos = cartaMotivos;
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

    public Integer getClaveRegistrodocumentacionmaestria() {
        return claveRegistrodocumentacionmaestria;
    }

    public void setClaveRegistrodocumentacionmaestria(Integer claveRegistrodocumentacionmaestria) {
        this.claveRegistrodocumentacionmaestria = claveRegistrodocumentacionmaestria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveRegistrodocumentacionmaestria != null ? claveRegistrodocumentacionmaestria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrodocumentacionmaestria)) {
            return false;
        }
        Registrodocumentacionmaestria other = (Registrodocumentacionmaestria) object;
        if ((this.claveRegistrodocumentacionmaestria == null && other.claveRegistrodocumentacionmaestria != null) || (this.claveRegistrodocumentacionmaestria != null && !this.claveRegistrodocumentacionmaestria.equals(other.claveRegistrodocumentacionmaestria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registrodocumentacionmaestria[ claveRegistrodocumentacionmaestria=" + claveRegistrodocumentacionmaestria + " ]";
    }
    
}
