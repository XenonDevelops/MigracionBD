/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import com.utez.integracion.dao.ModuloJpaController;
import com.utez.integracion.dao.TipoSexoJpaController;
import com.utez.integracion.dao.SuspensionLaboresJpaController;
import com.utez.integracion.dao.TipoEmpresaJpaController;
import com.utez.integracion.dao.TipoTelefonoJpaController;
import com.utez.integracion.dao.TelefonoJpaController;
import com.utez.integracion.dao.PersonaJpaController;
import com.utez.integracion.dao.RecursoJpaController;
import com.utez.integracion.dao.TipoPonderacionevaluacionlineaJpaController;
import com.utez.integracion.dao.ReactivoExamenimpresoJpaController;
import com.utez.integracion.dao.OrdentrabajoTitulacionJpaController;
import com.utez.integracion.dao.FechaInduccionJpaController;
import com.utez.integracion.dao.RespuestaAbiertaJpaController;
import com.utez.integracion.dao.TipoSinodoJpaController;
import com.utez.integracion.dao.AsignacionAutorintegradoraJpaController;
import com.utez.integracion.dao.AspiranteJpaController;
import com.utez.integracion.dao.AsignacionIntegradoragrupoJpaController;
import com.utez.integracion.dao.OrdenTrabajoJpaController;
import com.utez.integracion.dao.SeguimientoAlumnoJpaController;
import com.utez.integracion.dao.ResultadoEvaluacionJpaController;
import com.utez.integracion.dao.UsuarioRolJpaController;
import com.utez.integracion.dao.TipoProgramacionJpaController;
import com.utez.integracion.dao.AsignacionEncuestadocenteJpaController;
import com.utez.integracion.dao.AdministrativoPlantelJpaController;
import com.utez.integracion.dao.TipoMotivoJpaController;
import com.utez.integracion.dao.TipoEstadocivilJpaController;
import com.utez.integracion.dao.AreaCargoJpaController;
import com.utez.integracion.dao.EsquemaAlumnoasignaturaJpaController;
import com.utez.integracion.dao.TipoAmbitomovimientoJpaController;
import com.utez.integracion.dao.SuspensionPlantelJpaController;
import com.utez.integracion.dao.PlanEstudioJpaController;
import com.utez.integracion.dao.ReactivoJpaController;
import com.utez.integracion.dao.AlumnoAsignaturaJpaController;
import com.utez.integracion.dao.TipoAlumnoJpaController;
import com.utez.integracion.dao.OpcionEstudioJpaController;
import com.utez.integracion.dao.IdiomaJpaController;
import com.utez.integracion.dao.AsesorJpaController;
import com.utez.integracion.dao.CargoJpaController;
import com.utez.integracion.dao.AsignacionAutorbancoJpaController;
import com.utez.integracion.dao.CorrespondenciaJpaController;
import com.utez.integracion.dao.PlantelJpaController;
import com.utez.integracion.dao.AsignacionGrupoexamenextemporaneoJpaController;
import com.utez.integracion.dao.ProgramacionAsignaturaJpaController;
import com.utez.integracion.dao.TipoActaJpaController;
import com.utez.integracion.dao.ExamenExtemporaneoJpaController;
import com.utez.integracion.dao.AsignacionRecursohumanodocumentoJpaController;
import com.utez.integracion.dao.GrupoEventoJpaController;
import com.utez.integracion.dao.PosgradoSolicitudautorizacionJpaController;
import com.utez.integracion.dao.HistoricoAsesoriaJpaController;
import com.utez.integracion.dao.VigenciaCalificacionindividualJpaController;
import com.utez.integracion.dao.TipoNivelacademicoJpaController;
import com.utez.integracion.dao.DocumentoJpaController;
import com.utez.integracion.dao.EncuestaDocenteJpaController;
import com.utez.integracion.dao.AsignaturaAsesorJpaController;
import com.utez.integracion.dao.HistoricoCalificacionJpaController;
import com.utez.integracion.dao.CalendarioAsignaturaJpaController;
import com.utez.integracion.dao.SolicitudTitulacionJpaController;
import com.utez.integracion.dao.ProgramacionEspecificaJpaController;
import com.utez.integracion.dao.DocumentoTipotitulacionJpaController;
import com.utez.integracion.dao.ExamenTitulacionJpaController;
import com.utez.integracion.dao.CompromisoDocumentacionJpaController;
import com.utez.integracion.dao.TipoEncuestaJpaController;
import com.utez.integracion.dao.PagoPeriodoJpaController;
import com.utez.integracion.dao.AsignacionGrupoencuestaJpaController;
import com.utez.integracion.dao.TipoAsignaturaJpaController;
import com.utez.integracion.dao.CalificacionModuloJpaController;
import com.utez.integracion.dao.FechaExamenJpaController;
import com.utez.integracion.dao.HistorialCargoJpaController;
import com.utez.integracion.dao.AsignacionSeccionencuestaJpaController;
import com.utez.integracion.dao.RolRecursopermisoJpaController;
import com.utez.integracion.dao.BitacoraNotificacionJpaController;
import com.utez.integracion.dao.EncuestaJpaController;
import com.utez.integracion.dao.FolioCambioscalendarioJpaController;
import com.utez.integracion.dao.AsignacionEvaluacionJpaController;
import com.utez.integracion.dao.CalendarioRectoriaJpaController;
import com.utez.integracion.dao.GeneracionJpaController;
import com.utez.integracion.dao.SinodoExamenJpaController;
import com.utez.integracion.dao.PermisoJpaController;
import com.utez.integracion.dao.MovimientoCieJpaController;
import com.utez.integracion.dao.AsignacionPreguntaseccionJpaController;
import com.utez.integracion.dao.RespuestaImagenJpaController;
import com.utez.integracion.dao.FormacionAcademicaJpaController;
import com.utez.integracion.dao.AsignacionEncuestaalumnoJpaController;
import com.utez.integracion.dao.SeguimientoAspiranteJpaController;
import com.utez.integracion.dao.RespuestaEncuestadocenteJpaController;
import com.utez.integracion.dao.TipoExamenJpaController;
import com.utez.integracion.dao.TipoRespuestaJpaController;
import com.utez.integracion.dao.VacacionJpaController;
import com.utez.integracion.dao.GrupoAlumnoesquemaJpaController;
import com.utez.integracion.dao.AsignacionRespuestapredeterminadapreguntaJpaController;
import com.utez.integracion.dao.SeccionJpaController;
import com.utez.integracion.dao.TipoNotificacionJpaController;
import com.utez.integracion.dao.TipoMovimientoJpaController;
import com.utez.integracion.dao.ProgramacionCanceladaJpaController;
import com.utez.integracion.dao.CalificacionJpaController;
import com.utez.integracion.dao.AsignacionGrupoJpaController;
import com.utez.integracion.dao.AlumnoJpaController;
import com.utez.integracion.dao.GrupoJpaController;
import com.utez.integracion.dao.AsignaturaJpaController;
import com.utez.integracion.dao.CargoTabuladorJpaController;
import com.utez.integracion.dao.CursoJpaController;
import com.utez.integracion.dao.NominaAplicadoresJpaController;
import com.utez.integracion.dao.SolicitudProgramacionJpaController;
import com.utez.integracion.dao.ProgramacionOpcioncasesorJpaController;
import com.utez.integracion.dao.TabuladorConceptoJpaController;
import com.utez.integracion.dao.TipoBajaJpaController;
import com.utez.integracion.dao.RecursoHumanoJpaController;
import com.utez.integracion.dao.CalendarioEscolarJpaController;
import com.utez.integracion.dao.EntregaExamenJpaController;
import com.utez.integracion.dao.ContenidoReactivoJpaController;
import com.utez.integracion.dao.GrupoInduccionJpaController;
import com.utez.integracion.dao.EntidadFederativaJpaController;
import com.utez.integracion.dao.ConvenioJpaController;
import com.utez.integracion.dao.NotificacionCalendarioJpaController;
import com.utez.integracion.dao.TipoReactivoJpaController;
import com.utez.integracion.dao.ArchivoSolicitudprogramacionJpaController;
import com.utez.integracion.dao.RespuestaReactivoJpaController;
import com.utez.integracion.dao.AreaJpaController;
import com.utez.integracion.dao.ReactivoImagenJpaController;
import com.utez.integracion.dao.TipoModalidadJpaController;
import com.utez.integracion.dao.HorarioJpaController;
import com.utez.integracion.dao.AdministrativoJpaController;
import com.utez.integracion.dao.AsignaturaOpcioncJpaController;
import com.utez.integracion.dao.TipoPagoJpaController;
import com.utez.integracion.dao.MunicipioJpaController;
import com.utez.integracion.dao.InstitucionRegistroJpaController;
import com.utez.integracion.dao.EventoJpaController;
import com.utez.integracion.dao.RespuestaAbiertadocenteJpaController;
import com.utez.integracion.dao.DestinatarioNotificacionJpaController;
import com.utez.integracion.dao.CatalogoRecursoJpaController;
import com.utez.integracion.dao.ProgramacionOpcioncJpaController;
import com.utez.integracion.dao.AsignaturaSeriadaJpaController;
import com.utez.integracion.dao.PeriodoJpaController;
import com.utez.integracion.dao.TipoPreguntaencuestaJpaController;
import com.utez.integracion.dao.ServicioSocialJpaController;
import com.utez.integracion.dao.AsignacionAplicadorJpaController;
import com.utez.integracion.dao.ReporteServicioJpaController;
import com.utez.integracion.dao.RespuestaPredeterminadaJpaController;
import com.utez.integracion.dao.AsesorCalificacionJpaController;
import com.utez.integracion.dao.TipoInstitucionseguroJpaController;
import com.utez.integracion.dao.AsignacionAsesorplanJpaController;
import com.utez.integracion.dao.MaterialTitulacionJpaController;
import com.utez.integracion.dao.BloqueAsignaturaJpaController;
import com.utez.integracion.dao.TipoTrabajoJpaController;
import com.utez.integracion.dao.ActaJpaController;
import com.utez.integracion.dao.BitacoraMovimientoJpaController;
import com.utez.integracion.dao.VigenciaCalificacionJpaController;
import com.utez.integracion.dao.TipoOrdentrabajoJpaController;
import com.utez.integracion.dao.SeguroSocialJpaController;
import com.utez.integracion.dao.TipoEventoJpaController;
import com.utez.integracion.dao.EquivalenciaJpaController;
import com.utez.integracion.dao.FechaExamenopcioncJpaController;
import com.utez.integracion.dao.RespuestaEncuestaJpaController;
import com.utez.integracion.dao.AdeudoalumnoEsquematipoexamenJpaController;
import com.utez.integracion.dao.TemaSolicitudautorizacionJpaController;
import com.utez.integracion.dao.AsignacionTipotitulacionplanJpaController;
import com.utez.integracion.dao.ExamenImpresoJpaController;
import com.utez.integracion.dao.ProgramacionJpaController;
import com.utez.integracion.dao.AsignacionAsignaturaintegradoraJpaController;
import com.utez.integracion.dao.SolicitudBajaJpaController;
import com.utez.integracion.dao.TipoNivelasignaturaJpaController;
import com.utez.integracion.dao.AsesoriaJpaController;
import com.utez.integracion.dao.TipoDocumentoJpaController;
import com.utez.integracion.dao.DetalleNominaaplicadoresJpaController;
import com.utez.integracion.dao.RolJpaController;
import com.utez.integracion.dao.ControlDocumentoJpaController;
import com.utez.integracion.dao.AsignacionAmbitomovimientotipoJpaController;
import com.utez.integracion.dao.ExperienciaLaboralJpaController;
import com.utez.integracion.dao.PlanEstudiosexternoJpaController;
import com.utez.integracion.dao.TipoTitulacionJpaController;
import com.utez.integracion.dao.TabuladorJpaController;
import com.utez.integracion.dao.TipoAutorJpaController;
import com.utez.integracion.dao.TipoEvaluacionJpaController;
import com.utez.integracion.dao.ActividadIntegradoraJpaController;
import com.utez.integracion.dao.HistoricoFechaexamenJpaController;
import com.utez.integracion.dao.MesOpcioncJpaController;
import com.utez.integracion.dao.AsignacionTipomovimientorecursoJpaController;
import com.utez.integracion.dao.InscripcionEquivalenciaJpaController;
import com.utez.integracion.dao.OfertaEventoJpaController;
import com.utez.integracion.dao.AsentamientoJpaController;
import com.utez.integracion.dao.HistoricoAsesorasignaturaJpaController;
import com.utez.integracion.dao.AsignacionGrupoinduccionJpaController;
import com.utez.integracion.dao.AsesorTitulartitulacionJpaController;
import com.utez.integracion.dao.FacturaJpaController;
import com.utez.integracion.dao.RespuestaEvaluacionJpaController;
import com.utez.integracion.dao.MotivoAsignaturaasesorJpaController;
import com.utez.integracion.dao.TipoAmbitoJpaController;
import com.utez.integracion.dao.FechaExamenprogramadoJpaController;
import com.utez.integracion.dao.AsignacionAsignaturabancoJpaController;
import com.utez.integracion.dao.HistoricoCalendarioasignaturaJpaController;
import com.utez.integracion.dao.UsuarioJpaController;
import com.utez.integracion.dao.BancoReactivoJpaController;
import com.utez.integracion.dao.ObservacionSolicitudtitulacionJpaController;
import com.utez.integracion.dao.AspiranteOpcionJpaController;
import com.utez.integracion.dao.MovimientoAdeudoJpaController;
import com.utez.integracion.dao.PreguntaJpaController;
import com.utez.integracion.dao.AlumnoConvenioJpaController;
import com.utez.integracion.dao.TipoAdeudoJpaController;
import com.utez.integracion.dao.VacacionPlantelJpaController;
import com.utez.integracion.dao.AsignacionRecursoplantelJpaController;
import com.utez.integracion.dao.AplicadorJpaController;
import com.utez.integracion.dao.TramiteTitulacionJpaController;
import com.utez.integracion.dao.UniversidadExternaJpaController;
import com.utez.integracion.dao.FotografiaJpaController;
import com.utez.integracion.dao.GrupoExamenextemporaneoJpaController;
import com.utez.integracion.dao.AsignacionExamenimpresoJpaController;
import com.utez.integracion.dao.TipoVacacionJpaController;
import com.utez.integracion.dao.ExamenIndividualJpaController;
import com.utez.integracion.dao.TipoNivelidiomaJpaController;
import com.utez.integracion.entityMan.EntityManIntegracion;

