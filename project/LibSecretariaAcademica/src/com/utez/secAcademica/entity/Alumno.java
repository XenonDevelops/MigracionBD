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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "alumno")
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByMatricula", query = "SELECT a FROM Alumno a WHERE a.matricula = :matricula"),
    @NamedQuery(name = "Alumno.findByApellidopaterno", query = "SELECT a FROM Alumno a WHERE a.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Alumno.findByApellidomaterno", query = "SELECT a FROM Alumno a WHERE a.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Alumno.findByNombres", query = "SELECT a FROM Alumno a WHERE a.nombres = :nombres")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "apellidopaterno")
    private String apellidopaterno;
    @Column(name = "apellidomaterno")
    private String apellidomaterno;
    @Column(name = "nombres")
    private String nombres;
    @OneToMany(mappedBy = "matricula")
    private List<Adeudo> adeudoList;
    @OneToMany(mappedBy = "matricula")
    private List<Movimientocie> movimientocieList;

    public Alumno() {
    }

    public Alumno(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public List<Adeudo> getAdeudoList() {
        return adeudoList;
    }

    public void setAdeudoList(List<Adeudo> adeudoList) {
        this.adeudoList = adeudoList;
    }

    public List<Movimientocie> getMovimientocieList() {
        return movimientocieList;
    }

    public void setMovimientocieList(List<Movimientocie> movimientocieList) {
        this.movimientocieList = movimientocieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Alumno[ matricula=" + matricula + " ]";
    }
    
}
