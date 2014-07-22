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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fechasexam")
@NamedQueries({
    @NamedQuery(name = "Fechasexam.findAll", query = "SELECT f FROM Fechasexam f")})
public class Fechasexam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveCalendario")
    private Integer claveCalendario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tipoExamen")
    private String tipoExamen;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveFechasExam")
    private Integer claveFechasExam;
    @JoinColumn(name = "claveAsesoria", referencedColumnName = "claveAsesoria")
    @ManyToOne
    private Asesoriascalendario claveAsesoria;

    public Fechasexam() {
    }

    public Fechasexam(Integer claveFechasExam) {
        this.claveFechasExam = claveFechasExam;
    }

    public Integer getClaveCalendario() {
        return claveCalendario;
    }

    public void setClaveCalendario(Integer claveCalendario) {
        this.claveCalendario = claveCalendario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public Integer getClaveFechasExam() {
        return claveFechasExam;
    }

    public void setClaveFechasExam(Integer claveFechasExam) {
        this.claveFechasExam = claveFechasExam;
    }

    public Asesoriascalendario getClaveAsesoria() {
        return claveAsesoria;
    }

    public void setClaveAsesoria(Asesoriascalendario claveAsesoria) {
        this.claveAsesoria = claveAsesoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveFechasExam != null ? claveFechasExam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fechasexam)) {
            return false;
        }
        Fechasexam other = (Fechasexam) object;
        if ((this.claveFechasExam == null && other.claveFechasExam != null) || (this.claveFechasExam != null && !this.claveFechasExam.equals(other.claveFechasExam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Fechasexam[ claveFechasExam=" + claveFechasExam + " ]";
    }
    
}
