/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.dao.exceptions.PreexistingEntityException;
import com.utez.servEscolares.entity.Imagenes;
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
public class ImagenesJpaController implements Serializable {

    public ImagenesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagenes imagenes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(imagenes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findImagenes(imagenes.getIdImagen()) != null) {
                throw new PreexistingEntityException("Imagenes " + imagenes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagenes imagenes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            imagenes = em.merge(imagenes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imagenes.getIdImagen();
                if (findImagenes(id) == null) {
                    throw new NonexistentEntityException("The imagenes with id " + id + " no longer exists.");
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
            Imagenes imagenes;
            try {
                imagenes = em.getReference(Imagenes.class, id);
                imagenes.getIdImagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenes with id " + id + " no longer exists.", enfe);
            }
            em.remove(imagenes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagenes> findImagenesEntities() {
        return findImagenesEntities(true, -1, -1);
    }

    public List<Imagenes> findImagenesEntities(int maxResults, int firstResult) {
        return findImagenesEntities(false, maxResults, firstResult);
    }

    private List<Imagenes> findImagenesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Imagenes as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Imagenes findImagenes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagenes.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Imagenes as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
