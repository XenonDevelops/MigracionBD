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
@Table(name = "controldocumentos")
@NamedQueries({
    @NamedQuery(name = "Controldocumentos.findAll", query = "SELECT c FROM Controldocumentos c")})
public class Controldocumentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "responsable")
    private Short responsable;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "actaNacimiento")
    private Short actaNacimiento;
    @Column(name = "certificadoSecundaria")
    private Short certificadoSecundaria;
    @Column(name = "certificadoPreparatoriaBachillerato")
    private Short certificadoPreparatoriaBachillerato;
    @Column(name = "certificadoLicenciatura")
    private Short certificadoLicenciatura;
    @Column(name = "certificadoPosgrado")
    private Short certificadoPosgrado;
    @Column(name = "actaExamenProfesional")
    private Short actaExamenProfesional;
    @Column(name = "tituloProfesional")
    private Short tituloProfesional;
    @Column(name = "certificadoParcialBachillerato")
    private Short certificadoParcialBachillerato;
    @Column(name = "certificadoParcialLicenciatura")
    private Short certificadoParcialLicenciatura;
    @Column(name = "certificadoParcialPosgrado")
    private Short certificadoParcialPosgrado;
    @Column(name = "equivalencia")
    private Short equivalencia;
    @Column(name = "fotografias")
    private Short fotografias;
    @Column(name = "otros")
    private String otros;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveControldocumentos")
    private Integer claveControldocumentos;

    public Controldocumentos() {
    }

    public Controldocumentos(Integer claveControldocumentos) {
        this.claveControldocumentos = claveControldocumentos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Short getResponsable() {
        return responsable;
    }

    public void setResponsable(Short responsable) {
        this.responsable = responsable;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getActaNacimiento() {
        return actaNacimiento;
    }

    public void setActaNacimiento(Short actaNacimiento) {
        this.actaNacimiento = actaNacimiento;
    }

    public Short getCertificadoSecundaria() {
        return certificadoSecundaria;
    }

    public void setCertificadoSecundaria(Short certificadoSecundaria) {
        this.certificadoSecundaria = certificadoSecundaria;
    }

    public Short getCertificadoPreparatoriaBachillerato() {
        return certificadoPreparatoriaBachillerato;
    }

    public void setCertificadoPreparatoriaBachillerato(Short certificadoPreparatoriaBachillerato) {
        this.certificadoPreparatoriaBachillerato = certificadoPreparatoriaBachillerato;
    }

    public Short getCertificadoLicenciatura() {
        return certificadoLicenciatura;
    }

    public void setCertificadoLicenciatura(Short certificadoLicenciatura) {
        this.certificadoLicenciatura = certificadoLicenciatura;
    }

    public Short getCertificadoPosgrado() {
        return certificadoPosgrado;
    }

    public void setCertificadoPosgrado(Short certificadoPosgrado) {
        this.certificadoPosgrado = certificadoPosgrado;
    }

    public Short getActaExamenProfesional() {
        return actaExamenProfesional;
    }

    public void setActaExamenProfesional(Short actaExamenProfesional) {
        this.actaExamenProfesional = actaExamenProfesional;
    }

    public Short getTituloProfesional() {
        return tituloProfesional;
    }

    public void setTituloProfesional(Short tituloProfesional) {
        this.tituloProfesional = tituloProfesional;
    }

    public Short getCertificadoParcialBachillerato() {
        return certificadoParcialBachillerato;
    }

    public void setCertificadoParcialBachillerato(Short certificadoParcialBachillerato) {
        this.certificadoParcialBachillerato = certificadoParcialBachillerato;
    }

    public Short getCertificadoParcialLicenciatura() {
        return certificadoParcialLicenciatura;
    }

    public void setCertificadoParcialLicenciatura(Short certificadoParcialLicenciatura) {
        this.certificadoParcialLicenciatura = certificadoParcialLicenciatura;
    }

    public Short getCertificadoParcialPosgrado() {
        return certificadoParcialPosgrado;
    }

    public void setCertificadoParcialPosgrado(Short certificadoParcialPosgrado) {
        this.certificadoParcialPosgrado = certificadoParcialPosgrado;
    }

    public Short getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Short equivalencia) {
        this.equivalencia = equivalencia;
    }

    public Short getFotografias() {
        return fotografias;
    }

    public void setFotografias(Short fotografias) {
        this.fotografias = fotografias;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Integer getClaveControldocumentos() {
        return claveControldocumentos;
    }

    public void setClaveControldocumentos(Integer claveControldocumentos) {
        this.claveControldocumentos = claveControldocumentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveControldocumentos != null ? claveControldocumentos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controldocumentos)) {
            return false;
        }
        Controldocumentos other = (Controldocumentos) object;
        if ((this.claveControldocumentos == null && other.claveControldocumentos != null) || (this.claveControldocumentos != null && !this.claveControldocumentos.equals(other.claveControldocumentos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Controldocumentos[ claveControldocumentos=" + claveControldocumentos + " ]";
    }
    
}
