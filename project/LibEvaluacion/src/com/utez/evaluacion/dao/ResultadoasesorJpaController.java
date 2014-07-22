/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Resultadoasesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Resultadoencuesta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ResultadoasesorJpaController implements Serializable {

    public ResultadoasesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadoasesor resultadoasesor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoencuesta claveResultadoE = resultadoasesor.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE = em.getReference(claveResultadoE.getClass(), claveResultadoE.getClaveResultadoE());
                resultadoasesor.setClaveResultadoE(claveResultadoE);
            }
            em.persist(resultadoasesor);
            if (claveResultadoE != null) {
                claveResultadoE.getResultadoasesorList().add(resultadoasesor);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadoasesor resultadoasesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoasesor persistentResultadoasesor = em.find(Resultadoasesor.class, resultadoasesor.getClaveResultadoA());
            Resultadoencuesta claveResultadoEOld = persistentResultadoasesor.getClaveResultadoE();
            Resultadoencuesta claveResultadoENew = resultadoasesor.getClaveResultadoE();
            if (claveResultadoENew != null) {
                claveResultadoENew = em.getReference(claveResultadoENew.getClass(), claveResultadoENew.getClaveResultadoE());
                resultadoasesor.setClaveResultadoE(claveResultadoENew);
            }
            resultadoasesor = em.merge(resultadoasesor);
            if (claveResultadoEOld != null && !claveResultadoEOld.equals(claveResultadoENew)) {
                claveResultadoEOld.getResultadoasesorList().remove(resultadoasesor);
                claveResultadoEOld = em.merge(claveResultadoEOld);
            }
            if (claveResultadoENew != null && !claveResultadoENew.equals(claveResultadoEOld)) {
                claveResultadoENew.getResultadoasesorList().add(resultadoasesor);
                claveResultadoENew = em.merge(claveResultadoENew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoasesor.getClaveResultadoA();
                if (findResultadoasesor(id) == null) {
                    throw new NonexistentEntityException("The resultadoasesor with id " + id + " no longer exists.");
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
            Resultadoasesor resultadoasesor;
            try {
                resultadoasesor = em.getReference(Resultadoasesor.class, id);
                resultadoasesor.getClaveResultadoA();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoasesor with id " + id + " no longer exists.", enfe);
            }
            Resultadoencuesta claveResultadoE = resultadoasesor.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE.getResultadoasesorList().remove(resultadoasesor);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.remove(resultadoasesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadoasesor> findResultadoasesorEntities() {
        return findResultadoasesorEntities(true, -1, -1);
    }

    public List<Resultadoasesor> findResultadoasesorEntities(int maxResults, int firstResult) {
        return findResultadoasesorEntities(false, maxResults, firstResult);
    }

    private List<Resultadoasesor> findResultadoasesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Resultadoasesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resultadoasesor findResultadoasesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadoasesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoasesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Resultadoasesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
