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
@Table(name = "asignaciondocumentostitulacion")
@NamedQueries({
    @NamedQuery(name = "Asignaciondocumentostitulacion.findAll", query = "SELECT a FROM Asignaciondocumentostitulacion a")})
public class Asignaciondocumentostitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsignacion")
    private Integer claveAsignacion;
    @Basic(optional = false)
    @Column(name = "revoe")
    private String revoe;
    @Basic(optional = false)
    @Column(name = "opcionTitulacion")
    private String opcionTitulacion;
    @JoinColumn(name = "claveDocumento", referencedColumnName = "claveDocumento")
    @ManyToOne(optional = false)
    private Documentostitulacion claveDocumento;

    public Asignaciondocumentostitulacion() {
    }

    public Asignaciondocumentostitulacion(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public Asignaciondocumentostitulacion(Integer claveAsignacion, String revoe, String opcionTitulacion) {
        this.claveAsignacion = claveAsignacion;
        this.revoe = revoe;
        this.opcionTitulacion = opcionTitulacion;
    }

    public Integer getClaveAsignacion() {
        return claveAsignacion;
    }

    public void setClaveAsignacion(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public String getOpcionTitulacion() {
        return opcionTitulacion;
    }

    public void setOpcionTitulacion(String opcionTitulacion) {
        this.opcionTitulacion = opcionTitulacion;
    }

    public Documentostitulacion getClaveDocumento() {
        return claveDocumento;
    }

    public void setClaveDocumento(Documentostitulacion claveDocumento) {
        this.claveDocumento = claveDocumento;
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
        if (!(object instanceof Asignaciondocumentostitulacion)) {
            return false;
        }
        Asignaciondocumentostitulacion other = (Asignaciondocumentostitulacion) object;
        if ((this.claveAsignacion == null && other.claveAsignacion != null) || (this.claveAsignacion != null && !this.claveAsignacion.equals(other.claveAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Asignaciondocumentostitulacion[ claveAsignacion=" + claveAsignacion + " ]";
    }
    
}
