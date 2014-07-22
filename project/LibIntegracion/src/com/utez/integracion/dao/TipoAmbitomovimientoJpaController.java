/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.AsignacionAmbitomovimientotipo;
import com.utez.integracion.entity.TipoAmbitomovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoAmbitomovimientoJpaController implements Serializable {

    public TipoAmbitomovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoAmbitomovimiento tipoAmbitomovimiento) {
        if (tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList() == null) {
            tipoAmbitomovimiento.setAsignacionAmbitomovimientotipoList(new ArrayList<AsignacionAmbitomovimientotipo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionAmbitomovimientotipo> attachedAsignacionAmbitomovimientotipoList = new ArrayList<AsignacionAmbitomovimientotipo>();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach : tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList()) {
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach = em.getReference(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach.getClass(), asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach.getAsignacionAmbitomovimientotipoPK());
                attachedAsignacionAmbitomovimientotipoList.add(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach);
            }
            tipoAmbitomovimiento.setAsignacionAmbitomovimientotipoList(attachedAsignacionAmbitomovimientotipoList);
            em.persist(tipoAmbitomovimiento);
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo : tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList()) {
                TipoAmbitomovimiento oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.setTipoAmbitomovimiento(tipoAmbitomovimiento);
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = em.merge(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                if (oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo != null) {
                    oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                    oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = em.merge(oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoAmbitomovimiento tipoAmbitomovimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAmbitomovimiento persistentTipoAmbitomovimiento = em.find(TipoAmbitomovimiento.class, tipoAmbitomovimiento.getIdTipoambitomovimiento());
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListOld = persistentTipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList();
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListNew = tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList();
            List<String> illegalOrphanMessages = null;
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListOld) {
                if (!asignacionAmbitomovimientotipoListNew.contains(asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionAmbitomovimientotipo " + asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo + " since its tipoAmbitomovimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AsignacionAmbitomovimientotipo> attachedAsignacionAmbitomovimientotipoListNew = new ArrayList<AsignacionAmbitomovimientotipo>();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach : asignacionAmbitomovimientotipoListNew) {
                asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach = em.getReference(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach.getClass(), asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach.getAsignacionAmbitomovimientotipoPK());
                attachedAsignacionAmbitomovimientotipoListNew.add(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach);
            }
            asignacionAmbitomovimientotipoListNew = attachedAsignacionAmbitomovimientotipoListNew;
            tipoAmbitomovimiento.setAsignacionAmbitomovimientotipoList(asignacionAmbitomovimientotipoListNew);
            tipoAmbitomovimiento = em.merge(tipoAmbitomovimiento);
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListNew) {
                if (!asignacionAmbitomovimientotipoListOld.contains(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo)) {
                    TipoAmbitomovimiento oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.getTipoAmbitomovimiento();
                    asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.setTipoAmbitomovimiento(tipoAmbitomovimiento);
                    asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = em.merge(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                    if (oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo != null && !oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.equals(tipoAmbitomovimiento)) {
                        oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                        oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = em.merge(oldTipoAmbitomovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoAmbitomovimiento.getIdTipoambitomovimiento();
                if (findTipoAmbitomovimiento(id) == null) {
                    throw new NonexistentEntityException("The tipoAmbitomovimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoAmbitomovimiento tipoAmbitomovimiento;
            try {
                tipoAmbitomovimiento = em.getReference(TipoAmbitomovimiento.class, id);
                tipoAmbitomovimiento.getIdTipoambitomovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoAmbitomovimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListOrphanCheck = tipoAmbitomovimiento.getAsignacionAmbitomovimientotipoList();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListOrphanCheckAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoAmbitomovimiento (" + tipoAmbitomovimiento + ") cannot be destroyed since the AsignacionAmbitomovimientotipo " + asignacionAmbitomovimientotipoListOrphanCheckAsignacionAmbitomovimientotipo + " in its asignacionAmbitomovimientotipoList field has a non-nullable tipoAmbitomovimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoAmbitomovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities() {
        return findTipoAmbitomovimientoEntities(true, -1, -1);
    }

    public List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities(int maxResults, int firstResult) {
        return findTipoAmbitomovimientoEntities(false, maxResults, firstResult);
    }

    private List<TipoAmbitomovimiento> findTipoAmbitomovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoAmbitomovimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoAmbitomovimiento findTipoAmbitomovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoAmbitomovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoAmbitomovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoAmbitomovimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
