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
@Table(name = "asignatura")
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")})
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignatura")
    private Long idAsignatura;
    @Column(name = "clave_sep")
    private String claveSep;
    @Column(name = "nombre_asignatura")
    private String nombreAsignatura;
    @Column(name = "creditos")
    private Integer creditos;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AlumnoAsignatura> alumnoAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Correspondencia> correspondenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura1")
    private List<Correspondencia> correspondenciaList1;
    @OneToMany(mappedBy = "idAsignatura")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<Acta> actaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<AsignacionAsignaturabanco> asignacionAsignaturabancoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<AsignaturaSeriada> asignaturaSeriadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura1")
    private List<AsignaturaSeriada> asignaturaSeriadaList1;
    @OneToMany(mappedBy = "idAsignatura")
    private List<CalendarioAsignatura> calendarioAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<BloqueAsignatura> bloqueAsignaturaList;
    @OneToMany(mappedBy = "idAsignatura")
    private List<ProgramacionAsignatura> programacionAsignaturaList;
    @JoinColumn(name = "id_tiponivelasignatura", referencedColumnName = "id_tiponivelasignatura")
    @ManyToOne
    private TipoNivelasignatura idTiponivelasignatura;
    @JoinColumn(name = "id_tipoasignatura", referencedColumnName = "id_tipoasignatura")
    @ManyToOne
    private TipoAsignatura idTipoasignatura;
    @JoinColumn(name = "id_planestudio", referencedColumnName = "id_planestudio")
    @ManyToOne
    private PlanEstudio idPlanestudio;
    @OneToMany(mappedBy = "idAsignaturaseriada")
    private List<Asignatura> asignaturaList;
    @JoinColumn(name = "id_asignaturaseriada", referencedColumnName = "id_asignatura")
    @ManyToOne
    private Asignatura idAsignaturaseriada;

    public Asignatura() {
    }

    public Asignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getClaveSep() {
        return claveSep;
    }

    public void setClaveSep(String claveSep) {
        this.claveSep = claveSep;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public List<AlumnoAsignatura> getAlumnoAsignaturaList() {
        return alumnoAsignaturaList;
    }

    public void setAlumnoAsignaturaList(List<AlumnoAsignatura> alumnoAsignaturaList) {
        this.alumnoAsignaturaList = alumnoAsignaturaList;
    }

    public List<AsignacionAsignaturaintegradora> getAsignacionAsignaturaintegradoraList() {
        return asignacionAsignaturaintegradoraList;
    }

    public void setAsignacionAsignaturaintegradoraList(List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList) {
        this.asignacionAsignaturaintegradoraList = asignacionAsignaturaintegradoraList;
    }

    public List<Correspondencia> getCorrespondenciaList() {
        return correspondenciaList;
    }

    public void setCorrespondenciaList(List<Correspondencia> correspondenciaList) {
        this.correspondenciaList = correspondenciaList;
    }

    public List<Correspondencia> getCorrespondenciaList1() {
        return correspondenciaList1;
    }

    public void setCorrespondenciaList1(List<Correspondencia> correspondenciaList1) {
        this.correspondenciaList1 = correspondenciaList1;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<Acta> getActaList() {
        return actaList;
    }

    public void setActaList(List<Acta> actaList) {
        this.actaList = actaList;
    }

    public List<AsignacionAsignaturabanco> getAsignacionAsignaturabancoList() {
        return asignacionAsignaturabancoList;
    }

    public void setAsignacionAsignaturabancoList(List<AsignacionAsignaturabanco> asignacionAsignaturabancoList) {
        this.asignacionAsignaturabancoList = asignacionAsignaturabancoList;
    }

    public List<AsignaturaSeriada> getAsignaturaSeriadaList() {
        return asignaturaSeriadaList;
    }

    public void setAsignaturaSeriadaList(List<AsignaturaSeriada> asignaturaSeriadaList) {
        this.asignaturaSeriadaList = asignaturaSeriadaList;
    }

    public List<AsignaturaSeriada> getAsignaturaSeriadaList1() {
        return asignaturaSeriadaList1;
    }

    public void setAsignaturaSeriadaList1(List<AsignaturaSeriada> asignaturaSeriadaList1) {
        this.asignaturaSeriadaList1 = asignaturaSeriadaList1;
    }

    public List<CalendarioAsignatura> getCalendarioAsignaturaList() {
        return calendarioAsignaturaList;
    }

    public void setCalendarioAsignaturaList(List<CalendarioAsignatura> calendarioAsignaturaList) {
        this.calendarioAsignaturaList = calendarioAsignaturaList;
    }

    public List<BloqueAsignatura> getBloqueAsignaturaList() {
        return bloqueAsignaturaList;
    }

    public void setBloqueAsignaturaList(List<BloqueAsignatura> bloqueAsignaturaList) {
        this.bloqueAsignaturaList = bloqueAsignaturaList;
    }

    public List<ProgramacionAsignatura> getProgramacionAsignaturaList() {
        return programacionAsignaturaList;
    }

    public void setProgramacionAsignaturaList(List<ProgramacionAsignatura> programacionAsignaturaList) {
        this.programacionAsignaturaList = programacionAsignaturaList;
    }

    public TipoNivelasignatura getIdTiponivelasignatura() {
        return idTiponivelasignatura;
    }

    public void setIdTiponivelasignatura(TipoNivelasignatura idTiponivelasignatura) {
        this.idTiponivelasignatura = idTiponivelasignatura;
    }

    public TipoAsignatura getIdTipoasignatura() {
        return idTipoasignatura;
    }

    public void setIdTipoasignatura(TipoAsignatura idTipoasignatura) {
        this.idTipoasignatura = idTipoasignatura;
    }

    public PlanEstudio getIdPlanestudio() {
        return idPlanestudio;
    }

    public void setIdPlanestudio(PlanEstudio idPlanestudio) {
        this.idPlanestudio = idPlanestudio;
    }

    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Asignatura getIdAsignaturaseriada() {
        return idAsignaturaseriada;
    }

    public void setIdAsignaturaseriada(Asignatura idAsignaturaseriada) {
        this.idAsignaturaseriada = idAsignaturaseriada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Asignatura[ idAsignatura=" + idAsignatura + " ]";
    }
    
}
