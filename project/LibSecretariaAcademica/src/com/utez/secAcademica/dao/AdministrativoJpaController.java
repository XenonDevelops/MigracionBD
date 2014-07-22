/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secAcademica.entity.Administrativo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Recursohumano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AdministrativoJpaController implements Serializable {

    public AdministrativoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrativo administrativo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recursohumano idrecursohumano = administrativo.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano = em.getReference(idrecursohumano.getClass(), idrecursohumano.getIdrecursohumano());
                administrativo.setIdrecursohumano(idrecursohumano);
            }
            em.persist(administrativo);
            if (idrecursohumano != null) {
                idrecursohumano.getAdministrativoList().add(administrativo);
                idrecursohumano = em.merge(idrecursohumano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrativo administrativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo persistentAdministrativo = em.find(Administrativo.class, administrativo.getIdadministrativo());
            Recursohumano idrecursohumanoOld = persistentAdministrativo.getIdrecursohumano();
            Recursohumano idrecursohumanoNew = administrativo.getIdrecursohumano();
            if (idrecursohumanoNew != null) {
                idrecursohumanoNew = em.getReference(idrecursohumanoNew.getClass(), idrecursohumanoNew.getIdrecursohumano());
                administrativo.setIdrecursohumano(idrecursohumanoNew);
            }
            administrativo = em.merge(administrativo);
            if (idrecursohumanoOld != null && !idrecursohumanoOld.equals(idrecursohumanoNew)) {
                idrecursohumanoOld.getAdministrativoList().remove(administrativo);
                idrecursohumanoOld = em.merge(idrecursohumanoOld);
            }
            if (idrecursohumanoNew != null && !idrecursohumanoNew.equals(idrecursohumanoOld)) {
                idrecursohumanoNew.getAdministrativoList().add(administrativo);
                idrecursohumanoNew = em.merge(idrecursohumanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrativo.getIdadministrativo();
                if (findAdministrativo(id) == null) {
                    throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.");
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
            Administrativo administrativo;
            try {
                administrativo = em.getReference(Administrativo.class, id);
                administrativo.getIdadministrativo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.", enfe);
            }
            Recursohumano idrecursohumano = administrativo.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano.getAdministrativoList().remove(administrativo);
                idrecursohumano = em.merge(idrecursohumano);
            }
            em.remove(administrativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrativo> findAdministrativoEntities() {
        return findAdministrativoEntities(true, -1, -1);
    }

    public List<Administrativo> findAdministrativoEntities(int maxResults, int firstResult) {
        return findAdministrativoEntities(false, maxResults, firstResult);
    }

    private List<Administrativo> findAdministrativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Administrativo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Administrativo findAdministrativo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministrativoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Administrativo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
