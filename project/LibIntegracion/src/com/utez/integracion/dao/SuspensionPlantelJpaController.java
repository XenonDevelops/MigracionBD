/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.SuspensionLabores;
import com.utez.integracion.entity.Plantel;
import com.utez.integracion.entity.SuspensionPlantel;
import com.utez.integracion.entity.SuspensionPlantelPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SuspensionPlantelJpaController implements Serializable {

    public SuspensionPlantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SuspensionPlantel suspensionPlantel) throws PreexistingEntityException, Exception {
        if (suspensionPlantel.getSuspensionPlantelPK() == null) {
            suspensionPlantel.setSuspensionPlantelPK(new SuspensionPlantelPK());
        }
        suspensionPlantel.getSuspensionPlantelPK().setIdPlantel(suspensionPlantel.getPlantel().getIdPlantel());
        suspensionPlantel.getSuspensionPlantelPK().setIdSuspension(suspensionPlantel.getSuspensionLabores().getIdSuspensionlabores());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuspensionLabores suspensionLabores = suspensionPlantel.getSuspensionLabores();
            if (suspensionLabores != null) {
                suspensionLabores = em.getReference(suspensionLabores.getClass(), suspensionLabores.getIdSuspensionlabores());
                suspensionPlantel.setSuspensionLabores(suspensionLabores);
            }
            Plantel plantel = suspensionPlantel.getPlantel();
            if (plantel != null) {
                plantel = em.getReference(plantel.getClass(), plantel.getIdPlantel());
                suspensionPlantel.setPlantel(plantel);
            }
            em.persist(suspensionPlantel);
            if (suspensionLabores != null) {
                suspensionLabores.getSuspensionPlantelList().add(suspensionPlantel);
                suspensionLabores = em.merge(suspensionLabores);
            }
            if (plantel != null) {
                plantel.getSuspensionPlantelList().add(suspensionPlantel);
                plantel = em.merge(plantel);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSuspensionPlantel(suspensionPlantel.getSuspensionPlantelPK()) != null) {
                throw new PreexistingEntityException("SuspensionPlantel " + suspensionPlantel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SuspensionPlantel suspensionPlantel) throws NonexistentEntityException, Exception {
        suspensionPlantel.getSuspensionPlantelPK().setIdPlantel(suspensionPlantel.getPlantel().getIdPlantel());
        suspensionPlantel.getSuspensionPlantelPK().setIdSuspension(suspensionPlantel.getSuspensionLabores().getIdSuspensionlabores());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuspensionPlantel persistentSuspensionPlantel = em.find(SuspensionPlantel.class, suspensionPlantel.getSuspensionPlantelPK());
            SuspensionLabores suspensionLaboresOld = persistentSuspensionPlantel.getSuspensionLabores();
            SuspensionLabores suspensionLaboresNew = suspensionPlantel.getSuspensionLabores();
            Plantel plantelOld = persistentSuspensionPlantel.getPlantel();
            Plantel plantelNew = suspensionPlantel.getPlantel();
            if (suspensionLaboresNew != null) {
                suspensionLaboresNew = em.getReference(suspensionLaboresNew.getClass(), suspensionLaboresNew.getIdSuspensionlabores());
                suspensionPlantel.setSuspensionLabores(suspensionLaboresNew);
            }
            if (plantelNew != null) {
                plantelNew = em.getReference(plantelNew.getClass(), plantelNew.getIdPlantel());
                suspensionPlantel.setPlantel(plantelNew);
            }
            suspensionPlantel = em.merge(suspensionPlantel);
            if (suspensionLaboresOld != null && !suspensionLaboresOld.equals(suspensionLaboresNew)) {
                suspensionLaboresOld.getSuspensionPlantelList().remove(suspensionPlantel);
                suspensionLaboresOld = em.merge(suspensionLaboresOld);
            }
            if (suspensionLaboresNew != null && !suspensionLaboresNew.equals(suspensionLaboresOld)) {
                suspensionLaboresNew.getSuspensionPlantelList().add(suspensionPlantel);
                suspensionLaboresNew = em.merge(suspensionLaboresNew);
            }
            if (plantelOld != null && !plantelOld.equals(plantelNew)) {
                plantelOld.getSuspensionPlantelList().remove(suspensionPlantel);
                plantelOld = em.merge(plantelOld);
            }
            if (plantelNew != null && !plantelNew.equals(plantelOld)) {
                plantelNew.getSuspensionPlantelList().add(suspensionPlantel);
                plantelNew = em.merge(plantelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SuspensionPlantelPK id = suspensionPlantel.getSuspensionPlantelPK();
                if (findSuspensionPlantel(id) == null) {
                    throw new NonexistentEntityException("The suspensionPlantel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SuspensionPlantelPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuspensionPlantel suspensionPlantel;
            try {
                suspensionPlantel = em.getReference(SuspensionPlantel.class, id);
                suspensionPlantel.getSuspensionPlantelPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suspensionPlantel with id " + id + " no longer exists.", enfe);
            }
            SuspensionLabores suspensionLabores = suspensionPlantel.getSuspensionLabores();
            if (suspensionLabores != null) {
                suspensionLabores.getSuspensionPlantelList().remove(suspensionPlantel);
                suspensionLabores = em.merge(suspensionLabores);
            }
            Plantel plantel = suspensionPlantel.getPlantel();
            if (plantel != null) {
                plantel.getSuspensionPlantelList().remove(suspensionPlantel);
                plantel = em.merge(plantel);
            }
            em.remove(suspensionPlantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SuspensionPlantel> findSuspensionPlantelEntities() {
        return findSuspensionPlantelEntities(true, -1, -1);
    }

    public List<SuspensionPlantel> findSuspensionPlantelEntities(int maxResults, int firstResult) {
        return findSuspensionPlantelEntities(false, maxResults, firstResult);
    }

    private List<SuspensionPlantel> findSuspensionPlantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SuspensionPlantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SuspensionPlantel findSuspensionPlantel(SuspensionPlantelPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SuspensionPlantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuspensionPlantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SuspensionPlantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
