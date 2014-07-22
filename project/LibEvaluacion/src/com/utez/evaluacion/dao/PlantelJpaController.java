/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asignacionplan;
import java.util.ArrayList;
import java.util.List;
import com.utez.evaluacion.entity.Asignacionactividad;
import com.utez.evaluacion.entity.Asesor;
import com.utez.evaluacion.entity.Plantel;
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
        if (plantel.getAsignacionplanList() == null) {
            plantel.setAsignacionplanList(new ArrayList<Asignacionplan>());
        }
        if (plantel.getAsignacionactividadList() == null) {
            plantel.setAsignacionactividadList(new ArrayList<Asignacionactividad>());
        }
        if (plantel.getAsesorList() == null) {
            plantel.setAsesorList(new ArrayList<Asesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignacionplan> attachedAsignacionplanList = new ArrayList<Asignacionplan>();
            for (Asignacionplan asignacionplanListAsignacionplanToAttach : plantel.getAsignacionplanList()) {
                asignacionplanListAsignacionplanToAttach = em.getReference(asignacionplanListAsignacionplanToAttach.getClass(), asignacionplanListAsignacionplanToAttach.getClaveAsignacion());
                attachedAsignacionplanList.add(asignacionplanListAsignacionplanToAttach);
            }
            plantel.setAsignacionplanList(attachedAsignacionplanList);
            List<Asignacionactividad> attachedAsignacionactividadList = new ArrayList<Asignacionactividad>();
            for (Asignacionactividad asignacionactividadListAsignacionactividadToAttach : plantel.getAsignacionactividadList()) {
                asignacionactividadListAsignacionactividadToAttach = em.getReference(asignacionactividadListAsignacionactividadToAttach.getClass(), asignacionactividadListAsignacionactividadToAttach.getClaveAsignacion());
                attachedAsignacionactividadList.add(asignacionactividadListAsignacionactividadToAttach);
            }
            plantel.setAsignacionactividadList(attachedAsignacionactividadList);
            List<Asesor> attachedAsesorList = new ArrayList<Asesor>();
            for (Asesor asesorListAsesorToAttach : plantel.getAsesorList()) {
                asesorListAsesorToAttach = em.getReference(asesorListAsesorToAttach.getClass(), asesorListAsesorToAttach.getClaveAsesor());
                attachedAsesorList.add(asesorListAsesorToAttach);
            }
            plantel.setAsesorList(attachedAsesorList);
            em.persist(plantel);
            for (Asignacionplan asignacionplanListAsignacionplan : plantel.getAsignacionplanList()) {
                Plantel oldClavePlantelOfAsignacionplanListAsignacionplan = asignacionplanListAsignacionplan.getClavePlantel();
                asignacionplanListAsignacionplan.setClavePlantel(plantel);
                asignacionplanListAsignacionplan = em.merge(asignacionplanListAsignacionplan);
                if (oldClavePlantelOfAsignacionplanListAsignacionplan != null) {
                    oldClavePlantelOfAsignacionplanListAsignacionplan.getAsignacionplanList().remove(asignacionplanListAsignacionplan);
                    oldClavePlantelOfAsignacionplanListAsignacionplan = em.merge(oldClavePlantelOfAsignacionplanListAsignacionplan);
                }
            }
            for (Asignacionactividad asignacionactividadListAsignacionactividad : plantel.getAsignacionactividadList()) {
                Plantel oldClavePlantelOfAsignacionactividadListAsignacionactividad = asignacionactividadListAsignacionactividad.getClavePlantel();
                asignacionactividadListAsignacionactividad.setClavePlantel(plantel);
                asignacionactividadListAsignacionactividad = em.merge(asignacionactividadListAsignacionactividad);
                if (oldClavePlantelOfAsignacionactividadListAsignacionactividad != null) {
                    oldClavePlantelOfAsignacionactividadListAsignacionactividad.getAsignacionactividadList().remove(asignacionactividadListAsignacionactividad);
                    oldClavePlantelOfAsignacionactividadListAsignacionactividad = em.merge(oldClavePlantelOfAsignacionactividadListAsignacionactividad);
                }
            }
            for (Asesor asesorListAsesor : plantel.getAsesorList()) {
                Plantel oldClavePlantelOfAsesorListAsesor = asesorListAsesor.getClavePlantel();
                asesorListAsesor.setClavePlantel(plantel);
                asesorListAsesor = em.merge(asesorListAsesor);
                if (oldClavePlantelOfAsesorListAsesor != null) {
                    oldClavePlantelOfAsesorListAsesor.getAsesorList().remove(asesorListAsesor);
                    oldClavePlantelOfAsesorListAsesor = em.merge(oldClavePlantelOfAsesorListAsesor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plantel plantel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel persistentPlantel = em.find(Plantel.class, plantel.getClavePlantel());
            List<Asignacionplan> asignacionplanListOld = persistentPlantel.getAsignacionplanList();
            List<Asignacionplan> asignacionplanListNew = plantel.getAsignacionplanList();
            List<Asignacionactividad> asignacionactividadListOld = persistentPlantel.getAsignacionactividadList();
            List<Asignacionactividad> asignacionactividadListNew = plantel.getAsignacionactividadList();
            List<Asesor> asesorListOld = persistentPlantel.getAsesorList();
            List<Asesor> asesorListNew = plantel.getAsesorList();
            List<Asignacionplan> attachedAsignacionplanListNew = new ArrayList<Asignacionplan>();
            for (Asignacionplan asignacionplanListNewAsignacionplanToAttach : asignacionplanListNew) {
                asignacionplanListNewAsignacionplanToAttach = em.getReference(asignacionplanListNewAsignacionplanToAttach.getClass(), asignacionplanListNewAsignacionplanToAttach.getClaveAsignacion());
                attachedAsignacionplanListNew.add(asignacionplanListNewAsignacionplanToAttach);
            }
            asignacionplanListNew = attachedAsignacionplanListNew;
            plantel.setAsignacionplanList(asignacionplanListNew);
            List<Asignacionactividad> attachedAsignacionactividadListNew = new ArrayList<Asignacionactividad>();
            for (Asignacionactividad asignacionactividadListNewAsignacionactividadToAttach : asignacionactividadListNew) {
                asignacionactividadListNewAsignacionactividadToAttach = em.getReference(asignacionactividadListNewAsignacionactividadToAttach.getClass(), asignacionactividadListNewAsignacionactividadToAttach.getClaveAsignacion());
                attachedAsignacionactividadListNew.add(asignacionactividadListNewAsignacionactividadToAttach);
            }
            asignacionactividadListNew = attachedAsignacionactividadListNew;
            plantel.setAsignacionactividadList(asignacionactividadListNew);
            List<Asesor> attachedAsesorListNew = new ArrayList<Asesor>();
            for (Asesor asesorListNewAsesorToAttach : asesorListNew) {
                asesorListNewAsesorToAttach = em.getReference(asesorListNewAsesorToAttach.getClass(), asesorListNewAsesorToAttach.getClaveAsesor());
                attachedAsesorListNew.add(asesorListNewAsesorToAttach);
            }
            asesorListNew = attachedAsesorListNew;
            plantel.setAsesorList(asesorListNew);
            plantel = em.merge(plantel);
            for (Asignacionplan asignacionplanListOldAsignacionplan : asignacionplanListOld) {
                if (!asignacionplanListNew.contains(asignacionplanListOldAsignacionplan)) {
                    asignacionplanListOldAsignacionplan.setClavePlantel(null);
                    asignacionplanListOldAsignacionplan = em.merge(asignacionplanListOldAsignacionplan);
                }
            }
            for (Asignacionplan asignacionplanListNewAsignacionplan : asignacionplanListNew) {
                if (!asignacionplanListOld.contains(asignacionplanListNewAsignacionplan)) {
                    Plantel oldClavePlantelOfAsignacionplanListNewAsignacionplan = asignacionplanListNewAsignacionplan.getClavePlantel();
                    asignacionplanListNewAsignacionplan.setClavePlantel(plantel);
                    asignacionplanListNewAsignacionplan = em.merge(asignacionplanListNewAsignacionplan);
                    if (oldClavePlantelOfAsignacionplanListNewAsignacionplan != null && !oldClavePlantelOfAsignacionplanListNewAsignacionplan.equals(plantel)) {
                        oldClavePlantelOfAsignacionplanListNewAsignacionplan.getAsignacionplanList().remove(asignacionplanListNewAsignacionplan);
                        oldClavePlantelOfAsignacionplanListNewAsignacionplan = em.merge(oldClavePlantelOfAsignacionplanListNewAsignacionplan);
                    }
                }
            }
            for (Asignacionactividad asignacionactividadListOldAsignacionactividad : asignacionactividadListOld) {
                if (!asignacionactividadListNew.contains(asignacionactividadListOldAsignacionactividad)) {
                    asignacionactividadListOldAsignacionactividad.setClavePlantel(null);
                    asignacionactividadListOldAsignacionactividad = em.merge(asignacionactividadListOldAsignacionactividad);
                }
            }
            for (Asignacionactividad asignacionactividadListNewAsignacionactividad : asignacionactividadListNew) {
                if (!asignacionactividadListOld.contains(asignacionactividadListNewAsignacionactividad)) {
                    Plantel oldClavePlantelOfAsignacionactividadListNewAsignacionactividad = asignacionactividadListNewAsignacionactividad.getClavePlantel();
                    asignacionactividadListNewAsignacionactividad.setClavePlantel(plantel);
                    asignacionactividadListNewAsignacionactividad = em.merge(asignacionactividadListNewAsignacionactividad);
                    if (oldClavePlantelOfAsignacionactividadListNewAsignacionactividad != null && !oldClavePlantelOfAsignacionactividadListNewAsignacionactividad.equals(plantel)) {
                        oldClavePlantelOfAsignacionactividadListNewAsignacionactividad.getAsignacionactividadList().remove(asignacionactividadListNewAsignacionactividad);
                        oldClavePlantelOfAsignacionactividadListNewAsignacionactividad = em.merge(oldClavePlantelOfAsignacionactividadListNewAsignacionactividad);
                    }
                }
            }
            for (Asesor asesorListOldAsesor : asesorListOld) {
                if (!asesorListNew.contains(asesorListOldAsesor)) {
                    asesorListOldAsesor.setClavePlantel(null);
                    asesorListOldAsesor = em.merge(asesorListOldAsesor);
                }
            }
            for (Asesor asesorListNewAsesor : asesorListNew) {
                if (!asesorListOld.contains(asesorListNewAsesor)) {
                    Plantel oldClavePlantelOfAsesorListNewAsesor = asesorListNewAsesor.getClavePlantel();
                    asesorListNewAsesor.setClavePlantel(plantel);
                    asesorListNewAsesor = em.merge(asesorListNewAsesor);
                    if (oldClavePlantelOfAsesorListNewAsesor != null && !oldClavePlantelOfAsesorListNewAsesor.equals(plantel)) {
                        oldClavePlantelOfAsesorListNewAsesor.getAsesorList().remove(asesorListNewAsesor);
                        oldClavePlantelOfAsesorListNewAsesor = em.merge(oldClavePlantelOfAsesorListNewAsesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = plantel.getClavePlantel();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel plantel;
            try {
                plantel = em.getReference(Plantel.class, id);
                plantel.getClavePlantel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plantel with id " + id + " no longer exists.", enfe);
            }
            List<Asignacionplan> asignacionplanList = plantel.getAsignacionplanList();
            for (Asignacionplan asignacionplanListAsignacionplan : asignacionplanList) {
                asignacionplanListAsignacionplan.setClavePlantel(null);
                asignacionplanListAsignacionplan = em.merge(asignacionplanListAsignacionplan);
            }
            List<Asignacionactividad> asignacionactividadList = plantel.getAsignacionactividadList();
            for (Asignacionactividad asignacionactividadListAsignacionactividad : asignacionactividadList) {
                asignacionactividadListAsignacionactividad.setClavePlantel(null);
                asignacionactividadListAsignacionactividad = em.merge(asignacionactividadListAsignacionactividad);
            }
            List<Asesor> asesorList = plantel.getAsesorList();
            for (Asesor asesorListAsesor : asesorList) {
                asesorListAsesor.setClavePlantel(null);
                asesorListAsesor = em.merge(asesorListAsesor);
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
