/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Seguimiento;
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
public class SeguimientoJpaController implements Serializable {

    public SeguimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Seguimiento seguimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(seguimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seguimiento seguimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seguimiento = em.merge(seguimiento);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seguimiento.getClaveSeguimiento();
                if (findSeguimiento(id) == null) {
                    throw new NonexistentEntityException("The seguimiento with id " + id + " no longer exists.");
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
            Seguimiento seguimiento;
            try {
                seguimiento = em.getReference(Seguimiento.class, id);
                seguimiento.getClaveSeguimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguimiento with id " + id + " no longer exists.", enfe);
            }
            em.remove(seguimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seguimiento> findSeguimientoEntities() {
        return findSeguimientoEntities(true, -1, -1);
    }

    public List<Seguimiento> findSeguimientoEntities(int maxResults, int firstResult) {
        return findSeguimientoEntities(false, maxResults, firstResult);
    }

    private List<Seguimiento> findSeguimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Seguimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Seguimiento findSeguimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seguimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Seguimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
