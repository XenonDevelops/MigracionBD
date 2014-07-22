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
@Table(name = "grupos")
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g")})
public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clavegrupo")
    private String clavegrupo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "cupo")
    private Integer cupo;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "claveOpcEst")
    private String claveOpcEst;

    public Grupos() {
    }

    public Grupos(String clavegrupo) {
        this.clavegrupo = clavegrupo;
    }

    public String getClavegrupo() {
        return clavegrupo;
    }

    public void setClavegrupo(String clavegrupo) {
        this.clavegrupo = clavegrupo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getClaveOpcEst() {
        return claveOpcEst;
    }

    public void setClaveOpcEst(String claveOpcEst) {
        this.claveOpcEst = claveOpcEst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavegrupo != null ? clavegrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.clavegrupo == null && other.clavegrupo != null) || (this.clavegrupo != null && !this.clavegrupo.equals(other.clavegrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Grupos[ clavegrupo=" + clavegrupo + " ]";
    }
    
}
