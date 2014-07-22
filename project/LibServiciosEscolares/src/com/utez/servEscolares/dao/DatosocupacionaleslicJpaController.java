/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosocupacionaleslic;
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
public class DatosocupacionaleslicJpaController implements Serializable {

    public DatosocupacionaleslicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosocupacionaleslic datosocupacionaleslic) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosocupacionaleslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosocupacionaleslic(datosocupacionaleslic.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosocupacionaleslic " + datosocupacionaleslic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosocupacionaleslic datosocupacionaleslic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosocupacionaleslic = em.merge(datosocupacionaleslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosocupacionaleslic.getMatricula();
                if (findDatosocupacionaleslic(id) == null) {
                    throw new NonexistentEntityException("The datosocupacionaleslic with id " + id + " no longer exists.");
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
            Datosocupacionaleslic datosocupacionaleslic;
            try {
                datosocupacionaleslic = em.getReference(Datosocupacionaleslic.class, id);
                datosocupacionaleslic.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosocupacionaleslic with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosocupacionaleslic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosocupacionaleslic> findDatosocupacionaleslicEntities() {
        return findDatosocupacionaleslicEntities(true, -1, -1);
    }

    public List<Datosocupacionaleslic> findDatosocupacionaleslicEntities(int maxResults, int firstResult) {
        return findDatosocupacionaleslicEntities(false, maxResults, firstResult);
    }

    private List<Datosocupacionaleslic> findDatosocupacionaleslicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosocupacionaleslic as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosocupacionaleslic findDatosocupacionaleslic(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosocupacionaleslic.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosocupacionaleslicCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosocupacionaleslic as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
