/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "archivomuerto")
@NamedQueries({
    @NamedQuery(name = "Archivomuerto.findAll", query = "SELECT a FROM Archivomuerto a")})
public class Archivomuerto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveBitacora")
    private Integer claveBitacora;
    @Column(name = "tipoMovimiento")
    private Integer tipoMovimiento;
    @Basic(optional = false)
    @Column(name = "fechaMovimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Column(name = "movimiento")
    private String movimiento;
    @Column(name = "clavePlantel")
    private Integer clavePlantel;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "periodo")
    private String periodo;

    public Archivomuerto() {
    }

    public Archivomuerto(Integer claveBitacora) {
        this.claveBitacora = claveBitacora;
    }

    public Archivomuerto(Integer claveBitacora, Date fechaMovimiento) {
        this.claveBitacora = claveBitacora;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getClaveBitacora() {
        return claveBitacora;
    }

    public void setClaveBitacora(Integer claveBitacora) {
        this.claveBitacora = claveBitacora;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveBitacora != null ? claveBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivomuerto)) {
            return false;
        }
        Archivomuerto other = (Archivomuerto) object;
        if ((this.claveBitacora == null && other.claveBitacora != null) || (this.claveBitacora != null && !this.claveBitacora.equals(other.claveBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Archivomuerto[ claveBitacora=" + claveBitacora + " ]";
    }
    
}
