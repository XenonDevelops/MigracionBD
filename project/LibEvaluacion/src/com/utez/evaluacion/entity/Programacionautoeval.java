/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "programacionautoeval")
@NamedQueries({
    @NamedQuery(name = "Programacionautoeval.findAll", query = "SELECT p FROM Programacionautoeval p")})
public class Programacionautoeval implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveProgAuto")
    private Integer claveProgAuto;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveBanco")
    private Integer claveBanco;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "numReactivos")
    private Integer numReactivos;
    @OneToMany(mappedBy = "claveProgAuto")
    private List<Resultadoautoeval> resultadoautoevalList;

    public Programacionautoeval() {
    }

    public Programacionautoeval(Integer claveProgAuto) {
        this.claveProgAuto = claveProgAuto;
    }

    public Programacionautoeval(Integer claveProgAuto, Date fechaInicio, Date fechaFin) {
        this.claveProgAuto = claveProgAuto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getClaveProgAuto() {
        return claveProgAuto;
    }

    public void setClaveProgAuto(Integer claveProgAuto) {
        this.claveProgAuto = claveProgAuto;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Integer getClaveBanco() {
        return claveBanco;
    }

    public void setClaveBanco(Integer claveBanco) {
        this.claveBanco = claveBanco;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getNumReactivos() {
        return numReactivos;
    }

    public void setNumReactivos(Integer numReactivos) {
        this.numReactivos = numReactivos;
    }

    public List<Resultadoautoeval> getResultadoautoevalList() {
        return resultadoautoevalList;
    }

    public void setResultadoautoevalList(List<Resultadoautoeval> resultadoautoevalList) {
        this.resultadoautoevalList = resultadoautoevalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveProgAuto != null ? claveProgAuto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programacionautoeval)) {
            return false;
        }
        Programacionautoeval other = (Programacionautoeval) object;
        if ((this.claveProgAuto == null && other.claveProgAuto != null) || (this.claveProgAuto != null && !this.claveProgAuto.equals(other.claveProgAuto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Programacionautoeval[ claveProgAuto=" + claveProgAuto + " ]";
    }
    
}
