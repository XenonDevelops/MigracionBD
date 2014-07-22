/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "plandeestudios")
@NamedQueries({
    @NamedQuery(name = "Plandeestudios.findAll", query = "SELECT p FROM Plandeestudios p")})
public class Plandeestudios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "nombrePlan")
    private String nombrePlan;
    @Column(name = "fechaAcuerdo")
    @Temporal(TemporalType.DATE)
    private Date fechaAcuerdo;
    @Column(name = "numAcuerdo")
    private String numAcuerdo;
    @Column(name = "numCreditos")
    private Integer numCreditos;
    @Column(name = "letras")
    private String letras;
    @Column(name = "estado")
    private String estado;
    @Column(name = "instRegistro")
    private String instRegistro;

    public Plandeestudios() {
    }

    public Plandeestudios(String revoe) {
        this.revoe = revoe;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Date getFechaAcuerdo() {
        return fechaAcuerdo;
    }

    public void setFechaAcuerdo(Date fechaAcuerdo) {
        this.fechaAcuerdo = fechaAcuerdo;
    }

    public String getNumAcuerdo() {
        return numAcuerdo;
    }

    public void setNumAcuerdo(String numAcuerdo) {
        this.numAcuerdo = numAcuerdo;
    }

    public Integer getNumCreditos() {
        return numCreditos;
    }

    public void setNumCreditos(Integer numCreditos) {
        this.numCreditos = numCreditos;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInstRegistro() {
        return instRegistro;
    }

    public void setInstRegistro(String instRegistro) {
        this.instRegistro = instRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revoe != null ? revoe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plandeestudios)) {
            return false;
        }
        Plandeestudios other = (Plandeestudios) object;
        if ((this.revoe == null && other.revoe != null) || (this.revoe != null && !this.revoe.equals(other.revoe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Plandeestudios[ revoe=" + revoe + " ]";
    }
    
}
