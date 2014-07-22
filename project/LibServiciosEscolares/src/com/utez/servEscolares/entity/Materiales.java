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
@Table(name = "materiales")
@NamedQueries({
    @NamedQuery(name = "Materiales.findAll", query = "SELECT m FROM Materiales m")})
public class Materiales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveMaterial")
    private Integer claveMaterial;
    @Column(name = "nomMaterial")
    private String nomMaterial;
    @Column(name = "tipoMaterial")
    private String tipoMaterial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costoMaterial")
    private Double costoMaterial;
    @Column(name = "autorMaterial")
    private String autorMaterial;
    @Column(name = "axoEdicion")
    private String axoEdicion;
    @Column(name = "numHojas")
    private Integer numHojas;
    @Column(name = "existencia")
    private Integer existencia;

    public Materiales() {
    }

    public Materiales(Integer claveMaterial) {
        this.claveMaterial = claveMaterial;
    }

    public Integer getClaveMaterial() {
        return claveMaterial;
    }

    public void setClaveMaterial(Integer claveMaterial) {
        this.claveMaterial = claveMaterial;
    }

    public String getNomMaterial() {
        return nomMaterial;
    }

    public void setNomMaterial(String nomMaterial) {
        this.nomMaterial = nomMaterial;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Double getCostoMaterial() {
        return costoMaterial;
    }

    public void setCostoMaterial(Double costoMaterial) {
        this.costoMaterial = costoMaterial;
    }

    public String getAutorMaterial() {
        return autorMaterial;
    }

    public void setAutorMaterial(String autorMaterial) {
        this.autorMaterial = autorMaterial;
    }

    public String getAxoEdicion() {
        return axoEdicion;
    }

    public void setAxoEdicion(String axoEdicion) {
        this.axoEdicion = axoEdicion;
    }

    public Integer getNumHojas() {
        return numHojas;
    }

    public void setNumHojas(Integer numHojas) {
        this.numHojas = numHojas;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveMaterial != null ? claveMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiales)) {
            return false;
        }
        Materiales other = (Materiales) object;
        if ((this.claveMaterial == null && other.claveMaterial != null) || (this.claveMaterial != null && !this.claveMaterial.equals(other.claveMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Materiales[ claveMaterial=" + claveMaterial + " ]";
    }
    
}
