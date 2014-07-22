/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Respuestasresultado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Resultadoautoeval;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class RespuestasresultadoJpaController implements Serializable {

    public RespuestasresultadoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestasresultado respuestasresultado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoautoeval claveResultadoAuto = respuestasresultado.getClaveResultadoAuto();
            if (claveResultadoAuto != null) {
                claveResultadoAuto = em.getReference(claveResultadoAuto.getClass(), claveResultadoAuto.getClaveResultadoAuto());
                respuestasresultado.setClaveResultadoAuto(claveResultadoAuto);
            }
            em.persist(respuestasresultado);
            if (claveResultadoAuto != null) {
                claveResultadoAuto.getRespuestasresultadoList().add(respuestasresultado);
                claveResultadoAuto = em.merge(claveResultadoAuto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestasresultado respuestasresultado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestasresultado persistentRespuestasresultado = em.find(Respuestasresultado.class, respuestasresultado.getClaveRespuestaR());
            Resultadoautoeval claveResultadoAutoOld = persistentRespuestasresultado.getClaveResultadoAuto();
            Resultadoautoeval claveResultadoAutoNew = respuestasresultado.getClaveResultadoAuto();
            if (claveResultadoAutoNew != null) {
                claveResultadoAutoNew = em.getReference(claveResultadoAutoNew.getClass(), claveResultadoAutoNew.getClaveResultadoAuto());
                respuestasresultado.setClaveResultadoAuto(claveResultadoAutoNew);
            }
            respuestasresultado = em.merge(respuestasresultado);
            if (claveResultadoAutoOld != null && !claveResultadoAutoOld.equals(claveResultadoAutoNew)) {
                claveResultadoAutoOld.getRespuestasresultadoList().remove(respuestasresultado);
                claveResultadoAutoOld = em.merge(claveResultadoAutoOld);
            }
            if (claveResultadoAutoNew != null && !claveResultadoAutoNew.equals(claveResultadoAutoOld)) {
                claveResultadoAutoNew.getRespuestasresultadoList().add(respuestasresultado);
                claveResultadoAutoNew = em.merge(claveResultadoAutoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestasresultado.getClaveRespuestaR();
                if (findRespuestasresultado(id) == null) {
                    throw new NonexistentEntityException("The respuestasresultado with id " + id + " no longer exists.");
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
            Respuestasresultado respuestasresultado;
            try {
                respuestasresultado = em.getReference(Respuestasresultado.class, id);
                respuestasresultado.getClaveRespuestaR();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestasresultado with id " + id + " no longer exists.", enfe);
            }
            Resultadoautoeval claveResultadoAuto = respuestasresultado.getClaveResultadoAuto();
            if (claveResultadoAuto != null) {
                claveResultadoAuto.getRespuestasresultadoList().remove(respuestasresultado);
                claveResultadoAuto = em.merge(claveResultadoAuto);
            }
            em.remove(respuestasresultado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestasresultado> findRespuestasresultadoEntities() {
        return findRespuestasresultadoEntities(true, -1, -1);
    }

    public List<Respuestasresultado> findRespuestasresultadoEntities(int maxResults, int firstResult) {
        return findRespuestasresultadoEntities(false, maxResults, firstResult);
    }

    private List<Respuestasresultado> findRespuestasresultadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Respuestasresultado as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Respuestasresultado findRespuestasresultado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestasresultado.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasresultadoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Respuestasresultado as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
