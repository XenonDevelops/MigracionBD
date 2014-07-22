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
@Table(name = "opcionestitulacion")
@NamedQueries({
    @NamedQuery(name = "Opcionestitulacion.findAll", query = "SELECT o FROM Opcionestitulacion o")})
public class Opcionestitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claveOpcion")
    private String claveOpcion;
    @Column(name = "descripcion")
    private String descripcion;

    public Opcionestitulacion() {
    }

    public Opcionestitulacion(String claveOpcion) {
        this.claveOpcion = claveOpcion;
    }

    public String getClaveOpcion() {
        return claveOpcion;
    }

    public void setClaveOpcion(String claveOpcion) {
        this.claveOpcion = claveOpcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveOpcion != null ? claveOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcionestitulacion)) {
            return false;
        }
        Opcionestitulacion other = (Opcionestitulacion) object;
        if ((this.claveOpcion == null && other.claveOpcion != null) || (this.claveOpcion != null && !this.claveOpcion.equals(other.claveOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Opcionestitulacion[ claveOpcion=" + claveOpcion + " ]";
    }
    
}
