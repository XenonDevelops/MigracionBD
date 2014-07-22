/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Complementoalumno;
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
public class ComplementoalumnoJpaController implements Serializable {

    public ComplementoalumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Complementoalumno complementoalumno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(complementoalumno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComplementoalumno(complementoalumno.getMatricula()) != null) {
                throw new PreexistingEntityException("Complementoalumno " + complementoalumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Complementoalumno complementoalumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            complementoalumno = em.merge(complementoalumno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = complementoalumno.getMatricula();
                if (findComplementoalumno(id) == null) {
                    throw new NonexistentEntityException("The complementoalumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Complementoalumno complementoalumno;
            try {
                complementoalumno = em.getReference(Complementoalumno.class, id);
                complementoalumno.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complementoalumno with id " + id + " no longer exists.", enfe);
            }
            em.remove(complementoalumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Complementoalumno> findComplementoalumnoEntities() {
        return findComplementoalumnoEntities(true, -1, -1);
    }

    public List<Complementoalumno> findComplementoalumnoEntities(int maxResults, int firstResult) {
        return findComplementoalumnoEntities(false, maxResults, firstResult);
    }

    private List<Complementoalumno> findComplementoalumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Complementoalumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Complementoalumno findComplementoalumno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Complementoalumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplementoalumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Complementoalumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
