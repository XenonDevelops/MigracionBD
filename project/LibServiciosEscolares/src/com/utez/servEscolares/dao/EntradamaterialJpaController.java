/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Entradamaterial;
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
public class EntradamaterialJpaController implements Serializable {

    public EntradamaterialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entradamaterial entradamaterial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entradamaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entradamaterial entradamaterial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entradamaterial = em.merge(entradamaterial);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entradamaterial.getClaveEntrada();
                if (findEntradamaterial(id) == null) {
                    throw new NonexistentEntityException("The entradamaterial with id " + id + " no longer exists.");
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
            Entradamaterial entradamaterial;
            try {
                entradamaterial = em.getReference(Entradamaterial.class, id);
                entradamaterial.getClaveEntrada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entradamaterial with id " + id + " no longer exists.", enfe);
            }
            em.remove(entradamaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entradamaterial> findEntradamaterialEntities() {
        return findEntradamaterialEntities(true, -1, -1);
    }

    public List<Entradamaterial> findEntradamaterialEntities(int maxResults, int firstResult) {
        return findEntradamaterialEntities(false, maxResults, firstResult);
    }

    private List<Entradamaterial> findEntradamaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Entradamaterial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Entradamaterial findEntradamaterial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entradamaterial.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntradamaterialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Entradamaterial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
