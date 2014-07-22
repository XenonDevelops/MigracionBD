/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AsignacionTipotitulacionplan;
import com.utez.integracion.entity.AsignacionTipotitulacionplanPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoTitulacion;
import com.utez.integracion.entity.PlanEstudio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionTipotitulacionplanJpaController implements Serializable {

    public AsignacionTipotitulacionplanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionTipotitulacionplan asignacionTipotitulacionplan) throws PreexistingEntityException, Exception {
        if (asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK() == null) {
            asignacionTipotitulacionplan.setAsignacionTipotitulacionplanPK(new AsignacionTipotitulacionplanPK());
        }
        asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK().setIdTipotitulacion(asignacionTipotitulacionplan.getTipoTitulacion().getIdTipotitulacion());
        asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK().setIdPlanestudio(asignacionTipotitulacionplan.getPlanEstudio().getIdPlanestudio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTitulacion tipoTitulacion = asignacionTipotitulacionplan.getTipoTitulacion();
            if (tipoTitulacion != null) {
                tipoTitulacion = em.getReference(tipoTitulacion.getClass(), tipoTitulacion.getIdTipotitulacion());
                asignacionTipotitulacionplan.setTipoTitulacion(tipoTitulacion);
            }
            PlanEstudio planEstudio = asignacionTipotitulacionplan.getPlanEstudio();
            if (planEstudio != null) {
                planEstudio = em.getReference(planEstudio.getClass(), planEstudio.getIdPlanestudio());
                asignacionTipotitulacionplan.setPlanEstudio(planEstudio);
            }
            em.persist(asignacionTipotitulacionplan);
            if (tipoTitulacion != null) {
                tipoTitulacion.getAsignacionTipotitulacionplanList().add(asignacionTipotitulacionplan);
                tipoTitulacion = em.merge(tipoTitulacion);
            }
            if (planEstudio != null) {
                planEstudio.getAsignacionTipotitulacionplanList().add(asignacionTipotitulacionplan);
                planEstudio = em.merge(planEstudio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignacionTipotitulacionplan(asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK()) != null) {
                throw new PreexistingEntityException("AsignacionTipotitulacionplan " + asignacionTipotitulacionplan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionTipotitulacionplan asignacionTipotitulacionplan) throws NonexistentEntityException, Exception {
        asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK().setIdTipotitulacion(asignacionTipotitulacionplan.getTipoTitulacion().getIdTipotitulacion());
        asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK().setIdPlanestudio(asignacionTipotitulacionplan.getPlanEstudio().getIdPlanestudio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionTipotitulacionplan persistentAsignacionTipotitulacionplan = em.find(AsignacionTipotitulacionplan.class, asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK());
            TipoTitulacion tipoTitulacionOld = persistentAsignacionTipotitulacionplan.getTipoTitulacion();
            TipoTitulacion tipoTitulacionNew = asignacionTipotitulacionplan.getTipoTitulacion();
            PlanEstudio planEstudioOld = persistentAsignacionTipotitulacionplan.getPlanEstudio();
            PlanEstudio planEstudioNew = asignacionTipotitulacionplan.getPlanEstudio();
            if (tipoTitulacionNew != null) {
                tipoTitulacionNew = em.getReference(tipoTitulacionNew.getClass(), tipoTitulacionNew.getIdTipotitulacion());
                asignacionTipotitulacionplan.setTipoTitulacion(tipoTitulacionNew);
            }
            if (planEstudioNew != null) {
                planEstudioNew = em.getReference(planEstudioNew.getClass(), planEstudioNew.getIdPlanestudio());
                asignacionTipotitulacionplan.setPlanEstudio(planEstudioNew);
            }
            asignacionTipotitulacionplan = em.merge(asignacionTipotitulacionplan);
            if (tipoTitulacionOld != null && !tipoTitulacionOld.equals(tipoTitulacionNew)) {
                tipoTitulacionOld.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplan);
                tipoTitulacionOld = em.merge(tipoTitulacionOld);
            }
            if (tipoTitulacionNew != null && !tipoTitulacionNew.equals(tipoTitulacionOld)) {
                tipoTitulacionNew.getAsignacionTipotitulacionplanList().add(asignacionTipotitulacionplan);
                tipoTitulacionNew = em.merge(tipoTitulacionNew);
            }
            if (planEstudioOld != null && !planEstudioOld.equals(planEstudioNew)) {
                planEstudioOld.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplan);
                planEstudioOld = em.merge(planEstudioOld);
            }
            if (planEstudioNew != null && !planEstudioNew.equals(planEstudioOld)) {
                planEstudioNew.getAsignacionTipotitulacionplanList().add(asignacionTipotitulacionplan);
                planEstudioNew = em.merge(planEstudioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignacionTipotitulacionplanPK id = asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK();
                if (findAsignacionTipotitulacionplan(id) == null) {
                    throw new NonexistentEntityException("The asignacionTipotitulacionplan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignacionTipotitulacionplanPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionTipotitulacionplan asignacionTipotitulacionplan;
            try {
                asignacionTipotitulacionplan = em.getReference(AsignacionTipotitulacionplan.class, id);
                asignacionTipotitulacionplan.getAsignacionTipotitulacionplanPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionTipotitulacionplan with id " + id + " no longer exists.", enfe);
            }
            TipoTitulacion tipoTitulacion = asignacionTipotitulacionplan.getTipoTitulacion();
            if (tipoTitulacion != null) {
                tipoTitulacion.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplan);
                tipoTitulacion = em.merge(tipoTitulacion);
            }
            PlanEstudio planEstudio = asignacionTipotitulacionplan.getPlanEstudio();
            if (planEstudio != null) {
                planEstudio.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplan);
                planEstudio = em.merge(planEstudio);
            }
            em.remove(asignacionTipotitulacionplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionTipotitulacionplan> findAsignacionTipotitulacionplanEntities() {
        return findAsignacionTipotitulacionplanEntities(true, -1, -1);
    }

    public List<AsignacionTipotitulacionplan> findAsignacionTipotitulacionplanEntities(int maxResults, int firstResult) {
        return findAsignacionTipotitulacionplanEntities(false, maxResults, firstResult);
    }

    private List<AsignacionTipotitulacionplan> findAsignacionTipotitulacionplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionTipotitulacionplan as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionTipotitulacionplan findAsignacionTipotitulacionplan(AsignacionTipotitulacionplanPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionTipotitulacionplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionTipotitulacionplanCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionTipotitulacionplan as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
