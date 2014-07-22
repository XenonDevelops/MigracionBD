/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Requisitostramitetitulacion;
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
public class RequisitostramitetitulacionJpaController implements Serializable {

    public RequisitostramitetitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Requisitostramitetitulacion requisitostramitetitulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(requisitostramitetitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Requisitostramitetitulacion requisitostramitetitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            requisitostramitetitulacion = em.merge(requisitostramitetitulacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = requisitostramitetitulacion.getClaveRequisitostramitetitulacion();
                if (findRequisitostramitetitulacion(id) == null) {
                    throw new NonexistentEntityException("The requisitostramitetitulacion with id " + id + " no longer exists.");
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
            Requisitostramitetitulacion requisitostramitetitulacion;
            try {
                requisitostramitetitulacion = em.getReference(Requisitostramitetitulacion.class, id);
                requisitostramitetitulacion.getClaveRequisitostramitetitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The requisitostramitetitulacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(requisitostramitetitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Requisitostramitetitulacion> findRequisitostramitetitulacionEntities() {
        return findRequisitostramitetitulacionEntities(true, -1, -1);
    }

    public List<Requisitostramitetitulacion> findRequisitostramitetitulacionEntities(int maxResults, int firstResult) {
        return findRequisitostramitetitulacionEntities(false, maxResults, firstResult);
    }

    private List<Requisitostramitetitulacion> findRequisitostramitetitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Requisitostramitetitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Requisitostramitetitulacion findRequisitostramitetitulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Requisitostramitetitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRequisitostramitetitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Requisitostramitetitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
