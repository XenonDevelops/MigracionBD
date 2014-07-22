/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosescbach;
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
public class DatosescbachJpaController implements Serializable {

    public DatosescbachJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosescbach datosescbach) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosescbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosescbach(datosescbach.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosescbach " + datosescbach + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosescbach datosescbach) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosescbach = em.merge(datosescbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosescbach.getMatricula();
                if (findDatosescbach(id) == null) {
                    throw new NonexistentEntityException("The datosescbach with id " + id + " no longer exists.");
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
            Datosescbach datosescbach;
            try {
                datosescbach = em.getReference(Datosescbach.class, id);
                datosescbach.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosescbach with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosescbach);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosescbach> findDatosescbachEntities() {
        return findDatosescbachEntities(true, -1, -1);
    }

    public List<Datosescbach> findDatosescbachEntities(int maxResults, int firstResult) {
        return findDatosescbachEntities(false, maxResults, firstResult);
    }

    private List<Datosescbach> findDatosescbachEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosescbach as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosescbach findDatosescbach(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosescbach.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosescbachCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosescbach as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
