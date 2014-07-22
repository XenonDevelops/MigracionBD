/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.dao.exceptions.PreexistingEntityException;
import com.utez.evaluacion.entity.Imagenreactivo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Reactivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ImagenreactivoJpaController implements Serializable {

    public ImagenreactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Imagenreactivo imagenreactivo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo claveReactivo = imagenreactivo.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo = em.getReference(claveReactivo.getClass(), claveReactivo.getClaveReactivo());
                imagenreactivo.setClaveReactivo(claveReactivo);
            }
            em.persist(imagenreactivo);
            if (claveReactivo != null) {
                claveReactivo.getImagenreactivoList().add(imagenreactivo);
                claveReactivo = em.merge(claveReactivo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findImagenreactivo(imagenreactivo.getClaveImagen()) != null) {
                throw new PreexistingEntityException("Imagenreactivo " + imagenreactivo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Imagenreactivo imagenreactivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Imagenreactivo persistentImagenreactivo = em.find(Imagenreactivo.class, imagenreactivo.getClaveImagen());
            Reactivo claveReactivoOld = persistentImagenreactivo.getClaveReactivo();
            Reactivo claveReactivoNew = imagenreactivo.getClaveReactivo();
            if (claveReactivoNew != null) {
                claveReactivoNew = em.getReference(claveReactivoNew.getClass(), claveReactivoNew.getClaveReactivo());
                imagenreactivo.setClaveReactivo(claveReactivoNew);
            }
            imagenreactivo = em.merge(imagenreactivo);
            if (claveReactivoOld != null && !claveReactivoOld.equals(claveReactivoNew)) {
                claveReactivoOld.getImagenreactivoList().remove(imagenreactivo);
                claveReactivoOld = em.merge(claveReactivoOld);
            }
            if (claveReactivoNew != null && !claveReactivoNew.equals(claveReactivoOld)) {
                claveReactivoNew.getImagenreactivoList().add(imagenreactivo);
                claveReactivoNew = em.merge(claveReactivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = imagenreactivo.getClaveImagen();
                if (findImagenreactivo(id) == null) {
                    throw new NonexistentEntityException("The imagenreactivo with id " + id + " no longer exists.");
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
            Imagenreactivo imagenreactivo;
            try {
                imagenreactivo = em.getReference(Imagenreactivo.class, id);
                imagenreactivo.getClaveImagen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The imagenreactivo with id " + id + " no longer exists.", enfe);
            }
            Reactivo claveReactivo = imagenreactivo.getClaveReactivo();
            if (claveReactivo != null) {
                claveReactivo.getImagenreactivoList().remove(imagenreactivo);
                claveReactivo = em.merge(claveReactivo);
            }
            em.remove(imagenreactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Imagenreactivo> findImagenreactivoEntities() {
        return findImagenreactivoEntities(true, -1, -1);
    }

    public List<Imagenreactivo> findImagenreactivoEntities(int maxResults, int firstResult) {
        return findImagenreactivoEntities(false, maxResults, firstResult);
    }

    private List<Imagenreactivo> findImagenreactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Imagenreactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Imagenreactivo findImagenreactivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Imagenreactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getImagenreactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Imagenreactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
