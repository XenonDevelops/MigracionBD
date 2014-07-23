/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import com.utez.servEscolares.dao.AcusealumnoexamenJpaController;
import com.utez.servEscolares.dao.AlumnosJpaController;
import com.utez.servEscolares.dao.AsesoresJpaController;
import com.utez.servEscolares.dao.AsesoriascalendarioJpaController;
import com.utez.servEscolares.dao.AsignaciondocumentostitulacionJpaController;
import com.utez.servEscolares.dao.AspirantesJpaController;
import com.utez.servEscolares.dao.BitacoraJpaController;
import com.utez.servEscolares.dao.CalendarioescolarJpaController;
import com.utez.servEscolares.dao.CalificacionesmodulosJpaController;
import com.utez.servEscolares.dao.CalifkardexJpaController;
import com.utez.servEscolares.dao.ClavescarrerasJpaController;
import com.utez.servEscolares.dao.ComplementoalumnoJpaController;
import com.utez.servEscolares.dao.ConstantesJpaController;
import com.utez.servEscolares.dao.ControldocumentosJpaController;
import com.utez.servEscolares.dao.ControlmatriculaJpaController;
import com.utez.servEscolares.dao.CuadernoprogramacionJpaController;
import com.utez.servEscolares.dao.CuerpoactaJpaController;
import com.utez.servEscolares.dao.CursosasesoresJpaController;
import com.utez.servEscolares.dao.DatosescbachJpaController;
import com.utez.servEscolares.dao.DatosescolareslicJpaController;
import com.utez.servEscolares.dao.DatosgeneraleslicJpaController;
import com.utez.servEscolares.dao.DatosgralesmaestriaJpaController;
import com.utez.servEscolares.dao.DatosocupacionalesbachJpaController;
import com.utez.servEscolares.dao.DatosocupacionaleslicJpaController;
import com.utez.servEscolares.dao.DatossocioeconomicosbachJpaController;
import com.utez.servEscolares.dao.DocumentostitulacionJpaController;
import com.utez.servEscolares.dao.EncuestabajasJpaController;
import com.utez.servEscolares.dao.EntradamaterialJpaController;
import com.utez.servEscolares.dao.EstadoexamenJpaController;
import com.utez.servEscolares.dao.ExamenprofesionalJpaController;
import com.utez.servEscolares.dao.FechasexamJpaController;
import com.utez.servEscolares.dao.FoliodiplomaJpaController;
import com.utez.servEscolares.dao.FolioJpaController;
import com.utez.servEscolares.dao.GrupoinduccionJpaController;
import com.utez.servEscolares.dao.ProgramacionrecursadaJpaController;
import com.utez.servEscolares.dao.RegistrodocumentacionbachJpaController;
import com.utez.servEscolares.dao.MateriasopcionJpaController;
import com.utez.servEscolares.dao.ProgramacionopcioncJpaController;
import com.utez.servEscolares.dao.SolicitudmateriacJpaController;
import com.utez.servEscolares.dao.MaterialesJpaController;
import com.utez.servEscolares.dao.PlandeestudiosJpaController;
import com.utez.servEscolares.dao.ImagenesJpaController;
import com.utez.servEscolares.dao.OpcionesestudioJpaController;
import com.utez.servEscolares.dao.RegistrosepJpaController;
import com.utez.servEscolares.dao.OpcionestitulacionJpaController;
import com.utez.servEscolares.dao.RegistrodocumentacionlicJpaController;
import com.utez.servEscolares.dao.RegistroeventosJpaController;
import com.utez.servEscolares.dao.TramitetitulacionJpaController;
import com.utez.servEscolares.dao.KardexJpaController;
import com.utez.servEscolares.dao.SeguimientoJpaController;
import com.utez.servEscolares.dao.RegistroparticipanteJpaController;
import com.utez.servEscolares.dao.GruposJpaController;
import com.utez.servEscolares.dao.RequisitostramitetitulacionJpaController;
import com.utez.servEscolares.dao.SalidamaterialJpaController;
import com.utez.servEscolares.dao.LlenagrupoindJpaController;
import com.utez.servEscolares.dao.RegistrodeactasJpaController;
import com.utez.servEscolares.dao.RegistrodocumentacionmaestriaJpaController;
import com.utez.servEscolares.dao.MateriasdeplanJpaController;
import com.utez.servEscolares.dao.ProgramaseasesoresJpaController;
import com.utez.servEscolares.dao.ServiciosocialJpaController;
import com.utez.servEscolares.dao.TablaencabezadoJpaController;
import com.utez.servEscolares.dao.ProcesotitulacionJpaController;
import com.utez.servEscolares.dao.SolicitudautorizacionJpaController;
import com.utez.servEscolares.dao.ModuloseventosJpaController;
import com.utez.servEscolares.dao.UsuariosJpaController;
import com.utez.servEscolares.dao.ReporteservicioJpaController;
import com.utez.servEscolares.entityMan.EntityManServEscolares;

