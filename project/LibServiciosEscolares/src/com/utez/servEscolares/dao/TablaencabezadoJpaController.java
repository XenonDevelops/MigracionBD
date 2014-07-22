/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Tablaencabezado;
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
public class TablaencabezadoJpaController implements Serializable {

    public TablaencabezadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tablaencabezado tablaencabezado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tablaencabezado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tablaencabezado tablaencabezado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tablaencabezado = em.merge(tablaencabezado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tablaencabezado.getClaveTablaencabezad();
                if (findTablaencabezado(id) == null) {
                    throw new NonexistentEntityException("The tablaencabezado with id " + id + " no longer exists.");
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
            Tablaencabezado tablaencabezado;
            try {
                tablaencabezado = em.getReference(Tablaencabezado.class, id);
                tablaencabezado.getClaveTablaencabezad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tablaencabezado with id " + id + " no longer exists.", enfe);
            }
            em.remove(tablaencabezado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tablaencabezado> findTablaencabezadoEntities() {
        return findTablaencabezadoEntities(true, -1, -1);
    }

    public List<Tablaencabezado> findTablaencabezadoEntities(int maxResults, int firstResult) {
        return findTablaencabezadoEntities(false, maxResults, firstResult);
    }

    private List<Tablaencabezado> findTablaencabezadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tablaencabezado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tablaencabezado findTablaencabezado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tablaencabezado.class, id);
        } finally {
            em.close();
        }
    }

    public int getTablaencabezadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tablaencabezado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
