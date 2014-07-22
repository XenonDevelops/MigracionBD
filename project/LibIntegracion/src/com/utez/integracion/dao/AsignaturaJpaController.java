/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoNivelasignatura;
import com.utez.integracion.entity.TipoAsignatura;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.Asignatura;
import com.utez.integracion.entity.AlumnoAsignatura;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionAsignaturaintegradora;
import com.utez.integracion.entity.Correspondencia;
import com.utez.integracion.entity.ExamenExtemporaneo;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.AsignacionAsignaturabanco;
import com.utez.integracion.entity.AsignaturaSeriada;
import com.utez.integracion.entity.CalendarioAsignatura;
import com.utez.integracion.entity.BloqueAsignatura;
import com.utez.integracion.entity.ProgramacionAsignatura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getAlumnoAsignaturaList() == null) {
            asignatura.setAlumnoAsignaturaList(new ArrayList<AlumnoAsignatura>());
        }
        if (asignatura.getAsignacionAsignaturaintegradoraList() == null) {
            asignatura.setAsignacionAsignaturaintegradoraList(new ArrayList<AsignacionAsignaturaintegradora>());
        }
        if (asignatura.getCorrespondenciaList() == null) {
            asignatura.setCorrespondenciaList(new ArrayList<Correspondencia>());
        }
        if (asignatura.getCorrespondenciaList1() == null) {
            asignatura.setCorrespondenciaList1(new ArrayList<Correspondencia>());
        }
        if (asignatura.getExamenExtemporaneoList() == null) {
            asignatura.setExamenExtemporaneoList(new ArrayList<ExamenExtemporaneo>());
        }
        if (asignatura.getActaList() == null) {
            asignatura.setActaList(new ArrayList<Acta>());
        }
        if (asignatura.getAsignacionAsignaturabancoList() == null) {
            asignatura.setAsignacionAsignaturabancoList(new ArrayList<AsignacionAsignaturabanco>());
        }
        if (asignatura.getAsignaturaSeriadaList() == null) {
            asignatura.setAsignaturaSeriadaList(new ArrayList<AsignaturaSeriada>());
        }
        if (asignatura.getAsignaturaSeriadaList1() == null) {
            asignatura.setAsignaturaSeriadaList1(new ArrayList<AsignaturaSeriada>());
        }
        if (asignatura.getCalendarioAsignaturaList() == null) {
            asignatura.setCalendarioAsignaturaList(new ArrayList<CalendarioAsignatura>());
        }
        if (asignatura.getBloqueAsignaturaList() == null) {
            asignatura.setBloqueAsignaturaList(new ArrayList<BloqueAsignatura>());
        }
        if (asignatura.getProgramacionAsignaturaList() == null) {
            asignatura.setProgramacionAsignaturaList(new ArrayList<ProgramacionAsignatura>());
        }
        if (asignatura.getAsignaturaList() == null) {
            asignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoNivelasignatura idTiponivelasignatura = asignatura.getIdTiponivelasignatura();
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura = em.getReference(idTiponivelasignatura.getClass(), idTiponivelasignatura.getIdTiponivelasignatura());
                asignatura.setIdTiponivelasignatura(idTiponivelasignatura);
            }
            TipoAsignatura idTipoasignatura = asignatura.getIdTipoasignatura();
            if (idTipoasignatura != null) {
                idTipoasignatura = em.getReference(idTipoasignatura.getClass(), idTipoasignatura.getIdTipoasignatura());
                asignatura.setIdTipoasignatura(idTipoasignatura);
            }
            PlanEstudio idPlanestudio = asignatura.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio = em.getReference(idPlanestudio.getClass(), idPlanestudio.getIdPlanestudio());
                asignatura.setIdPlanestudio(idPlanestudio);
            }
            Asignatura idAsignaturaseriada = asignatura.getIdAsignaturaseriada();
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada = em.getReference(idAsignaturaseriada.getClass(), idAsignaturaseriada.getIdAsignatura());
                asignatura.setIdAsignaturaseriada(idAsignaturaseriada);
            }
            List<AlumnoAsignatura> attachedAlumnoAsignaturaList = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignaturaToAttach : asignatura.getAlumnoAsignaturaList()) {
                alumnoAsignaturaListAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaList.add(alumnoAsignaturaListAlumnoAsignaturaToAttach);
            }
            asignatura.setAlumnoAsignaturaList(attachedAlumnoAsignaturaList);
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraList = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach : asignatura.getAsignacionAsignaturaintegradoraList()) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraList.add(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradoraToAttach);
            }
            asignatura.setAsignacionAsignaturaintegradoraList(attachedAsignacionAsignaturaintegradoraList);
            List<Correspondencia> attachedCorrespondenciaList = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaListCorrespondenciaToAttach : asignatura.getCorrespondenciaList()) {
                correspondenciaListCorrespondenciaToAttach = em.getReference(correspondenciaListCorrespondenciaToAttach.getClass(), correspondenciaListCorrespondenciaToAttach.getCorrespondenciaPK());
                attachedCorrespondenciaList.add(correspondenciaListCorrespondenciaToAttach);
            }
            asignatura.setCorrespondenciaList(attachedCorrespondenciaList);
            List<Correspondencia> attachedCorrespondenciaList1 = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaList1CorrespondenciaToAttach : asignatura.getCorrespondenciaList1()) {
                correspondenciaList1CorrespondenciaToAttach = em.getReference(correspondenciaList1CorrespondenciaToAttach.getClass(), correspondenciaList1CorrespondenciaToAttach.getCorrespondenciaPK());
                attachedCorrespondenciaList1.add(correspondenciaList1CorrespondenciaToAttach);
            }
            asignatura.setCorrespondenciaList1(attachedCorrespondenciaList1);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoList = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneoToAttach : asignatura.getExamenExtemporaneoList()) {
                examenExtemporaneoListExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoList.add(examenExtemporaneoListExamenExtemporaneoToAttach);
            }
            asignatura.setExamenExtemporaneoList(attachedExamenExtemporaneoList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : asignatura.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            asignatura.setActaList(attachedActaList);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoList = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach : asignatura.getAsignacionAsignaturabancoList()) {
                asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoList.add(asignacionAsignaturabancoListAsignacionAsignaturabancoToAttach);
            }
            asignatura.setAsignacionAsignaturabancoList(attachedAsignacionAsignaturabancoList);
            List<AsignaturaSeriada> attachedAsignaturaSeriadaList = new ArrayList<AsignaturaSeriada>();
            for (AsignaturaSeriada asignaturaSeriadaListAsignaturaSeriadaToAttach : asignatura.getAsignaturaSeriadaList()) {
                asignaturaSeriadaListAsignaturaSeriadaToAttach = em.getReference(asignaturaSeriadaListAsignaturaSeriadaToAttach.getClass(), asignaturaSeriadaListAsignaturaSeriadaToAttach.getAsignaturaSeriadaPK());
                attachedAsignaturaSeriadaList.add(asignaturaSeriadaListAsignaturaSeriadaToAttach);
            }
            asignatura.setAsignaturaSeriadaList(attachedAsignaturaSeriadaList);
            List<AsignaturaSeriada> attachedAsignaturaSeriadaList1 = new ArrayList<AsignaturaSeriada>();
            for (AsignaturaSeriada asignaturaSeriadaList1AsignaturaSeriadaToAttach : asignatura.getAsignaturaSeriadaList1()) {
                asignaturaSeriadaList1AsignaturaSeriadaToAttach = em.getReference(asignaturaSeriadaList1AsignaturaSeriadaToAttach.getClass(), asignaturaSeriadaList1AsignaturaSeriadaToAttach.getAsignaturaSeriadaPK());
                attachedAsignaturaSeriadaList1.add(asignaturaSeriadaList1AsignaturaSeriadaToAttach);
            }
            asignatura.setAsignaturaSeriadaList1(attachedAsignaturaSeriadaList1);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaList = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignaturaToAttach : asignatura.getCalendarioAsignaturaList()) {
                calendarioAsignaturaListCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaList.add(calendarioAsignaturaListCalendarioAsignaturaToAttach);
            }
            asignatura.setCalendarioAsignaturaList(attachedCalendarioAsignaturaList);
            List<BloqueAsignatura> attachedBloqueAsignaturaList = new ArrayList<BloqueAsignatura>();
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignaturaToAttach : asignatura.getBloqueAsignaturaList()) {
                bloqueAsignaturaListBloqueAsignaturaToAttach = em.getReference(bloqueAsignaturaListBloqueAsignaturaToAttach.getClass(), bloqueAsignaturaListBloqueAsignaturaToAttach.getIdBloqueasignatura());
                attachedBloqueAsignaturaList.add(bloqueAsignaturaListBloqueAsignaturaToAttach);
            }
            asignatura.setBloqueAsignaturaList(attachedBloqueAsignaturaList);
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaList = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignaturaToAttach : asignatura.getProgramacionAsignaturaList()) {
                programacionAsignaturaListProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaList.add(programacionAsignaturaListProgramacionAsignaturaToAttach);
            }
            asignatura.setProgramacionAsignaturaList(attachedProgramacionAsignaturaList);
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : asignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdAsignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            asignatura.setAsignaturaList(attachedAsignaturaList);
            em.persist(asignatura);
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura.getAsignaturaList().add(asignatura);
                idTiponivelasignatura = em.merge(idTiponivelasignatura);
            }
            if (idTipoasignatura != null) {
                idTipoasignatura.getAsignaturaList().add(asignatura);
                idTipoasignatura = em.merge(idTipoasignatura);
            }
            if (idPlanestudio != null) {
                idPlanestudio.getAsignaturaList().add(asignatura);
                idPlanestudio = em.merge(idPlanestudio);
            }
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada.getAsignaturaList().add(asignatura);
                idAsignaturaseriada = em.merge(idAsignaturaseriada);
            }
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : asignatura.getAlumnoAsignaturaList()) {
                Asignatura oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = alumnoAsignaturaListAlumnoAsignatura.getIdAsignatura();
                alumnoAsignaturaListAlumnoAsignatura.setIdAsignatura(asignatura);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
                if (oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura != null) {
                    oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListAlumnoAsignatura);
                    oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura = em.merge(oldIdAsignaturaOfAlumnoAsignaturaListAlumnoAsignatura);
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : asignatura.getAsignacionAsignaturaintegradoraList()) {
                Asignatura oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getIdAsignatura();
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdAsignatura(asignatura);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                if (oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora != null) {
                    oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                    oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(oldIdAsignaturaOfAsignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
                }
            }
            for (Correspondencia correspondenciaListCorrespondencia : asignatura.getCorrespondenciaList()) {
                Asignatura oldAsignaturaOfCorrespondenciaListCorrespondencia = correspondenciaListCorrespondencia.getAsignatura();
                correspondenciaListCorrespondencia.setAsignatura(asignatura);
                correspondenciaListCorrespondencia = em.merge(correspondenciaListCorrespondencia);
                if (oldAsignaturaOfCorrespondenciaListCorrespondencia != null) {
                    oldAsignaturaOfCorrespondenciaListCorrespondencia.getCorrespondenciaList().remove(correspondenciaListCorrespondencia);
                    oldAsignaturaOfCorrespondenciaListCorrespondencia = em.merge(oldAsignaturaOfCorrespondenciaListCorrespondencia);
                }
            }
            for (Correspondencia correspondenciaList1Correspondencia : asignatura.getCorrespondenciaList1()) {
                Asignatura oldAsignatura1OfCorrespondenciaList1Correspondencia = correspondenciaList1Correspondencia.getAsignatura1();
                correspondenciaList1Correspondencia.setAsignatura1(asignatura);
                correspondenciaList1Correspondencia = em.merge(correspondenciaList1Correspondencia);
                if (oldAsignatura1OfCorrespondenciaList1Correspondencia != null) {
                    oldAsignatura1OfCorrespondenciaList1Correspondencia.getCorrespondenciaList1().remove(correspondenciaList1Correspondencia);
                    oldAsignatura1OfCorrespondenciaList1Correspondencia = em.merge(oldAsignatura1OfCorrespondenciaList1Correspondencia);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : asignatura.getExamenExtemporaneoList()) {
                Asignatura oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo = examenExtemporaneoListExamenExtemporaneo.getIdAsignatura();
                examenExtemporaneoListExamenExtemporaneo.setIdAsignatura(asignatura);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
                if (oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo != null) {
                    oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListExamenExtemporaneo);
                    oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo = em.merge(oldIdAsignaturaOfExamenExtemporaneoListExamenExtemporaneo);
                }
            }
            for (Acta actaListActa : asignatura.getActaList()) {
                Asignatura oldIdAsignaturaOfActaListActa = actaListActa.getIdAsignatura();
                actaListActa.setIdAsignatura(asignatura);
                actaListActa = em.merge(actaListActa);
                if (oldIdAsignaturaOfActaListActa != null) {
                    oldIdAsignaturaOfActaListActa.getActaList().remove(actaListActa);
                    oldIdAsignaturaOfActaListActa = em.merge(oldIdAsignaturaOfActaListActa);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : asignatura.getAsignacionAsignaturabancoList()) {
                Asignatura oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = asignacionAsignaturabancoListAsignacionAsignaturabanco.getIdAsignatura();
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdAsignatura(asignatura);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                if (oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco != null) {
                    oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListAsignacionAsignaturabanco);
                    oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(oldIdAsignaturaOfAsignacionAsignaturabancoListAsignacionAsignaturabanco);
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaListAsignaturaSeriada : asignatura.getAsignaturaSeriadaList()) {
                Asignatura oldAsignaturaOfAsignaturaSeriadaListAsignaturaSeriada = asignaturaSeriadaListAsignaturaSeriada.getAsignatura();
                asignaturaSeriadaListAsignaturaSeriada.setAsignatura(asignatura);
                asignaturaSeriadaListAsignaturaSeriada = em.merge(asignaturaSeriadaListAsignaturaSeriada);
                if (oldAsignaturaOfAsignaturaSeriadaListAsignaturaSeriada != null) {
                    oldAsignaturaOfAsignaturaSeriadaListAsignaturaSeriada.getAsignaturaSeriadaList().remove(asignaturaSeriadaListAsignaturaSeriada);
                    oldAsignaturaOfAsignaturaSeriadaListAsignaturaSeriada = em.merge(oldAsignaturaOfAsignaturaSeriadaListAsignaturaSeriada);
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaList1AsignaturaSeriada : asignatura.getAsignaturaSeriadaList1()) {
                Asignatura oldAsignatura1OfAsignaturaSeriadaList1AsignaturaSeriada = asignaturaSeriadaList1AsignaturaSeriada.getAsignatura1();
                asignaturaSeriadaList1AsignaturaSeriada.setAsignatura1(asignatura);
                asignaturaSeriadaList1AsignaturaSeriada = em.merge(asignaturaSeriadaList1AsignaturaSeriada);
                if (oldAsignatura1OfAsignaturaSeriadaList1AsignaturaSeriada != null) {
                    oldAsignatura1OfAsignaturaSeriadaList1AsignaturaSeriada.getAsignaturaSeriadaList1().remove(asignaturaSeriadaList1AsignaturaSeriada);
                    oldAsignatura1OfAsignaturaSeriadaList1AsignaturaSeriada = em.merge(oldAsignatura1OfAsignaturaSeriadaList1AsignaturaSeriada);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : asignatura.getCalendarioAsignaturaList()) {
                Asignatura oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura = calendarioAsignaturaListCalendarioAsignatura.getIdAsignatura();
                calendarioAsignaturaListCalendarioAsignatura.setIdAsignatura(asignatura);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
                if (oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura != null) {
                    oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListCalendarioAsignatura);
                    oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura = em.merge(oldIdAsignaturaOfCalendarioAsignaturaListCalendarioAsignatura);
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignatura : asignatura.getBloqueAsignaturaList()) {
                Asignatura oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura = bloqueAsignaturaListBloqueAsignatura.getIdAsignatura();
                bloqueAsignaturaListBloqueAsignatura.setIdAsignatura(asignatura);
                bloqueAsignaturaListBloqueAsignatura = em.merge(bloqueAsignaturaListBloqueAsignatura);
                if (oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura != null) {
                    oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura.getBloqueAsignaturaList().remove(bloqueAsignaturaListBloqueAsignatura);
                    oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura = em.merge(oldIdAsignaturaOfBloqueAsignaturaListBloqueAsignatura);
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : asignatura.getProgramacionAsignaturaList()) {
                Asignatura oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura = programacionAsignaturaListProgramacionAsignatura.getIdAsignatura();
                programacionAsignaturaListProgramacionAsignatura.setIdAsignatura(asignatura);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
                if (oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura != null) {
                    oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListProgramacionAsignatura);
                    oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura = em.merge(oldIdAsignaturaOfProgramacionAsignaturaListProgramacionAsignatura);
                }
            }
            for (Asignatura asignaturaListAsignatura : asignatura.getAsignaturaList()) {
                Asignatura oldIdAsignaturaseriadaOfAsignaturaListAsignatura = asignaturaListAsignatura.getIdAsignaturaseriada();
                asignaturaListAsignatura.setIdAsignaturaseriada(asignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldIdAsignaturaseriadaOfAsignaturaListAsignatura != null) {
                    oldIdAsignaturaseriadaOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldIdAsignaturaseriadaOfAsignaturaListAsignatura = em.merge(oldIdAsignaturaseriadaOfAsignaturaListAsignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getIdAsignatura());
            TipoNivelasignatura idTiponivelasignaturaOld = persistentAsignatura.getIdTiponivelasignatura();
            TipoNivelasignatura idTiponivelasignaturaNew = asignatura.getIdTiponivelasignatura();
            TipoAsignatura idTipoasignaturaOld = persistentAsignatura.getIdTipoasignatura();
            TipoAsignatura idTipoasignaturaNew = asignatura.getIdTipoasignatura();
            PlanEstudio idPlanestudioOld = persistentAsignatura.getIdPlanestudio();
            PlanEstudio idPlanestudioNew = asignatura.getIdPlanestudio();
            Asignatura idAsignaturaseriadaOld = persistentAsignatura.getIdAsignaturaseriada();
            Asignatura idAsignaturaseriadaNew = asignatura.getIdAsignaturaseriada();
            List<AlumnoAsignatura> alumnoAsignaturaListOld = persistentAsignatura.getAlumnoAsignaturaList();
            List<AlumnoAsignatura> alumnoAsignaturaListNew = asignatura.getAlumnoAsignaturaList();
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListOld = persistentAsignatura.getAsignacionAsignaturaintegradoraList();
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraListNew = asignatura.getAsignacionAsignaturaintegradoraList();
            List<Correspondencia> correspondenciaListOld = persistentAsignatura.getCorrespondenciaList();
            List<Correspondencia> correspondenciaListNew = asignatura.getCorrespondenciaList();
            List<Correspondencia> correspondenciaList1Old = persistentAsignatura.getCorrespondenciaList1();
            List<Correspondencia> correspondenciaList1New = asignatura.getCorrespondenciaList1();
            List<ExamenExtemporaneo> examenExtemporaneoListOld = persistentAsignatura.getExamenExtemporaneoList();
            List<ExamenExtemporaneo> examenExtemporaneoListNew = asignatura.getExamenExtemporaneoList();
            List<Acta> actaListOld = persistentAsignatura.getActaList();
            List<Acta> actaListNew = asignatura.getActaList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListOld = persistentAsignatura.getAsignacionAsignaturabancoList();
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoListNew = asignatura.getAsignacionAsignaturabancoList();
            List<AsignaturaSeriada> asignaturaSeriadaListOld = persistentAsignatura.getAsignaturaSeriadaList();
            List<AsignaturaSeriada> asignaturaSeriadaListNew = asignatura.getAsignaturaSeriadaList();
            List<AsignaturaSeriada> asignaturaSeriadaList1Old = persistentAsignatura.getAsignaturaSeriadaList1();
            List<AsignaturaSeriada> asignaturaSeriadaList1New = asignatura.getAsignaturaSeriadaList1();
            List<CalendarioAsignatura> calendarioAsignaturaListOld = persistentAsignatura.getCalendarioAsignaturaList();
            List<CalendarioAsignatura> calendarioAsignaturaListNew = asignatura.getCalendarioAsignaturaList();
            List<BloqueAsignatura> bloqueAsignaturaListOld = persistentAsignatura.getBloqueAsignaturaList();
            List<BloqueAsignatura> bloqueAsignaturaListNew = asignatura.getBloqueAsignaturaList();
            List<ProgramacionAsignatura> programacionAsignaturaListOld = persistentAsignatura.getProgramacionAsignaturaList();
            List<ProgramacionAsignatura> programacionAsignaturaListNew = asignatura.getProgramacionAsignaturaList();
            List<Asignatura> asignaturaListOld = persistentAsignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = asignatura.getAsignaturaList();
            List<String> illegalOrphanMessages = null;
            for (Correspondencia correspondenciaListOldCorrespondencia : correspondenciaListOld) {
                if (!correspondenciaListNew.contains(correspondenciaListOldCorrespondencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Correspondencia " + correspondenciaListOldCorrespondencia + " since its asignatura field is not nullable.");
                }
            }
            for (Correspondencia correspondenciaList1OldCorrespondencia : correspondenciaList1Old) {
                if (!correspondenciaList1New.contains(correspondenciaList1OldCorrespondencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Correspondencia " + correspondenciaList1OldCorrespondencia + " since its asignatura1 field is not nullable.");
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaListOldAsignaturaSeriada : asignaturaSeriadaListOld) {
                if (!asignaturaSeriadaListNew.contains(asignaturaSeriadaListOldAsignaturaSeriada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignaturaSeriada " + asignaturaSeriadaListOldAsignaturaSeriada + " since its asignatura field is not nullable.");
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaList1OldAsignaturaSeriada : asignaturaSeriadaList1Old) {
                if (!asignaturaSeriadaList1New.contains(asignaturaSeriadaList1OldAsignaturaSeriada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignaturaSeriada " + asignaturaSeriadaList1OldAsignaturaSeriada + " since its asignatura1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTiponivelasignaturaNew != null) {
                idTiponivelasignaturaNew = em.getReference(idTiponivelasignaturaNew.getClass(), idTiponivelasignaturaNew.getIdTiponivelasignatura());
                asignatura.setIdTiponivelasignatura(idTiponivelasignaturaNew);
            }
            if (idTipoasignaturaNew != null) {
                idTipoasignaturaNew = em.getReference(idTipoasignaturaNew.getClass(), idTipoasignaturaNew.getIdTipoasignatura());
                asignatura.setIdTipoasignatura(idTipoasignaturaNew);
            }
            if (idPlanestudioNew != null) {
                idPlanestudioNew = em.getReference(idPlanestudioNew.getClass(), idPlanestudioNew.getIdPlanestudio());
                asignatura.setIdPlanestudio(idPlanestudioNew);
            }
            if (idAsignaturaseriadaNew != null) {
                idAsignaturaseriadaNew = em.getReference(idAsignaturaseriadaNew.getClass(), idAsignaturaseriadaNew.getIdAsignatura());
                asignatura.setIdAsignaturaseriada(idAsignaturaseriadaNew);
            }
            List<AlumnoAsignatura> attachedAlumnoAsignaturaListNew = new ArrayList<AlumnoAsignatura>();
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignaturaToAttach : alumnoAsignaturaListNew) {
                alumnoAsignaturaListNewAlumnoAsignaturaToAttach = em.getReference(alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getClass(), alumnoAsignaturaListNewAlumnoAsignaturaToAttach.getIdAlumnoasignatura());
                attachedAlumnoAsignaturaListNew.add(alumnoAsignaturaListNewAlumnoAsignaturaToAttach);
            }
            alumnoAsignaturaListNew = attachedAlumnoAsignaturaListNew;
            asignatura.setAlumnoAsignaturaList(alumnoAsignaturaListNew);
            List<AsignacionAsignaturaintegradora> attachedAsignacionAsignaturaintegradoraListNew = new ArrayList<AsignacionAsignaturaintegradora>();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach : asignacionAsignaturaintegradoraListNew) {
                asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach = em.getReference(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getClass(), asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach.getIdAsignacionasignaturaintegradora());
                attachedAsignacionAsignaturaintegradoraListNew.add(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradoraToAttach);
            }
            asignacionAsignaturaintegradoraListNew = attachedAsignacionAsignaturaintegradoraListNew;
            asignatura.setAsignacionAsignaturaintegradoraList(asignacionAsignaturaintegradoraListNew);
            List<Correspondencia> attachedCorrespondenciaListNew = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaListNewCorrespondenciaToAttach : correspondenciaListNew) {
                correspondenciaListNewCorrespondenciaToAttach = em.getReference(correspondenciaListNewCorrespondenciaToAttach.getClass(), correspondenciaListNewCorrespondenciaToAttach.getCorrespondenciaPK());
                attachedCorrespondenciaListNew.add(correspondenciaListNewCorrespondenciaToAttach);
            }
            correspondenciaListNew = attachedCorrespondenciaListNew;
            asignatura.setCorrespondenciaList(correspondenciaListNew);
            List<Correspondencia> attachedCorrespondenciaList1New = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaList1NewCorrespondenciaToAttach : correspondenciaList1New) {
                correspondenciaList1NewCorrespondenciaToAttach = em.getReference(correspondenciaList1NewCorrespondenciaToAttach.getClass(), correspondenciaList1NewCorrespondenciaToAttach.getCorrespondenciaPK());
                attachedCorrespondenciaList1New.add(correspondenciaList1NewCorrespondenciaToAttach);
            }
            correspondenciaList1New = attachedCorrespondenciaList1New;
            asignatura.setCorrespondenciaList1(correspondenciaList1New);
            List<ExamenExtemporaneo> attachedExamenExtemporaneoListNew = new ArrayList<ExamenExtemporaneo>();
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneoToAttach : examenExtemporaneoListNew) {
                examenExtemporaneoListNewExamenExtemporaneoToAttach = em.getReference(examenExtemporaneoListNewExamenExtemporaneoToAttach.getClass(), examenExtemporaneoListNewExamenExtemporaneoToAttach.getIdExamenextemporaneo());
                attachedExamenExtemporaneoListNew.add(examenExtemporaneoListNewExamenExtemporaneoToAttach);
            }
            examenExtemporaneoListNew = attachedExamenExtemporaneoListNew;
            asignatura.setExamenExtemporaneoList(examenExtemporaneoListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            asignatura.setActaList(actaListNew);
            List<AsignacionAsignaturabanco> attachedAsignacionAsignaturabancoListNew = new ArrayList<AsignacionAsignaturabanco>();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach : asignacionAsignaturabancoListNew) {
                asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach = em.getReference(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getClass(), asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach.getIdAsignacionasignaturabanco());
                attachedAsignacionAsignaturabancoListNew.add(asignacionAsignaturabancoListNewAsignacionAsignaturabancoToAttach);
            }
            asignacionAsignaturabancoListNew = attachedAsignacionAsignaturabancoListNew;
            asignatura.setAsignacionAsignaturabancoList(asignacionAsignaturabancoListNew);
            List<AsignaturaSeriada> attachedAsignaturaSeriadaListNew = new ArrayList<AsignaturaSeriada>();
            for (AsignaturaSeriada asignaturaSeriadaListNewAsignaturaSeriadaToAttach : asignaturaSeriadaListNew) {
                asignaturaSeriadaListNewAsignaturaSeriadaToAttach = em.getReference(asignaturaSeriadaListNewAsignaturaSeriadaToAttach.getClass(), asignaturaSeriadaListNewAsignaturaSeriadaToAttach.getAsignaturaSeriadaPK());
                attachedAsignaturaSeriadaListNew.add(asignaturaSeriadaListNewAsignaturaSeriadaToAttach);
            }
            asignaturaSeriadaListNew = attachedAsignaturaSeriadaListNew;
            asignatura.setAsignaturaSeriadaList(asignaturaSeriadaListNew);
            List<AsignaturaSeriada> attachedAsignaturaSeriadaList1New = new ArrayList<AsignaturaSeriada>();
            for (AsignaturaSeriada asignaturaSeriadaList1NewAsignaturaSeriadaToAttach : asignaturaSeriadaList1New) {
                asignaturaSeriadaList1NewAsignaturaSeriadaToAttach = em.getReference(asignaturaSeriadaList1NewAsignaturaSeriadaToAttach.getClass(), asignaturaSeriadaList1NewAsignaturaSeriadaToAttach.getAsignaturaSeriadaPK());
                attachedAsignaturaSeriadaList1New.add(asignaturaSeriadaList1NewAsignaturaSeriadaToAttach);
            }
            asignaturaSeriadaList1New = attachedAsignaturaSeriadaList1New;
            asignatura.setAsignaturaSeriadaList1(asignaturaSeriadaList1New);
            List<CalendarioAsignatura> attachedCalendarioAsignaturaListNew = new ArrayList<CalendarioAsignatura>();
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignaturaToAttach : calendarioAsignaturaListNew) {
                calendarioAsignaturaListNewCalendarioAsignaturaToAttach = em.getReference(calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getClass(), calendarioAsignaturaListNewCalendarioAsignaturaToAttach.getIdCalendarioasignatura());
                attachedCalendarioAsignaturaListNew.add(calendarioAsignaturaListNewCalendarioAsignaturaToAttach);
            }
            calendarioAsignaturaListNew = attachedCalendarioAsignaturaListNew;
            asignatura.setCalendarioAsignaturaList(calendarioAsignaturaListNew);
            List<BloqueAsignatura> attachedBloqueAsignaturaListNew = new ArrayList<BloqueAsignatura>();
            for (BloqueAsignatura bloqueAsignaturaListNewBloqueAsignaturaToAttach : bloqueAsignaturaListNew) {
                bloqueAsignaturaListNewBloqueAsignaturaToAttach = em.getReference(bloqueAsignaturaListNewBloqueAsignaturaToAttach.getClass(), bloqueAsignaturaListNewBloqueAsignaturaToAttach.getIdBloqueasignatura());
                attachedBloqueAsignaturaListNew.add(bloqueAsignaturaListNewBloqueAsignaturaToAttach);
            }
            bloqueAsignaturaListNew = attachedBloqueAsignaturaListNew;
            asignatura.setBloqueAsignaturaList(bloqueAsignaturaListNew);
            List<ProgramacionAsignatura> attachedProgramacionAsignaturaListNew = new ArrayList<ProgramacionAsignatura>();
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignaturaToAttach : programacionAsignaturaListNew) {
                programacionAsignaturaListNewProgramacionAsignaturaToAttach = em.getReference(programacionAsignaturaListNewProgramacionAsignaturaToAttach.getClass(), programacionAsignaturaListNewProgramacionAsignaturaToAttach.getIdProgramacionasignatura());
                attachedProgramacionAsignaturaListNew.add(programacionAsignaturaListNewProgramacionAsignaturaToAttach);
            }
            programacionAsignaturaListNew = attachedProgramacionAsignaturaListNew;
            asignatura.setProgramacionAsignaturaList(programacionAsignaturaListNew);
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdAsignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            asignatura.setAsignaturaList(asignaturaListNew);
            asignatura = em.merge(asignatura);
            if (idTiponivelasignaturaOld != null && !idTiponivelasignaturaOld.equals(idTiponivelasignaturaNew)) {
                idTiponivelasignaturaOld.getAsignaturaList().remove(asignatura);
                idTiponivelasignaturaOld = em.merge(idTiponivelasignaturaOld);
            }
            if (idTiponivelasignaturaNew != null && !idTiponivelasignaturaNew.equals(idTiponivelasignaturaOld)) {
                idTiponivelasignaturaNew.getAsignaturaList().add(asignatura);
                idTiponivelasignaturaNew = em.merge(idTiponivelasignaturaNew);
            }
            if (idTipoasignaturaOld != null && !idTipoasignaturaOld.equals(idTipoasignaturaNew)) {
                idTipoasignaturaOld.getAsignaturaList().remove(asignatura);
                idTipoasignaturaOld = em.merge(idTipoasignaturaOld);
            }
            if (idTipoasignaturaNew != null && !idTipoasignaturaNew.equals(idTipoasignaturaOld)) {
                idTipoasignaturaNew.getAsignaturaList().add(asignatura);
                idTipoasignaturaNew = em.merge(idTipoasignaturaNew);
            }
            if (idPlanestudioOld != null && !idPlanestudioOld.equals(idPlanestudioNew)) {
                idPlanestudioOld.getAsignaturaList().remove(asignatura);
                idPlanestudioOld = em.merge(idPlanestudioOld);
            }
            if (idPlanestudioNew != null && !idPlanestudioNew.equals(idPlanestudioOld)) {
                idPlanestudioNew.getAsignaturaList().add(asignatura);
                idPlanestudioNew = em.merge(idPlanestudioNew);
            }
            if (idAsignaturaseriadaOld != null && !idAsignaturaseriadaOld.equals(idAsignaturaseriadaNew)) {
                idAsignaturaseriadaOld.getAsignaturaList().remove(asignatura);
                idAsignaturaseriadaOld = em.merge(idAsignaturaseriadaOld);
            }
            if (idAsignaturaseriadaNew != null && !idAsignaturaseriadaNew.equals(idAsignaturaseriadaOld)) {
                idAsignaturaseriadaNew.getAsignaturaList().add(asignatura);
                idAsignaturaseriadaNew = em.merge(idAsignaturaseriadaNew);
            }
            for (AlumnoAsignatura alumnoAsignaturaListOldAlumnoAsignatura : alumnoAsignaturaListOld) {
                if (!alumnoAsignaturaListNew.contains(alumnoAsignaturaListOldAlumnoAsignatura)) {
                    alumnoAsignaturaListOldAlumnoAsignatura.setIdAsignatura(null);
                    alumnoAsignaturaListOldAlumnoAsignatura = em.merge(alumnoAsignaturaListOldAlumnoAsignatura);
                }
            }
            for (AlumnoAsignatura alumnoAsignaturaListNewAlumnoAsignatura : alumnoAsignaturaListNew) {
                if (!alumnoAsignaturaListOld.contains(alumnoAsignaturaListNewAlumnoAsignatura)) {
                    Asignatura oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = alumnoAsignaturaListNewAlumnoAsignatura.getIdAsignatura();
                    alumnoAsignaturaListNewAlumnoAsignatura.setIdAsignatura(asignatura);
                    alumnoAsignaturaListNewAlumnoAsignatura = em.merge(alumnoAsignaturaListNewAlumnoAsignatura);
                    if (oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura != null && !oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura.getAlumnoAsignaturaList().remove(alumnoAsignaturaListNewAlumnoAsignatura);
                        oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura = em.merge(oldIdAsignaturaOfAlumnoAsignaturaListNewAlumnoAsignatura);
                    }
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListOld) {
                if (!asignacionAsignaturaintegradoraListNew.contains(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora)) {
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora.setIdAsignatura(null);
                    asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListOldAsignacionAsignaturaintegradora);
                }
            }
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraListNew) {
                if (!asignacionAsignaturaintegradoraListOld.contains(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora)) {
                    Asignatura oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getIdAsignatura();
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.setIdAsignatura(asignatura);
                    asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    if (oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora != null && !oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.equals(asignatura)) {
                        oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora.getAsignacionAsignaturaintegradoraList().remove(asignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                        oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora = em.merge(oldIdAsignaturaOfAsignacionAsignaturaintegradoraListNewAsignacionAsignaturaintegradora);
                    }
                }
            }
            for (Correspondencia correspondenciaListNewCorrespondencia : correspondenciaListNew) {
                if (!correspondenciaListOld.contains(correspondenciaListNewCorrespondencia)) {
                    Asignatura oldAsignaturaOfCorrespondenciaListNewCorrespondencia = correspondenciaListNewCorrespondencia.getAsignatura();
                    correspondenciaListNewCorrespondencia.setAsignatura(asignatura);
                    correspondenciaListNewCorrespondencia = em.merge(correspondenciaListNewCorrespondencia);
                    if (oldAsignaturaOfCorrespondenciaListNewCorrespondencia != null && !oldAsignaturaOfCorrespondenciaListNewCorrespondencia.equals(asignatura)) {
                        oldAsignaturaOfCorrespondenciaListNewCorrespondencia.getCorrespondenciaList().remove(correspondenciaListNewCorrespondencia);
                        oldAsignaturaOfCorrespondenciaListNewCorrespondencia = em.merge(oldAsignaturaOfCorrespondenciaListNewCorrespondencia);
                    }
                }
            }
            for (Correspondencia correspondenciaList1NewCorrespondencia : correspondenciaList1New) {
                if (!correspondenciaList1Old.contains(correspondenciaList1NewCorrespondencia)) {
                    Asignatura oldAsignatura1OfCorrespondenciaList1NewCorrespondencia = correspondenciaList1NewCorrespondencia.getAsignatura1();
                    correspondenciaList1NewCorrespondencia.setAsignatura1(asignatura);
                    correspondenciaList1NewCorrespondencia = em.merge(correspondenciaList1NewCorrespondencia);
                    if (oldAsignatura1OfCorrespondenciaList1NewCorrespondencia != null && !oldAsignatura1OfCorrespondenciaList1NewCorrespondencia.equals(asignatura)) {
                        oldAsignatura1OfCorrespondenciaList1NewCorrespondencia.getCorrespondenciaList1().remove(correspondenciaList1NewCorrespondencia);
                        oldAsignatura1OfCorrespondenciaList1NewCorrespondencia = em.merge(oldAsignatura1OfCorrespondenciaList1NewCorrespondencia);
                    }
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListOldExamenExtemporaneo : examenExtemporaneoListOld) {
                if (!examenExtemporaneoListNew.contains(examenExtemporaneoListOldExamenExtemporaneo)) {
                    examenExtemporaneoListOldExamenExtemporaneo.setIdAsignatura(null);
                    examenExtemporaneoListOldExamenExtemporaneo = em.merge(examenExtemporaneoListOldExamenExtemporaneo);
                }
            }
            for (ExamenExtemporaneo examenExtemporaneoListNewExamenExtemporaneo : examenExtemporaneoListNew) {
                if (!examenExtemporaneoListOld.contains(examenExtemporaneoListNewExamenExtemporaneo)) {
                    Asignatura oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo = examenExtemporaneoListNewExamenExtemporaneo.getIdAsignatura();
                    examenExtemporaneoListNewExamenExtemporaneo.setIdAsignatura(asignatura);
                    examenExtemporaneoListNewExamenExtemporaneo = em.merge(examenExtemporaneoListNewExamenExtemporaneo);
                    if (oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo != null && !oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo.equals(asignatura)) {
                        oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo.getExamenExtemporaneoList().remove(examenExtemporaneoListNewExamenExtemporaneo);
                        oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo = em.merge(oldIdAsignaturaOfExamenExtemporaneoListNewExamenExtemporaneo);
                    }
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdAsignatura(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    Asignatura oldIdAsignaturaOfActaListNewActa = actaListNewActa.getIdAsignatura();
                    actaListNewActa.setIdAsignatura(asignatura);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdAsignaturaOfActaListNewActa != null && !oldIdAsignaturaOfActaListNewActa.equals(asignatura)) {
                        oldIdAsignaturaOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdAsignaturaOfActaListNewActa = em.merge(oldIdAsignaturaOfActaListNewActa);
                    }
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListOldAsignacionAsignaturabanco : asignacionAsignaturabancoListOld) {
                if (!asignacionAsignaturabancoListNew.contains(asignacionAsignaturabancoListOldAsignacionAsignaturabanco)) {
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco.setIdAsignatura(null);
                    asignacionAsignaturabancoListOldAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListOldAsignacionAsignaturabanco);
                }
            }
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListNewAsignacionAsignaturabanco : asignacionAsignaturabancoListNew) {
                if (!asignacionAsignaturabancoListOld.contains(asignacionAsignaturabancoListNewAsignacionAsignaturabanco)) {
                    Asignatura oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = asignacionAsignaturabancoListNewAsignacionAsignaturabanco.getIdAsignatura();
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco.setIdAsignatura(asignatura);
                    asignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    if (oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco != null && !oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.equals(asignatura)) {
                        oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco.getAsignacionAsignaturabancoList().remove(asignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                        oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco = em.merge(oldIdAsignaturaOfAsignacionAsignaturabancoListNewAsignacionAsignaturabanco);
                    }
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaListNewAsignaturaSeriada : asignaturaSeriadaListNew) {
                if (!asignaturaSeriadaListOld.contains(asignaturaSeriadaListNewAsignaturaSeriada)) {
                    Asignatura oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada = asignaturaSeriadaListNewAsignaturaSeriada.getAsignatura();
                    asignaturaSeriadaListNewAsignaturaSeriada.setAsignatura(asignatura);
                    asignaturaSeriadaListNewAsignaturaSeriada = em.merge(asignaturaSeriadaListNewAsignaturaSeriada);
                    if (oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada != null && !oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada.equals(asignatura)) {
                        oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada.getAsignaturaSeriadaList().remove(asignaturaSeriadaListNewAsignaturaSeriada);
                        oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada = em.merge(oldAsignaturaOfAsignaturaSeriadaListNewAsignaturaSeriada);
                    }
                }
            }
            for (AsignaturaSeriada asignaturaSeriadaList1NewAsignaturaSeriada : asignaturaSeriadaList1New) {
                if (!asignaturaSeriadaList1Old.contains(asignaturaSeriadaList1NewAsignaturaSeriada)) {
                    Asignatura oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada = asignaturaSeriadaList1NewAsignaturaSeriada.getAsignatura1();
                    asignaturaSeriadaList1NewAsignaturaSeriada.setAsignatura1(asignatura);
                    asignaturaSeriadaList1NewAsignaturaSeriada = em.merge(asignaturaSeriadaList1NewAsignaturaSeriada);
                    if (oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada != null && !oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada.equals(asignatura)) {
                        oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada.getAsignaturaSeriadaList1().remove(asignaturaSeriadaList1NewAsignaturaSeriada);
                        oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada = em.merge(oldAsignatura1OfAsignaturaSeriadaList1NewAsignaturaSeriada);
                    }
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListOldCalendarioAsignatura : calendarioAsignaturaListOld) {
                if (!calendarioAsignaturaListNew.contains(calendarioAsignaturaListOldCalendarioAsignatura)) {
                    calendarioAsignaturaListOldCalendarioAsignatura.setIdAsignatura(null);
                    calendarioAsignaturaListOldCalendarioAsignatura = em.merge(calendarioAsignaturaListOldCalendarioAsignatura);
                }
            }
            for (CalendarioAsignatura calendarioAsignaturaListNewCalendarioAsignatura : calendarioAsignaturaListNew) {
                if (!calendarioAsignaturaListOld.contains(calendarioAsignaturaListNewCalendarioAsignatura)) {
                    Asignatura oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura = calendarioAsignaturaListNewCalendarioAsignatura.getIdAsignatura();
                    calendarioAsignaturaListNewCalendarioAsignatura.setIdAsignatura(asignatura);
                    calendarioAsignaturaListNewCalendarioAsignatura = em.merge(calendarioAsignaturaListNewCalendarioAsignatura);
                    if (oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura != null && !oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura.getCalendarioAsignaturaList().remove(calendarioAsignaturaListNewCalendarioAsignatura);
                        oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura = em.merge(oldIdAsignaturaOfCalendarioAsignaturaListNewCalendarioAsignatura);
                    }
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListOldBloqueAsignatura : bloqueAsignaturaListOld) {
                if (!bloqueAsignaturaListNew.contains(bloqueAsignaturaListOldBloqueAsignatura)) {
                    bloqueAsignaturaListOldBloqueAsignatura.setIdAsignatura(null);
                    bloqueAsignaturaListOldBloqueAsignatura = em.merge(bloqueAsignaturaListOldBloqueAsignatura);
                }
            }
            for (BloqueAsignatura bloqueAsignaturaListNewBloqueAsignatura : bloqueAsignaturaListNew) {
                if (!bloqueAsignaturaListOld.contains(bloqueAsignaturaListNewBloqueAsignatura)) {
                    Asignatura oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura = bloqueAsignaturaListNewBloqueAsignatura.getIdAsignatura();
                    bloqueAsignaturaListNewBloqueAsignatura.setIdAsignatura(asignatura);
                    bloqueAsignaturaListNewBloqueAsignatura = em.merge(bloqueAsignaturaListNewBloqueAsignatura);
                    if (oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura != null && !oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura.getBloqueAsignaturaList().remove(bloqueAsignaturaListNewBloqueAsignatura);
                        oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura = em.merge(oldIdAsignaturaOfBloqueAsignaturaListNewBloqueAsignatura);
                    }
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListOldProgramacionAsignatura : programacionAsignaturaListOld) {
                if (!programacionAsignaturaListNew.contains(programacionAsignaturaListOldProgramacionAsignatura)) {
                    programacionAsignaturaListOldProgramacionAsignatura.setIdAsignatura(null);
                    programacionAsignaturaListOldProgramacionAsignatura = em.merge(programacionAsignaturaListOldProgramacionAsignatura);
                }
            }
            for (ProgramacionAsignatura programacionAsignaturaListNewProgramacionAsignatura : programacionAsignaturaListNew) {
                if (!programacionAsignaturaListOld.contains(programacionAsignaturaListNewProgramacionAsignatura)) {
                    Asignatura oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura = programacionAsignaturaListNewProgramacionAsignatura.getIdAsignatura();
                    programacionAsignaturaListNewProgramacionAsignatura.setIdAsignatura(asignatura);
                    programacionAsignaturaListNewProgramacionAsignatura = em.merge(programacionAsignaturaListNewProgramacionAsignatura);
                    if (oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura != null && !oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura.equals(asignatura)) {
                        oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura.getProgramacionAsignaturaList().remove(programacionAsignaturaListNewProgramacionAsignatura);
                        oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura = em.merge(oldIdAsignaturaOfProgramacionAsignaturaListNewProgramacionAsignatura);
                    }
                }
            }
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setIdAsignaturaseriada(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    Asignatura oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getIdAsignaturaseriada();
                    asignaturaListNewAsignatura.setIdAsignaturaseriada(asignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura != null && !oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura.equals(asignatura)) {
                        oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura = em.merge(oldIdAsignaturaseriadaOfAsignaturaListNewAsignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignatura.getIdAsignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getIdAsignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Correspondencia> correspondenciaListOrphanCheck = asignatura.getCorrespondenciaList();
            for (Correspondencia correspondenciaListOrphanCheckCorrespondencia : correspondenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Correspondencia " + correspondenciaListOrphanCheckCorrespondencia + " in its correspondenciaList field has a non-nullable asignatura field.");
            }
            List<Correspondencia> correspondenciaList1OrphanCheck = asignatura.getCorrespondenciaList1();
            for (Correspondencia correspondenciaList1OrphanCheckCorrespondencia : correspondenciaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Correspondencia " + correspondenciaList1OrphanCheckCorrespondencia + " in its correspondenciaList1 field has a non-nullable asignatura1 field.");
            }
            List<AsignaturaSeriada> asignaturaSeriadaListOrphanCheck = asignatura.getAsignaturaSeriadaList();
            for (AsignaturaSeriada asignaturaSeriadaListOrphanCheckAsignaturaSeriada : asignaturaSeriadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the AsignaturaSeriada " + asignaturaSeriadaListOrphanCheckAsignaturaSeriada + " in its asignaturaSeriadaList field has a non-nullable asignatura field.");
            }
            List<AsignaturaSeriada> asignaturaSeriadaList1OrphanCheck = asignatura.getAsignaturaSeriadaList1();
            for (AsignaturaSeriada asignaturaSeriadaList1OrphanCheckAsignaturaSeriada : asignaturaSeriadaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the AsignaturaSeriada " + asignaturaSeriadaList1OrphanCheckAsignaturaSeriada + " in its asignaturaSeriadaList1 field has a non-nullable asignatura1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoNivelasignatura idTiponivelasignatura = asignatura.getIdTiponivelasignatura();
            if (idTiponivelasignatura != null) {
                idTiponivelasignatura.getAsignaturaList().remove(asignatura);
                idTiponivelasignatura = em.merge(idTiponivelasignatura);
            }
            TipoAsignatura idTipoasignatura = asignatura.getIdTipoasignatura();
            if (idTipoasignatura != null) {
                idTipoasignatura.getAsignaturaList().remove(asignatura);
                idTipoasignatura = em.merge(idTipoasignatura);
            }
            PlanEstudio idPlanestudio = asignatura.getIdPlanestudio();
            if (idPlanestudio != null) {
                idPlanestudio.getAsignaturaList().remove(asignatura);
                idPlanestudio = em.merge(idPlanestudio);
            }
            Asignatura idAsignaturaseriada = asignatura.getIdAsignaturaseriada();
            if (idAsignaturaseriada != null) {
                idAsignaturaseriada.getAsignaturaList().remove(asignatura);
                idAsignaturaseriada = em.merge(idAsignaturaseriada);
            }
            List<AlumnoAsignatura> alumnoAsignaturaList = asignatura.getAlumnoAsignaturaList();
            for (AlumnoAsignatura alumnoAsignaturaListAlumnoAsignatura : alumnoAsignaturaList) {
                alumnoAsignaturaListAlumnoAsignatura.setIdAsignatura(null);
                alumnoAsignaturaListAlumnoAsignatura = em.merge(alumnoAsignaturaListAlumnoAsignatura);
            }
            List<AsignacionAsignaturaintegradora> asignacionAsignaturaintegradoraList = asignatura.getAsignacionAsignaturaintegradoraList();
            for (AsignacionAsignaturaintegradora asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora : asignacionAsignaturaintegradoraList) {
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora.setIdAsignatura(null);
                asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora = em.merge(asignacionAsignaturaintegradoraListAsignacionAsignaturaintegradora);
            }
            List<ExamenExtemporaneo> examenExtemporaneoList = asignatura.getExamenExtemporaneoList();
            for (ExamenExtemporaneo examenExtemporaneoListExamenExtemporaneo : examenExtemporaneoList) {
                examenExtemporaneoListExamenExtemporaneo.setIdAsignatura(null);
                examenExtemporaneoListExamenExtemporaneo = em.merge(examenExtemporaneoListExamenExtemporaneo);
            }
            List<Acta> actaList = asignatura.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdAsignatura(null);
                actaListActa = em.merge(actaListActa);
            }
            List<AsignacionAsignaturabanco> asignacionAsignaturabancoList = asignatura.getAsignacionAsignaturabancoList();
            for (AsignacionAsignaturabanco asignacionAsignaturabancoListAsignacionAsignaturabanco : asignacionAsignaturabancoList) {
                asignacionAsignaturabancoListAsignacionAsignaturabanco.setIdAsignatura(null);
                asignacionAsignaturabancoListAsignacionAsignaturabanco = em.merge(asignacionAsignaturabancoListAsignacionAsignaturabanco);
            }
            List<CalendarioAsignatura> calendarioAsignaturaList = asignatura.getCalendarioAsignaturaList();
            for (CalendarioAsignatura calendarioAsignaturaListCalendarioAsignatura : calendarioAsignaturaList) {
                calendarioAsignaturaListCalendarioAsignatura.setIdAsignatura(null);
                calendarioAsignaturaListCalendarioAsignatura = em.merge(calendarioAsignaturaListCalendarioAsignatura);
            }
            List<BloqueAsignatura> bloqueAsignaturaList = asignatura.getBloqueAsignaturaList();
            for (BloqueAsignatura bloqueAsignaturaListBloqueAsignatura : bloqueAsignaturaList) {
                bloqueAsignaturaListBloqueAsignatura.setIdAsignatura(null);
                bloqueAsignaturaListBloqueAsignatura = em.merge(bloqueAsignaturaListBloqueAsignatura);
            }
            List<ProgramacionAsignatura> programacionAsignaturaList = asignatura.getProgramacionAsignaturaList();
            for (ProgramacionAsignatura programacionAsignaturaListProgramacionAsignatura : programacionAsignaturaList) {
                programacionAsignaturaListProgramacionAsignatura.setIdAsignatura(null);
                programacionAsignaturaListProgramacionAsignatura = em.merge(programacionAsignaturaListProgramacionAsignatura);
            }
            List<Asignatura> asignaturaList = asignatura.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setIdAsignaturaseriada(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignatura(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
