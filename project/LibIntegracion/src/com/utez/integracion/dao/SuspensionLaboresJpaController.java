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
import com.utez.integracion.entity.CalendarioRectoria;
import com.utez.integracion.entity.SuspensionLabores;
import com.utez.integracion.entity.SuspensionPlantel;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class SuspensionLaboresJpaController implements Serializable {

    public SuspensionLaboresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SuspensionLabores suspensionLabores) {
        if (suspensionLabores.getSuspensionPlantelList() == null) {
            suspensionLabores.setSuspensionPlantelList(new ArrayList<SuspensionPlantel>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CalendarioRectoria idCalendariorectoria = suspensionLabores.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria = em.getReference(idCalendariorectoria.getClass(), idCalendariorectoria.getIdCalendariorectoria());
                suspensionLabores.setIdCalendariorectoria(idCalendariorectoria);
            }
            List<SuspensionPlantel> attachedSuspensionPlantelList = new ArrayList<SuspensionPlantel>();
            for (SuspensionPlantel suspensionPlantelListSuspensionPlantelToAttach : suspensionLabores.getSuspensionPlantelList()) {
                suspensionPlantelListSuspensionPlantelToAttach = em.getReference(suspensionPlantelListSuspensionPlantelToAttach.getClass(), suspensionPlantelListSuspensionPlantelToAttach.getSuspensionPlantelPK());
                attachedSuspensionPlantelList.add(suspensionPlantelListSuspensionPlantelToAttach);
            }
            suspensionLabores.setSuspensionPlantelList(attachedSuspensionPlantelList);
            em.persist(suspensionLabores);
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getSuspensionLaboresList().add(suspensionLabores);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            for (SuspensionPlantel suspensionPlantelListSuspensionPlantel : suspensionLabores.getSuspensionPlantelList()) {
                SuspensionLabores oldSuspensionLaboresOfSuspensionPlantelListSuspensionPlantel = suspensionPlantelListSuspensionPlantel.getSuspensionLabores();
                suspensionPlantelListSuspensionPlantel.setSuspensionLabores(suspensionLabores);
                suspensionPlantelListSuspensionPlantel = em.merge(suspensionPlantelListSuspensionPlantel);
                if (oldSuspensionLaboresOfSuspensionPlantelListSuspensionPlantel != null) {
                    oldSuspensionLaboresOfSuspensionPlantelListSuspensionPlantel.getSuspensionPlantelList().remove(suspensionPlantelListSuspensionPlantel);
                    oldSuspensionLaboresOfSuspensionPlantelListSuspensionPlantel = em.merge(oldSuspensionLaboresOfSuspensionPlantelListSuspensionPlantel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SuspensionLabores suspensionLabores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SuspensionLabores persistentSuspensionLabores = em.find(SuspensionLabores.class, suspensionLabores.getIdSuspensionlabores());
            CalendarioRectoria idCalendariorectoriaOld = persistentSuspensionLabores.getIdCalendariorectoria();
            CalendarioRectoria idCalendariorectoriaNew = suspensionLabores.getIdCalendariorectoria();
            List<SuspensionPlantel> suspensionPlantelListOld = persistentSuspensionLabores.getSuspensionPlantelList();
            List<SuspensionPlantel> suspensionPlantelListNew = suspensionLabores.getSuspensionPlantelList();
            List<String> illegalOrphanMessages = null;
            for (SuspensionPlantel suspensionPlantelListOldSuspensionPlantel : suspensionPlantelListOld) {
                if (!suspensionPlantelListNew.contains(suspensionPlantelListOldSuspensionPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SuspensionPlantel " + suspensionPlantelListOldSuspensionPlantel + " since its suspensionLabores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCalendariorectoriaNew != null) {
                idCalendariorectoriaNew = em.getReference(idCalendariorectoriaNew.getClass(), idCalendariorectoriaNew.getIdCalendariorectoria());
                suspensionLabores.setIdCalendariorectoria(idCalendariorectoriaNew);
            }
            List<SuspensionPlantel> attachedSuspensionPlantelListNew = new ArrayList<SuspensionPlantel>();
            for (SuspensionPlantel suspensionPlantelListNewSuspensionPlantelToAttach : suspensionPlantelListNew) {
                suspensionPlantelListNewSuspensionPlantelToAttach = em.getReference(suspensionPlantelListNewSuspensionPlantelToAttach.getClass(), suspensionPlantelListNewSuspensionPlantelToAttach.getSuspensionPlantelPK());
                attachedSuspensionPlantelListNew.add(suspensionPlantelListNewSuspensionPlantelToAttach);
            }
            suspensionPlantelListNew = attachedSuspensionPlantelListNew;
            suspensionLabores.setSuspensionPlantelList(suspensionPlantelListNew);
            suspensionLabores = em.merge(suspensionLabores);
            if (idCalendariorectoriaOld != null && !idCalendariorectoriaOld.equals(idCalendariorectoriaNew)) {
                idCalendariorectoriaOld.getSuspensionLaboresList().remove(suspensionLabores);
                idCalendariorectoriaOld = em.merge(idCalendariorectoriaOld);
            }
            if (idCalendariorectoriaNew != null && !idCalendariorectoriaNew.equals(idCalendariorectoriaOld)) {
                idCalendariorectoriaNew.getSuspensionLaboresList().add(suspensionLabores);
                idCalendariorectoriaNew = em.merge(idCalendariorectoriaNew);
            }
            for (SuspensionPlantel suspensionPlantelListNewSuspensionPlantel : suspensionPlantelListNew) {
                if (!suspensionPlantelListOld.contains(suspensionPlantelListNewSuspensionPlantel)) {
                    SuspensionLabores oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel = suspensionPlantelListNewSuspensionPlantel.getSuspensionLabores();
                    suspensionPlantelListNewSuspensionPlantel.setSuspensionLabores(suspensionLabores);
                    suspensionPlantelListNewSuspensionPlantel = em.merge(suspensionPlantelListNewSuspensionPlantel);
                    if (oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel != null && !oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel.equals(suspensionLabores)) {
                        oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel.getSuspensionPlantelList().remove(suspensionPlantelListNewSuspensionPlantel);
                        oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel = em.merge(oldSuspensionLaboresOfSuspensionPlantelListNewSuspensionPlantel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = suspensionLabores.getIdSuspensionlabores();
                if (findSuspensionLabores(id) == null) {
                    throw new NonexistentEntityException("The suspensionLabores with id " + id + " no longer exists.");
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
            SuspensionLabores suspensionLabores;
            try {
                suspensionLabores = em.getReference(SuspensionLabores.class, id);
                suspensionLabores.getIdSuspensionlabores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suspensionLabores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SuspensionPlantel> suspensionPlantelListOrphanCheck = suspensionLabores.getSuspensionPlantelList();
            for (SuspensionPlantel suspensionPlantelListOrphanCheckSuspensionPlantel : suspensionPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SuspensionLabores (" + suspensionLabores + ") cannot be destroyed since the SuspensionPlantel " + suspensionPlantelListOrphanCheckSuspensionPlantel + " in its suspensionPlantelList field has a non-nullable suspensionLabores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CalendarioRectoria idCalendariorectoria = suspensionLabores.getIdCalendariorectoria();
            if (idCalendariorectoria != null) {
                idCalendariorectoria.getSuspensionLaboresList().remove(suspensionLabores);
                idCalendariorectoria = em.merge(idCalendariorectoria);
            }
            em.remove(suspensionLabores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SuspensionLabores> findSuspensionLaboresEntities() {
        return findSuspensionLaboresEntities(true, -1, -1);
    }

    public List<SuspensionLabores> findSuspensionLaboresEntities(int maxResults, int firstResult) {
        return findSuspensionLaboresEntities(false, maxResults, firstResult);
    }

    private List<SuspensionLabores> findSuspensionLaboresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SuspensionLabores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SuspensionLabores findSuspensionLabores(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SuspensionLabores.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuspensionLaboresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SuspensionLabores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
