/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TramiteTitulacion;
import com.utez.integracion.entity.OrdenTrabajo;
import com.utez.integracion.entity.OrdentrabajoTitulacion;
import com.utez.integracion.entity.OrdentrabajoTitulacionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OrdentrabajoTitulacionJpaController implements Serializable {

    public OrdentrabajoTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdentrabajoTitulacion ordentrabajoTitulacion) throws PreexistingEntityException, Exception {
        if (ordentrabajoTitulacion.getOrdentrabajoTitulacionPK() == null) {
            ordentrabajoTitulacion.setOrdentrabajoTitulacionPK(new OrdentrabajoTitulacionPK());
        }
        ordentrabajoTitulacion.getOrdentrabajoTitulacionPK().setIdTramitetitulacion(ordentrabajoTitulacion.getTramiteTitulacion().getIdTramitetitulacion());
        ordentrabajoTitulacion.getOrdentrabajoTitulacionPK().setIdOrdentrabajo(ordentrabajoTitulacion.getOrdenTrabajo().getIdOrdentrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TramiteTitulacion tramiteTitulacion = ordentrabajoTitulacion.getTramiteTitulacion();
            if (tramiteTitulacion != null) {
                tramiteTitulacion = em.getReference(tramiteTitulacion.getClass(), tramiteTitulacion.getIdTramitetitulacion());
                ordentrabajoTitulacion.setTramiteTitulacion(tramiteTitulacion);
            }
            OrdenTrabajo ordenTrabajo = ordentrabajoTitulacion.getOrdenTrabajo();
            if (ordenTrabajo != null) {
                ordenTrabajo = em.getReference(ordenTrabajo.getClass(), ordenTrabajo.getIdOrdentrabajo());
                ordentrabajoTitulacion.setOrdenTrabajo(ordenTrabajo);
            }
            em.persist(ordentrabajoTitulacion);
            if (tramiteTitulacion != null) {
                tramiteTitulacion.getOrdentrabajoTitulacionList().add(ordentrabajoTitulacion);
                tramiteTitulacion = em.merge(tramiteTitulacion);
            }
            if (ordenTrabajo != null) {
                ordenTrabajo.getOrdentrabajoTitulacionList().add(ordentrabajoTitulacion);
                ordenTrabajo = em.merge(ordenTrabajo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrdentrabajoTitulacion(ordentrabajoTitulacion.getOrdentrabajoTitulacionPK()) != null) {
                throw new PreexistingEntityException("OrdentrabajoTitulacion " + ordentrabajoTitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdentrabajoTitulacion ordentrabajoTitulacion) throws NonexistentEntityException, Exception {
        ordentrabajoTitulacion.getOrdentrabajoTitulacionPK().setIdTramitetitulacion(ordentrabajoTitulacion.getTramiteTitulacion().getIdTramitetitulacion());
        ordentrabajoTitulacion.getOrdentrabajoTitulacionPK().setIdOrdentrabajo(ordentrabajoTitulacion.getOrdenTrabajo().getIdOrdentrabajo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdentrabajoTitulacion persistentOrdentrabajoTitulacion = em.find(OrdentrabajoTitulacion.class, ordentrabajoTitulacion.getOrdentrabajoTitulacionPK());
            TramiteTitulacion tramiteTitulacionOld = persistentOrdentrabajoTitulacion.getTramiteTitulacion();
            TramiteTitulacion tramiteTitulacionNew = ordentrabajoTitulacion.getTramiteTitulacion();
            OrdenTrabajo ordenTrabajoOld = persistentOrdentrabajoTitulacion.getOrdenTrabajo();
            OrdenTrabajo ordenTrabajoNew = ordentrabajoTitulacion.getOrdenTrabajo();
            if (tramiteTitulacionNew != null) {
                tramiteTitulacionNew = em.getReference(tramiteTitulacionNew.getClass(), tramiteTitulacionNew.getIdTramitetitulacion());
                ordentrabajoTitulacion.setTramiteTitulacion(tramiteTitulacionNew);
            }
            if (ordenTrabajoNew != null) {
                ordenTrabajoNew = em.getReference(ordenTrabajoNew.getClass(), ordenTrabajoNew.getIdOrdentrabajo());
                ordentrabajoTitulacion.setOrdenTrabajo(ordenTrabajoNew);
            }
            ordentrabajoTitulacion = em.merge(ordentrabajoTitulacion);
            if (tramiteTitulacionOld != null && !tramiteTitulacionOld.equals(tramiteTitulacionNew)) {
                tramiteTitulacionOld.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacion);
                tramiteTitulacionOld = em.merge(tramiteTitulacionOld);
            }
            if (tramiteTitulacionNew != null && !tramiteTitulacionNew.equals(tramiteTitulacionOld)) {
                tramiteTitulacionNew.getOrdentrabajoTitulacionList().add(ordentrabajoTitulacion);
                tramiteTitulacionNew = em.merge(tramiteTitulacionNew);
            }
            if (ordenTrabajoOld != null && !ordenTrabajoOld.equals(ordenTrabajoNew)) {
                ordenTrabajoOld.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacion);
                ordenTrabajoOld = em.merge(ordenTrabajoOld);
            }
            if (ordenTrabajoNew != null && !ordenTrabajoNew.equals(ordenTrabajoOld)) {
                ordenTrabajoNew.getOrdentrabajoTitulacionList().add(ordentrabajoTitulacion);
                ordenTrabajoNew = em.merge(ordenTrabajoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OrdentrabajoTitulacionPK id = ordentrabajoTitulacion.getOrdentrabajoTitulacionPK();
                if (findOrdentrabajoTitulacion(id) == null) {
                    throw new NonexistentEntityException("The ordentrabajoTitulacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OrdentrabajoTitulacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdentrabajoTitulacion ordentrabajoTitulacion;
            try {
                ordentrabajoTitulacion = em.getReference(OrdentrabajoTitulacion.class, id);
                ordentrabajoTitulacion.getOrdentrabajoTitulacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordentrabajoTitulacion with id " + id + " no longer exists.", enfe);
            }
            TramiteTitulacion tramiteTitulacion = ordentrabajoTitulacion.getTramiteTitulacion();
            if (tramiteTitulacion != null) {
                tramiteTitulacion.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacion);
                tramiteTitulacion = em.merge(tramiteTitulacion);
            }
            OrdenTrabajo ordenTrabajo = ordentrabajoTitulacion.getOrdenTrabajo();
            if (ordenTrabajo != null) {
                ordenTrabajo.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacion);
                ordenTrabajo = em.merge(ordenTrabajo);
            }
            em.remove(ordentrabajoTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdentrabajoTitulacion> findOrdentrabajoTitulacionEntities() {
        return findOrdentrabajoTitulacionEntities(true, -1, -1);
    }

    public List<OrdentrabajoTitulacion> findOrdentrabajoTitulacionEntities(int maxResults, int firstResult) {
        return findOrdentrabajoTitulacionEntities(false, maxResults, firstResult);
    }

    private List<OrdentrabajoTitulacion> findOrdentrabajoTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from OrdentrabajoTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OrdentrabajoTitulacion findOrdentrabajoTitulacion(OrdentrabajoTitulacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdentrabajoTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdentrabajoTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from OrdentrabajoTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
