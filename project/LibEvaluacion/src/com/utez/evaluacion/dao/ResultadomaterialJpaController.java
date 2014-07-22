/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Resultadoencuesta;
import com.utez.evaluacion.entity.Resultadomaterial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ResultadomaterialJpaController implements Serializable {

    public ResultadomaterialJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadomaterial resultadomaterial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoencuesta claveResultadoE = resultadomaterial.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE = em.getReference(claveResultadoE.getClass(), claveResultadoE.getClaveResultadoE());
                resultadomaterial.setClaveResultadoE(claveResultadoE);
            }
            em.persist(resultadomaterial);
            if (claveResultadoE != null) {
                claveResultadoE.getResultadomaterialList().add(resultadomaterial);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadomaterial resultadomaterial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadomaterial persistentResultadomaterial = em.find(Resultadomaterial.class, resultadomaterial.getClaveResultadoM());
            Resultadoencuesta claveResultadoEOld = persistentResultadomaterial.getClaveResultadoE();
            Resultadoencuesta claveResultadoENew = resultadomaterial.getClaveResultadoE();
            if (claveResultadoENew != null) {
                claveResultadoENew = em.getReference(claveResultadoENew.getClass(), claveResultadoENew.getClaveResultadoE());
                resultadomaterial.setClaveResultadoE(claveResultadoENew);
            }
            resultadomaterial = em.merge(resultadomaterial);
            if (claveResultadoEOld != null && !claveResultadoEOld.equals(claveResultadoENew)) {
                claveResultadoEOld.getResultadomaterialList().remove(resultadomaterial);
                claveResultadoEOld = em.merge(claveResultadoEOld);
            }
            if (claveResultadoENew != null && !claveResultadoENew.equals(claveResultadoEOld)) {
                claveResultadoENew.getResultadomaterialList().add(resultadomaterial);
                claveResultadoENew = em.merge(claveResultadoENew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadomaterial.getClaveResultadoM();
                if (findResultadomaterial(id) == null) {
                    throw new NonexistentEntityException("The resultadomaterial with id " + id + " no longer exists.");
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
            Resultadomaterial resultadomaterial;
            try {
                resultadomaterial = em.getReference(Resultadomaterial.class, id);
                resultadomaterial.getClaveResultadoM();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadomaterial with id " + id + " no longer exists.", enfe);
            }
            Resultadoencuesta claveResultadoE = resultadomaterial.getClaveResultadoE();
            if (claveResultadoE != null) {
                claveResultadoE.getResultadomaterialList().remove(resultadomaterial);
                claveResultadoE = em.merge(claveResultadoE);
            }
            em.remove(resultadomaterial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadomaterial> findResultadomaterialEntities() {
        return findResultadomaterialEntities(true, -1, -1);
    }

    public List<Resultadomaterial> findResultadomaterialEntities(int maxResults, int firstResult) {
        return findResultadomaterialEntities(false, maxResults, firstResult);
    }

    private List<Resultadomaterial> findResultadomaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Resultadomaterial as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resultadomaterial findResultadomaterial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadomaterial.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadomaterialCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Resultadomaterial as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
