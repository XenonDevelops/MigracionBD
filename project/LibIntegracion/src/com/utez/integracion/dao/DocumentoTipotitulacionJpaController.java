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
import com.utez.integracion.entity.TipoTitulacion;
import com.utez.integracion.entity.Documento;
import com.utez.integracion.entity.DocumentoTipotitulacion;
import com.utez.integracion.entity.DocumentoTipotitulacionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class DocumentoTipotitulacionJpaController implements Serializable {

    public DocumentoTipotitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoTipotitulacion documentoTipotitulacion) throws PreexistingEntityException, Exception {
        if (documentoTipotitulacion.getDocumentoTipotitulacionPK() == null) {
            documentoTipotitulacion.setDocumentoTipotitulacionPK(new DocumentoTipotitulacionPK());
        }
        documentoTipotitulacion.getDocumentoTipotitulacionPK().setIdTipotitulacion(documentoTipotitulacion.getTipoTitulacion().getIdTipotitulacion());
        documentoTipotitulacion.getDocumentoTipotitulacionPK().setIdDocumento(documentoTipotitulacion.getDocumento().getIdDocumento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTitulacion tipoTitulacion = documentoTipotitulacion.getTipoTitulacion();
            if (tipoTitulacion != null) {
                tipoTitulacion = em.getReference(tipoTitulacion.getClass(), tipoTitulacion.getIdTipotitulacion());
                documentoTipotitulacion.setTipoTitulacion(tipoTitulacion);
            }
            Documento documento = documentoTipotitulacion.getDocumento();
            if (documento != null) {
                documento = em.getReference(documento.getClass(), documento.getIdDocumento());
                documentoTipotitulacion.setDocumento(documento);
            }
            em.persist(documentoTipotitulacion);
            if (tipoTitulacion != null) {
                tipoTitulacion.getDocumentoTipotitulacionList().add(documentoTipotitulacion);
                tipoTitulacion = em.merge(tipoTitulacion);
            }
            if (documento != null) {
                documento.getDocumentoTipotitulacionList().add(documentoTipotitulacion);
                documento = em.merge(documento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDocumentoTipotitulacion(documentoTipotitulacion.getDocumentoTipotitulacionPK()) != null) {
                throw new PreexistingEntityException("DocumentoTipotitulacion " + documentoTipotitulacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoTipotitulacion documentoTipotitulacion) throws NonexistentEntityException, Exception {
        documentoTipotitulacion.getDocumentoTipotitulacionPK().setIdTipotitulacion(documentoTipotitulacion.getTipoTitulacion().getIdTipotitulacion());
        documentoTipotitulacion.getDocumentoTipotitulacionPK().setIdDocumento(documentoTipotitulacion.getDocumento().getIdDocumento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoTipotitulacion persistentDocumentoTipotitulacion = em.find(DocumentoTipotitulacion.class, documentoTipotitulacion.getDocumentoTipotitulacionPK());
            TipoTitulacion tipoTitulacionOld = persistentDocumentoTipotitulacion.getTipoTitulacion();
            TipoTitulacion tipoTitulacionNew = documentoTipotitulacion.getTipoTitulacion();
            Documento documentoOld = persistentDocumentoTipotitulacion.getDocumento();
            Documento documentoNew = documentoTipotitulacion.getDocumento();
            if (tipoTitulacionNew != null) {
                tipoTitulacionNew = em.getReference(tipoTitulacionNew.getClass(), tipoTitulacionNew.getIdTipotitulacion());
                documentoTipotitulacion.setTipoTitulacion(tipoTitulacionNew);
            }
            if (documentoNew != null) {
                documentoNew = em.getReference(documentoNew.getClass(), documentoNew.getIdDocumento());
                documentoTipotitulacion.setDocumento(documentoNew);
            }
            documentoTipotitulacion = em.merge(documentoTipotitulacion);
            if (tipoTitulacionOld != null && !tipoTitulacionOld.equals(tipoTitulacionNew)) {
                tipoTitulacionOld.getDocumentoTipotitulacionList().remove(documentoTipotitulacion);
                tipoTitulacionOld = em.merge(tipoTitulacionOld);
            }
            if (tipoTitulacionNew != null && !tipoTitulacionNew.equals(tipoTitulacionOld)) {
                tipoTitulacionNew.getDocumentoTipotitulacionList().add(documentoTipotitulacion);
                tipoTitulacionNew = em.merge(tipoTitulacionNew);
            }
            if (documentoOld != null && !documentoOld.equals(documentoNew)) {
                documentoOld.getDocumentoTipotitulacionList().remove(documentoTipotitulacion);
                documentoOld = em.merge(documentoOld);
            }
            if (documentoNew != null && !documentoNew.equals(documentoOld)) {
                documentoNew.getDocumentoTipotitulacionList().add(documentoTipotitulacion);
                documentoNew = em.merge(documentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DocumentoTipotitulacionPK id = documentoTipotitulacion.getDocumentoTipotitulacionPK();
                if (findDocumentoTipotitulacion(id) == null) {
                    throw new NonexistentEntityException("The documentoTipotitulacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DocumentoTipotitulacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoTipotitulacion documentoTipotitulacion;
            try {
                documentoTipotitulacion = em.getReference(DocumentoTipotitulacion.class, id);
                documentoTipotitulacion.getDocumentoTipotitulacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoTipotitulacion with id " + id + " no longer exists.", enfe);
            }
            TipoTitulacion tipoTitulacion = documentoTipotitulacion.getTipoTitulacion();
            if (tipoTitulacion != null) {
                tipoTitulacion.getDocumentoTipotitulacionList().remove(documentoTipotitulacion);
                tipoTitulacion = em.merge(tipoTitulacion);
            }
            Documento documento = documentoTipotitulacion.getDocumento();
            if (documento != null) {
                documento.getDocumentoTipotitulacionList().remove(documentoTipotitulacion);
                documento = em.merge(documento);
            }
            em.remove(documentoTipotitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoTipotitulacion> findDocumentoTipotitulacionEntities() {
        return findDocumentoTipotitulacionEntities(true, -1, -1);
    }

    public List<DocumentoTipotitulacion> findDocumentoTipotitulacionEntities(int maxResults, int firstResult) {
        return findDocumentoTipotitulacionEntities(false, maxResults, firstResult);
    }

    private List<DocumentoTipotitulacion> findDocumentoTipotitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DocumentoTipotitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DocumentoTipotitulacion findDocumentoTipotitulacion(DocumentoTipotitulacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoTipotitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoTipotitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DocumentoTipotitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
