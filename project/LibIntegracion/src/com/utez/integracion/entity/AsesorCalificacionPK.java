/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sergio
 */
@Embeddable
public class AsesorCalificacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_alumnoasignaturaesquema")
    private long idAlumnoasignaturaesquema;
    @Basic(optional = false)
    @Column(name = "id_asesor")
    private long idAsesor;

    public AsesorCalificacionPK() {
    }

    public AsesorCalificacionPK(long idAlumnoasignaturaesquema, long idAsesor) {
        this.idAlumnoasignaturaesquema = idAlumnoasignaturaesquema;
        this.idAsesor = idAsesor;
    }

    public long getIdAlumnoasignaturaesquema() {
        return idAlumnoasignaturaesquema;
    }

    public void setIdAlumnoasignaturaesquema(long idAlumnoasignaturaesquema) {
        this.idAlumnoasignaturaesquema = idAlumnoasignaturaesquema;
    }

    public long getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(long idAsesor) {
        this.idAsesor = idAsesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAlumnoasignaturaesquema;
        hash += (int) idAsesor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsesorCalificacionPK)) {
            return false;
        }
        AsesorCalificacionPK other = (AsesorCalificacionPK) object;
        if (this.idAlumnoasignaturaesquema != other.idAlumnoasignaturaesquema) {
            return false;
        }
        if (this.idAsesor != other.idAsesor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsesorCalificacionPK[ idAlumnoasignaturaesquema=" + idAlumnoasignaturaesquema + ", idAsesor=" + idAsesor + " ]";
    }
    
}
