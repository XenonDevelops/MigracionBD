/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "tablaencabezado")
@NamedQueries({
    @NamedQuery(name = "Tablaencabezado.findAll", query = "SELECT t FROM Tablaencabezado t")})
public class Tablaencabezado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Basic(optional = false)
    @Column(name = "tipoActa")
    private String tipoActa;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Column(name = "nombrePlan")
    private String nombrePlan;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "nombreMateria")
    private String nombreMateria;
    @Column(name = "nombreAsesor")
    private String nombreAsesor;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "folio")
    private String folio;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "firma")
    private String firma;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "claveMat")
    private String claveMat;
    @Column(name = "ciclo")
    private String ciclo;
    @Column(name = "labelGrado")
    private String labelGrado;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveTablaencabezad")
    private Integer claveTablaencabezad;

    public Tablaencabezado() {
    }

    public Tablaencabezado(Integer claveTablaencabezad) {
        this.claveTablaencabezad = claveTablaencabezad;
    }

    public Tablaencabezado(Integer claveTablaencabezad, String claveGrupo, String tipoActa) {
        this.claveTablaencabezad = claveTablaencabezad;
        this.claveGrupo = claveGrupo;
        this.tipoActa = tipoActa;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getTipoActa() {
        return tipoActa;
    }

    public void setTipoActa(String tipoActa) {
        this.tipoActa = tipoActa;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getClaveMat() {
        return claveMat;
    }

    public void setClaveMat(String claveMat) {
        this.claveMat = claveMat;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getLabelGrado() {
        return labelGrado;
    }

    public void setLabelGrado(String labelGrado) {
        this.labelGrado = labelGrado;
    }

    public Integer getClaveTablaencabezad() {
        return claveTablaencabezad;
    }

    public void setClaveTablaencabezad(Integer claveTablaencabezad) {
        this.claveTablaencabezad = claveTablaencabezad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveTablaencabezad != null ? claveTablaencabezad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablaencabezado)) {
            return false;
        }
        Tablaencabezado other = (Tablaencabezado) object;
        if ((this.claveTablaencabezad == null && other.claveTablaencabezad != null) || (this.claveTablaencabezad != null && !this.claveTablaencabezad.equals(other.claveTablaencabezad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Tablaencabezado[ claveTablaencabezad=" + claveTablaencabezad + " ]";
    }
    
}
