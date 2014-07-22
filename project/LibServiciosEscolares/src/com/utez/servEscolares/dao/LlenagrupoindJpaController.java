/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Llenagrupoind;
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
public class LlenagrupoindJpaController implements Serializable {

    public LlenagrupoindJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Llenagrupoind llenagrupoind) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(llenagrupoind);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Llenagrupoind llenagrupoind) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            llenagrupoind = em.merge(llenagrupoind);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = llenagrupoind.getClaveLlenagrupoind();
                if (findLlenagrupoind(id) == null) {
                    throw new NonexistentEntityException("The llenagrupoind with id " + id + " no longer exists.");
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
            Llenagrupoind llenagrupoind;
            try {
                llenagrupoind = em.getReference(Llenagrupoind.class, id);
                llenagrupoind.getClaveLlenagrupoind();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The llenagrupoind with id " + id + " no longer exists.", enfe);
            }
            em.remove(llenagrupoind);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Llenagrupoind> findLlenagrupoindEntities() {
        return findLlenagrupoindEntities(true, -1, -1);
    }

    public List<Llenagrupoind> findLlenagrupoindEntities(int maxResults, int firstResult) {
        return findLlenagrupoindEntities(false, maxResults, firstResult);
    }

    private List<Llenagrupoind> findLlenagrupoindEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Llenagrupoind as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Llenagrupoind findLlenagrupoind(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Llenagrupoind.class, id);
        } finally {
            em.close();
        }
    }

    public int getLlenagrupoindCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Llenagrupoind as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
