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
import com.utez.evaluacion.entity.Asignaaplicador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaaplicadorJpaController implements Serializable {

    public AsignaaplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignaaplicador asignaaplicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacionexamen claveAplicacion = asignaaplicador.getClaveAplicacion();
            if (claveAplicacion != null) {
                claveAplicacion = em.getReference(claveAplicacion.getClass(), claveAplicacion.getClaveAplicacion());
                asignaaplicador.setClaveAplicacion(claveAplicacion);
            }
            em.persist(asignaaplicador);
            if (claveAplicacion != null) {
                claveAplicacion.getAsignaaplicadorList().add(asignaaplicador);
                claveAplicacion = em.merge(claveAplicacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignaaplicador asignaaplicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignaaplicador persistentAsignaaplicador = em.find(Asignaaplicador.class, asignaaplicador.getClaveAsigna());
            Aplicacionexamen claveAplicacionOld = persistentAsignaaplicador.getClaveAplicacion();
            Aplicacionexamen claveAplicacionNew = asignaaplicador.getClaveAplicacion();
            if (claveAplicacionNew != null) {
                claveAplicacionNew = em.getReference(claveAplicacionNew.getClass(), claveAplicacionNew.getClaveAplicacion());
                asignaaplicador.setClaveAplicacion(claveAplicacionNew);
            }
            asignaaplicador = em.merge(asignaaplicador);
            if (claveAplicacionOld != null && !claveAplicacionOld.equals(claveAplicacionNew)) {
                claveAplicacionOld.getAsignaaplicadorList().remove(asignaaplicador);
                claveAplicacionOld = em.merge(claveAplicacionOld);
            }
            if (claveAplicacionNew != null && !claveAplicacionNew.equals(claveAplicacionOld)) {
                claveAplicacionNew.getAsignaaplicadorList().add(asignaaplicador);
                claveAplicacionNew = em.merge(claveAplicacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignaaplicador.getClaveAsigna();
                if (findAsignaaplicador(id) == null) {
                    throw new NonexistentEntityException("The asignaaplicador with id " + id + " no longer exists.");
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
            Asignaaplicador asignaaplicador;
            try {
                asignaaplicador = em.getReference(Asignaaplicador.class, id);
                asignaaplicador.getClaveAsigna();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaaplicador with id " + id + " no longer exists.", enfe);
            }
            Aplicacionexamen claveAplicacion = asignaaplicador.getClaveAplicacion();
            if (claveAplicacion != null) {
                claveAplicacion.getAsignaaplicadorList().remove(asignaaplicador);
                claveAplicacion = em.merge(claveAplicacion);
            }
            em.remove(asignaaplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignaaplicador> findAsignaaplicadorEntities() {
        return findAsignaaplicadorEntities(true, -1, -1);
    }

    public List<Asignaaplicador> findAsignaaplicadorEntities(int maxResults, int firstResult) {
        return findAsignaaplicadorEntities(false, maxResults, firstResult);
    }

    private List<Asignaaplicador> findAsignaaplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignaaplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignaaplicador findAsignaaplicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignaaplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaaplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignaaplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