/**
 *
 * @author Sergio
 */
public final class UtilityIntegracion {

    private static ActaJpaController actaJpaController = new ActaJpaController(null, EntityManIntegracion.getInstance());
    private static ActividadIntegradoraJpaController actividadIntegradoraJpaController = new ActividadIntegradoraJpaController(null, EntityManIntegracion.getInstance());
    private static AdeudoalumnoEsquematipoexamenJpaController adeudoalumnoEsquematipoexamenJpaController = new AdeudoalumnoEsquematipoexamenJpaController(null, EntityManIntegracion.getInstance());
    private static AdministrativoJpaController administrativoJpaController = new AdministrativoJpaController(null, EntityManIntegracion.getInstance());
    private static AdministrativoPlantelJpaController administrativoPlantelJpaController = new AdministrativoPlantelJpaController(null, EntityManIntegracion.getInstance());
    private static AlumnoAsignaturaJpaController alumnoAsignaturaJpaController = new AlumnoAsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static AlumnoConvenioJpaController alumnoConvenioJpaController = new AlumnoConvenioJpaController(null, EntityManIntegracion.getInstance());
    private static AlumnoJpaController alumnoJpaController = new AlumnoJpaController(null, EntityManIntegracion.getInstance());
    private static AplicadorJpaController aplicadorJpaController = new AplicadorJpaController(null, EntityManIntegracion.getInstance());
    private static ArchivoSolicitudprogramacionJpaController archivoSolicitudprogramacionJpaController = new ArchivoSolicitudprogramacionJpaController(null, EntityManIntegracion.getInstance());
    private static AreaCargoJpaController areaCargoJpaController = new AreaCargoJpaController(null, EntityManIntegracion.getInstance());
    private static AreaJpaController areaJpaController = new AreaJpaController(null, EntityManIntegracion.getInstance());
    private static AsentamientoJpaController asentamientoJpaController = new AsentamientoJpaController(null, EntityManIntegracion.getInstance());
    private static AsesorCalificacionJpaController asesorCalificacionJpaController = new AsesorCalificacionJpaController(null, EntityManIntegracion.getInstance());
    private static AsesoriaJpaController asesoriaJpaController = new AsesoriaJpaController(null, EntityManIntegracion.getInstance());
    private static AsesorJpaController asesorJpaController = new AsesorJpaController(null, EntityManIntegracion.getInstance());
    private static AsesorTitulartitulacionJpaController asesorTitulartitulacionJpaController = new AsesorTitulartitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAmbitomovimientotipoJpaController asignacionAmbitomovimientotipoJpaController = new AsignacionAmbitomovimientotipoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAplicadorJpaController asignacionAplicadorJpaController = new AsignacionAplicadorJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAsesorplanJpaController asignacionAsesorplanJpaController = new AsignacionAsesorplanJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAsignaturabancoJpaController asignacionAsignaturabancoJpaController = new AsignacionAsignaturabancoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAsignaturaintegradoraJpaController asignacionAsignaturaintegradoraJpaController = new AsignacionAsignaturaintegradoraJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAutorbancoJpaController asignacionAutorbancoJpaController = new AsignacionAutorbancoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionAutorintegradoraJpaController asignacionAutorintegradoraJpaController = new AsignacionAutorintegradoraJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionEncuestaalumnoJpaController asignacionEncuestaalumnoJpaController = new AsignacionEncuestaalumnoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionEncuestadocenteJpaController asignacionEncuestadocenteJpaController = new AsignacionEncuestadocenteJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionEvaluacionJpaController asignacionEvaluacionJpaController = new AsignacionEvaluacionJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionExamenimpresoJpaController asignacionExamenimpresoJpaController = new AsignacionExamenimpresoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionGrupoencuestaJpaController asignacionGrupoencuestaJpaController = new AsignacionGrupoencuestaJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionGrupoexamenextemporaneoJpaController asignacionGrupoexamenextemporaneoJpaController = new AsignacionGrupoexamenextemporaneoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionGrupoinduccionJpaController asignacionGrupoinduccionJpaController = new AsignacionGrupoinduccionJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionGrupoJpaController asignacionGrupoJpaController = new AsignacionGrupoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionIntegradoragrupoJpaController asignacionIntegradoragrupoJpaController = new AsignacionIntegradoragrupoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionPreguntaseccionJpaController asignacionPreguntaseccionJpaController = new AsignacionPreguntaseccionJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionRecursohumanodocumentoJpaController asignacionRecursohumanodocumentoJpaController = new AsignacionRecursohumanodocumentoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionRecursoplantelJpaController asignacionRecursoplantelJpaController = new AsignacionRecursoplantelJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionRespuestapredeterminadapreguntaJpaController asignacionRespuestapredeterminadapreguntaJpaController = new AsignacionRespuestapredeterminadapreguntaJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionSeccionencuestaJpaController asignacionSeccionencuestaJpaController = new AsignacionSeccionencuestaJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionTipomovimientorecursoJpaController asignacionTipomovimientorecursoJpaController = new AsignacionTipomovimientorecursoJpaController(null, EntityManIntegracion.getInstance());
    private static AsignacionTipotitulacionplanJpaController asignacionTipotitulacionplanJpaController = new AsignacionTipotitulacionplanJpaController(null, EntityManIntegracion.getInstance());
    private static AsignaturaAsesorJpaController asignaturaAsesorJpaController = new AsignaturaAsesorJpaController(null, EntityManIntegracion.getInstance());
    private static AsignaturaJpaController asignaturaJpaController = new AsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static AsignaturaOpcioncJpaController asignaturaOpcioncJpaController = new AsignaturaOpcioncJpaController(null, EntityManIntegracion.getInstance());
    private static AsignaturaSeriadaJpaController asignaturaSeriadaJpaController = new AsignaturaSeriadaJpaController(null, EntityManIntegracion.getInstance());
    private static AspiranteJpaController aspiranteJpaController = new AspiranteJpaController(null, EntityManIntegracion.getInstance());
    private static AspiranteOpcionJpaController aspiranteOpcionJpaController = new AspiranteOpcionJpaController(null, EntityManIntegracion.getInstance());
    private static BancoReactivoJpaController bancoReactivoJpaController = new BancoReactivoJpaController(null, EntityManIntegracion.getInstance());
    private static BitacoraMovimientoJpaController bitacoraMovimientoJpaController = new BitacoraMovimientoJpaController(null, EntityManIntegracion.getInstance());
    private static BitacoraNotificacionJpaController bitacoraNotificacionJpaController = new BitacoraNotificacionJpaController(null, EntityManIntegracion.getInstance());
    private static BloqueAsignaturaJpaController bloqueAsignaturaJpaController = new BloqueAsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static CalendarioAsignaturaJpaController calendarioAsignaturaJpaController = new CalendarioAsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static CalendarioEscolarJpaController calendarioEscolarJpaController = new CalendarioEscolarJpaController(null, EntityManIntegracion.getInstance());
    private static CalendarioRectoriaJpaController calendarioRectoriaJpaController = new CalendarioRectoriaJpaController(null, EntityManIntegracion.getInstance());
    private static CalificacionJpaController calificacionJpaController = new CalificacionJpaController(null, EntityManIntegracion.getInstance());
    private static CalificacionModuloJpaController calificacionModuloJpaController = new CalificacionModuloJpaController(null, EntityManIntegracion.getInstance());
    private static CargoJpaController cargoJpaController = new CargoJpaController(null, EntityManIntegracion.getInstance());
    private static CargoTabuladorJpaController cargoTabuladorJpaController = new CargoTabuladorJpaController(null, EntityManIntegracion.getInstance());
    private static CatalogoRecursoJpaController catalogoRecursoJpaController = new CatalogoRecursoJpaController(null, EntityManIntegracion.getInstance());
    private static CompromisoDocumentacionJpaController compromisoDocumentacionJpaController = new CompromisoDocumentacionJpaController(null, EntityManIntegracion.getInstance());
    private static ContenidoReactivoJpaController contenidoReactivoJpaController = new ContenidoReactivoJpaController(null, EntityManIntegracion.getInstance());
    private static ControlDocumentoJpaController controlDocumentoJpaController = new ControlDocumentoJpaController(null, EntityManIntegracion.getInstance());
    private static ConvenioJpaController convenioJpaController = new ConvenioJpaController(null, EntityManIntegracion.getInstance());
    private static CorrespondenciaJpaController correspondenciaJpaController = new CorrespondenciaJpaController(null, EntityManIntegracion.getInstance());
    private static CursoJpaController cursoJpaController = new CursoJpaController(null, EntityManIntegracion.getInstance());
    private static DestinatarioNotificacionJpaController destinatarioNotificacionJpaController = new DestinatarioNotificacionJpaController(null, EntityManIntegracion.getInstance());
    private static DetalleNominaaplicadoresJpaController detalleNominaaplicadoresJpaController = new DetalleNominaaplicadoresJpaController(null, EntityManIntegracion.getInstance());
    private static DocumentoJpaController documentoJpaController = new DocumentoJpaController(null, EntityManIntegracion.getInstance());
    private static DocumentoTipotitulacionJpaController documentoTipotitulacionJpaController = new DocumentoTipotitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static EncuestaDocenteJpaController encuestaDocenteJpaController = new EncuestaDocenteJpaController(null, EntityManIntegracion.getInstance());
    private static EncuestaJpaController encuestaJpaController = new EncuestaJpaController(null, EntityManIntegracion.getInstance());
    private static EntidadFederativaJpaController entidadFederativaJpaController = new EntidadFederativaJpaController(null, EntityManIntegracion.getInstance());
    private static EntregaExamenJpaController entregaExamenJpaController = new EntregaExamenJpaController(null, EntityManIntegracion.getInstance());
    private static EquivalenciaJpaController equivalenciaJpaController = new EquivalenciaJpaController(null, EntityManIntegracion.getInstance());
    private static EsquemaAlumnoasignaturaJpaController esquemaAlumnoasignaturaJpaController = new EsquemaAlumnoasignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static EventoJpaController eventoJpaController = new EventoJpaController(null, EntityManIntegracion.getInstance());
    private static ExamenExtemporaneoJpaController examenExtemporaneoJpaController = new ExamenExtemporaneoJpaController(null, EntityManIntegracion.getInstance());
    private static ExamenImpresoJpaController examenImpresoJpaController = new ExamenImpresoJpaController(null, EntityManIntegracion.getInstance());
    private static ExamenIndividualJpaController examenIndividualJpaController = new ExamenIndividualJpaController(null, EntityManIntegracion.getInstance());
    private static ExamenTitulacionJpaController examenTitulacionJpaController = new ExamenTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static ExperienciaLaboralJpaController experienciaLaboralJpaController = new ExperienciaLaboralJpaController(null, EntityManIntegracion.getInstance());
    private static FacturaJpaController facturaJpaController = new FacturaJpaController(null, EntityManIntegracion.getInstance());
    private static FechaExamenJpaController fechaExamenJpaController = new FechaExamenJpaController(null, EntityManIntegracion.getInstance());
    private static FechaExamenopcioncJpaController fechaExamenopcioncJpaController = new FechaExamenopcioncJpaController(null, EntityManIntegracion.getInstance());
    private static FechaExamenprogramadoJpaController fechaExamenprogramadoJpaController = new FechaExamenprogramadoJpaController(null, EntityManIntegracion.getInstance());
    private static FechaInduccionJpaController fechaInduccionJpaController = new FechaInduccionJpaController(null, EntityManIntegracion.getInstance());
    private static FolioCambioscalendarioJpaController folioCambioscalendarioJpaController = new FolioCambioscalendarioJpaController(null, EntityManIntegracion.getInstance());
    private static FormacionAcademicaJpaController formacionAcademicaJpaController = new FormacionAcademicaJpaController(null, EntityManIntegracion.getInstance());
    private static FotografiaJpaController fotografiaJpaController = new FotografiaJpaController(null, EntityManIntegracion.getInstance());
    private static GeneracionJpaController generacionJpaController = new GeneracionJpaController(null, EntityManIntegracion.getInstance());
    private static GrupoAlumnoesquemaJpaController grupoAlumnoesquemaJpaController = new GrupoAlumnoesquemaJpaController(null, EntityManIntegracion.getInstance());
    private static GrupoEventoJpaController grupoEventoJpaController = new GrupoEventoJpaController(null, EntityManIntegracion.getInstance());
    private static GrupoExamenextemporaneoJpaController grupoExamenextemporaneoJpaController = new GrupoExamenextemporaneoJpaController(null, EntityManIntegracion.getInstance());
    private static GrupoInduccionJpaController grupoInduccionJpaController = new GrupoInduccionJpaController(null, EntityManIntegracion.getInstance());
    private static GrupoJpaController grupoJpaController = new GrupoJpaController(null, EntityManIntegracion.getInstance());
    private static HistorialCargoJpaController historialCargoJpaController = new HistorialCargoJpaController(null, EntityManIntegracion.getInstance());
    private static HistoricoAsesorasignaturaJpaController historicoAsesorasignaturaJpaController = new HistoricoAsesorasignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static HistoricoAsesoriaJpaController historicoAsesoriaJpaController = new HistoricoAsesoriaJpaController(null, EntityManIntegracion.getInstance());
    private static HistoricoCalendarioasignaturaJpaController historicoCalendarioasignaturaJpaController = new HistoricoCalendarioasignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static HistoricoCalificacionJpaController historicoCalificacionJpaController = new HistoricoCalificacionJpaController(null, EntityManIntegracion.getInstance());
    private static HistoricoFechaexamenJpaController historicoFechaexamenJpaController = new HistoricoFechaexamenJpaController(null, EntityManIntegracion.getInstance());
    private static HorarioJpaController horarioJpaController = new HorarioJpaController(null, EntityManIntegracion.getInstance());
    private static IdiomaJpaController idiomaJpaController = new IdiomaJpaController(null, EntityManIntegracion.getInstance());
    private static InscripcionEquivalenciaJpaController inscripcionEquivalenciaJpaController = new InscripcionEquivalenciaJpaController(null, EntityManIntegracion.getInstance());
    private static InstitucionRegistroJpaController institucionRegistroJpaController = new InstitucionRegistroJpaController(null, EntityManIntegracion.getInstance());
    private static MaterialTitulacionJpaController materialTitulacionJpaController = new MaterialTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static MesOpcioncJpaController mesOpcioncJpaController = new MesOpcioncJpaController(null, EntityManIntegracion.getInstance());
    private static ModuloJpaController moduloJpaController = new ModuloJpaController(null, EntityManIntegracion.getInstance());
    private static MotivoAsignaturaasesorJpaController motivoAsignaturaasesorJpaController = new MotivoAsignaturaasesorJpaController(null, EntityManIntegracion.getInstance());
    private static MovimientoAdeudoJpaController movimientoAdeudoJpaController = new MovimientoAdeudoJpaController(null, EntityManIntegracion.getInstance());
    private static MovimientoCieJpaController movimientoCieJpaController = new MovimientoCieJpaController(null, EntityManIntegracion.getInstance());
    private static MunicipioJpaController municipioJpaController = new MunicipioJpaController(null, EntityManIntegracion.getInstance());
    private static NominaAplicadoresJpaController nominaAplicadoresJpaController = new NominaAplicadoresJpaController(null, EntityManIntegracion.getInstance());
    private static NotificacionCalendarioJpaController notificacionCalendarioJpaController = new NotificacionCalendarioJpaController(null, EntityManIntegracion.getInstance());
    private static ObservacionSolicitudtitulacionJpaController observacionSolicitudtitulacionJpaController = new ObservacionSolicitudtitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static OfertaEventoJpaController ofertaEventoJpaController = new OfertaEventoJpaController(null, EntityManIntegracion.getInstance());
    private static OpcionEstudioJpaController opcionEstudioJpaController = new OpcionEstudioJpaController(null, EntityManIntegracion.getInstance());
    private static OrdenTrabajoJpaController ordenTrabajoJpaController = new OrdenTrabajoJpaController(null, EntityManIntegracion.getInstance());
    private static OrdentrabajoTitulacionJpaController ordentrabajoTitulacionJpaController = new OrdentrabajoTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static PagoPeriodoJpaController pagoPeriodoJpaController = new PagoPeriodoJpaController(null, EntityManIntegracion.getInstance());
    private static PeriodoJpaController periodoJpaController = new PeriodoJpaController(null, EntityManIntegracion.getInstance());
    private static PermisoJpaController permisoJpaController = new PermisoJpaController(null, EntityManIntegracion.getInstance());
    private static PersonaJpaController personaJpaController = new PersonaJpaController(null, EntityManIntegracion.getInstance());
    private static PlanEstudioJpaController planEstudioJpaController = new PlanEstudioJpaController(null, EntityManIntegracion.getInstance());
    private static PlanEstudiosexternoJpaController planEstudiosexternoJpaController = new PlanEstudiosexternoJpaController(null, EntityManIntegracion.getInstance());
    private static PlantelJpaController plantelJpaController = new PlantelJpaController(null, EntityManIntegracion.getInstance());
    private static PosgradoSolicitudautorizacionJpaController posgradoSolicitudautorizacionJpaController = new PosgradoSolicitudautorizacionJpaController(null, EntityManIntegracion.getInstance());
    private static PreguntaJpaController preguntaJpaController = new PreguntaJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionAsignaturaJpaController programacionAsignaturaJpaController = new ProgramacionAsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionCanceladaJpaController programacionCanceladaJpaController = new ProgramacionCanceladaJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionEspecificaJpaController programacionEspecificaJpaController = new ProgramacionEspecificaJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionJpaController programacionJpaController = new ProgramacionJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionOpcioncasesorJpaController programacionOpcioncasesorJpaController = new ProgramacionOpcioncasesorJpaController(null, EntityManIntegracion.getInstance());
    private static ProgramacionOpcioncJpaController programacionOpcioncJpaController = new ProgramacionOpcioncJpaController(null, EntityManIntegracion.getInstance());
    private static ReactivoExamenimpresoJpaController reactivoExamenimpresoJpaController = new ReactivoExamenimpresoJpaController(null, EntityManIntegracion.getInstance());
    private static ReactivoImagenJpaController reactivoImagenJpaController = new ReactivoImagenJpaController(null, EntityManIntegracion.getInstance());
    private static ReactivoJpaController reactivoJpaController = new ReactivoJpaController(null, EntityManIntegracion.getInstance());
    private static RecursoHumanoJpaController recursoHumanoJpaController = new RecursoHumanoJpaController(null, EntityManIntegracion.getInstance());
    private static RecursoJpaController recursoJpaController = new RecursoJpaController(null, EntityManIntegracion.getInstance());
    private static ReporteServicioJpaController reporteServicioJpaController = new ReporteServicioJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaAbiertadocenteJpaController respuestaAbiertadocenteJpaController = new RespuestaAbiertadocenteJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaAbiertaJpaController respuestaAbiertaJpaController = new RespuestaAbiertaJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaEncuestadocenteJpaController respuestaEncuestadocenteJpaController = new RespuestaEncuestadocenteJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaEncuestaJpaController respuestaEncuestaJpaController = new RespuestaEncuestaJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaEvaluacionJpaController respuestaEvaluacionJpaController = new RespuestaEvaluacionJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaImagenJpaController respuestaImagenJpaController = new RespuestaImagenJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaPredeterminadaJpaController respuestaPredeterminadaJpaController = new RespuestaPredeterminadaJpaController(null, EntityManIntegracion.getInstance());
    private static RespuestaReactivoJpaController respuestaReactivoJpaController = new RespuestaReactivoJpaController(null, EntityManIntegracion.getInstance());
    private static ResultadoEvaluacionJpaController resultadoEvaluacionJpaController = new ResultadoEvaluacionJpaController(null, EntityManIntegracion.getInstance());
    private static RolJpaController rolJpaController = new RolJpaController(null, EntityManIntegracion.getInstance());
    private static RolRecursopermisoJpaController rolRecursopermisoJpaController = new RolRecursopermisoJpaController(null, EntityManIntegracion.getInstance());
    private static SeccionJpaController seccionJpaController = new SeccionJpaController(null, EntityManIntegracion.getInstance());
    private static SeguimientoAlumnoJpaController seguimientoAlumnoJpaController = new SeguimientoAlumnoJpaController(null, EntityManIntegracion.getInstance());
    private static SeguimientoAspiranteJpaController seguimientoAspiranteJpaController = new SeguimientoAspiranteJpaController(null, EntityManIntegracion.getInstance());
    private static SeguroSocialJpaController seguroSocialJpaController = new SeguroSocialJpaController(null, EntityManIntegracion.getInstance());
    private static ServicioSocialJpaController servicioSocialJpaController = new ServicioSocialJpaController(null, EntityManIntegracion.getInstance());
    private static SinodoExamenJpaController sinodoExamenJpaController = new SinodoExamenJpaController(null, EntityManIntegracion.getInstance());
    private static SolicitudBajaJpaController solicitudBajaJpaController = new SolicitudBajaJpaController(null, EntityManIntegracion.getInstance());
    private static SolicitudProgramacionJpaController solicitudProgramacionJpaController = new SolicitudProgramacionJpaController(null, EntityManIntegracion.getInstance());
    private static SolicitudTitulacionJpaController solicitudTitulacionJpaController = new SolicitudTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static SuspensionLaboresJpaController suspensionLaboresJpaController = new SuspensionLaboresJpaController(null, EntityManIntegracion.getInstance());
    private static SuspensionPlantelJpaController suspensionPlantelJpaController = new SuspensionPlantelJpaController(null, EntityManIntegracion.getInstance());
    private static TabuladorConceptoJpaController tabuladorConceptoJpaController = new TabuladorConceptoJpaController(null, EntityManIntegracion.getInstance());
    private static TabuladorJpaController tabuladorJpaController = new TabuladorJpaController(null, EntityManIntegracion.getInstance());
    private static TelefonoJpaController telefonoJpaController = new TelefonoJpaController(null, EntityManIntegracion.getInstance());
    private static TemaSolicitudautorizacionJpaController temaSolicitudautorizacionJpaController = new TemaSolicitudautorizacionJpaController(null, EntityManIntegracion.getInstance());
    private static TipoActaJpaController tipoActaJpaController = new TipoActaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAdeudoJpaController tipoAdeudoJpaController = new TipoAdeudoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAlumnoJpaController tipoAlumnoJpaController = new TipoAlumnoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAmbitoJpaController tipoAmbitoJpaController = new TipoAmbitoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAmbitomovimientoJpaController tipoAmbitomovimientoJpaController = new TipoAmbitomovimientoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAsignaturaJpaController tipoAsignaturaJpaController = new TipoAsignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoAutorJpaController tipoAutorJpaController = new TipoAutorJpaController(null, EntityManIntegracion.getInstance());
    private static TipoBajaJpaController tipoBajaJpaController = new TipoBajaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoDocumentoJpaController tipoDocumentoJpaController = new TipoDocumentoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoEmpresaJpaController tipoEmpresaJpaController = new TipoEmpresaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoEncuestaJpaController tipoEncuestaJpaController = new TipoEncuestaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoEstadocivilJpaController tipoEstadocivilJpaController = new TipoEstadocivilJpaController(null, EntityManIntegracion.getInstance());
    private static TipoEvaluacionJpaController tipoEvaluacionJpaController = new TipoEvaluacionJpaController(null, EntityManIntegracion.getInstance());
    private static TipoEventoJpaController tipoEventoJpaController = new TipoEventoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoExamenJpaController tipoExamenJpaController = new TipoExamenJpaController(null, EntityManIntegracion.getInstance());
    private static TipoInstitucionseguroJpaController tipoInstitucionseguroJpaController = new TipoInstitucionseguroJpaController(null, EntityManIntegracion.getInstance());
    private static TipoModalidadJpaController tipoModalidadJpaController = new TipoModalidadJpaController(null, EntityManIntegracion.getInstance());
    private static TipoMotivoJpaController tipoMotivoJpaController = new TipoMotivoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoMovimientoJpaController tipoMovimientoJpaController = new TipoMovimientoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoNivelacademicoJpaController tipoNivelacademicoJpaController = new TipoNivelacademicoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoNivelasignaturaJpaController tipoNivelasignaturaJpaController = new TipoNivelasignaturaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoNivelidiomaJpaController tipoNivelidiomaJpaController = new TipoNivelidiomaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoNotificacionJpaController tipoNotificacionJpaController = new TipoNotificacionJpaController(null, EntityManIntegracion.getInstance());
    private static TipoOrdentrabajoJpaController tipoOrdentrabajoJpaController = new TipoOrdentrabajoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoPagoJpaController tipoPagoJpaController = new TipoPagoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoPonderacionevaluacionlineaJpaController tipoPonderacionevaluacionlineaJpaController = new TipoPonderacionevaluacionlineaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoPreguntaencuestaJpaController tipoPreguntaencuestaJpaController = new TipoPreguntaencuestaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoProgramacionJpaController tipoProgramacionJpaController = new TipoProgramacionJpaController(null, EntityManIntegracion.getInstance());
    private static TipoReactivoJpaController tipoReactivoJpaController = new TipoReactivoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoRespuestaJpaController tipoRespuestaJpaController = new TipoRespuestaJpaController(null, EntityManIntegracion.getInstance());
    private static TipoSexoJpaController tipoSexoJpaController = new TipoSexoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoSinodoJpaController tipoSinodoJpaController = new TipoSinodoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoTelefonoJpaController tipoTelefonoJpaController = new TipoTelefonoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoTitulacionJpaController tipoTitulacionJpaController = new TipoTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static TipoTrabajoJpaController tipoTrabajoJpaController = new TipoTrabajoJpaController(null, EntityManIntegracion.getInstance());
    private static TipoVacacionJpaController tipoVacacionJpaController = new TipoVacacionJpaController(null, EntityManIntegracion.getInstance());
    private static TramiteTitulacionJpaController tramiteTitulacionJpaController = new TramiteTitulacionJpaController(null, EntityManIntegracion.getInstance());
    private static UniversidadExternaJpaController universidadExternaJpaController = new UniversidadExternaJpaController(null, EntityManIntegracion.getInstance());
    private static UsuarioJpaController usuarioJpaController = new UsuarioJpaController(null, EntityManIntegracion.getInstance());
    private static UsuarioRolJpaController usuarioRolJpaController = new UsuarioRolJpaController(null, EntityManIntegracion.getInstance());
    private static VacacionJpaController vacacionJpaController = new VacacionJpaController(null, EntityManIntegracion.getInstance());
    private static VacacionPlantelJpaController vacacionPlantelJpaController = new VacacionPlantelJpaController(null, EntityManIntegracion.getInstance());
    private static VigenciaCalificacionindividualJpaController vigenciaCalificacionindividualJpaController = new VigenciaCalificacionindividualJpaController(null, EntityManIntegracion.getInstance());
    private static VigenciaCalificacionJpaController vigenciaCalificacionJpaController = new VigenciaCalificacionJpaController(null, EntityManIntegracion.getInstance());