/**
 *
 * @author Sergio
 */
public class UtilityServiciosEscolares {

    private static AcusealumnoexamenJpaController acusealumnoexamenJpaController = new AcusealumnoexamenJpaController(null, EntityManServEscolares.getInstance());
    private static AlumnosJpaController alumnosJpaController = new AlumnosJpaController(null, EntityManServEscolares.getInstance());
    private static AsesoresJpaController asesoresJpaController = new AsesoresJpaController(null, EntityManServEscolares.getInstance());
    private static AsesoriascalendarioJpaController asesoriascalendarioJpaController = new AsesoriascalendarioJpaController(null, EntityManServEscolares.getInstance());
    private static AsignaciondocumentostitulacionJpaController asignaciondocumentostitulacionJpaController = new AsignaciondocumentostitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static AspirantesJpaController aspirantesJpaController = new AspirantesJpaController(null, EntityManServEscolares.getInstance());
    private static BitacoraJpaController bitacoraJpaController = new BitacoraJpaController(null, EntityManServEscolares.getInstance());
    private static CalendarioescolarJpaController calendarioescolarJpaController = new CalendarioescolarJpaController(null, EntityManServEscolares.getInstance());
    private static CalificacionesmodulosJpaController calificacionesmodulosJpaController = new CalificacionesmodulosJpaController(null, EntityManServEscolares.getInstance());
    private static CalifkardexJpaController califkardexJpaController = new CalifkardexJpaController(null, EntityManServEscolares.getInstance());
    private static ClavescarrerasJpaController clavescarrerasJpaController = new ClavescarrerasJpaController(null, EntityManServEscolares.getInstance());
    private static ComplementoalumnoJpaController complementoalumnoJpaController = new ComplementoalumnoJpaController(null, EntityManServEscolares.getInstance());
    private static ConstantesJpaController constantesJpaController = new ConstantesJpaController(null, EntityManServEscolares.getInstance());
    private static ControldocumentosJpaController controldocumentosJpaController = new ControldocumentosJpaController(null, EntityManServEscolares.getInstance());
    private static ControlmatriculaJpaController controlmatriculaJpaController = new ControlmatriculaJpaController(null, EntityManServEscolares.getInstance());
    private static CuadernoprogramacionJpaController cuadernoprogramacionJpaController = new CuadernoprogramacionJpaController(null, EntityManServEscolares.getInstance());
    private static CuerpoactaJpaController cuerpoactaJpaController = new CuerpoactaJpaController(null, EntityManServEscolares.getInstance());
    private static CursosasesoresJpaController cursosasesoresJpaController = new CursosasesoresJpaController(null, EntityManServEscolares.getInstance());
    private static DatosescbachJpaController datosescbachJpaController = new DatosescbachJpaController(null, EntityManServEscolares.getInstance());
    private static DatosescolareslicJpaController datosescolareslicJpaController = new DatosescolareslicJpaController(null, EntityManServEscolares.getInstance());
    private static DatosgeneraleslicJpaController datosgeneraleslicJpaController = new DatosgeneraleslicJpaController(null, EntityManServEscolares.getInstance());
    private static DatosgralesmaestriaJpaController datosgralesmaestriaJpaController = new DatosgralesmaestriaJpaController(null, EntityManServEscolares.getInstance());
    private static DatosocupacionalesbachJpaController datosocupacionalesbachJpaController = new DatosocupacionalesbachJpaController(null, EntityManServEscolares.getInstance());
    private static DatosocupacionaleslicJpaController datosocupacionaleslicJpaController = new DatosocupacionaleslicJpaController(null, EntityManServEscolares.getInstance());
    private static DatossocioeconomicosbachJpaController datossocioeconomicosbachJpaController = new DatossocioeconomicosbachJpaController(null, EntityManServEscolares.getInstance());
    private static DocumentostitulacionJpaController documentostitulacionJpaController = new DocumentostitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static EncuestabajasJpaController encuestabajasJpaController = new EncuestabajasJpaController(null, EntityManServEscolares.getInstance());
    private static EntradamaterialJpaController entradamaterialJpaController = new EntradamaterialJpaController(null, EntityManServEscolares.getInstance());
    private static EstadoexamenJpaController estadoexamenJpaController = new EstadoexamenJpaController(null, EntityManServEscolares.getInstance());
    private static ExamenprofesionalJpaController examenprofesionalJpaController = new ExamenprofesionalJpaController(null, EntityManServEscolares.getInstance());
    private static FechasexamJpaController fechasexamJpaController = new FechasexamJpaController(null, EntityManServEscolares.getInstance());
    private static FoliodiplomaJpaController foliodiplomaJpaController = new FoliodiplomaJpaController(null, EntityManServEscolares.getInstance());
    private static FolioJpaController folioJpaController = new FolioJpaController(null, EntityManServEscolares.getInstance());
    private static GrupoinduccionJpaController grupoinduccionJpaController = new GrupoinduccionJpaController(null, EntityManServEscolares.getInstance());
    private static GruposJpaController gruposJpaController = new GruposJpaController(null, EntityManServEscolares.getInstance());
    private static ImagenesJpaController imagenesJpaController = new ImagenesJpaController(null, EntityManServEscolares.getInstance());
    private static KardexJpaController kardexJpaController = new KardexJpaController(null, EntityManServEscolares.getInstance());
    private static LlenagrupoindJpaController llenagrupoindJpaController = new LlenagrupoindJpaController(null, EntityManServEscolares.getInstance());
    private static MaterialesJpaController materialesJpaController = new MaterialesJpaController(null, EntityManServEscolares.getInstance());
    private static MateriasdeplanJpaController materiasdeplanJpaController = new MateriasdeplanJpaController(null, EntityManServEscolares.getInstance());
    private static MateriasopcionJpaController materiasopcionJpaController = new MateriasopcionJpaController(null, EntityManServEscolares.getInstance());
    private static ModuloseventosJpaController moduloseventosJpaController = new ModuloseventosJpaController(null, EntityManServEscolares.getInstance());
    private static OpcionesestudioJpaController opcionesestudioJpaController = new OpcionesestudioJpaController(null, EntityManServEscolares.getInstance());
    private static OpcionestitulacionJpaController opcionestitulacionJpaController = new OpcionestitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static PlandeestudiosJpaController plandeestudiosJpaController = new PlandeestudiosJpaController(null, EntityManServEscolares.getInstance());
    private static ProcesotitulacionJpaController procesotitulacionJpaController = new ProcesotitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static ProgramacionopcioncJpaController programacionopcioncJpaController = new ProgramacionopcioncJpaController(null, EntityManServEscolares.getInstance());
    private static ProgramacionrecursadaJpaController programacionrecursadaJpaController = new ProgramacionrecursadaJpaController(null, EntityManServEscolares.getInstance());
    private static ProgramaseasesoresJpaController programaseasesoresJpaController = new ProgramaseasesoresJpaController(null, EntityManServEscolares.getInstance());
    private static RegistrodeactasJpaController registrodeactasJpaController = new RegistrodeactasJpaController(null, EntityManServEscolares.getInstance());
    private static RegistrodocumentacionbachJpaController registrodocumentacionbachJpaController = new RegistrodocumentacionbachJpaController(null, EntityManServEscolares.getInstance());
    private static RegistrodocumentacionlicJpaController registrodocumentacionlicJpaController = new RegistrodocumentacionlicJpaController(null, EntityManServEscolares.getInstance());
    private static RegistrodocumentacionmaestriaJpaController registrodocumentacionmaestriaJpaController = new RegistrodocumentacionmaestriaJpaController(null, EntityManServEscolares.getInstance());
    private static RegistroeventosJpaController registroeventosJpaController = new RegistroeventosJpaController(null, EntityManServEscolares.getInstance());
    private static RegistroparticipanteJpaController registroparticipanteJpaController = new RegistroparticipanteJpaController(null, EntityManServEscolares.getInstance());
    private static RegistrosepJpaController registrosepJpaController = new RegistrosepJpaController(null, EntityManServEscolares.getInstance());
    private static ReporteservicioJpaController reporteservicioJpaController = new ReporteservicioJpaController(null, EntityManServEscolares.getInstance());
    private static RequisitostramitetitulacionJpaController requisitostramitetitulacionJpaController = new RequisitostramitetitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static SalidamaterialJpaController salidamaterialJpaController = new SalidamaterialJpaController(null, EntityManServEscolares.getInstance());
    private static SeguimientoJpaController seguimientoJpaController = new SeguimientoJpaController(null, EntityManServEscolares.getInstance());
    private static ServiciosocialJpaController serviciosocialJpaController = new ServiciosocialJpaController(null, EntityManServEscolares.getInstance());
    private static SolicitudautorizacionJpaController solicitudautorizacionJpaController = new SolicitudautorizacionJpaController(null, EntityManServEscolares.getInstance());
    private static SolicitudmateriacJpaController solicitudmateriacJpaController = new SolicitudmateriacJpaController(null, EntityManServEscolares.getInstance());
    private static TablaencabezadoJpaController tablaencabezadoJpaController = new TablaencabezadoJpaController(null, EntityManServEscolares.getInstance());
    private static TramitetitulacionJpaController tramitetitulacionJpaController = new TramitetitulacionJpaController(null, EntityManServEscolares.getInstance());
    private static UsuariosJpaController usuariosJpaController = new UsuariosJpaController(null, EntityManServEscolares.getInstance());

