/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Moduloseventos;
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
public class ModuloseventosJpaController implements Serializable {

    public ModuloseventosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Moduloseventos moduloseventos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(moduloseventos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Moduloseventos moduloseventos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            moduloseventos = em.merge(moduloseventos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = moduloseventos.getClaveModulo();
                if (findModuloseventos(id) == null) {
                    throw new NonexistentEntityException("The moduloseventos with id " + id + " no longer exists.");
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
            Moduloseventos moduloseventos;
            try {
                moduloseventos = em.getReference(Moduloseventos.class, id);
                moduloseventos.getClaveModulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The moduloseventos with id " + id + " no longer exists.", enfe);
            }
            em.remove(moduloseventos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Moduloseventos> findModuloseventosEntities() {
        return findModuloseventosEntities(true, -1, -1);
    }

    public List<Moduloseventos> findModuloseventosEntities(int maxResults, int firstResult) {
        return findModuloseventosEntities(false, maxResults, firstResult);
    }

    private List<Moduloseventos> findModuloseventosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Moduloseventos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Moduloseventos findModuloseventos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Moduloseventos.class, id);
        } finally {
            em.close();
        }
    }

    public int getModuloseventosCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Moduloseventos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
