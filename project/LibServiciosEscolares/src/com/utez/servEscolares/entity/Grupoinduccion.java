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
@Table(name = "grupoinduccion")
@NamedQueries({
    @NamedQuery(name = "Grupoinduccion.findAll", query = "SELECT g FROM Grupoinduccion g")})
public class Grupoinduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveGrupoInd")
    private Integer claveGrupoInd;
    @Column(name = "nombreGrupoInd")
    private String nombreGrupoInd;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "capacidad")
    private Integer capacidad;

    public Grupoinduccion() {
    }

    public Grupoinduccion(Integer claveGrupoInd) {
        this.claveGrupoInd = claveGrupoInd;
    }

    public Integer getClaveGrupoInd() {
        return claveGrupoInd;
    }

    public void setClaveGrupoInd(Integer claveGrupoInd) {
        this.claveGrupoInd = claveGrupoInd;
    }

    public String getNombreGrupoInd() {
        return nombreGrupoInd;
    }

    public void setNombreGrupoInd(String nombreGrupoInd) {
        this.nombreGrupoInd = nombreGrupoInd;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveGrupoInd != null ? claveGrupoInd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoinduccion)) {
            return false;
        }
        Grupoinduccion other = (Grupoinduccion) object;
        if ((this.claveGrupoInd == null && other.claveGrupoInd != null) || (this.claveGrupoInd != null && !this.claveGrupoInd.equals(other.claveGrupoInd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Grupoinduccion[ claveGrupoInd=" + claveGrupoInd + " ]";
    }
    
}
