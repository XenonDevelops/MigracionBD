/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosescolareslic;
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
public class DatosescolareslicJpaController implements Serializable {

    public DatosescolareslicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosescolareslic datosescolareslic) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosescolareslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosescolareslic(datosescolareslic.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosescolareslic " + datosescolareslic + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosescolareslic datosescolareslic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosescolareslic = em.merge(datosescolareslic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosescolareslic.getMatricula();
                if (findDatosescolareslic(id) == null) {
                    throw new NonexistentEntityException("The datosescolareslic with id " + id + " no longer exists.");
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
            Datosescolareslic datosescolareslic;
            try {
                datosescolareslic = em.getReference(Datosescolareslic.class, id);
                datosescolareslic.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosescolareslic with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosescolareslic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosescolareslic> findDatosescolareslicEntities() {
        return findDatosescolareslicEntities(true, -1, -1);
    }

    public List<Datosescolareslic> findDatosescolareslicEntities(int maxResults, int firstResult) {
        return findDatosescolareslicEntities(false, maxResults, firstResult);
    }

    private List<Datosescolareslic> findDatosescolareslicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosescolareslic as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosescolareslic findDatosescolareslic(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosescolareslic.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosescolareslicCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosescolareslic as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
