/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Materiasopcion;
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
public class MateriasopcionJpaController implements Serializable {

    public MateriasopcionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiasopcion materiasopcion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(materiasopcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiasopcion materiasopcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            materiasopcion = em.merge(materiasopcion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiasopcion.getClaveMateriasopcion();
                if (findMateriasopcion(id) == null) {
                    throw new NonexistentEntityException("The materiasopcion with id " + id + " no longer exists.");
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
            Materiasopcion materiasopcion;
            try {
                materiasopcion = em.getReference(Materiasopcion.class, id);
                materiasopcion.getClaveMateriasopcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiasopcion with id " + id + " no longer exists.", enfe);
            }
            em.remove(materiasopcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiasopcion> findMateriasopcionEntities() {
        return findMateriasopcionEntities(true, -1, -1);
    }

    public List<Materiasopcion> findMateriasopcionEntities(int maxResults, int firstResult) {
        return findMateriasopcionEntities(false, maxResults, firstResult);
    }

    private List<Materiasopcion> findMateriasopcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Materiasopcion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materiasopcion findMateriasopcion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiasopcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriasopcionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Materiasopcion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
