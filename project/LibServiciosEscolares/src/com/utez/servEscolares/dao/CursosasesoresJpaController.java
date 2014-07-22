/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Cursosasesores;
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
public class CursosasesoresJpaController implements Serializable {

    public CursosasesoresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cursosasesores cursosasesores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cursosasesores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cursosasesores cursosasesores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cursosasesores = em.merge(cursosasesores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cursosasesores.getClaveCurso();
                if (findCursosasesores(id) == null) {
                    throw new NonexistentEntityException("The cursosasesores with id " + id + " no longer exists.");
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
            Cursosasesores cursosasesores;
            try {
                cursosasesores = em.getReference(Cursosasesores.class, id);
                cursosasesores.getClaveCurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursosasesores with id " + id + " no longer exists.", enfe);
            }
            em.remove(cursosasesores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cursosasesores> findCursosasesoresEntities() {
        return findCursosasesoresEntities(true, -1, -1);
    }

    public List<Cursosasesores> findCursosasesoresEntities(int maxResults, int firstResult) {
        return findCursosasesoresEntities(false, maxResults, firstResult);
    }

    private List<Cursosasesores> findCursosasesoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cursosasesores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cursosasesores findCursosasesores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cursosasesores.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursosasesoresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cursosasesores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
