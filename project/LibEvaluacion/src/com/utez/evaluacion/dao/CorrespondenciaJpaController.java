/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Correspondencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Materiasdeplan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CorrespondenciaJpaController implements Serializable {

    public CorrespondenciaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Correspondencia correspondencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiasdeplan claveMateria = correspondencia.getClaveMateria();
            if (claveMateria != null) {
                claveMateria = em.getReference(claveMateria.getClass(), claveMateria.getClaveMateria());
                correspondencia.setClaveMateria(claveMateria);
            }
            em.persist(correspondencia);
            if (claveMateria != null) {
                claveMateria.getCorrespondenciaList().add(correspondencia);
                claveMateria = em.merge(claveMateria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Correspondencia correspondencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Correspondencia persistentCorrespondencia = em.find(Correspondencia.class, correspondencia.getClaveCorrespondencia());
            Materiasdeplan claveMateriaOld = persistentCorrespondencia.getClaveMateria();
            Materiasdeplan claveMateriaNew = correspondencia.getClaveMateria();
            if (claveMateriaNew != null) {
                claveMateriaNew = em.getReference(claveMateriaNew.getClass(), claveMateriaNew.getClaveMateria());
                correspondencia.setClaveMateria(claveMateriaNew);
            }
            correspondencia = em.merge(correspondencia);
            if (claveMateriaOld != null && !claveMateriaOld.equals(claveMateriaNew)) {
                claveMateriaOld.getCorrespondenciaList().remove(correspondencia);
                claveMateriaOld = em.merge(claveMateriaOld);
            }
            if (claveMateriaNew != null && !claveMateriaNew.equals(claveMateriaOld)) {
                claveMateriaNew.getCorrespondenciaList().add(correspondencia);
                claveMateriaNew = em.merge(claveMateriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = correspondencia.getClaveCorrespondencia();
                if (findCorrespondencia(id) == null) {
                    throw new NonexistentEntityException("The correspondencia with id " + id + " no longer exists.");
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
            Correspondencia correspondencia;
            try {
                correspondencia = em.getReference(Correspondencia.class, id);
                correspondencia.getClaveCorrespondencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The correspondencia with id " + id + " no longer exists.", enfe);
            }
            Materiasdeplan claveMateria = correspondencia.getClaveMateria();
            if (claveMateria != null) {
                claveMateria.getCorrespondenciaList().remove(correspondencia);
                claveMateria = em.merge(claveMateria);
            }
            em.remove(correspondencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Correspondencia> findCorrespondenciaEntities() {
        return findCorrespondenciaEntities(true, -1, -1);
    }

    public List<Correspondencia> findCorrespondenciaEntities(int maxResults, int firstResult) {
        return findCorrespondenciaEntities(false, maxResults, firstResult);
    }

    private List<Correspondencia> findCorrespondenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Correspondencia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Correspondencia findCorrespondencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Correspondencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorrespondenciaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Correspondencia as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
