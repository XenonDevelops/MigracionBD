/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import com.utez.integracion.entity.AsignacionAmbitomovimientotipo;
import com.utez.integracion.entity.AsignacionAmbitomovimientotipoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoMovimiento;
import com.utez.integracion.entity.TipoAmbitomovimiento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAmbitomovimientotipoJpaController implements Serializable {

    public AsignacionAmbitomovimientotipoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipo) throws PreexistingEntityException, Exception {
        if (asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK() == null) {
            asignacionAmbitomovimientotipo.setAsignacionAmbitomovimientotipoPK(new AsignacionAmbitomovimientotipoPK());
        }
        asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK().setIdTipomovimiento(asignacionAmbitomovimientotipo.getTipoMovimiento().getIdTipomovimiento());
        asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK().setIdAmbitomovimiento(asignacionAmbitomovimientotipo.getTipoAmbitomovimiento().getIdTipoambitomovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovimiento tipoMovimiento = asignacionAmbitomovimientotipo.getTipoMovimiento();
            if (tipoMovimiento != null) {
                tipoMovimiento = em.getReference(tipoMovimiento.getClass(), tipoMovimiento.getIdTipomovimiento());
                asignacionAmbitomovimientotipo.setTipoMovimiento(tipoMovimiento);
            }
            TipoAmbitomovimiento tipoAmbitomovimiento = asignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
            if (tipoAmbitomovimiento != null) {
                tipoAmbitomovimiento = em.getReference(tipoAmbitomovimiento.getClass(), tipoAmbitomovimiento.getIdTipoambitomovimiento());
                asignacionAmbitomovimientotipo.setTipoAmbitomovimiento(tipoAmbitomovimiento);
            }
            em.persist(asignacionAmbitomovimientotipo);
            if (tipoMovimiento != null) {
                tipoMovimiento.getAsignacionAmbitomovimientotipoList().add(asignacionAmbitomovimientotipo);
                tipoMovimiento = em.merge(tipoMovimiento);
            }
            if (tipoAmbitomovimiento != null) {
                tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList().add(asignacionAmbitomovimientotipo);
                tipoAmbitomovimiento = em.merge(tipoAmbitomovimiento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignacionAmbitomovimientotipo(asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK()) != null) {
                throw new PreexistingEntityException("AsignacionAmbitomovimientotipo " + asignacionAmbitomovimientotipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipo) throws NonexistentEntityException, Exception {
        asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK().setIdTipomovimiento(asignacionAmbitomovimientotipo.getTipoMovimiento().getIdTipomovimiento());
        asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK().setIdAmbitomovimiento(asignacionAmbitomovimientotipo.getTipoAmbitomovimiento().getIdTipoambitomovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAmbitomovimientotipo persistentAsignacionAmbitomovimientotipo = em.find(AsignacionAmbitomovimientotipo.class, asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK());
            TipoMovimiento tipoMovimientoOld = persistentAsignacionAmbitomovimientotipo.getTipoMovimiento();
            TipoMovimiento tipoMovimientoNew = asignacionAmbitomovimientotipo.getTipoMovimiento();
            TipoAmbitomovimiento tipoAmbitomovimientoOld = persistentAsignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
            TipoAmbitomovimiento tipoAmbitomovimientoNew = asignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
            if (tipoMovimientoNew != null) {
                tipoMovimientoNew = em.getReference(tipoMovimientoNew.getClass(), tipoMovimientoNew.getIdTipomovimiento());
                asignacionAmbitomovimientotipo.setTipoMovimiento(tipoMovimientoNew);
            }
            if (tipoAmbitomovimientoNew != null) {
                tipoAmbitomovimientoNew = em.getReference(tipoAmbitomovimientoNew.getClass(), tipoAmbitomovimientoNew.getIdTipoambitomovimiento());
                asignacionAmbitomovimientotipo.setTipoAmbitomovimiento(tipoAmbitomovimientoNew);
            }
            asignacionAmbitomovimientotipo = em.merge(asignacionAmbitomovimientotipo);
            if (tipoMovimientoOld != null && !tipoMovimientoOld.equals(tipoMovimientoNew)) {
                tipoMovimientoOld.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipo);
                tipoMovimientoOld = em.merge(tipoMovimientoOld);
            }
            if (tipoMovimientoNew != null && !tipoMovimientoNew.equals(tipoMovimientoOld)) {
                tipoMovimientoNew.getAsignacionAmbitomovimientotipoList().add(asignacionAmbitomovimientotipo);
                tipoMovimientoNew = em.merge(tipoMovimientoNew);
            }
            if (tipoAmbitomovimientoOld != null && !tipoAmbitomovimientoOld.equals(tipoAmbitomovimientoNew)) {
                tipoAmbitomovimientoOld.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipo);
                tipoAmbitomovimientoOld = em.merge(tipoAmbitomovimientoOld);
            }
            if (tipoAmbitomovimientoNew != null && !tipoAmbitomovimientoNew.equals(tipoAmbitomovimientoOld)) {
                tipoAmbitomovimientoNew.getAsignacionAmbitomovimientotipoList().add(asignacionAmbitomovimientotipo);
                tipoAmbitomovimientoNew = em.merge(tipoAmbitomovimientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignacionAmbitomovimientotipoPK id = asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK();
                if (findAsignacionAmbitomovimientotipo(id) == null) {
                    throw new NonexistentEntityException("The asignacionAmbitomovimientotipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignacionAmbitomovimientotipoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipo;
            try {
                asignacionAmbitomovimientotipo = em.getReference(AsignacionAmbitomovimientotipo.class, id);
                asignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAmbitomovimientotipo with id " + id + " no longer exists.", enfe);
            }
            TipoMovimiento tipoMovimiento = asignacionAmbitomovimientotipo.getTipoMovimiento();
            if (tipoMovimiento != null) {
                tipoMovimiento.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipo);
                tipoMovimiento = em.merge(tipoMovimiento);
            }
            TipoAmbitomovimiento tipoAmbitomovimiento = asignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
            if (tipoAmbitomovimiento != null) {
                tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipo);
                tipoAmbitomovimiento = em.merge(tipoAmbitomovimiento);
            }
            em.remove(asignacionAmbitomovimientotipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAmbitomovimientotipo> findAsignacionAmbitomovimientotipoEntities() {
        return findAsignacionAmbitomovimientotipoEntities(true, -1, -1);
    }

    public List<AsignacionAmbitomovimientotipo> findAsignacionAmbitomovimientotipoEntities(int maxResults, int firstResult) {
        return findAsignacionAmbitomovimientotipoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAmbitomovimientotipo> findAsignacionAmbitomovimientotipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAmbitomovimientotipo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAmbitomovimientotipo findAsignacionAmbitomovimientotipo(AsignacionAmbitomovimientotipoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAmbitomovimientotipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAmbitomovimientotipoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAmbitomovimientotipo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
