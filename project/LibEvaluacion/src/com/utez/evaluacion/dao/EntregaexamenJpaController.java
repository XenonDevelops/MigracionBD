/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Aplicacionexamen;
import com.utez.evaluacion.entity.Entregaexamen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class EntregaexamenJpaController implements Serializable {

    public EntregaexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entregaexamen entregaexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacionexamen claveAplicacion = entregaexamen.getClaveAplicacion();
            if (claveAplicacion != null) {
                claveAplicacion = em.getReference(claveAplicacion.getClass(), claveAplicacion.getClaveAplicacion());
                entregaexamen.setClaveAplicacion(claveAplicacion);
            }
            em.persist(entregaexamen);
            if (claveAplicacion != null) {
                claveAplicacion.getEntregaexamenList().add(entregaexamen);
                claveAplicacion = em.merge(claveAplicacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entregaexamen entregaexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregaexamen persistentEntregaexamen = em.find(Entregaexamen.class, entregaexamen.getClaveEntrega());
            Aplicacionexamen claveAplicacionOld = persistentEntregaexamen.getClaveAplicacion();
            Aplicacionexamen claveAplicacionNew = entregaexamen.getClaveAplicacion();
            if (claveAplicacionNew != null) {
                claveAplicacionNew = em.getReference(claveAplicacionNew.getClass(), claveAplicacionNew.getClaveAplicacion());
                entregaexamen.setClaveAplicacion(claveAplicacionNew);
            }
            entregaexamen = em.merge(entregaexamen);
            if (claveAplicacionOld != null && !claveAplicacionOld.equals(claveAplicacionNew)) {
                claveAplicacionOld.getEntregaexamenList().remove(entregaexamen);
                claveAplicacionOld = em.merge(claveAplicacionOld);
            }
            if (claveAplicacionNew != null && !claveAplicacionNew.equals(claveAplicacionOld)) {
                claveAplicacionNew.getEntregaexamenList().add(entregaexamen);
                claveAplicacionNew = em.merge(claveAplicacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entregaexamen.getClaveEntrega();
                if (findEntregaexamen(id) == null) {
                    throw new NonexistentEntityException("The entregaexamen with id " + id + " no longer exists.");
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
            Entregaexamen entregaexamen;
            try {
                entregaexamen = em.getReference(Entregaexamen.class, id);
                entregaexamen.getClaveEntrega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregaexamen with id " + id + " no longer exists.", enfe);
            }
            Aplicacionexamen claveAplicacion = entregaexamen.getClaveAplicacion();
            if (claveAplicacion != null) {
                claveAplicacion.getEntregaexamenList().remove(entregaexamen);
                claveAplicacion = em.merge(claveAplicacion);
            }
            em.remove(entregaexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entregaexamen> findEntregaexamenEntities() {
        return findEntregaexamenEntities(true, -1, -1);
    }

    public List<Entregaexamen> findEntregaexamenEntities(int maxResults, int firstResult) {
        return findEntregaexamenEntities(false, maxResults, firstResult);
    }

    private List<Entregaexamen> findEntregaexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Entregaexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Entregaexamen findEntregaexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entregaexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Entregaexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
