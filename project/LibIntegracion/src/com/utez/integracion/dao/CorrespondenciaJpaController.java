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
import com.utez.integracion.entity.Asignatura;
import com.utez.integracion.entity.Correspondencia;
import com.utez.integracion.entity.CorrespondenciaPK;
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

    public void create(Correspondencia correspondencia) throws PreexistingEntityException, Exception {
        if (correspondencia.getCorrespondenciaPK() == null) {
            correspondencia.setCorrespondenciaPK(new CorrespondenciaPK());
        }
        correspondencia.getCorrespondenciaPK().setIdAsignatura(correspondencia.getAsignatura1().getIdAsignatura());
        correspondencia.getCorrespondenciaPK().setIdAsignaturacorrespondencia(correspondencia.getAsignatura().getIdAsignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = correspondencia.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getIdAsignatura());
                correspondencia.setAsignatura(asignatura);
            }
            Asignatura asignatura1 = correspondencia.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1 = em.getReference(asignatura1.getClass(), asignatura1.getIdAsignatura());
                correspondencia.setAsignatura1(asignatura1);
            }
            em.persist(correspondencia);
            if (asignatura != null) {
                asignatura.getCorrespondenciaList().add(correspondencia);
                asignatura = em.merge(asignatura);
            }
            if (asignatura1 != null) {
                asignatura1.getCorrespondenciaList().add(correspondencia);
                asignatura1 = em.merge(asignatura1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCorrespondencia(correspondencia.getCorrespondenciaPK()) != null) {
                throw new PreexistingEntityException("Correspondencia " + correspondencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Correspondencia correspondencia) throws NonexistentEntityException, Exception {
        correspondencia.getCorrespondenciaPK().setIdAsignatura(correspondencia.getAsignatura1().getIdAsignatura());
        correspondencia.getCorrespondenciaPK().setIdAsignaturacorrespondencia(correspondencia.getAsignatura().getIdAsignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Correspondencia persistentCorrespondencia = em.find(Correspondencia.class, correspondencia.getCorrespondenciaPK());
            Asignatura asignaturaOld = persistentCorrespondencia.getAsignatura();
            Asignatura asignaturaNew = correspondencia.getAsignatura();
            Asignatura asignatura1Old = persistentCorrespondencia.getAsignatura1();
            Asignatura asignatura1New = correspondencia.getAsignatura1();
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getIdAsignatura());
                correspondencia.setAsignatura(asignaturaNew);
            }
            if (asignatura1New != null) {
                asignatura1New = em.getReference(asignatura1New.getClass(), asignatura1New.getIdAsignatura());
                correspondencia.setAsignatura1(asignatura1New);
            }
            correspondencia = em.merge(correspondencia);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getCorrespondenciaList().remove(correspondencia);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getCorrespondenciaList().add(correspondencia);
                asignaturaNew = em.merge(asignaturaNew);
            }
            if (asignatura1Old != null && !asignatura1Old.equals(asignatura1New)) {
                asignatura1Old.getCorrespondenciaList().remove(correspondencia);
                asignatura1Old = em.merge(asignatura1Old);
            }
            if (asignatura1New != null && !asignatura1New.equals(asignatura1Old)) {
                asignatura1New.getCorrespondenciaList().add(correspondencia);
                asignatura1New = em.merge(asignatura1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CorrespondenciaPK id = correspondencia.getCorrespondenciaPK();
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

    public void destroy(CorrespondenciaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Correspondencia correspondencia;
            try {
                correspondencia = em.getReference(Correspondencia.class, id);
                correspondencia.getCorrespondenciaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The correspondencia with id " + id + " no longer exists.", enfe);
            }
            Asignatura asignatura = correspondencia.getAsignatura();
            if (asignatura != null) {
                asignatura.getCorrespondenciaList().remove(correspondencia);
                asignatura = em.merge(asignatura);
            }
            Asignatura asignatura1 = correspondencia.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1.getCorrespondenciaList().remove(correspondencia);
                asignatura1 = em.merge(asignatura1);
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

    public Correspondencia findCorrespondencia(CorrespondenciaPK id) {
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
