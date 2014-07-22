/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Registrosep;
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
public class RegistrosepJpaController implements Serializable {

    public RegistrosepJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrosep registrosep) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registrosep);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrosep(registrosep.getNumRegistro()) != null) {
                throw new PreexistingEntityException("Registrosep " + registrosep + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrosep registrosep) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registrosep = em.merge(registrosep);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = registrosep.getNumRegistro();
                if (findRegistrosep(id) == null) {
                    throw new NonexistentEntityException("The registrosep with id " + id + " no longer exists.");
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
            Registrosep registrosep;
            try {
                registrosep = em.getReference(Registrosep.class, id);
                registrosep.getNumRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrosep with id " + id + " no longer exists.", enfe);
            }
            em.remove(registrosep);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrosep> findRegistrosepEntities() {
        return findRegistrosepEntities(true, -1, -1);
    }

    public List<Registrosep> findRegistrosepEntities(int maxResults, int firstResult) {
        return findRegistrosepEntities(false, maxResults, firstResult);
    }

    private List<Registrosep> findRegistrosepEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Registrosep as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registrosep findRegistrosep(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrosep.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrosepCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Registrosep as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
