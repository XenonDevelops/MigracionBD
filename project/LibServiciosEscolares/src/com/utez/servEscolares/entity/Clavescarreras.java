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
@Table(name = "clavescarreras")
@NamedQueries({
    @NamedQuery(name = "Clavescarreras.findAll", query = "SELECT c FROM Clavescarreras c")})
public class Clavescarreras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "valor")
    private Integer valor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveClavescarreras")
    private Integer claveClavescarreras;

    public Clavescarreras() {
    }

    public Clavescarreras(Integer claveClavescarreras) {
        this.claveClavescarreras = claveClavescarreras;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getClaveClavescarreras() {
        return claveClavescarreras;
    }

    public void setClaveClavescarreras(Integer claveClavescarreras) {
        this.claveClavescarreras = claveClavescarreras;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveClavescarreras != null ? claveClavescarreras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clavescarreras)) {
            return false;
        }
        Clavescarreras other = (Clavescarreras) object;
        if ((this.claveClavescarreras == null && other.claveClavescarreras != null) || (this.claveClavescarreras != null && !this.claveClavescarreras.equals(other.claveClavescarreras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Clavescarreras[ claveClavescarreras=" + claveClavescarreras + " ]";
    }
    
}
