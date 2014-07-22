/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "asignatura_seriada")
@NamedQueries({
    @NamedQuery(name = "AsignaturaSeriada.findAll", query = "SELECT a FROM AsignaturaSeriada a")})
public class AsignaturaSeriada implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsignaturaSeriadaPK asignaturaSeriadaPK;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumn(name = "id_asignaturaseriada", referencedColumnName = "id_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura1;

    public AsignaturaSeriada() {
    }

    public AsignaturaSeriada(AsignaturaSeriadaPK asignaturaSeriadaPK) {
        this.asignaturaSeriadaPK = asignaturaSeriadaPK;
    }

    public AsignaturaSeriada(long idAsignatura, long idAsignaturaseriada) {
        this.asignaturaSeriadaPK = new AsignaturaSeriadaPK(idAsignatura, idAsignaturaseriada);
    }

    public AsignaturaSeriadaPK getAsignaturaSeriadaPK() {
        return asignaturaSeriadaPK;
    }

    public void setAsignaturaSeriadaPK(AsignaturaSeriadaPK asignaturaSeriadaPK) {
        this.asignaturaSeriadaPK = asignaturaSeriadaPK;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura1() {
        return asignatura1;
    }

    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignaturaSeriadaPK != null ? asignaturaSeriadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaturaSeriada)) {
            return false;
        }
        AsignaturaSeriada other = (AsignaturaSeriada) object;
        if ((this.asignaturaSeriadaPK == null && other.asignaturaSeriadaPK != null) || (this.asignaturaSeriadaPK != null && !this.asignaturaSeriadaPK.equals(other.asignaturaSeriadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignaturaSeriada[ asignaturaSeriadaPK=" + asignaturaSeriadaPK + " ]";
    }
    
}
