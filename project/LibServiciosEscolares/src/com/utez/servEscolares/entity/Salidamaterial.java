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
@Table(name = "salidamaterial")
@NamedQueries({
    @NamedQuery(name = "Salidamaterial.findAll", query = "SELECT s FROM Salidamaterial s")})
public class Salidamaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveSalida")
    private Integer claveSalida;
    @Column(name = "fechaEntrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "matricula")
    private String matricula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "tipoSolicitante")
    private String tipoSolicitante;
    @Basic(optional = false)
    @Column(name = "claveMaterial")
    private int claveMaterial;

    public Salidamaterial() {
    }

    public Salidamaterial(Integer claveSalida) {
        this.claveSalida = claveSalida;
    }

    public Salidamaterial(Integer claveSalida, int claveMaterial) {
        this.claveSalida = claveSalida;
        this.claveMaterial = claveMaterial;
    }

    public Integer getClaveSalida() {
        return claveSalida;
    }

    public void setClaveSalida(Integer claveSalida) {
        this.claveSalida = claveSalida;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public int getClaveMaterial() {
        return claveMaterial;
    }

    public void setClaveMaterial(int claveMaterial) {
        this.claveMaterial = claveMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveSalida != null ? claveSalida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salidamaterial)) {
            return false;
        }
        Salidamaterial other = (Salidamaterial) object;
        if ((this.claveSalida == null && other.claveSalida != null) || (this.claveSalida != null && !this.claveSalida.equals(other.claveSalida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Salidamaterial[ claveSalida=" + claveSalida + " ]";
    }
    
}
