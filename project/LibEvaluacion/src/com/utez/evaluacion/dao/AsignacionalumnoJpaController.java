/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asignacionactividad;
import com.utez.evaluacion.entity.Asignacionalumno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionalumnoJpaController implements Serializable {

    public AsignacionalumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignacionalumno asignacionalumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignacionactividad claveAsignacion = asignacionalumno.getClaveAsignacion();
            if (claveAsignacion != null) {
                claveAsignacion = em.getReference(claveAsignacion.getClass(), claveAsignacion.getClaveAsignacion());
                asignacionalumno.setClaveAsignacion(claveAsignacion);
            }
            em.persist(asignacionalumno);
            if (claveAsignacion != null) {
                claveAsignacion.getAsignacionalumnoList().add(asignacionalumno);
                claveAsignacion = em.merge(claveAsignacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignacionalumno asignacionalumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignacionalumno persistentAsignacionalumno = em.find(Asignacionalumno.class, asignacionalumno.getClaveAsignacionAlumno());
            Asignacionactividad claveAsignacionOld = persistentAsignacionalumno.getClaveAsignacion();
            Asignacionactividad claveAsignacionNew = asignacionalumno.getClaveAsignacion();
            if (claveAsignacionNew != null) {
                claveAsignacionNew = em.getReference(claveAsignacionNew.getClass(), claveAsignacionNew.getClaveAsignacion());
                asignacionalumno.setClaveAsignacion(claveAsignacionNew);
            }
            asignacionalumno = em.merge(asignacionalumno);
            if (claveAsignacionOld != null && !claveAsignacionOld.equals(claveAsignacionNew)) {
                claveAsignacionOld.getAsignacionalumnoList().remove(asignacionalumno);
                claveAsignacionOld = em.merge(claveAsignacionOld);
            }
            if (claveAsignacionNew != null && !claveAsignacionNew.equals(claveAsignacionOld)) {
                claveAsignacionNew.getAsignacionalumnoList().add(asignacionalumno);
                claveAsignacionNew = em.merge(claveAsignacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignacionalumno.getClaveAsignacionAlumno();
                if (findAsignacionalumno(id) == null) {
                    throw new NonexistentEntityException("The asignacionalumno with id " + id + " no longer exists.");
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
            Asignacionalumno asignacionalumno;
            try {
                asignacionalumno = em.getReference(Asignacionalumno.class, id);
                asignacionalumno.getClaveAsignacionAlumno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionalumno with id " + id + " no longer exists.", enfe);
            }
            Asignacionactividad claveAsignacion = asignacionalumno.getClaveAsignacion();
            if (claveAsignacion != null) {
                claveAsignacion.getAsignacionalumnoList().remove(asignacionalumno);
                claveAsignacion = em.merge(claveAsignacion);
            }
            em.remove(asignacionalumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignacionalumno> findAsignacionalumnoEntities() {
        return findAsignacionalumnoEntities(true, -1, -1);
    }

    public List<Asignacionalumno> findAsignacionalumnoEntities(int maxResults, int firstResult) {
        return findAsignacionalumnoEntities(false, maxResults, firstResult);
    }

    private List<Asignacionalumno> findAsignacionalumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignacionalumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignacionalumno findAsignacionalumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignacionalumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionalumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignacionalumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
