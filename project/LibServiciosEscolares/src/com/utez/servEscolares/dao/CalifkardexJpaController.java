/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Califkardex;
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
public class CalifkardexJpaController implements Serializable {

    public CalifkardexJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Califkardex califkardex) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(califkardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Califkardex califkardex) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            califkardex = em.merge(califkardex);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = califkardex.getClave();
                if (findCalifkardex(id) == null) {
                    throw new NonexistentEntityException("The califkardex with id " + id + " no longer exists.");
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
            Califkardex califkardex;
            try {
                califkardex = em.getReference(Califkardex.class, id);
                califkardex.getClave();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The califkardex with id " + id + " no longer exists.", enfe);
            }
            em.remove(califkardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Califkardex> findCalifkardexEntities() {
        return findCalifkardexEntities(true, -1, -1);
    }

    public List<Califkardex> findCalifkardexEntities(int maxResults, int firstResult) {
        return findCalifkardexEntities(false, maxResults, firstResult);
    }

    private List<Califkardex> findCalifkardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Califkardex as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Califkardex findCalifkardex(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Califkardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalifkardexCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Califkardex as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
