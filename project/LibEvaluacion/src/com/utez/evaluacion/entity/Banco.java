/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "banco")
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")})
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveBanco")
    private Integer claveBanco;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "autor")
    private String autor;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "edoBaja")
    private Integer edoBaja;
    @OneToMany(mappedBy = "claveBanco")
    private List<Reactivo> reactivoList;

    public Banco() {
    }

    public Banco(Integer claveBanco) {
        this.claveBanco = claveBanco;
    }

    public Integer getClaveBanco() {
        return claveBanco;
    }

    public void setClaveBanco(Integer claveBanco) {
        this.claveBanco = claveBanco;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEdoBaja() {
        return edoBaja;
    }

    public void setEdoBaja(Integer edoBaja) {
        this.edoBaja = edoBaja;
    }

    public List<Reactivo> getReactivoList() {
        return reactivoList;
    }

    public void setReactivoList(List<Reactivo> reactivoList) {
        this.reactivoList = reactivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveBanco != null ? claveBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.claveBanco == null && other.claveBanco != null) || (this.claveBanco != null && !this.claveBanco.equals(other.claveBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Banco[ claveBanco=" + claveBanco + " ]";
    }
    
}
