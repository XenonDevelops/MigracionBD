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
@Table(name = "encuestabajas")
@NamedQueries({
    @NamedQuery(name = "Encuestabajas.findAll", query = "SELECT e FROM Encuestabajas e")})
public class Encuestabajas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "expectativa")
    private String expectativa;
    @Column(name = "servicios")
    private String servicios;
    @Column(name = "descripcionServicios")
    private String descripcionServicios;

    public Encuestabajas() {
    }

    public Encuestabajas(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getExpectativa() {
        return expectativa;
    }

    public void setExpectativa(String expectativa) {
        this.expectativa = expectativa;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getDescripcionServicios() {
        return descripcionServicios;
    }

    public void setDescripcionServicios(String descripcionServicios) {
        this.descripcionServicios = descripcionServicios;
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
        if (!(object instanceof Encuestabajas)) {
            return false;
        }
        Encuestabajas other = (Encuestabajas) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Encuestabajas[ matricula=" + matricula + " ]";
    }
    
}
