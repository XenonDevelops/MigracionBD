/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "examenextemporaneo")
@NamedQueries({
    @NamedQuery(name = "Examenextemporaneo.findAll", query = "SELECT e FROM Examenextemporaneo e")})
public class Examenextemporaneo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveExaExtem")
    private Integer claveExaExtem;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Column(name = "fechaProgramada")
    @Temporal(TemporalType.DATE)
    private Date fechaProgramada;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "matricula")
    private String matricula;

    public Examenextemporaneo() {
    }

    public Examenextemporaneo(Integer claveExaExtem) {
        this.claveExaExtem = claveExaExtem;
    }

    public Integer getClaveExaExtem() {
        return claveExaExtem;
    }

    public void setClaveExaExtem(Integer claveExaExtem) {
        this.claveExaExtem = claveExaExtem;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
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

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveExaExtem != null ? claveExaExtem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examenextemporaneo)) {
            return false;
        }
        Examenextemporaneo other = (Examenextemporaneo) object;
        if ((this.claveExaExtem == null && other.claveExaExtem != null) || (this.claveExaExtem != null && !this.claveExaExtem.equals(other.claveExaExtem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Examenextemporaneo[ claveExaExtem=" + claveExaExtem + " ]";
    }
    
}
