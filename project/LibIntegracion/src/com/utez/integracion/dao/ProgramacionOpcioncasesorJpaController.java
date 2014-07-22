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
import com.utez.integracion.entity.ProgramacionOpcionc;
import com.utez.integracion.entity.Asesor;
import com.utez.integracion.entity.ProgramacionOpcioncasesor;
import com.utez.integracion.entity.ProgramacionOpcioncasesorPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionOpcioncasesorJpaController implements Serializable {

    public ProgramacionOpcioncasesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionOpcioncasesor programacionOpcioncasesor) throws PreexistingEntityException, Exception {
        if (programacionOpcioncasesor.getProgramacionOpcioncasesorPK() == null) {
            programacionOpcioncasesor.setProgramacionOpcioncasesorPK(new ProgramacionOpcioncasesorPK());
        }
        programacionOpcioncasesor.getProgramacionOpcioncasesorPK().setIdAsesor(programacionOpcioncasesor.getAsesor().getIdAsesor());
        programacionOpcioncasesor.getProgramacionOpcioncasesorPK().setIdProgramacionasignatura(programacionOpcioncasesor.getProgramacionOpcionc().getIdProgramacionasignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionOpcionc programacionOpcionc = programacionOpcioncasesor.getProgramacionOpcionc();
            if (programacionOpcionc != null) {
                programacionOpcionc = em.getReference(programacionOpcionc.getClass(), programacionOpcionc.getIdProgramacionasignatura());
                programacionOpcioncasesor.setProgramacionOpcionc(programacionOpcionc);
            }
            Asesor asesor = programacionOpcioncasesor.getAsesor();
            if (asesor != null) {
                asesor = em.getReference(asesor.getClass(), asesor.getIdAsesor());
                programacionOpcioncasesor.setAsesor(asesor);
            }
            em.persist(programacionOpcioncasesor);
            if (programacionOpcionc != null) {
                programacionOpcionc.getProgramacionOpcioncasesorList().add(programacionOpcioncasesor);
                programacionOpcionc = em.merge(programacionOpcionc);
            }
            if (asesor != null) {
                asesor.getProgramacionOpcioncasesorList().add(programacionOpcioncasesor);
                asesor = em.merge(asesor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacionOpcioncasesor(programacionOpcioncasesor.getProgramacionOpcioncasesorPK()) != null) {
                throw new PreexistingEntityException("ProgramacionOpcioncasesor " + programacionOpcioncasesor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionOpcioncasesor programacionOpcioncasesor) throws NonexistentEntityException, Exception {
        programacionOpcioncasesor.getProgramacionOpcioncasesorPK().setIdAsesor(programacionOpcioncasesor.getAsesor().getIdAsesor());
        programacionOpcioncasesor.getProgramacionOpcioncasesorPK().setIdProgramacionasignatura(programacionOpcioncasesor.getProgramacionOpcionc().getIdProgramacionasignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionOpcioncasesor persistentProgramacionOpcioncasesor = em.find(ProgramacionOpcioncasesor.class, programacionOpcioncasesor.getProgramacionOpcioncasesorPK());
            ProgramacionOpcionc programacionOpcioncOld = persistentProgramacionOpcioncasesor.getProgramacionOpcionc();
            ProgramacionOpcionc programacionOpcioncNew = programacionOpcioncasesor.getProgramacionOpcionc();
            Asesor asesorOld = persistentProgramacionOpcioncasesor.getAsesor();
            Asesor asesorNew = programacionOpcioncasesor.getAsesor();
            if (programacionOpcioncNew != null) {
                programacionOpcioncNew = em.getReference(programacionOpcioncNew.getClass(), programacionOpcioncNew.getIdProgramacionasignatura());
                programacionOpcioncasesor.setProgramacionOpcionc(programacionOpcioncNew);
            }
            if (asesorNew != null) {
                asesorNew = em.getReference(asesorNew.getClass(), asesorNew.getIdAsesor());
                programacionOpcioncasesor.setAsesor(asesorNew);
            }
            programacionOpcioncasesor = em.merge(programacionOpcioncasesor);
            if (programacionOpcioncOld != null && !programacionOpcioncOld.equals(programacionOpcioncNew)) {
                programacionOpcioncOld.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesor);
                programacionOpcioncOld = em.merge(programacionOpcioncOld);
            }
            if (programacionOpcioncNew != null && !programacionOpcioncNew.equals(programacionOpcioncOld)) {
                programacionOpcioncNew.getProgramacionOpcioncasesorList().add(programacionOpcioncasesor);
                programacionOpcioncNew = em.merge(programacionOpcioncNew);
            }
            if (asesorOld != null && !asesorOld.equals(asesorNew)) {
                asesorOld.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesor);
                asesorOld = em.merge(asesorOld);
            }
            if (asesorNew != null && !asesorNew.equals(asesorOld)) {
                asesorNew.getProgramacionOpcioncasesorList().add(programacionOpcioncasesor);
                asesorNew = em.merge(asesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProgramacionOpcioncasesorPK id = programacionOpcioncasesor.getProgramacionOpcioncasesorPK();
                if (findProgramacionOpcioncasesor(id) == null) {
                    throw new NonexistentEntityException("The programacionOpcioncasesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProgramacionOpcioncasesorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionOpcioncasesor programacionOpcioncasesor;
            try {
                programacionOpcioncasesor = em.getReference(ProgramacionOpcioncasesor.class, id);
                programacionOpcioncasesor.getProgramacionOpcioncasesorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionOpcioncasesor with id " + id + " no longer exists.", enfe);
            }
            ProgramacionOpcionc programacionOpcionc = programacionOpcioncasesor.getProgramacionOpcionc();
            if (programacionOpcionc != null) {
                programacionOpcionc.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesor);
                programacionOpcionc = em.merge(programacionOpcionc);
            }
            Asesor asesor = programacionOpcioncasesor.getAsesor();
            if (asesor != null) {
                asesor.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesor);
                asesor = em.merge(asesor);
            }
            em.remove(programacionOpcioncasesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionOpcioncasesor> findProgramacionOpcioncasesorEntities() {
        return findProgramacionOpcioncasesorEntities(true, -1, -1);
    }

    public List<ProgramacionOpcioncasesor> findProgramacionOpcioncasesorEntities(int maxResults, int firstResult) {
        return findProgramacionOpcioncasesorEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionOpcioncasesor> findProgramacionOpcioncasesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProgramacionOpcioncasesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramacionOpcioncasesor findProgramacionOpcioncasesor(ProgramacionOpcioncasesorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionOpcioncasesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionOpcioncasesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProgramacionOpcioncasesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
