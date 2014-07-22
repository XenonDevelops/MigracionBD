/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "nominaconcepto")
@NamedQueries({
    @NamedQuery(name = "Nominaconcepto.findAll", query = "SELECT n FROM Nominaconcepto n")})
public class Nominaconcepto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveConcepto")
    private Integer claveConcepto;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "folio", referencedColumnName = "folio")
    @ManyToOne(optional = false)
    private Nominaaplicador folio;

    public Nominaconcepto() {
    }

    public Nominaconcepto(Integer claveConcepto) {
        this.claveConcepto = claveConcepto;
    }

    public Integer getClaveConcepto() {
        return claveConcepto;
    }

    public void setClaveConcepto(Integer claveConcepto) {
        this.claveConcepto = claveConcepto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Nominaaplicador getFolio() {
        return folio;
    }

    public void setFolio(Nominaaplicador folio) {
        this.folio = folio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveConcepto != null ? claveConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nominaconcepto)) {
            return false;
        }
        Nominaconcepto other = (Nominaconcepto) object;
        if ((this.claveConcepto == null && other.claveConcepto != null) || (this.claveConcepto != null && !this.claveConcepto.equals(other.claveConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Nominaconcepto[ claveConcepto=" + claveConcepto + " ]";
    }
    
}
