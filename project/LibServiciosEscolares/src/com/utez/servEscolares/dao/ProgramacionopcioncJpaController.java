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
import com.utez.servEscolares.entity.Programacionopcionc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionopcioncJpaController implements Serializable {

    public ProgramacionopcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionopcionc programacionopcionc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion claveCuaderno = programacionopcionc.getClaveCuaderno();
            if (claveCuaderno != null) {
                claveCuaderno = em.getReference(claveCuaderno.getClass(), claveCuaderno.getClaveCuaderno());
                programacionopcionc.setClaveCuaderno(claveCuaderno);
            }
            em.persist(programacionopcionc);
            if (claveCuaderno != null) {
                claveCuaderno.getProgramacionopcioncList().add(programacionopcionc);
                claveCuaderno = em.merge(claveCuaderno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionopcionc programacionopcionc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionopcionc persistentProgramacionopcionc = em.find(Programacionopcionc.class, programacionopcionc.getClaveProgramacionopcionc());
            Cuadernoprogramacion claveCuadernoOld = persistentProgramacionopcionc.getClaveCuaderno();
            Cuadernoprogramacion claveCuadernoNew = programacionopcionc.getClaveCuaderno();
            if (claveCuadernoNew != null) {
                claveCuadernoNew = em.getReference(claveCuadernoNew.getClass(), claveCuadernoNew.getClaveCuaderno());
                programacionopcionc.setClaveCuaderno(claveCuadernoNew);
            }
            programacionopcionc = em.merge(programacionopcionc);
            if (claveCuadernoOld != null && !claveCuadernoOld.equals(claveCuadernoNew)) {
                claveCuadernoOld.getProgramacionopcioncList().remove(programacionopcionc);
                claveCuadernoOld = em.merge(claveCuadernoOld);
            }
            if (claveCuadernoNew != null && !claveCuadernoNew.equals(claveCuadernoOld)) {
                claveCuadernoNew.getProgramacionopcioncList().add(programacionopcionc);
                claveCuadernoNew = em.merge(claveCuadernoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionopcionc.getClaveProgramacionopcionc();
                if (findProgramacionopcionc(id) == null) {
                    throw new NonexistentEntityException("The programacionopcionc with id " + id + " no longer exists.");
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
            Programacionopcionc programacionopcionc;
            try {
                programacionopcionc = em.getReference(Programacionopcionc.class, id);
                programacionopcionc.getClaveProgramacionopcionc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionopcionc with id " + id + " no longer exists.", enfe);
            }
            Cuadernoprogramacion claveCuaderno = programacionopcionc.getClaveCuaderno();
            if (claveCuaderno != null) {
                claveCuaderno.getProgramacionopcioncList().remove(programacionopcionc);
                claveCuaderno = em.merge(claveCuaderno);
            }
            em.remove(programacionopcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionopcionc> findProgramacionopcioncEntities() {
        return findProgramacionopcioncEntities(true, -1, -1);
    }

    public List<Programacionopcionc> findProgramacionopcioncEntities(int maxResults, int firstResult) {
        return findProgramacionopcioncEntities(false, maxResults, firstResult);
    }

    private List<Programacionopcionc> findProgramacionopcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionopcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionopcionc findProgramacionopcionc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionopcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionopcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionopcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
