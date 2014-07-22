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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "imagenes")
@NamedQueries({
    @NamedQuery(name = "Imagenes.findAll", query = "SELECT i FROM Imagenes i")})
public class Imagenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idImagen")
    private Integer idImagen;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @Basic(optional = false)
    @Column(name = "nombreImagen")
    private String nombreImagen;

    public Imagenes() {
    }

    public Imagenes(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Imagenes(Integer idImagen, String nombreImagen) {
        this.idImagen = idImagen;
        this.nombreImagen = nombreImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenes)) {
            return false;
        }
        Imagenes other = (Imagenes) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Imagenes[ idImagen=" + idImagen + " ]";
    }
    
}
