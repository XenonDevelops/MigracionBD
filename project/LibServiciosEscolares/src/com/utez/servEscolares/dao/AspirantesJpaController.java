/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Aspirantes;
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
public class AspirantesJpaController implements Serializable {

    public AspirantesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aspirantes aspirantes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aspirantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aspirantes aspirantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aspirantes = em.merge(aspirantes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aspirantes.getClaveAspirante();
                if (findAspirantes(id) == null) {
                    throw new NonexistentEntityException("The aspirantes with id " + id + " no longer exists.");
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
            Aspirantes aspirantes;
            try {
                aspirantes = em.getReference(Aspirantes.class, id);
                aspirantes.getClaveAspirante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aspirantes with id " + id + " no longer exists.", enfe);
            }
            em.remove(aspirantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aspirantes> findAspirantesEntities() {
        return findAspirantesEntities(true, -1, -1);
    }

    public List<Aspirantes> findAspirantesEntities(int maxResults, int firstResult) {
        return findAspirantesEntities(false, maxResults, firstResult);
    }

    private List<Aspirantes> findAspirantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aspirantes as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aspirantes findAspirantes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aspirantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getAspirantesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Aspirantes as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
