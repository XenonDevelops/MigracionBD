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
import com.utez.integracion.entity.TipoDocumento;
import com.utez.integracion.entity.DocumentoTipotitulacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.ControlDocumento;
import com.utez.integracion.entity.AsignacionRecursohumanodocumento;
import com.utez.integracion.entity.Documento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class DocumentoJpaController implements Serializable {

    public DocumentoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) {
        if (documento.getDocumentoTipotitulacionList() == null) {
            documento.setDocumentoTipotitulacionList(new ArrayList<DocumentoTipotitulacion>());
        }
        if (documento.getControlDocumentoList() == null) {
            documento.setControlDocumentoList(new ArrayList<ControlDocumento>());
        }
        if (documento.getAsignacionRecursohumanodocumentoList() == null) {
            documento.setAsignacionRecursohumanodocumentoList(new ArrayList<AsignacionRecursohumanodocumento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento idTipodocumento = documento.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento = em.getReference(idTipodocumento.getClass(), idTipodocumento.getIdTipodocumento());
                documento.setIdTipodocumento(idTipodocumento);
            }
            List<DocumentoTipotitulacion> attachedDocumentoTipotitulacionList = new ArrayList<DocumentoTipotitulacion>();
            for (DocumentoTipotitulacion documentoTipotitulacionListDocumentoTipotitulacionToAttach : documento.getDocumentoTipotitulacionList()) {
                documentoTipotitulacionListDocumentoTipotitulacionToAttach = em.getReference(documentoTipotitulacionListDocumentoTipotitulacionToAttach.getClass(), documentoTipotitulacionListDocumentoTipotitulacionToAttach.getDocumentoTipotitulacionPK());
                attachedDocumentoTipotitulacionList.add(documentoTipotitulacionListDocumentoTipotitulacionToAttach);
            }
            documento.setDocumentoTipotitulacionList(attachedDocumentoTipotitulacionList);
            List<ControlDocumento> attachedControlDocumentoList = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListControlDocumentoToAttach : documento.getControlDocumentoList()) {
                controlDocumentoListControlDocumentoToAttach = em.getReference(controlDocumentoListControlDocumentoToAttach.getClass(), controlDocumentoListControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoList.add(controlDocumentoListControlDocumentoToAttach);
            }
            documento.setControlDocumentoList(attachedControlDocumentoList);
            List<AsignacionRecursohumanodocumento> attachedAsignacionRecursohumanodocumentoList = new ArrayList<AsignacionRecursohumanodocumento>();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach : documento.getAsignacionRecursohumanodocumentoList()) {
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach = em.getReference(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach.getClass(), asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach.getIdAsignacionrecursohumanodocumento());
                attachedAsignacionRecursohumanodocumentoList.add(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumentoToAttach);
            }
            documento.setAsignacionRecursohumanodocumentoList(attachedAsignacionRecursohumanodocumentoList);
            em.persist(documento);
            if (idTipodocumento != null) {
                idTipodocumento.getDocumentoList().add(documento);
                idTipodocumento = em.merge(idTipodocumento);
            }
            for (DocumentoTipotitulacion documentoTipotitulacionListDocumentoTipotitulacion : documento.getDocumentoTipotitulacionList()) {
                Documento oldDocumentoOfDocumentoTipotitulacionListDocumentoTipotitulacion = documentoTipotitulacionListDocumentoTipotitulacion.getDocumento();
                documentoTipotitulacionListDocumentoTipotitulacion.setDocumento(documento);
                documentoTipotitulacionListDocumentoTipotitulacion = em.merge(documentoTipotitulacionListDocumentoTipotitulacion);
                if (oldDocumentoOfDocumentoTipotitulacionListDocumentoTipotitulacion != null) {
                    oldDocumentoOfDocumentoTipotitulacionListDocumentoTipotitulacion.getDocumentoTipotitulacionList().remove(documentoTipotitulacionListDocumentoTipotitulacion);
                    oldDocumentoOfDocumentoTipotitulacionListDocumentoTipotitulacion = em.merge(oldDocumentoOfDocumentoTipotitulacionListDocumentoTipotitulacion);
                }
            }
            for (ControlDocumento controlDocumentoListControlDocumento : documento.getControlDocumentoList()) {
                Documento oldIdDocumentoOfControlDocumentoListControlDocumento = controlDocumentoListControlDocumento.getIdDocumento();
                controlDocumentoListControlDocumento.setIdDocumento(documento);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
                if (oldIdDocumentoOfControlDocumentoListControlDocumento != null) {
                    oldIdDocumentoOfControlDocumentoListControlDocumento.getControlDocumentoList().remove(controlDocumentoListControlDocumento);
                    oldIdDocumentoOfControlDocumentoListControlDocumento = em.merge(oldIdDocumentoOfControlDocumentoListControlDocumento);
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento : documento.getAsignacionRecursohumanodocumentoList()) {
                Documento oldIdDocumentoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.getIdDocumento();
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.setIdDocumento(documento);
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                if (oldIdDocumentoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento != null) {
                    oldIdDocumentoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                    oldIdDocumentoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(oldIdDocumentoOfAsignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documento documento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getIdDocumento());
            TipoDocumento idTipodocumentoOld = persistentDocumento.getIdTipodocumento();
            TipoDocumento idTipodocumentoNew = documento.getIdTipodocumento();
            List<DocumentoTipotitulacion> documentoTipotitulacionListOld = persistentDocumento.getDocumentoTipotitulacionList();
            List<DocumentoTipotitulacion> documentoTipotitulacionListNew = documento.getDocumentoTipotitulacionList();
            List<ControlDocumento> controlDocumentoListOld = persistentDocumento.getControlDocumentoList();
            List<ControlDocumento> controlDocumentoListNew = documento.getControlDocumentoList();
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoListOld = persistentDocumento.getAsignacionRecursohumanodocumentoList();
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoListNew = documento.getAsignacionRecursohumanodocumentoList();
            List<String> illegalOrphanMessages = null;
            for (DocumentoTipotitulacion documentoTipotitulacionListOldDocumentoTipotitulacion : documentoTipotitulacionListOld) {
                if (!documentoTipotitulacionListNew.contains(documentoTipotitulacionListOldDocumentoTipotitulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DocumentoTipotitulacion " + documentoTipotitulacionListOldDocumentoTipotitulacion + " since its documento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipodocumentoNew != null) {
                idTipodocumentoNew = em.getReference(idTipodocumentoNew.getClass(), idTipodocumentoNew.getIdTipodocumento());
                documento.setIdTipodocumento(idTipodocumentoNew);
            }
            List<DocumentoTipotitulacion> attachedDocumentoTipotitulacionListNew = new ArrayList<DocumentoTipotitulacion>();
            for (DocumentoTipotitulacion documentoTipotitulacionListNewDocumentoTipotitulacionToAttach : documentoTipotitulacionListNew) {
                documentoTipotitulacionListNewDocumentoTipotitulacionToAttach = em.getReference(documentoTipotitulacionListNewDocumentoTipotitulacionToAttach.getClass(), documentoTipotitulacionListNewDocumentoTipotitulacionToAttach.getDocumentoTipotitulacionPK());
                attachedDocumentoTipotitulacionListNew.add(documentoTipotitulacionListNewDocumentoTipotitulacionToAttach);
            }
            documentoTipotitulacionListNew = attachedDocumentoTipotitulacionListNew;
            documento.setDocumentoTipotitulacionList(documentoTipotitulacionListNew);
            List<ControlDocumento> attachedControlDocumentoListNew = new ArrayList<ControlDocumento>();
            for (ControlDocumento controlDocumentoListNewControlDocumentoToAttach : controlDocumentoListNew) {
                controlDocumentoListNewControlDocumentoToAttach = em.getReference(controlDocumentoListNewControlDocumentoToAttach.getClass(), controlDocumentoListNewControlDocumentoToAttach.getIdControldocumento());
                attachedControlDocumentoListNew.add(controlDocumentoListNewControlDocumentoToAttach);
            }
            controlDocumentoListNew = attachedControlDocumentoListNew;
            documento.setControlDocumentoList(controlDocumentoListNew);
            List<AsignacionRecursohumanodocumento> attachedAsignacionRecursohumanodocumentoListNew = new ArrayList<AsignacionRecursohumanodocumento>();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach : asignacionRecursohumanodocumentoListNew) {
                asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach = em.getReference(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach.getClass(), asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach.getIdAsignacionrecursohumanodocumento());
                attachedAsignacionRecursohumanodocumentoListNew.add(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumentoToAttach);
            }
            asignacionRecursohumanodocumentoListNew = attachedAsignacionRecursohumanodocumentoListNew;
            documento.setAsignacionRecursohumanodocumentoList(asignacionRecursohumanodocumentoListNew);
            documento = em.merge(documento);
            if (idTipodocumentoOld != null && !idTipodocumentoOld.equals(idTipodocumentoNew)) {
                idTipodocumentoOld.getDocumentoList().remove(documento);
                idTipodocumentoOld = em.merge(idTipodocumentoOld);
            }
            if (idTipodocumentoNew != null && !idTipodocumentoNew.equals(idTipodocumentoOld)) {
                idTipodocumentoNew.getDocumentoList().add(documento);
                idTipodocumentoNew = em.merge(idTipodocumentoNew);
            }
            for (DocumentoTipotitulacion documentoTipotitulacionListNewDocumentoTipotitulacion : documentoTipotitulacionListNew) {
                if (!documentoTipotitulacionListOld.contains(documentoTipotitulacionListNewDocumentoTipotitulacion)) {
                    Documento oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion = documentoTipotitulacionListNewDocumentoTipotitulacion.getDocumento();
                    documentoTipotitulacionListNewDocumentoTipotitulacion.setDocumento(documento);
                    documentoTipotitulacionListNewDocumentoTipotitulacion = em.merge(documentoTipotitulacionListNewDocumentoTipotitulacion);
                    if (oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion != null && !oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion.equals(documento)) {
                        oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion.getDocumentoTipotitulacionList().remove(documentoTipotitulacionListNewDocumentoTipotitulacion);
                        oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion = em.merge(oldDocumentoOfDocumentoTipotitulacionListNewDocumentoTipotitulacion);
                    }
                }
            }
            for (ControlDocumento controlDocumentoListOldControlDocumento : controlDocumentoListOld) {
                if (!controlDocumentoListNew.contains(controlDocumentoListOldControlDocumento)) {
                    controlDocumentoListOldControlDocumento.setIdDocumento(null);
                    controlDocumentoListOldControlDocumento = em.merge(controlDocumentoListOldControlDocumento);
                }
            }
            for (ControlDocumento controlDocumentoListNewControlDocumento : controlDocumentoListNew) {
                if (!controlDocumentoListOld.contains(controlDocumentoListNewControlDocumento)) {
                    Documento oldIdDocumentoOfControlDocumentoListNewControlDocumento = controlDocumentoListNewControlDocumento.getIdDocumento();
                    controlDocumentoListNewControlDocumento.setIdDocumento(documento);
                    controlDocumentoListNewControlDocumento = em.merge(controlDocumentoListNewControlDocumento);
                    if (oldIdDocumentoOfControlDocumentoListNewControlDocumento != null && !oldIdDocumentoOfControlDocumentoListNewControlDocumento.equals(documento)) {
                        oldIdDocumentoOfControlDocumentoListNewControlDocumento.getControlDocumentoList().remove(controlDocumentoListNewControlDocumento);
                        oldIdDocumentoOfControlDocumentoListNewControlDocumento = em.merge(oldIdDocumentoOfControlDocumentoListNewControlDocumento);
                    }
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoListOld) {
                if (!asignacionRecursohumanodocumentoListNew.contains(asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento)) {
                    asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento.setIdDocumento(null);
                    asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListOldAsignacionRecursohumanodocumento);
                }
            }
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoListNew) {
                if (!asignacionRecursohumanodocumentoListOld.contains(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento)) {
                    Documento oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.getIdDocumento();
                    asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.setIdDocumento(documento);
                    asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                    if (oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento != null && !oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.equals(documento)) {
                        oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento.getAsignacionRecursohumanodocumentoList().remove(asignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                        oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento = em.merge(oldIdDocumentoOfAsignacionRecursohumanodocumentoListNewAsignacionRecursohumanodocumento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = documento.getIdDocumento();
                if (findDocumento(id) == null) {
                    throw new NonexistentEntityException("The documento with id " + id + " no longer exists.");
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
            Documento documento;
            try {
                documento = em.getReference(Documento.class, id);
                documento.getIdDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DocumentoTipotitulacion> documentoTipotitulacionListOrphanCheck = documento.getDocumentoTipotitulacionList();
            for (DocumentoTipotitulacion documentoTipotitulacionListOrphanCheckDocumentoTipotitulacion : documentoTipotitulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documento (" + documento + ") cannot be destroyed since the DocumentoTipotitulacion " + documentoTipotitulacionListOrphanCheckDocumentoTipotitulacion + " in its documentoTipotitulacionList field has a non-nullable documento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoDocumento idTipodocumento = documento.getIdTipodocumento();
            if (idTipodocumento != null) {
                idTipodocumento.getDocumentoList().remove(documento);
                idTipodocumento = em.merge(idTipodocumento);
            }
            List<ControlDocumento> controlDocumentoList = documento.getControlDocumentoList();
            for (ControlDocumento controlDocumentoListControlDocumento : controlDocumentoList) {
                controlDocumentoListControlDocumento.setIdDocumento(null);
                controlDocumentoListControlDocumento = em.merge(controlDocumentoListControlDocumento);
            }
            List<AsignacionRecursohumanodocumento> asignacionRecursohumanodocumentoList = documento.getAsignacionRecursohumanodocumentoList();
            for (AsignacionRecursohumanodocumento asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento : asignacionRecursohumanodocumentoList) {
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento.setIdDocumento(null);
                asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento = em.merge(asignacionRecursohumanodocumentoListAsignacionRecursohumanodocumento);
            }
            em.remove(documento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Documento> findDocumentoEntities() {
        return findDocumentoEntities(true, -1, -1);
    }

    public List<Documento> findDocumentoEntities(int maxResults, int firstResult) {
        return findDocumentoEntities(false, maxResults, firstResult);
    }

    private List<Documento> findDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Documento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Documento findDocumento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Documento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
