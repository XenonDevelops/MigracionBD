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
@Table(name = "datosgralesmaestria")
@NamedQueries({
    @NamedQuery(name = "Datosgralesmaestria.findAll", query = "SELECT d FROM Datosgralesmaestria d")})
public class Datosgralesmaestria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "InstitucionLabora")
    private String institucionLabora;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaFactura")
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    @Column(name = "numFactura")
    private Integer numFactura;
    @Column(name = "opcTitulacion")
    private String opcTitulacion;
    @Column(name = "fechaActual")
    @Temporal(TemporalType.DATE)
    private Date fechaActual;
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "egresadoDe")
    private String egresadoDe;
    @Column(name = "titulado")
    private String titulado;
    @Column(name = "RFC")
    private String rfc;

    public Datosgralesmaestria() {
    }

    public Datosgralesmaestria(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getInstitucionLabora() {
        return institucionLabora;
    }

    public void setInstitucionLabora(String institucionLabora) {
        this.institucionLabora = institucionLabora;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Integer getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    public String getOpcTitulacion() {
        return opcTitulacion;
    }

    public void setOpcTitulacion(String opcTitulacion) {
        this.opcTitulacion = opcTitulacion;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEgresadoDe() {
        return egresadoDe;
    }

    public void setEgresadoDe(String egresadoDe) {
        this.egresadoDe = egresadoDe;
    }

    public String getTitulado() {
        return titulado;
    }

    public void setTitulado(String titulado) {
        this.titulado = titulado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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
        if (!(object instanceof Datosgralesmaestria)) {
            return false;
        }
        Datosgralesmaestria other = (Datosgralesmaestria) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Datosgralesmaestria[ matricula=" + matricula + " ]";
    }
    
}
