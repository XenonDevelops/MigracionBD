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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "alumno")
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "estado_alumno")
    private String estadoAlumno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<AlumnoConvenio> alumnoConvenioList;
    @OneToMany(mappedBy = "matricula")
    private List<AsignacionEncuestadocente> asignacionEncuestadocenteList;
    @OneToMany(mappedBy = "matricula")
    private List<AlumnoAsignatura> alumnoAsignaturaList;
    @OneToMany(mappedBy = "matricula")
    private List<AsignacionGrupoinduccion> asignacionGrupoinduccionList;
    @OneToMany(mappedBy = "matricula")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "matricula")
    private List<SolicitudBaja> solicitudBajaList;
    @OneToMany(mappedBy = "matricula")
    private List<ResultadoEvaluacion> resultadoEvaluacionList;
    @OneToMany(mappedBy = "matricula")
    private List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "alumno")
    private MovimientoAdeudo movimientoAdeudo;
    @OneToMany(mappedBy = "matricula")
    private List<SeguimientoAlumno> seguimientoAlumnoList;
    @OneToMany(mappedBy = "matricula")
    private List<MovimientoCie> movimientoCieList;
    @OneToMany(mappedBy = "matricula")
    private List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList;
    @OneToMany(mappedBy = "matricula")
    private List<SolicitudTitulacion> solicitudTitulacionList;
    @OneToMany(mappedBy = "matricula")
    private List<ServicioSocial> servicioSocialList;
    @OneToMany(mappedBy = "matricula")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "matricula")
    private List<InscripcionEquivalencia> inscripcionEquivalenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<AsignacionGrupo> asignacionGrupoList;
    @OneToMany(mappedBy = "matricula")
    private List<ControlDocumento> controlDocumentoList;
    @JoinColumn(name = "id_tipoalumno", referencedColumnName = "id_tipoalumno")
    @ManyToOne
    private TipoAlumno idTipoalumno;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante")
    @ManyToOne
    private Aspirante idAspirante;
    @OneToMany(mappedBy = "matricula")
    private List<CompromisoDocumentacion> compromisoDocumentacionList;
    @OneToMany(mappedBy = "matricula")
    private List<SolicitudProgramacion> solicitudProgramacionList;

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstadoAlumno() {
        return estadoAlumno;
    }

    public void setEstadoAlumno(String estadoAlumno) {
        this.estadoAlumno = estadoAlumno;
    }

    public List<AlumnoConvenio> getAlumnoConvenioList() {
        return alumnoConvenioList;
    }

    public void setAlumnoConvenioList(List<AlumnoConvenio> alumnoConvenioList) {
        this.alumnoConvenioList = alumnoConvenioList;
    }

    public List<AsignacionEncuestadocente> getAsignacionEncuestadocenteList() {
        return asignacionEncuestadocenteList;
    }

    public void setAsignacionEncuestadocenteList(List<AsignacionEncuestadocente> asignacionEncuestadocenteList) {
        this.asignacionEncuestadocenteList = asignacionEncuestadocenteList;
    }

    public List<AlumnoAsignatura> getAlumnoAsignaturaList() {
        return alumnoAsignaturaList;
    }

    public void setAlumnoAsignaturaList(List<AlumnoAsignatura> alumnoAsignaturaList) {
        this.alumnoAsignaturaList = alumnoAsignaturaList;
    }

    public List<AsignacionGrupoinduccion> getAsignacionGrupoinduccionList() {
        return asignacionGrupoinduccionList;
    }

    public void setAsignacionGrupoinduccionList(List<AsignacionGrupoinduccion> asignacionGrupoinduccionList) {
        this.asignacionGrupoinduccionList = asignacionGrupoinduccionList;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<SolicitudBaja> getSolicitudBajaList() {
        return solicitudBajaList;
    }

    public void setSolicitudBajaList(List<SolicitudBaja> solicitudBajaList) {
        this.solicitudBajaList = solicitudBajaList;
    }

    public List<ResultadoEvaluacion> getResultadoEvaluacionList() {
        return resultadoEvaluacionList;
    }

    public void setResultadoEvaluacionList(List<ResultadoEvaluacion> resultadoEvaluacionList) {
        this.resultadoEvaluacionList = resultadoEvaluacionList;
    }

    public List<AsignacionEncuestaalumno> getAsignacionEncuestaalumnoList() {
        return asignacionEncuestaalumnoList;
    }

    public void setAsignacionEncuestaalumnoList(List<AsignacionEncuestaalumno> asignacionEncuestaalumnoList) {
        this.asignacionEncuestaalumnoList = asignacionEncuestaalumnoList;
    }

    public MovimientoAdeudo getMovimientoAdeudo() {
        return movimientoAdeudo;
    }

    public void setMovimientoAdeudo(MovimientoAdeudo movimientoAdeudo) {
        this.movimientoAdeudo = movimientoAdeudo;
    }

    public List<SeguimientoAlumno> getSeguimientoAlumnoList() {
        return seguimientoAlumnoList;
    }

    public void setSeguimientoAlumnoList(List<SeguimientoAlumno> seguimientoAlumnoList) {
        this.seguimientoAlumnoList = seguimientoAlumnoList;
    }

    public List<MovimientoCie> getMovimientoCieList() {
        return movimientoCieList;
    }

    public void setMovimientoCieList(List<MovimientoCie> movimientoCieList) {
        this.movimientoCieList = movimientoCieList;
    }

    public List<AsignacionGrupoexamenextemporaneo> getAsignacionGrupoexamenextemporaneoList() {
        return asignacionGrupoexamenextemporaneoList;
    }

    public void setAsignacionGrupoexamenextemporaneoList(List<AsignacionGrupoexamenextemporaneo> asignacionGrupoexamenextemporaneoList) {
        this.asignacionGrupoexamenextemporaneoList = asignacionGrupoexamenextemporaneoList;
    }

    public List<SolicitudTitulacion> getSolicitudTitulacionList() {
        return solicitudTitulacionList;
    }

    public void setSolicitudTitulacionList(List<SolicitudTitulacion> solicitudTitulacionList) {
        this.solicitudTitulacionList = solicitudTitulacionList;
    }

    public List<ServicioSocial> getServicioSocialList() {
        return servicioSocialList;
    }

    public void setServicioSocialList(List<ServicioSocial> servicioSocialList) {
        this.servicioSocialList = servicioSocialList;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public List<InscripcionEquivalencia> getInscripcionEquivalenciaList() {
        return inscripcionEquivalenciaList;
    }

    public void setInscripcionEquivalenciaList(List<InscripcionEquivalencia> inscripcionEquivalenciaList) {
        this.inscripcionEquivalenciaList = inscripcionEquivalenciaList;
    }

    public List<AsignacionGrupo> getAsignacionGrupoList() {
        return asignacionGrupoList;
    }

    public void setAsignacionGrupoList(List<AsignacionGrupo> asignacionGrupoList) {
        this.asignacionGrupoList = asignacionGrupoList;
    }

    public List<ControlDocumento> getControlDocumentoList() {
        return controlDocumentoList;
    }

    public void setControlDocumentoList(List<ControlDocumento> controlDocumentoList) {
        this.controlDocumentoList = controlDocumentoList;
    }

    public TipoAlumno getIdTipoalumno() {
        return idTipoalumno;
    }

    public void setIdTipoalumno(TipoAlumno idTipoalumno) {
        this.idTipoalumno = idTipoalumno;
    }

    public Aspirante getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirante idAspirante) {
        this.idAspirante = idAspirante;
    }

    public List<CompromisoDocumentacion> getCompromisoDocumentacionList() {
        return compromisoDocumentacionList;
    }

    public void setCompromisoDocumentacionList(List<CompromisoDocumentacion> compromisoDocumentacionList) {
        this.compromisoDocumentacionList = compromisoDocumentacionList;
    }

    public List<SolicitudProgramacion> getSolicitudProgramacionList() {
        return solicitudProgramacionList;
    }

    public void setSolicitudProgramacionList(List<SolicitudProgramacion> solicitudProgramacionList) {
        this.solicitudProgramacionList = solicitudProgramacionList;
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
        return "com.utez.integracion.entity.Alumno[ matricula=" + matricula + " ]";
    }
    
}
