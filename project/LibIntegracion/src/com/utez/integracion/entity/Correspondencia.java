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
@Table(name = "correspondencia")
@NamedQueries({
    @NamedQuery(name = "Correspondencia.findAll", query = "SELECT c FROM Correspondencia c")})
public class Correspondencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CorrespondenciaPK correspondenciaPK;
    @JoinColumn(name = "id_asignaturacorrespondencia", referencedColumnName = "id_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura1;

    public Correspondencia() {
    }

    public Correspondencia(CorrespondenciaPK correspondenciaPK) {
        this.correspondenciaPK = correspondenciaPK;
    }

    public Correspondencia(long idAsignatura, long idAsignaturacorrespondencia) {
        this.correspondenciaPK = new CorrespondenciaPK(idAsignatura, idAsignaturacorrespondencia);
    }

    public CorrespondenciaPK getCorrespondenciaPK() {
        return correspondenciaPK;
    }

    public void setCorrespondenciaPK(CorrespondenciaPK correspondenciaPK) {
        this.correspondenciaPK = correspondenciaPK;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura1() {
        return asignatura1;
    }

    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correspondenciaPK != null ? correspondenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correspondencia)) {
            return false;
        }
        Correspondencia other = (Correspondencia) object;
        if ((this.correspondenciaPK == null && other.correspondenciaPK != null) || (this.correspondenciaPK != null && !this.correspondenciaPK.equals(other.correspondenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Correspondencia[ correspondenciaPK=" + correspondenciaPK + " ]";
    }
    
}
