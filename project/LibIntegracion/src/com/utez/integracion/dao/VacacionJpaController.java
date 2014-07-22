/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.TipoVacacion;
import com.utez.integracion.entity.CalendarioRectoria;
import com.utez.integracion.entity.Vacacion;
import com.utez.integracion.entity.VacacionPlantel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class VacacionJpaController implements Serializable {

    public VacacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacacion vacacion) {
        if (vacacion.getVacacionPlantelList() == null) {
            vacacion.setVacacionPlantelList(new ArrayList<VacacionPlantel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoVacacion idTipovacacion = vacacion.getIdTipovacacion();
            if (idTipovacacion != null) {
                idTipovacacion = em.getReference(idTipovacacion.getClass(), idTipovacacion.getIdTipovacacion());
                vacacion.setIdTipovacacion(idTipovacacion);
            }
            CalendarioRectoria idCalendariorectoria = vacacion.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria = em.getReference(idCalendariorectoria.getClass(), idCalendariorectoria.getIdCalendariorectoria());
                vacacion.setIdCalendariorectoria(idCalendariorectoria);
            }
            List<VacacionPlantel> attachedVacacionPlantelList = new ArrayList<VacacionPlantel>();
            for (VacacionPlantel vacacionPlantelListVacacionPlantelToAttach : vacacion.getVacacionPlantelList()) {
                vacacionPlantelListVacacionPlantelToAttach = em.getReference(vacacionPlantelListVacacionPlantelToAttach.getClass(), vacacionPlantelListVacacionPlantelToAttach.getVacacionPlantelPK());
                attachedVacacionPlantelList.add(vacacionPlantelListVacacionPlantelToAttach);
            }
            vacacion.setVacacionPlantelList(attachedVacacionPlantelList);
            em.persist(vacacion);
            if (idTipovacacion != null) {
                idTipovacacion.getVacacionList().add(vacacion);
                idTipovacacion = em.merge(idTipovacacion);
            }
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getVacacionList().add(vacacion);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            for (VacacionPlantel vacacionPlantelListVacacionPlantel : vacacion.getVacacionPlantelList()) {
                Vacacion oldVacacionOfVacacionPlantelListVacacionPlantel = vacacionPlantelListVacacionPlantel.getVacacion();
                vacacionPlantelListVacacionPlantel.setVacacion(vacacion);
                vacacionPlantelListVacacionPlantel = em.merge(vacacionPlantelListVacacionPlantel);
                if (oldVacacionOfVacacionPlantelListVacacionPlantel != null) {
                    oldVacacionOfVacacionPlantelListVacacionPlantel.getVacacionPlantelList().remove(vacacionPlantelListVacacionPlantel);
                    oldVacacionOfVacacionPlantelListVacacionPlantel = em.merge(oldVacacionOfVacacionPlantelListVacacionPlantel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacacion vacacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacacion persistentVacacion = em.find(Vacacion.class, vacacion.getIdVacacion());
            TipoVacacion idTipovacacionOld = persistentVacacion.getIdTipovacacion();
            TipoVacacion idTipovacacionNew = vacacion.getIdTipovacacion();
            CalendarioRectoria idCalendariorectoriaOld = persistentVacacion.getIdCalendariorectoria();
            CalendarioRectoria idCalendariorectoriaNew = vacacion.getIdCalendariorectoria();
            List<VacacionPlantel> vacacionPlantelListOld = persistentVacacion.getVacacionPlantelList();
            List<VacacionPlantel> vacacionPlantelListNew = vacacion.getVacacionPlantelList();
            List<String> illegalOrphanMessages = null;
            for (VacacionPlantel vacacionPlantelListOldVacacionPlantel : vacacionPlantelListOld) {
                if (!vacacionPlantelListNew.contains(vacacionPlantelListOldVacacionPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacacionPlantel " + vacacionPlantelListOldVacacionPlantel + " since its vacacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipovacacionNew != null) {
                idTipovacacionNew = em.getReference(idTipovacacionNew.getClass(), idTipovacacionNew.getIdTipovacacion());
                vacacion.setIdTipovacacion(idTipovacacionNew);
            }
            if (idCalendariorectoriaNew != null) {
                idCalendariorectoriaNew = em.getReference(idCalendariorectoriaNew.getClass(), idCalendariorectoriaNew.getIdCalendariorectoria());
                vacacion.setIdCalendariorectoria(idCalendariorectoriaNew);
            }
            List<VacacionPlantel> attachedVacacionPlantelListNew = new ArrayList<VacacionPlantel>();
            for (VacacionPlantel vacacionPlantelListNewVacacionPlantelToAttach : vacacionPlantelListNew) {
                vacacionPlantelListNewVacacionPlantelToAttach = em.getReference(vacacionPlantelListNewVacacionPlantelToAttach.getClass(), vacacionPlantelListNewVacacionPlantelToAttach.getVacacionPlantelPK());
                attachedVacacionPlantelListNew.add(vacacionPlantelListNewVacacionPlantelToAttach);
            }
            vacacionPlantelListNew = attachedVacacionPlantelListNew;
            vacacion.setVacacionPlantelList(vacacionPlantelListNew);
            vacacion = em.merge(vacacion);
            if (idTipovacacionOld != null && !idTipovacacionOld.equals(idTipovacacionNew)) {
                idTipovacacionOld.getVacacionList().remove(vacacion);
                idTipovacacionOld = em.merge(idTipovacacionOld);
            }
            if (idTipovacacionNew != null && !idTipovacacionNew.equals(idTipovacacionOld)) {
                idTipovacacionNew.getVacacionList().add(vacacion);
                idTipovacacionNew = em.merge(idTipovacacionNew);
            }
            if (idCalendariorectoriaOld != null && !idCalendariorectoriaOld.equals(idCalendariorectoriaNew)) {
                idCalendariorectoriaOld.getVacacionList().remove(vacacion);
                idCalendariorectoriaOld = em.merge(idCalendariorectoriaOld);
            }
            if (idCalendariorectoriaNew != null && !idCalendariorectoriaNew.equals(idCalendariorectoriaOld)) {
                idCalendariorectoriaNew.getVacacionList().add(vacacion);
                idCalendariorectoriaNew = em.merge(idCalendariorectoriaNew);
            }
            for (VacacionPlantel vacacionPlantelListNewVacacionPlantel : vacacionPlantelListNew) {
                if (!vacacionPlantelListOld.contains(vacacionPlantelListNewVacacionPlantel)) {
                    Vacacion oldVacacionOfVacacionPlantelListNewVacacionPlantel = vacacionPlantelListNewVacacionPlantel.getVacacion();
                    vacacionPlantelListNewVacacionPlantel.setVacacion(vacacion);
                    vacacionPlantelListNewVacacionPlantel = em.merge(vacacionPlantelListNewVacacionPlantel);
                    if (oldVacacionOfVacacionPlantelListNewVacacionPlantel != null && !oldVacacionOfVacacionPlantelListNewVacacionPlantel.equals(vacacion)) {
                        oldVacacionOfVacacionPlantelListNewVacacionPlantel.getVacacionPlantelList().remove(vacacionPlantelListNewVacacionPlantel);
                        oldVacacionOfVacacionPlantelListNewVacacionPlantel = em.merge(oldVacacionOfVacacionPlantelListNewVacacionPlantel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = vacacion.getIdVacacion();
                if (findVacacion(id) == null) {
                    throw new NonexistentEntityException("The vacacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacacion vacacion;
            try {
                vacacion = em.getReference(Vacacion.class, id);
                vacacion.getIdVacacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<VacacionPlantel> vacacionPlantelListOrphanCheck = vacacion.getVacacionPlantelList();
            for (VacacionPlantel vacacionPlantelListOrphanCheckVacacionPlantel : vacacionPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vacacion (" + vacacion + ") cannot be destroyed since the VacacionPlantel " + vacacionPlantelListOrphanCheckVacacionPlantel + " in its vacacionPlantelList field has a non-nullable vacacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoVacacion idTipovacacion = vacacion.getIdTipovacacion();
            if (idTipovacacion != null) {
                idTipovacacion.getVacacionList().remove(vacacion);
                idTipovacacion = em.merge(idTipovacacion);
            }
            CalendarioRectoria idCalendariorectoria = vacacion.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getVacacionList().remove(vacacion);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            em.remove(vacacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacacion> findVacacionEntities() {
        return findVacacionEntities(true, -1, -1);
    }

    public List<Vacacion> findVacacionEntities(int maxResults, int firstResult) {
        return findVacacionEntities(false, maxResults, firstResult);
    }

    private List<Vacacion> findVacacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Vacacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vacacion findVacacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Vacacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
