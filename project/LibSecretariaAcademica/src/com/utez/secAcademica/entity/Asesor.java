/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asesor")
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a"),
    @NamedQuery(name = "Asesor.findByIdasesor", query = "SELECT a FROM Asesor a WHERE a.idasesor = :idasesor"),
    @NamedQuery(name = "Asesor.findByFechatitulacion", query = "SELECT a FROM Asesor a WHERE a.fechatitulacion = :fechatitulacion"),
    @NamedQuery(name = "Asesor.findByFechacedula", query = "SELECT a FROM Asesor a WHERE a.fechacedula = :fechacedula"),
    @NamedQuery(name = "Asesor.findByProfesion", query = "SELECT a FROM Asesor a WHERE a.profesion = :profesion"),
    @NamedQuery(name = "Asesor.findByEstado", query = "SELECT a FROM Asesor a WHERE a.estado = :estado"),
    @NamedQuery(name = "Asesor.findByNumerocedula", query = "SELECT a FROM Asesor a WHERE a.numerocedula = :numerocedula")})
public class Asesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasesor")
    private Integer idasesor;
    @Column(name = "fechatitulacion")
    @Temporal(TemporalType.DATE)
    private Date fechatitulacion;
    @Column(name = "fechacedula")
    @Temporal(TemporalType.DATE)
    private Date fechacedula;
    @Column(name = "profesion")
    private String profesion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "numerocedula")
    private String numerocedula;
    @JoinTable(name = "asesorplan", joinColumns = {
        @JoinColumn(name = "idasesor", referencedColumnName = "idasesor")}, inverseJoinColumns = {
        @JoinColumn(name = "rvoe", referencedColumnName = "rvoe")})
    @ManyToMany
    private List<Planestudios> planestudiosList;
    @OneToMany(mappedBy = "idasesor")
    private List<Asesoriaasignatura> asesoriaasignaturaList;
    @JoinColumn(name = "idrecursohumano", referencedColumnName = "idrecursohumano")
    @ManyToOne
    private Recursohumano idrecursohumano;

    public Asesor() {
    }

    public Asesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Integer getIdasesor() {
        return idasesor;
    }

    public void setIdasesor(Integer idasesor) {
        this.idasesor = idasesor;
    }

    public Date getFechatitulacion() {
        return fechatitulacion;
    }

    public void setFechatitulacion(Date fechatitulacion) {
        this.fechatitulacion = fechatitulacion;
    }

    public Date getFechacedula() {
        return fechacedula;
    }

    public void setFechacedula(Date fechacedula) {
        this.fechacedula = fechacedula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumerocedula() {
        return numerocedula;
    }

    public void setNumerocedula(String numerocedula) {
        this.numerocedula = numerocedula;
    }

    public List<Planestudios> getPlanestudiosList() {
        return planestudiosList;
    }

    public void setPlanestudiosList(List<Planestudios> planestudiosList) {
        this.planestudiosList = planestudiosList;
    }

    public List<Asesoriaasignatura> getAsesoriaasignaturaList() {
        return asesoriaasignaturaList;
    }

    public void setAsesoriaasignaturaList(List<Asesoriaasignatura> asesoriaasignaturaList) {
        this.asesoriaasignaturaList = asesoriaasignaturaList;
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
        hash += (idasesor != null ? idasesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.idasesor == null && other.idasesor != null) || (this.idasesor != null && !this.idasesor.equals(other.idasesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Asesor[ idasesor=" + idasesor + " ]";
    }
    
}
