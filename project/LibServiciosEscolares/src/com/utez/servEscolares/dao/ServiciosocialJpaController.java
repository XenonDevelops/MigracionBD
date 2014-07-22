/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Serviciosocial;
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
public class ServiciosocialJpaController implements Serializable {

    public ServiciosocialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Serviciosocial serviciosocial) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(serviciosocial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiciosocial(serviciosocial.getMatricula()) != null) {
                throw new PreexistingEntityException("Serviciosocial " + serviciosocial + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Serviciosocial serviciosocial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            serviciosocial = em.merge(serviciosocial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = serviciosocial.getMatricula();
                if (findServiciosocial(id) == null) {
                    throw new NonexistentEntityException("The serviciosocial with id " + id + " no longer exists.");
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
            Serviciosocial serviciosocial;
            try {
                serviciosocial = em.getReference(Serviciosocial.class, id);
                serviciosocial.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosocial with id " + id + " no longer exists.", enfe);
            }
            em.remove(serviciosocial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Serviciosocial> findServiciosocialEntities() {
        return findServiciosocialEntities(true, -1, -1);
    }

    public List<Serviciosocial> findServiciosocialEntities(int maxResults, int firstResult) {
        return findServiciosocialEntities(false, maxResults, firstResult);
    }

    private List<Serviciosocial> findServiciosocialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Serviciosocial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Serviciosocial findServiciosocial(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Serviciosocial.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosocialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Serviciosocial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
