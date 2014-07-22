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
@Table(name = "actividadintegradora")
@NamedQueries({
    @NamedQuery(name = "Actividadintegradora.findAll", query = "SELECT a FROM Actividadintegradora a")})
public class Actividadintegradora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveActividadIntegra")
    private Integer claveActividadIntegra;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "nombreActividad")
    private String nombreActividad;
    @Column(name = "numControl")
    private Integer numControl;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "autor")
    private String autor;
    @Column(name = "pathArchivoWord")
    private String pathArchivoWord;
    @Column(name = "pathArchivoPDF")
    private String pathArchivoPDF;
    @Column(name = "vigencia")
    private String vigencia;
    @Column(name = "edoBaja")
    private Integer edoBaja;
    @OneToMany(mappedBy = "claveActividadIntegra")
    private List<Asignacionactividad> asignacionactividadList;

    public Actividadintegradora() {
    }

    public Actividadintegradora(Integer claveActividadIntegra) {
        this.claveActividadIntegra = claveActividadIntegra;
    }

    public Integer getClaveActividadIntegra() {
        return claveActividadIntegra;
    }

    public void setClaveActividadIntegra(Integer claveActividadIntegra) {
        this.claveActividadIntegra = claveActividadIntegra;
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

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Integer getNumControl() {
        return numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPathArchivoWord() {
        return pathArchivoWord;
    }

    public void setPathArchivoWord(String pathArchivoWord) {
        this.pathArchivoWord = pathArchivoWord;
    }

    public String getPathArchivoPDF() {
        return pathArchivoPDF;
    }

    public void setPathArchivoPDF(String pathArchivoPDF) {
        this.pathArchivoPDF = pathArchivoPDF;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Integer getEdoBaja() {
        return edoBaja;
    }

    public void setEdoBaja(Integer edoBaja) {
        this.edoBaja = edoBaja;
    }

    public List<Asignacionactividad> getAsignacionactividadList() {
        return asignacionactividadList;
    }

    public void setAsignacionactividadList(List<Asignacionactividad> asignacionactividadList) {
        this.asignacionactividadList = asignacionactividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveActividadIntegra != null ? claveActividadIntegra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadintegradora)) {
            return false;
        }
        Actividadintegradora other = (Actividadintegradora) object;
        if ((this.claveActividadIntegra == null && other.claveActividadIntegra != null) || (this.claveActividadIntegra != null && !this.claveActividadIntegra.equals(other.claveActividadIntegra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Actividadintegradora[ claveActividadIntegra=" + claveActividadIntegra + " ]";
    }
    
}
