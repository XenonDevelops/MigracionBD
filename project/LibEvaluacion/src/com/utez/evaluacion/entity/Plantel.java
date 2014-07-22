/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "plantel")
@NamedQueries({
    @NamedQuery(name = "Plantel.findAll", query = "SELECT p FROM Plantel p")})
public class Plantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clavePlantel")
    private Integer clavePlantel;
    @Column(name = "nombrePlantel")
    private String nombrePlantel;
    @OneToMany(mappedBy = "clavePlantel")
    private List<Asignacionplan> asignacionplanList;
    @OneToMany(mappedBy = "clavePlantel")
    private List<Asignacionactividad> asignacionactividadList;
    @OneToMany(mappedBy = "clavePlantel")
    private List<Asesor> asesorList;

    public Plantel() {
    }

    public Plantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public Integer getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public String getNombrePlantel() {
        return nombrePlantel;
    }

    public void setNombrePlantel(String nombrePlantel) {
        this.nombrePlantel = nombrePlantel;
    }

    public List<Asignacionplan> getAsignacionplanList() {
        return asignacionplanList;
    }

    public void setAsignacionplanList(List<Asignacionplan> asignacionplanList) {
        this.asignacionplanList = asignacionplanList;
    }

    public List<Asignacionactividad> getAsignacionactividadList() {
        return asignacionactividadList;
    }

    public void setAsignacionactividadList(List<Asignacionactividad> asignacionactividadList) {
        this.asignacionactividadList = asignacionactividadList;
    }

    public List<Asesor> getAsesorList() {
        return asesorList;
    }

    public void setAsesorList(List<Asesor> asesorList) {
        this.asesorList = asesorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavePlantel != null ? clavePlantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantel)) {
            return false;
        }
        Plantel other = (Plantel) object;
        if ((this.clavePlantel == null && other.clavePlantel != null) || (this.clavePlantel != null && !this.clavePlantel.equals(other.clavePlantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Plantel[ clavePlantel=" + clavePlantel + " ]";
    }
    
}
