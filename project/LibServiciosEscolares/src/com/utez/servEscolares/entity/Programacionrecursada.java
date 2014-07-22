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
@Table(name = "programacionrecursada")
@NamedQueries({
    @NamedQuery(name = "Programacionrecursada.findAll", query = "SELECT p FROM Programacionrecursada p")})
public class Programacionrecursada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveGrupoMat")
    private String claveGrupoMat;
    @Column(name = "claveMateriaGrupo")
    private Integer claveMateriaGrupo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProgramacionrecursada")
    private Integer claveProgramacionrecursada;
    @JoinColumn(name = "claveCuaderno", referencedColumnName = "claveCuaderno")
    @ManyToOne(optional = false)
    private Cuadernoprogramacion claveCuaderno;

    public Programacionrecursada() {
    }

    public Programacionrecursada(Integer claveProgramacionrecursada) {
        this.claveProgramacionrecursada = claveProgramacionrecursada;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getClaveGrupoMat() {
        return claveGrupoMat;
    }

    public void setClaveGrupoMat(String claveGrupoMat) {
        this.claveGrupoMat = claveGrupoMat;
    }

    public Integer getClaveMateriaGrupo() {
        return claveMateriaGrupo;
    }

    public void setClaveMateriaGrupo(Integer claveMateriaGrupo) {
        this.claveMateriaGrupo = claveMateriaGrupo;
    }

    public Integer getClaveProgramacionrecursada() {
        return claveProgramacionrecursada;
    }

    public void setClaveProgramacionrecursada(Integer claveProgramacionrecursada) {
        this.claveProgramacionrecursada = claveProgramacionrecursada;
    }

    public Cuadernoprogramacion getClaveCuaderno() {
        return claveCuaderno;
    }

    public void setClaveCuaderno(Cuadernoprogramacion claveCuaderno) {
        this.claveCuaderno = claveCuaderno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveProgramacionrecursada != null ? claveProgramacionrecursada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionrecursada)) {
            return false;
        }
        Programacionrecursada other = (Programacionrecursada) object;
        if ((this.claveProgramacionrecursada == null && other.claveProgramacionrecursada != null) || (this.claveProgramacionrecursada != null && !this.claveProgramacionrecursada.equals(other.claveProgramacionrecursada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Programacionrecursada[ claveProgramacionrecursada=" + claveProgramacionrecursada + " ]";
    }
    
}
