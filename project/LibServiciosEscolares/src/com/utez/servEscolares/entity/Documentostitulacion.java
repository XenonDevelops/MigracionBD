/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "documentostitulacion")
@NamedQueries({
    @NamedQuery(name = "Documentostitulacion.findAll", query = "SELECT d FROM Documentostitulacion d")})
public class Documentostitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveDocumento")
    private Integer claveDocumento;
    @Basic(optional = false)
    @Column(name = "nombreDocumento")
    private String nombreDocumento;
    @Basic(optional = false)
    @Column(name = "rutaPlantilla")
    private String rutaPlantilla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveDocumento")
    private List<Asignaciondocumentostitulacion> asignaciondocumentostitulacionList;

    public Documentostitulacion() {
    }

    public Documentostitulacion(Integer claveDocumento) {
        this.claveDocumento = claveDocumento;
    }

    public Documentostitulacion(Integer claveDocumento, String nombreDocumento, String rutaPlantilla) {
        this.claveDocumento = claveDocumento;
        this.nombreDocumento = nombreDocumento;
        this.rutaPlantilla = rutaPlantilla;
    }

    public Integer getClaveDocumento() {
        return claveDocumento;
    }

    public void setClaveDocumento(Integer claveDocumento) {
        this.claveDocumento = claveDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getRutaPlantilla() {
        return rutaPlantilla;
    }

    public void setRutaPlantilla(String rutaPlantilla) {
        this.rutaPlantilla = rutaPlantilla;
    }

    public List<Asignaciondocumentostitulacion> getAsignaciondocumentostitulacionList() {
        return asignaciondocumentostitulacionList;
    }

    public void setAsignaciondocumentostitulacionList(List<Asignaciondocumentostitulacion> asignaciondocumentostitulacionList) {
        this.asignaciondocumentostitulacionList = asignaciondocumentostitulacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveDocumento != null ? claveDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documentostitulacion)) {
            return false;
        }
        Documentostitulacion other = (Documentostitulacion) object;
        if ((this.claveDocumento == null && other.claveDocumento != null) || (this.claveDocumento != null && !this.claveDocumento.equals(other.claveDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Documentostitulacion[ claveDocumento=" + claveDocumento + " ]";
    }
    
}
