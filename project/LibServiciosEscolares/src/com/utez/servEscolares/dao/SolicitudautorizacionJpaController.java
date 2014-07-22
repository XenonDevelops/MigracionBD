/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Solicitudautorizacion;
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
public class SolicitudautorizacionJpaController implements Serializable {

    public SolicitudautorizacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitudautorizacion solicitudautorizacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(solicitudautorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitudautorizacion solicitudautorizacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            solicitudautorizacion = em.merge(solicitudautorizacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudautorizacion.getClavesolicitud();
                if (findSolicitudautorizacion(id) == null) {
                    throw new NonexistentEntityException("The solicitudautorizacion with id " + id + " no longer exists.");
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
            Solicitudautorizacion solicitudautorizacion;
            try {
                solicitudautorizacion = em.getReference(Solicitudautorizacion.class, id);
                solicitudautorizacion.getClavesolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudautorizacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(solicitudautorizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitudautorizacion> findSolicitudautorizacionEntities() {
        return findSolicitudautorizacionEntities(true, -1, -1);
    }

    public List<Solicitudautorizacion> findSolicitudautorizacionEntities(int maxResults, int firstResult) {
        return findSolicitudautorizacionEntities(false, maxResults, firstResult);
    }

    private List<Solicitudautorizacion> findSolicitudautorizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Solicitudautorizacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Solicitudautorizacion findSolicitudautorizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitudautorizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudautorizacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Solicitudautorizacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
