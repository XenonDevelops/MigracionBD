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
@Table(name = "aplicacionexamen")
@NamedQueries({
    @NamedQuery(name = "Aplicacionexamen.findAll", query = "SELECT a FROM Aplicacionexamen a")})
public class Aplicacionexamen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAplicacion")
    private Integer claveAplicacion;
    @Column(name = "fechaAplicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAplicacion;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "noAlumnos")
    private Integer noAlumnos;
    @Column(name = "programado")
    private Integer programado;
    @Column(name = "revoe")
    private String revoe;
    @OneToMany(mappedBy = "claveAplicacion")
    private List<Asignaaplicador> asignaaplicadorList;
    @OneToMany(mappedBy = "claveAplicacion")
    private List<Entregaexamen> entregaexamenList;

    public Aplicacionexamen() {
    }

    public Aplicacionexamen(Integer claveAplicacion) {
        this.claveAplicacion = claveAplicacion;
    }

    public Integer getClaveAplicacion() {
        return claveAplicacion;
    }

    public void setClaveAplicacion(Integer claveAplicacion) {
        this.claveAplicacion = claveAplicacion;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
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

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public Integer getNoAlumnos() {
        return noAlumnos;
    }

    public void setNoAlumnos(Integer noAlumnos) {
        this.noAlumnos = noAlumnos;
    }

    public Integer getProgramado() {
        return programado;
    }

    public void setProgramado(Integer programado) {
        this.programado = programado;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public List<Asignaaplicador> getAsignaaplicadorList() {
        return asignaaplicadorList;
    }

    public void setAsignaaplicadorList(List<Asignaaplicador> asignaaplicadorList) {
        this.asignaaplicadorList = asignaaplicadorList;
    }

    public List<Entregaexamen> getEntregaexamenList() {
        return entregaexamenList;
    }

    public void setEntregaexamenList(List<Entregaexamen> entregaexamenList) {
        this.entregaexamenList = entregaexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAplicacion != null ? claveAplicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aplicacionexamen)) {
            return false;
        }
        Aplicacionexamen other = (Aplicacionexamen) object;
        if ((this.claveAplicacion == null && other.claveAplicacion != null) || (this.claveAplicacion != null && !this.claveAplicacion.equals(other.claveAplicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Aplicacionexamen[ claveAplicacion=" + claveAplicacion + " ]";
    }
    
}