    public static AcusealumnoexamenJpaController getAcusealumnoexamenJpaController() {
        return acusealumnoexamenJpaController;
    }

    public static AlumnosJpaController getAlumnosJpaController() {
        return alumnosJpaController;
    }

    public static AsesoresJpaController getAsesoresJpaController() {
        return asesoresJpaController;
    }

    public static AsesoriascalendarioJpaController getAsesoriascalendarioJpaController() {
        return asesoriascalendarioJpaController;
    }

    public static AsignaciondocumentostitulacionJpaController getAsignaciondocumentostitulacionJpaController() {
        return asignaciondocumentostitulacionJpaController;
    }

    public static AspirantesJpaController getAspirantesJpaController() {
        return aspirantesJpaController;
    }

    public static BitacoraJpaController getBitacoraJpaController() {
        return bitacoraJpaController;
    }

    public static CalendarioescolarJpaController getCalendarioescolarJpaController() {
        return calendarioescolarJpaController;
    }

    public static CalificacionesmodulosJpaController getCalificacionesmodulosJpaController() {
        return calificacionesmodulosJpaController;
    }

    public static CalifkardexJpaController getCalifkardexJpaController() {
        return califkardexJpaController;
    }

    public static ClavescarrerasJpaController getClavescarrerasJpaController() {
        return clavescarrerasJpaController;
    }

