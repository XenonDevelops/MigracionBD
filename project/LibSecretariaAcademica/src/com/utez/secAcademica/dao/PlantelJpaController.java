/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.IllegalOrphanException;
import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Mesopcionc;
import java.util.ArrayList;
import java.util.List;
import com.utez.secAcademica.entity.Recursohumano;
import com.utez.secAcademica.entity.Grupo;
import com.utez.secAcademica.entity.Calendariorectoria;
import com.utez.secAcademica.entity.Calendario;
import com.utez.secAcademica.entity.Planestudios;
import com.utez.secAcademica.entity.Plantel;
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
        if (plantel.getMesopcioncList() == null) {
            plantel.setMesopcioncList(new ArrayList<Mesopcionc>());
        }
        if (plantel.getRecursohumanoList() == null) {
            plantel.setRecursohumanoList(new ArrayList<Recursohumano>());
        }
        if (plantel.getGrupoList() == null) {
            plantel.setGrupoList(new ArrayList<Grupo>());
        }
        if (plantel.getCalendariorectoriaList() == null) {
            plantel.setCalendariorectoriaList(new ArrayList<Calendariorectoria>());
        }
        if (plantel.getCalendarioList() == null) {
            plantel.setCalendarioList(new ArrayList<Calendario>());
        }
        if (plantel.getPlanestudiosList() == null) {
            plantel.setPlanestudiosList(new ArrayList<Planestudios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mesopcionc> attachedMesopcioncList = new ArrayList<Mesopcionc>();
            for (Mesopcionc mesopcioncListMesopcioncToAttach : plantel.getMesopcioncList()) {
                mesopcioncListMesopcioncToAttach = em.getReference(mesopcioncListMesopcioncToAttach.getClass(), mesopcioncListMesopcioncToAttach.getIdmesopcionc());
                attachedMesopcioncList.add(mesopcioncListMesopcioncToAttach);
            }
            plantel.setMesopcioncList(attachedMesopcioncList);
            List<Recursohumano> attachedRecursohumanoList = new ArrayList<Recursohumano>();
            for (Recursohumano recursohumanoListRecursohumanoToAttach : plantel.getRecursohumanoList()) {
                recursohumanoListRecursohumanoToAttach = em.getReference(recursohumanoListRecursohumanoToAttach.getClass(), recursohumanoListRecursohumanoToAttach.getIdrecursohumano());
                attachedRecursohumanoList.add(recursohumanoListRecursohumanoToAttach);
            }
            plantel.setRecursohumanoList(attachedRecursohumanoList);
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : plantel.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            plantel.setGrupoList(attachedGrupoList);
            List<Calendariorectoria> attachedCalendariorectoriaList = new ArrayList<Calendariorectoria>();
            for (Calendariorectoria calendariorectoriaListCalendariorectoriaToAttach : plantel.getCalendariorectoriaList()) {
                calendariorectoriaListCalendariorectoriaToAttach = em.getReference(calendariorectoriaListCalendariorectoriaToAttach.getClass(), calendariorectoriaListCalendariorectoriaToAttach.getIdcalendariorectoria());
                attachedCalendariorectoriaList.add(calendariorectoriaListCalendariorectoriaToAttach);
            }
            plantel.setCalendariorectoriaList(attachedCalendariorectoriaList);
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : plantel.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdcalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            plantel.setCalendarioList(attachedCalendarioList);
            List<Planestudios> attachedPlanestudiosList = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListPlanestudiosToAttach : plantel.getPlanestudiosList()) {
                planestudiosListPlanestudiosToAttach = em.getReference(planestudiosListPlanestudiosToAttach.getClass(), planestudiosListPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosList.add(planestudiosListPlanestudiosToAttach);
            }
            plantel.setPlanestudiosList(attachedPlanestudiosList);
            em.persist(plantel);
            for (Mesopcionc mesopcioncListMesopcionc : plantel.getMesopcioncList()) {
                Plantel oldIdplantelOfMesopcioncListMesopcionc = mesopcioncListMesopcionc.getIdplantel();
                mesopcioncListMesopcionc.setIdplantel(plantel);
                mesopcioncListMesopcionc = em.merge(mesopcioncListMesopcionc);
                if (oldIdplantelOfMesopcioncListMesopcionc != null) {
                    oldIdplantelOfMesopcioncListMesopcionc.getMesopcioncList().remove(mesopcioncListMesopcionc);
                    oldIdplantelOfMesopcioncListMesopcionc = em.merge(oldIdplantelOfMesopcioncListMesopcionc);
                }
            }
            for (Recursohumano recursohumanoListRecursohumano : plantel.getRecursohumanoList()) {
                Plantel oldIdplantelOfRecursohumanoListRecursohumano = recursohumanoListRecursohumano.getIdplantel();
                recursohumanoListRecursohumano.setIdplantel(plantel);
                recursohumanoListRecursohumano = em.merge(recursohumanoListRecursohumano);
                if (oldIdplantelOfRecursohumanoListRecursohumano != null) {
                    oldIdplantelOfRecursohumanoListRecursohumano.getRecursohumanoList().remove(recursohumanoListRecursohumano);
                    oldIdplantelOfRecursohumanoListRecursohumano = em.merge(oldIdplantelOfRecursohumanoListRecursohumano);
                }
            }
            for (Grupo grupoListGrupo : plantel.getGrupoList()) {
                Plantel oldIdplantelOfGrupoListGrupo = grupoListGrupo.getIdplantel();
                grupoListGrupo.setIdplantel(plantel);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdplantelOfGrupoListGrupo != null) {
                    oldIdplantelOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdplantelOfGrupoListGrupo = em.merge(oldIdplantelOfGrupoListGrupo);
                }
            }
            for (Calendariorectoria calendariorectoriaListCalendariorectoria : plantel.getCalendariorectoriaList()) {
                Plantel oldIdplantelOfCalendariorectoriaListCalendariorectoria = calendariorectoriaListCalendariorectoria.getIdplantel();
                calendariorectoriaListCalendariorectoria.setIdplantel(plantel);
                calendariorectoriaListCalendariorectoria = em.merge(calendariorectoriaListCalendariorectoria);
                if (oldIdplantelOfCalendariorectoriaListCalendariorectoria != null) {
                    oldIdplantelOfCalendariorectoriaListCalendariorectoria.getCalendariorectoriaList().remove(calendariorectoriaListCalendariorectoria);
                    oldIdplantelOfCalendariorectoriaListCalendariorectoria = em.merge(oldIdplantelOfCalendariorectoriaListCalendariorectoria);
                }
            }
            for (Calendario calendarioListCalendario : plantel.getCalendarioList()) {
                Plantel oldIdplantelOfCalendarioListCalendario = calendarioListCalendario.getIdplantel();
                calendarioListCalendario.setIdplantel(plantel);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldIdplantelOfCalendarioListCalendario != null) {
                    oldIdplantelOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldIdplantelOfCalendarioListCalendario = em.merge(oldIdplantelOfCalendarioListCalendario);
                }
            }
            for (Planestudios planestudiosListPlanestudios : plantel.getPlanestudiosList()) {
                Plantel oldIdplantelOfPlanestudiosListPlanestudios = planestudiosListPlanestudios.getIdplantel();
                planestudiosListPlanestudios.setIdplantel(plantel);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
                if (oldIdplantelOfPlanestudiosListPlanestudios != null) {
                    oldIdplantelOfPlanestudiosListPlanestudios.getPlanestudiosList().remove(planestudiosListPlanestudios);
                    oldIdplantelOfPlanestudiosListPlanestudios = em.merge(oldIdplantelOfPlanestudiosListPlanestudios);
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
            Plantel persistentPlantel = em.find(Plantel.class, plantel.getIdplantel());
            List<Mesopcionc> mesopcioncListOld = persistentPlantel.getMesopcioncList();
            List<Mesopcionc> mesopcioncListNew = plantel.getMesopcioncList();
            List<Recursohumano> recursohumanoListOld = persistentPlantel.getRecursohumanoList();
            List<Recursohumano> recursohumanoListNew = plantel.getRecursohumanoList();
            List<Grupo> grupoListOld = persistentPlantel.getGrupoList();
            List<Grupo> grupoListNew = plantel.getGrupoList();
            List<Calendariorectoria> calendariorectoriaListOld = persistentPlantel.getCalendariorectoriaList();
            List<Calendariorectoria> calendariorectoriaListNew = plantel.getCalendariorectoriaList();
            List<Calendario> calendarioListOld = persistentPlantel.getCalendarioList();
            List<Calendario> calendarioListNew = plantel.getCalendarioList();
            List<Planestudios> planestudiosListOld = persistentPlantel.getPlanestudiosList();
            List<Planestudios> planestudiosListNew = plantel.getPlanestudiosList();
            List<String> illegalOrphanMessages = null;
            for (Planestudios planestudiosListOldPlanestudios : planestudiosListOld) {
                if (!planestudiosListNew.contains(planestudiosListOldPlanestudios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planestudios " + planestudiosListOldPlanestudios + " since its idplantel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Mesopcionc> attachedMesopcioncListNew = new ArrayList<Mesopcionc>();
            for (Mesopcionc mesopcioncListNewMesopcioncToAttach : mesopcioncListNew) {
                mesopcioncListNewMesopcioncToAttach = em.getReference(mesopcioncListNewMesopcioncToAttach.getClass(), mesopcioncListNewMesopcioncToAttach.getIdmesopcionc());
                attachedMesopcioncListNew.add(mesopcioncListNewMesopcioncToAttach);
            }
            mesopcioncListNew = attachedMesopcioncListNew;
            plantel.setMesopcioncList(mesopcioncListNew);
            List<Recursohumano> attachedRecursohumanoListNew = new ArrayList<Recursohumano>();
            for (Recursohumano recursohumanoListNewRecursohumanoToAttach : recursohumanoListNew) {
                recursohumanoListNewRecursohumanoToAttach = em.getReference(recursohumanoListNewRecursohumanoToAttach.getClass(), recursohumanoListNewRecursohumanoToAttach.getIdrecursohumano());
                attachedRecursohumanoListNew.add(recursohumanoListNewRecursohumanoToAttach);
            }
            recursohumanoListNew = attachedRecursohumanoListNew;
            plantel.setRecursohumanoList(recursohumanoListNew);
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            plantel.setGrupoList(grupoListNew);
            List<Calendariorectoria> attachedCalendariorectoriaListNew = new ArrayList<Calendariorectoria>();
            for (Calendariorectoria calendariorectoriaListNewCalendariorectoriaToAttach : calendariorectoriaListNew) {
                calendariorectoriaListNewCalendariorectoriaToAttach = em.getReference(calendariorectoriaListNewCalendariorectoriaToAttach.getClass(), calendariorectoriaListNewCalendariorectoriaToAttach.getIdcalendariorectoria());
                attachedCalendariorectoriaListNew.add(calendariorectoriaListNewCalendariorectoriaToAttach);
            }
            calendariorectoriaListNew = attachedCalendariorectoriaListNew;
            plantel.setCalendariorectoriaList(calendariorectoriaListNew);
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdcalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            plantel.setCalendarioList(calendarioListNew);
            List<Planestudios> attachedPlanestudiosListNew = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListNewPlanestudiosToAttach : planestudiosListNew) {
                planestudiosListNewPlanestudiosToAttach = em.getReference(planestudiosListNewPlanestudiosToAttach.getClass(), planestudiosListNewPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosListNew.add(planestudiosListNewPlanestudiosToAttach);
            }
            planestudiosListNew = attachedPlanestudiosListNew;
            plantel.setPlanestudiosList(planestudiosListNew);
            plantel = em.merge(plantel);
            for (Mesopcionc mesopcioncListOldMesopcionc : mesopcioncListOld) {
                if (!mesopcioncListNew.contains(mesopcioncListOldMesopcionc)) {
                    mesopcioncListOldMesopcionc.setIdplantel(null);
                    mesopcioncListOldMesopcionc = em.merge(mesopcioncListOldMesopcionc);
                }
            }
            for (Mesopcionc mesopcioncListNewMesopcionc : mesopcioncListNew) {
                if (!mesopcioncListOld.contains(mesopcioncListNewMesopcionc)) {
                    Plantel oldIdplantelOfMesopcioncListNewMesopcionc = mesopcioncListNewMesopcionc.getIdplantel();
                    mesopcioncListNewMesopcionc.setIdplantel(plantel);
                    mesopcioncListNewMesopcionc = em.merge(mesopcioncListNewMesopcionc);
                    if (oldIdplantelOfMesopcioncListNewMesopcionc != null && !oldIdplantelOfMesopcioncListNewMesopcionc.equals(plantel)) {
                        oldIdplantelOfMesopcioncListNewMesopcionc.getMesopcioncList().remove(mesopcioncListNewMesopcionc);
                        oldIdplantelOfMesopcioncListNewMesopcionc = em.merge(oldIdplantelOfMesopcioncListNewMesopcionc);
                    }
                }
            }
            for (Recursohumano recursohumanoListOldRecursohumano : recursohumanoListOld) {
                if (!recursohumanoListNew.contains(recursohumanoListOldRecursohumano)) {
                    recursohumanoListOldRecursohumano.setIdplantel(null);
                    recursohumanoListOldRecursohumano = em.merge(recursohumanoListOldRecursohumano);
                }
            }
            for (Recursohumano recursohumanoListNewRecursohumano : recursohumanoListNew) {
                if (!recursohumanoListOld.contains(recursohumanoListNewRecursohumano)) {
                    Plantel oldIdplantelOfRecursohumanoListNewRecursohumano = recursohumanoListNewRecursohumano.getIdplantel();
                    recursohumanoListNewRecursohumano.setIdplantel(plantel);
                    recursohumanoListNewRecursohumano = em.merge(recursohumanoListNewRecursohumano);
                    if (oldIdplantelOfRecursohumanoListNewRecursohumano != null && !oldIdplantelOfRecursohumanoListNewRecursohumano.equals(plantel)) {
                        oldIdplantelOfRecursohumanoListNewRecursohumano.getRecursohumanoList().remove(recursohumanoListNewRecursohumano);
                        oldIdplantelOfRecursohumanoListNewRecursohumano = em.merge(oldIdplantelOfRecursohumanoListNewRecursohumano);
                    }
                }
            }
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setIdplantel(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Plantel oldIdplantelOfGrupoListNewGrupo = grupoListNewGrupo.getIdplantel();
                    grupoListNewGrupo.setIdplantel(plantel);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdplantelOfGrupoListNewGrupo != null && !oldIdplantelOfGrupoListNewGrupo.equals(plantel)) {
                        oldIdplantelOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdplantelOfGrupoListNewGrupo = em.merge(oldIdplantelOfGrupoListNewGrupo);
                    }
                }
            }
            for (Calendariorectoria calendariorectoriaListOldCalendariorectoria : calendariorectoriaListOld) {
                if (!calendariorectoriaListNew.contains(calendariorectoriaListOldCalendariorectoria)) {
                    calendariorectoriaListOldCalendariorectoria.setIdplantel(null);
                    calendariorectoriaListOldCalendariorectoria = em.merge(calendariorectoriaListOldCalendariorectoria);
                }
            }
            for (Calendariorectoria calendariorectoriaListNewCalendariorectoria : calendariorectoriaListNew) {
                if (!calendariorectoriaListOld.contains(calendariorectoriaListNewCalendariorectoria)) {
                    Plantel oldIdplantelOfCalendariorectoriaListNewCalendariorectoria = calendariorectoriaListNewCalendariorectoria.getIdplantel();
                    calendariorectoriaListNewCalendariorectoria.setIdplantel(plantel);
                    calendariorectoriaListNewCalendariorectoria = em.merge(calendariorectoriaListNewCalendariorectoria);
                    if (oldIdplantelOfCalendariorectoriaListNewCalendariorectoria != null && !oldIdplantelOfCalendariorectoriaListNewCalendariorectoria.equals(plantel)) {
                        oldIdplantelOfCalendariorectoriaListNewCalendariorectoria.getCalendariorectoriaList().remove(calendariorectoriaListNewCalendariorectoria);
                        oldIdplantelOfCalendariorectoriaListNewCalendariorectoria = em.merge(oldIdplantelOfCalendariorectoriaListNewCalendariorectoria);
                    }
                }
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    calendarioListOldCalendario.setIdplantel(null);
                    calendarioListOldCalendario = em.merge(calendarioListOldCalendario);
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Plantel oldIdplantelOfCalendarioListNewCalendario = calendarioListNewCalendario.getIdplantel();
                    calendarioListNewCalendario.setIdplantel(plantel);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldIdplantelOfCalendarioListNewCalendario != null && !oldIdplantelOfCalendarioListNewCalendario.equals(plantel)) {
                        oldIdplantelOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldIdplantelOfCalendarioListNewCalendario = em.merge(oldIdplantelOfCalendarioListNewCalendario);
                    }
                }
            }
            for (Planestudios planestudiosListNewPlanestudios : planestudiosListNew) {
                if (!planestudiosListOld.contains(planestudiosListNewPlanestudios)) {
                    Plantel oldIdplantelOfPlanestudiosListNewPlanestudios = planestudiosListNewPlanestudios.getIdplantel();
                    planestudiosListNewPlanestudios.setIdplantel(plantel);
                    planestudiosListNewPlanestudios = em.merge(planestudiosListNewPlanestudios);
                    if (oldIdplantelOfPlanestudiosListNewPlanestudios != null && !oldIdplantelOfPlanestudiosListNewPlanestudios.equals(plantel)) {
                        oldIdplantelOfPlanestudiosListNewPlanestudios.getPlanestudiosList().remove(planestudiosListNewPlanestudios);
                        oldIdplantelOfPlanestudiosListNewPlanestudios = em.merge(oldIdplantelOfPlanestudiosListNewPlanestudios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = plantel.getIdplantel();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel plantel;
            try {
                plantel = em.getReference(Plantel.class, id);
                plantel.getIdplantel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Planestudios> planestudiosListOrphanCheck = plantel.getPlanestudiosList();
            for (Planestudios planestudiosListOrphanCheckPlanestudios : planestudiosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Plantel (" + plantel + ") cannot be destroyed since the Planestudios " + planestudiosListOrphanCheckPlanestudios + " in its planestudiosList field has a non-nullable idplantel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Mesopcionc> mesopcioncList = plantel.getMesopcioncList();
            for (Mesopcionc mesopcioncListMesopcionc : mesopcioncList) {
                mesopcioncListMesopcionc.setIdplantel(null);
                mesopcioncListMesopcionc = em.merge(mesopcioncListMesopcionc);
            }
            List<Recursohumano> recursohumanoList = plantel.getRecursohumanoList();
            for (Recursohumano recursohumanoListRecursohumano : recursohumanoList) {
                recursohumanoListRecursohumano.setIdplantel(null);
                recursohumanoListRecursohumano = em.merge(recursohumanoListRecursohumano);
            }
            List<Grupo> grupoList = plantel.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setIdplantel(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            List<Calendariorectoria> calendariorectoriaList = plantel.getCalendariorectoriaList();
            for (Calendariorectoria calendariorectoriaListCalendariorectoria : calendariorectoriaList) {
                calendariorectoriaListCalendariorectoria.setIdplantel(null);
                calendariorectoriaListCalendariorectoria = em.merge(calendariorectoriaListCalendariorectoria);
            }
            List<Calendario> calendarioList = plantel.getCalendarioList();
            for (Calendario calendarioListCalendario : calendarioList) {
                calendarioListCalendario.setIdplantel(null);
                calendarioListCalendario = em.merge(calendarioListCalendario);
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

    public Plantel findPlantel(Integer id) {
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
