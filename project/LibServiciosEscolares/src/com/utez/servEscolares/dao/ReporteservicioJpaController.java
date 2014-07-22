/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Reporteservicio;
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
public class ReporteservicioJpaController implements Serializable {

    public ReporteservicioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reporteservicio reporteservicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reporteservicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reporteservicio reporteservicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reporteservicio = em.merge(reporteservicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reporteservicio.getClaveReporteservicio();
                if (findReporteservicio(id) == null) {
                    throw new NonexistentEntityException("The reporteservicio with id " + id + " no longer exists.");
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
            Reporteservicio reporteservicio;
            try {
                reporteservicio = em.getReference(Reporteservicio.class, id);
                reporteservicio.getClaveReporteservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporteservicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(reporteservicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reporteservicio> findReporteservicioEntities() {
        return findReporteservicioEntities(true, -1, -1);
    }

    public List<Reporteservicio> findReporteservicioEntities(int maxResults, int firstResult) {
        return findReporteservicioEntities(false, maxResults, firstResult);
    }

    private List<Reporteservicio> findReporteservicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Reporteservicio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reporteservicio findReporteservicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporteservicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getReporteservicioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Reporteservicio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
