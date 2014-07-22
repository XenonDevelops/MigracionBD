/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Datosgralesmaestria;
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
public class DatosgralesmaestriaJpaController implements Serializable {

    public DatosgralesmaestriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datosgralesmaestria datosgralesmaestria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosgralesmaestria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatosgralesmaestria(datosgralesmaestria.getMatricula()) != null) {
                throw new PreexistingEntityException("Datosgralesmaestria " + datosgralesmaestria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datosgralesmaestria datosgralesmaestria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosgralesmaestria = em.merge(datosgralesmaestria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datosgralesmaestria.getMatricula();
                if (findDatosgralesmaestria(id) == null) {
                    throw new NonexistentEntityException("The datosgralesmaestria with id " + id + " no longer exists.");
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
            Datosgralesmaestria datosgralesmaestria;
            try {
                datosgralesmaestria = em.getReference(Datosgralesmaestria.class, id);
                datosgralesmaestria.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosgralesmaestria with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosgralesmaestria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datosgralesmaestria> findDatosgralesmaestriaEntities() {
        return findDatosgralesmaestriaEntities(true, -1, -1);
    }

    public List<Datosgralesmaestria> findDatosgralesmaestriaEntities(int maxResults, int firstResult) {
        return findDatosgralesmaestriaEntities(false, maxResults, firstResult);
    }

    private List<Datosgralesmaestria> findDatosgralesmaestriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Datosgralesmaestria as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Datosgralesmaestria findDatosgralesmaestria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datosgralesmaestria.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosgralesmaestriaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Datosgralesmaestria as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
