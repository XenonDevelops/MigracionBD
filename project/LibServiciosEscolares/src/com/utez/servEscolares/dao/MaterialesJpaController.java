/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Materiales;
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
public class MaterialesJpaController implements Serializable {

    public MaterialesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiales materiales) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(materiales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiales materiales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            materiales = em.merge(materiales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiales.getClaveMaterial();
                if (findMateriales(id) == null) {
                    throw new NonexistentEntityException("The materiales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiales materiales;
            try {
                materiales = em.getReference(Materiales.class, id);
                materiales.getClaveMaterial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiales with id " + id + " no longer exists.", enfe);
            }
            em.remove(materiales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiales> findMaterialesEntities() {
        return findMaterialesEntities(true, -1, -1);
    }

    public List<Materiales> findMaterialesEntities(int maxResults, int firstResult) {
        return findMaterialesEntities(false, maxResults, firstResult);
    }

    private List<Materiales> findMaterialesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Materiales as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materiales findMateriales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiales.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Materiales as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
