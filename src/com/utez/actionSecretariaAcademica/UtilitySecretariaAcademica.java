/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.actionSecretariaAcademica;

import com.utez.secAcademica.dao.AdeudoJpaController;
import com.utez.secAcademica.dao.AdministrativoJpaController;
import com.utez.secAcademica.dao.AlumnoJpaController;
import com.utez.secAcademica.dao.AsesorJpaController;
import com.utez.secAcademica.dao.AsesoriaasignaturaJpaController;
import com.utez.secAcademica.dao.AsignaturaJpaController;
import com.utez.secAcademica.dao.BloqueasignaturaJpaController;
import com.utez.secAcademica.dao.CalendarioJpaController;
import com.utez.secAcademica.dao.CalendariorectoriaJpaController;
import com.utez.secAcademica.dao.CicloescolarJpaController;
import com.utez.secAcademica.dao.CuadernoprogramacionJpaController;
import com.utez.secAcademica.dao.FechaasesoriaJpaController;
import com.utez.secAcademica.dao.FechaasesoriabloquelineaJpaController;
import com.utez.secAcademica.dao.FechasexamenJpaController;
import com.utez.secAcademica.dao.FechasexamenopcioncJpaController;
import com.utez.secAcademica.dao.GrupoJpaController;
import com.utez.secAcademica.dao.HistoricogrupoJpaController;
import com.utez.secAcademica.dao.HistoricoopcioncJpaController;
import com.utez.secAcademica.dao.MesopcioncJpaController;
import com.utez.secAcademica.dao.MovimientocieJpaController;
import com.utez.secAcademica.dao.OpcionestudioJpaController;
import com.utez.secAcademica.dao.PeriodoJpaController;
import com.utez.secAcademica.dao.PlanestudiosJpaController;
import com.utez.secAcademica.dao.PlantelJpaController;
import com.utez.secAcademica.dao.ProgramacioncanceladaJpaController;
import com.utez.secAcademica.dao.ProgramacionopcioncJpaController;
import com.utez.secAcademica.dao.ProgramacionraJpaController;
import com.utez.secAcademica.dao.RecursohumanoJpaController;
import com.utez.secAcademica.dao.SolicitudprogramacionJpaController;
import com.utez.secAcademica.dao.SuspensionlaboresJpaController;
import com.utez.secAcademica.dao.TipopagosJpaController;
import com.utez.secAcademica.dao.VacacionesJpaController;
import com.utez.secAcademica.entityMan.EntityManSecAcademica;
import utez.edu.secretariaAcademica.dao.FechasexamenbloqueJpaController;


/**
 *
 * @author Sergio
 *///asesorplan
//kardex
public class UtilitySecretariaAcademica {
    private static AdeudoJpaController adeudoJpaController=new AdeudoJpaController(null,EntityManSecAcademica.getInstance());
    private static AdministrativoJpaController administrativoJpaController=new AdministrativoJpaController(null, EntityManSecAcademica.getInstance());
    private static AlumnoJpaController alumnoJpaController=new AlumnoJpaController(null, EntityManSecAcademica.getInstance());
    private static AsesorJpaController asesorJpaController=new AsesorJpaController(null, EntityManSecAcademica.getInstance());
    private static AsesoriaasignaturaJpaController asesoriaasignaturaJpaController=new AsesoriaasignaturaJpaController(null, EntityManSecAcademica.getInstance());
    private static AsignaturaJpaController asignaturaJpaController=new AsignaturaJpaController(null, EntityManSecAcademica.getInstance());
    private static BloqueasignaturaJpaController bloqueasignaturaJpaController=new BloqueasignaturaJpaController(null, EntityManSecAcademica.getInstance());
    private static CalendariorectoriaJpaController calendariorectoriaJpaController=new CalendariorectoriaJpaController(null, EntityManSecAcademica.getInstance());
    private static CalendarioJpaController calendarioJpaController=new CalendarioJpaController(null, EntityManSecAcademica.getInstance());
    private static CicloescolarJpaController cicloescolarJpaController=new CicloescolarJpaController(null, EntityManSecAcademica.getInstance());
    private static CuadernoprogramacionJpaController cuadernoprogramacionJpaController=new CuadernoprogramacionJpaController(null, EntityManSecAcademica.getInstance());
    private static FechaasesoriaJpaController fechaasesoriaJpaController=new FechaasesoriaJpaController(null, EntityManSecAcademica.getInstance());
    private static FechaasesoriabloquelineaJpaController fechaasesoriabloquelineaJpaController=new FechaasesoriabloquelineaJpaController(null, EntityManSecAcademica.getInstance());
    private static FechasexamenJpaController fechasexamenJpaController=new FechasexamenJpaController(null, EntityManSecAcademica.getInstance());
    private static FechasexamenbloqueJpaController fechasexamenbloqueJpaController=new FechasexamenbloqueJpaController(null, EntityManSecAcademica.getInstance());
    private static FechasexamenopcioncJpaController fechasexamenopcioncJpaController=new FechasexamenopcioncJpaController(null, EntityManSecAcademica.getInstance());
    private static GrupoJpaController grupoJpaController=new GrupoJpaController(null, EntityManSecAcademica.getInstance());
    private static HistoricogrupoJpaController historicogrupoJpaController=new HistoricogrupoJpaController(null, EntityManSecAcademica.getInstance());
    private static HistoricoopcioncJpaController historicoopcioncJpaController=new HistoricoopcioncJpaController(null, EntityManSecAcademica.getInstance());
    private static MesopcioncJpaController mesopcioncJpaController=new MesopcioncJpaController(null, EntityManSecAcademica.getInstance());
    private static MovimientocieJpaController movimientocieJpaController=new MovimientocieJpaController(null, EntityManSecAcademica.getInstance());
    private static OpcionestudioJpaController opcionestudioJpaController=new OpcionestudioJpaController(null, EntityManSecAcademica.getInstance());
    private static PeriodoJpaController periodoJpaController=new PeriodoJpaController(null, EntityManSecAcademica.getInstance());
    private static PlanestudiosJpaController planestudiosJpaController=new PlanestudiosJpaController(null, EntityManSecAcademica.getInstance());
    private static PlantelJpaController plantelJpaController=new PlantelJpaController(null, EntityManSecAcademica.getInstance());
    private static ProgramacioncanceladaJpaController programacioncanceladaJpaController=new ProgramacioncanceladaJpaController(null, EntityManSecAcademica.getInstance());
    private static ProgramacionopcioncJpaController programacionopcioncJpaController=new ProgramacionopcioncJpaController(null, EntityManSecAcademica.getInstance());
    private static ProgramacionraJpaController programacionraJpaController=new ProgramacionraJpaController(null, EntityManSecAcademica.getInstance());
    private static RecursohumanoJpaController recursohumanoJpaController=new RecursohumanoJpaController(null, EntityManSecAcademica.getInstance());
    private static SolicitudprogramacionJpaController solicitudprogramacionJpaController=new SolicitudprogramacionJpaController(null, EntityManSecAcademica.getInstance());
    private static SuspensionlaboresJpaController suspensionlaboresJpaController=new SuspensionlaboresJpaController(null, EntityManSecAcademica.getInstance());
    private static TipopagosJpaController tipopagosJpaController=new TipopagosJpaController(null, EntityManSecAcademica.getInstance());
    private static VacacionesJpaController vacacionesJpaController=new VacacionesJpaController(null, EntityManSecAcademica.getInstance());

