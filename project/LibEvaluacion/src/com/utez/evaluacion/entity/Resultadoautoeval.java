/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "resultadoautoeval")
@NamedQueries({
    @NamedQuery(name = "Resultadoautoeval.findAll", query = "SELECT r FROM Resultadoautoeval r")})
public class Resultadoautoeval implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveResultadoAuto")
    private Integer claveResultadoAuto;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "estado")
    private Integer estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion")
    private Double calificacion;
    @OneToMany(mappedBy = "claveResultadoAuto")
    private List<Respuestasresultado> respuestasresultadoList;
    @JoinColumn(name = "claveProgAuto", referencedColumnName = "claveProgAuto")
    @ManyToOne
    private Programacionautoeval claveProgAuto;

    public Resultadoautoeval() {
    }

    public Resultadoautoeval(Integer claveResultadoAuto) {
        this.claveResultadoAuto = claveResultadoAuto;
    }

    public Integer getClaveResultadoAuto() {
        return claveResultadoAuto;
    }

    public void setClaveResultadoAuto(Integer claveResultadoAuto) {
        this.claveResultadoAuto = claveResultadoAuto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public List<Respuestasresultado> getRespuestasresultadoList() {
        return respuestasresultadoList;
    }

    public void setRespuestasresultadoList(List<Respuestasresultado> respuestasresultadoList) {
        this.respuestasresultadoList = respuestasresultadoList;
    }

    public Programacionautoeval getClaveProgAuto() {
        return claveProgAuto;
    }

    public void setClaveProgAuto(Programacionautoeval claveProgAuto) {
        this.claveProgAuto = claveProgAuto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveResultadoAuto != null ? claveResultadoAuto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoautoeval)) {
            return false;
        }
        Resultadoautoeval other = (Resultadoautoeval) object;
        if ((this.claveResultadoAuto == null && other.claveResultadoAuto != null) || (this.claveResultadoAuto != null && !this.claveResultadoAuto.equals(other.claveResultadoAuto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Resultadoautoeval[ claveResultadoAuto=" + claveResultadoAuto + " ]";
    }
    
}
