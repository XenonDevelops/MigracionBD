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
@Table(name = "complementoalumno")
@NamedQueries({
    @NamedQuery(name = "Complementoalumno.findAll", query = "SELECT c FROM Complementoalumno c")})
public class Complementoalumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "lugarNacimiento")
    private String lugarNacimiento;
    @Column(name = "estadoCivil")
    private String estadoCivil;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "estado")
    private String estado;
    @Column(name = "telefonop")
    private String telefonop;
    @Column(name = "telRecados")
    private String telRecados;
    @Column(name = "telTrabajo")
    private String telTrabajo;
    @Column(name = "celular")
    private String celular;
    @Column(name = "emailalumno")
    private String emailalumno;
    @Column(name = "fechaEquivalencia")
    @Temporal(TemporalType.DATE)
    private Date fechaEquivalencia;
    @Column(name = "numEquivalencia")
    private String numEquivalencia;
    @Column(name = "tipoInscripcion")
    private String tipoInscripcion;
    @Column(name = "nombreUsuario")
    private String nombreUsuario;

    public Complementoalumno() {
    }

    public Complementoalumno(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTelefonop() {
        return telefonop;
    }

    public void setTelefonop(String telefonop) {
        this.telefonop = telefonop;
    }

    public String getTelRecados() {
        return telRecados;
    }

    public void setTelRecados(String telRecados) {
        this.telRecados = telRecados;
    }

    public String getTelTrabajo() {
        return telTrabajo;
    }

    public void setTelTrabajo(String telTrabajo) {
        this.telTrabajo = telTrabajo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmailalumno() {
        return emailalumno;
    }

    public void setEmailalumno(String emailalumno) {
        this.emailalumno = emailalumno;
    }

    public Date getFechaEquivalencia() {
        return fechaEquivalencia;
    }

    public void setFechaEquivalencia(Date fechaEquivalencia) {
        this.fechaEquivalencia = fechaEquivalencia;
    }

    public String getNumEquivalencia() {
        return numEquivalencia;
    }

    public void setNumEquivalencia(String numEquivalencia) {
        this.numEquivalencia = numEquivalencia;
    }

    public String getTipoInscripcion() {
        return tipoInscripcion;
    }

    public void setTipoInscripcion(String tipoInscripcion) {
        this.tipoInscripcion = tipoInscripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
        if (!(object instanceof Complementoalumno)) {
            return false;
        }
        Complementoalumno other = (Complementoalumno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Complementoalumno[ matricula=" + matricula + " ]";
    }
    
}