    public static ActaJpaController getActaJpaController() {
        return actaJpaController;
    }

    public static ActividadIntegradoraJpaController getActividadIntegradoraJpaController() {
        return actividadIntegradoraJpaController;
    }

    public static AdeudoalumnoEsquematipoexamenJpaController getAdeudoalumnoEsquematipoexamenJpaController() {
        return adeudoalumnoEsquematipoexamenJpaController;
    }

    public static AdministrativoJpaController getAdministrativoJpaController() {
        return administrativoJpaController;
    }

    public static AdministrativoPlantelJpaController getAdministrativoPlantelJpaController() {
        return administrativoPlantelJpaController;
    }

    public static AlumnoAsignaturaJpaController getAlumnoAsignaturaJpaController() {
        return alumnoAsignaturaJpaController;
    }

    public static AlumnoConvenioJpaController getAlumnoConvenioJpaController() {
        return alumnoConvenioJpaController;
    }

    public static AlumnoJpaController getAlumnoJpaController() {
        return alumnoJpaController;
    }

    public static AplicadorJpaController getAplicadorJpaController() {
        return aplicadorJpaController;
    }

    public static ArchivoSolicitudprogramacionJpaController getArchivoSolicitudprogramacionJpaController() {
        return archivoSolicitudprogramacionJpaController;
    }

