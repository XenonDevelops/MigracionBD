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
@Table(name = "registrosep")
@NamedQueries({
    @NamedQuery(name = "Registrosep.findAll", query = "SELECT r FROM Registrosep r")})
public class Registrosep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numRegistro")
    private String numRegistro;
    @Column(name = "numEvento")
    private Integer numEvento;

    public Registrosep() {
    }

    public Registrosep(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRegistro != null ? numRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrosep)) {
            return false;
        }
        Registrosep other = (Registrosep) object;
        if ((this.numRegistro == null && other.numRegistro != null) || (this.numRegistro != null && !this.numRegistro.equals(other.numRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registrosep[ numRegistro=" + numRegistro + " ]";
    }
    
}
