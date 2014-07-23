/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import com.utez.evaluacion.dao.AsesoriascalendarioJpaController;
import com.utez.evaluacion.dao.GrupoJpaController;
import com.utez.evaluacion.dao.AplicadorJpaController;
import com.utez.evaluacion.dao.ResultadoautoevalJpaController;
import com.utez.evaluacion.dao.ReactivoJpaController;
import com.utez.evaluacion.dao.BitacoraJpaController;
import com.utez.evaluacion.dao.ActividadintegradoraJpaController;
import com.utez.evaluacion.dao.EntregaexamenJpaController;
import com.utez.evaluacion.dao.ProgramacionexamenJpaController;
import com.utez.evaluacion.dao.NominaaplicadorJpaController;
import com.utez.evaluacion.dao.PlantelJpaController;
import com.utez.evaluacion.dao.NominaconceptoJpaController;
import com.utez.evaluacion.dao.RespuestasreactivocolJpaController;
import com.utez.evaluacion.dao.AsignacionactividadJpaController;
import com.utez.evaluacion.dao.TipoexamenJpaController;
import com.utez.evaluacion.dao.AsignacionplanJpaController;
import com.utez.evaluacion.dao.ResultadodireccionesJpaController;
import com.utez.evaluacion.dao.ResultadomaterialJpaController;
import com.utez.evaluacion.dao.ProgramacionautoevalJpaController;
import com.utez.evaluacion.dao.FechasexamJpaController;
import com.utez.evaluacion.dao.ExamenJpaController;
import com.utez.evaluacion.dao.ResultadoencuestaJpaController;
import com.utez.evaluacion.dao.ImagenreactivoJpaController;
import com.utez.evaluacion.dao.RespuestasreactivoJpaController;
import com.utez.evaluacion.dao.CorrespondenciaJpaController;
import com.utez.evaluacion.dao.PlandeestudiosJpaController;
import com.utez.evaluacion.dao.ResultadoasesorJpaController;
import com.utez.evaluacion.dao.AsignacionalumnoJpaController;
import com.utez.evaluacion.dao.BancoJpaController;
import com.utez.evaluacion.dao.AplicacionexamenJpaController;
import com.utez.evaluacion.dao.AsesorJpaController;
import com.utez.evaluacion.dao.UsuariosJpaController;
import com.utez.evaluacion.dao.AsignaaplicadorJpaController;
import com.utez.evaluacion.dao.CalendarioescolarJpaController;
import com.utez.evaluacion.dao.MateriasdeplanJpaController;
import com.utez.evaluacion.dao.MovimientosJpaController;
import com.utez.evaluacion.dao.ExamenextemporaneoJpaController;
import com.utez.evaluacion.dao.RespuestasresultadoJpaController;
import com.utez.evaluacion.dao.ConstantesJpaController;
import com.utez.evaluacion.dao.ArchivomuertoJpaController;
import com.utez.evaluacion.entityMan.EntityManEvaluacion;

/**
 *
 * @author Sergio
 */
public class UtilityEvaluacion {

