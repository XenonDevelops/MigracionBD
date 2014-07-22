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
@Table(name = "programaseasesores")
@NamedQueries({
    @NamedQuery(name = "Programaseasesores.findAll", query = "SELECT p FROM Programaseasesores p")})
public class Programaseasesores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "revoe")
    private String revoe;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProgramaseasesores")
    private Integer claveProgramaseasesores;

    public Programaseasesores() {
    }

    public Programaseasesores(Integer claveProgramaseasesores) {
        this.claveProgramaseasesores = claveProgramaseasesores;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Integer getClaveProgramaseasesores() {
        return claveProgramaseasesores;
    }

    public void setClaveProgramaseasesores(Integer claveProgramaseasesores) {
        this.claveProgramaseasesores = claveProgramaseasesores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveProgramaseasesores != null ? claveProgramaseasesores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaseasesores)) {
            return false;
        }
        Programaseasesores other = (Programaseasesores) object;
        if ((this.claveProgramaseasesores == null && other.claveProgramaseasesores != null) || (this.claveProgramaseasesores != null && !this.claveProgramaseasesores.equals(other.claveProgramaseasesores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Programaseasesores[ claveProgramaseasesores=" + claveProgramaseasesores + " ]";
    }
    
}
