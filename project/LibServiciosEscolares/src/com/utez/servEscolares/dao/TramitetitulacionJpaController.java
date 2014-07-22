/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Tramitetitulacion;
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
public class TramitetitulacionJpaController implements Serializable {

    public TramitetitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tramitetitulacion tramitetitulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tramitetitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tramitetitulacion tramitetitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tramitetitulacion = em.merge(tramitetitulacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tramitetitulacion.getClavetramite();
                if (findTramitetitulacion(id) == null) {
                    throw new NonexistentEntityException("The tramitetitulacion with id " + id + " no longer exists.");
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
            Tramitetitulacion tramitetitulacion;
            try {
                tramitetitulacion = em.getReference(Tramitetitulacion.class, id);
                tramitetitulacion.getClavetramite();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tramitetitulacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(tramitetitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tramitetitulacion> findTramitetitulacionEntities() {
        return findTramitetitulacionEntities(true, -1, -1);
    }

    public List<Tramitetitulacion> findTramitetitulacionEntities(int maxResults, int firstResult) {
        return findTramitetitulacionEntities(false, maxResults, firstResult);
    }

    private List<Tramitetitulacion> findTramitetitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tramitetitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tramitetitulacion findTramitetitulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tramitetitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTramitetitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tramitetitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
