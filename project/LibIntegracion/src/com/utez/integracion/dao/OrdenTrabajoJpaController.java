/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.OrdenTrabajo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoOrdentrabajo;
import com.utez.integracion.entity.OrdentrabajoTitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class OrdenTrabajoJpaController implements Serializable {

    public OrdenTrabajoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenTrabajo ordenTrabajo) {
        if (ordenTrabajo.getOrdentrabajoTitulacionList() == null) {
            ordenTrabajo.setOrdentrabajoTitulacionList(new ArrayList<OrdentrabajoTitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoOrdentrabajo idTipoordentrabajo = ordenTrabajo.getIdTipoordentrabajo();
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo = em.getReference(idTipoordentrabajo.getClass(), idTipoordentrabajo.getIdTipoordentrabajo());
                ordenTrabajo.setIdTipoordentrabajo(idTipoordentrabajo);
            }
            List<OrdentrabajoTitulacion> attachedOrdentrabajoTitulacionList = new ArrayList<OrdentrabajoTitulacion>();
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListOrdentrabajoTitulacionToAttach : ordenTrabajo.getOrdentrabajoTitulacionList()) {
                ordentrabajoTitulacionListOrdentrabajoTitulacionToAttach = em.getReference(ordentrabajoTitulacionListOrdentrabajoTitulacionToAttach.getClass(), ordentrabajoTitulacionListOrdentrabajoTitulacionToAttach.getOrdentrabajoTitulacionPK());
                attachedOrdentrabajoTitulacionList.add(ordentrabajoTitulacionListOrdentrabajoTitulacionToAttach);
            }
            ordenTrabajo.setOrdentrabajoTitulacionList(attachedOrdentrabajoTitulacionList);
            em.persist(ordenTrabajo);
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo.getOrdenTrabajoList().add(ordenTrabajo);
                idTipoordentrabajo = em.merge(idTipoordentrabajo);
            }
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListOrdentrabajoTitulacion : ordenTrabajo.getOrdentrabajoTitulacionList()) {
                OrdenTrabajo oldOrdenTrabajoOfOrdentrabajoTitulacionListOrdentrabajoTitulacion = ordentrabajoTitulacionListOrdentrabajoTitulacion.getOrdenTrabajo();
                ordentrabajoTitulacionListOrdentrabajoTitulacion.setOrdenTrabajo(ordenTrabajo);
                ordentrabajoTitulacionListOrdentrabajoTitulacion = em.merge(ordentrabajoTitulacionListOrdentrabajoTitulacion);
                if (oldOrdenTrabajoOfOrdentrabajoTitulacionListOrdentrabajoTitulacion != null) {
                    oldOrdenTrabajoOfOrdentrabajoTitulacionListOrdentrabajoTitulacion.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacionListOrdentrabajoTitulacion);
                    oldOrdenTrabajoOfOrdentrabajoTitulacionListOrdentrabajoTitulacion = em.merge(oldOrdenTrabajoOfOrdentrabajoTitulacionListOrdentrabajoTitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenTrabajo ordenTrabajo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenTrabajo persistentOrdenTrabajo = em.find(OrdenTrabajo.class, ordenTrabajo.getIdOrdentrabajo());
            TipoOrdentrabajo idTipoordentrabajoOld = persistentOrdenTrabajo.getIdTipoordentrabajo();
            TipoOrdentrabajo idTipoordentrabajoNew = ordenTrabajo.getIdTipoordentrabajo();
            List<OrdentrabajoTitulacion> ordentrabajoTitulacionListOld = persistentOrdenTrabajo.getOrdentrabajoTitulacionList();
            List<OrdentrabajoTitulacion> ordentrabajoTitulacionListNew = ordenTrabajo.getOrdentrabajoTitulacionList();
            List<String> illegalOrphanMessages = null;
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListOldOrdentrabajoTitulacion : ordentrabajoTitulacionListOld) {
                if (!ordentrabajoTitulacionListNew.contains(ordentrabajoTitulacionListOldOrdentrabajoTitulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrdentrabajoTitulacion " + ordentrabajoTitulacionListOldOrdentrabajoTitulacion + " since its ordenTrabajo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoordentrabajoNew != null) {
                idTipoordentrabajoNew = em.getReference(idTipoordentrabajoNew.getClass(), idTipoordentrabajoNew.getIdTipoordentrabajo());
                ordenTrabajo.setIdTipoordentrabajo(idTipoordentrabajoNew);
            }
            List<OrdentrabajoTitulacion> attachedOrdentrabajoTitulacionListNew = new ArrayList<OrdentrabajoTitulacion>();
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListNewOrdentrabajoTitulacionToAttach : ordentrabajoTitulacionListNew) {
                ordentrabajoTitulacionListNewOrdentrabajoTitulacionToAttach = em.getReference(ordentrabajoTitulacionListNewOrdentrabajoTitulacionToAttach.getClass(), ordentrabajoTitulacionListNewOrdentrabajoTitulacionToAttach.getOrdentrabajoTitulacionPK());
                attachedOrdentrabajoTitulacionListNew.add(ordentrabajoTitulacionListNewOrdentrabajoTitulacionToAttach);
            }
            ordentrabajoTitulacionListNew = attachedOrdentrabajoTitulacionListNew;
            ordenTrabajo.setOrdentrabajoTitulacionList(ordentrabajoTitulacionListNew);
            ordenTrabajo = em.merge(ordenTrabajo);
            if (idTipoordentrabajoOld != null && !idTipoordentrabajoOld.equals(idTipoordentrabajoNew)) {
                idTipoordentrabajoOld.getOrdenTrabajoList().remove(ordenTrabajo);
                idTipoordentrabajoOld = em.merge(idTipoordentrabajoOld);
            }
            if (idTipoordentrabajoNew != null && !idTipoordentrabajoNew.equals(idTipoordentrabajoOld)) {
                idTipoordentrabajoNew.getOrdenTrabajoList().add(ordenTrabajo);
                idTipoordentrabajoNew = em.merge(idTipoordentrabajoNew);
            }
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListNewOrdentrabajoTitulacion : ordentrabajoTitulacionListNew) {
                if (!ordentrabajoTitulacionListOld.contains(ordentrabajoTitulacionListNewOrdentrabajoTitulacion)) {
                    OrdenTrabajo oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion = ordentrabajoTitulacionListNewOrdentrabajoTitulacion.getOrdenTrabajo();
                    ordentrabajoTitulacionListNewOrdentrabajoTitulacion.setOrdenTrabajo(ordenTrabajo);
                    ordentrabajoTitulacionListNewOrdentrabajoTitulacion = em.merge(ordentrabajoTitulacionListNewOrdentrabajoTitulacion);
                    if (oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion != null && !oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion.equals(ordenTrabajo)) {
                        oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion.getOrdentrabajoTitulacionList().remove(ordentrabajoTitulacionListNewOrdentrabajoTitulacion);
                        oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion = em.merge(oldOrdenTrabajoOfOrdentrabajoTitulacionListNewOrdentrabajoTitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ordenTrabajo.getIdOrdentrabajo();
                if (findOrdenTrabajo(id) == null) {
                    throw new NonexistentEntityException("The ordenTrabajo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenTrabajo ordenTrabajo;
            try {
                ordenTrabajo = em.getReference(OrdenTrabajo.class, id);
                ordenTrabajo.getIdOrdentrabajo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenTrabajo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrdentrabajoTitulacion> ordentrabajoTitulacionListOrphanCheck = ordenTrabajo.getOrdentrabajoTitulacionList();
            for (OrdentrabajoTitulacion ordentrabajoTitulacionListOrphanCheckOrdentrabajoTitulacion : ordentrabajoTitulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OrdenTrabajo (" + ordenTrabajo + ") cannot be destroyed since the OrdentrabajoTitulacion " + ordentrabajoTitulacionListOrphanCheckOrdentrabajoTitulacion + " in its ordentrabajoTitulacionList field has a non-nullable ordenTrabajo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoOrdentrabajo idTipoordentrabajo = ordenTrabajo.getIdTipoordentrabajo();
            if (idTipoordentrabajo != null) {
                idTipoordentrabajo.getOrdenTrabajoList().remove(ordenTrabajo);
                idTipoordentrabajo = em.merge(idTipoordentrabajo);
            }
            em.remove(ordenTrabajo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenTrabajo> findOrdenTrabajoEntities() {
        return findOrdenTrabajoEntities(true, -1, -1);
    }

    public List<OrdenTrabajo> findOrdenTrabajoEntities(int maxResults, int firstResult) {
        return findOrdenTrabajoEntities(false, maxResults, firstResult);
    }

    private List<OrdenTrabajo> findOrdenTrabajoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from OrdenTrabajo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OrdenTrabajo findOrdenTrabajo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenTrabajo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenTrabajoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from OrdenTrabajo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
