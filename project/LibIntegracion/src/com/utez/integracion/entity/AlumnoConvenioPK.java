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
public class AlumnoConvenioPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "id_convenio")
    private long idConvenio;

    public AlumnoConvenioPK() {
    }

    public AlumnoConvenioPK(String matricula, long idConvenio) {
        this.matricula = matricula;
        this.idConvenio = idConvenio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(long idConvenio) {
        this.idConvenio = idConvenio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        hash += (int) idConvenio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlumnoConvenioPK)) {
            return false;
        }
        AlumnoConvenioPK other = (AlumnoConvenioPK) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.idConvenio != other.idConvenio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AlumnoConvenioPK[ matricula=" + matricula + ", idConvenio=" + idConvenio + " ]";
    }
    
}
