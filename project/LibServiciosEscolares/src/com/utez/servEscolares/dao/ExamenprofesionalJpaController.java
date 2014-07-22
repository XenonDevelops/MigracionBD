/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Examenprofesional;
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
public class ExamenprofesionalJpaController implements Serializable {

    public ExamenprofesionalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examenprofesional examenprofesional) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(examenprofesional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examenprofesional examenprofesional) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            examenprofesional = em.merge(examenprofesional);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examenprofesional.getClaveExamenprofesional();
                if (findExamenprofesional(id) == null) {
                    throw new NonexistentEntityException("The examenprofesional with id " + id + " no longer exists.");
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
            Examenprofesional examenprofesional;
            try {
                examenprofesional = em.getReference(Examenprofesional.class, id);
                examenprofesional.getClaveExamenprofesional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examenprofesional with id " + id + " no longer exists.", enfe);
            }
            em.remove(examenprofesional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examenprofesional> findExamenprofesionalEntities() {
        return findExamenprofesionalEntities(true, -1, -1);
    }

    public List<Examenprofesional> findExamenprofesionalEntities(int maxResults, int firstResult) {
        return findExamenprofesionalEntities(false, maxResults, firstResult);
    }

    private List<Examenprofesional> findExamenprofesionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Examenprofesional as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Examenprofesional findExamenprofesional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examenprofesional.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenprofesionalCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Examenprofesional as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
