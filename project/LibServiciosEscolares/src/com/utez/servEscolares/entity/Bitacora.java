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
@Table(name = "bitacora")
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b")})
public class Bitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveBitacora")
    private Integer claveBitacora;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "nombreusuario")
    private String nombreusuario;
    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    @Column(name = "observaciones")
    private String observaciones;

    public Bitacora() {
    }

    public Bitacora(Integer claveBitacora) {
        this.claveBitacora = claveBitacora;
    }

    public Integer getClaveBitacora() {
        return claveBitacora;
    }

    public void setClaveBitacora(Integer claveBitacora) {
        this.claveBitacora = claveBitacora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.claveBitacora == null && other.claveBitacora != null) || (this.claveBitacora != null && !this.claveBitacora.equals(other.claveBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Bitacora[ claveBitacora=" + claveBitacora + " ]";
    }
    
}
