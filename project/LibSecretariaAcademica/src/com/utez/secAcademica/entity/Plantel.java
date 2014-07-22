/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entity;

import java.io.Serializable;
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

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "plantel")
@NamedQueries({
    @NamedQuery(name = "Plantel.findAll", query = "SELECT p FROM Plantel p"),
    @NamedQuery(name = "Plantel.findByIdplantel", query = "SELECT p FROM Plantel p WHERE p.idplantel = :idplantel"),
    @NamedQuery(name = "Plantel.findByNombre", query = "SELECT p FROM Plantel p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Plantel.findByLugar", query = "SELECT p FROM Plantel p WHERE p.lugar = :lugar"),
    @NamedQuery(name = "Plantel.findByDireccion", query = "SELECT p FROM Plantel p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Plantel.findByTelefono", query = "SELECT p FROM Plantel p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Plantel.findByCorreoelectronico", query = "SELECT p FROM Plantel p WHERE p.correoelectronico = :correoelectronico")})
public class Plantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplantel")
    private Integer idplantel;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @OneToMany(mappedBy = "idplantel")
    private List<Mesopcionc> mesopcioncList;
    @OneToMany(mappedBy = "idplantel")
    private List<Recursohumano> recursohumanoList;
    @OneToMany(mappedBy = "idplantel")
    private List<Grupo> grupoList;
    @OneToMany(mappedBy = "idplantel")
    private List<Calendariorectoria> calendariorectoriaList;
    @OneToMany(mappedBy = "idplantel")
    private List<Calendario> calendarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idplantel")
    private List<Planestudios> planestudiosList;

    public Plantel() {
    }

    public Plantel(Integer idplantel) {
        this.idplantel = idplantel;
    }

    public Integer getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Integer idplantel) {
        this.idplantel = idplantel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public List<Mesopcionc> getMesopcioncList() {
        return mesopcioncList;
    }

    public void setMesopcioncList(List<Mesopcionc> mesopcioncList) {
        this.mesopcioncList = mesopcioncList;
    }

    public List<Recursohumano> getRecursohumanoList() {
        return recursohumanoList;
    }

    public void setRecursohumanoList(List<Recursohumano> recursohumanoList) {
        this.recursohumanoList = recursohumanoList;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public List<Calendariorectoria> getCalendariorectoriaList() {
        return calendariorectoriaList;
    }

    public void setCalendariorectoriaList(List<Calendariorectoria> calendariorectoriaList) {
        this.calendariorectoriaList = calendariorectoriaList;
    }

    public List<Calendario> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<Calendario> calendarioList) {
        this.calendarioList = calendarioList;
    }

    public List<Planestudios> getPlanestudiosList() {
        return planestudiosList;
    }

    public void setPlanestudiosList(List<Planestudios> planestudiosList) {
        this.planestudiosList = planestudiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplantel != null ? idplantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantel)) {
            return false;
        }
        Plantel other = (Plantel) object;
        if ((this.idplantel == null && other.idplantel != null) || (this.idplantel != null && !this.idplantel.equals(other.idplantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Plantel[ idplantel=" + idplantel + " ]";
    }
    
}
