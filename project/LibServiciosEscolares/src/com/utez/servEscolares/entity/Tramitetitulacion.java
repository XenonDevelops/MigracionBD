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
@Table(name = "tramitetitulacion")
@NamedQueries({
    @NamedQuery(name = "Tramitetitulacion.findAll", query = "SELECT t FROM Tramitetitulacion t")})
public class Tramitetitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clavetramite")
    private Integer clavetramite;
    @Column(name = "clavesolicitud")
    private Integer clavesolicitud;
    @Column(name = "matricula")
    private String matricula;

    public Tramitetitulacion() {
    }

    public Tramitetitulacion(Integer clavetramite) {
        this.clavetramite = clavetramite;
    }

    public Integer getClavetramite() {
        return clavetramite;
    }

    public void setClavetramite(Integer clavetramite) {
        this.clavetramite = clavetramite;
    }

    public Integer getClavesolicitud() {
        return clavesolicitud;
    }

    public void setClavesolicitud(Integer clavesolicitud) {
        this.clavesolicitud = clavesolicitud;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavetramite != null ? clavetramite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tramitetitulacion)) {
            return false;
        }
        Tramitetitulacion other = (Tramitetitulacion) object;
        if ((this.clavetramite == null && other.clavetramite != null) || (this.clavetramite != null && !this.clavetramite.equals(other.clavetramite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Tramitetitulacion[ clavetramite=" + clavetramite + " ]";
    }
    
}
