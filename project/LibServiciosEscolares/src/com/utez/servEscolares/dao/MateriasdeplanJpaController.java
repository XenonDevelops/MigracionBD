/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Materiasdeplan;
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
public class MateriasdeplanJpaController implements Serializable {

    public MateriasdeplanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiasdeplan materiasdeplan) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(materiasdeplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiasdeplan materiasdeplan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            materiasdeplan = em.merge(materiasdeplan);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiasdeplan.getClaveMateria();
                if (findMateriasdeplan(id) == null) {
                    throw new NonexistentEntityException("The materiasdeplan with id " + id + " no longer exists.");
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
            Materiasdeplan materiasdeplan;
            try {
                materiasdeplan = em.getReference(Materiasdeplan.class, id);
                materiasdeplan.getClaveMateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiasdeplan with id " + id + " no longer exists.", enfe);
            }
            em.remove(materiasdeplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiasdeplan> findMateriasdeplanEntities() {
        return findMateriasdeplanEntities(true, -1, -1);
    }

    public List<Materiasdeplan> findMateriasdeplanEntities(int maxResults, int firstResult) {
        return findMateriasdeplanEntities(false, maxResults, firstResult);
    }

    private List<Materiasdeplan> findMateriasdeplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Materiasdeplan as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materiasdeplan findMateriasdeplan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiasdeplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriasdeplanCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Materiasdeplan as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
