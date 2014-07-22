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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignaaplicador")
@NamedQueries({
    @NamedQuery(name = "Asignaaplicador.findAll", query = "SELECT a FROM Asignaaplicador a")})
public class Asignaaplicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsigna")
    private Integer claveAsigna;
    @Column(name = "claveAplicador")
    private Integer claveAplicador;
    @Column(name = "salon")
    private Integer salon;
    @JoinColumn(name = "claveAplicacion", referencedColumnName = "claveAplicacion")
    @ManyToOne
    private Aplicacionexamen claveAplicacion;

    public Asignaaplicador() {
    }

    public Asignaaplicador(Integer claveAsigna) {
        this.claveAsigna = claveAsigna;
    }

    public Integer getClaveAsigna() {
        return claveAsigna;
    }

    public void setClaveAsigna(Integer claveAsigna) {
        this.claveAsigna = claveAsigna;
    }

    public Integer getClaveAplicador() {
        return claveAplicador;
    }

    public void setClaveAplicador(Integer claveAplicador) {
        this.claveAplicador = claveAplicador;
    }

    public Integer getSalon() {
        return salon;
    }

    public void setSalon(Integer salon) {
        this.salon = salon;
    }

    public Aplicacionexamen getClaveAplicacion() {
        return claveAplicacion;
    }

    public void setClaveAplicacion(Aplicacionexamen claveAplicacion) {
        this.claveAplicacion = claveAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsigna != null ? claveAsigna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignaaplicador)) {
            return false;
        }
        Asignaaplicador other = (Asignaaplicador) object;
        if ((this.claveAsigna == null && other.claveAsigna != null) || (this.claveAsigna != null && !this.claveAsigna.equals(other.claveAsigna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asignaaplicador[ claveAsigna=" + claveAsigna + " ]";
    }
    
}