    public static AreaCargoJpaController getAreaCargoJpaController() {
        return areaCargoJpaController;
    }

    public static AreaJpaController getAreaJpaController() {
        return areaJpaController;
    }

    public static AsentamientoJpaController getAsentamientoJpaController() {
        return asentamientoJpaController;
    }

    public static AsesorCalificacionJpaController getAsesorCalificacionJpaController() {
        return asesorCalificacionJpaController;
    }

    public static AsesoriaJpaController getAsesoriaJpaController() {
        return asesoriaJpaController;
    }

    public static AsesorJpaController getAsesorJpaController() {
        return asesorJpaController;
    }

    public static AsesorTitulartitulacionJpaController getAsesorTitulartitulacionJpaController() {
        return asesorTitulartitulacionJpaController;
    }

    public static AsignacionAmbitomovimientotipoJpaController getAsignacionAmbitomovimientotipoJpaController() {
        return asignacionAmbitomovimientotipoJpaController;
    }

    public static AsignacionAplicadorJpaController getAsignacionAplicadorJpaController() {
        return asignacionAplicadorJpaController;
    }

    public static AsignacionAsesorplanJpaController getAsignacionAsesorplanJpaController() {
        return asignacionAsesorplanJpaController;
    }

    public static AsignacionAsignaturabancoJpaController getAsignacionAsignaturabancoJpaController() {
        return asignacionAsignaturabancoJpaController;
    }

