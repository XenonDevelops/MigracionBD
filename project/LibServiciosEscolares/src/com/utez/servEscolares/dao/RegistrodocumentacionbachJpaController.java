/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Registrodocumentacionbach;
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
public class RegistrodocumentacionbachJpaController implements Serializable {

    public RegistrodocumentacionbachJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrodocumentacionbach registrodocumentacionbach) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registrodocumentacionbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrodocumentacionbach(registrodocumentacionbach.getMatricula()) != null) {
                throw new PreexistingEntityException("Registrodocumentacionbach " + registrodocumentacionbach + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrodocumentacionbach registrodocumentacionbach) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registrodocumentacionbach = em.merge(registrodocumentacionbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = registrodocumentacionbach.getMatricula();
                if (findRegistrodocumentacionbach(id) == null) {
                    throw new NonexistentEntityException("The registrodocumentacionbach with id " + id + " no longer exists.");
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
            Registrodocumentacionbach registrodocumentacionbach;
            try {
                registrodocumentacionbach = em.getReference(Registrodocumentacionbach.class, id);
                registrodocumentacionbach.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrodocumentacionbach with id " + id + " no longer exists.", enfe);
            }
            em.remove(registrodocumentacionbach);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrodocumentacionbach> findRegistrodocumentacionbachEntities() {
        return findRegistrodocumentacionbachEntities(true, -1, -1);
    }

    public List<Registrodocumentacionbach> findRegistrodocumentacionbachEntities(int maxResults, int firstResult) {
        return findRegistrodocumentacionbachEntities(false, maxResults, firstResult);
    }

    private List<Registrodocumentacionbach> findRegistrodocumentacionbachEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Registrodocumentacionbach as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registrodocumentacionbach findRegistrodocumentacionbach(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrodocumentacionbach.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrodocumentacionbachCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Registrodocumentacionbach as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
