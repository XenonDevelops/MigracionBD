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
import com.utez.integracion.entity.AsignacionTipotitulacionplan;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.SolicitudTitulacion;
import com.utez.integracion.entity.DocumentoTipotitulacion;
import com.utez.integracion.entity.TipoTitulacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoTitulacionJpaController implements Serializable {

    public TipoTitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTitulacion tipoTitulacion) {
        if (tipoTitulacion.getAsignacionTipotitulacionplanList() == null) {
            tipoTitulacion.setAsignacionTipotitulacionplanList(new ArrayList<AsignacionTipotitulacionplan>());
        }
        if (tipoTitulacion.getSolicitudTitulacionList() == null) {
            tipoTitulacion.setSolicitudTitulacionList(new ArrayList<SolicitudTitulacion>());
        }
        if (tipoTitulacion.getDocumentoTipotitulacionList() == null) {
            tipoTitulacion.setDocumentoTipotitulacionList(new ArrayList<DocumentoTipotitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionTipotitulacionplan> attachedAsignacionTipotitulacionplanList = new ArrayList<AsignacionTipotitulacionplan>();
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListAsignacionTipotitulacionplanToAttach : tipoTitulacion.getAsignacionTipotitulacionplanList()) {
                asignacionTipotitulacionplanListAsignacionTipotitulacionplanToAttach = em.getReference(asignacionTipotitulacionplanListAsignacionTipotitulacionplanToAttach.getClass(), asignacionTipotitulacionplanListAsignacionTipotitulacionplanToAttach.getAsignacionTipotitulacionplanPK());
                attachedAsignacionTipotitulacionplanList.add(asignacionTipotitulacionplanListAsignacionTipotitulacionplanToAttach);
            }
            tipoTitulacion.setAsignacionTipotitulacionplanList(attachedAsignacionTipotitulacionplanList);
            List<SolicitudTitulacion> attachedSolicitudTitulacionList = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacionToAttach : tipoTitulacion.getSolicitudTitulacionList()) {
                solicitudTitulacionListSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionList.add(solicitudTitulacionListSolicitudTitulacionToAttach);
            }
            tipoTitulacion.setSolicitudTitulacionList(attachedSolicitudTitulacionList);
            List<DocumentoTipotitulacion> attachedDocumentoTipotitulacionList = new ArrayList<DocumentoTipotitulacion>();
            for (DocumentoTipotitulacion documentoTipotitulacionListDocumentoTipotitulacionToAttach : tipoTitulacion.getDocumentoTipotitulacionList()) {
                documentoTipotitulacionListDocumentoTipotitulacionToAttach = em.getReference(documentoTipotitulacionListDocumentoTipotitulacionToAttach.getClass(), documentoTipotitulacionListDocumentoTipotitulacionToAttach.getDocumentoTipotitulacionPK());
                attachedDocumentoTipotitulacionList.add(documentoTipotitulacionListDocumentoTipotitulacionToAttach);
            }
            tipoTitulacion.setDocumentoTipotitulacionList(attachedDocumentoTipotitulacionList);
            em.persist(tipoTitulacion);
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListAsignacionTipotitulacionplan : tipoTitulacion.getAsignacionTipotitulacionplanList()) {
                TipoTitulacion oldTipoTitulacionOfAsignacionTipotitulacionplanListAsignacionTipotitulacionplan = asignacionTipotitulacionplanListAsignacionTipotitulacionplan.getTipoTitulacion();
                asignacionTipotitulacionplanListAsignacionTipotitulacionplan.setTipoTitulacion(tipoTitulacion);
                asignacionTipotitulacionplanListAsignacionTipotitulacionplan = em.merge(asignacionTipotitulacionplanListAsignacionTipotitulacionplan);
                if (oldTipoTitulacionOfAsignacionTipotitulacionplanListAsignacionTipotitulacionplan != null) {
                    oldTipoTitulacionOfAsignacionTipotitulacionplanListAsignacionTipotitulacionplan.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplanListAsignacionTipotitulacionplan);
                    oldTipoTitulacionOfAsignacionTipotitulacionplanListAsignacionTipotitulacionplan = em.merge(oldTipoTitulacionOfAsignacionTipotitulacionplanListAsignacionTipotitulacionplan);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : tipoTitulacion.getSolicitudTitulacionList()) {
                TipoTitulacion oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion = solicitudTitulacionListSolicitudTitulacion.getIdTipotitulacion();
                solicitudTitulacionListSolicitudTitulacion.setIdTipotitulacion(tipoTitulacion);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
                if (oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion != null) {
                    oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListSolicitudTitulacion);
                    oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion = em.merge(oldIdTipotitulacionOfSolicitudTitulacionListSolicitudTitulacion);
                }
            }
            for (DocumentoTipotitulacion documentoTipotitulacionListDocumentoTipotitulacion : tipoTitulacion.getDocumentoTipotitulacionList()) {
                TipoTitulacion oldTipoTitulacionOfDocumentoTipotitulacionListDocumentoTipotitulacion = documentoTipotitulacionListDocumentoTipotitulacion.getTipoTitulacion();
                documentoTipotitulacionListDocumentoTipotitulacion.setTipoTitulacion(tipoTitulacion);
                documentoTipotitulacionListDocumentoTipotitulacion = em.merge(documentoTipotitulacionListDocumentoTipotitulacion);
                if (oldTipoTitulacionOfDocumentoTipotitulacionListDocumentoTipotitulacion != null) {
                    oldTipoTitulacionOfDocumentoTipotitulacionListDocumentoTipotitulacion.getDocumentoTipotitulacionList().remove(documentoTipotitulacionListDocumentoTipotitulacion);
                    oldTipoTitulacionOfDocumentoTipotitulacionListDocumentoTipotitulacion = em.merge(oldTipoTitulacionOfDocumentoTipotitulacionListDocumentoTipotitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTitulacion tipoTitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTitulacion persistentTipoTitulacion = em.find(TipoTitulacion.class, tipoTitulacion.getIdTipotitulacion());
            List<AsignacionTipotitulacionplan> asignacionTipotitulacionplanListOld = persistentTipoTitulacion.getAsignacionTipotitulacionplanList();
            List<AsignacionTipotitulacionplan> asignacionTipotitulacionplanListNew = tipoTitulacion.getAsignacionTipotitulacionplanList();
            List<SolicitudTitulacion> solicitudTitulacionListOld = persistentTipoTitulacion.getSolicitudTitulacionList();
            List<SolicitudTitulacion> solicitudTitulacionListNew = tipoTitulacion.getSolicitudTitulacionList();
            List<DocumentoTipotitulacion> documentoTipotitulacionListOld = persistentTipoTitulacion.getDocumentoTipotitulacionList();
            List<DocumentoTipotitulacion> documentoTipotitulacionListNew = tipoTitulacion.getDocumentoTipotitulacionList();
            List<String> illegalOrphanMessages = null;
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListOldAsignacionTipotitulacionplan : asignacionTipotitulacionplanListOld) {
                if (!asignacionTipotitulacionplanListNew.contains(asignacionTipotitulacionplanListOldAsignacionTipotitulacionplan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionTipotitulacionplan " + asignacionTipotitulacionplanListOldAsignacionTipotitulacionplan + " since its tipoTitulacion field is not nullable.");
                }
            }
            for (DocumentoTipotitulacion documentoTipotitulacionListOldDocumentoTipotitulacion : documentoTipotitulacionListOld) {
                if (!documentoTipotitulacionListNew.contains(documentoTipotitulacionListOldDocumentoTipotitulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DocumentoTipotitulacion " + documentoTipotitulacionListOldDocumentoTipotitulacion + " since its tipoTitulacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AsignacionTipotitulacionplan> attachedAsignacionTipotitulacionplanListNew = new ArrayList<AsignacionTipotitulacionplan>();
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListNewAsignacionTipotitulacionplanToAttach : asignacionTipotitulacionplanListNew) {
                asignacionTipotitulacionplanListNewAsignacionTipotitulacionplanToAttach = em.getReference(asignacionTipotitulacionplanListNewAsignacionTipotitulacionplanToAttach.getClass(), asignacionTipotitulacionplanListNewAsignacionTipotitulacionplanToAttach.getAsignacionTipotitulacionplanPK());
                attachedAsignacionTipotitulacionplanListNew.add(asignacionTipotitulacionplanListNewAsignacionTipotitulacionplanToAttach);
            }
            asignacionTipotitulacionplanListNew = attachedAsignacionTipotitulacionplanListNew;
            tipoTitulacion.setAsignacionTipotitulacionplanList(asignacionTipotitulacionplanListNew);
            List<SolicitudTitulacion> attachedSolicitudTitulacionListNew = new ArrayList<SolicitudTitulacion>();
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacionToAttach : solicitudTitulacionListNew) {
                solicitudTitulacionListNewSolicitudTitulacionToAttach = em.getReference(solicitudTitulacionListNewSolicitudTitulacionToAttach.getClass(), solicitudTitulacionListNewSolicitudTitulacionToAttach.getIdSolicitudtitulacion());
                attachedSolicitudTitulacionListNew.add(solicitudTitulacionListNewSolicitudTitulacionToAttach);
            }
            solicitudTitulacionListNew = attachedSolicitudTitulacionListNew;
            tipoTitulacion.setSolicitudTitulacionList(solicitudTitulacionListNew);
            List<DocumentoTipotitulacion> attachedDocumentoTipotitulacionListNew = new ArrayList<DocumentoTipotitulacion>();
            for (DocumentoTipotitulacion documentoTipotitulacionListNewDocumentoTipotitulacionToAttach : documentoTipotitulacionListNew) {
                documentoTipotitulacionListNewDocumentoTipotitulacionToAttach = em.getReference(documentoTipotitulacionListNewDocumentoTipotitulacionToAttach.getClass(), documentoTipotitulacionListNewDocumentoTipotitulacionToAttach.getDocumentoTipotitulacionPK());
                attachedDocumentoTipotitulacionListNew.add(documentoTipotitulacionListNewDocumentoTipotitulacionToAttach);
            }
            documentoTipotitulacionListNew = attachedDocumentoTipotitulacionListNew;
            tipoTitulacion.setDocumentoTipotitulacionList(documentoTipotitulacionListNew);
            tipoTitulacion = em.merge(tipoTitulacion);
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan : asignacionTipotitulacionplanListNew) {
                if (!asignacionTipotitulacionplanListOld.contains(asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan)) {
                    TipoTitulacion oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan = asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan.getTipoTitulacion();
                    asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan.setTipoTitulacion(tipoTitulacion);
                    asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan = em.merge(asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan);
                    if (oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan != null && !oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan.equals(tipoTitulacion)) {
                        oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan.getAsignacionTipotitulacionplanList().remove(asignacionTipotitulacionplanListNewAsignacionTipotitulacionplan);
                        oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan = em.merge(oldTipoTitulacionOfAsignacionTipotitulacionplanListNewAsignacionTipotitulacionplan);
                    }
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListOldSolicitudTitulacion : solicitudTitulacionListOld) {
                if (!solicitudTitulacionListNew.contains(solicitudTitulacionListOldSolicitudTitulacion)) {
                    solicitudTitulacionListOldSolicitudTitulacion.setIdTipotitulacion(null);
                    solicitudTitulacionListOldSolicitudTitulacion = em.merge(solicitudTitulacionListOldSolicitudTitulacion);
                }
            }
            for (SolicitudTitulacion solicitudTitulacionListNewSolicitudTitulacion : solicitudTitulacionListNew) {
                if (!solicitudTitulacionListOld.contains(solicitudTitulacionListNewSolicitudTitulacion)) {
                    TipoTitulacion oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion = solicitudTitulacionListNewSolicitudTitulacion.getIdTipotitulacion();
                    solicitudTitulacionListNewSolicitudTitulacion.setIdTipotitulacion(tipoTitulacion);
                    solicitudTitulacionListNewSolicitudTitulacion = em.merge(solicitudTitulacionListNewSolicitudTitulacion);
                    if (oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion != null && !oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion.equals(tipoTitulacion)) {
                        oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion.getSolicitudTitulacionList().remove(solicitudTitulacionListNewSolicitudTitulacion);
                        oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion = em.merge(oldIdTipotitulacionOfSolicitudTitulacionListNewSolicitudTitulacion);
                    }
                }
            }
            for (DocumentoTipotitulacion documentoTipotitulacionListNewDocumentoTipotitulacion : documentoTipotitulacionListNew) {
                if (!documentoTipotitulacionListOld.contains(documentoTipotitulacionListNewDocumentoTipotitulacion)) {
                    TipoTitulacion oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion = documentoTipotitulacionListNewDocumentoTipotitulacion.getTipoTitulacion();
                    documentoTipotitulacionListNewDocumentoTipotitulacion.setTipoTitulacion(tipoTitulacion);
                    documentoTipotitulacionListNewDocumentoTipotitulacion = em.merge(documentoTipotitulacionListNewDocumentoTipotitulacion);
                    if (oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion != null && !oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion.equals(tipoTitulacion)) {
                        oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion.getDocumentoTipotitulacionList().remove(documentoTipotitulacionListNewDocumentoTipotitulacion);
                        oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion = em.merge(oldTipoTitulacionOfDocumentoTipotitulacionListNewDocumentoTipotitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoTitulacion.getIdTipotitulacion();
                if (findTipoTitulacion(id) == null) {
                    throw new NonexistentEntityException("The tipoTitulacion with id " + id + " no longer exists.");
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
            TipoTitulacion tipoTitulacion;
            try {
                tipoTitulacion = em.getReference(TipoTitulacion.class, id);
                tipoTitulacion.getIdTipotitulacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTitulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AsignacionTipotitulacionplan> asignacionTipotitulacionplanListOrphanCheck = tipoTitulacion.getAsignacionTipotitulacionplanList();
            for (AsignacionTipotitulacionplan asignacionTipotitulacionplanListOrphanCheckAsignacionTipotitulacionplan : asignacionTipotitulacionplanListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoTitulacion (" + tipoTitulacion + ") cannot be destroyed since the AsignacionTipotitulacionplan " + asignacionTipotitulacionplanListOrphanCheckAsignacionTipotitulacionplan + " in its asignacionTipotitulacionplanList field has a non-nullable tipoTitulacion field.");
            }
            List<DocumentoTipotitulacion> documentoTipotitulacionListOrphanCheck = tipoTitulacion.getDocumentoTipotitulacionList();
            for (DocumentoTipotitulacion documentoTipotitulacionListOrphanCheckDocumentoTipotitulacion : documentoTipotitulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoTitulacion (" + tipoTitulacion + ") cannot be destroyed since the DocumentoTipotitulacion " + documentoTipotitulacionListOrphanCheckDocumentoTipotitulacion + " in its documentoTipotitulacionList field has a non-nullable tipoTitulacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SolicitudTitulacion> solicitudTitulacionList = tipoTitulacion.getSolicitudTitulacionList();
            for (SolicitudTitulacion solicitudTitulacionListSolicitudTitulacion : solicitudTitulacionList) {
                solicitudTitulacionListSolicitudTitulacion.setIdTipotitulacion(null);
                solicitudTitulacionListSolicitudTitulacion = em.merge(solicitudTitulacionListSolicitudTitulacion);
            }
            em.remove(tipoTitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTitulacion> findTipoTitulacionEntities() {
        return findTipoTitulacionEntities(true, -1, -1);
    }

    public List<TipoTitulacion> findTipoTitulacionEntities(int maxResults, int firstResult) {
        return findTipoTitulacionEntities(false, maxResults, firstResult);
    }

    private List<TipoTitulacion> findTipoTitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoTitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoTitulacion findTipoTitulacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoTitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
