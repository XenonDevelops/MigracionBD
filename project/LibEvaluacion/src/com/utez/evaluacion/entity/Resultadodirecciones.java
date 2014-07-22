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
@Table(name = "resultadodirecciones")
@NamedQueries({
    @NamedQuery(name = "Resultadodirecciones.findAll", query = "SELECT r FROM Resultadodirecciones r")})
public class Resultadodirecciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveResultadoD")
    private Integer claveResultadoD;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "califDirServEsc")
    private Double califDirServEsc;
    @Column(name = "califDirSede")
    private Double califDirSede;
    @Column(name = "califDirEval")
    private Double califDirEval;
    @Column(name = "califCoordOpcL")
    private Double califCoordOpcL;
    @JoinColumn(name = "claveResultadoE", referencedColumnName = "claveResultadoE")
    @ManyToOne
    private Resultadoencuesta claveResultadoE;

    public Resultadodirecciones() {
    }

    public Resultadodirecciones(Integer claveResultadoD) {
        this.claveResultadoD = claveResultadoD;
    }

    public Integer getClaveResultadoD() {
        return claveResultadoD;
    }

    public void setClaveResultadoD(Integer claveResultadoD) {
        this.claveResultadoD = claveResultadoD;
    }

    public Double getCalifDirServEsc() {
        return califDirServEsc;
    }

    public void setCalifDirServEsc(Double califDirServEsc) {
        this.califDirServEsc = califDirServEsc;
    }

    public Double getCalifDirSede() {
        return califDirSede;
    }

    public void setCalifDirSede(Double califDirSede) {
        this.califDirSede = califDirSede;
    }

    public Double getCalifDirEval() {
        return califDirEval;
    }

    public void setCalifDirEval(Double califDirEval) {
        this.califDirEval = califDirEval;
    }

    public Double getCalifCoordOpcL() {
        return califCoordOpcL;
    }

    public void setCalifCoordOpcL(Double califCoordOpcL) {
        this.califCoordOpcL = califCoordOpcL;
    }

    public Resultadoencuesta getClaveResultadoE() {
        return claveResultadoE;
    }

    public void setClaveResultadoE(Resultadoencuesta claveResultadoE) {
        this.claveResultadoE = claveResultadoE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveResultadoD != null ? claveResultadoD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadodirecciones)) {
            return false;
        }
        Resultadodirecciones other = (Resultadodirecciones) object;
        if ((this.claveResultadoD == null && other.claveResultadoD != null) || (this.claveResultadoD != null && !this.claveResultadoD.equals(other.claveResultadoD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Resultadodirecciones[ claveResultadoD=" + claveResultadoD + " ]";
    }
    
}
