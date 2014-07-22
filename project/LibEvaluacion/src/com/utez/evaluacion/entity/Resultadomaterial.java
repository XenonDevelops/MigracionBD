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
@Table(name = "resultadomaterial")
@NamedQueries({
    @NamedQuery(name = "Resultadomaterial.findAll", query = "SELECT r FROM Resultadomaterial r")})
public class Resultadomaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveResultadoM")
    private Integer claveResultadoM;
    @Column(name = "numPregunta")
    private Integer numPregunta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "respMaterial1")
    private Double respMaterial1;
    @Column(name = "respMaterial2")
    private Double respMaterial2;
    @Column(name = "respMaterial3")
    private Double respMaterial3;
    @Column(name = "respMaterial4")
    private Double respMaterial4;
    @Column(name = "respMaterial5")
    private Double respMaterial5;
    @JoinColumn(name = "claveResultadoE", referencedColumnName = "claveResultadoE")
    @ManyToOne(optional = false)
    private Resultadoencuesta claveResultadoE;

    public Resultadomaterial() {
    }

    public Resultadomaterial(Integer claveResultadoM) {
        this.claveResultadoM = claveResultadoM;
    }

    public Integer getClaveResultadoM() {
        return claveResultadoM;
    }

    public void setClaveResultadoM(Integer claveResultadoM) {
        this.claveResultadoM = claveResultadoM;
    }

    public Integer getNumPregunta() {
        return numPregunta;
    }

    public void setNumPregunta(Integer numPregunta) {
        this.numPregunta = numPregunta;
    }

    public Double getRespMaterial1() {
        return respMaterial1;
    }

    public void setRespMaterial1(Double respMaterial1) {
        this.respMaterial1 = respMaterial1;
    }

    public Double getRespMaterial2() {
        return respMaterial2;
    }

    public void setRespMaterial2(Double respMaterial2) {
        this.respMaterial2 = respMaterial2;
    }

    public Double getRespMaterial3() {
        return respMaterial3;
    }

    public void setRespMaterial3(Double respMaterial3) {
        this.respMaterial3 = respMaterial3;
    }

    public Double getRespMaterial4() {
        return respMaterial4;
    }

    public void setRespMaterial4(Double respMaterial4) {
        this.respMaterial4 = respMaterial4;
    }

    public Double getRespMaterial5() {
        return respMaterial5;
    }

    public void setRespMaterial5(Double respMaterial5) {
        this.respMaterial5 = respMaterial5;
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
        hash += (claveResultadoM != null ? claveResultadoM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadomaterial)) {
            return false;
        }
        Resultadomaterial other = (Resultadomaterial) object;
        if ((this.claveResultadoM == null && other.claveResultadoM != null) || (this.claveResultadoM != null && !this.claveResultadoM.equals(other.claveResultadoM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Resultadomaterial[ claveResultadoM=" + claveResultadoM + " ]";
    }
    
}
