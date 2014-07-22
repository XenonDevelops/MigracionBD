/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "programacionopcionc")
@NamedQueries({
    @NamedQuery(name = "Programacionopcionc.findAll", query = "SELECT p FROM Programacionopcionc p")})
public class Programacionopcionc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Basic(optional = false)
    @Column(name = "claveMateria")
    private int claveMateria;
    @Column(name = "fechaExamOrd")
    @Temporal(TemporalType.DATE)
    private Date fechaExamOrd;
    @Column(name = "fechaExamExt")
    @Temporal(TemporalType.DATE)
    private Date fechaExamExt;
    @Column(name = "fechaExamTit")
    @Temporal(TemporalType.DATE)
    private Date fechaExamTit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProgramacionopcionc")
    private Integer claveProgramacionopcionc;
    @JoinColumn(name = "claveCuaderno", referencedColumnName = "claveCuaderno")
    @ManyToOne(optional = false)
    private Cuadernoprogramacion claveCuaderno;

    public Programacionopcionc() {
    }

    public Programacionopcionc(Integer claveProgramacionopcionc) {
        this.claveProgramacionopcionc = claveProgramacionopcionc;
    }

    public Programacionopcionc(Integer claveProgramacionopcionc, int claveMateria) {
        this.claveProgramacionopcionc = claveProgramacionopcionc;
        this.claveMateria = claveMateria;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public int getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(int claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Date getFechaExamOrd() {
        return fechaExamOrd;
    }

    public void setFechaExamOrd(Date fechaExamOrd) {
        this.fechaExamOrd = fechaExamOrd;
    }

    public Date getFechaExamExt() {
        return fechaExamExt;
    }

    public void setFechaExamExt(Date fechaExamExt) {
        this.fechaExamExt = fechaExamExt;
    }

    public Date getFechaExamTit() {
        return fechaExamTit;
    }

    public void setFechaExamTit(Date fechaExamTit) {
        this.fechaExamTit = fechaExamTit;
    }

    public Integer getClaveProgramacionopcionc() {
        return claveProgramacionopcionc;
    }

    public void setClaveProgramacionopcionc(Integer claveProgramacionopcionc) {
        this.claveProgramacionopcionc = claveProgramacionopcionc;
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
        hash += (claveProgramacionopcionc != null ? claveProgramacionopcionc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionopcionc)) {
            return false;
        }
        Programacionopcionc other = (Programacionopcionc) object;
        if ((this.claveProgramacionopcionc == null && other.claveProgramacionopcionc != null) || (this.claveProgramacionopcionc != null && !this.claveProgramacionopcionc.equals(other.claveProgramacionopcionc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Programacionopcionc[ claveProgramacionopcionc=" + claveProgramacionopcionc + " ]";
    }
    
}
