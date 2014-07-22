/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Asignaciondocumentostitulacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.servEscolares.entity.Documentostitulacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaciondocumentostitulacionJpaController implements Serializable {

    public AsignaciondocumentostitulacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignaciondocumentostitulacion asignaciondocumentostitulacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentostitulacion claveDocumento = asignaciondocumentostitulacion.getClaveDocumento();
            if (claveDocumento != null) {
                claveDocumento = em.getReference(claveDocumento.getClass(), claveDocumento.getClaveDocumento());
                asignaciondocumentostitulacion.setClaveDocumento(claveDocumento);
            }
            em.persist(asignaciondocumentostitulacion);
            if (claveDocumento != null) {
                claveDocumento.getAsignaciondocumentostitulacionList().add(asignaciondocumentostitulacion);
                claveDocumento = em.merge(claveDocumento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignaciondocumentostitulacion asignaciondocumentostitulacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignaciondocumentostitulacion persistentAsignaciondocumentostitulacion = em.find(Asignaciondocumentostitulacion.class, asignaciondocumentostitulacion.getClaveAsignacion());
            Documentostitulacion claveDocumentoOld = persistentAsignaciondocumentostitulacion.getClaveDocumento();
            Documentostitulacion claveDocumentoNew = asignaciondocumentostitulacion.getClaveDocumento();
            if (claveDocumentoNew != null) {
                claveDocumentoNew = em.getReference(claveDocumentoNew.getClass(), claveDocumentoNew.getClaveDocumento());
                asignaciondocumentostitulacion.setClaveDocumento(claveDocumentoNew);
            }
            asignaciondocumentostitulacion = em.merge(asignaciondocumentostitulacion);
            if (claveDocumentoOld != null && !claveDocumentoOld.equals(claveDocumentoNew)) {
                claveDocumentoOld.getAsignaciondocumentostitulacionList().remove(asignaciondocumentostitulacion);
                claveDocumentoOld = em.merge(claveDocumentoOld);
            }
            if (claveDocumentoNew != null && !claveDocumentoNew.equals(claveDocumentoOld)) {
                claveDocumentoNew.getAsignaciondocumentostitulacionList().add(asignaciondocumentostitulacion);
                claveDocumentoNew = em.merge(claveDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignaciondocumentostitulacion.getClaveAsignacion();
                if (findAsignaciondocumentostitulacion(id) == null) {
                    throw new NonexistentEntityException("The asignaciondocumentostitulacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignaciondocumentostitulacion asignaciondocumentostitulacion;
            try {
                asignaciondocumentostitulacion = em.getReference(Asignaciondocumentostitulacion.class, id);
                asignaciondocumentostitulacion.getClaveAsignacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaciondocumentostitulacion with id " + id + " no longer exists.", enfe);
            }
            Documentostitulacion claveDocumento = asignaciondocumentostitulacion.getClaveDocumento();
            if (claveDocumento != null) {
                claveDocumento.getAsignaciondocumentostitulacionList().remove(asignaciondocumentostitulacion);
                claveDocumento = em.merge(claveDocumento);
            }
            em.remove(asignaciondocumentostitulacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignaciondocumentostitulacion> findAsignaciondocumentostitulacionEntities() {
        return findAsignaciondocumentostitulacionEntities(true, -1, -1);
    }

    public List<Asignaciondocumentostitulacion> findAsignaciondocumentostitulacionEntities(int maxResults, int firstResult) {
        return findAsignaciondocumentostitulacionEntities(false, maxResults, firstResult);
    }

    private List<Asignaciondocumentostitulacion> findAsignaciondocumentostitulacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignaciondocumentostitulacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignaciondocumentostitulacion findAsignaciondocumentostitulacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignaciondocumentostitulacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaciondocumentostitulacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignaciondocumentostitulacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
