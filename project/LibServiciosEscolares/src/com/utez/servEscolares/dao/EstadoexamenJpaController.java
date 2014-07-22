/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Estadoexamen;
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
public class EstadoexamenJpaController implements Serializable {

    public EstadoexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoexamen estadoexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoexamen estadoexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoexamen = em.merge(estadoexamen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoexamen.getClaveEstadoexamen();
                if (findEstadoexamen(id) == null) {
                    throw new NonexistentEntityException("The estadoexamen with id " + id + " no longer exists.");
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
            Estadoexamen estadoexamen;
            try {
                estadoexamen = em.getReference(Estadoexamen.class, id);
                estadoexamen.getClaveEstadoexamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoexamen with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoexamen> findEstadoexamenEntities() {
        return findEstadoexamenEntities(true, -1, -1);
    }

    public List<Estadoexamen> findEstadoexamenEntities(int maxResults, int firstResult) {
        return findEstadoexamenEntities(false, maxResults, firstResult);
    }

    private List<Estadoexamen> findEstadoexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Estadoexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estadoexamen findEstadoexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Estadoexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
