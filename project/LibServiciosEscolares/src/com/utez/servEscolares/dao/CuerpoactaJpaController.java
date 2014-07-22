/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Cuerpoacta;
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
public class CuerpoactaJpaController implements Serializable {

    public CuerpoactaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuerpoacta cuerpoacta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cuerpoacta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuerpoacta cuerpoacta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cuerpoacta = em.merge(cuerpoacta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuerpoacta.getClaveCuerpoacta();
                if (findCuerpoacta(id) == null) {
                    throw new NonexistentEntityException("The cuerpoacta with id " + id + " no longer exists.");
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
            Cuerpoacta cuerpoacta;
            try {
                cuerpoacta = em.getReference(Cuerpoacta.class, id);
                cuerpoacta.getClaveCuerpoacta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuerpoacta with id " + id + " no longer exists.", enfe);
            }
            em.remove(cuerpoacta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuerpoacta> findCuerpoactaEntities() {
        return findCuerpoactaEntities(true, -1, -1);
    }

    public List<Cuerpoacta> findCuerpoactaEntities(int maxResults, int firstResult) {
        return findCuerpoactaEntities(false, maxResults, firstResult);
    }

    private List<Cuerpoacta> findCuerpoactaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cuerpoacta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuerpoacta findCuerpoacta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuerpoacta.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuerpoactaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cuerpoacta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