    public static ComplementoalumnoJpaController getComplementoalumnoJpaController() {
        return complementoalumnoJpaController;
    }

    public static ConstantesJpaController getConstantesJpaController() {
        return constantesJpaController;
    }

    public static ControldocumentosJpaController getControldocumentosJpaController() {
        return controldocumentosJpaController;
    }

    public static ControlmatriculaJpaController getControlmatriculaJpaController() {
        return controlmatriculaJpaController;
    }

    public static CuadernoprogramacionJpaController getCuadernoprogramacionJpaController() {
        return cuadernoprogramacionJpaController;
    }

    public static CuerpoactaJpaController getCuerpoactaJpaController() {
        return cuerpoactaJpaController;
    }

    public static CursosasesoresJpaController getCursosasesoresJpaController() {
        return cursosasesoresJpaController;
    }

    public static DatosescbachJpaController getDatosescbachJpaController() {
        return datosescbachJpaController;
    }

    public static DatosescolareslicJpaController getDatosescolareslicJpaController() {
        return datosescolareslicJpaController;
    }

    public static DatosgeneraleslicJpaController getDatosgeneraleslicJpaController() {
        return datosgeneraleslicJpaController;
    }

    public static DatosgralesmaestriaJpaController getDatosgralesmaestriaJpaController() {
        return datosgralesmaestriaJpaController;
    }

    public static DatosocupacionalesbachJpaController getDatosocupacionalesbachJpaController() {
        return datosocupacionalesbachJpaController;
    }

    public static DatosocupacionaleslicJpaController getDatosocupacionaleslicJpaController() {
        return datosocupacionaleslicJpaController;
    }

    public static DatossocioeconomicosbachJpaController getDatossocioeconomicosbachJpaController() {
        return datossocioeconomicosbachJpaController;
    }

    public static DocumentostitulacionJpaController getDocumentostitulacionJpaController() {
        return documentostitulacionJpaController;
    }

    public static EncuestabajasJpaController getEncuestabajasJpaController() {
        return encuestabajasJpaController;
    }

    public static EntradamaterialJpaController getEntradamaterialJpaController() {
        return entradamaterialJpaController;
    }

    public static EstadoexamenJpaController getEstadoexamenJpaController() {
        return estadoexamenJpaController;
    }

