/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Registrodocumentacionlic;
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
public class RegistrodocumentacionlicJpaController implements Serializable {

    public RegistrodocumentacionlicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registrodocumentacionlic registrodocumentacionlic) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registrodocumentacionlic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistrodocumentacionlic(registrodocumentacionlic.getMatricula()) != null) {
                throw new PreexistingEntityException("Registrodocumentacionlic " + registrodocumentacionlic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registrodocumentacionlic registrodocumentacionlic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registrodocumentacionlic = em.merge(registrodocumentacionlic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = registrodocumentacionlic.getMatricula();
                if (findRegistrodocumentacionlic(id) == null) {
                    throw new NonexistentEntityException("The registrodocumentacionlic with id " + id + " no longer exists.");
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
            Registrodocumentacionlic registrodocumentacionlic;
            try {
                registrodocumentacionlic = em.getReference(Registrodocumentacionlic.class, id);
                registrodocumentacionlic.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registrodocumentacionlic with id " + id + " no longer exists.", enfe);
            }
            em.remove(registrodocumentacionlic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registrodocumentacionlic> findRegistrodocumentacionlicEntities() {
        return findRegistrodocumentacionlicEntities(true, -1, -1);
    }

    public List<Registrodocumentacionlic> findRegistrodocumentacionlicEntities(int maxResults, int firstResult) {
        return findRegistrodocumentacionlicEntities(false, maxResults, firstResult);
    }

    private List<Registrodocumentacionlic> findRegistrodocumentacionlicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Registrodocumentacionlic as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registrodocumentacionlic findRegistrodocumentacionlic(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registrodocumentacionlic.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistrodocumentacionlicCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Registrodocumentacionlic as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
