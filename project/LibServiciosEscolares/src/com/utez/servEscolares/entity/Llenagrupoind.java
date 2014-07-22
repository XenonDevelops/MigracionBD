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
@Table(name = "llenagrupoind")
@NamedQueries({
    @NamedQuery(name = "Llenagrupoind.findAll", query = "SELECT l FROM Llenagrupoind l")})
public class Llenagrupoind implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "claveGrupoInd")
    private int claveGrupoInd;
    @Column(name = "matricula")
    private String matricula;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveLlenagrupoind")
    private Integer claveLlenagrupoind;

    public Llenagrupoind() {
    }

    public Llenagrupoind(Integer claveLlenagrupoind) {
        this.claveLlenagrupoind = claveLlenagrupoind;
    }

    public Llenagrupoind(Integer claveLlenagrupoind, int claveGrupoInd) {
        this.claveLlenagrupoind = claveLlenagrupoind;
        this.claveGrupoInd = claveGrupoInd;
    }

    public int getClaveGrupoInd() {
        return claveGrupoInd;
    }

    public void setClaveGrupoInd(int claveGrupoInd) {
        this.claveGrupoInd = claveGrupoInd;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getClaveLlenagrupoind() {
        return claveLlenagrupoind;
    }

    public void setClaveLlenagrupoind(Integer claveLlenagrupoind) {
        this.claveLlenagrupoind = claveLlenagrupoind;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveLlenagrupoind != null ? claveLlenagrupoind.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Llenagrupoind)) {
            return false;
        }
        Llenagrupoind other = (Llenagrupoind) object;
        if ((this.claveLlenagrupoind == null && other.claveLlenagrupoind != null) || (this.claveLlenagrupoind != null && !this.claveLlenagrupoind.equals(other.claveLlenagrupoind))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Llenagrupoind[ claveLlenagrupoind=" + claveLlenagrupoind + " ]";
    }
    
}
