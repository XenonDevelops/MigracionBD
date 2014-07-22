/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Registroparticipante;
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
public class RegistroparticipanteJpaController implements Serializable {

    public RegistroparticipanteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroparticipante registroparticipante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroparticipante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroparticipante registroparticipante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroparticipante = em.merge(registroparticipante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroparticipante.getNumControl();
                if (findRegistroparticipante(id) == null) {
                    throw new NonexistentEntityException("The registroparticipante with id " + id + " no longer exists.");
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
            Registroparticipante registroparticipante;
            try {
                registroparticipante = em.getReference(Registroparticipante.class, id);
                registroparticipante.getNumControl();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroparticipante with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroparticipante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroparticipante> findRegistroparticipanteEntities() {
        return findRegistroparticipanteEntities(true, -1, -1);
    }

    public List<Registroparticipante> findRegistroparticipanteEntities(int maxResults, int firstResult) {
        return findRegistroparticipanteEntities(false, maxResults, firstResult);
    }

    private List<Registroparticipante> findRegistroparticipanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Registroparticipante as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Registroparticipante findRegistroparticipante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroparticipante.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroparticipanteCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Registroparticipante as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
