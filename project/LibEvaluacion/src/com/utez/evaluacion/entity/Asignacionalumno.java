/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignacionalumno")
@NamedQueries({
    @NamedQuery(name = "Asignacionalumno.findAll", query = "SELECT a FROM Asignacionalumno a")})
public class Asignacionalumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsignacionAlumno")
    private Integer claveAsignacionAlumno;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "claveAsignacion", referencedColumnName = "claveAsignacion")
    @ManyToOne
    private Asignacionactividad claveAsignacion;

    public Asignacionalumno() {
    }

    public Asignacionalumno(Integer claveAsignacionAlumno) {
        this.claveAsignacionAlumno = claveAsignacionAlumno;
    }

    public Integer getClaveAsignacionAlumno() {
        return claveAsignacionAlumno;
    }

    public void setClaveAsignacionAlumno(Integer claveAsignacionAlumno) {
        this.claveAsignacionAlumno = claveAsignacionAlumno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Asignacionactividad getClaveAsignacion() {
        return claveAsignacion;
    }

    public void setClaveAsignacion(Asignacionactividad claveAsignacion) {
        this.claveAsignacion = claveAsignacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsignacionAlumno != null ? claveAsignacionAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacionalumno)) {
            return false;
        }
        Asignacionalumno other = (Asignacionalumno) object;
        if ((this.claveAsignacionAlumno == null && other.claveAsignacionAlumno != null) || (this.claveAsignacionAlumno != null && !this.claveAsignacionAlumno.equals(other.claveAsignacionAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asignacionalumno[ claveAsignacionAlumno=" + claveAsignacionAlumno + " ]";
    }
    
}
