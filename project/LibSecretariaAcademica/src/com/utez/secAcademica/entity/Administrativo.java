/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entity;

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
@Table(name = "administrativo")
@NamedQueries({
    @NamedQuery(name = "Administrativo.findAll", query = "SELECT a FROM Administrativo a"),
    @NamedQuery(name = "Administrativo.findByIdadministrativo", query = "SELECT a FROM Administrativo a WHERE a.idadministrativo = :idadministrativo"),
    @NamedQuery(name = "Administrativo.findByDepartamento", query = "SELECT a FROM Administrativo a WHERE a.departamento = :departamento"),
    @NamedQuery(name = "Administrativo.findByPuesto", query = "SELECT a FROM Administrativo a WHERE a.puesto = :puesto"),
    @NamedQuery(name = "Administrativo.findByEstado", query = "SELECT a FROM Administrativo a WHERE a.estado = :estado")})
public class Administrativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadministrativo")
    private Integer idadministrativo;
    @Basic(optional = false)
    @Column(name = "departamento")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "puesto")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idrecursohumano", referencedColumnName = "idrecursohumano")
    @ManyToOne(optional = false)
    private Recursohumano idrecursohumano;

    public Administrativo() {
    }

    public Administrativo(Integer idadministrativo) {
        this.idadministrativo = idadministrativo;
    }

    public Administrativo(Integer idadministrativo, String departamento, String puesto, String estado) {
        this.idadministrativo = idadministrativo;
        this.departamento = departamento;
        this.puesto = puesto;
        this.estado = estado;
    }

    public Integer getIdadministrativo() {
        return idadministrativo;
    }

    public void setIdadministrativo(Integer idadministrativo) {
        this.idadministrativo = idadministrativo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Recursohumano getIdrecursohumano() {
        return idrecursohumano;
    }

    public void setIdrecursohumano(Recursohumano idrecursohumano) {
        this.idrecursohumano = idrecursohumano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadministrativo != null ? idadministrativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrativo)) {
            return false;
        }
        Administrativo other = (Administrativo) object;
        if ((this.idadministrativo == null && other.idadministrativo != null) || (this.idadministrativo != null && !this.idadministrativo.equals(other.idadministrativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Administrativo[ idadministrativo=" + idadministrativo + " ]";
    }
    
}
