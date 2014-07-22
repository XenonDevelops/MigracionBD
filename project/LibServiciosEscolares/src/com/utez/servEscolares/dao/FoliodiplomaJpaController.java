/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Foliodiploma;
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
public class FoliodiplomaJpaController implements Serializable {

    public FoliodiplomaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Foliodiploma foliodiploma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(foliodiploma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Foliodiploma foliodiploma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            foliodiploma = em.merge(foliodiploma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = foliodiploma.getFoliodiploma();
                if (findFoliodiploma(id) == null) {
                    throw new NonexistentEntityException("The foliodiploma with id " + id + " no longer exists.");
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
            Foliodiploma foliodiploma;
            try {
                foliodiploma = em.getReference(Foliodiploma.class, id);
                foliodiploma.getFoliodiploma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The foliodiploma with id " + id + " no longer exists.", enfe);
            }
            em.remove(foliodiploma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Foliodiploma> findFoliodiplomaEntities() {
        return findFoliodiplomaEntities(true, -1, -1);
    }

    public List<Foliodiploma> findFoliodiplomaEntities(int maxResults, int firstResult) {
        return findFoliodiplomaEntities(false, maxResults, firstResult);
    }

    private List<Foliodiploma> findFoliodiplomaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Foliodiploma as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Foliodiploma findFoliodiploma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Foliodiploma.class, id);
        } finally {
            em.close();
        }
    }

    public int getFoliodiplomaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Foliodiploma as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
