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
@Table(name = "procesotitulacion")
@NamedQueries({
    @NamedQuery(name = "Procesotitulacion.findAll", query = "SELECT p FROM Procesotitulacion p")})
public class Procesotitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "status")
    private Integer status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProcesotitulacion")
    private Integer claveProcesotitulacion;

    public Procesotitulacion() {
    }

    public Procesotitulacion(Integer claveProcesotitulacion) {
        this.claveProcesotitulacion = claveProcesotitulacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClaveProcesotitulacion() {
        return claveProcesotitulacion;
    }

    public void setClaveProcesotitulacion(Integer claveProcesotitulacion) {
        this.claveProcesotitulacion = claveProcesotitulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveProcesotitulacion != null ? claveProcesotitulacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesotitulacion)) {
            return false;
        }
        Procesotitulacion other = (Procesotitulacion) object;
        if ((this.claveProcesotitulacion == null && other.claveProcesotitulacion != null) || (this.claveProcesotitulacion != null && !this.claveProcesotitulacion.equals(other.claveProcesotitulacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Procesotitulacion[ claveProcesotitulacion=" + claveProcesotitulacion + " ]";
    }
    
}