    public static ExamenprofesionalJpaController getExamenprofesionalJpaController() {
        return examenprofesionalJpaController;
    }

    public static FechasexamJpaController getFechasexamJpaController() {
        return fechasexamJpaController;
    }

    public static FoliodiplomaJpaController getFoliodiplomaJpaController() {
        return foliodiplomaJpaController;
    }

    public static FolioJpaController getFolioJpaController() {
        return folioJpaController;
    }

    public static GrupoinduccionJpaController getGrupoinduccionJpaController() {
        return grupoinduccionJpaController;
    }

    public static GruposJpaController getGruposJpaController() {
        return gruposJpaController;
    }

    public static ImagenesJpaController getImagenesJpaController() {
        return imagenesJpaController;
    }

    public static KardexJpaController getKardexJpaController() {
        return kardexJpaController;
    }

    public static LlenagrupoindJpaController getLlenagrupoindJpaController() {
        return llenagrupoindJpaController;
    }

    public static MaterialesJpaController getMaterialesJpaController() {
        return materialesJpaController;
    }

    public static MateriasdeplanJpaController getMateriasdeplanJpaController() {
        return materiasdeplanJpaController;
    }

    public static MateriasopcionJpaController getMateriasopcionJpaController() {
        return materiasopcionJpaController;
    }

    public static ModuloseventosJpaController getModuloseventosJpaController() {
        return moduloseventosJpaController;
    }

    public static OpcionesestudioJpaController getOpcionesestudioJpaController() {
        return opcionesestudioJpaController;
    }

    public static OpcionestitulacionJpaController getOpcionestitulacionJpaController() {
        return opcionestitulacionJpaController;
    }

    public static PlandeestudiosJpaController getPlandeestudiosJpaController() {
        return plandeestudiosJpaController;
    }

    public static ProcesotitulacionJpaController getProcesotitulacionJpaController() {
        return procesotitulacionJpaController;
    }

    public static ProgramacionopcioncJpaController getProgramacionopcioncJpaController() {
        return programacionopcioncJpaController;
    }

    public static ProgramacionrecursadaJpaController getProgramacionrecursadaJpaController() {
        return programacionrecursadaJpaController;
    }

    public static ProgramaseasesoresJpaController getProgramaseasesoresJpaController() {
        return programaseasesoresJpaController;
    }

    public static RegistrodeactasJpaController getRegistrodeactasJpaController() {
        return registrodeactasJpaController;
    }

    public static RegistrodocumentacionbachJpaController getRegistrodocumentacionbachJpaController() {
        return registrodocumentacionbachJpaController;
    }

    public static RegistrodocumentacionlicJpaController getRegistrodocumentacionlicJpaController() {
        return registrodocumentacionlicJpaController;
    }

    public static RegistrodocumentacionmaestriaJpaController getRegistrodocumentacionmaestriaJpaController() {
        return registrodocumentacionmaestriaJpaController;
    }

    public static RegistroeventosJpaController getRegistroeventosJpaController() {
        return registroeventosJpaController;
    }

    public static RegistroparticipanteJpaController getRegistroparticipanteJpaController() {
        return registroparticipanteJpaController;
    }

    public static RegistrosepJpaController getRegistrosepJpaController() {
        return registrosepJpaController;
    }

    public static ReporteservicioJpaController getReporteservicioJpaController() {
        return reporteservicioJpaController;
    }

    public static RequisitostramitetitulacionJpaController getRequisitostramitetitulacionJpaController() {
        return requisitostramitetitulacionJpaController;
    }

    public static SalidamaterialJpaController getSalidamaterialJpaController() {
        return salidamaterialJpaController;
    }

    public static SeguimientoJpaController getSeguimientoJpaController() {
        return seguimientoJpaController;
    }

    public static ServiciosocialJpaController getServiciosocialJpaController() {
        return serviciosocialJpaController;
    }

    public static SolicitudautorizacionJpaController getSolicitudautorizacionJpaController() {
        return solicitudautorizacionJpaController;
    }

    public static SolicitudmateriacJpaController getSolicitudmateriacJpaController() {
        return solicitudmateriacJpaController;
    }

    public static TablaencabezadoJpaController getTablaencabezadoJpaController() {
        return tablaencabezadoJpaController;
    }

    public static TramitetitulacionJpaController getTramitetitulacionJpaController() {
        return tramitetitulacionJpaController;
    }

    public static UsuariosJpaController getUsuariosJpaController() {
        return usuariosJpaController;
    }
}
