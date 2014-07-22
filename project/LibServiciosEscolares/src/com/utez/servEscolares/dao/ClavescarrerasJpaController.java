/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Clavescarreras;
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
public class ClavescarrerasJpaController implements Serializable {

    public ClavescarrerasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clavescarreras clavescarreras) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clavescarreras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clavescarreras clavescarreras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clavescarreras = em.merge(clavescarreras);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clavescarreras.getClaveClavescarreras();
                if (findClavescarreras(id) == null) {
                    throw new NonexistentEntityException("The clavescarreras with id " + id + " no longer exists.");
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
            Clavescarreras clavescarreras;
            try {
                clavescarreras = em.getReference(Clavescarreras.class, id);
                clavescarreras.getClaveClavescarreras();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clavescarreras with id " + id + " no longer exists.", enfe);
            }
            em.remove(clavescarreras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clavescarreras> findClavescarrerasEntities() {
        return findClavescarrerasEntities(true, -1, -1);
    }

    public List<Clavescarreras> findClavescarrerasEntities(int maxResults, int firstResult) {
        return findClavescarrerasEntities(false, maxResults, firstResult);
    }

    private List<Clavescarreras> findClavescarrerasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Clavescarreras as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clavescarreras findClavescarreras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clavescarreras.class, id);
        } finally {
            em.close();
        }
    }

    public int getClavescarrerasCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Clavescarreras as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
