/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "acusealumnoexamen")
@NamedQueries({
    @NamedQuery(name = "Acusealumnoexamen.findAll", query = "SELECT a FROM Acusealumnoexamen a")})
public class Acusealumnoexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claveTramite")
    private Integer claveTramite;
    @Basic(optional = false)
    @Column(name = "numeroSillas")
    private int numeroSillas;
    @Basic(optional = false)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "canon")
    private String canon;
    @Basic(optional = false)
    @Column(name = "pantalla")
    private String pantalla;
    @Basic(optional = false)
    @Column(name = "retroproyector")
    private String retroproyector;
    @Basic(optional = false)
    @Column(name = "brindis")
    private String brindis;

    public Acusealumnoexamen() {
    }

    public Acusealumnoexamen(Integer claveTramite) {
        this.claveTramite = claveTramite;
    }

    public Acusealumnoexamen(Integer claveTramite, int numeroSillas, String observaciones, String canon, String pantalla, String retroproyector, String brindis) {
        this.claveTramite = claveTramite;
        this.numeroSillas = numeroSillas;
        this.observaciones = observaciones;
        this.canon = canon;
        this.pantalla = pantalla;
        this.retroproyector = retroproyector;
        this.brindis = brindis;
    }

    public Integer getClaveTramite() {
        return claveTramite;
    }

    public void setClaveTramite(Integer claveTramite) {
        this.claveTramite = claveTramite;
    }

    public int getNumeroSillas() {
        return numeroSillas;
    }

    public void setNumeroSillas(int numeroSillas) {
        this.numeroSillas = numeroSillas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCanon() {
        return canon;
    }

    public void setCanon(String canon) {
        this.canon = canon;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getRetroproyector() {
        return retroproyector;
    }

    public void setRetroproyector(String retroproyector) {
        this.retroproyector = retroproyector;
    }

    public String getBrindis() {
        return brindis;
    }

    public void setBrindis(String brindis) {
        this.brindis = brindis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveTramite != null ? claveTramite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acusealumnoexamen)) {
            return false;
        }
        Acusealumnoexamen other = (Acusealumnoexamen) object;
        if ((this.claveTramite == null && other.claveTramite != null) || (this.claveTramite != null && !this.claveTramite.equals(other.claveTramite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Acusealumnoexamen[ claveTramite=" + claveTramite + " ]";
    }
    
}
