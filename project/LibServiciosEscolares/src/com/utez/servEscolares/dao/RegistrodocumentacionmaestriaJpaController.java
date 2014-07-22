/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Registrodocumentacionmaestria;
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
public class RegistrodocumentacionmaestriaJpaController implements Serializable {

    public RegistrodocumentacionmaestriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrodocumentacionmaestria registrodocumentacionmaestria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registrodocumentacionmaestria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrodocumentacionmaestria registrodocumentacionmaestria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registrodocumentacionmaestria = em.merge(registrodocumentacionmaestria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registrodocumentacionmaestria.getClaveRegistrodocumentacionmaestria();
                if (findRegistrodocumentacionmaestria(id) == null) {
                    throw new NonexistentEntityException("The registrodocumentacionmaestria with id " + id + " no longer exists.");
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
            Registrodocumentacionmaestria registrodocumentacionmaestria;
            try {
                registrodocumentacionmaestria = em.getReference(Registrodocumentacionmaestria.class, id);
                registrodocumentacionmaestria.getClaveRegistrodocumentacionmaestria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrodocumentacionmaestria with id " + id + " no longer exists.", enfe);
            }
            em.remove(registrodocumentacionmaestria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrodocumentacionmaestria> findRegistrodocumentacionmaestriaEntities() {
        return findRegistrodocumentacionmaestriaEntities(true, -1, -1);
    }

    public List<Registrodocumentacionmaestria> findRegistrodocumentacionmaestriaEntities(int maxResults, int firstResult) {
        return findRegistrodocumentacionmaestriaEntities(false, maxResults, firstResult);
    }

    private List<Registrodocumentacionmaestria> findRegistrodocumentacionmaestriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Registrodocumentacionmaestria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registrodocumentacionmaestria findRegistrodocumentacionmaestria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrodocumentacionmaestria.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrodocumentacionmaestriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Registrodocumentacionmaestria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
