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
import com.utez.integracion.entity.Vacacion;
import com.utez.integracion.entity.Plantel;
import com.utez.integracion.entity.VacacionPlantel;
import com.utez.integracion.entity.VacacionPlantelPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class VacacionPlantelJpaController implements Serializable {

    public VacacionPlantelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VacacionPlantel vacacionPlantel) throws PreexistingEntityException, Exception {
        if (vacacionPlantel.getVacacionPlantelPK() == null) {
            vacacionPlantel.setVacacionPlantelPK(new VacacionPlantelPK());
        }
        vacacionPlantel.getVacacionPlantelPK().setIdPlantel(vacacionPlantel.getPlantel().getIdPlantel());
        vacacionPlantel.getVacacionPlantelPK().setIdVacacion(vacacionPlantel.getVacacion().getIdVacacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacacion vacacion = vacacionPlantel.getVacacion();
            if (vacacion != null) {
                vacacion = em.getReference(vacacion.getClass(), vacacion.getIdVacacion());
                vacacionPlantel.setVacacion(vacacion);
            }
            Plantel plantel = vacacionPlantel.getPlantel();
            if (plantel != null) {
                plantel = em.getReference(plantel.getClass(), plantel.getIdPlantel());
                vacacionPlantel.setPlantel(plantel);
            }
            em.persist(vacacionPlantel);
            if (vacacion != null) {
                vacacion.getVacacionPlantelList().add(vacacionPlantel);
                vacacion = em.merge(vacacion);
            }
            if (plantel != null) {
                plantel.getVacacionPlantelList().add(vacacionPlantel);
                plantel = em.merge(plantel);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVacacionPlantel(vacacionPlantel.getVacacionPlantelPK()) != null) {
                throw new PreexistingEntityException("VacacionPlantel " + vacacionPlantel + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VacacionPlantel vacacionPlantel) throws NonexistentEntityException, Exception {
        vacacionPlantel.getVacacionPlantelPK().setIdPlantel(vacacionPlantel.getPlantel().getIdPlantel());
        vacacionPlantel.getVacacionPlantelPK().setIdVacacion(vacacionPlantel.getVacacion().getIdVacacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VacacionPlantel persistentVacacionPlantel = em.find(VacacionPlantel.class, vacacionPlantel.getVacacionPlantelPK());
            Vacacion vacacionOld = persistentVacacionPlantel.getVacacion();
            Vacacion vacacionNew = vacacionPlantel.getVacacion();
            Plantel plantelOld = persistentVacacionPlantel.getPlantel();
            Plantel plantelNew = vacacionPlantel.getPlantel();
            if (vacacionNew != null) {
                vacacionNew = em.getReference(vacacionNew.getClass(), vacacionNew.getIdVacacion());
                vacacionPlantel.setVacacion(vacacionNew);
            }
            if (plantelNew != null) {
                plantelNew = em.getReference(plantelNew.getClass(), plantelNew.getIdPlantel());
                vacacionPlantel.setPlantel(plantelNew);
            }
            vacacionPlantel = em.merge(vacacionPlantel);
            if (vacacionOld != null && !vacacionOld.equals(vacacionNew)) {
                vacacionOld.getVacacionPlantelList().remove(vacacionPlantel);
                vacacionOld = em.merge(vacacionOld);
            }
            if (vacacionNew != null && !vacacionNew.equals(vacacionOld)) {
                vacacionNew.getVacacionPlantelList().add(vacacionPlantel);
                vacacionNew = em.merge(vacacionNew);
            }
            if (plantelOld != null && !plantelOld.equals(plantelNew)) {
                plantelOld.getVacacionPlantelList().remove(vacacionPlantel);
                plantelOld = em.merge(plantelOld);
            }
            if (plantelNew != null && !plantelNew.equals(plantelOld)) {
                plantelNew.getVacacionPlantelList().add(vacacionPlantel);
                plantelNew = em.merge(plantelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                VacacionPlantelPK id = vacacionPlantel.getVacacionPlantelPK();
                if (findVacacionPlantel(id) == null) {
                    throw new NonexistentEntityException("The vacacionPlantel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(VacacionPlantelPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            VacacionPlantel vacacionPlantel;
            try {
                vacacionPlantel = em.getReference(VacacionPlantel.class, id);
                vacacionPlantel.getVacacionPlantelPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacacionPlantel with id " + id + " no longer exists.", enfe);
            }
            Vacacion vacacion = vacacionPlantel.getVacacion();
            if (vacacion != null) {
                vacacion.getVacacionPlantelList().remove(vacacionPlantel);
                vacacion = em.merge(vacacion);
            }
            Plantel plantel = vacacionPlantel.getPlantel();
            if (plantel != null) {
                plantel.getVacacionPlantelList().remove(vacacionPlantel);
                plantel = em.merge(plantel);
            }
            em.remove(vacacionPlantel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VacacionPlantel> findVacacionPlantelEntities() {
        return findVacacionPlantelEntities(true, -1, -1);
    }

    public List<VacacionPlantel> findVacacionPlantelEntities(int maxResults, int firstResult) {
        return findVacacionPlantelEntities(false, maxResults, firstResult);
    }

    private List<VacacionPlantel> findVacacionPlantelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from VacacionPlantel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VacacionPlantel findVacacionPlantel(VacacionPlantelPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VacacionPlantel.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacacionPlantelCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from VacacionPlantel as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
