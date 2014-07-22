/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "constantes")
@NamedQueries({
    @NamedQuery(name = "Constantes.findAll", query = "SELECT c FROM Constantes c")})
public class Constantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveConstante")
    private Integer claveConstante;
    @Column(name = "nombreConstante")
    private String nombreConstante;
    @Column(name = "valorConstante")
    private String valorConstante;
    @Column(name = "ambito")
    private String ambito;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "puesto")
    private String puesto;

    public Constantes() {
    }

    public Constantes(Integer claveConstante) {
        this.claveConstante = claveConstante;
    }

    public Integer getClaveConstante() {
        return claveConstante;
    }

    public void setClaveConstante(Integer claveConstante) {
        this.claveConstante = claveConstante;
    }

    public String getNombreConstante() {
        return nombreConstante;
    }

    public void setNombreConstante(String nombreConstante) {
        this.nombreConstante = nombreConstante;
    }

    public String getValorConstante() {
        return valorConstante;
    }

    public void setValorConstante(String valorConstante) {
        this.valorConstante = valorConstante;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveConstante != null ? claveConstante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Constantes)) {
            return false;
        }
        Constantes other = (Constantes) object;
        if ((this.claveConstante == null && other.claveConstante != null) || (this.claveConstante != null && !this.claveConstante.equals(other.claveConstante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Constantes[ claveConstante=" + claveConstante + " ]";
    }
    
}
