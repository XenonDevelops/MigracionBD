/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Programacionautoeval;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Resultadoautoeval;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionautoevalJpaController implements Serializable {

    public ProgramacionautoevalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programacionautoeval programacionautoeval) {
        if (programacionautoeval.getResultadoautoevalList() == null) {
            programacionautoeval.setResultadoautoevalList(new ArrayList<Resultadoautoeval>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resultadoautoeval> attachedResultadoautoevalList = new ArrayList<Resultadoautoeval>();
            for (Resultadoautoeval resultadoautoevalListResultadoautoevalToAttach : programacionautoeval.getResultadoautoevalList()) {
                resultadoautoevalListResultadoautoevalToAttach = em.getReference(resultadoautoevalListResultadoautoevalToAttach.getClass(), resultadoautoevalListResultadoautoevalToAttach.getClaveResultadoAuto());
                attachedResultadoautoevalList.add(resultadoautoevalListResultadoautoevalToAttach);
            }
            programacionautoeval.setResultadoautoevalList(attachedResultadoautoevalList);
            em.persist(programacionautoeval);
            for (Resultadoautoeval resultadoautoevalListResultadoautoeval : programacionautoeval.getResultadoautoevalList()) {
                Programacionautoeval oldClaveProgAutoOfResultadoautoevalListResultadoautoeval = resultadoautoevalListResultadoautoeval.getClaveProgAuto();
                resultadoautoevalListResultadoautoeval.setClaveProgAuto(programacionautoeval);
                resultadoautoevalListResultadoautoeval = em.merge(resultadoautoevalListResultadoautoeval);
                if (oldClaveProgAutoOfResultadoautoevalListResultadoautoeval != null) {
                    oldClaveProgAutoOfResultadoautoevalListResultadoautoeval.getResultadoautoevalList().remove(resultadoautoevalListResultadoautoeval);
                    oldClaveProgAutoOfResultadoautoevalListResultadoautoeval = em.merge(oldClaveProgAutoOfResultadoautoevalListResultadoautoeval);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programacionautoeval programacionautoeval) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionautoeval persistentProgramacionautoeval = em.find(Programacionautoeval.class, programacionautoeval.getClaveProgAuto());
            List<Resultadoautoeval> resultadoautoevalListOld = persistentProgramacionautoeval.getResultadoautoevalList();
            List<Resultadoautoeval> resultadoautoevalListNew = programacionautoeval.getResultadoautoevalList();
            List<Resultadoautoeval> attachedResultadoautoevalListNew = new ArrayList<Resultadoautoeval>();
            for (Resultadoautoeval resultadoautoevalListNewResultadoautoevalToAttach : resultadoautoevalListNew) {
                resultadoautoevalListNewResultadoautoevalToAttach = em.getReference(resultadoautoevalListNewResultadoautoevalToAttach.getClass(), resultadoautoevalListNewResultadoautoevalToAttach.getClaveResultadoAuto());
                attachedResultadoautoevalListNew.add(resultadoautoevalListNewResultadoautoevalToAttach);
            }
            resultadoautoevalListNew = attachedResultadoautoevalListNew;
            programacionautoeval.setResultadoautoevalList(resultadoautoevalListNew);
            programacionautoeval = em.merge(programacionautoeval);
            for (Resultadoautoeval resultadoautoevalListOldResultadoautoeval : resultadoautoevalListOld) {
                if (!resultadoautoevalListNew.contains(resultadoautoevalListOldResultadoautoeval)) {
                    resultadoautoevalListOldResultadoautoeval.setClaveProgAuto(null);
                    resultadoautoevalListOldResultadoautoeval = em.merge(resultadoautoevalListOldResultadoautoeval);
                }
            }
            for (Resultadoautoeval resultadoautoevalListNewResultadoautoeval : resultadoautoevalListNew) {
                if (!resultadoautoevalListOld.contains(resultadoautoevalListNewResultadoautoeval)) {
                    Programacionautoeval oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval = resultadoautoevalListNewResultadoautoeval.getClaveProgAuto();
                    resultadoautoevalListNewResultadoautoeval.setClaveProgAuto(programacionautoeval);
                    resultadoautoevalListNewResultadoautoeval = em.merge(resultadoautoevalListNewResultadoautoeval);
                    if (oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval != null && !oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval.equals(programacionautoeval)) {
                        oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval.getResultadoautoevalList().remove(resultadoautoevalListNewResultadoautoeval);
                        oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval = em.merge(oldClaveProgAutoOfResultadoautoevalListNewResultadoautoeval);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programacionautoeval.getClaveProgAuto();
                if (findProgramacionautoeval(id) == null) {
                    throw new NonexistentEntityException("The programacionautoeval with id " + id + " no longer exists.");
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
            Programacionautoeval programacionautoeval;
            try {
                programacionautoeval = em.getReference(Programacionautoeval.class, id);
                programacionautoeval.getClaveProgAuto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionautoeval with id " + id + " no longer exists.", enfe);
            }
            List<Resultadoautoeval> resultadoautoevalList = programacionautoeval.getResultadoautoevalList();
            for (Resultadoautoeval resultadoautoevalListResultadoautoeval : resultadoautoevalList) {
                resultadoautoevalListResultadoautoeval.setClaveProgAuto(null);
                resultadoautoevalListResultadoautoeval = em.merge(resultadoautoevalListResultadoautoeval);
            }
            em.remove(programacionautoeval);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programacionautoeval> findProgramacionautoevalEntities() {
        return findProgramacionautoevalEntities(true, -1, -1);
    }

    public List<Programacionautoeval> findProgramacionautoevalEntities(int maxResults, int firstResult) {
        return findProgramacionautoevalEntities(false, maxResults, firstResult);
    }

    private List<Programacionautoeval> findProgramacionautoevalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Programacionautoeval as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programacionautoeval findProgramacionautoeval(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programacionautoeval.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionautoevalCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Programacionautoeval as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
