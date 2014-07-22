/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Asesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Plantel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesorJpaController implements Serializable {

    public AsesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesor asesor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel clavePlantel = asesor.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel = em.getReference(clavePlantel.getClass(), clavePlantel.getClavePlantel());
                asesor.setClavePlantel(clavePlantel);
            }
            em.persist(asesor);
            if (clavePlantel != null) {
                clavePlantel.getAsesorList().add(asesor);
                clavePlantel = em.merge(clavePlantel);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesor asesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor persistentAsesor = em.find(Asesor.class, asesor.getClaveAsesor());
            Plantel clavePlantelOld = persistentAsesor.getClavePlantel();
            Plantel clavePlantelNew = asesor.getClavePlantel();
            if (clavePlantelNew != null) {
                clavePlantelNew = em.getReference(clavePlantelNew.getClass(), clavePlantelNew.getClavePlantel());
                asesor.setClavePlantel(clavePlantelNew);
            }
            asesor = em.merge(asesor);
            if (clavePlantelOld != null && !clavePlantelOld.equals(clavePlantelNew)) {
                clavePlantelOld.getAsesorList().remove(asesor);
                clavePlantelOld = em.merge(clavePlantelOld);
            }
            if (clavePlantelNew != null && !clavePlantelNew.equals(clavePlantelOld)) {
                clavePlantelNew.getAsesorList().add(asesor);
                clavePlantelNew = em.merge(clavePlantelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesor.getClaveAsesor();
                if (findAsesor(id) == null) {
                    throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.");
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
            Asesor asesor;
            try {
                asesor = em.getReference(Asesor.class, id);
                asesor.getClaveAsesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.", enfe);
            }
            Plantel clavePlantel = asesor.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel.getAsesorList().remove(asesor);
                clavePlantel = em.merge(clavePlantel);
            }
            em.remove(asesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesor> findAsesorEntities() {
        return findAsesorEntities(true, -1, -1);
    }

    public List<Asesor> findAsesorEntities(int maxResults, int firstResult) {
        return findAsesorEntities(false, maxResults, firstResult);
    }

    private List<Asesor> findAsesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesor findAsesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
