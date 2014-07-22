/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Examenextemporaneo;
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
public class ExamenextemporaneoJpaController implements Serializable {

    public ExamenextemporaneoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examenextemporaneo examenextemporaneo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(examenextemporaneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examenextemporaneo examenextemporaneo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            examenextemporaneo = em.merge(examenextemporaneo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examenextemporaneo.getClaveExaExtem();
                if (findExamenextemporaneo(id) == null) {
                    throw new NonexistentEntityException("The examenextemporaneo with id " + id + " no longer exists.");
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
            Examenextemporaneo examenextemporaneo;
            try {
                examenextemporaneo = em.getReference(Examenextemporaneo.class, id);
                examenextemporaneo.getClaveExaExtem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenextemporaneo with id " + id + " no longer exists.", enfe);
            }
            em.remove(examenextemporaneo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examenextemporaneo> findExamenextemporaneoEntities() {
        return findExamenextemporaneoEntities(true, -1, -1);
    }

    public List<Examenextemporaneo> findExamenextemporaneoEntities(int maxResults, int firstResult) {
        return findExamenextemporaneoEntities(false, maxResults, firstResult);
    }

    private List<Examenextemporaneo> findExamenextemporaneoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Examenextemporaneo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Examenextemporaneo findExamenextemporaneo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examenextemporaneo.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenextemporaneoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Examenextemporaneo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
