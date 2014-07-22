/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "asesor_calificacion")
@NamedQueries({
    @NamedQuery(name = "AsesorCalificacion.findAll", query = "SELECT a FROM AsesorCalificacion a")})
public class AsesorCalificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsesorCalificacionPK asesorCalificacionPK;
    @JoinColumn(name = "id_alumnoasignaturaesquema", referencedColumnName = "id_esquemaalumnoasignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EsquemaAlumnoasignatura esquemaAlumnoasignatura;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asesor asesor;

    public AsesorCalificacion() {
    }

    public AsesorCalificacion(AsesorCalificacionPK asesorCalificacionPK) {
        this.asesorCalificacionPK = asesorCalificacionPK;
    }

    public AsesorCalificacion(long idAlumnoasignaturaesquema, long idAsesor) {
        this.asesorCalificacionPK = new AsesorCalificacionPK(idAlumnoasignaturaesquema, idAsesor);
    }

    public AsesorCalificacionPK getAsesorCalificacionPK() {
        return asesorCalificacionPK;
    }

    public void setAsesorCalificacionPK(AsesorCalificacionPK asesorCalificacionPK) {
        this.asesorCalificacionPK = asesorCalificacionPK;
    }

    public EsquemaAlumnoasignatura getEsquemaAlumnoasignatura() {
        return esquemaAlumnoasignatura;
    }

    public void setEsquemaAlumnoasignatura(EsquemaAlumnoasignatura esquemaAlumnoasignatura) {
        this.esquemaAlumnoasignatura = esquemaAlumnoasignatura;
    }

    public Asesor getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesor asesor) {
        this.asesor = asesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asesorCalificacionPK != null ? asesorCalificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsesorCalificacion)) {
            return false;
        }
        AsesorCalificacion other = (AsesorCalificacion) object;
        if ((this.asesorCalificacionPK == null && other.asesorCalificacionPK != null) || (this.asesorCalificacionPK != null && !this.asesorCalificacionPK.equals(other.asesorCalificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsesorCalificacion[ asesorCalificacionPK=" + asesorCalificacionPK + " ]";
    }
    
}
