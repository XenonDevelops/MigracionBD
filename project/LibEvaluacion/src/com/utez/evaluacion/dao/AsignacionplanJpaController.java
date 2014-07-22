/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Asignacionplan;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Plandeestudios;
import com.utez.evaluacion.entity.Plantel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionplanJpaController implements Serializable {

    public AsignacionplanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignacionplan asignacionplan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plandeestudios revoe = asignacionplan.getRevoe();
            if (revoe != null) {
                revoe = em.getReference(revoe.getClass(), revoe.getRevoe());
                asignacionplan.setRevoe(revoe);
            }
            Plantel clavePlantel = asignacionplan.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel = em.getReference(clavePlantel.getClass(), clavePlantel.getClavePlantel());
                asignacionplan.setClavePlantel(clavePlantel);
            }
            em.persist(asignacionplan);
            if (revoe != null) {
                revoe.getAsignacionplanList().add(asignacionplan);
                revoe = em.merge(revoe);
            }
            if (clavePlantel != null) {
                clavePlantel.getAsignacionplanList().add(asignacionplan);
                clavePlantel = em.merge(clavePlantel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignacionplan asignacionplan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignacionplan persistentAsignacionplan = em.find(Asignacionplan.class, asignacionplan.getClaveAsignacion());
            Plandeestudios revoeOld = persistentAsignacionplan.getRevoe();
            Plandeestudios revoeNew = asignacionplan.getRevoe();
            Plantel clavePlantelOld = persistentAsignacionplan.getClavePlantel();
            Plantel clavePlantelNew = asignacionplan.getClavePlantel();
            if (revoeNew != null) {
                revoeNew = em.getReference(revoeNew.getClass(), revoeNew.getRevoe());
                asignacionplan.setRevoe(revoeNew);
            }
            if (clavePlantelNew != null) {
                clavePlantelNew = em.getReference(clavePlantelNew.getClass(), clavePlantelNew.getClavePlantel());
                asignacionplan.setClavePlantel(clavePlantelNew);
            }
            asignacionplan = em.merge(asignacionplan);
            if (revoeOld != null && !revoeOld.equals(revoeNew)) {
                revoeOld.getAsignacionplanList().remove(asignacionplan);
                revoeOld = em.merge(revoeOld);
            }
            if (revoeNew != null && !revoeNew.equals(revoeOld)) {
                revoeNew.getAsignacionplanList().add(asignacionplan);
                revoeNew = em.merge(revoeNew);
            }
            if (clavePlantelOld != null && !clavePlantelOld.equals(clavePlantelNew)) {
                clavePlantelOld.getAsignacionplanList().remove(asignacionplan);
                clavePlantelOld = em.merge(clavePlantelOld);
            }
            if (clavePlantelNew != null && !clavePlantelNew.equals(clavePlantelOld)) {
                clavePlantelNew.getAsignacionplanList().add(asignacionplan);
                clavePlantelNew = em.merge(clavePlantelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignacionplan.getClaveAsignacion();
                if (findAsignacionplan(id) == null) {
                    throw new NonexistentEntityException("The asignacionplan with id " + id + " no longer exists.");
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
            Asignacionplan asignacionplan;
            try {
                asignacionplan = em.getReference(Asignacionplan.class, id);
                asignacionplan.getClaveAsignacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionplan with id " + id + " no longer exists.", enfe);
            }
            Plandeestudios revoe = asignacionplan.getRevoe();
            if (revoe != null) {
                revoe.getAsignacionplanList().remove(asignacionplan);
                revoe = em.merge(revoe);
            }
            Plantel clavePlantel = asignacionplan.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel.getAsignacionplanList().remove(asignacionplan);
                clavePlantel = em.merge(clavePlantel);
            }
            em.remove(asignacionplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignacionplan> findAsignacionplanEntities() {
        return findAsignacionplanEntities(true, -1, -1);
    }

    public List<Asignacionplan> findAsignacionplanEntities(int maxResults, int firstResult) {
        return findAsignacionplanEntities(false, maxResults, firstResult);
    }

    private List<Asignacionplan> findAsignacionplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignacionplan as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignacionplan findAsignacionplan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignacionplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionplanCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignacionplan as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
