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
public class ProgramacionOpcioncasesorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_programacionasignatura")
    private long idProgramacionasignatura;
    @Basic(optional = false)
    @Column(name = "id_asesor")
    private long idAsesor;

    public ProgramacionOpcioncasesorPK() {
    }

    public ProgramacionOpcioncasesorPK(long idProgramacionasignatura, long idAsesor) {
        this.idProgramacionasignatura = idProgramacionasignatura;
        this.idAsesor = idAsesor;
    }

    public long getIdProgramacionasignatura() {
        return idProgramacionasignatura;
    }

    public void setIdProgramacionasignatura(long idProgramacionasignatura) {
        this.idProgramacionasignatura = idProgramacionasignatura;
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
        hash += (int) idProgramacionasignatura;
        hash += (int) idAsesor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionOpcioncasesorPK)) {
            return false;
        }
        ProgramacionOpcioncasesorPK other = (ProgramacionOpcioncasesorPK) object;
        if (this.idProgramacionasignatura != other.idProgramacionasignatura) {
            return false;
        }
        if (this.idAsesor != other.idAsesor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionOpcioncasesorPK[ idProgramacionasignatura=" + idProgramacionasignatura + ", idAsesor=" + idAsesor + " ]";
    }
    
}
