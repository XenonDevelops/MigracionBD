/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "requisitostramitetitulacion")
@NamedQueries({
    @NamedQuery(name = "Requisitostramitetitulacion.findAll", query = "SELECT r FROM Requisitostramitetitulacion r")})
public class Requisitostramitetitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveTramite")
    private Integer claveTramite;
    @Column(name = "liberacionServicioSocial")
    private String liberacionServicioSocial;
    @Column(name = "cartaPasante")
    private String cartaPasante;
    @Column(name = "constanciaNoadeudo")
    private String constanciaNoadeudo;
    @Column(name = "certificadoTotal")
    private String certificadoTotal;
    @Column(name = "donacionLibros")
    private String donacionLibros;
    @Column(name = "acreditacionComputacion")
    private String acreditacionComputacion;
    @Column(name = "acreditacionIngles")
    private String acreditacionIngles;
    @Column(name = "pagosTitulacion")
    private String pagosTitulacion;
    @Column(name = "Fotografias")
    private String fotografias;
    @Column(name = "votos")
    private String votos;
    @Column(name = "ordenPagoDireccion")
    private String ordenPagoDireccion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRequisitostramitetitulacion")
    private Integer claveRequisitostramitetitulacion;

    public Requisitostramitetitulacion() {
    }

    public Requisitostramitetitulacion(Integer claveRequisitostramitetitulacion) {
        this.claveRequisitostramitetitulacion = claveRequisitostramitetitulacion;
    }

    public Integer getClaveTramite() {
        return claveTramite;
    }

    public void setClaveTramite(Integer claveTramite) {
        this.claveTramite = claveTramite;
    }

    public String getLiberacionServicioSocial() {
        return liberacionServicioSocial;
    }

    public void setLiberacionServicioSocial(String liberacionServicioSocial) {
        this.liberacionServicioSocial = liberacionServicioSocial;
    }

    public String getCartaPasante() {
        return cartaPasante;
    }

    public void setCartaPasante(String cartaPasante) {
        this.cartaPasante = cartaPasante;
    }

    public String getConstanciaNoadeudo() {
        return constanciaNoadeudo;
    }

    public void setConstanciaNoadeudo(String constanciaNoadeudo) {
        this.constanciaNoadeudo = constanciaNoadeudo;
    }

    public String getCertificadoTotal() {
        return certificadoTotal;
    }

    public void setCertificadoTotal(String certificadoTotal) {
        this.certificadoTotal = certificadoTotal;
    }

    public String getDonacionLibros() {
        return donacionLibros;
    }

    public void setDonacionLibros(String donacionLibros) {
        this.donacionLibros = donacionLibros;
    }

    public String getAcreditacionComputacion() {
        return acreditacionComputacion;
    }

    public void setAcreditacionComputacion(String acreditacionComputacion) {
        this.acreditacionComputacion = acreditacionComputacion;
    }

    public String getAcreditacionIngles() {
        return acreditacionIngles;
    }

    public void setAcreditacionIngles(String acreditacionIngles) {
        this.acreditacionIngles = acreditacionIngles;
    }

    public String getPagosTitulacion() {
        return pagosTitulacion;
    }

    public void setPagosTitulacion(String pagosTitulacion) {
        this.pagosTitulacion = pagosTitulacion;
    }

    public String getFotografias() {
        return fotografias;
    }

    public void setFotografias(String fotografias) {
        this.fotografias = fotografias;
    }

    public String getVotos() {
        return votos;
    }

    public void setVotos(String votos) {
        this.votos = votos;
    }

    public String getOrdenPagoDireccion() {
        return ordenPagoDireccion;
    }

    public void setOrdenPagoDireccion(String ordenPagoDireccion) {
        this.ordenPagoDireccion = ordenPagoDireccion;
    }

    public Integer getClaveRequisitostramitetitulacion() {
        return claveRequisitostramitetitulacion;
    }

    public void setClaveRequisitostramitetitulacion(Integer claveRequisitostramitetitulacion) {
        this.claveRequisitostramitetitulacion = claveRequisitostramitetitulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveRequisitostramitetitulacion != null ? claveRequisitostramitetitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requisitostramitetitulacion)) {
            return false;
        }
        Requisitostramitetitulacion other = (Requisitostramitetitulacion) object;
        if ((this.claveRequisitostramitetitulacion == null && other.claveRequisitostramitetitulacion != null) || (this.claveRequisitostramitetitulacion != null && !this.claveRequisitostramitetitulacion.equals(other.claveRequisitostramitetitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Requisitostramitetitulacion[ claveRequisitostramitetitulacion=" + claveRequisitostramitetitulacion + " ]";
    }
    
}
