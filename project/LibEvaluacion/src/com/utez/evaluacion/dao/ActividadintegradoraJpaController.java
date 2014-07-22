/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Actividadintegradora;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asignacionactividad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ActividadintegradoraJpaController implements Serializable {

    public ActividadintegradoraJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividadintegradora actividadintegradora) {
        if (actividadintegradora.getAsignacionactividadList() == null) {
            actividadintegradora.setAsignacionactividadList(new ArrayList<Asignacionactividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignacionactividad> attachedAsignacionactividadList = new ArrayList<Asignacionactividad>();
            for (Asignacionactividad asignacionactividadListAsignacionactividadToAttach : actividadintegradora.getAsignacionactividadList()) {
                asignacionactividadListAsignacionactividadToAttach = em.getReference(asignacionactividadListAsignacionactividadToAttach.getClass(), asignacionactividadListAsignacionactividadToAttach.getClaveAsignacion());
                attachedAsignacionactividadList.add(asignacionactividadListAsignacionactividadToAttach);
            }
            actividadintegradora.setAsignacionactividadList(attachedAsignacionactividadList);
            em.persist(actividadintegradora);
            for (Asignacionactividad asignacionactividadListAsignacionactividad : actividadintegradora.getAsignacionactividadList()) {
                Actividadintegradora oldClaveActividadIntegraOfAsignacionactividadListAsignacionactividad = asignacionactividadListAsignacionactividad.getClaveActividadIntegra();
                asignacionactividadListAsignacionactividad.setClaveActividadIntegra(actividadintegradora);
                asignacionactividadListAsignacionactividad = em.merge(asignacionactividadListAsignacionactividad);
                if (oldClaveActividadIntegraOfAsignacionactividadListAsignacionactividad != null) {
                    oldClaveActividadIntegraOfAsignacionactividadListAsignacionactividad.getAsignacionactividadList().remove(asignacionactividadListAsignacionactividad);
                    oldClaveActividadIntegraOfAsignacionactividadListAsignacionactividad = em.merge(oldClaveActividadIntegraOfAsignacionactividadListAsignacionactividad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadintegradora actividadintegradora) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadintegradora persistentActividadintegradora = em.find(Actividadintegradora.class, actividadintegradora.getClaveActividadIntegra());
            List<Asignacionactividad> asignacionactividadListOld = persistentActividadintegradora.getAsignacionactividadList();
            List<Asignacionactividad> asignacionactividadListNew = actividadintegradora.getAsignacionactividadList();
            List<Asignacionactividad> attachedAsignacionactividadListNew = new ArrayList<Asignacionactividad>();
            for (Asignacionactividad asignacionactividadListNewAsignacionactividadToAttach : asignacionactividadListNew) {
                asignacionactividadListNewAsignacionactividadToAttach = em.getReference(asignacionactividadListNewAsignacionactividadToAttach.getClass(), asignacionactividadListNewAsignacionactividadToAttach.getClaveAsignacion());
                attachedAsignacionactividadListNew.add(asignacionactividadListNewAsignacionactividadToAttach);
            }
            asignacionactividadListNew = attachedAsignacionactividadListNew;
            actividadintegradora.setAsignacionactividadList(asignacionactividadListNew);
            actividadintegradora = em.merge(actividadintegradora);
            for (Asignacionactividad asignacionactividadListOldAsignacionactividad : asignacionactividadListOld) {
                if (!asignacionactividadListNew.contains(asignacionactividadListOldAsignacionactividad)) {
                    asignacionactividadListOldAsignacionactividad.setClaveActividadIntegra(null);
                    asignacionactividadListOldAsignacionactividad = em.merge(asignacionactividadListOldAsignacionactividad);
                }
            }
            for (Asignacionactividad asignacionactividadListNewAsignacionactividad : asignacionactividadListNew) {
                if (!asignacionactividadListOld.contains(asignacionactividadListNewAsignacionactividad)) {
                    Actividadintegradora oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad = asignacionactividadListNewAsignacionactividad.getClaveActividadIntegra();
                    asignacionactividadListNewAsignacionactividad.setClaveActividadIntegra(actividadintegradora);
                    asignacionactividadListNewAsignacionactividad = em.merge(asignacionactividadListNewAsignacionactividad);
                    if (oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad != null && !oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad.equals(actividadintegradora)) {
                        oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad.getAsignacionactividadList().remove(asignacionactividadListNewAsignacionactividad);
                        oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad = em.merge(oldClaveActividadIntegraOfAsignacionactividadListNewAsignacionactividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividadintegradora.getClaveActividadIntegra();
                if (findActividadintegradora(id) == null) {
                    throw new NonexistentEntityException("The actividadintegradora with id " + id + " no longer exists.");
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
            Actividadintegradora actividadintegradora;
            try {
                actividadintegradora = em.getReference(Actividadintegradora.class, id);
                actividadintegradora.getClaveActividadIntegra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadintegradora with id " + id + " no longer exists.", enfe);
            }
            List<Asignacionactividad> asignacionactividadList = actividadintegradora.getAsignacionactividadList();
            for (Asignacionactividad asignacionactividadListAsignacionactividad : asignacionactividadList) {
                asignacionactividadListAsignacionactividad.setClaveActividadIntegra(null);
                asignacionactividadListAsignacionactividad = em.merge(asignacionactividadListAsignacionactividad);
            }
            em.remove(actividadintegradora);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadintegradora> findActividadintegradoraEntities() {
        return findActividadintegradoraEntities(true, -1, -1);
    }

    public List<Actividadintegradora> findActividadintegradoraEntities(int maxResults, int firstResult) {
        return findActividadintegradoraEntities(false, maxResults, firstResult);
    }

    private List<Actividadintegradora> findActividadintegradoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Actividadintegradora as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Actividadintegradora findActividadintegradora(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadintegradora.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadintegradoraCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Actividadintegradora as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
