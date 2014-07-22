/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Plantel;
import com.utez.evaluacion.entity.Actividadintegradora;
import com.utez.evaluacion.entity.Asignacionactividad;
import com.utez.evaluacion.entity.Asignacionalumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionactividadJpaController implements Serializable {

    public AsignacionactividadJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignacionactividad asignacionactividad) {
        if (asignacionactividad.getAsignacionalumnoList() == null) {
            asignacionactividad.setAsignacionalumnoList(new ArrayList<Asignacionalumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel clavePlantel = asignacionactividad.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel = em.getReference(clavePlantel.getClass(), clavePlantel.getClavePlantel());
                asignacionactividad.setClavePlantel(clavePlantel);
            }
            Actividadintegradora claveActividadIntegra = asignacionactividad.getClaveActividadIntegra();
            if (claveActividadIntegra != null) {
                claveActividadIntegra = em.getReference(claveActividadIntegra.getClass(), claveActividadIntegra.getClaveActividadIntegra());
                asignacionactividad.setClaveActividadIntegra(claveActividadIntegra);
            }
            List<Asignacionalumno> attachedAsignacionalumnoList = new ArrayList<Asignacionalumno>();
            for (Asignacionalumno asignacionalumnoListAsignacionalumnoToAttach : asignacionactividad.getAsignacionalumnoList()) {
                asignacionalumnoListAsignacionalumnoToAttach = em.getReference(asignacionalumnoListAsignacionalumnoToAttach.getClass(), asignacionalumnoListAsignacionalumnoToAttach.getClaveAsignacionAlumno());
                attachedAsignacionalumnoList.add(asignacionalumnoListAsignacionalumnoToAttach);
            }
            asignacionactividad.setAsignacionalumnoList(attachedAsignacionalumnoList);
            em.persist(asignacionactividad);
            if (clavePlantel != null) {
                clavePlantel.getAsignacionactividadList().add(asignacionactividad);
                clavePlantel = em.merge(clavePlantel);
            }
            if (claveActividadIntegra != null) {
                claveActividadIntegra.getAsignacionactividadList().add(asignacionactividad);
                claveActividadIntegra = em.merge(claveActividadIntegra);
            }
            for (Asignacionalumno asignacionalumnoListAsignacionalumno : asignacionactividad.getAsignacionalumnoList()) {
                Asignacionactividad oldClaveAsignacionOfAsignacionalumnoListAsignacionalumno = asignacionalumnoListAsignacionalumno.getClaveAsignacion();
                asignacionalumnoListAsignacionalumno.setClaveAsignacion(asignacionactividad);
                asignacionalumnoListAsignacionalumno = em.merge(asignacionalumnoListAsignacionalumno);
                if (oldClaveAsignacionOfAsignacionalumnoListAsignacionalumno != null) {
                    oldClaveAsignacionOfAsignacionalumnoListAsignacionalumno.getAsignacionalumnoList().remove(asignacionalumnoListAsignacionalumno);
                    oldClaveAsignacionOfAsignacionalumnoListAsignacionalumno = em.merge(oldClaveAsignacionOfAsignacionalumnoListAsignacionalumno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignacionactividad asignacionactividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignacionactividad persistentAsignacionactividad = em.find(Asignacionactividad.class, asignacionactividad.getClaveAsignacion());
            Plantel clavePlantelOld = persistentAsignacionactividad.getClavePlantel();
            Plantel clavePlantelNew = asignacionactividad.getClavePlantel();
            Actividadintegradora claveActividadIntegraOld = persistentAsignacionactividad.getClaveActividadIntegra();
            Actividadintegradora claveActividadIntegraNew = asignacionactividad.getClaveActividadIntegra();
            List<Asignacionalumno> asignacionalumnoListOld = persistentAsignacionactividad.getAsignacionalumnoList();
            List<Asignacionalumno> asignacionalumnoListNew = asignacionactividad.getAsignacionalumnoList();
            if (clavePlantelNew != null) {
                clavePlantelNew = em.getReference(clavePlantelNew.getClass(), clavePlantelNew.getClavePlantel());
                asignacionactividad.setClavePlantel(clavePlantelNew);
            }
            if (claveActividadIntegraNew != null) {
                claveActividadIntegraNew = em.getReference(claveActividadIntegraNew.getClass(), claveActividadIntegraNew.getClaveActividadIntegra());
                asignacionactividad.setClaveActividadIntegra(claveActividadIntegraNew);
            }
            List<Asignacionalumno> attachedAsignacionalumnoListNew = new ArrayList<Asignacionalumno>();
            for (Asignacionalumno asignacionalumnoListNewAsignacionalumnoToAttach : asignacionalumnoListNew) {
                asignacionalumnoListNewAsignacionalumnoToAttach = em.getReference(asignacionalumnoListNewAsignacionalumnoToAttach.getClass(), asignacionalumnoListNewAsignacionalumnoToAttach.getClaveAsignacionAlumno());
                attachedAsignacionalumnoListNew.add(asignacionalumnoListNewAsignacionalumnoToAttach);
            }
            asignacionalumnoListNew = attachedAsignacionalumnoListNew;
            asignacionactividad.setAsignacionalumnoList(asignacionalumnoListNew);
            asignacionactividad = em.merge(asignacionactividad);
            if (clavePlantelOld != null && !clavePlantelOld.equals(clavePlantelNew)) {
                clavePlantelOld.getAsignacionactividadList().remove(asignacionactividad);
                clavePlantelOld = em.merge(clavePlantelOld);
            }
            if (clavePlantelNew != null && !clavePlantelNew.equals(clavePlantelOld)) {
                clavePlantelNew.getAsignacionactividadList().add(asignacionactividad);
                clavePlantelNew = em.merge(clavePlantelNew);
            }
            if (claveActividadIntegraOld != null && !claveActividadIntegraOld.equals(claveActividadIntegraNew)) {
                claveActividadIntegraOld.getAsignacionactividadList().remove(asignacionactividad);
                claveActividadIntegraOld = em.merge(claveActividadIntegraOld);
            }
            if (claveActividadIntegraNew != null && !claveActividadIntegraNew.equals(claveActividadIntegraOld)) {
                claveActividadIntegraNew.getAsignacionactividadList().add(asignacionactividad);
                claveActividadIntegraNew = em.merge(claveActividadIntegraNew);
            }
            for (Asignacionalumno asignacionalumnoListOldAsignacionalumno : asignacionalumnoListOld) {
                if (!asignacionalumnoListNew.contains(asignacionalumnoListOldAsignacionalumno)) {
                    asignacionalumnoListOldAsignacionalumno.setClaveAsignacion(null);
                    asignacionalumnoListOldAsignacionalumno = em.merge(asignacionalumnoListOldAsignacionalumno);
                }
            }
            for (Asignacionalumno asignacionalumnoListNewAsignacionalumno : asignacionalumnoListNew) {
                if (!asignacionalumnoListOld.contains(asignacionalumnoListNewAsignacionalumno)) {
                    Asignacionactividad oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno = asignacionalumnoListNewAsignacionalumno.getClaveAsignacion();
                    asignacionalumnoListNewAsignacionalumno.setClaveAsignacion(asignacionactividad);
                    asignacionalumnoListNewAsignacionalumno = em.merge(asignacionalumnoListNewAsignacionalumno);
                    if (oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno != null && !oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno.equals(asignacionactividad)) {
                        oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno.getAsignacionalumnoList().remove(asignacionalumnoListNewAsignacionalumno);
                        oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno = em.merge(oldClaveAsignacionOfAsignacionalumnoListNewAsignacionalumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignacionactividad.getClaveAsignacion();
                if (findAsignacionactividad(id) == null) {
                    throw new NonexistentEntityException("The asignacionactividad with id " + id + " no longer exists.");
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
            Asignacionactividad asignacionactividad;
            try {
                asignacionactividad = em.getReference(Asignacionactividad.class, id);
                asignacionactividad.getClaveAsignacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionactividad with id " + id + " no longer exists.", enfe);
            }
            Plantel clavePlantel = asignacionactividad.getClavePlantel();
            if (clavePlantel != null) {
                clavePlantel.getAsignacionactividadList().remove(asignacionactividad);
                clavePlantel = em.merge(clavePlantel);
            }
            Actividadintegradora claveActividadIntegra = asignacionactividad.getClaveActividadIntegra();
            if (claveActividadIntegra != null) {
                claveActividadIntegra.getAsignacionactividadList().remove(asignacionactividad);
                claveActividadIntegra = em.merge(claveActividadIntegra);
            }
            List<Asignacionalumno> asignacionalumnoList = asignacionactividad.getAsignacionalumnoList();
            for (Asignacionalumno asignacionalumnoListAsignacionalumno : asignacionalumnoList) {
                asignacionalumnoListAsignacionalumno.setClaveAsignacion(null);
                asignacionalumnoListAsignacionalumno = em.merge(asignacionalumnoListAsignacionalumno);
            }
            em.remove(asignacionactividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignacionactividad> findAsignacionactividadEntities() {
        return findAsignacionactividadEntities(true, -1, -1);
    }

    public List<Asignacionactividad> findAsignacionactividadEntities(int maxResults, int firstResult) {
        return findAsignacionactividadEntities(false, maxResults, firstResult);
    }

    private List<Asignacionactividad> findAsignacionactividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignacionactividad as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignacionactividad findAsignacionactividad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignacionactividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionactividadCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignacionactividad as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
