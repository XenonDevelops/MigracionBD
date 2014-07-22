/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Examen;
import com.utez.evaluacion.entity.Programacionexamen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionexamenJpaController implements Serializable {

    public ProgramacionexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionexamen programacionexamen) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examen claveExamen = programacionexamen.getClaveExamen();
            if (claveExamen != null) {
                claveExamen = em.getReference(claveExamen.getClass(), claveExamen.getClaveExamen());
                programacionexamen.setClaveExamen(claveExamen);
            }
            em.persist(programacionexamen);
            if (claveExamen != null) {
                claveExamen.getProgramacionexamenList().add(programacionexamen);
                claveExamen = em.merge(claveExamen);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionexamen programacionexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionexamen persistentProgramacionexamen = em.find(Programacionexamen.class, programacionexamen.getClaveProExa());
            Examen claveExamenOld = persistentProgramacionexamen.getClaveExamen();
            Examen claveExamenNew = programacionexamen.getClaveExamen();
            if (claveExamenNew != null) {
                claveExamenNew = em.getReference(claveExamenNew.getClass(), claveExamenNew.getClaveExamen());
                programacionexamen.setClaveExamen(claveExamenNew);
            }
            programacionexamen = em.merge(programacionexamen);
            if (claveExamenOld != null && !claveExamenOld.equals(claveExamenNew)) {
                claveExamenOld.getProgramacionexamenList().remove(programacionexamen);
                claveExamenOld = em.merge(claveExamenOld);
            }
            if (claveExamenNew != null && !claveExamenNew.equals(claveExamenOld)) {
                claveExamenNew.getProgramacionexamenList().add(programacionexamen);
                claveExamenNew = em.merge(claveExamenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionexamen.getClaveProExa();
                if (findProgramacionexamen(id) == null) {
                    throw new NonexistentEntityException("The programacionexamen with id " + id + " no longer exists.");
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
            Programacionexamen programacionexamen;
            try {
                programacionexamen = em.getReference(Programacionexamen.class, id);
                programacionexamen.getClaveProExa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionexamen with id " + id + " no longer exists.", enfe);
            }
            Examen claveExamen = programacionexamen.getClaveExamen();
            if (claveExamen != null) {
                claveExamen.getProgramacionexamenList().remove(programacionexamen);
                claveExamen = em.merge(claveExamen);
            }
            em.remove(programacionexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionexamen> findProgramacionexamenEntities() {
        return findProgramacionexamenEntities(true, -1, -1);
    }

    public List<Programacionexamen> findProgramacionexamenEntities(int maxResults, int firstResult) {
        return findProgramacionexamenEntities(false, maxResults, firstResult);
    }

    private List<Programacionexamen> findProgramacionexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionexamen findProgramacionexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
