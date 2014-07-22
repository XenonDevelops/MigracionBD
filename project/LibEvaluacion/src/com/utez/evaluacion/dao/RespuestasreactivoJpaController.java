/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Reactivo;
import com.utez.evaluacion.entity.Respuestasreactivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestasreactivoJpaController implements Serializable {

    public RespuestasreactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestasreactivo respuestasreactivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo claveReactivo = respuestasreactivo.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo = em.getReference(claveReactivo.getClass(), claveReactivo.getClaveReactivo());
                respuestasreactivo.setClaveReactivo(claveReactivo);
            }
            em.persist(respuestasreactivo);
            if (claveReactivo != null) {
                claveReactivo.getRespuestasreactivoList().add(respuestasreactivo);
                claveReactivo = em.merge(claveReactivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestasreactivo respuestasreactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestasreactivo persistentRespuestasreactivo = em.find(Respuestasreactivo.class, respuestasreactivo.getClaveRespuesta());
            Reactivo claveReactivoOld = persistentRespuestasreactivo.getClaveReactivo();
            Reactivo claveReactivoNew = respuestasreactivo.getClaveReactivo();
            if (claveReactivoNew != null) {
                claveReactivoNew = em.getReference(claveReactivoNew.getClass(), claveReactivoNew.getClaveReactivo());
                respuestasreactivo.setClaveReactivo(claveReactivoNew);
            }
            respuestasreactivo = em.merge(respuestasreactivo);
            if (claveReactivoOld != null && !claveReactivoOld.equals(claveReactivoNew)) {
                claveReactivoOld.getRespuestasreactivoList().remove(respuestasreactivo);
                claveReactivoOld = em.merge(claveReactivoOld);
            }
            if (claveReactivoNew != null && !claveReactivoNew.equals(claveReactivoOld)) {
                claveReactivoNew.getRespuestasreactivoList().add(respuestasreactivo);
                claveReactivoNew = em.merge(claveReactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestasreactivo.getClaveRespuesta();
                if (findRespuestasreactivo(id) == null) {
                    throw new NonexistentEntityException("The respuestasreactivo with id " + id + " no longer exists.");
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
            Respuestasreactivo respuestasreactivo;
            try {
                respuestasreactivo = em.getReference(Respuestasreactivo.class, id);
                respuestasreactivo.getClaveRespuesta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestasreactivo with id " + id + " no longer exists.", enfe);
            }
            Reactivo claveReactivo = respuestasreactivo.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo.getRespuestasreactivoList().remove(respuestasreactivo);
                claveReactivo = em.merge(claveReactivo);
            }
            em.remove(respuestasreactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestasreactivo> findRespuestasreactivoEntities() {
        return findRespuestasreactivoEntities(true, -1, -1);
    }

    public List<Respuestasreactivo> findRespuestasreactivoEntities(int maxResults, int firstResult) {
        return findRespuestasreactivoEntities(false, maxResults, firstResult);
    }

    private List<Respuestasreactivo> findRespuestasreactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Respuestasreactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Respuestasreactivo findRespuestasreactivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestasreactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasreactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Respuestasreactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
