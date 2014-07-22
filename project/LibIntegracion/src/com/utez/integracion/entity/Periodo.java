/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "periodo")
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p")})
public class Periodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_periodo")
    private String idPeriodo;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "descripcion_periodo")
    private String descripcionPeriodo;
    @Column(name = "estado_periodo")
    private String estadoPeriodo;
    @OneToMany(mappedBy = "idPeriodo")
    private List<CalendarioEscolar> calendarioEscolarList;
    @OneToMany(mappedBy = "idPeriodo")
    private List<PagoPeriodo> pagoPeriodoList;
    @OneToMany(mappedBy = "idPeriodo")
    private List<GrupoInduccion> grupoInduccionList;
    @OneToMany(mappedBy = "idPeriodo")
    private List<CalendarioRectoria> calendarioRectoriaList;
    @OneToMany(mappedBy = "idPeriodo")
    private List<Aspirante> aspiranteList;
    @OneToMany(mappedBy = "idPeriodo")
    private List<Generacion> generacionList;

    public Periodo() {
    }

    public Periodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(String idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionPeriodo() {
        return descripcionPeriodo;
    }

    public void setDescripcionPeriodo(String descripcionPeriodo) {
        this.descripcionPeriodo = descripcionPeriodo;
    }

    public String getEstadoPeriodo() {
        return estadoPeriodo;
    }

    public void setEstadoPeriodo(String estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }

    public List<CalendarioEscolar> getCalendarioEscolarList() {
        return calendarioEscolarList;
    }

    public void setCalendarioEscolarList(List<CalendarioEscolar> calendarioEscolarList) {
        this.calendarioEscolarList = calendarioEscolarList;
    }

    public List<PagoPeriodo> getPagoPeriodoList() {
        return pagoPeriodoList;
    }

    public void setPagoPeriodoList(List<PagoPeriodo> pagoPeriodoList) {
        this.pagoPeriodoList = pagoPeriodoList;
    }

    public List<GrupoInduccion> getGrupoInduccionList() {
        return grupoInduccionList;
    }

    public void setGrupoInduccionList(List<GrupoInduccion> grupoInduccionList) {
        this.grupoInduccionList = grupoInduccionList;
    }

    public List<CalendarioRectoria> getCalendarioRectoriaList() {
        return calendarioRectoriaList;
    }

    public void setCalendarioRectoriaList(List<CalendarioRectoria> calendarioRectoriaList) {
        this.calendarioRectoriaList = calendarioRectoriaList;
    }

    public List<Aspirante> getAspiranteList() {
        return aspiranteList;
    }

    public void setAspiranteList(List<Aspirante> aspiranteList) {
        this.aspiranteList = aspiranteList;
    }

    public List<Generacion> getGeneracionList() {
        return generacionList;
    }

    public void setGeneracionList(List<Generacion> generacionList) {
        this.generacionList = generacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Periodo[ idPeriodo=" + idPeriodo + " ]";
    }
    
}
