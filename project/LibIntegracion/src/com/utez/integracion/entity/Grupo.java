/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
import javax.persistence.JoinColumn;
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
@Table(name = "grupo")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Long idGrupo;
    @Column(name = "nombre_grupo")
    private String nombreGrupo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado_grupo")
    private String estadoGrupo;
    @OneToMany(mappedBy = "idGrupo")
    private List<CalendarioEscolar> calendarioEscolarList;
    @OneToMany(mappedBy = "idGrupo")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idGrupo")
    private List<AsignacionEvaluacion> asignacionEvaluacionList;
    @OneToMany(mappedBy = "idGrupo")
    private List<BitacoraNotificacion> bitacoraNotificacionList;
    @OneToMany(mappedBy = "idGrupo")
    private List<AsignacionGrupoencuesta> asignacionGrupoencuestaList;
    @OneToMany(mappedBy = "idGrupo")
    private List<GrupoAlumnoesquema> grupoAlumnoesquemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private List<AsignacionGrupo> asignacionGrupoList;
    @JoinColumn(name = "id_opcionestudio", referencedColumnName = "id_opcionestudio")
    @ManyToOne
    private OpcionEstudio idOpcionestudio;
    @JoinColumn(name = "id_generacion", referencedColumnName = "id_generacion")
    @ManyToOne
    private Generacion idGeneracion;

    public Grupo() {
    }

    public Grupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoGrupo() {
        return estadoGrupo;
    }

    public void setEstadoGrupo(String estadoGrupo) {
        this.estadoGrupo = estadoGrupo;
    }

    public List<CalendarioEscolar> getCalendarioEscolarList() {
        return calendarioEscolarList;
    }

    public void setCalendarioEscolarList(List<CalendarioEscolar> calendarioEscolarList) {
        this.calendarioEscolarList = calendarioEscolarList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<AsignacionEvaluacion> getAsignacionEvaluacionList() {
        return asignacionEvaluacionList;
    }

    public void setAsignacionEvaluacionList(List<AsignacionEvaluacion> asignacionEvaluacionList) {
        this.asignacionEvaluacionList = asignacionEvaluacionList;
    }

    public List<BitacoraNotificacion> getBitacoraNotificacionList() {
        return bitacoraNotificacionList;
    }

    public void setBitacoraNotificacionList(List<BitacoraNotificacion> bitacoraNotificacionList) {
        this.bitacoraNotificacionList = bitacoraNotificacionList;
    }

    public List<AsignacionGrupoencuesta> getAsignacionGrupoencuestaList() {
        return asignacionGrupoencuestaList;
    }

    public void setAsignacionGrupoencuestaList(List<AsignacionGrupoencuesta> asignacionGrupoencuestaList) {
        this.asignacionGrupoencuestaList = asignacionGrupoencuestaList;
    }

    public List<GrupoAlumnoesquema> getGrupoAlumnoesquemaList() {
        return grupoAlumnoesquemaList;
    }

    public void setGrupoAlumnoesquemaList(List<GrupoAlumnoesquema> grupoAlumnoesquemaList) {
        this.grupoAlumnoesquemaList = grupoAlumnoesquemaList;
    }

    public List<AsignacionGrupo> getAsignacionGrupoList() {
        return asignacionGrupoList;
    }

    public void setAsignacionGrupoList(List<AsignacionGrupo> asignacionGrupoList) {
        this.asignacionGrupoList = asignacionGrupoList;
    }

    public OpcionEstudio getIdOpcionestudio() {
        return idOpcionestudio;
    }

    public void setIdOpcionestudio(OpcionEstudio idOpcionestudio) {
        this.idOpcionestudio = idOpcionestudio;
    }

    public Generacion getIdGeneracion() {
        return idGeneracion;
    }

    public void setIdGeneracion(Generacion idGeneracion) {
        this.idGeneracion = idGeneracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Grupo[ idGrupo=" + idGrupo + " ]";
    }
    
}
