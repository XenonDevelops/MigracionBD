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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "asignacionactividad")
@NamedQueries({
    @NamedQuery(name = "Asignacionactividad.findAll", query = "SELECT a FROM Asignacionactividad a")})
public class Asignacionactividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsignacion")
    private Integer claveAsignacion;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fechaAsignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @OneToMany(mappedBy = "claveAsignacion")
    private List<Asignacionalumno> asignacionalumnoList;
    @JoinColumn(name = "clavePlantel", referencedColumnName = "clavePlantel")
    @ManyToOne
    private Plantel clavePlantel;
    @JoinColumn(name = "claveActividadIntegra", referencedColumnName = "claveActividadIntegra")
    @ManyToOne
    private Actividadintegradora claveActividadIntegra;

    public Asignacionactividad() {
    }

    public Asignacionactividad(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public Integer getClaveAsignacion() {
        return claveAsignacion;
    }

    public void setClaveAsignacion(Integer claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public List<Asignacionalumno> getAsignacionalumnoList() {
        return asignacionalumnoList;
    }

    public void setAsignacionalumnoList(List<Asignacionalumno> asignacionalumnoList) {
        this.asignacionalumnoList = asignacionalumnoList;
    }

    public Plantel getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Plantel clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public Actividadintegradora getClaveActividadIntegra() {
        return claveActividadIntegra;
    }

    public void setClaveActividadIntegra(Actividadintegradora claveActividadIntegra) {
        this.claveActividadIntegra = claveActividadIntegra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsignacion != null ? claveAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacionactividad)) {
            return false;
        }
        Asignacionactividad other = (Asignacionactividad) object;
        if ((this.claveAsignacion == null && other.claveAsignacion != null) || (this.claveAsignacion != null && !this.claveAsignacion.equals(other.claveAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asignacionactividad[ claveAsignacion=" + claveAsignacion + " ]";
    }
    
}
