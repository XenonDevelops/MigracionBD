/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Archivomuerto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ArchivomuertoJpaController implements Serializable {

    public ArchivomuertoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Archivomuerto archivomuerto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(archivomuerto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Archivomuerto archivomuerto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            archivomuerto = em.merge(archivomuerto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = archivomuerto.getClaveBitacora();
                if (findArchivomuerto(id) == null) {
                    throw new NonexistentEntityException("The archivomuerto with id " + id + " no longer exists.");
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
            Archivomuerto archivomuerto;
            try {
                archivomuerto = em.getReference(Archivomuerto.class, id);
                archivomuerto.getClaveBitacora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The archivomuerto with id " + id + " no longer exists.", enfe);
            }
            em.remove(archivomuerto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Archivomuerto> findArchivomuertoEntities() {
        return findArchivomuertoEntities(true, -1, -1);
    }

    public List<Archivomuerto> findArchivomuertoEntities(int maxResults, int firstResult) {
        return findArchivomuertoEntities(false, maxResults, firstResult);
    }

    private List<Archivomuerto> findArchivomuertoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Archivomuerto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Archivomuerto findArchivomuerto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Archivomuerto.class, id);
        } finally {
            em.close();
        }
    }

    public int getArchivomuertoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Archivomuerto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
