/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Salidamaterial;
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
public class SalidamaterialJpaController implements Serializable {

    public SalidamaterialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salidamaterial salidamaterial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(salidamaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salidamaterial salidamaterial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            salidamaterial = em.merge(salidamaterial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salidamaterial.getClaveSalida();
                if (findSalidamaterial(id) == null) {
                    throw new NonexistentEntityException("The salidamaterial with id " + id + " no longer exists.");
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
            Salidamaterial salidamaterial;
            try {
                salidamaterial = em.getReference(Salidamaterial.class, id);
                salidamaterial.getClaveSalida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salidamaterial with id " + id + " no longer exists.", enfe);
            }
            em.remove(salidamaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salidamaterial> findSalidamaterialEntities() {
        return findSalidamaterialEntities(true, -1, -1);
    }

    public List<Salidamaterial> findSalidamaterialEntities(int maxResults, int firstResult) {
        return findSalidamaterialEntities(false, maxResults, firstResult);
    }

    private List<Salidamaterial> findSalidamaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Salidamaterial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Salidamaterial findSalidamaterial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salidamaterial.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalidamaterialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Salidamaterial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
