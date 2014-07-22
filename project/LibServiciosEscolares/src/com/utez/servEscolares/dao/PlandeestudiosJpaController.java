/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Plandeestudios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(plandeestudios);
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
            plandeestudios = em.merge(plandeestudios);
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
