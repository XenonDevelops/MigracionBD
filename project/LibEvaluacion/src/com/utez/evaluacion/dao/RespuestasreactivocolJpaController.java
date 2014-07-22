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
import com.utez.evaluacion.entity.Respuestasreactivocol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestasreactivocolJpaController implements Serializable {

    public RespuestasreactivocolJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestasreactivocol respuestasreactivocol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo claveReactivo = respuestasreactivocol.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo = em.getReference(claveReactivo.getClass(), claveReactivo.getClaveReactivo());
                respuestasreactivocol.setClaveReactivo(claveReactivo);
            }
            em.persist(respuestasreactivocol);
            if (claveReactivo != null) {
                claveReactivo.getRespuestasreactivocolList().add(respuestasreactivocol);
                claveReactivo = em.merge(claveReactivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestasreactivocol respuestasreactivocol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestasreactivocol persistentRespuestasreactivocol = em.find(Respuestasreactivocol.class, respuestasreactivocol.getClaveRespuestaCol());
            Reactivo claveReactivoOld = persistentRespuestasreactivocol.getClaveReactivo();
            Reactivo claveReactivoNew = respuestasreactivocol.getClaveReactivo();
            if (claveReactivoNew != null) {
                claveReactivoNew = em.getReference(claveReactivoNew.getClass(), claveReactivoNew.getClaveReactivo());
                respuestasreactivocol.setClaveReactivo(claveReactivoNew);
            }
            respuestasreactivocol = em.merge(respuestasreactivocol);
            if (claveReactivoOld != null && !claveReactivoOld.equals(claveReactivoNew)) {
                claveReactivoOld.getRespuestasreactivocolList().remove(respuestasreactivocol);
                claveReactivoOld = em.merge(claveReactivoOld);
            }
            if (claveReactivoNew != null && !claveReactivoNew.equals(claveReactivoOld)) {
                claveReactivoNew.getRespuestasreactivocolList().add(respuestasreactivocol);
                claveReactivoNew = em.merge(claveReactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestasreactivocol.getClaveRespuestaCol();
                if (findRespuestasreactivocol(id) == null) {
                    throw new NonexistentEntityException("The respuestasreactivocol with id " + id + " no longer exists.");
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
            Respuestasreactivocol respuestasreactivocol;
            try {
                respuestasreactivocol = em.getReference(Respuestasreactivocol.class, id);
                respuestasreactivocol.getClaveRespuestaCol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestasreactivocol with id " + id + " no longer exists.", enfe);
            }
            Reactivo claveReactivo = respuestasreactivocol.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo.getRespuestasreactivocolList().remove(respuestasreactivocol);
                claveReactivo = em.merge(claveReactivo);
            }
            em.remove(respuestasreactivocol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestasreactivocol> findRespuestasreactivocolEntities() {
        return findRespuestasreactivocolEntities(true, -1, -1);
    }

    public List<Respuestasreactivocol> findRespuestasreactivocolEntities(int maxResults, int firstResult) {
        return findRespuestasreactivocolEntities(false, maxResults, firstResult);
    }

    private List<Respuestasreactivocol> findRespuestasreactivocolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Respuestasreactivocol as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Respuestasreactivocol findRespuestasreactivocol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestasreactivocol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasreactivocolCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Respuestasreactivocol as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
