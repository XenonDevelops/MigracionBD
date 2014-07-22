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
@Table(name = "alumno_convenio")
@NamedQueries({
    @NamedQuery(name = "AlumnoConvenio.findAll", query = "SELECT a FROM AlumnoConvenio a")})
public class AlumnoConvenio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumnoConvenioPK alumnoConvenioPK;
    @JoinColumn(name = "id_convenio", referencedColumnName = "id_convenio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Convenio convenio;
    @JoinColumn(name = "matricula", referencedColumnName = "matricula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    public AlumnoConvenio() {
    }

    public AlumnoConvenio(AlumnoConvenioPK alumnoConvenioPK) {
        this.alumnoConvenioPK = alumnoConvenioPK;
    }

    public AlumnoConvenio(String matricula, long idConvenio) {
        this.alumnoConvenioPK = new AlumnoConvenioPK(matricula, idConvenio);
    }

    public AlumnoConvenioPK getAlumnoConvenioPK() {
        return alumnoConvenioPK;
    }

    public void setAlumnoConvenioPK(AlumnoConvenioPK alumnoConvenioPK) {
        this.alumnoConvenioPK = alumnoConvenioPK;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnoConvenioPK != null ? alumnoConvenioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoConvenio)) {
            return false;
        }
        AlumnoConvenio other = (AlumnoConvenio) object;
        if ((this.alumnoConvenioPK == null && other.alumnoConvenioPK != null) || (this.alumnoConvenioPK != null && !this.alumnoConvenioPK.equals(other.alumnoConvenioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AlumnoConvenio[ alumnoConvenioPK=" + alumnoConvenioPK + " ]";
    }
    
}
