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
@Table(name = "controlmatricula")
@NamedQueries({
    @NamedQuery(name = "Controlmatricula.findAll", query = "SELECT c FROM Controlmatricula c")})
public class Controlmatricula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "controlMatricula")
    private Integer controlMatricula;

    public Controlmatricula() {
    }

    public Controlmatricula(Integer controlMatricula) {
        this.controlMatricula = controlMatricula;
    }

    public Integer getControlMatricula() {
        return controlMatricula;
    }

    public void setControlMatricula(Integer controlMatricula) {
        this.controlMatricula = controlMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlMatricula != null ? controlMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controlmatricula)) {
            return false;
        }
        Controlmatricula other = (Controlmatricula) object;
        if ((this.controlMatricula == null && other.controlMatricula != null) || (this.controlMatricula != null && !this.controlMatricula.equals(other.controlMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Controlmatricula[ controlMatricula=" + controlMatricula + " ]";
    }
    
}
