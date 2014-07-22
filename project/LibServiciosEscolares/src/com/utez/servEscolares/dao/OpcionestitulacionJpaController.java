/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Opcionestitulacion;
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
public class OpcionestitulacionJpaController implements Serializable {

    public OpcionestitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opcionestitulacion opcionestitulacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opcionestitulacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpcionestitulacion(opcionestitulacion.getClaveOpcion()) != null) {
                throw new PreexistingEntityException("Opcionestitulacion " + opcionestitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opcionestitulacion opcionestitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opcionestitulacion = em.merge(opcionestitulacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = opcionestitulacion.getClaveOpcion();
                if (findOpcionestitulacion(id) == null) {
                    throw new NonexistentEntityException("The opcionestitulacion with id " + id + " no longer exists.");
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
            Opcionestitulacion opcionestitulacion;
            try {
                opcionestitulacion = em.getReference(Opcionestitulacion.class, id);
                opcionestitulacion.getClaveOpcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opcionestitulacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(opcionestitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opcionestitulacion> findOpcionestitulacionEntities() {
        return findOpcionestitulacionEntities(true, -1, -1);
    }

    public List<Opcionestitulacion> findOpcionestitulacionEntities(int maxResults, int firstResult) {
        return findOpcionestitulacionEntities(false, maxResults, firstResult);
    }

    private List<Opcionestitulacion> findOpcionestitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Opcionestitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Opcionestitulacion findOpcionestitulacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opcionestitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpcionestitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Opcionestitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