    private static ActividadintegradoraJpaController actividadintegradoraJpaController = new ActividadintegradoraJpaController(null, EntityManEvaluacion.getInstance());
    private static AplicacionexamenJpaController aplicacionexamenJpaController = new AplicacionexamenJpaController(null, EntityManEvaluacion.getInstance());
    private static AplicadorJpaController aplicadorJpaController = new AplicadorJpaController(null, EntityManEvaluacion.getInstance());
    private static ArchivomuertoJpaController archivomuertoJpaController = new ArchivomuertoJpaController(null, EntityManEvaluacion.getInstance());
    private static AsesorJpaController asesorJpaController = new AsesorJpaController(null, EntityManEvaluacion.getInstance());
    private static AsesoriascalendarioJpaController asesoriascalendarioJpaController = new AsesoriascalendarioJpaController(null, EntityManEvaluacion.getInstance());
    private static AsignaaplicadorJpaController asignaaplicadorJpaController = new AsignaaplicadorJpaController(null, EntityManEvaluacion.getInstance());
    private static AsignacionactividadJpaController asignacionactividadJpaController = new AsignacionactividadJpaController(null, EntityManEvaluacion.getInstance());
    private static AsignacionalumnoJpaController asignacionalumnoJpaController = new AsignacionalumnoJpaController(null, null);
    private static AsignacionplanJpaController asignacionplanJpaController = new AsignacionplanJpaController(null, EntityManEvaluacion.getInstance());
    private static BancoJpaController bancoJpaController = new BancoJpaController(null, EntityManEvaluacion.getInstance());
    private static BitacoraJpaController bitacoraJpaController = new BitacoraJpaController(null, EntityManEvaluacion.getInstance());
    private static CalendarioescolarJpaController calendarioescolarJpaController = new CalendarioescolarJpaController(null, EntityManEvaluacion.getInstance());
    private static ConstantesJpaController constantesJpaController = new ConstantesJpaController(null, EntityManEvaluacion.getInstance());
    private static CorrespondenciaJpaController correspondenciaJpaController = new CorrespondenciaJpaController(null, EntityManEvaluacion.getInstance());
    private static EntregaexamenJpaController entregaexamenJpaController = new EntregaexamenJpaController(null, EntityManEvaluacion.getInstance());
    private static ExamenJpaController examenJpaController = new ExamenJpaController(null, EntityManEvaluacion.getInstance());
    private static ExamenextemporaneoJpaController examenextemporaneoJpaController = new ExamenextemporaneoJpaController(null, EntityManEvaluacion.getInstance());
    private static FechasexamJpaController fechasexamJpaController = new FechasexamJpaController(null, EntityManEvaluacion.getInstance());
    private static GrupoJpaController grupoJpaController = new GrupoJpaController(null, EntityManEvaluacion.getInstance());
    private static ImagenreactivoJpaController imagenreactivoJpaController = new ImagenreactivoJpaController(null, EntityManEvaluacion.getInstance());
    private static MateriasdeplanJpaController materiasdeplanJpaController = new MateriasdeplanJpaController(null, EntityManEvaluacion.getInstance());
    private static MovimientosJpaController movimientosJpaController = new MovimientosJpaController(null, EntityManEvaluacion.getInstance());
    private static NominaaplicadorJpaController nominaaplicadorJpaController = new NominaaplicadorJpaController(null, EntityManEvaluacion.getInstance());
    private static NominaconceptoJpaController nominaconceptoJpaController = new NominaconceptoJpaController(null, EntityManEvaluacion.getInstance());
    private static PlandeestudiosJpaController plandeestudiosJpaController = new PlandeestudiosJpaController(null, EntityManEvaluacion.getInstance());
    private static PlantelJpaController plantelJpaController = new PlantelJpaController(null, EntityManEvaluacion.getInstance());
    private static ProgramacionautoevalJpaController programacionautoevalJpaController = new ProgramacionautoevalJpaController(null, EntityManEvaluacion.getInstance());
    private static ProgramacionexamenJpaController programacionexamenJpaController = new ProgramacionexamenJpaController(null, EntityManEvaluacion.getInstance());
    private static ReactivoJpaController reactivoJpaController = new ReactivoJpaController(null, EntityManEvaluacion.getInstance());
    private static RespuestasreactivoJpaController respuestaReactivoJpaController = new RespuestasreactivoJpaController(null, EntityManEvaluacion.getInstance());
    private static RespuestasreactivocolJpaController respuestasreactivocolJpaController = new RespuestasreactivocolJpaController(null, EntityManEvaluacion.getInstance());
    private static RespuestasresultadoJpaController respuestasresultadoJpaController = new RespuestasresultadoJpaController(null, EntityManEvaluacion.getInstance());
    private static ResultadoasesorJpaController resultadoasesorJpaController = new ResultadoasesorJpaController(null, EntityManEvaluacion.getInstance());
    private static ResultadoautoevalJpaController resultadoautoevalJpaController = new ResultadoautoevalJpaController(null, EntityManEvaluacion.getInstance());
    private static ResultadodireccionesJpaController resultadodireccionesJpaController = new ResultadodireccionesJpaController(null, EntityManEvaluacion.getInstance());
    private static ResultadoencuestaJpaController resultadoencuestaJpaController = new ResultadoencuestaJpaController(null, EntityManEvaluacion.getInstance());
    private static ResultadomaterialJpaController resultadomaterialJpaController = new ResultadomaterialJpaController(null, EntityManEvaluacion.getInstance());
    private static TipoexamenJpaController tipoexamenJpaController = new TipoexamenJpaController(null, EntityManEvaluacion.getInstance());
    private static UsuariosJpaController usuariosJpaController = new UsuariosJpaController(null, EntityManEvaluacion.getInstance());

    public static ActividadintegradoraJpaController getActividadintegradoraJpaController() {
        return actividadintegradoraJpaController;
    }

