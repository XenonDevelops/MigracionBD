/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.IllegalOrphanException;
import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.servEscolares.entity.Asignaciondocumentostitulacion;
import com.utez.servEscolares.entity.Documentostitulacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class DocumentostitulacionJpaController implements Serializable {

    public DocumentostitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documentostitulacion documentostitulacion) {
        if (documentostitulacion.getAsignaciondocumentostitulacionList() == null) {
            documentostitulacion.setAsignaciondocumentostitulacionList(new ArrayList<Asignaciondocumentostitulacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignaciondocumentostitulacion> attachedAsignaciondocumentostitulacionList = new ArrayList<Asignaciondocumentostitulacion>();
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListAsignaciondocumentostitulacionToAttach : documentostitulacion.getAsignaciondocumentostitulacionList()) {
                asignaciondocumentostitulacionListAsignaciondocumentostitulacionToAttach = em.getReference(asignaciondocumentostitulacionListAsignaciondocumentostitulacionToAttach.getClass(), asignaciondocumentostitulacionListAsignaciondocumentostitulacionToAttach.getClaveAsignacion());
                attachedAsignaciondocumentostitulacionList.add(asignaciondocumentostitulacionListAsignaciondocumentostitulacionToAttach);
            }
            documentostitulacion.setAsignaciondocumentostitulacionList(attachedAsignaciondocumentostitulacionList);
            em.persist(documentostitulacion);
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListAsignaciondocumentostitulacion : documentostitulacion.getAsignaciondocumentostitulacionList()) {
                Documentostitulacion oldClaveDocumentoOfAsignaciondocumentostitulacionListAsignaciondocumentostitulacion = asignaciondocumentostitulacionListAsignaciondocumentostitulacion.getClaveDocumento();
                asignaciondocumentostitulacionListAsignaciondocumentostitulacion.setClaveDocumento(documentostitulacion);
                asignaciondocumentostitulacionListAsignaciondocumentostitulacion = em.merge(asignaciondocumentostitulacionListAsignaciondocumentostitulacion);
                if (oldClaveDocumentoOfAsignaciondocumentostitulacionListAsignaciondocumentostitulacion != null) {
                    oldClaveDocumentoOfAsignaciondocumentostitulacionListAsignaciondocumentostitulacion.getAsignaciondocumentostitulacionList().remove(asignaciondocumentostitulacionListAsignaciondocumentostitulacion);
                    oldClaveDocumentoOfAsignaciondocumentostitulacionListAsignaciondocumentostitulacion = em.merge(oldClaveDocumentoOfAsignaciondocumentostitulacionListAsignaciondocumentostitulacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documentostitulacion documentostitulacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentostitulacion persistentDocumentostitulacion = em.find(Documentostitulacion.class, documentostitulacion.getClaveDocumento());
            List<Asignaciondocumentostitulacion> asignaciondocumentostitulacionListOld = persistentDocumentostitulacion.getAsignaciondocumentostitulacionList();
            List<Asignaciondocumentostitulacion> asignaciondocumentostitulacionListNew = documentostitulacion.getAsignaciondocumentostitulacionList();
            List<String> illegalOrphanMessages = null;
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListOldAsignaciondocumentostitulacion : asignaciondocumentostitulacionListOld) {
                if (!asignaciondocumentostitulacionListNew.contains(asignaciondocumentostitulacionListOldAsignaciondocumentostitulacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignaciondocumentostitulacion " + asignaciondocumentostitulacionListOldAsignaciondocumentostitulacion + " since its claveDocumento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Asignaciondocumentostitulacion> attachedAsignaciondocumentostitulacionListNew = new ArrayList<Asignaciondocumentostitulacion>();
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListNewAsignaciondocumentostitulacionToAttach : asignaciondocumentostitulacionListNew) {
                asignaciondocumentostitulacionListNewAsignaciondocumentostitulacionToAttach = em.getReference(asignaciondocumentostitulacionListNewAsignaciondocumentostitulacionToAttach.getClass(), asignaciondocumentostitulacionListNewAsignaciondocumentostitulacionToAttach.getClaveAsignacion());
                attachedAsignaciondocumentostitulacionListNew.add(asignaciondocumentostitulacionListNewAsignaciondocumentostitulacionToAttach);
            }
            asignaciondocumentostitulacionListNew = attachedAsignaciondocumentostitulacionListNew;
            documentostitulacion.setAsignaciondocumentostitulacionList(asignaciondocumentostitulacionListNew);
            documentostitulacion = em.merge(documentostitulacion);
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion : asignaciondocumentostitulacionListNew) {
                if (!asignaciondocumentostitulacionListOld.contains(asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion)) {
                    Documentostitulacion oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion = asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion.getClaveDocumento();
                    asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion.setClaveDocumento(documentostitulacion);
                    asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion = em.merge(asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion);
                    if (oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion != null && !oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion.equals(documentostitulacion)) {
                        oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion.getAsignaciondocumentostitulacionList().remove(asignaciondocumentostitulacionListNewAsignaciondocumentostitulacion);
                        oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion = em.merge(oldClaveDocumentoOfAsignaciondocumentostitulacionListNewAsignaciondocumentostitulacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documentostitulacion.getClaveDocumento();
                if (findDocumentostitulacion(id) == null) {
                    throw new NonexistentEntityException("The documentostitulacion with id " + id + " no longer exists.");
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
            Documentostitulacion documentostitulacion;
            try {
                documentostitulacion = em.getReference(Documentostitulacion.class, id);
                documentostitulacion.getClaveDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentostitulacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Asignaciondocumentostitulacion> asignaciondocumentostitulacionListOrphanCheck = documentostitulacion.getAsignaciondocumentostitulacionList();
            for (Asignaciondocumentostitulacion asignaciondocumentostitulacionListOrphanCheckAsignaciondocumentostitulacion : asignaciondocumentostitulacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentostitulacion (" + documentostitulacion + ") cannot be destroyed since the Asignaciondocumentostitulacion " + asignaciondocumentostitulacionListOrphanCheckAsignaciondocumentostitulacion + " in its asignaciondocumentostitulacionList field has a non-nullable claveDocumento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(documentostitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Documentostitulacion> findDocumentostitulacionEntities() {
        return findDocumentostitulacionEntities(true, -1, -1);
    }

    public List<Documentostitulacion> findDocumentostitulacionEntities(int maxResults, int firstResult) {
        return findDocumentostitulacionEntities(false, maxResults, firstResult);
    }

    private List<Documentostitulacion> findDocumentostitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Documentostitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Documentostitulacion findDocumentostitulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documentostitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentostitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Documentostitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
