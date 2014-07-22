/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Aplicador;
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
public class AplicadorJpaController implements Serializable {

    public AplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicador aplicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(aplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicador aplicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            aplicador = em.merge(aplicador);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aplicador.getClaveAplicador();
                if (findAplicador(id) == null) {
                    throw new NonexistentEntityException("The aplicador with id " + id + " no longer exists.");
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
            Aplicador aplicador;
            try {
                aplicador = em.getReference(Aplicador.class, id);
                aplicador.getClaveAplicador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicador with id " + id + " no longer exists.", enfe);
            }
            em.remove(aplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicador> findAplicadorEntities() {
        return findAplicadorEntities(true, -1, -1);
    }

    public List<Aplicador> findAplicadorEntities(int maxResults, int firstResult) {
        return findAplicadorEntities(false, maxResults, firstResult);
    }

    private List<Aplicador> findAplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aplicador findAplicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Aplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