    public static AplicacionexamenJpaController getAplicacionexamenJpaController() {
        return aplicacionexamenJpaController;
    }

    public static AplicadorJpaController getAplicadorJpaController() {
        return aplicadorJpaController;
    }

    public static ArchivomuertoJpaController getArchivomuertoJpaController() {
        return archivomuertoJpaController;
    }

    public static AsesorJpaController getAsesorJpaController() {
        return asesorJpaController;
    }

    public static AsesoriascalendarioJpaController getAsesoriascalendarioJpaController() {
        return asesoriascalendarioJpaController;
    }

    public static AsignaaplicadorJpaController getAsignaaplicadorJpaController() {
        return asignaaplicadorJpaController;
    }

    public static AsignacionactividadJpaController getAsignacionactividadJpaController() {
        return asignacionactividadJpaController;
    }

    public static AsignacionalumnoJpaController getAsignacionalumnoJpaController() {
        return asignacionalumnoJpaController;
    }

    public static AsignacionplanJpaController getAsignacionplanJpaController() {
        return asignacionplanJpaController;
    }

    public static BancoJpaController getBancoJpaController() {
        return bancoJpaController;
    }

    public static BitacoraJpaController getBitacoraJpaController() {
        return bitacoraJpaController;
    }

    public static CalendarioescolarJpaController getCalendarioescolarJpaController() {
        return calendarioescolarJpaController;
    }

    public static ConstantesJpaController getConstantesJpaController() {
        return constantesJpaController;
    }

    public static CorrespondenciaJpaController getCorrespondenciaJpaController() {
        return correspondenciaJpaController;
    }

    public static EntregaexamenJpaController getEntregaexamenJpaController() {
        return entregaexamenJpaController;
    }

    public static ExamenJpaController getExamenJpaController() {
        return examenJpaController;
    }

    public static ExamenextemporaneoJpaController getExamenextemporaneoJpaController() {
        return examenextemporaneoJpaController;
    }

    public static FechasexamJpaController getFechasexamJpaController() {
        return fechasexamJpaController;
    }

    public static GrupoJpaController getGrupoJpaController() {
        return grupoJpaController;
    }

    public static ImagenreactivoJpaController getImagenreactivoJpaController() {
        return imagenreactivoJpaController;
    }

    public static MateriasdeplanJpaController getMateriasdeplanJpaController() {
        return materiasdeplanJpaController;
    }

    public static MovimientosJpaController getMovimientosJpaController() {
        return movimientosJpaController;
    }

    public static NominaaplicadorJpaController getNominaaplicadorJpaController() {
        return nominaaplicadorJpaController;
    }

    public static NominaconceptoJpaController getNominaconceptoJpaController() {
        return nominaconceptoJpaController;
    }

    public static PlandeestudiosJpaController getPlandeestudiosJpaController() {
        return plandeestudiosJpaController;
    }

    public static PlantelJpaController getPlantelJpaController() {
        return plantelJpaController;
    }

    public static ProgramacionautoevalJpaController getProgramacionautoevalJpaController() {
        return programacionautoevalJpaController;
    }

    public static ProgramacionexamenJpaController getProgramacionexamenJpaController() {
        return programacionexamenJpaController;
    }

    public static ReactivoJpaController getReactivoJpaController() {
        return reactivoJpaController;
    }

    public static RespuestasreactivoJpaController getRespuestaReactivoJpaController() {
        return respuestaReactivoJpaController;
    }

    public static RespuestasreactivocolJpaController getRespuestasreactivocolJpaController() {
        return respuestasreactivocolJpaController;
    }

    public static RespuestasresultadoJpaController getRespuestasresultadoJpaController() {
        return respuestasresultadoJpaController;
    }

    public static ResultadoasesorJpaController getResultadoasesorJpaController() {
        return resultadoasesorJpaController;
    }

    public static ResultadoautoevalJpaController getResultadoautoevalJpaController() {
        return resultadoautoevalJpaController;
    }

    public static ResultadodireccionesJpaController getResultadodireccionesJpaController() {
        return resultadodireccionesJpaController;
    }

    public static ResultadoencuestaJpaController getResultadoencuestaJpaController() {
        return resultadoencuestaJpaController;
    }

    public static ResultadomaterialJpaController getResultadomaterialJpaController() {
        return resultadomaterialJpaController;
    }

    public static TipoexamenJpaController getTipoexamenJpaController() {
        return tipoexamenJpaController;
    }

    public static UsuariosJpaController getUsuariosJpaController() {
        return usuariosJpaController;
    }
}