    public static AdeudoJpaController getAdeudoJpaController() {
        return adeudoJpaController;
    }

    public static AdministrativoJpaController getAdministrativoJpaController() {
        return administrativoJpaController;
    }

    public static AlumnoJpaController getAlumnoJpaController() {
        return alumnoJpaController;
    }

    public static AsesorJpaController getAsesorJpaController() {
        return asesorJpaController;
    }

    public static AsesoriaasignaturaJpaController getAsesoriaasignaturaJpaController() {
        return asesoriaasignaturaJpaController;
    }

    public static AsignaturaJpaController getAsignaturaJpaController() {
        return asignaturaJpaController;
    }

    public static BloqueasignaturaJpaController getBloqueasignaturaJpaController() {
        return bloqueasignaturaJpaController;
    }

    public static CalendariorectoriaJpaController getCalendariorectoriaJpaController() {
        return calendariorectoriaJpaController;
    }

    public static CalendarioJpaController getCalendarioJpaController() {
        return calendarioJpaController;
    }

    public static CicloescolarJpaController getCicloescolarJpaController() {
        return cicloescolarJpaController;
    }

    public static CuadernoprogramacionJpaController getCuadernoprogramacionJpaController() {
        return cuadernoprogramacionJpaController;
    }

    public static FechaasesoriaJpaController getFechaasesoriaJpaController() {
        return fechaasesoriaJpaController;
    }

    public static FechaasesoriabloquelineaJpaController getFechaasesoriabloquelineaJpaController() {
        return fechaasesoriabloquelineaJpaController;
    }

    public static FechasexamenJpaController getFechasexamenJpaController() {
        return fechasexamenJpaController;
    }

    public static FechasexamenbloqueJpaController getFechasexamenbloqueJpaController() {
        return fechasexamenbloqueJpaController;
    }

    public static FechasexamenopcioncJpaController getFechasexamenopcioncJpaController() {
        return fechasexamenopcioncJpaController;
    }

    public static GrupoJpaController getGrupoJpaController() {
        return grupoJpaController;
    }

    public static HistoricogrupoJpaController getHistoricogrupoJpaController() {
        return historicogrupoJpaController;
    }

    public static HistoricoopcioncJpaController getHistoricoopcioncJpaController() {
        return historicoopcioncJpaController;
    }

    public static MesopcioncJpaController getMesopcioncJpaController() {
        return mesopcioncJpaController;
    }

    public static MovimientocieJpaController getMovimientocieJpaController() {
        return movimientocieJpaController;
    }

    public static OpcionestudioJpaController getOpcionestudioJpaController() {
        return opcionestudioJpaController;
    }

    public static PeriodoJpaController getPeriodoJpaController() {
        return periodoJpaController;
    }

    public static PlanestudiosJpaController getPlanestudiosJpaController() {
        return planestudiosJpaController;
    }

    public static PlantelJpaController getPlantelJpaController() {
        return plantelJpaController;
    }

    public static ProgramacioncanceladaJpaController getProgramacioncanceladaJpaController() {
        return programacioncanceladaJpaController;
    }

    public static ProgramacionopcioncJpaController getProgramacionopcioncJpaController() {
        return programacionopcioncJpaController;
    }

    public static ProgramacionraJpaController getProgramacionraJpaController() {
        return programacionraJpaController;
    }

    public static RecursohumanoJpaController getRecursohumanoJpaController() {
        return recursohumanoJpaController;
    }

    public static SolicitudprogramacionJpaController getSolicitudprogramacionJpaController() {
        return solicitudprogramacionJpaController;
    }

    public static SuspensionlaboresJpaController getSuspensionlaboresJpaController() {
        return suspensionlaboresJpaController;
    }

    public static TipopagosJpaController getTipopagosJpaController() {
        return tipopagosJpaController;
    }

    public static VacacionesJpaController getVacacionesJpaController() {
        return vacacionesJpaController;
    }
}