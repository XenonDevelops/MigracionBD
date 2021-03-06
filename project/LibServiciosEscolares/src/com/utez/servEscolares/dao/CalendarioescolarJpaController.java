/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Calendarioescolar;
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
public class CalendarioescolarJpaController implements Serializable {

    public CalendarioescolarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calendarioescolar calendarioescolar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(calendarioescolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calendarioescolar calendarioescolar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            calendarioescolar = em.merge(calendarioescolar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calendarioescolar.getClaveCalendario();
                if (findCalendarioescolar(id) == null) {
                    throw new NonexistentEntityException("The calendarioescolar with id " + id + " no longer exists.");
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
            Calendarioescolar calendarioescolar;
            try {
                calendarioescolar = em.getReference(Calendarioescolar.class, id);
                calendarioescolar.getClaveCalendario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioescolar with id " + id + " no longer exists.", enfe);
            }
            em.remove(calendarioescolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calendarioescolar> findCalendarioescolarEntities() {
        return findCalendarioescolarEntities(true, -1, -1);
    }

    public List<Calendarioescolar> findCalendarioescolarEntities(int maxResults, int firstResult) {
        return findCalendarioescolarEntities(false, maxResults, firstResult);
    }

    private List<Calendarioescolar> findCalendarioescolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calendarioescolar as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calendarioescolar findCalendarioescolar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calendarioescolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioescolarCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calendarioescolar as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
