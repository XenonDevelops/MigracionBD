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
@Table(name = "programacion_opcioncasesor")
@NamedQueries({
    @NamedQuery(name = "ProgramacionOpcioncasesor.findAll", query = "SELECT p FROM ProgramacionOpcioncasesor p")})
public class ProgramacionOpcioncasesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramacionOpcioncasesorPK programacionOpcioncasesorPK;
    @JoinColumn(name = "id_programacionasignatura", referencedColumnName = "id_programacionasignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProgramacionOpcionc programacionOpcionc;
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asesor asesor;

    public ProgramacionOpcioncasesor() {
    }

    public ProgramacionOpcioncasesor(ProgramacionOpcioncasesorPK programacionOpcioncasesorPK) {
        this.programacionOpcioncasesorPK = programacionOpcioncasesorPK;
    }

    public ProgramacionOpcioncasesor(long idProgramacionasignatura, long idAsesor) {
        this.programacionOpcioncasesorPK = new ProgramacionOpcioncasesorPK(idProgramacionasignatura, idAsesor);
    }

    public ProgramacionOpcioncasesorPK getProgramacionOpcioncasesorPK() {
        return programacionOpcioncasesorPK;
    }

    public void setProgramacionOpcioncasesorPK(ProgramacionOpcioncasesorPK programacionOpcioncasesorPK) {
        this.programacionOpcioncasesorPK = programacionOpcioncasesorPK;
    }

    public ProgramacionOpcionc getProgramacionOpcionc() {
        return programacionOpcionc;
    }

    public void setProgramacionOpcionc(ProgramacionOpcionc programacionOpcionc) {
        this.programacionOpcionc = programacionOpcionc;
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
        hash += (programacionOpcioncasesorPK != null ? programacionOpcioncasesorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionOpcioncasesor)) {
            return false;
        }
        ProgramacionOpcioncasesor other = (ProgramacionOpcioncasesor) object;
        if ((this.programacionOpcioncasesorPK == null && other.programacionOpcioncasesorPK != null) || (this.programacionOpcioncasesorPK != null && !this.programacionOpcioncasesorPK.equals(other.programacionOpcioncasesorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ProgramacionOpcioncasesor[ programacionOpcioncasesorPK=" + programacionOpcioncasesorPK + " ]";
    }
    
}
