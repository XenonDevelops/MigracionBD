/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Kardex;
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
public class KardexJpaController implements Serializable {

    public KardexJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kardex kardex) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kardex);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKardex(kardex.getMatricula()) != null) {
                throw new PreexistingEntityException("Kardex " + kardex + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kardex kardex) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kardex = em.merge(kardex);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = kardex.getMatricula();
                if (findKardex(id) == null) {
                    throw new NonexistentEntityException("The kardex with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kardex kardex;
            try {
                kardex = em.getReference(Kardex.class, id);
                kardex.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kardex with id " + id + " no longer exists.", enfe);
            }
            em.remove(kardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kardex> findKardexEntities() {
        return findKardexEntities(true, -1, -1);
    }

    public List<Kardex> findKardexEntities(int maxResults, int firstResult) {
        return findKardexEntities(false, maxResults, firstResult);
    }

    private List<Kardex> findKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Kardex as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Kardex findKardex(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getKardexCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Kardex as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
