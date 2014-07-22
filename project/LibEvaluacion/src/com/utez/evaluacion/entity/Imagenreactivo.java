/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "imagenreactivo")
@NamedQueries({
    @NamedQuery(name = "Imagenreactivo.findAll", query = "SELECT i FROM Imagenreactivo i")})
public class Imagenreactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claveImagen")
    private Integer claveImagen;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
    @JoinColumn(name = "claveReactivo", referencedColumnName = "claveReactivo")
    @ManyToOne
    private Reactivo claveReactivo;

    public Imagenreactivo() {
    }

    public Imagenreactivo(Integer claveImagen) {
        this.claveImagen = claveImagen;
    }

    public Integer getClaveImagen() {
        return claveImagen;
    }

    public void setClaveImagen(Integer claveImagen) {
        this.claveImagen = claveImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Reactivo getClaveReactivo() {
        return claveReactivo;
    }

    public void setClaveReactivo(Reactivo claveReactivo) {
        this.claveReactivo = claveReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveImagen != null ? claveImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenreactivo)) {
            return false;
        }
        Imagenreactivo other = (Imagenreactivo) object;
        if ((this.claveImagen == null && other.claveImagen != null) || (this.claveImagen != null && !this.claveImagen.equals(other.claveImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Imagenreactivo[ claveImagen=" + claveImagen + " ]";
    }
    
}
