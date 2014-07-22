/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Controldocumentos;
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
public class ControldocumentosJpaController implements Serializable {

    public ControldocumentosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Controldocumentos controldocumentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controldocumentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Controldocumentos controldocumentos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controldocumentos = em.merge(controldocumentos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = controldocumentos.getClaveControldocumentos();
                if (findControldocumentos(id) == null) {
                    throw new NonexistentEntityException("The controldocumentos with id " + id + " no longer exists.");
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
            Controldocumentos controldocumentos;
            try {
                controldocumentos = em.getReference(Controldocumentos.class, id);
                controldocumentos.getClaveControldocumentos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controldocumentos with id " + id + " no longer exists.", enfe);
            }
            em.remove(controldocumentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Controldocumentos> findControldocumentosEntities() {
        return findControldocumentosEntities(true, -1, -1);
    }

    public List<Controldocumentos> findControldocumentosEntities(int maxResults, int firstResult) {
        return findControldocumentosEntities(false, maxResults, firstResult);
    }

    private List<Controldocumentos> findControldocumentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Controldocumentos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Controldocumentos findControldocumentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Controldocumentos.class, id);
        } finally {
            em.close();
        }
    }

    public int getControldocumentosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Controldocumentos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
