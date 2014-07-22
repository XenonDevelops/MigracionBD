/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Fechasexam;
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
public class FechasexamJpaController implements Serializable {

    public FechasexamJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechasexam fechasexam) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fechasexam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechasexam fechasexam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fechasexam = em.merge(fechasexam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechasexam.getClaveFechasexam();
                if (findFechasexam(id) == null) {
                    throw new NonexistentEntityException("The fechasexam with id " + id + " no longer exists.");
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
            Fechasexam fechasexam;
            try {
                fechasexam = em.getReference(Fechasexam.class, id);
                fechasexam.getClaveFechasexam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechasexam with id " + id + " no longer exists.", enfe);
            }
            em.remove(fechasexam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechasexam> findFechasexamEntities() {
        return findFechasexamEntities(true, -1, -1);
    }

    public List<Fechasexam> findFechasexamEntities(int maxResults, int firstResult) {
        return findFechasexamEntities(false, maxResults, firstResult);
    }

    private List<Fechasexam> findFechasexamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechasexam as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechasexam findFechasexam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechasexam.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechasexamCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechasexam as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
