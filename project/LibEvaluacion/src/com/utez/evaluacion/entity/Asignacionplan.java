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
@Table(name = "asignacionplan")
@NamedQueries({
    @NamedQuery(name = "Asignacionplan.findAll", query = "SELECT a FROM Asignacionplan a")})
public class Asignacionplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsignacion")
    private Integer claveAsignacion;
    @JoinColumn(name = "revoe", referencedColumnName = "revoe")
    @ManyToOne
    private Plandeestudios revoe;
    @JoinColumn(name = "clavePlantel", referencedColumnName = "clavePlantel")
    @ManyToOne
    private Plantel clavePlantel;

    public Asignacionplan() {
    }

    public Asignacionplan(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public Integer getClaveAsignacion() {
        return claveAsignacion;
    }

    public void setClaveAsignacion(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public Plandeestudios getRevoe() {
        return revoe;
    }

    public void setRevoe(Plandeestudios revoe) {
        this.revoe = revoe;
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
        hash += (claveAsignacion != null ? claveAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacionplan)) {
            return false;
        }
        Asignacionplan other = (Asignacionplan) object;
        if ((this.claveAsignacion == null && other.claveAsignacion != null) || (this.claveAsignacion != null && !this.claveAsignacion.equals(other.claveAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asignacionplan[ claveAsignacion=" + claveAsignacion + " ]";
    }
    
}
