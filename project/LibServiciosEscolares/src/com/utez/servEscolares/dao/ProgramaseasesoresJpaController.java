/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Programaseasesores;
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
public class ProgramaseasesoresJpaController implements Serializable {

    public ProgramaseasesoresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programaseasesores programaseasesores) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(programaseasesores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programaseasesores programaseasesores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            programaseasesores = em.merge(programaseasesores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programaseasesores.getClaveProgramaseasesores();
                if (findProgramaseasesores(id) == null) {
                    throw new NonexistentEntityException("The programaseasesores with id " + id + " no longer exists.");
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
            Programaseasesores programaseasesores;
            try {
                programaseasesores = em.getReference(Programaseasesores.class, id);
                programaseasesores.getClaveProgramaseasesores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programaseasesores with id " + id + " no longer exists.", enfe);
            }
            em.remove(programaseasesores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programaseasesores> findProgramaseasesoresEntities() {
        return findProgramaseasesoresEntities(true, -1, -1);
    }

    public List<Programaseasesores> findProgramaseasesoresEntities(int maxResults, int firstResult) {
        return findProgramaseasesoresEntities(false, maxResults, firstResult);
    }

    private List<Programaseasesores> findProgramaseasesoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programaseasesores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programaseasesores findProgramaseasesores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programaseasesores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaseasesoresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programaseasesores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
