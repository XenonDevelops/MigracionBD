/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datossocioeconomicosbach;
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
public class DatossocioeconomicosbachJpaController implements Serializable {

    public DatossocioeconomicosbachJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datossocioeconomicosbach datossocioeconomicosbach) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datossocioeconomicosbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatossocioeconomicosbach(datossocioeconomicosbach.getMatricula()) != null) {
                throw new PreexistingEntityException("Datossocioeconomicosbach " + datossocioeconomicosbach + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datossocioeconomicosbach datossocioeconomicosbach) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datossocioeconomicosbach = em.merge(datossocioeconomicosbach);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datossocioeconomicosbach.getMatricula();
                if (findDatossocioeconomicosbach(id) == null) {
                    throw new NonexistentEntityException("The datossocioeconomicosbach with id " + id + " no longer exists.");
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
            Datossocioeconomicosbach datossocioeconomicosbach;
            try {
                datossocioeconomicosbach = em.getReference(Datossocioeconomicosbach.class, id);
                datossocioeconomicosbach.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datossocioeconomicosbach with id " + id + " no longer exists.", enfe);
            }
            em.remove(datossocioeconomicosbach);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datossocioeconomicosbach> findDatossocioeconomicosbachEntities() {
        return findDatossocioeconomicosbachEntities(true, -1, -1);
    }

    public List<Datossocioeconomicosbach> findDatossocioeconomicosbachEntities(int maxResults, int firstResult) {
        return findDatossocioeconomicosbachEntities(false, maxResults, firstResult);
    }

    private List<Datossocioeconomicosbach> findDatossocioeconomicosbachEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datossocioeconomicosbach as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datossocioeconomicosbach findDatossocioeconomicosbach(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datossocioeconomicosbach.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatossocioeconomicosbachCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datossocioeconomicosbach as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
