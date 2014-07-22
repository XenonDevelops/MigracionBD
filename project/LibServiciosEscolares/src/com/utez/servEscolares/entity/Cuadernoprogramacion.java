/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cuadernoprogramacion")
@NamedQueries({
    @NamedQuery(name = "Cuadernoprogramacion.findAll", query = "SELECT c FROM Cuadernoprogramacion c")})
public class Cuadernoprogramacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCuaderno")
    private Integer claveCuaderno;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Column(name = "fechaAceptacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAceptacion;
    @Column(name = "numProgramacion")
    private Integer numProgramacion;
    @Column(name = "tipoAlumno")
    private String tipoAlumno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveCuaderno")
    private List<Programacionopcionc> programacionopcioncList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveCuaderno")
    private List<Programacionrecursada> programacionrecursadaList;

    public Cuadernoprogramacion() {
    }

    public Cuadernoprogramacion(Integer claveCuaderno) {
        this.claveCuaderno = claveCuaderno;
    }

    public Integer getClaveCuaderno() {
        return claveCuaderno;
    }

    public void setClaveCuaderno(Integer claveCuaderno) {
        this.claveCuaderno = claveCuaderno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Date getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(Date fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public Integer getNumProgramacion() {
        return numProgramacion;
    }

    public void setNumProgramacion(Integer numProgramacion) {
        this.numProgramacion = numProgramacion;
    }

    public String getTipoAlumno() {
        return tipoAlumno;
    }

    public void setTipoAlumno(String tipoAlumno) {
        this.tipoAlumno = tipoAlumno;
    }

    public List<Programacionopcionc> getProgramacionopcioncList() {
        return programacionopcioncList;
    }

    public void setProgramacionopcioncList(List<Programacionopcionc> programacionopcioncList) {
        this.programacionopcioncList = programacionopcioncList;
    }

    public List<Programacionrecursada> getProgramacionrecursadaList() {
        return programacionrecursadaList;
    }

    public void setProgramacionrecursadaList(List<Programacionrecursada> programacionrecursadaList) {
        this.programacionrecursadaList = programacionrecursadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCuaderno != null ? claveCuaderno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuadernoprogramacion)) {
            return false;
        }
        Cuadernoprogramacion other = (Cuadernoprogramacion) object;
        if ((this.claveCuaderno == null && other.claveCuaderno != null) || (this.claveCuaderno != null && !this.claveCuaderno.equals(other.claveCuaderno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Cuadernoprogramacion[ claveCuaderno=" + claveCuaderno + " ]";
    }
    
}
