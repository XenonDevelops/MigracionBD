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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "calendarioescolar")
@NamedQueries({
    @NamedQuery(name = "Calendarioescolar.findAll", query = "SELECT c FROM Calendarioescolar c")})
public class Calendarioescolar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCalendario")
    private Integer claveCalendario;
    @Column(name = "clavePlantel")
    private Integer clavePlantel;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "periodoEscolar")
    private String periodoEscolar;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "generacion")
    private String generacion;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "claveCalendario")
    private List<Asesoriascalendario> asesoriascalendarioList;

    public Calendarioescolar() {
    }

    public Calendarioescolar(Integer claveCalendario) {
        this.claveCalendario = claveCalendario;
    }

    public Integer getClaveCalendario() {
        return claveCalendario;
    }

    public void setClaveCalendario(Integer claveCalendario) {
        this.claveCalendario = claveCalendario;
    }

    public Integer getClavePlantel() {
        return clavePlantel;
    }

    public void setClavePlantel(Integer clavePlantel) {
        this.clavePlantel = clavePlantel;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Asesoriascalendario> getAsesoriascalendarioList() {
        return asesoriascalendarioList;
    }

    public void setAsesoriascalendarioList(List<Asesoriascalendario> asesoriascalendarioList) {
        this.asesoriascalendarioList = asesoriascalendarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCalendario != null ? claveCalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendarioescolar)) {
            return false;
        }
        Calendarioescolar other = (Calendarioescolar) object;
        if ((this.claveCalendario == null && other.claveCalendario != null) || (this.claveCalendario != null && !this.claveCalendario.equals(other.claveCalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Calendarioescolar[ claveCalendario=" + claveCalendario + " ]";
    }
    
}
