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
@Table(name = "correspondencia")
@NamedQueries({
    @NamedQuery(name = "Correspondencia.findAll", query = "SELECT c FROM Correspondencia c")})
public class Correspondencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCorrespondencia")
    private Integer claveCorrespondencia;
    @Column(name = "correspondencia")
    private Integer correspondencia;
    @JoinColumn(name = "claveMateria", referencedColumnName = "claveMateria")
    @ManyToOne
    private Materiasdeplan claveMateria;

    public Correspondencia() {
    }

    public Correspondencia(Integer claveCorrespondencia) {
        this.claveCorrespondencia = claveCorrespondencia;
    }

    public Integer getClaveCorrespondencia() {
        return claveCorrespondencia;
    }

    public void setClaveCorrespondencia(Integer claveCorrespondencia) {
        this.claveCorrespondencia = claveCorrespondencia;
    }

    public Integer getCorrespondencia() {
        return correspondencia;
    }

    public void setCorrespondencia(Integer correspondencia) {
        this.correspondencia = correspondencia;
    }

    public Materiasdeplan getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Materiasdeplan claveMateria) {
        this.claveMateria = claveMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCorrespondencia != null ? claveCorrespondencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correspondencia)) {
            return false;
        }
        Correspondencia other = (Correspondencia) object;
        if ((this.claveCorrespondencia == null && other.claveCorrespondencia != null) || (this.claveCorrespondencia != null && !this.claveCorrespondencia.equals(other.claveCorrespondencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Correspondencia[ claveCorrespondencia=" + claveCorrespondencia + " ]";
    }
    
}
