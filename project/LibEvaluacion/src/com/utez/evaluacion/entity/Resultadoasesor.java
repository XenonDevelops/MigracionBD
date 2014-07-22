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
@Table(name = "resultadoasesor")
@NamedQueries({
    @NamedQuery(name = "Resultadoasesor.findAll", query = "SELECT r FROM Resultadoasesor r")})
public class Resultadoasesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveResultadoA")
    private Integer claveResultadoA;
    @Column(name = "numPregunta")
    private Integer numPregunta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "respAsesor1")
    private Double respAsesor1;
    @Column(name = "respAsesor2")
    private Double respAsesor2;
    @Column(name = "respAsesor3")
    private Double respAsesor3;
    @Column(name = "respAsesor4")
    private Double respAsesor4;
    @Column(name = "respAsesor5")
    private Double respAsesor5;
    @JoinColumn(name = "claveResultadoE", referencedColumnName = "claveResultadoE")
    @ManyToOne
    private Resultadoencuesta claveResultadoE;

    public Resultadoasesor() {
    }

    public Resultadoasesor(Integer claveResultadoA) {
        this.claveResultadoA = claveResultadoA;
    }

    public Integer getClaveResultadoA() {
        return claveResultadoA;
    }

    public void setClaveResultadoA(Integer claveResultadoA) {
        this.claveResultadoA = claveResultadoA;
    }

    public Integer getNumPregunta() {
        return numPregunta;
    }

    public void setNumPregunta(Integer numPregunta) {
        this.numPregunta = numPregunta;
    }

    public Double getRespAsesor1() {
        return respAsesor1;
    }

    public void setRespAsesor1(Double respAsesor1) {
        this.respAsesor1 = respAsesor1;
    }

    public Double getRespAsesor2() {
        return respAsesor2;
    }

    public void setRespAsesor2(Double respAsesor2) {
        this.respAsesor2 = respAsesor2;
    }

    public Double getRespAsesor3() {
        return respAsesor3;
    }

    public void setRespAsesor3(Double respAsesor3) {
        this.respAsesor3 = respAsesor3;
    }

    public Double getRespAsesor4() {
        return respAsesor4;
    }

    public void setRespAsesor4(Double respAsesor4) {
        this.respAsesor4 = respAsesor4;
    }

    public Double getRespAsesor5() {
        return respAsesor5;
    }

    public void setRespAsesor5(Double respAsesor5) {
        this.respAsesor5 = respAsesor5;
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
        hash += (claveResultadoA != null ? claveResultadoA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoasesor)) {
            return false;
        }
        Resultadoasesor other = (Resultadoasesor) object;
        if ((this.claveResultadoA == null && other.claveResultadoA != null) || (this.claveResultadoA != null && !this.claveResultadoA.equals(other.claveResultadoA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Resultadoasesor[ claveResultadoA=" + claveResultadoA + " ]";
    }
    
}
