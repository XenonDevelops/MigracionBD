/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.servEscolares.entity.Cuadernoprogramacion;
import com.utez.servEscolares.entity.Programacionrecursada;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionrecursadaJpaController implements Serializable {

    public ProgramacionrecursadaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionrecursada programacionrecursada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion claveCuaderno = programacionrecursada.getClaveCuaderno();
            if (claveCuaderno != null) {
                claveCuaderno = em.getReference(claveCuaderno.getClass(), claveCuaderno.getClaveCuaderno());
                programacionrecursada.setClaveCuaderno(claveCuaderno);
            }
            em.persist(programacionrecursada);
            if (claveCuaderno != null) {
                claveCuaderno.getProgramacionrecursadaList().add(programacionrecursada);
                claveCuaderno = em.merge(claveCuaderno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionrecursada programacionrecursada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionrecursada persistentProgramacionrecursada = em.find(Programacionrecursada.class, programacionrecursada.getClaveProgramacionrecursada());
            Cuadernoprogramacion claveCuadernoOld = persistentProgramacionrecursada.getClaveCuaderno();
            Cuadernoprogramacion claveCuadernoNew = programacionrecursada.getClaveCuaderno();
            if (claveCuadernoNew != null) {
                claveCuadernoNew = em.getReference(claveCuadernoNew.getClass(), claveCuadernoNew.getClaveCuaderno());
                programacionrecursada.setClaveCuaderno(claveCuadernoNew);
            }
            programacionrecursada = em.merge(programacionrecursada);
            if (claveCuadernoOld != null && !claveCuadernoOld.equals(claveCuadernoNew)) {
                claveCuadernoOld.getProgramacionrecursadaList().remove(programacionrecursada);
                claveCuadernoOld = em.merge(claveCuadernoOld);
            }
            if (claveCuadernoNew != null && !claveCuadernoNew.equals(claveCuadernoOld)) {
                claveCuadernoNew.getProgramacionrecursadaList().add(programacionrecursada);
                claveCuadernoNew = em.merge(claveCuadernoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionrecursada.getClaveProgramacionrecursada();
                if (findProgramacionrecursada(id) == null) {
                    throw new NonexistentEntityException("The programacionrecursada with id " + id + " no longer exists.");
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
            Programacionrecursada programacionrecursada;
            try {
                programacionrecursada = em.getReference(Programacionrecursada.class, id);
                programacionrecursada.getClaveProgramacionrecursada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionrecursada with id " + id + " no longer exists.", enfe);
            }
            Cuadernoprogramacion claveCuaderno = programacionrecursada.getClaveCuaderno();
            if (claveCuaderno != null) {
                claveCuaderno.getProgramacionrecursadaList().remove(programacionrecursada);
                claveCuaderno = em.merge(claveCuaderno);
            }
            em.remove(programacionrecursada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionrecursada> findProgramacionrecursadaEntities() {
        return findProgramacionrecursadaEntities(true, -1, -1);
    }

    public List<Programacionrecursada> findProgramacionrecursadaEntities(int maxResults, int firstResult) {
        return findProgramacionrecursadaEntities(false, maxResults, firstResult);
    }

    private List<Programacionrecursada> findProgramacionrecursadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionrecursada as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionrecursada findProgramacionrecursada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionrecursada.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionrecursadaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionrecursada as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
