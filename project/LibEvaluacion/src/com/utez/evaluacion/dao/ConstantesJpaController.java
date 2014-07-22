/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Constantes;
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
public class ConstantesJpaController implements Serializable {

    public ConstantesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Constantes constantes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(constantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Constantes constantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            constantes = em.merge(constantes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = constantes.getClaveConstante();
                if (findConstantes(id) == null) {
                    throw new NonexistentEntityException("The constantes with id " + id + " no longer exists.");
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
            Constantes constantes;
            try {
                constantes = em.getReference(Constantes.class, id);
                constantes.getClaveConstante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The constantes with id " + id + " no longer exists.", enfe);
            }
            em.remove(constantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Constantes> findConstantesEntities() {
        return findConstantesEntities(true, -1, -1);
    }

    public List<Constantes> findConstantesEntities(int maxResults, int firstResult) {
        return findConstantesEntities(false, maxResults, firstResult);
    }

    private List<Constantes> findConstantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Constantes as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Constantes findConstantes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Constantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstantesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Constantes as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
