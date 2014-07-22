/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosgeneraleslic;
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
public class DatosgeneraleslicJpaController implements Serializable {

    public DatosgeneraleslicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosgeneraleslic datosgeneraleslic) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosgeneraleslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosgeneraleslic(datosgeneraleslic.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosgeneraleslic " + datosgeneraleslic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosgeneraleslic datosgeneraleslic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosgeneraleslic = em.merge(datosgeneraleslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosgeneraleslic.getMatricula();
                if (findDatosgeneraleslic(id) == null) {
                    throw new NonexistentEntityException("The datosgeneraleslic with id " + id + " no longer exists.");
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
            Datosgeneraleslic datosgeneraleslic;
            try {
                datosgeneraleslic = em.getReference(Datosgeneraleslic.class, id);
                datosgeneraleslic.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosgeneraleslic with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosgeneraleslic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosgeneraleslic> findDatosgeneraleslicEntities() {
        return findDatosgeneraleslicEntities(true, -1, -1);
    }

    public List<Datosgeneraleslic> findDatosgeneraleslicEntities(int maxResults, int firstResult) {
        return findDatosgeneraleslicEntities(false, maxResults, firstResult);
    }

    private List<Datosgeneraleslic> findDatosgeneraleslicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosgeneraleslic as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosgeneraleslic findDatosgeneraleslic(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosgeneraleslic.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosgeneraleslicCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosgeneraleslic as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
