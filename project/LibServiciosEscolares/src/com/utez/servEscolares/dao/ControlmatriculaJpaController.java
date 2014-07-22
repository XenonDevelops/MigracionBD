/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Controlmatricula;
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
public class ControlmatriculaJpaController implements Serializable {

    public ControlmatriculaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Controlmatricula controlmatricula) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlmatricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Controlmatricula controlmatricula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlmatricula = em.merge(controlmatricula);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = controlmatricula.getControlMatricula();
                if (findControlmatricula(id) == null) {
                    throw new NonexistentEntityException("The controlmatricula with id " + id + " no longer exists.");
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
            Controlmatricula controlmatricula;
            try {
                controlmatricula = em.getReference(Controlmatricula.class, id);
                controlmatricula.getControlMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlmatricula with id " + id + " no longer exists.", enfe);
            }
            em.remove(controlmatricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Controlmatricula> findControlmatriculaEntities() {
        return findControlmatriculaEntities(true, -1, -1);
    }

    public List<Controlmatricula> findControlmatriculaEntities(int maxResults, int firstResult) {
        return findControlmatriculaEntities(false, maxResults, firstResult);
    }

    private List<Controlmatricula> findControlmatriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Controlmatricula as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Controlmatricula findControlmatricula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Controlmatricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getControlmatriculaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Controlmatricula as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
