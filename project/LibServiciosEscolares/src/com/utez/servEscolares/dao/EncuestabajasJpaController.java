/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Encuestabajas;
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
public class EncuestabajasJpaController implements Serializable {

    public EncuestabajasJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encuestabajas encuestabajas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(encuestabajas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncuestabajas(encuestabajas.getMatricula()) != null) {
                throw new PreexistingEntityException("Encuestabajas " + encuestabajas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encuestabajas encuestabajas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            encuestabajas = em.merge(encuestabajas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = encuestabajas.getMatricula();
                if (findEncuestabajas(id) == null) {
                    throw new NonexistentEntityException("The encuestabajas with id " + id + " no longer exists.");
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
            Encuestabajas encuestabajas;
            try {
                encuestabajas = em.getReference(Encuestabajas.class, id);
                encuestabajas.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuestabajas with id " + id + " no longer exists.", enfe);
            }
            em.remove(encuestabajas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encuestabajas> findEncuestabajasEntities() {
        return findEncuestabajasEntities(true, -1, -1);
    }

    public List<Encuestabajas> findEncuestabajasEntities(int maxResults, int firstResult) {
        return findEncuestabajasEntities(false, maxResults, firstResult);
    }

    private List<Encuestabajas> findEncuestabajasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Encuestabajas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Encuestabajas findEncuestabajas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encuestabajas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncuestabajasCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Encuestabajas as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
