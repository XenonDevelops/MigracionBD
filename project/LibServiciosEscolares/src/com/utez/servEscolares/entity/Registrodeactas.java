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
@Table(name = "registrodeactas")
@NamedQueries({
    @NamedQuery(name = "Registrodeactas.findAll", query = "SELECT r FROM Registrodeactas r")})
public class Registrodeactas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Column(name = "tipoActa")
    private String tipoActa;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "folio")
    private String folio;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "matricula")
    private String matricula;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRegistrodeactas")
    private Integer claveRegistrodeactas;

    public Registrodeactas() {
    }

    public Registrodeactas(Integer claveRegistrodeactas) {
        this.claveRegistrodeactas = claveRegistrodeactas;
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

    public String getTipoActa() {
        return tipoActa;
    }

    public void setTipoActa(String tipoActa) {
        this.tipoActa = tipoActa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getClaveRegistrodeactas() {
        return claveRegistrodeactas;
    }

    public void setClaveRegistrodeactas(Integer claveRegistrodeactas) {
        this.claveRegistrodeactas = claveRegistrodeactas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveRegistrodeactas != null ? claveRegistrodeactas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registrodeactas)) {
            return false;
        }
        Registrodeactas other = (Registrodeactas) object;
        if ((this.claveRegistrodeactas == null && other.claveRegistrodeactas != null) || (this.claveRegistrodeactas != null && !this.claveRegistrodeactas.equals(other.claveRegistrodeactas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registrodeactas[ claveRegistrodeactas=" + claveRegistrodeactas + " ]";
    }
    
}