    public static AsignacionAsignaturaintegradoraJpaController getAsignacionAsignaturaintegradoraJpaController() {
        return asignacionAsignaturaintegradoraJpaController;
    }

    public static AsignacionAutorbancoJpaController getAsignacionAutorbancoJpaController() {
        return asignacionAutorbancoJpaController;
    }

    public static AsignacionAutorintegradoraJpaController getAsignacionAutorintegradoraJpaController() {
        return asignacionAutorintegradoraJpaController;
    }

    public static AsignacionEncuestaalumnoJpaController getAsignacionEncuestaalumnoJpaController() {
        return asignacionEncuestaalumnoJpaController;
    }

    public static AsignacionEncuestadocenteJpaController getAsignacionEncuestadocenteJpaController() {
        return asignacionEncuestadocenteJpaController;
    }

    public static AsignacionEvaluacionJpaController getAsignacionEvaluacionJpaController() {
        return asignacionEvaluacionJpaController;
    }

    public static AsignacionExamenimpresoJpaController getAsignacionExamenimpresoJpaController() {
        return asignacionExamenimpresoJpaController;
    }

    public static AsignacionGrupoencuestaJpaController getAsignacionGrupoencuestaJpaController() {
        return asignacionGrupoencuestaJpaController;
    }

    public static AsignacionGrupoexamenextemporaneoJpaController getAsignacionGrupoexamenextemporaneoJpaController() {
        return asignacionGrupoexamenextemporaneoJpaController;
    }

