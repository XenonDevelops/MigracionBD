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
import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.SuspensionPlantel;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AdministrativoPlantel;
import com.utez.integracion.entity.Evento;
import com.utez.integracion.entity.VacacionPlantel;
import com.utez.integracion.entity.AsignacionRecursoplantel;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.GrupoInduccion;
import com.utez.integracion.entity.Plantel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlantelJpaController implements Serializable {

    public PlantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plantel plantel) {
        if (plantel.getSuspensionPlantelList() == null) {
            plantel.setSuspensionPlantelList(new ArrayList<SuspensionPlantel>());
        }
        if (plantel.getAdministrativoPlantelList() == null) {
            plantel.setAdministrativoPlantelList(new ArrayList<AdministrativoPlantel>());
        }
        if (plantel.getEventoList() == null) {
            plantel.setEventoList(new ArrayList<Evento>());
        }
        if (plantel.getVacacionPlantelList() == null) {
            plantel.setVacacionPlantelList(new ArrayList<VacacionPlantel>());
        }
        if (plantel.getAsignacionRecursoplantelList() == null) {
            plantel.setAsignacionRecursoplantelList(new ArrayList<AsignacionRecursoplantel>());
        }
        if (plantel.getPlanEstudioList() == null) {
            plantel.setPlanEstudioList(new ArrayList<PlanEstudio>());
        }
        if (plantel.getGrupoInduccionList() == null) {
            plantel.setGrupoInduccionList(new ArrayList<GrupoInduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asentamiento idAsentamiento = plantel.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento = em.getReference(idAsentamiento.getClass(), idAsentamiento.getIdAsentamiento());
                plantel.setIdAsentamiento(idAsentamiento);
            }
            List<SuspensionPlantel> attachedSuspensionPlantelList = new ArrayList<SuspensionPlantel>();
            for (SuspensionPlantel suspensionPlantelListSuspensionPlantelToAttach : plantel.getSuspensionPlantelList()) {
                suspensionPlantelListSuspensionPlantelToAttach = em.getReference(suspensionPlantelListSuspensionPlantelToAttach.getClass(), suspensionPlantelListSuspensionPlantelToAttach.getSuspensionPlantelPK());
                attachedSuspensionPlantelList.add(suspensionPlantelListSuspensionPlantelToAttach);
            }
            plantel.setSuspensionPlantelList(attachedSuspensionPlantelList);
            List<AdministrativoPlantel> attachedAdministrativoPlantelList = new ArrayList<AdministrativoPlantel>();
            for (AdministrativoPlantel administrativoPlantelListAdministrativoPlantelToAttach : plantel.getAdministrativoPlantelList()) {
                administrativoPlantelListAdministrativoPlantelToAttach = em.getReference(administrativoPlantelListAdministrativoPlantelToAttach.getClass(), administrativoPlantelListAdministrativoPlantelToAttach.getAdministrativoPlantelPK());
                attachedAdministrativoPlantelList.add(administrativoPlantelListAdministrativoPlantelToAttach);
            }
            plantel.setAdministrativoPlantelList(attachedAdministrativoPlantelList);
            List<Evento> attachedEventoList = new ArrayList<Evento>();
            for (Evento eventoListEventoToAttach : plantel.getEventoList()) {
                eventoListEventoToAttach = em.getReference(eventoListEventoToAttach.getClass(), eventoListEventoToAttach.getIdEvento());
                attachedEventoList.add(eventoListEventoToAttach);
            }
            plantel.setEventoList(attachedEventoList);
            List<VacacionPlantel> attachedVacacionPlantelList = new ArrayList<VacacionPlantel>();
            for (VacacionPlantel vacacionPlantelListVacacionPlantelToAttach : plantel.getVacacionPlantelList()) {
                vacacionPlantelListVacacionPlantelToAttach = em.getReference(vacacionPlantelListVacacionPlantelToAttach.getClass(), vacacionPlantelListVacacionPlantelToAttach.getVacacionPlantelPK());
                attachedVacacionPlantelList.add(vacacionPlantelListVacacionPlantelToAttach);
            }
            plantel.setVacacionPlantelList(attachedVacacionPlantelList);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelList = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantelToAttach : plantel.getAsignacionRecursoplantelList()) {
                asignacionRecursoplantelListAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelList.add(asignacionRecursoplantelListAsignacionRecursoplantelToAttach);
            }
            plantel.setAsignacionRecursoplantelList(attachedAsignacionRecursoplantelList);
            List<PlanEstudio> attachedPlanEstudioList = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListPlanEstudioToAttach : plantel.getPlanEstudioList()) {
                planEstudioListPlanEstudioToAttach = em.getReference(planEstudioListPlanEstudioToAttach.getClass(), planEstudioListPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioList.add(planEstudioListPlanEstudioToAttach);
            }
            plantel.setPlanEstudioList(attachedPlanEstudioList);
            List<GrupoInduccion> attachedGrupoInduccionList = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListGrupoInduccionToAttach : plantel.getGrupoInduccionList()) {
                grupoInduccionListGrupoInduccionToAttach = em.getReference(grupoInduccionListGrupoInduccionToAttach.getClass(), grupoInduccionListGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionList.add(grupoInduccionListGrupoInduccionToAttach);
            }
            plantel.setGrupoInduccionList(attachedGrupoInduccionList);
            em.persist(plantel);
            if (idAsentamiento != null) {
                idAsentamiento.getPlantelList().add(plantel);
                idAsentamiento = em.merge(idAsentamiento);
            }
            for (SuspensionPlantel suspensionPlantelListSuspensionPlantel : plantel.getSuspensionPlantelList()) {
                Plantel oldPlantelOfSuspensionPlantelListSuspensionPlantel = suspensionPlantelListSuspensionPlantel.getPlantel();
                suspensionPlantelListSuspensionPlantel.setPlantel(plantel);
                suspensionPlantelListSuspensionPlantel = em.merge(suspensionPlantelListSuspensionPlantel);
                if (oldPlantelOfSuspensionPlantelListSuspensionPlantel != null) {
                    oldPlantelOfSuspensionPlantelListSuspensionPlantel.getSuspensionPlantelList().remove(suspensionPlantelListSuspensionPlantel);
                    oldPlantelOfSuspensionPlantelListSuspensionPlantel = em.merge(oldPlantelOfSuspensionPlantelListSuspensionPlantel);
                }
            }
            for (AdministrativoPlantel administrativoPlantelListAdministrativoPlantel : plantel.getAdministrativoPlantelList()) {
                Plantel oldPlantelOfAdministrativoPlantelListAdministrativoPlantel = administrativoPlantelListAdministrativoPlantel.getPlantel();
                administrativoPlantelListAdministrativoPlantel.setPlantel(plantel);
                administrativoPlantelListAdministrativoPlantel = em.merge(administrativoPlantelListAdministrativoPlantel);
                if (oldPlantelOfAdministrativoPlantelListAdministrativoPlantel != null) {
                    oldPlantelOfAdministrativoPlantelListAdministrativoPlantel.getAdministrativoPlantelList().remove(administrativoPlantelListAdministrativoPlantel);
                    oldPlantelOfAdministrativoPlantelListAdministrativoPlantel = em.merge(oldPlantelOfAdministrativoPlantelListAdministrativoPlantel);
                }
            }
            for (Evento eventoListEvento : plantel.getEventoList()) {
                Plantel oldIdPlantelOfEventoListEvento = eventoListEvento.getIdPlantel();
                eventoListEvento.setIdPlantel(plantel);
                eventoListEvento = em.merge(eventoListEvento);
                if (oldIdPlantelOfEventoListEvento != null) {
                    oldIdPlantelOfEventoListEvento.getEventoList().remove(eventoListEvento);
                    oldIdPlantelOfEventoListEvento = em.merge(oldIdPlantelOfEventoListEvento);
                }
            }
            for (VacacionPlantel vacacionPlantelListVacacionPlantel : plantel.getVacacionPlantelList()) {
                Plantel oldPlantelOfVacacionPlantelListVacacionPlantel = vacacionPlantelListVacacionPlantel.getPlantel();
                vacacionPlantelListVacacionPlantel.setPlantel(plantel);
                vacacionPlantelListVacacionPlantel = em.merge(vacacionPlantelListVacacionPlantel);
                if (oldPlantelOfVacacionPlantelListVacacionPlantel != null) {
                    oldPlantelOfVacacionPlantelListVacacionPlantel.getVacacionPlantelList().remove(vacacionPlantelListVacacionPlantel);
                    oldPlantelOfVacacionPlantelListVacacionPlantel = em.merge(oldPlantelOfVacacionPlantelListVacacionPlantel);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : plantel.getAsignacionRecursoplantelList()) {
                Plantel oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel = asignacionRecursoplantelListAsignacionRecursoplantel.getIdPlantel();
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdPlantel(plantel);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
                if (oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel != null) {
                    oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListAsignacionRecursoplantel);
                    oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel = em.merge(oldIdPlantelOfAsignacionRecursoplantelListAsignacionRecursoplantel);
                }
            }
            for (PlanEstudio planEstudioListPlanEstudio : plantel.getPlanEstudioList()) {
                Plantel oldIdPlantelOfPlanEstudioListPlanEstudio = planEstudioListPlanEstudio.getIdPlantel();
                planEstudioListPlanEstudio.setIdPlantel(plantel);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
                if (oldIdPlantelOfPlanEstudioListPlanEstudio != null) {
                    oldIdPlantelOfPlanEstudioListPlanEstudio.getPlanEstudioList().remove(planEstudioListPlanEstudio);
                    oldIdPlantelOfPlanEstudioListPlanEstudio = em.merge(oldIdPlantelOfPlanEstudioListPlanEstudio);
                }
            }
            for (GrupoInduccion grupoInduccionListGrupoInduccion : plantel.getGrupoInduccionList()) {
                Plantel oldIdPlantelOfGrupoInduccionListGrupoInduccion = grupoInduccionListGrupoInduccion.getIdPlantel();
                grupoInduccionListGrupoInduccion.setIdPlantel(plantel);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
                if (oldIdPlantelOfGrupoInduccionListGrupoInduccion != null) {
                    oldIdPlantelOfGrupoInduccionListGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListGrupoInduccion);
                    oldIdPlantelOfGrupoInduccionListGrupoInduccion = em.merge(oldIdPlantelOfGrupoInduccionListGrupoInduccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plantel plantel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel persistentPlantel = em.find(Plantel.class, plantel.getIdPlantel());
            Asentamiento idAsentamientoOld = persistentPlantel.getIdAsentamiento();
            Asentamiento idAsentamientoNew = plantel.getIdAsentamiento();
            List<SuspensionPlantel> suspensionPlantelListOld = persistentPlantel.getSuspensionPlantelList();
            List<SuspensionPlantel> suspensionPlantelListNew = plantel.getSuspensionPlantelList();
            List<AdministrativoPlantel> administrativoPlantelListOld = persistentPlantel.getAdministrativoPlantelList();
            List<AdministrativoPlantel> administrativoPlantelListNew = plantel.getAdministrativoPlantelList();
            List<Evento> eventoListOld = persistentPlantel.getEventoList();
            List<Evento> eventoListNew = plantel.getEventoList();
            List<VacacionPlantel> vacacionPlantelListOld = persistentPlantel.getVacacionPlantelList();
            List<VacacionPlantel> vacacionPlantelListNew = plantel.getVacacionPlantelList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListOld = persistentPlantel.getAsignacionRecursoplantelList();
            List<AsignacionRecursoplantel> asignacionRecursoplantelListNew = plantel.getAsignacionRecursoplantelList();
            List<PlanEstudio> planEstudioListOld = persistentPlantel.getPlanEstudioList();
            List<PlanEstudio> planEstudioListNew = plantel.getPlanEstudioList();
            List<GrupoInduccion> grupoInduccionListOld = persistentPlantel.getGrupoInduccionList();
            List<GrupoInduccion> grupoInduccionListNew = plantel.getGrupoInduccionList();
            List<String> illegalOrphanMessages = null;
            for (SuspensionPlantel suspensionPlantelListOldSuspensionPlantel : suspensionPlantelListOld) {
                if (!suspensionPlantelListNew.contains(suspensionPlantelListOldSuspensionPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SuspensionPlantel " + suspensionPlantelListOldSuspensionPlantel + " since its plantel field is not nullable.");
                }
            }
            for (AdministrativoPlantel administrativoPlantelListOldAdministrativoPlantel : administrativoPlantelListOld) {
                if (!administrativoPlantelListNew.contains(administrativoPlantelListOldAdministrativoPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AdministrativoPlantel " + administrativoPlantelListOldAdministrativoPlantel + " since its plantel field is not nullable.");
                }
            }
            for (VacacionPlantel vacacionPlantelListOldVacacionPlantel : vacacionPlantelListOld) {
                if (!vacacionPlantelListNew.contains(vacacionPlantelListOldVacacionPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacacionPlantel " + vacacionPlantelListOldVacacionPlantel + " since its plantel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAsentamientoNew != null) {
                idAsentamientoNew = em.getReference(idAsentamientoNew.getClass(), idAsentamientoNew.getIdAsentamiento());
                plantel.setIdAsentamiento(idAsentamientoNew);
            }
            List<SuspensionPlantel> attachedSuspensionPlantelListNew = new ArrayList<SuspensionPlantel>();
            for (SuspensionPlantel suspensionPlantelListNewSuspensionPlantelToAttach : suspensionPlantelListNew) {
                suspensionPlantelListNewSuspensionPlantelToAttach = em.getReference(suspensionPlantelListNewSuspensionPlantelToAttach.getClass(), suspensionPlantelListNewSuspensionPlantelToAttach.getSuspensionPlantelPK());
                attachedSuspensionPlantelListNew.add(suspensionPlantelListNewSuspensionPlantelToAttach);
            }
            suspensionPlantelListNew = attachedSuspensionPlantelListNew;
            plantel.setSuspensionPlantelList(suspensionPlantelListNew);
            List<AdministrativoPlantel> attachedAdministrativoPlantelListNew = new ArrayList<AdministrativoPlantel>();
            for (AdministrativoPlantel administrativoPlantelListNewAdministrativoPlantelToAttach : administrativoPlantelListNew) {
                administrativoPlantelListNewAdministrativoPlantelToAttach = em.getReference(administrativoPlantelListNewAdministrativoPlantelToAttach.getClass(), administrativoPlantelListNewAdministrativoPlantelToAttach.getAdministrativoPlantelPK());
                attachedAdministrativoPlantelListNew.add(administrativoPlantelListNewAdministrativoPlantelToAttach);
            }
            administrativoPlantelListNew = attachedAdministrativoPlantelListNew;
            plantel.setAdministrativoPlantelList(administrativoPlantelListNew);
            List<Evento> attachedEventoListNew = new ArrayList<Evento>();
            for (Evento eventoListNewEventoToAttach : eventoListNew) {
                eventoListNewEventoToAttach = em.getReference(eventoListNewEventoToAttach.getClass(), eventoListNewEventoToAttach.getIdEvento());
                attachedEventoListNew.add(eventoListNewEventoToAttach);
            }
            eventoListNew = attachedEventoListNew;
            plantel.setEventoList(eventoListNew);
            List<VacacionPlantel> attachedVacacionPlantelListNew = new ArrayList<VacacionPlantel>();
            for (VacacionPlantel vacacionPlantelListNewVacacionPlantelToAttach : vacacionPlantelListNew) {
                vacacionPlantelListNewVacacionPlantelToAttach = em.getReference(vacacionPlantelListNewVacacionPlantelToAttach.getClass(), vacacionPlantelListNewVacacionPlantelToAttach.getVacacionPlantelPK());
                attachedVacacionPlantelListNew.add(vacacionPlantelListNewVacacionPlantelToAttach);
            }
            vacacionPlantelListNew = attachedVacacionPlantelListNew;
            plantel.setVacacionPlantelList(vacacionPlantelListNew);
            List<AsignacionRecursoplantel> attachedAsignacionRecursoplantelListNew = new ArrayList<AsignacionRecursoplantel>();
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach : asignacionRecursoplantelListNew) {
                asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach = em.getReference(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getClass(), asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach.getIdAsignacionrecursoplantel());
                attachedAsignacionRecursoplantelListNew.add(asignacionRecursoplantelListNewAsignacionRecursoplantelToAttach);
            }
            asignacionRecursoplantelListNew = attachedAsignacionRecursoplantelListNew;
            plantel.setAsignacionRecursoplantelList(asignacionRecursoplantelListNew);
            List<PlanEstudio> attachedPlanEstudioListNew = new ArrayList<PlanEstudio>();
            for (PlanEstudio planEstudioListNewPlanEstudioToAttach : planEstudioListNew) {
                planEstudioListNewPlanEstudioToAttach = em.getReference(planEstudioListNewPlanEstudioToAttach.getClass(), planEstudioListNewPlanEstudioToAttach.getIdPlanestudio());
                attachedPlanEstudioListNew.add(planEstudioListNewPlanEstudioToAttach);
            }
            planEstudioListNew = attachedPlanEstudioListNew;
            plantel.setPlanEstudioList(planEstudioListNew);
            List<GrupoInduccion> attachedGrupoInduccionListNew = new ArrayList<GrupoInduccion>();
            for (GrupoInduccion grupoInduccionListNewGrupoInduccionToAttach : grupoInduccionListNew) {
                grupoInduccionListNewGrupoInduccionToAttach = em.getReference(grupoInduccionListNewGrupoInduccionToAttach.getClass(), grupoInduccionListNewGrupoInduccionToAttach.getIdGrupoinduccion());
                attachedGrupoInduccionListNew.add(grupoInduccionListNewGrupoInduccionToAttach);
            }
            grupoInduccionListNew = attachedGrupoInduccionListNew;
            plantel.setGrupoInduccionList(grupoInduccionListNew);
            plantel = em.merge(plantel);
            if (idAsentamientoOld != null && !idAsentamientoOld.equals(idAsentamientoNew)) {
                idAsentamientoOld.getPlantelList().remove(plantel);
                idAsentamientoOld = em.merge(idAsentamientoOld);
            }
            if (idAsentamientoNew != null && !idAsentamientoNew.equals(idAsentamientoOld)) {
                idAsentamientoNew.getPlantelList().add(plantel);
                idAsentamientoNew = em.merge(idAsentamientoNew);
            }
            for (SuspensionPlantel suspensionPlantelListNewSuspensionPlantel : suspensionPlantelListNew) {
                if (!suspensionPlantelListOld.contains(suspensionPlantelListNewSuspensionPlantel)) {
                    Plantel oldPlantelOfSuspensionPlantelListNewSuspensionPlantel = suspensionPlantelListNewSuspensionPlantel.getPlantel();
                    suspensionPlantelListNewSuspensionPlantel.setPlantel(plantel);
                    suspensionPlantelListNewSuspensionPlantel = em.merge(suspensionPlantelListNewSuspensionPlantel);
                    if (oldPlantelOfSuspensionPlantelListNewSuspensionPlantel != null && !oldPlantelOfSuspensionPlantelListNewSuspensionPlantel.equals(plantel)) {
                        oldPlantelOfSuspensionPlantelListNewSuspensionPlantel.getSuspensionPlantelList().remove(suspensionPlantelListNewSuspensionPlantel);
                        oldPlantelOfSuspensionPlantelListNewSuspensionPlantel = em.merge(oldPlantelOfSuspensionPlantelListNewSuspensionPlantel);
                    }
                }
            }
            for (AdministrativoPlantel administrativoPlantelListNewAdministrativoPlantel : administrativoPlantelListNew) {
                if (!administrativoPlantelListOld.contains(administrativoPlantelListNewAdministrativoPlantel)) {
                    Plantel oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel = administrativoPlantelListNewAdministrativoPlantel.getPlantel();
                    administrativoPlantelListNewAdministrativoPlantel.setPlantel(plantel);
                    administrativoPlantelListNewAdministrativoPlantel = em.merge(administrativoPlantelListNewAdministrativoPlantel);
                    if (oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel != null && !oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel.equals(plantel)) {
                        oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel.getAdministrativoPlantelList().remove(administrativoPlantelListNewAdministrativoPlantel);
                        oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel = em.merge(oldPlantelOfAdministrativoPlantelListNewAdministrativoPlantel);
                    }
                }
            }
            for (Evento eventoListOldEvento : eventoListOld) {
                if (!eventoListNew.contains(eventoListOldEvento)) {
                    eventoListOldEvento.setIdPlantel(null);
                    eventoListOldEvento = em.merge(eventoListOldEvento);
                }
            }
            for (Evento eventoListNewEvento : eventoListNew) {
                if (!eventoListOld.contains(eventoListNewEvento)) {
                    Plantel oldIdPlantelOfEventoListNewEvento = eventoListNewEvento.getIdPlantel();
                    eventoListNewEvento.setIdPlantel(plantel);
                    eventoListNewEvento = em.merge(eventoListNewEvento);
                    if (oldIdPlantelOfEventoListNewEvento != null && !oldIdPlantelOfEventoListNewEvento.equals(plantel)) {
                        oldIdPlantelOfEventoListNewEvento.getEventoList().remove(eventoListNewEvento);
                        oldIdPlantelOfEventoListNewEvento = em.merge(oldIdPlantelOfEventoListNewEvento);
                    }
                }
            }
            for (VacacionPlantel vacacionPlantelListNewVacacionPlantel : vacacionPlantelListNew) {
                if (!vacacionPlantelListOld.contains(vacacionPlantelListNewVacacionPlantel)) {
                    Plantel oldPlantelOfVacacionPlantelListNewVacacionPlantel = vacacionPlantelListNewVacacionPlantel.getPlantel();
                    vacacionPlantelListNewVacacionPlantel.setPlantel(plantel);
                    vacacionPlantelListNewVacacionPlantel = em.merge(vacacionPlantelListNewVacacionPlantel);
                    if (oldPlantelOfVacacionPlantelListNewVacacionPlantel != null && !oldPlantelOfVacacionPlantelListNewVacacionPlantel.equals(plantel)) {
                        oldPlantelOfVacacionPlantelListNewVacacionPlantel.getVacacionPlantelList().remove(vacacionPlantelListNewVacacionPlantel);
                        oldPlantelOfVacacionPlantelListNewVacacionPlantel = em.merge(oldPlantelOfVacacionPlantelListNewVacacionPlantel);
                    }
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListOldAsignacionRecursoplantel : asignacionRecursoplantelListOld) {
                if (!asignacionRecursoplantelListNew.contains(asignacionRecursoplantelListOldAsignacionRecursoplantel)) {
                    asignacionRecursoplantelListOldAsignacionRecursoplantel.setIdPlantel(null);
                    asignacionRecursoplantelListOldAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListOldAsignacionRecursoplantel);
                }
            }
            for (AsignacionRecursoplantel asignacionRecursoplantelListNewAsignacionRecursoplantel : asignacionRecursoplantelListNew) {
                if (!asignacionRecursoplantelListOld.contains(asignacionRecursoplantelListNewAsignacionRecursoplantel)) {
                    Plantel oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = asignacionRecursoplantelListNewAsignacionRecursoplantel.getIdPlantel();
                    asignacionRecursoplantelListNewAsignacionRecursoplantel.setIdPlantel(plantel);
                    asignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                    if (oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel != null && !oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.equals(plantel)) {
                        oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel.getAsignacionRecursoplantelList().remove(asignacionRecursoplantelListNewAsignacionRecursoplantel);
                        oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel = em.merge(oldIdPlantelOfAsignacionRecursoplantelListNewAsignacionRecursoplantel);
                    }
                }
            }
            for (PlanEstudio planEstudioListOldPlanEstudio : planEstudioListOld) {
                if (!planEstudioListNew.contains(planEstudioListOldPlanEstudio)) {
                    planEstudioListOldPlanEstudio.setIdPlantel(null);
                    planEstudioListOldPlanEstudio = em.merge(planEstudioListOldPlanEstudio);
                }
            }
            for (PlanEstudio planEstudioListNewPlanEstudio : planEstudioListNew) {
                if (!planEstudioListOld.contains(planEstudioListNewPlanEstudio)) {
                    Plantel oldIdPlantelOfPlanEstudioListNewPlanEstudio = planEstudioListNewPlanEstudio.getIdPlantel();
                    planEstudioListNewPlanEstudio.setIdPlantel(plantel);
                    planEstudioListNewPlanEstudio = em.merge(planEstudioListNewPlanEstudio);
                    if (oldIdPlantelOfPlanEstudioListNewPlanEstudio != null && !oldIdPlantelOfPlanEstudioListNewPlanEstudio.equals(plantel)) {
                        oldIdPlantelOfPlanEstudioListNewPlanEstudio.getPlanEstudioList().remove(planEstudioListNewPlanEstudio);
                        oldIdPlantelOfPlanEstudioListNewPlanEstudio = em.merge(oldIdPlantelOfPlanEstudioListNewPlanEstudio);
                    }
                }
            }
            for (GrupoInduccion grupoInduccionListOldGrupoInduccion : grupoInduccionListOld) {
                if (!grupoInduccionListNew.contains(grupoInduccionListOldGrupoInduccion)) {
                    grupoInduccionListOldGrupoInduccion.setIdPlantel(null);
                    grupoInduccionListOldGrupoInduccion = em.merge(grupoInduccionListOldGrupoInduccion);
                }
            }
            for (GrupoInduccion grupoInduccionListNewGrupoInduccion : grupoInduccionListNew) {
                if (!grupoInduccionListOld.contains(grupoInduccionListNewGrupoInduccion)) {
                    Plantel oldIdPlantelOfGrupoInduccionListNewGrupoInduccion = grupoInduccionListNewGrupoInduccion.getIdPlantel();
                    grupoInduccionListNewGrupoInduccion.setIdPlantel(plantel);
                    grupoInduccionListNewGrupoInduccion = em.merge(grupoInduccionListNewGrupoInduccion);
                    if (oldIdPlantelOfGrupoInduccionListNewGrupoInduccion != null && !oldIdPlantelOfGrupoInduccionListNewGrupoInduccion.equals(plantel)) {
                        oldIdPlantelOfGrupoInduccionListNewGrupoInduccion.getGrupoInduccionList().remove(grupoInduccionListNewGrupoInduccion);
                        oldIdPlantelOfGrupoInduccionListNewGrupoInduccion = em.merge(oldIdPlantelOfGrupoInduccionListNewGrupoInduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = plantel.getIdPlantel();
                if (findPlantel(id) == null) {
                    throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.");
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
            Plantel plantel;
            try {
                plantel = em.getReference(Plantel.class, id);
                plantel.getIdPlantel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SuspensionPlantel> suspensionPlantelListOrphanCheck = plantel.getSuspensionPlantelList();
            for (SuspensionPlantel suspensionPlantelListOrphanCheckSuspensionPlantel : suspensionPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Plantel (" + plantel + ") cannot be destroyed since the SuspensionPlantel " + suspensionPlantelListOrphanCheckSuspensionPlantel + " in its suspensionPlantelList field has a non-nullable plantel field.");
            }
            List<AdministrativoPlantel> administrativoPlantelListOrphanCheck = plantel.getAdministrativoPlantelList();
            for (AdministrativoPlantel administrativoPlantelListOrphanCheckAdministrativoPlantel : administrativoPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Plantel (" + plantel + ") cannot be destroyed since the AdministrativoPlantel " + administrativoPlantelListOrphanCheckAdministrativoPlantel + " in its administrativoPlantelList field has a non-nullable plantel field.");
            }
            List<VacacionPlantel> vacacionPlantelListOrphanCheck = plantel.getVacacionPlantelList();
            for (VacacionPlantel vacacionPlantelListOrphanCheckVacacionPlantel : vacacionPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Plantel (" + plantel + ") cannot be destroyed since the VacacionPlantel " + vacacionPlantelListOrphanCheckVacacionPlantel + " in its vacacionPlantelList field has a non-nullable plantel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asentamiento idAsentamiento = plantel.getIdAsentamiento();
            if (idAsentamiento != null) {
                idAsentamiento.getPlantelList().remove(plantel);
                idAsentamiento = em.merge(idAsentamiento);
            }
            List<Evento> eventoList = plantel.getEventoList();
            for (Evento eventoListEvento : eventoList) {
                eventoListEvento.setIdPlantel(null);
                eventoListEvento = em.merge(eventoListEvento);
            }
            List<AsignacionRecursoplantel> asignacionRecursoplantelList = plantel.getAsignacionRecursoplantelList();
            for (AsignacionRecursoplantel asignacionRecursoplantelListAsignacionRecursoplantel : asignacionRecursoplantelList) {
                asignacionRecursoplantelListAsignacionRecursoplantel.setIdPlantel(null);
                asignacionRecursoplantelListAsignacionRecursoplantel = em.merge(asignacionRecursoplantelListAsignacionRecursoplantel);
            }
            List<PlanEstudio> planEstudioList = plantel.getPlanEstudioList();
            for (PlanEstudio planEstudioListPlanEstudio : planEstudioList) {
                planEstudioListPlanEstudio.setIdPlantel(null);
                planEstudioListPlanEstudio = em.merge(planEstudioListPlanEstudio);
            }
            List<GrupoInduccion> grupoInduccionList = plantel.getGrupoInduccionList();
            for (GrupoInduccion grupoInduccionListGrupoInduccion : grupoInduccionList) {
                grupoInduccionListGrupoInduccion.setIdPlantel(null);
                grupoInduccionListGrupoInduccion = em.merge(grupoInduccionListGrupoInduccion);
            }
            em.remove(plantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Plantel> findPlantelEntities() {
        return findPlantelEntities(true, -1, -1);
    }

    public List<Plantel> findPlantelEntities(int maxResults, int firstResult) {
        return findPlantelEntities(false, maxResults, firstResult);
    }

    private List<Plantel> findPlantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Plantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Plantel findPlantel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Plantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
