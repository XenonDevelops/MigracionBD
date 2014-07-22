/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asignacionplan;
import java.util.ArrayList;
import java.util.List;
import com.utez.evaluacion.entity.Materiasdeplan;
import com.utez.evaluacion.entity.Plandeestudios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PlandeestudiosJpaController implements Serializable {

    public PlandeestudiosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Plandeestudios plandeestudios) throws PreexistingEntityException, Exception {
        if (plandeestudios.getAsignacionplanList() == null) {
            plandeestudios.setAsignacionplanList(new ArrayList<Asignacionplan>());
        }
        if (plandeestudios.getMateriasdeplanList() == null) {
            plandeestudios.setMateriasdeplanList(new ArrayList<Materiasdeplan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignacionplan> attachedAsignacionplanList = new ArrayList<Asignacionplan>();
            for (Asignacionplan asignacionplanListAsignacionplanToAttach : plandeestudios.getAsignacionplanList()) {
                asignacionplanListAsignacionplanToAttach = em.getReference(asignacionplanListAsignacionplanToAttach.getClass(), asignacionplanListAsignacionplanToAttach.getClaveAsignacion());
                attachedAsignacionplanList.add(asignacionplanListAsignacionplanToAttach);
            }
            plandeestudios.setAsignacionplanList(attachedAsignacionplanList);
            List<Materiasdeplan> attachedMateriasdeplanList = new ArrayList<Materiasdeplan>();
            for (Materiasdeplan materiasdeplanListMateriasdeplanToAttach : plandeestudios.getMateriasdeplanList()) {
                materiasdeplanListMateriasdeplanToAttach = em.getReference(materiasdeplanListMateriasdeplanToAttach.getClass(), materiasdeplanListMateriasdeplanToAttach.getClaveMateria());
                attachedMateriasdeplanList.add(materiasdeplanListMateriasdeplanToAttach);
            }
            plandeestudios.setMateriasdeplanList(attachedMateriasdeplanList);
            em.persist(plandeestudios);
            for (Asignacionplan asignacionplanListAsignacionplan : plandeestudios.getAsignacionplanList()) {
                Plandeestudios oldRevoeOfAsignacionplanListAsignacionplan = asignacionplanListAsignacionplan.getRevoe();
                asignacionplanListAsignacionplan.setRevoe(plandeestudios);
                asignacionplanListAsignacionplan = em.merge(asignacionplanListAsignacionplan);
                if (oldRevoeOfAsignacionplanListAsignacionplan != null) {
                    oldRevoeOfAsignacionplanListAsignacionplan.getAsignacionplanList().remove(asignacionplanListAsignacionplan);
                    oldRevoeOfAsignacionplanListAsignacionplan = em.merge(oldRevoeOfAsignacionplanListAsignacionplan);
                }
            }
            for (Materiasdeplan materiasdeplanListMateriasdeplan : plandeestudios.getMateriasdeplanList()) {
                Plandeestudios oldRevoeOfMateriasdeplanListMateriasdeplan = materiasdeplanListMateriasdeplan.getRevoe();
                materiasdeplanListMateriasdeplan.setRevoe(plandeestudios);
                materiasdeplanListMateriasdeplan = em.merge(materiasdeplanListMateriasdeplan);
                if (oldRevoeOfMateriasdeplanListMateriasdeplan != null) {
                    oldRevoeOfMateriasdeplanListMateriasdeplan.getMateriasdeplanList().remove(materiasdeplanListMateriasdeplan);
                    oldRevoeOfMateriasdeplanListMateriasdeplan = em.merge(oldRevoeOfMateriasdeplanListMateriasdeplan);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlandeestudios(plandeestudios.getRevoe()) != null) {
                throw new PreexistingEntityException("Plandeestudios " + plandeestudios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Plandeestudios plandeestudios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plandeestudios persistentPlandeestudios = em.find(Plandeestudios.class, plandeestudios.getRevoe());
            List<Asignacionplan> asignacionplanListOld = persistentPlandeestudios.getAsignacionplanList();
            List<Asignacionplan> asignacionplanListNew = plandeestudios.getAsignacionplanList();
            List<Materiasdeplan> materiasdeplanListOld = persistentPlandeestudios.getMateriasdeplanList();
            List<Materiasdeplan> materiasdeplanListNew = plandeestudios.getMateriasdeplanList();
            List<Asignacionplan> attachedAsignacionplanListNew = new ArrayList<Asignacionplan>();
            for (Asignacionplan asignacionplanListNewAsignacionplanToAttach : asignacionplanListNew) {
                asignacionplanListNewAsignacionplanToAttach = em.getReference(asignacionplanListNewAsignacionplanToAttach.getClass(), asignacionplanListNewAsignacionplanToAttach.getClaveAsignacion());
                attachedAsignacionplanListNew.add(asignacionplanListNewAsignacionplanToAttach);
            }
            asignacionplanListNew = attachedAsignacionplanListNew;
            plandeestudios.setAsignacionplanList(asignacionplanListNew);
            List<Materiasdeplan> attachedMateriasdeplanListNew = new ArrayList<Materiasdeplan>();
            for (Materiasdeplan materiasdeplanListNewMateriasdeplanToAttach : materiasdeplanListNew) {
                materiasdeplanListNewMateriasdeplanToAttach = em.getReference(materiasdeplanListNewMateriasdeplanToAttach.getClass(), materiasdeplanListNewMateriasdeplanToAttach.getClaveMateria());
                attachedMateriasdeplanListNew.add(materiasdeplanListNewMateriasdeplanToAttach);
            }
            materiasdeplanListNew = attachedMateriasdeplanListNew;
            plandeestudios.setMateriasdeplanList(materiasdeplanListNew);
            plandeestudios = em.merge(plandeestudios);
            for (Asignacionplan asignacionplanListOldAsignacionplan : asignacionplanListOld) {
                if (!asignacionplanListNew.contains(asignacionplanListOldAsignacionplan)) {
                    asignacionplanListOldAsignacionplan.setRevoe(null);
                    asignacionplanListOldAsignacionplan = em.merge(asignacionplanListOldAsignacionplan);
                }
            }
            for (Asignacionplan asignacionplanListNewAsignacionplan : asignacionplanListNew) {
                if (!asignacionplanListOld.contains(asignacionplanListNewAsignacionplan)) {
                    Plandeestudios oldRevoeOfAsignacionplanListNewAsignacionplan = asignacionplanListNewAsignacionplan.getRevoe();
                    asignacionplanListNewAsignacionplan.setRevoe(plandeestudios);
                    asignacionplanListNewAsignacionplan = em.merge(asignacionplanListNewAsignacionplan);
                    if (oldRevoeOfAsignacionplanListNewAsignacionplan != null && !oldRevoeOfAsignacionplanListNewAsignacionplan.equals(plandeestudios)) {
                        oldRevoeOfAsignacionplanListNewAsignacionplan.getAsignacionplanList().remove(asignacionplanListNewAsignacionplan);
                        oldRevoeOfAsignacionplanListNewAsignacionplan = em.merge(oldRevoeOfAsignacionplanListNewAsignacionplan);
                    }
                }
            }
            for (Materiasdeplan materiasdeplanListOldMateriasdeplan : materiasdeplanListOld) {
                if (!materiasdeplanListNew.contains(materiasdeplanListOldMateriasdeplan)) {
                    materiasdeplanListOldMateriasdeplan.setRevoe(null);
                    materiasdeplanListOldMateriasdeplan = em.merge(materiasdeplanListOldMateriasdeplan);
                }
            }
            for (Materiasdeplan materiasdeplanListNewMateriasdeplan : materiasdeplanListNew) {
                if (!materiasdeplanListOld.contains(materiasdeplanListNewMateriasdeplan)) {
                    Plandeestudios oldRevoeOfMateriasdeplanListNewMateriasdeplan = materiasdeplanListNewMateriasdeplan.getRevoe();
                    materiasdeplanListNewMateriasdeplan.setRevoe(plandeestudios);
                    materiasdeplanListNewMateriasdeplan = em.merge(materiasdeplanListNewMateriasdeplan);
                    if (oldRevoeOfMateriasdeplanListNewMateriasdeplan != null && !oldRevoeOfMateriasdeplanListNewMateriasdeplan.equals(plandeestudios)) {
                        oldRevoeOfMateriasdeplanListNewMateriasdeplan.getMateriasdeplanList().remove(materiasdeplanListNewMateriasdeplan);
                        oldRevoeOfMateriasdeplanListNewMateriasdeplan = em.merge(oldRevoeOfMateriasdeplanListNewMateriasdeplan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = plandeestudios.getRevoe();
                if (findPlandeestudios(id) == null) {
                    throw new NonexistentEntityException("The plandeestudios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plandeestudios plandeestudios;
            try {
                plandeestudios = em.getReference(Plandeestudios.class, id);
                plandeestudios.getRevoe();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plandeestudios with id " + id + " no longer exists.", enfe);
            }
            List<Asignacionplan> asignacionplanList = plandeestudios.getAsignacionplanList();
            for (Asignacionplan asignacionplanListAsignacionplan : asignacionplanList) {
                asignacionplanListAsignacionplan.setRevoe(null);
                asignacionplanListAsignacionplan = em.merge(asignacionplanListAsignacionplan);
            }
            List<Materiasdeplan> materiasdeplanList = plandeestudios.getMateriasdeplanList();
            for (Materiasdeplan materiasdeplanListMateriasdeplan : materiasdeplanList) {
                materiasdeplanListMateriasdeplan.setRevoe(null);
                materiasdeplanListMateriasdeplan = em.merge(materiasdeplanListMateriasdeplan);
            }
            em.remove(plandeestudios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Plandeestudios> findPlandeestudiosEntities() {
        return findPlandeestudiosEntities(true, -1, -1);
    }

    public List<Plandeestudios> findPlandeestudiosEntities(int maxResults, int firstResult) {
        return findPlandeestudiosEntities(false, maxResults, firstResult);
    }

    private List<Plandeestudios> findPlandeestudiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Plandeestudios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Plandeestudios findPlandeestudios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Plandeestudios.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlandeestudiosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Plandeestudios as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
