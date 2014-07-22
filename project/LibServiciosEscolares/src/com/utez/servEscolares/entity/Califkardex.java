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
@Table(name = "califkardex")
@NamedQueries({
    @NamedQuery(name = "Califkardex.findAll", query = "SELECT c FROM Califkardex c")})
public class Califkardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Column(name = "ciclo")
    private String ciclo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "califExamen")
    private Integer califExamen;
    @Column(name = "califTrabajo")
    private Integer califTrabajo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clave")
    private Integer clave;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "adeudo")
    private String adeudo;

    public Califkardex() {
    }

    public Califkardex(Integer clave) {
        this.clave = clave;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCalifExamen() {
        return califExamen;
    }

    public void setCalifExamen(Integer califExamen) {
        this.califExamen = califExamen;
    }

    public Integer getCalifTrabajo() {
        return califTrabajo;
    }

    public void setCalifTrabajo(Integer califTrabajo) {
        this.califTrabajo = califTrabajo;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(String adeudo) {
        this.adeudo = adeudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clave != null ? clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Califkardex)) {
            return false;
        }
        Califkardex other = (Califkardex) object;
        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Califkardex[ clave=" + clave + " ]";
    }
    
}
