/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AsignacionTipomovimientorecurso;
import com.utez.integracion.entity.AsignacionTipomovimientorecursoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoMovimiento;
import com.utez.integracion.entity.RecursoHumano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionTipomovimientorecursoJpaController implements Serializable {

    public AsignacionTipomovimientorecursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionTipomovimientorecurso asignacionTipomovimientorecurso) throws PreexistingEntityException, Exception {
        if (asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK() == null) {
            asignacionTipomovimientorecurso.setAsignacionTipomovimientorecursoPK(new AsignacionTipomovimientorecursoPK());
        }
        asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK().setIdTipomovimiento(asignacionTipomovimientorecurso.getTipoMovimiento().getIdTipomovimiento());
        asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK().setIdRecursohumano(asignacionTipomovimientorecurso.getRecursoHumano().getIdRecursohumano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovimiento tipoMovimiento = asignacionTipomovimientorecurso.getTipoMovimiento();
            if (tipoMovimiento != null) {
                tipoMovimiento = em.getReference(tipoMovimiento.getClass(), tipoMovimiento.getIdTipomovimiento());
                asignacionTipomovimientorecurso.setTipoMovimiento(tipoMovimiento);
            }
            RecursoHumano recursoHumano = asignacionTipomovimientorecurso.getRecursoHumano();
            if (recursoHumano != null) {
                recursoHumano = em.getReference(recursoHumano.getClass(), recursoHumano.getIdRecursohumano());
                asignacionTipomovimientorecurso.setRecursoHumano(recursoHumano);
            }
            em.persist(asignacionTipomovimientorecurso);
            if (tipoMovimiento != null) {
                tipoMovimiento.getAsignacionTipomovimientorecursoList().add(asignacionTipomovimientorecurso);
                tipoMovimiento = em.merge(tipoMovimiento);
            }
            if (recursoHumano != null) {
                recursoHumano.getAsignacionTipomovimientorecursoList().add(asignacionTipomovimientorecurso);
                recursoHumano = em.merge(recursoHumano);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignacionTipomovimientorecurso(asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK()) != null) {
                throw new PreexistingEntityException("AsignacionTipomovimientorecurso " + asignacionTipomovimientorecurso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionTipomovimientorecurso asignacionTipomovimientorecurso) throws NonexistentEntityException, Exception {
        asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK().setIdTipomovimiento(asignacionTipomovimientorecurso.getTipoMovimiento().getIdTipomovimiento());
        asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK().setIdRecursohumano(asignacionTipomovimientorecurso.getRecursoHumano().getIdRecursohumano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionTipomovimientorecurso persistentAsignacionTipomovimientorecurso = em.find(AsignacionTipomovimientorecurso.class, asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK());
            TipoMovimiento tipoMovimientoOld = persistentAsignacionTipomovimientorecurso.getTipoMovimiento();
            TipoMovimiento tipoMovimientoNew = asignacionTipomovimientorecurso.getTipoMovimiento();
            RecursoHumano recursoHumanoOld = persistentAsignacionTipomovimientorecurso.getRecursoHumano();
            RecursoHumano recursoHumanoNew = asignacionTipomovimientorecurso.getRecursoHumano();
            if (tipoMovimientoNew != null) {
                tipoMovimientoNew = em.getReference(tipoMovimientoNew.getClass(), tipoMovimientoNew.getIdTipomovimiento());
                asignacionTipomovimientorecurso.setTipoMovimiento(tipoMovimientoNew);
            }
            if (recursoHumanoNew != null) {
                recursoHumanoNew = em.getReference(recursoHumanoNew.getClass(), recursoHumanoNew.getIdRecursohumano());
                asignacionTipomovimientorecurso.setRecursoHumano(recursoHumanoNew);
            }
            asignacionTipomovimientorecurso = em.merge(asignacionTipomovimientorecurso);
            if (tipoMovimientoOld != null && !tipoMovimientoOld.equals(tipoMovimientoNew)) {
                tipoMovimientoOld.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecurso);
                tipoMovimientoOld = em.merge(tipoMovimientoOld);
            }
            if (tipoMovimientoNew != null && !tipoMovimientoNew.equals(tipoMovimientoOld)) {
                tipoMovimientoNew.getAsignacionTipomovimientorecursoList().add(asignacionTipomovimientorecurso);
                tipoMovimientoNew = em.merge(tipoMovimientoNew);
            }
            if (recursoHumanoOld != null && !recursoHumanoOld.equals(recursoHumanoNew)) {
                recursoHumanoOld.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecurso);
                recursoHumanoOld = em.merge(recursoHumanoOld);
            }
            if (recursoHumanoNew != null && !recursoHumanoNew.equals(recursoHumanoOld)) {
                recursoHumanoNew.getAsignacionTipomovimientorecursoList().add(asignacionTipomovimientorecurso);
                recursoHumanoNew = em.merge(recursoHumanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignacionTipomovimientorecursoPK id = asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK();
                if (findAsignacionTipomovimientorecurso(id) == null) {
                    throw new NonexistentEntityException("The asignacionTipomovimientorecurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignacionTipomovimientorecursoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionTipomovimientorecurso asignacionTipomovimientorecurso;
            try {
                asignacionTipomovimientorecurso = em.getReference(AsignacionTipomovimientorecurso.class, id);
                asignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionTipomovimientorecurso with id " + id + " no longer exists.", enfe);
            }
            TipoMovimiento tipoMovimiento = asignacionTipomovimientorecurso.getTipoMovimiento();
            if (tipoMovimiento != null) {
                tipoMovimiento.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecurso);
                tipoMovimiento = em.merge(tipoMovimiento);
            }
            RecursoHumano recursoHumano = asignacionTipomovimientorecurso.getRecursoHumano();
            if (recursoHumano != null) {
                recursoHumano.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecurso);
                recursoHumano = em.merge(recursoHumano);
            }
            em.remove(asignacionTipomovimientorecurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionTipomovimientorecurso> findAsignacionTipomovimientorecursoEntities() {
        return findAsignacionTipomovimientorecursoEntities(true, -1, -1);
    }

    public List<AsignacionTipomovimientorecurso> findAsignacionTipomovimientorecursoEntities(int maxResults, int firstResult) {
        return findAsignacionTipomovimientorecursoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionTipomovimientorecurso> findAsignacionTipomovimientorecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionTipomovimientorecurso as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionTipomovimientorecurso findAsignacionTipomovimientorecurso(AsignacionTipomovimientorecursoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionTipomovimientorecurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionTipomovimientorecursoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionTipomovimientorecurso as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
