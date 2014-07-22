/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Procesotitulacion;
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
public class ProcesotitulacionJpaController implements Serializable {

    public ProcesotitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesotitulacion procesotitulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(procesotitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesotitulacion procesotitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            procesotitulacion = em.merge(procesotitulacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procesotitulacion.getClaveProcesotitulacion();
                if (findProcesotitulacion(id) == null) {
                    throw new NonexistentEntityException("The procesotitulacion with id " + id + " no longer exists.");
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
            Procesotitulacion procesotitulacion;
            try {
                procesotitulacion = em.getReference(Procesotitulacion.class, id);
                procesotitulacion.getClaveProcesotitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesotitulacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(procesotitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procesotitulacion> findProcesotitulacionEntities() {
        return findProcesotitulacionEntities(true, -1, -1);
    }

    public List<Procesotitulacion> findProcesotitulacionEntities(int maxResults, int firstResult) {
        return findProcesotitulacionEntities(false, maxResults, firstResult);
    }

    private List<Procesotitulacion> findProcesotitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Procesotitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Procesotitulacion findProcesotitulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesotitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesotitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Procesotitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
