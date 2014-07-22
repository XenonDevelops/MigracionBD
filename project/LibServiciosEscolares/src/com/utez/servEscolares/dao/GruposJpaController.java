/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Grupos;
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
public class GruposJpaController implements Serializable {

    public GruposJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupos grupos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(grupos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupos(grupos.getClavegrupo()) != null) {
                throw new PreexistingEntityException("Grupos " + grupos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupos grupos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            grupos = em.merge(grupos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = grupos.getClavegrupo();
                if (findGrupos(id) == null) {
                    throw new NonexistentEntityException("The grupos with id " + id + " no longer exists.");
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
            Grupos grupos;
            try {
                grupos = em.getReference(Grupos.class, id);
                grupos.getClavegrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupos with id " + id + " no longer exists.", enfe);
            }
            em.remove(grupos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupos> findGruposEntities() {
        return findGruposEntities(true, -1, -1);
    }

    public List<Grupos> findGruposEntities(int maxResults, int firstResult) {
        return findGruposEntities(false, maxResults, firstResult);
    }

    private List<Grupos> findGruposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Grupos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupos findGrupos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGruposCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Grupos as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
