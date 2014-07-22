/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entity;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asignatura")
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIdasignatura", query = "SELECT a FROM Asignatura a WHERE a.idasignatura = :idasignatura"),
    @NamedQuery(name = "Asignatura.findByClavesep", query = "SELECT a FROM Asignatura a WHERE a.clavesep = :clavesep"),
    @NamedQuery(name = "Asignatura.findByAsignatura", query = "SELECT a FROM Asignatura a WHERE a.asignatura = :asignatura"),
    @NamedQuery(name = "Asignatura.findByCreditos", query = "SELECT a FROM Asignatura a WHERE a.creditos = :creditos"),
    @NamedQuery(name = "Asignatura.findByNivel", query = "SELECT a FROM Asignatura a WHERE a.nivel = :nivel"),
    @NamedQuery(name = "Asignatura.findByTipoasignatura", query = "SELECT a FROM Asignatura a WHERE a.tipoasignatura = :tipoasignatura")})
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasignatura")
    private Integer idasignatura;
    @Column(name = "clavesep")
    private String clavesep;
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "creditos")
    private Integer creditos;
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "tipoasignatura")
    private String tipoasignatura;
    @OneToMany(mappedBy = "idasignatura")
    private List<Cuadernoprogramacion> cuadernoprogramacionList;
    @JoinColumn(name = "rvoe", referencedColumnName = "rvoe")
    @ManyToOne
    private Planestudios rvoe;
    @OneToMany(mappedBy = "seriadacon")
    private List<Asignatura> asignaturaList;
    @JoinColumn(name = "seriadacon", referencedColumnName = "idasignatura")
    @ManyToOne
    private Asignatura seriadacon;
    @OneToMany(mappedBy = "idasignatura")
    private List<Asesoriaasignatura> asesoriaasignaturaList;
    @OneToMany(mappedBy = "idasignatura")
    private List<Bloqueasignatura> bloqueasignaturaList;

    public Asignatura() {
    }

    public Asignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

    public Integer getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(Integer idasignatura) {
        this.idasignatura = idasignatura;
    }

    public String getClavesep() {
        return clavesep;
    }

    public void setClavesep(String clavesep) {
        this.clavesep = clavesep;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipoasignatura() {
        return tipoasignatura;
    }

    public void setTipoasignatura(String tipoasignatura) {
        this.tipoasignatura = tipoasignatura;
    }

    public List<Cuadernoprogramacion> getCuadernoprogramacionList() {
        return cuadernoprogramacionList;
    }

    public void setCuadernoprogramacionList(List<Cuadernoprogramacion> cuadernoprogramacionList) {
        this.cuadernoprogramacionList = cuadernoprogramacionList;
    }

    public Planestudios getRvoe() {
        return rvoe;
    }

    public void setRvoe(Planestudios rvoe) {
        this.rvoe = rvoe;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Asignatura getSeriadacon() {
        return seriadacon;
    }

    public void setSeriadacon(Asignatura seriadacon) {
        this.seriadacon = seriadacon;
    }

    public List<Asesoriaasignatura> getAsesoriaasignaturaList() {
        return asesoriaasignaturaList;
    }

    public void setAsesoriaasignaturaList(List<Asesoriaasignatura> asesoriaasignaturaList) {
        this.asesoriaasignaturaList = asesoriaasignaturaList;
    }

    public List<Bloqueasignatura> getBloqueasignaturaList() {
        return bloqueasignaturaList;
    }

    public void setBloqueasignaturaList(List<Bloqueasignatura> bloqueasignaturaList) {
        this.bloqueasignaturaList = bloqueasignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasignatura != null ? idasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idasignatura == null && other.idasignatura != null) || (this.idasignatura != null && !this.idasignatura.equals(other.idasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Asignatura[ idasignatura=" + idasignatura + " ]";
    }
    
}
