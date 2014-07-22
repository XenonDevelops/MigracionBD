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
@Table(name = "moduloseventos")
@NamedQueries({
    @NamedQuery(name = "Moduloseventos.findAll", query = "SELECT m FROM Moduloseventos m")})
public class Moduloseventos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveModulo")
    private Integer claveModulo;
    @Column(name = "numEvento")
    private Integer numEvento;
    @Column(name = "nombreModulo")
    private String nombreModulo;

    public Moduloseventos() {
    }

    public Moduloseventos(Integer claveModulo) {
        this.claveModulo = claveModulo;
    }

    public Integer getClaveModulo() {
        return claveModulo;
    }

    public void setClaveModulo(Integer claveModulo) {
        this.claveModulo = claveModulo;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveModulo != null ? claveModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moduloseventos)) {
            return false;
        }
        Moduloseventos other = (Moduloseventos) object;
        if ((this.claveModulo == null && other.claveModulo != null) || (this.claveModulo != null && !this.claveModulo.equals(other.claveModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Moduloseventos[ claveModulo=" + claveModulo + " ]";
    }
    
}
