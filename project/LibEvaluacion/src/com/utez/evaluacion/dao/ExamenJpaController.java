/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Examen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Programacionexamen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ExamenJpaController implements Serializable {

    public ExamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Examen examen) {
        if (examen.getProgramacionexamenList() == null) {
            examen.setProgramacionexamenList(new ArrayList<Programacionexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programacionexamen> attachedProgramacionexamenList = new ArrayList<Programacionexamen>();
            for (Programacionexamen programacionexamenListProgramacionexamenToAttach : examen.getProgramacionexamenList()) {
                programacionexamenListProgramacionexamenToAttach = em.getReference(programacionexamenListProgramacionexamenToAttach.getClass(), programacionexamenListProgramacionexamenToAttach.getClaveProExa());
                attachedProgramacionexamenList.add(programacionexamenListProgramacionexamenToAttach);
            }
            examen.setProgramacionexamenList(attachedProgramacionexamenList);
            em.persist(examen);
            for (Programacionexamen programacionexamenListProgramacionexamen : examen.getProgramacionexamenList()) {
                Examen oldClaveExamenOfProgramacionexamenListProgramacionexamen = programacionexamenListProgramacionexamen.getClaveExamen();
                programacionexamenListProgramacionexamen.setClaveExamen(examen);
                programacionexamenListProgramacionexamen = em.merge(programacionexamenListProgramacionexamen);
                if (oldClaveExamenOfProgramacionexamenListProgramacionexamen != null) {
                    oldClaveExamenOfProgramacionexamenListProgramacionexamen.getProgramacionexamenList().remove(programacionexamenListProgramacionexamen);
                    oldClaveExamenOfProgramacionexamenListProgramacionexamen = em.merge(oldClaveExamenOfProgramacionexamenListProgramacionexamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Examen examen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Examen persistentExamen = em.find(Examen.class, examen.getClaveExamen());
            List<Programacionexamen> programacionexamenListOld = persistentExamen.getProgramacionexamenList();
            List<Programacionexamen> programacionexamenListNew = examen.getProgramacionexamenList();
            List<Programacionexamen> attachedProgramacionexamenListNew = new ArrayList<Programacionexamen>();
            for (Programacionexamen programacionexamenListNewProgramacionexamenToAttach : programacionexamenListNew) {
                programacionexamenListNewProgramacionexamenToAttach = em.getReference(programacionexamenListNewProgramacionexamenToAttach.getClass(), programacionexamenListNewProgramacionexamenToAttach.getClaveProExa());
                attachedProgramacionexamenListNew.add(programacionexamenListNewProgramacionexamenToAttach);
            }
            programacionexamenListNew = attachedProgramacionexamenListNew;
            examen.setProgramacionexamenList(programacionexamenListNew);
            examen = em.merge(examen);
            for (Programacionexamen programacionexamenListOldProgramacionexamen : programacionexamenListOld) {
                if (!programacionexamenListNew.contains(programacionexamenListOldProgramacionexamen)) {
                    programacionexamenListOldProgramacionexamen.setClaveExamen(null);
                    programacionexamenListOldProgramacionexamen = em.merge(programacionexamenListOldProgramacionexamen);
                }
            }
            for (Programacionexamen programacionexamenListNewProgramacionexamen : programacionexamenListNew) {
                if (!programacionexamenListOld.contains(programacionexamenListNewProgramacionexamen)) {
                    Examen oldClaveExamenOfProgramacionexamenListNewProgramacionexamen = programacionexamenListNewProgramacionexamen.getClaveExamen();
                    programacionexamenListNewProgramacionexamen.setClaveExamen(examen);
                    programacionexamenListNewProgramacionexamen = em.merge(programacionexamenListNewProgramacionexamen);
                    if (oldClaveExamenOfProgramacionexamenListNewProgramacionexamen != null && !oldClaveExamenOfProgramacionexamenListNewProgramacionexamen.equals(examen)) {
                        oldClaveExamenOfProgramacionexamenListNewProgramacionexamen.getProgramacionexamenList().remove(programacionexamenListNewProgramacionexamen);
                        oldClaveExamenOfProgramacionexamenListNewProgramacionexamen = em.merge(oldClaveExamenOfProgramacionexamenListNewProgramacionexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = examen.getClaveExamen();
                if (findExamen(id) == null) {
                    throw new NonexistentEntityException("The examen with id " + id + " no longer exists.");
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
            Examen examen;
            try {
                examen = em.getReference(Examen.class, id);
                examen.getClaveExamen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examen with id " + id + " no longer exists.", enfe);
            }
            List<Programacionexamen> programacionexamenList = examen.getProgramacionexamenList();
            for (Programacionexamen programacionexamenListProgramacionexamen : programacionexamenList) {
                programacionexamenListProgramacionexamen.setClaveExamen(null);
                programacionexamenListProgramacionexamen = em.merge(programacionexamenListProgramacionexamen);
            }
            em.remove(examen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Examen> findExamenEntities() {
        return findExamenEntities(true, -1, -1);
    }

    public List<Examen> findExamenEntities(int maxResults, int firstResult) {
        return findExamenEntities(false, maxResults, firstResult);
    }

    private List<Examen> findExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Examen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Examen findExamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Examen.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Examen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
