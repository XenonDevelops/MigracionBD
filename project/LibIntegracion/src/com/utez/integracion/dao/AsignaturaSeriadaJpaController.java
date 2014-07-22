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
import com.utez.integracion.entity.AsignaturaSeriada;
import com.utez.integracion.entity.AsignaturaSeriadaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaSeriadaJpaController implements Serializable {

    public AsignaturaSeriadaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignaturaSeriada asignaturaSeriada) throws PreexistingEntityException, Exception {
        if (asignaturaSeriada.getAsignaturaSeriadaPK() == null) {
            asignaturaSeriada.setAsignaturaSeriadaPK(new AsignaturaSeriadaPK());
        }
        asignaturaSeriada.getAsignaturaSeriadaPK().setIdAsignatura(asignaturaSeriada.getAsignatura().getIdAsignatura());
        asignaturaSeriada.getAsignaturaSeriadaPK().setIdAsignaturaseriada(asignaturaSeriada.getAsignatura1().getIdAsignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura asignatura = asignaturaSeriada.getAsignatura();
            if (asignatura != null) {
                asignatura = em.getReference(asignatura.getClass(), asignatura.getIdAsignatura());
                asignaturaSeriada.setAsignatura(asignatura);
            }
            Asignatura asignatura1 = asignaturaSeriada.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1 = em.getReference(asignatura1.getClass(), asignatura1.getIdAsignatura());
                asignaturaSeriada.setAsignatura1(asignatura1);
            }
            em.persist(asignaturaSeriada);
            if (asignatura != null) {
                asignatura.getAsignaturaSeriadaList().add(asignaturaSeriada);
                asignatura = em.merge(asignatura);
            }
            if (asignatura1 != null) {
                asignatura1.getAsignaturaSeriadaList().add(asignaturaSeriada);
                asignatura1 = em.merge(asignatura1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignaturaSeriada(asignaturaSeriada.getAsignaturaSeriadaPK()) != null) {
                throw new PreexistingEntityException("AsignaturaSeriada " + asignaturaSeriada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignaturaSeriada asignaturaSeriada) throws NonexistentEntityException, Exception {
        asignaturaSeriada.getAsignaturaSeriadaPK().setIdAsignatura(asignaturaSeriada.getAsignatura().getIdAsignatura());
        asignaturaSeriada.getAsignaturaSeriadaPK().setIdAsignaturaseriada(asignaturaSeriada.getAsignatura1().getIdAsignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaSeriada persistentAsignaturaSeriada = em.find(AsignaturaSeriada.class, asignaturaSeriada.getAsignaturaSeriadaPK());
            Asignatura asignaturaOld = persistentAsignaturaSeriada.getAsignatura();
            Asignatura asignaturaNew = asignaturaSeriada.getAsignatura();
            Asignatura asignatura1Old = persistentAsignaturaSeriada.getAsignatura1();
            Asignatura asignatura1New = asignaturaSeriada.getAsignatura1();
            if (asignaturaNew != null) {
                asignaturaNew = em.getReference(asignaturaNew.getClass(), asignaturaNew.getIdAsignatura());
                asignaturaSeriada.setAsignatura(asignaturaNew);
            }
            if (asignatura1New != null) {
                asignatura1New = em.getReference(asignatura1New.getClass(), asignatura1New.getIdAsignatura());
                asignaturaSeriada.setAsignatura1(asignatura1New);
            }
            asignaturaSeriada = em.merge(asignaturaSeriada);
            if (asignaturaOld != null && !asignaturaOld.equals(asignaturaNew)) {
                asignaturaOld.getAsignaturaSeriadaList().remove(asignaturaSeriada);
                asignaturaOld = em.merge(asignaturaOld);
            }
            if (asignaturaNew != null && !asignaturaNew.equals(asignaturaOld)) {
                asignaturaNew.getAsignaturaSeriadaList().add(asignaturaSeriada);
                asignaturaNew = em.merge(asignaturaNew);
            }
            if (asignatura1Old != null && !asignatura1Old.equals(asignatura1New)) {
                asignatura1Old.getAsignaturaSeriadaList().remove(asignaturaSeriada);
                asignatura1Old = em.merge(asignatura1Old);
            }
            if (asignatura1New != null && !asignatura1New.equals(asignatura1Old)) {
                asignatura1New.getAsignaturaSeriadaList().add(asignaturaSeriada);
                asignatura1New = em.merge(asignatura1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsignaturaSeriadaPK id = asignaturaSeriada.getAsignaturaSeriadaPK();
                if (findAsignaturaSeriada(id) == null) {
                    throw new NonexistentEntityException("The asignaturaSeriada with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsignaturaSeriadaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignaturaSeriada asignaturaSeriada;
            try {
                asignaturaSeriada = em.getReference(AsignaturaSeriada.class, id);
                asignaturaSeriada.getAsignaturaSeriadaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturaSeriada with id " + id + " no longer exists.", enfe);
            }
            Asignatura asignatura = asignaturaSeriada.getAsignatura();
            if (asignatura != null) {
                asignatura.getAsignaturaSeriadaList().remove(asignaturaSeriada);
                asignatura = em.merge(asignatura);
            }
            Asignatura asignatura1 = asignaturaSeriada.getAsignatura1();
            if (asignatura1 != null) {
                asignatura1.getAsignaturaSeriadaList().remove(asignaturaSeriada);
                asignatura1 = em.merge(asignatura1);
            }
            em.remove(asignaturaSeriada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignaturaSeriada> findAsignaturaSeriadaEntities() {
        return findAsignaturaSeriadaEntities(true, -1, -1);
    }

    public List<AsignaturaSeriada> findAsignaturaSeriadaEntities(int maxResults, int firstResult) {
        return findAsignaturaSeriadaEntities(false, maxResults, firstResult);
    }

    private List<AsignaturaSeriada> findAsignaturaSeriadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignaturaSeriada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignaturaSeriada findAsignaturaSeriada(AsignaturaSeriadaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignaturaSeriada.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaSeriadaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignaturaSeriada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
