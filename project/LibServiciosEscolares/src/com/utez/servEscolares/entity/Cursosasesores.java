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
@Table(name = "cursosasesores")
@NamedQueries({
    @NamedQuery(name = "Cursosasesores.findAll", query = "SELECT c FROM Cursosasesores c")})
public class Cursosasesores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCurso")
    private Integer claveCurso;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "curso")
    private String curso;

    public Cursosasesores() {
    }

    public Cursosasesores(Integer claveCurso) {
        this.claveCurso = claveCurso;
    }

    public Integer getClaveCurso() {
        return claveCurso;
    }

    public void setClaveCurso(Integer claveCurso) {
        this.claveCurso = claveCurso;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCurso != null ? claveCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursosasesores)) {
            return false;
        }
        Cursosasesores other = (Cursosasesores) object;
        if ((this.claveCurso == null && other.claveCurso != null) || (this.claveCurso != null && !this.claveCurso.equals(other.claveCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Cursosasesores[ claveCurso=" + claveCurso + " ]";
    }
    
}
