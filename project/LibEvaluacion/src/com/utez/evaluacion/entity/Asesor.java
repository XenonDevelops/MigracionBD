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
@Table(name = "asesor")
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a")})
public class Asesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "nombreAsesor")
    private String nombreAsesor;
    @Column(name = "edoBaja")
    private Integer edoBaja;
    @JoinColumn(name = "clavePlantel", referencedColumnName = "clavePlantel")
    @ManyToOne
    private Plantel clavePlantel;

    public Asesor() {
    }

    public Asesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public Integer getEdoBaja() {
        return edoBaja;
    }

    public void setEdoBaja(Integer edoBaja) {
        this.edoBaja = edoBaja;
    }

    public Plantel getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Plantel clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsesor != null ? claveAsesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.claveAsesor == null && other.claveAsesor != null) || (this.claveAsesor != null && !this.claveAsesor.equals(other.claveAsesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asesor[ claveAsesor=" + claveAsesor + " ]";
    }
    
}
