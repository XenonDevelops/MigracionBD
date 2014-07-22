/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosocupacionalesbach;
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
public class DatosocupacionalesbachJpaController implements Serializable {

    public DatosocupacionalesbachJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosocupacionalesbach datosocupacionalesbach) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosocupacionalesbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosocupacionalesbach(datosocupacionalesbach.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosocupacionalesbach " + datosocupacionalesbach + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosocupacionalesbach datosocupacionalesbach) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosocupacionalesbach = em.merge(datosocupacionalesbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosocupacionalesbach.getMatricula();
                if (findDatosocupacionalesbach(id) == null) {
                    throw new NonexistentEntityException("The datosocupacionalesbach with id " + id + " no longer exists.");
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
            Datosocupacionalesbach datosocupacionalesbach;
            try {
                datosocupacionalesbach = em.getReference(Datosocupacionalesbach.class, id);
                datosocupacionalesbach.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosocupacionalesbach with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosocupacionalesbach);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosocupacionalesbach> findDatosocupacionalesbachEntities() {
        return findDatosocupacionalesbachEntities(true, -1, -1);
    }

    public List<Datosocupacionalesbach> findDatosocupacionalesbachEntities(int maxResults, int firstResult) {
        return findDatosocupacionalesbachEntities(false, maxResults, firstResult);
    }

    private List<Datosocupacionalesbach> findDatosocupacionalesbachEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosocupacionalesbach as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosocupacionalesbach findDatosocupacionalesbach(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosocupacionalesbach.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosocupacionalesbachCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosocupacionalesbach as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