    public static AsignacionGrupoinduccionJpaController getAsignacionGrupoinduccionJpaController() {
        return asignacionGrupoinduccionJpaController;
    }

    public static AsignacionGrupoJpaController getAsignacionGrupoJpaController() {
        return asignacionGrupoJpaController;
    }

    public static AsignacionIntegradoragrupoJpaController getAsignacionIntegradoragrupoJpaController() {
        return asignacionIntegradoragrupoJpaController;
    }

    public static AsignacionPreguntaseccionJpaController getAsignacionPreguntaseccionJpaController() {
        return asignacionPreguntaseccionJpaController;
    }

    public static AsignacionRecursohumanodocumentoJpaController getAsignacionRecursohumanodocumentoJpaController() {
        return asignacionRecursohumanodocumentoJpaController;
    }

    public static AsignacionRecursoplantelJpaController getAsignacionRecursoplantelJpaController() {
        return asignacionRecursoplantelJpaController;
    }

    public static AsignacionRespuestapredeterminadapreguntaJpaController getAsignacionRespuestapredeterminadapreguntaJpaController() {
        return asignacionRespuestapredeterminadapreguntaJpaController;
    }

    public static AsignacionSeccionencuestaJpaController getAsignacionSeccionencuestaJpaController() {
        return asignacionSeccionencuestaJpaController;
    }

    public static AsignacionTipomovimientorecursoJpaController getAsignacionTipomovimientorecursoJpaController() {
        return asignacionTipomovimientorecursoJpaController;
    }

    public static AsignacionTipotitulacionplanJpaController getAsignacionTipotitulacionplanJpaController() {
        return asignacionTipotitulacionplanJpaController;
    }

    public static AsignaturaAsesorJpaController getAsignaturaAsesorJpaController() {
        return asignaturaAsesorJpaController;
    }

    public static AsignaturaJpaController getAsignaturaJpaController() {
        return asignaturaJpaController;
    }

    public static AsignaturaOpcioncJpaController getAsignaturaOpcioncJpaController() {
        return asignaturaOpcioncJpaController;
    }

    public static AsignaturaSeriadaJpaController getAsignaturaSeriadaJpaController() {
        return asignaturaSeriadaJpaController;
    }

    public static AspiranteJpaController getAspiranteJpaController() {
        return aspiranteJpaController;
    }

    public static AspiranteOpcionJpaController getAspiranteOpcionJpaController() {
        return aspiranteOpcionJpaController;
    }

    public static BancoReactivoJpaController getBancoReactivoJpaController() {
        return bancoReactivoJpaController;
    }

    public static BitacoraMovimientoJpaController getBitacoraMovimientoJpaController() {
        return bitacoraMovimientoJpaController;
    }

    public static BitacoraNotificacionJpaController getBitacoraNotificacionJpaController() {
        return bitacoraNotificacionJpaController;
    }

    public static BloqueAsignaturaJpaController getBloqueAsignaturaJpaController() {
        return bloqueAsignaturaJpaController;
    }

    public static CalendarioAsignaturaJpaController getCalendarioAsignaturaJpaController() {
        return calendarioAsignaturaJpaController;
    }

    public static CalendarioEscolarJpaController getCalendarioEscolarJpaController() {
        return calendarioEscolarJpaController;
    }

    public static CalendarioRectoriaJpaController getCalendarioRectoriaJpaController() {
        return calendarioRectoriaJpaController;
    }

    public static CalificacionJpaController getCalificacionJpaController() {
        return calificacionJpaController;
    }

    public static CalificacionModuloJpaController getCalificacionModuloJpaController() {
        return calificacionModuloJpaController;
    }

    public static CargoJpaController getCargoJpaController() {
        return cargoJpaController;
    }

    public static CargoTabuladorJpaController getCargoTabuladorJpaController() {
        return cargoTabuladorJpaController;
    }

    public static CatalogoRecursoJpaController getCatalogoRecursoJpaController() {
        return catalogoRecursoJpaController;
    }

    public static CompromisoDocumentacionJpaController getCompromisoDocumentacionJpaController() {
        return compromisoDocumentacionJpaController;
    }

    public static ContenidoReactivoJpaController getContenidoReactivoJpaController() {
        return contenidoReactivoJpaController;
    }

    public static ControlDocumentoJpaController getControlDocumentoJpaController() {
        return controlDocumentoJpaController;
    }

    public static ConvenioJpaController getConvenioJpaController() {
        return convenioJpaController;
    }

    public static CorrespondenciaJpaController getCorrespondenciaJpaController() {
        return correspondenciaJpaController;
    }

    public static CursoJpaController getCursoJpaController() {
        return cursoJpaController;
    }

    public static DestinatarioNotificacionJpaController getDestinatarioNotificacionJpaController() {
        return destinatarioNotificacionJpaController;
    }

    public static DetalleNominaaplicadoresJpaController getDetalleNominaaplicadoresJpaController() {
        return detalleNominaaplicadoresJpaController;
    }

    public static DocumentoJpaController getDocumentoJpaController() {
        return documentoJpaController;
    }

    public static DocumentoTipotitulacionJpaController getDocumentoTipotitulacionJpaController() {
        return documentoTipotitulacionJpaController;
    }

    public static EncuestaDocenteJpaController getEncuestaDocenteJpaController() {
        return encuestaDocenteJpaController;
    }

    public static EncuestaJpaController getEncuestaJpaController() {
        return encuestaJpaController;
    }

    public static EntidadFederativaJpaController getEntidadFederativaJpaController() {
        return entidadFederativaJpaController;
    }

    public static EntregaExamenJpaController getEntregaExamenJpaController() {
        return entregaExamenJpaController;
    }

