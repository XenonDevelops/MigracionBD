/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "resultadoencuesta")
@NamedQueries({
    @NamedQuery(name = "Resultadoencuesta.findAll", query = "SELECT r FROM Resultadoencuesta r")})
public class Resultadoencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveResultadoE")
    private Integer claveResultadoE;
    @Column(name = "clavePlantel")
    private Integer clavePlantel;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "numAlumnos")
    private Integer numAlumnos;
    @Column(name = "comentarios")
    private String comentarios;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "califAsesor")
    private Double califAsesor;
    @Column(name = "califMaterial")
    private Double califMaterial;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "comentariosDirectores")
    private String comentariosDirectores;
    @OneToMany(mappedBy = "claveResultadoE")
    private List<Resultadoasesor> resultadoasesorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveResultadoE")
    private List<Resultadomaterial> resultadomaterialList;
    @OneToMany(mappedBy = "claveResultadoE")
    private List<Resultadodirecciones> resultadodireccionesList;

    public Resultadoencuesta() {
    }

    public Resultadoencuesta(Integer claveResultadoE) {
        this.claveResultadoE = claveResultadoE;
    }

    public Integer getClaveResultadoE() {
        return claveResultadoE;
    }

    public void setClaveResultadoE(Integer claveResultadoE) {
        this.claveResultadoE = claveResultadoE;
    }

    public Integer getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public Integer getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(Integer numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Double getCalifAsesor() {
        return califAsesor;
    }

    public void setCalifAsesor(Double califAsesor) {
        this.califAsesor = califAsesor;
    }

    public Double getCalifMaterial() {
        return califMaterial;
    }

    public void setCalifMaterial(Double califMaterial) {
        this.califMaterial = califMaterial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getComentariosDirectores() {
        return comentariosDirectores;
    }

    public void setComentariosDirectores(String comentariosDirectores) {
        this.comentariosDirectores = comentariosDirectores;
    }

    public List<Resultadoasesor> getResultadoasesorList() {
        return resultadoasesorList;
    }

    public void setResultadoasesorList(List<Resultadoasesor> resultadoasesorList) {
        this.resultadoasesorList = resultadoasesorList;
    }

    public List<Resultadomaterial> getResultadomaterialList() {
        return resultadomaterialList;
    }

    public void setResultadomaterialList(List<Resultadomaterial> resultadomaterialList) {
        this.resultadomaterialList = resultadomaterialList;
    }

    public List<Resultadodirecciones> getResultadodireccionesList() {
        return resultadodireccionesList;
    }

    public void setResultadodireccionesList(List<Resultadodirecciones> resultadodireccionesList) {
        this.resultadodireccionesList = resultadodireccionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveResultadoE != null ? claveResultadoE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoencuesta)) {
            return false;
        }
        Resultadoencuesta other = (Resultadoencuesta) object;
        if ((this.claveResultadoE == null && other.claveResultadoE != null) || (this.claveResultadoE != null && !this.claveResultadoE.equals(other.claveResultadoE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Resultadoencuesta[ claveResultadoE=" + claveResultadoE + " ]";
    }
    
}
