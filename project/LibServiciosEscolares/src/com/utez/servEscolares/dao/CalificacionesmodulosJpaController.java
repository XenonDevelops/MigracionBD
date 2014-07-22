/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Calificacionesmodulos;
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
public class CalificacionesmodulosJpaController implements Serializable {

    public CalificacionesmodulosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacionesmodulos calificacionesmodulos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(calificacionesmodulos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacionesmodulos calificacionesmodulos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calificacionesmodulos = em.merge(calificacionesmodulos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calificacionesmodulos.getClaveCalificacionesmodulos();
                if (findCalificacionesmodulos(id) == null) {
                    throw new NonexistentEntityException("The calificacionesmodulos with id " + id + " no longer exists.");
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
            Calificacionesmodulos calificacionesmodulos;
            try {
                calificacionesmodulos = em.getReference(Calificacionesmodulos.class, id);
                calificacionesmodulos.getClaveCalificacionesmodulos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacionesmodulos with id " + id + " no longer exists.", enfe);
            }
            em.remove(calificacionesmodulos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacionesmodulos> findCalificacionesmodulosEntities() {
        return findCalificacionesmodulosEntities(true, -1, -1);
    }

    public List<Calificacionesmodulos> findCalificacionesmodulosEntities(int maxResults, int firstResult) {
        return findCalificacionesmodulosEntities(false, maxResults, firstResult);
    }

    private List<Calificacionesmodulos> findCalificacionesmodulosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calificacionesmodulos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacionesmodulos findCalificacionesmodulos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacionesmodulos.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionesmodulosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calificacionesmodulos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
