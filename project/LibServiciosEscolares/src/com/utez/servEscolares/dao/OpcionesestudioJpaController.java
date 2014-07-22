/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Opcionesestudio;
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
public class OpcionesestudioJpaController implements Serializable {

    public OpcionesestudioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opcionesestudio opcionesestudio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opcionesestudio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpcionesestudio(opcionesestudio.getClaveopcest()) != null) {
                throw new PreexistingEntityException("Opcionesestudio " + opcionesestudio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opcionesestudio opcionesestudio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opcionesestudio = em.merge(opcionesestudio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = opcionesestudio.getClaveopcest();
                if (findOpcionesestudio(id) == null) {
                    throw new NonexistentEntityException("The opcionesestudio with id " + id + " no longer exists.");
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
            Opcionesestudio opcionesestudio;
            try {
                opcionesestudio = em.getReference(Opcionesestudio.class, id);
                opcionesestudio.getClaveopcest();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opcionesestudio with id " + id + " no longer exists.", enfe);
            }
            em.remove(opcionesestudio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opcionesestudio> findOpcionesestudioEntities() {
        return findOpcionesestudioEntities(true, -1, -1);
    }

    public List<Opcionesestudio> findOpcionesestudioEntities(int maxResults, int firstResult) {
        return findOpcionesestudioEntities(false, maxResults, firstResult);
    }

    private List<Opcionesestudio> findOpcionesestudioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Opcionesestudio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Opcionesestudio findOpcionesestudio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opcionesestudio.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionesestudioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Opcionesestudio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
