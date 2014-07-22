/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AsignacionExamenimpreso;
import com.utez.integracion.entity.AsignacionExamenimpresoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.FechaExamen;
import com.utez.integracion.entity.ExamenImpreso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionExamenimpresoJpaController implements Serializable {

    public AsignacionExamenimpresoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionExamenimpreso asignacionExamenimpreso) throws PreexistingEntityException, Exception {
        if (asignacionExamenimpreso.getAsignacionExamenimpresoPK() == null) {
            asignacionExamenimpreso.setAsignacionExamenimpresoPK(new AsignacionExamenimpresoPK());
        }
        asignacionExamenimpreso.getAsignacionExamenimpresoPK().setIdExamenimpreso(asignacionExamenimpreso.getExamenImpreso().getIdExamenimpreso());
        asignacionExamenimpreso.getAsignacionExamenimpresoPK().setIdFechaexamen(asignacionExamenimpreso.getFechaExamen().getIdFechaexamen());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamen fechaExamen = asignacionExamenimpreso.getFechaExamen();
            if (fechaExamen != null) {
                fechaExamen = em.getReference(fechaExamen.getClass(), fechaExamen.getIdFechaexamen());
                asignacionExamenimpreso.setFechaExamen(fechaExamen);
            }
            ExamenImpreso examenImpreso = asignacionExamenimpreso.getExamenImpreso();
            if (examenImpreso != null) {
                examenImpreso = em.getReference(examenImpreso.getClass(), examenImpreso.getIdExamenimpreso());
                asignacionExamenimpreso.setExamenImpreso(examenImpreso);
            }
            em.persist(asignacionExamenimpreso);
            if (fechaExamen != null) {
                fechaExamen.getAsignacionExamenimpresoList().add(asignacionExamenimpreso);
                fechaExamen = em.merge(fechaExamen);
            }
            if (examenImpreso != null) {
                examenImpreso.getAsignacionExamenimpresoList().add(asignacionExamenimpreso);
                examenImpreso = em.merge(examenImpreso);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignacionExamenimpreso(asignacionExamenimpreso.getAsignacionExamenimpresoPK()) != null) {
                throw new PreexistingEntityException("AsignacionExamenimpreso " + asignacionExamenimpreso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionExamenimpreso asignacionExamenimpreso) throws NonexistentEntityException, Exception {
        asignacionExamenimpreso.getAsignacionExamenimpresoPK().setIdExamenimpreso(asignacionExamenimpreso.getExamenImpreso().getIdExamenimpreso());
        asignacionExamenimpreso.getAsignacionExamenimpresoPK().setIdFechaexamen(asignacionExamenimpreso.getFechaExamen().getIdFechaexamen());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionExamenimpreso persistentAsignacionExamenimpreso = em.find(AsignacionExamenimpreso.class, asignacionExamenimpreso.getAsignacionExamenimpresoPK());
            FechaExamen fechaExamenOld = persistentAsignacionExamenimpreso.getFechaExamen();
            FechaExamen fechaExamenNew = asignacionExamenimpreso.getFechaExamen();
            ExamenImpreso examenImpresoOld = persistentAsignacionExamenimpreso.getExamenImpreso();
            ExamenImpreso examenImpresoNew = asignacionExamenimpreso.getExamenImpreso();
            if (fechaExamenNew != null) {
                fechaExamenNew = em.getReference(fechaExamenNew.getClass(), fechaExamenNew.getIdFechaexamen());
                asignacionExamenimpreso.setFechaExamen(fechaExamenNew);
            }
            if (examenImpresoNew != null) {
                examenImpresoNew = em.getReference(examenImpresoNew.getClass(), examenImpresoNew.getIdExamenimpreso());
                asignacionExamenimpreso.setExamenImpreso(examenImpresoNew);
            }
            asignacionExamenimpreso = em.merge(asignacionExamenimpreso);
            if (fechaExamenOld != null && !fechaExamenOld.equals(fechaExamenNew)) {
                fechaExamenOld.getAsignacionExamenimpresoList().remove(asignacionExamenimpreso);
                fechaExamenOld = em.merge(fechaExamenOld);
            }
            if (fechaExamenNew != null && !fechaExamenNew.equals(fechaExamenOld)) {
                fechaExamenNew.getAsignacionExamenimpresoList().add(asignacionExamenimpreso);
                fechaExamenNew = em.merge(fechaExamenNew);
            }
            if (examenImpresoOld != null && !examenImpresoOld.equals(examenImpresoNew)) {
                examenImpresoOld.getAsignacionExamenimpresoList().remove(asignacionExamenimpreso);
                examenImpresoOld = em.merge(examenImpresoOld);
            }
            if (examenImpresoNew != null && !examenImpresoNew.equals(examenImpresoOld)) {
                examenImpresoNew.getAsignacionExamenimpresoList().add(asignacionExamenimpreso);
                examenImpresoNew = em.merge(examenImpresoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignacionExamenimpresoPK id = asignacionExamenimpreso.getAsignacionExamenimpresoPK();
                if (findAsignacionExamenimpreso(id) == null) {
                    throw new NonexistentEntityException("The asignacionExamenimpreso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignacionExamenimpresoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionExamenimpreso asignacionExamenimpreso;
            try {
                asignacionExamenimpreso = em.getReference(AsignacionExamenimpreso.class, id);
                asignacionExamenimpreso.getAsignacionExamenimpresoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionExamenimpreso with id " + id + " no longer exists.", enfe);
            }
            FechaExamen fechaExamen = asignacionExamenimpreso.getFechaExamen();
            if (fechaExamen != null) {
                fechaExamen.getAsignacionExamenimpresoList().remove(asignacionExamenimpreso);
                fechaExamen = em.merge(fechaExamen);
            }
            ExamenImpreso examenImpreso = asignacionExamenimpreso.getExamenImpreso();
            if (examenImpreso != null) {
                examenImpreso.getAsignacionExamenimpresoList().remove(asignacionExamenimpreso);
                examenImpreso = em.merge(examenImpreso);
            }
            em.remove(asignacionExamenimpreso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionExamenimpreso> findAsignacionExamenimpresoEntities() {
        return findAsignacionExamenimpresoEntities(true, -1, -1);
    }

    public List<AsignacionExamenimpreso> findAsignacionExamenimpresoEntities(int maxResults, int firstResult) {
        return findAsignacionExamenimpresoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionExamenimpreso> findAsignacionExamenimpresoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionExamenimpreso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionExamenimpreso findAsignacionExamenimpreso(AsignacionExamenimpresoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionExamenimpreso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionExamenimpresoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionExamenimpreso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
