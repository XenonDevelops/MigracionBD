/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Tipoexamen;
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
public class TipoexamenJpaController implements Serializable {

    public TipoexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoexamen tipoexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoexamen tipoexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoexamen = em.merge(tipoexamen);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoexamen.getClaveExamen();
                if (findTipoexamen(id) == null) {
                    throw new NonexistentEntityException("The tipoexamen with id " + id + " no longer exists.");
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
            Tipoexamen tipoexamen;
            try {
                tipoexamen = em.getReference(Tipoexamen.class, id);
                tipoexamen.getClaveExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoexamen with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipoexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoexamen> findTipoexamenEntities() {
        return findTipoexamenEntities(true, -1, -1);
    }

    public List<Tipoexamen> findTipoexamenEntities(int maxResults, int firstResult) {
        return findTipoexamenEntities(false, maxResults, firstResult);
    }

    private List<Tipoexamen> findTipoexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tipoexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tipoexamen findTipoexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tipoexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
