/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "plantel")
@NamedQueries({
    @NamedQuery(name = "Plantel.findAll", query = "SELECT p FROM Plantel p")})
public class Plantel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plantel")
    private Long idPlantel;
    @Column(name = "nombre_plantel")
    private String nombrePlantel;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fax")
    private String fax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantel")
    private List<SuspensionPlantel> suspensionPlantelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantel")
    private List<AdministrativoPlantel> administrativoPlantelList;
    @OneToMany(mappedBy = "idPlantel")
    private List<Evento> eventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plantel")
    private List<VacacionPlantel> vacacionPlantelList;
    @JoinColumn(name = "id_asentamiento", referencedColumnName = "id_asentamiento")
    @ManyToOne
    private Asentamiento idAsentamiento;
    @OneToMany(mappedBy = "idPlantel")
    private List<AsignacionRecursoplantel> asignacionRecursoplantelList;
    @OneToMany(mappedBy = "idPlantel")
    private List<PlanEstudio> planEstudioList;
    @OneToMany(mappedBy = "idPlantel")
    private List<GrupoInduccion> grupoInduccionList;

    public Plantel() {
    }

    public Plantel(Long idPlantel) {
        this.idPlantel = idPlantel;
    }

    public Long getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Long idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getNombrePlantel() {
        return nombrePlantel;
    }

    public void setNombrePlantel(String nombrePlantel) {
        this.nombrePlantel = nombrePlantel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<SuspensionPlantel> getSuspensionPlantelList() {
        return suspensionPlantelList;
    }

    public void setSuspensionPlantelList(List<SuspensionPlantel> suspensionPlantelList) {
        this.suspensionPlantelList = suspensionPlantelList;
    }

    public List<AdministrativoPlantel> getAdministrativoPlantelList() {
        return administrativoPlantelList;
    }

    public void setAdministrativoPlantelList(List<AdministrativoPlantel> administrativoPlantelList) {
        this.administrativoPlantelList = administrativoPlantelList;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public List<VacacionPlantel> getVacacionPlantelList() {
        return vacacionPlantelList;
    }

    public void setVacacionPlantelList(List<VacacionPlantel> vacacionPlantelList) {
        this.vacacionPlantelList = vacacionPlantelList;
    }

    public Asentamiento getIdAsentamiento() {
        return idAsentamiento;
    }

    public void setIdAsentamiento(Asentamiento idAsentamiento) {
        this.idAsentamiento = idAsentamiento;
    }

    public List<AsignacionRecursoplantel> getAsignacionRecursoplantelList() {
        return asignacionRecursoplantelList;
    }

    public void setAsignacionRecursoplantelList(List<AsignacionRecursoplantel> asignacionRecursoplantelList) {
        this.asignacionRecursoplantelList = asignacionRecursoplantelList;
    }

    public List<PlanEstudio> getPlanEstudioList() {
        return planEstudioList;
    }

    public void setPlanEstudioList(List<PlanEstudio> planEstudioList) {
        this.planEstudioList = planEstudioList;
    }

    public List<GrupoInduccion> getGrupoInduccionList() {
        return grupoInduccionList;
    }

    public void setGrupoInduccionList(List<GrupoInduccion> grupoInduccionList) {
        this.grupoInduccionList = grupoInduccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlantel != null ? idPlantel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantel)) {
            return false;
        }
        Plantel other = (Plantel) object;
        if ((this.idPlantel == null && other.idPlantel != null) || (this.idPlantel != null && !this.idPlantel.equals(other.idPlantel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Plantel[ idPlantel=" + idPlantel + " ]";
    }
    
}
