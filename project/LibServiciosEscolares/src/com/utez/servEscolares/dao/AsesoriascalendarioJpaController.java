/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Asesoriascalendario;
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
public class AsesoriascalendarioJpaController implements Serializable {

    public AsesoriascalendarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesoriascalendario asesoriascalendario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asesoriascalendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesoriascalendario asesoriascalendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asesoriascalendario = em.merge(asesoriascalendario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesoriascalendario.getClaveAsesoria();
                if (findAsesoriascalendario(id) == null) {
                    throw new NonexistentEntityException("The asesoriascalendario with id " + id + " no longer exists.");
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
            Asesoriascalendario asesoriascalendario;
            try {
                asesoriascalendario = em.getReference(Asesoriascalendario.class, id);
                asesoriascalendario.getClaveAsesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesoriascalendario with id " + id + " no longer exists.", enfe);
            }
            em.remove(asesoriascalendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesoriascalendario> findAsesoriascalendarioEntities() {
        return findAsesoriascalendarioEntities(true, -1, -1);
    }

    public List<Asesoriascalendario> findAsesoriascalendarioEntities(int maxResults, int firstResult) {
        return findAsesoriascalendarioEntities(false, maxResults, firstResult);
    }

    private List<Asesoriascalendario> findAsesoriascalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesoriascalendario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesoriascalendario findAsesoriascalendario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesoriascalendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesoriascalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesoriascalendario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