    public static EquivalenciaJpaController getEquivalenciaJpaController() {
        return equivalenciaJpaController;
    }

    public static EsquemaAlumnoasignaturaJpaController getEsquemaAlumnoasignaturaJpaController() {
        return esquemaAlumnoasignaturaJpaController;
    }

    public static EventoJpaController getEventoJpaController() {
        return eventoJpaController;
    }

    public static ExamenExtemporaneoJpaController getExamenExtemporaneoJpaController() {
        return examenExtemporaneoJpaController;
    }

    public static ExamenImpresoJpaController getExamenImpresoJpaController() {
        return examenImpresoJpaController;
    }

    public static ExamenIndividualJpaController getExamenIndividualJpaController() {
        return examenIndividualJpaController;
    }

    public static ExamenTitulacionJpaController getExamenTitulacionJpaController() {
        return examenTitulacionJpaController;
    }

    public static ExperienciaLaboralJpaController getExperienciaLaboralJpaController() {
        return experienciaLaboralJpaController;
    }

    public static FacturaJpaController getFacturaJpaController() {
        return facturaJpaController;
    }

    public static FechaExamenJpaController getFechaExamenJpaController() {
        return fechaExamenJpaController;
    }

    public static FechaExamenopcioncJpaController getFechaExamenopcioncJpaController() {
        return fechaExamenopcioncJpaController;
    }

    public static FechaExamenprogramadoJpaController getFechaExamenprogramadoJpaController() {
        return fechaExamenprogramadoJpaController;
    }

    public static FechaInduccionJpaController getFechaInduccionJpaController() {
        return fechaInduccionJpaController;
    }

    public static FolioCambioscalendarioJpaController getFolioCambioscalendarioJpaController() {
        return folioCambioscalendarioJpaController;
    }

    public static FormacionAcademicaJpaController getFormacionAcademicaJpaController() {
        return formacionAcademicaJpaController;
    }

    public static FotografiaJpaController getFotografiaJpaController() {
        return fotografiaJpaController;
    }

    public static GeneracionJpaController getGeneracionJpaController() {
        return generacionJpaController;
    }

    public static GrupoAlumnoesquemaJpaController getGrupoAlumnoesquemaJpaController() {
        return grupoAlumnoesquemaJpaController;
    }

    public static GrupoEventoJpaController getGrupoEventoJpaController() {
        return grupoEventoJpaController;
    }

    public static GrupoExamenextemporaneoJpaController getGrupoExamenextemporaneoJpaController() {
        return grupoExamenextemporaneoJpaController;
    }

    public static GrupoInduccionJpaController getGrupoInduccionJpaController() {
        return grupoInduccionJpaController;
    }

    public static GrupoJpaController getGrupoJpaController() {
        return grupoJpaController;
    }

    public static HistorialCargoJpaController getHistorialCargoJpaController() {
        return historialCargoJpaController;
    }

    public static HistoricoAsesorasignaturaJpaController getHistoricoAsesorasignaturaJpaController() {
        return historicoAsesorasignaturaJpaController;
    }

    public static HistoricoAsesoriaJpaController getHistoricoAsesoriaJpaController() {
        return historicoAsesoriaJpaController;
    }

    public static HistoricoCalendarioasignaturaJpaController getHistoricoCalendarioasignaturaJpaController() {
        return historicoCalendarioasignaturaJpaController;
    }

    public static HistoricoCalificacionJpaController getHistoricoCalificacionJpaController() {
        return historicoCalificacionJpaController;
    }

    public static HistoricoFechaexamenJpaController getHistoricoFechaexamenJpaController() {
        return historicoFechaexamenJpaController;
    }

    public static HorarioJpaController getHorarioJpaController() {
        return horarioJpaController;
    }

    public static IdiomaJpaController getIdiomaJpaController() {
        return idiomaJpaController;
    }

    public static InscripcionEquivalenciaJpaController getInscripcionEquivalenciaJpaController() {
        return inscripcionEquivalenciaJpaController;
    }

    public static InstitucionRegistroJpaController getInstitucionRegistroJpaController() {
        return institucionRegistroJpaController;
    }

    public static MaterialTitulacionJpaController getMaterialTitulacionJpaController() {
        return materialTitulacionJpaController;
    }

    public static MesOpcioncJpaController getMesOpcioncJpaController() {
        return mesOpcioncJpaController;
    }

    public static ModuloJpaController getModuloJpaController() {
        return moduloJpaController;
    }

    public static MotivoAsignaturaasesorJpaController getMotivoAsignaturaasesorJpaController() {
        return motivoAsignaturaasesorJpaController;
    }

    public static MovimientoAdeudoJpaController getMovimientoAdeudoJpaController() {
        return movimientoAdeudoJpaController;
    }

    public static MovimientoCieJpaController getMovimientoCieJpaController() {
        return movimientoCieJpaController;
    }

    public static MunicipioJpaController getMunicipioJpaController() {
        return municipioJpaController;
    }

    public static NominaAplicadoresJpaController getNominaAplicadoresJpaController() {
        return nominaAplicadoresJpaController;
    }

    public static NotificacionCalendarioJpaController getNotificacionCalendarioJpaController() {
        return notificacionCalendarioJpaController;
    }

    public static ObservacionSolicitudtitulacionJpaController getObservacionSolicitudtitulacionJpaController() {
        return observacionSolicitudtitulacionJpaController;
    }

    public static OfertaEventoJpaController getOfertaEventoJpaController() {
        return ofertaEventoJpaController;
    }

    public static OpcionEstudioJpaController getOpcionEstudioJpaController() {
        return opcionEstudioJpaController;
    }

    public static OrdenTrabajoJpaController getOrdenTrabajoJpaController() {
        return ordenTrabajoJpaController;
    }

    public static OrdentrabajoTitulacionJpaController getOrdentrabajoTitulacionJpaController() {
        return ordentrabajoTitulacionJpaController;
    }

    public static PagoPeriodoJpaController getPagoPeriodoJpaController() {
        return pagoPeriodoJpaController;
    }

    public static PeriodoJpaController getPeriodoJpaController() {
        return periodoJpaController;
    }

    public static PermisoJpaController getPermisoJpaController() {
        return permisoJpaController;
    }

    public static PersonaJpaController getPersonaJpaController() {
        return personaJpaController;
    }

    public static PlanEstudioJpaController getPlanEstudioJpaController() {
        return planEstudioJpaController;
    }

    public static PlanEstudiosexternoJpaController getPlanEstudiosexternoJpaController() {
        return planEstudiosexternoJpaController;
    }

    public static PlantelJpaController getPlantelJpaController() {
        return plantelJpaController;
    }

    public static PosgradoSolicitudautorizacionJpaController getPosgradoSolicitudautorizacionJpaController() {
        return posgradoSolicitudautorizacionJpaController;
    }

    public static PreguntaJpaController getPreguntaJpaController() {
        return preguntaJpaController;
    }

    public static ProgramacionAsignaturaJpaController getProgramacionAsignaturaJpaController() {
        return programacionAsignaturaJpaController;
    }

    public static ProgramacionCanceladaJpaController getProgramacionCanceladaJpaController() {
        return programacionCanceladaJpaController;
    }

    public static ProgramacionEspecificaJpaController getProgramacionEspecificaJpaController() {
        return programacionEspecificaJpaController;
    }

    public static ProgramacionJpaController getProgramacionJpaController() {
        return programacionJpaController;
    }

    public static ProgramacionOpcioncasesorJpaController getProgramacionOpcioncasesorJpaController() {
        return programacionOpcioncasesorJpaController;
    }

    public static ProgramacionOpcioncJpaController getProgramacionOpcioncJpaController() {
        return programacionOpcioncJpaController;
    }

    public static ReactivoExamenimpresoJpaController getReactivoExamenimpresoJpaController() {
        return reactivoExamenimpresoJpaController;
    }

    public static ReactivoImagenJpaController getReactivoImagenJpaController() {
        return reactivoImagenJpaController;
    }

    public static ReactivoJpaController getReactivoJpaController() {
        return reactivoJpaController;
    }

    public static RecursoHumanoJpaController getRecursoHumanoJpaController() {
        return recursoHumanoJpaController;
    }

    public static RecursoJpaController getRecursoJpaController() {
        return recursoJpaController;
    }

    public static ReporteServicioJpaController getReporteServicioJpaController() {
        return reporteServicioJpaController;
    }

    public static RespuestaAbiertadocenteJpaController getRespuestaAbiertadocenteJpaController() {
        return respuestaAbiertadocenteJpaController;
    }

    public static RespuestaAbiertaJpaController getRespuestaAbiertaJpaController() {
        return respuestaAbiertaJpaController;
    }

