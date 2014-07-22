/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Grupoinduccion;
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
public class GrupoinduccionJpaController implements Serializable {

    public GrupoinduccionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupoinduccion grupoinduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(grupoinduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupoinduccion grupoinduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            grupoinduccion = em.merge(grupoinduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupoinduccion.getClaveGrupoInd();
                if (findGrupoinduccion(id) == null) {
                    throw new NonexistentEntityException("The grupoinduccion with id " + id + " no longer exists.");
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
            Grupoinduccion grupoinduccion;
            try {
                grupoinduccion = em.getReference(Grupoinduccion.class, id);
                grupoinduccion.getClaveGrupoInd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoinduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(grupoinduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupoinduccion> findGrupoinduccionEntities() {
        return findGrupoinduccionEntities(true, -1, -1);
    }

    public List<Grupoinduccion> findGrupoinduccionEntities(int maxResults, int firstResult) {
        return findGrupoinduccionEntities(false, maxResults, firstResult);
    }

    private List<Grupoinduccion> findGrupoinduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Grupoinduccion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupoinduccion findGrupoinduccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupoinduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoinduccionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Grupoinduccion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
