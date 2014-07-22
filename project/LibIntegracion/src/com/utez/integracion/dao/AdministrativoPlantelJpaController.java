/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Plantel;
import com.utez.integracion.entity.Administrativo;
import com.utez.integracion.entity.AdministrativoPlantel;
import com.utez.integracion.entity.AdministrativoPlantelPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AdministrativoPlantelJpaController implements Serializable {

    public AdministrativoPlantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AdministrativoPlantel administrativoPlantel) throws PreexistingEntityException, Exception {
        if (administrativoPlantel.getAdministrativoPlantelPK() == null) {
            administrativoPlantel.setAdministrativoPlantelPK(new AdministrativoPlantelPK());
        }
        administrativoPlantel.getAdministrativoPlantelPK().setIdAdministrativo(administrativoPlantel.getAdministrativo().getIdAdministrativo());
        administrativoPlantel.getAdministrativoPlantelPK().setIdPlantel(administrativoPlantel.getPlantel().getIdPlantel());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel plantel = administrativoPlantel.getPlantel();
            if (plantel != null) {
                plantel = em.getReference(plantel.getClass(), plantel.getIdPlantel());
                administrativoPlantel.setPlantel(plantel);
            }
            Administrativo administrativo = administrativoPlantel.getAdministrativo();
            if (administrativo != null) {
                administrativo = em.getReference(administrativo.getClass(), administrativo.getIdAdministrativo());
                administrativoPlantel.setAdministrativo(administrativo);
            }
            em.persist(administrativoPlantel);
            if (plantel != null) {
                plantel.getAdministrativoPlantelList().add(administrativoPlantel);
                plantel = em.merge(plantel);
            }
            if (administrativo != null) {
                administrativo.getAdministrativoPlantelList().add(administrativoPlantel);
                administrativo = em.merge(administrativo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdministrativoPlantel(administrativoPlantel.getAdministrativoPlantelPK()) != null) {
                throw new PreexistingEntityException("AdministrativoPlantel " + administrativoPlantel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AdministrativoPlantel administrativoPlantel) throws NonexistentEntityException, Exception {
        administrativoPlantel.getAdministrativoPlantelPK().setIdAdministrativo(administrativoPlantel.getAdministrativo().getIdAdministrativo());
        administrativoPlantel.getAdministrativoPlantelPK().setIdPlantel(administrativoPlantel.getPlantel().getIdPlantel());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AdministrativoPlantel persistentAdministrativoPlantel = em.find(AdministrativoPlantel.class, administrativoPlantel.getAdministrativoPlantelPK());
            Plantel plantelOld = persistentAdministrativoPlantel.getPlantel();
            Plantel plantelNew = administrativoPlantel.getPlantel();
            Administrativo administrativoOld = persistentAdministrativoPlantel.getAdministrativo();
            Administrativo administrativoNew = administrativoPlantel.getAdministrativo();
            if (plantelNew != null) {
                plantelNew = em.getReference(plantelNew.getClass(), plantelNew.getIdPlantel());
                administrativoPlantel.setPlantel(plantelNew);
            }
            if (administrativoNew != null) {
                administrativoNew = em.getReference(administrativoNew.getClass(), administrativoNew.getIdAdministrativo());
                administrativoPlantel.setAdministrativo(administrativoNew);
            }
            administrativoPlantel = em.merge(administrativoPlantel);
            if (plantelOld != null && !plantelOld.equals(plantelNew)) {
                plantelOld.getAdministrativoPlantelList().remove(administrativoPlantel);
                plantelOld = em.merge(plantelOld);
            }
            if (plantelNew != null && !plantelNew.equals(plantelOld)) {
                plantelNew.getAdministrativoPlantelList().add(administrativoPlantel);
                plantelNew = em.merge(plantelNew);
            }
            if (administrativoOld != null && !administrativoOld.equals(administrativoNew)) {
                administrativoOld.getAdministrativoPlantelList().remove(administrativoPlantel);
                administrativoOld = em.merge(administrativoOld);
            }
            if (administrativoNew != null && !administrativoNew.equals(administrativoOld)) {
                administrativoNew.getAdministrativoPlantelList().add(administrativoPlantel);
                administrativoNew = em.merge(administrativoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AdministrativoPlantelPK id = administrativoPlantel.getAdministrativoPlantelPK();
                if (findAdministrativoPlantel(id) == null) {
                    throw new NonexistentEntityException("The administrativoPlantel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AdministrativoPlantelPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AdministrativoPlantel administrativoPlantel;
            try {
                administrativoPlantel = em.getReference(AdministrativoPlantel.class, id);
                administrativoPlantel.getAdministrativoPlantelPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrativoPlantel with id " + id + " no longer exists.", enfe);
            }
            Plantel plantel = administrativoPlantel.getPlantel();
            if (plantel != null) {
                plantel.getAdministrativoPlantelList().remove(administrativoPlantel);
                plantel = em.merge(plantel);
            }
            Administrativo administrativo = administrativoPlantel.getAdministrativo();
            if (administrativo != null) {
                administrativo.getAdministrativoPlantelList().remove(administrativoPlantel);
                administrativo = em.merge(administrativo);
            }
            em.remove(administrativoPlantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AdministrativoPlantel> findAdministrativoPlantelEntities() {
        return findAdministrativoPlantelEntities(true, -1, -1);
    }

    public List<AdministrativoPlantel> findAdministrativoPlantelEntities(int maxResults, int firstResult) {
        return findAdministrativoPlantelEntities(false, maxResults, firstResult);
    }

    private List<AdministrativoPlantel> findAdministrativoPlantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AdministrativoPlantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AdministrativoPlantel findAdministrativoPlantel(AdministrativoPlantelPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AdministrativoPlantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministrativoPlantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AdministrativoPlantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