    public static RespuestaEncuestadocenteJpaController getRespuestaEncuestadocenteJpaController() {
        return respuestaEncuestadocenteJpaController;
    }

    public static RespuestaEncuestaJpaController getRespuestaEncuestaJpaController() {
        return respuestaEncuestaJpaController;
    }

    public static RespuestaEvaluacionJpaController getRespuestaEvaluacionJpaController() {
        return respuestaEvaluacionJpaController;
    }

    public static RespuestaImagenJpaController getRespuestaImagenJpaController() {
        return respuestaImagenJpaController;
    }

    public static RespuestaPredeterminadaJpaController getRespuestaPredeterminadaJpaController() {
        return respuestaPredeterminadaJpaController;
    }

    public static RespuestaReactivoJpaController getRespuestaReactivoJpaController() {
        return respuestaReactivoJpaController;
    }

    public static ResultadoEvaluacionJpaController getResultadoEvaluacionJpaController() {
        return resultadoEvaluacionJpaController;
    }

    public static RolJpaController getRolJpaController() {
        return rolJpaController;
    }

    public static RolRecursopermisoJpaController getRolRecursopermisoJpaController() {
        return rolRecursopermisoJpaController;
    }

    public static SeccionJpaController getSeccionJpaController() {
        return seccionJpaController;
    }

    public static SeguimientoAlumnoJpaController getSeguimientoAlumnoJpaController() {
        return seguimientoAlumnoJpaController;
    }

    public static SeguimientoAspiranteJpaController getSeguimientoAspiranteJpaController() {
        return seguimientoAspiranteJpaController;
    }

    public static SeguroSocialJpaController getSeguroSocialJpaController() {
        return seguroSocialJpaController;
    }

    public static ServicioSocialJpaController getServicioSocialJpaController() {
        return servicioSocialJpaController;
    }

    public static SinodoExamenJpaController getSinodoExamenJpaController() {
        return sinodoExamenJpaController;
    }

    public static SolicitudBajaJpaController getSolicitudBajaJpaController() {
        return solicitudBajaJpaController;
    }

    public static SolicitudProgramacionJpaController getSolicitudProgramacionJpaController() {
        return solicitudProgramacionJpaController;
    }

    public static SolicitudTitulacionJpaController getSolicitudTitulacionJpaController() {
        return solicitudTitulacionJpaController;
    }

    public static SuspensionLaboresJpaController getSuspensionLaboresJpaController() {
        return suspensionLaboresJpaController;
    }

    public static SuspensionPlantelJpaController getSuspensionPlantelJpaController() {
        return suspensionPlantelJpaController;
    }

    public static TabuladorConceptoJpaController getTabuladorConceptoJpaController() {
        return tabuladorConceptoJpaController;
    }

    public static TabuladorJpaController getTabuladorJpaController() {
        return tabuladorJpaController;
    }

    public static TelefonoJpaController getTelefonoJpaController() {
        return telefonoJpaController;
    }

    public static TemaSolicitudautorizacionJpaController getTemaSolicitudautorizacionJpaController() {
        return temaSolicitudautorizacionJpaController;
    }

    public static TipoActaJpaController getTipoActaJpaController() {
        return tipoActaJpaController;
    }

    public static TipoAdeudoJpaController getTipoAdeudoJpaController() {
        return tipoAdeudoJpaController;
    }

    public static TipoAlumnoJpaController getTipoAlumnoJpaController() {
        return tipoAlumnoJpaController;
    }

    public static TipoAmbitoJpaController getTipoAmbitoJpaController() {
        return tipoAmbitoJpaController;
    }

    public static TipoAmbitomovimientoJpaController getTipoAmbitomovimientoJpaController() {
        return tipoAmbitomovimientoJpaController;
    }

    public static TipoAsignaturaJpaController getTipoAsignaturaJpaController() {
        return tipoAsignaturaJpaController;
    }

    public static TipoAutorJpaController getTipoAutorJpaController() {
        return tipoAutorJpaController;
    }

    public static TipoBajaJpaController getTipoBajaJpaController() {
        return tipoBajaJpaController;
    }

    public static TipoDocumentoJpaController getTipoDocumentoJpaController() {
        return tipoDocumentoJpaController;
    }

    public static TipoEmpresaJpaController getTipoEmpresaJpaController() {
        return tipoEmpresaJpaController;
    }

    public static TipoEncuestaJpaController getTipoEncuestaJpaController() {
        return tipoEncuestaJpaController;
    }

    public static TipoEstadocivilJpaController getTipoEstadocivilJpaController() {
        return tipoEstadocivilJpaController;
    }

    public static TipoEvaluacionJpaController getTipoEvaluacionJpaController() {
        return tipoEvaluacionJpaController;
    }

    public static TipoEventoJpaController getTipoEventoJpaController() {
        return tipoEventoJpaController;
    }

    public static TipoExamenJpaController getTipoExamenJpaController() {
        return tipoExamenJpaController;
    }

    public static TipoInstitucionseguroJpaController getTipoInstitucionseguroJpaController() {
        return tipoInstitucionseguroJpaController;
    }

    public static TipoModalidadJpaController getTipoModalidadJpaController() {
        return tipoModalidadJpaController;
    }

    public static TipoMotivoJpaController getTipoMotivoJpaController() {
        return tipoMotivoJpaController;
    }

    public static TipoMovimientoJpaController getTipoMovimientoJpaController() {
        return tipoMovimientoJpaController;
    }

    public static TipoNivelacademicoJpaController getTipoNivelacademicoJpaController() {
        return tipoNivelacademicoJpaController;
    }

    public static TipoNivelasignaturaJpaController getTipoNivelasignaturaJpaController() {
        return tipoNivelasignaturaJpaController;
    }

    public static TipoNivelidiomaJpaController getTipoNivelidiomaJpaController() {
        return tipoNivelidiomaJpaController;
    }

    public static TipoNotificacionJpaController getTipoNotificacionJpaController() {
        return tipoNotificacionJpaController;
    }

    public static TipoOrdentrabajoJpaController getTipoOrdentrabajoJpaController() {
        return tipoOrdentrabajoJpaController;
    }

    public static TipoPagoJpaController getTipoPagoJpaController() {
        return tipoPagoJpaController;
    }

    public static TipoPonderacionevaluacionlineaJpaController getTipoPonderacionevaluacionlineaJpaController() {
        return tipoPonderacionevaluacionlineaJpaController;
    }

    public static TipoPreguntaencuestaJpaController getTipoPreguntaencuestaJpaController() {
        return tipoPreguntaencuestaJpaController;
    }

    public static TipoProgramacionJpaController getTipoProgramacionJpaController() {
        return tipoProgramacionJpaController;
    }

    public static TipoReactivoJpaController getTipoReactivoJpaController() {
        return tipoReactivoJpaController;
    }

    public static TipoRespuestaJpaController getTipoRespuestaJpaController() {
        return tipoRespuestaJpaController;
    }

    public static TipoSexoJpaController getTipoSexoJpaController() {
        return tipoSexoJpaController;
    }

    public static TipoSinodoJpaController getTipoSinodoJpaController() {
        return tipoSinodoJpaController;
    }

    public static TipoTelefonoJpaController getTipoTelefonoJpaController() {
        return tipoTelefonoJpaController;
    }

    public static TipoTitulacionJpaController getTipoTitulacionJpaController() {
        return tipoTitulacionJpaController;
    }

    public static TipoTrabajoJpaController getTipoTrabajoJpaController() {
        return tipoTrabajoJpaController;
    }

    public static TipoVacacionJpaController getTipoVacacionJpaController() {
        return tipoVacacionJpaController;
    }

    public static TramiteTitulacionJpaController getTramiteTitulacionJpaController() {
        return tramiteTitulacionJpaController;
    }

    public static UniversidadExternaJpaController getUniversidadExternaJpaController() {
        return universidadExternaJpaController;
    }

    public static UsuarioJpaController getUsuarioJpaController() {
        return usuarioJpaController;
    }

    public static UsuarioRolJpaController getUsuarioRolJpaController() {
        return usuarioRolJpaController;
    }

    public static VacacionJpaController getVacacionJpaController() {
        return vacacionJpaController;
    }

    public static VacacionPlantelJpaController getVacacionPlantelJpaController() {
        return vacacionPlantelJpaController;
    }

    public static VigenciaCalificacionindividualJpaController getVigenciaCalificacionindividualJpaController() {
        return vigenciaCalificacionindividualJpaController;
    }

    public static VigenciaCalificacionJpaController getVigenciaCalificacionJpaController() {
        return vigenciaCalificacionJpaController;
    }
}
