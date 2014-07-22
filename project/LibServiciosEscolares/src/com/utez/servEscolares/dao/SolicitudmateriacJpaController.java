/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Solicitudmateriac;
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
public class SolicitudmateriacJpaController implements Serializable {

    public SolicitudmateriacJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitudmateriac solicitudmateriac) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(solicitudmateriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitudmateriac solicitudmateriac) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            solicitudmateriac = em.merge(solicitudmateriac);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitudmateriac.getNumSolicitud();
                if (findSolicitudmateriac(id) == null) {
                    throw new NonexistentEntityException("The solicitudmateriac with id " + id + " no longer exists.");
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
            Solicitudmateriac solicitudmateriac;
            try {
                solicitudmateriac = em.getReference(Solicitudmateriac.class, id);
                solicitudmateriac.getNumSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitudmateriac with id " + id + " no longer exists.", enfe);
            }
            em.remove(solicitudmateriac);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitudmateriac> findSolicitudmateriacEntities() {
        return findSolicitudmateriacEntities(true, -1, -1);
    }

    public List<Solicitudmateriac> findSolicitudmateriacEntities(int maxResults, int firstResult) {
        return findSolicitudmateriacEntities(false, maxResults, firstResult);
    }

    private List<Solicitudmateriac> findSolicitudmateriacEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Solicitudmateriac as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Solicitudmateriac findSolicitudmateriac(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitudmateriac.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudmateriacCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Solicitudmateriac as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
