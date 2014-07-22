/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Resultadodirecciones;
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
public class ResultadodireccionesJpaController implements Serializable {

    public ResultadodireccionesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadodirecciones resultadodirecciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoencuesta claveResultadoE = resultadodirecciones.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE = em.getReference(claveResultadoE.getClass(), claveResultadoE.getClaveResultadoE());
                resultadodirecciones.setClaveResultadoE(claveResultadoE);
            }
            em.persist(resultadodirecciones);
            if (claveResultadoE != null) {
                claveResultadoE.getResultadodireccionesList().add(resultadodirecciones);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadodirecciones resultadodirecciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadodirecciones persistentResultadodirecciones = em.find(Resultadodirecciones.class, resultadodirecciones.getClaveResultadoD());
            Resultadoencuesta claveResultadoEOld = persistentResultadodirecciones.getClaveResultadoE();
            Resultadoencuesta claveResultadoENew = resultadodirecciones.getClaveResultadoE();
            if (claveResultadoENew != null) {
                claveResultadoENew = em.getReference(claveResultadoENew.getClass(), claveResultadoENew.getClaveResultadoE());
                resultadodirecciones.setClaveResultadoE(claveResultadoENew);
            }
            resultadodirecciones = em.merge(resultadodirecciones);
            if (claveResultadoEOld != null && !claveResultadoEOld.equals(claveResultadoENew)) {
                claveResultadoEOld.getResultadodireccionesList().remove(resultadodirecciones);
                claveResultadoEOld = em.merge(claveResultadoEOld);
            }
            if (claveResultadoENew != null && !claveResultadoENew.equals(claveResultadoEOld)) {
                claveResultadoENew.getResultadodireccionesList().add(resultadodirecciones);
                claveResultadoENew = em.merge(claveResultadoENew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadodirecciones.getClaveResultadoD();
                if (findResultadodirecciones(id) == null) {
                    throw new NonexistentEntityException("The resultadodirecciones with id " + id + " no longer exists.");
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
            Resultadodirecciones resultadodirecciones;
            try {
                resultadodirecciones = em.getReference(Resultadodirecciones.class, id);
                resultadodirecciones.getClaveResultadoD();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadodirecciones with id " + id + " no longer exists.", enfe);
            }
            Resultadoencuesta claveResultadoE = resultadodirecciones.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE.getResultadodireccionesList().remove(resultadodirecciones);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.remove(resultadodirecciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadodirecciones> findResultadodireccionesEntities() {
        return findResultadodireccionesEntities(true, -1, -1);
    }

    public List<Resultadodirecciones> findResultadodireccionesEntities(int maxResults, int firstResult) {
        return findResultadodireccionesEntities(false, maxResults, firstResult);
    }

    private List<Resultadodirecciones> findResultadodireccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Resultadodirecciones as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resultadodirecciones findResultadodirecciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadodirecciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadodireccionesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Resultadodirecciones as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
