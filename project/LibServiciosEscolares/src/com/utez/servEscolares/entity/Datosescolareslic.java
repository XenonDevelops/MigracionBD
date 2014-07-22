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
@Table(name = "datosescolareslic")
@NamedQueries({
    @NamedQuery(name = "Datosescolareslic.findAll", query = "SELECT d FROM Datosescolareslic d")})
public class Datosescolareslic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "fachaFactura")
    @Temporal(TemporalType.DATE)
    private Date fachaFactura;
    @Column(name = "noFactura")
    private Integer noFactura;
    @Column(name = "estudiosRealizados")
    private String estudiosRealizados;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "estado")
    private String estado;
    @Column(name = "sistemaAbierto")
    private String sistemaAbierto;
    @Column(name = "programasConoce")
    private String programasConoce;
    @Column(name = "hrsestudio")
    private String hrsestudio;

    public Datosescolareslic() {
    }

    public Datosescolareslic(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFachaFactura() {
        return fachaFactura;
    }

    public void setFachaFactura(Date fachaFactura) {
        this.fachaFactura = fachaFactura;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSistemaAbierto() {
        return sistemaAbierto;
    }

    public void setSistemaAbierto(String sistemaAbierto) {
        this.sistemaAbierto = sistemaAbierto;
    }

    public String getProgramasConoce() {
        return programasConoce;
    }

    public void setProgramasConoce(String programasConoce) {
        this.programasConoce = programasConoce;
    }

    public String getHrsestudio() {
        return hrsestudio;
    }

    public void setHrsestudio(String hrsestudio) {
        this.hrsestudio = hrsestudio;
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
        if (!(object instanceof Datosescolareslic)) {
            return false;
        }
        Datosescolareslic other = (Datosescolareslic) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosescolareslic[ matricula=" + matricula + " ]";
    }
    
}
