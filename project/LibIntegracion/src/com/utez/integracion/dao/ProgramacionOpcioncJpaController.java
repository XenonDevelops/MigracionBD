/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.ProgramacionOpcionc;
import com.utez.integracion.entity.AsignaturaOpcionc;
import com.utez.integracion.entity.ProgramacionOpcioncasesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ProgramacionOpcioncJpaController implements Serializable {

    public ProgramacionOpcioncJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProgramacionOpcionc programacionOpcionc) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (programacionOpcionc.getProgramacionOpcioncasesorList() == null) {
            programacionOpcionc.setProgramacionOpcioncasesorList(new ArrayList<ProgramacionOpcioncasesor>());
        }
        List<String> illegalOrphanMessages = null;
        ProgramacionOpcionc programacionOpcionc1OrphanCheck = programacionOpcionc.getProgramacionOpcionc1();
        if (programacionOpcionc1OrphanCheck != null) {
            ProgramacionOpcionc oldProgramacionOpcioncOfProgramacionOpcionc1 = programacionOpcionc1OrphanCheck.getProgramacionOpcionc();
            if (oldProgramacionOpcioncOfProgramacionOpcionc1 != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ProgramacionOpcionc " + programacionOpcionc1OrphanCheck + " already has an item of type ProgramacionOpcionc whose programacionOpcionc1 column cannot be null. Please make another selection for the programacionOpcionc1 field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionOpcionc programacionOpcioncRel = programacionOpcionc.getProgramacionOpcionc();
            if (programacionOpcioncRel != null) {
                programacionOpcioncRel = em.getReference(programacionOpcioncRel.getClass(), programacionOpcioncRel.getIdProgramacionasignatura());
                programacionOpcionc.setProgramacionOpcionc(programacionOpcioncRel);
            }
            ProgramacionOpcionc programacionOpcionc1 = programacionOpcionc.getProgramacionOpcionc1();
            if (programacionOpcionc1 != null) {
                programacionOpcionc1 = em.getReference(programacionOpcionc1.getClass(), programacionOpcionc1.getIdProgramacionasignatura());
                programacionOpcionc.setProgramacionOpcionc1(programacionOpcionc1);
            }
            AsignaturaOpcionc idCalendarioopcionc = programacionOpcionc.getIdCalendarioopcionc();
            if (idCalendarioopcionc != null) {
                idCalendarioopcionc = em.getReference(idCalendarioopcionc.getClass(), idCalendarioopcionc.getIdAsignaturaopcionc());
                programacionOpcionc.setIdCalendarioopcionc(idCalendarioopcionc);
            }
            List<ProgramacionOpcioncasesor> attachedProgramacionOpcioncasesorList = new ArrayList<ProgramacionOpcioncasesor>();
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListProgramacionOpcioncasesorToAttach : programacionOpcionc.getProgramacionOpcioncasesorList()) {
                programacionOpcioncasesorListProgramacionOpcioncasesorToAttach = em.getReference(programacionOpcioncasesorListProgramacionOpcioncasesorToAttach.getClass(), programacionOpcioncasesorListProgramacionOpcioncasesorToAttach.getProgramacionOpcioncasesorPK());
                attachedProgramacionOpcioncasesorList.add(programacionOpcioncasesorListProgramacionOpcioncasesorToAttach);
            }
            programacionOpcionc.setProgramacionOpcioncasesorList(attachedProgramacionOpcioncasesorList);
            em.persist(programacionOpcionc);
            if (programacionOpcioncRel != null) {
                ProgramacionOpcionc oldProgramacionOpcionc1OfProgramacionOpcioncRel = programacionOpcioncRel.getProgramacionOpcionc1();
                if (oldProgramacionOpcionc1OfProgramacionOpcioncRel != null) {
                    oldProgramacionOpcionc1OfProgramacionOpcioncRel.setProgramacionOpcionc(null);
                    oldProgramacionOpcionc1OfProgramacionOpcioncRel = em.merge(oldProgramacionOpcionc1OfProgramacionOpcioncRel);
                }
                programacionOpcioncRel.setProgramacionOpcionc1(programacionOpcionc);
                programacionOpcioncRel = em.merge(programacionOpcioncRel);
            }
            if (programacionOpcionc1 != null) {
                programacionOpcionc1.setProgramacionOpcionc(programacionOpcionc);
                programacionOpcionc1 = em.merge(programacionOpcionc1);
            }
            if (idCalendarioopcionc != null) {
                idCalendarioopcionc.getProgramacionOpcioncList().add(programacionOpcionc);
                idCalendarioopcionc = em.merge(idCalendarioopcionc);
            }
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListProgramacionOpcioncasesor : programacionOpcionc.getProgramacionOpcioncasesorList()) {
                ProgramacionOpcionc oldProgramacionOpcioncOfProgramacionOpcioncasesorListProgramacionOpcioncasesor = programacionOpcioncasesorListProgramacionOpcioncasesor.getProgramacionOpcionc();
                programacionOpcioncasesorListProgramacionOpcioncasesor.setProgramacionOpcionc(programacionOpcionc);
                programacionOpcioncasesorListProgramacionOpcioncasesor = em.merge(programacionOpcioncasesorListProgramacionOpcioncasesor);
                if (oldProgramacionOpcioncOfProgramacionOpcioncasesorListProgramacionOpcioncasesor != null) {
                    oldProgramacionOpcioncOfProgramacionOpcioncasesorListProgramacionOpcioncasesor.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesorListProgramacionOpcioncasesor);
                    oldProgramacionOpcioncOfProgramacionOpcioncasesorListProgramacionOpcioncasesor = em.merge(oldProgramacionOpcioncOfProgramacionOpcioncasesorListProgramacionOpcioncasesor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProgramacionOpcionc(programacionOpcionc.getIdProgramacionasignatura()) != null) {
                throw new PreexistingEntityException("ProgramacionOpcionc " + programacionOpcionc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProgramacionOpcionc programacionOpcionc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProgramacionOpcionc persistentProgramacionOpcionc = em.find(ProgramacionOpcionc.class, programacionOpcionc.getIdProgramacionasignatura());
            ProgramacionOpcionc programacionOpcioncRelOld = persistentProgramacionOpcionc.getProgramacionOpcionc();
            ProgramacionOpcionc programacionOpcioncRelNew = programacionOpcionc.getProgramacionOpcionc();
            ProgramacionOpcionc programacionOpcionc1Old = persistentProgramacionOpcionc.getProgramacionOpcionc1();
            ProgramacionOpcionc programacionOpcionc1New = programacionOpcionc.getProgramacionOpcionc1();
            AsignaturaOpcionc idCalendarioopcioncOld = persistentProgramacionOpcionc.getIdCalendarioopcionc();
            AsignaturaOpcionc idCalendarioopcioncNew = programacionOpcionc.getIdCalendarioopcionc();
            List<ProgramacionOpcioncasesor> programacionOpcioncasesorListOld = persistentProgramacionOpcionc.getProgramacionOpcioncasesorList();
            List<ProgramacionOpcioncasesor> programacionOpcioncasesorListNew = programacionOpcionc.getProgramacionOpcioncasesorList();
            List<String> illegalOrphanMessages = null;
            if (programacionOpcioncRelOld != null && !programacionOpcioncRelOld.equals(programacionOpcioncRelNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ProgramacionOpcionc " + programacionOpcioncRelOld + " since its programacionOpcionc1 field is not nullable.");
            }
            if (programacionOpcionc1New != null && !programacionOpcionc1New.equals(programacionOpcionc1Old)) {
                ProgramacionOpcionc oldProgramacionOpcioncOfProgramacionOpcionc1 = programacionOpcionc1New.getProgramacionOpcionc();
                if (oldProgramacionOpcioncOfProgramacionOpcionc1 != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ProgramacionOpcionc " + programacionOpcionc1New + " already has an item of type ProgramacionOpcionc whose programacionOpcionc1 column cannot be null. Please make another selection for the programacionOpcionc1 field.");
                }
            }
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListOldProgramacionOpcioncasesor : programacionOpcioncasesorListOld) {
                if (!programacionOpcioncasesorListNew.contains(programacionOpcioncasesorListOldProgramacionOpcioncasesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProgramacionOpcioncasesor " + programacionOpcioncasesorListOldProgramacionOpcioncasesor + " since its programacionOpcionc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (programacionOpcioncRelNew != null) {
                programacionOpcioncRelNew = em.getReference(programacionOpcioncRelNew.getClass(), programacionOpcioncRelNew.getIdProgramacionasignatura());
                programacionOpcionc.setProgramacionOpcionc(programacionOpcioncRelNew);
            }
            if (programacionOpcionc1New != null) {
                programacionOpcionc1New = em.getReference(programacionOpcionc1New.getClass(), programacionOpcionc1New.getIdProgramacionasignatura());
                programacionOpcionc.setProgramacionOpcionc1(programacionOpcionc1New);
            }
            if (idCalendarioopcioncNew != null) {
                idCalendarioopcioncNew = em.getReference(idCalendarioopcioncNew.getClass(), idCalendarioopcioncNew.getIdAsignaturaopcionc());
                programacionOpcionc.setIdCalendarioopcionc(idCalendarioopcioncNew);
            }
            List<ProgramacionOpcioncasesor> attachedProgramacionOpcioncasesorListNew = new ArrayList<ProgramacionOpcioncasesor>();
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListNewProgramacionOpcioncasesorToAttach : programacionOpcioncasesorListNew) {
                programacionOpcioncasesorListNewProgramacionOpcioncasesorToAttach = em.getReference(programacionOpcioncasesorListNewProgramacionOpcioncasesorToAttach.getClass(), programacionOpcioncasesorListNewProgramacionOpcioncasesorToAttach.getProgramacionOpcioncasesorPK());
                attachedProgramacionOpcioncasesorListNew.add(programacionOpcioncasesorListNewProgramacionOpcioncasesorToAttach);
            }
            programacionOpcioncasesorListNew = attachedProgramacionOpcioncasesorListNew;
            programacionOpcionc.setProgramacionOpcioncasesorList(programacionOpcioncasesorListNew);
            programacionOpcionc = em.merge(programacionOpcionc);
            if (programacionOpcioncRelNew != null && !programacionOpcioncRelNew.equals(programacionOpcioncRelOld)) {
                ProgramacionOpcionc oldProgramacionOpcionc1OfProgramacionOpcioncRel = programacionOpcioncRelNew.getProgramacionOpcionc1();
                if (oldProgramacionOpcionc1OfProgramacionOpcioncRel != null) {
                    oldProgramacionOpcionc1OfProgramacionOpcioncRel.setProgramacionOpcionc(null);
                    oldProgramacionOpcionc1OfProgramacionOpcioncRel = em.merge(oldProgramacionOpcionc1OfProgramacionOpcioncRel);
                }
                programacionOpcioncRelNew.setProgramacionOpcionc1(programacionOpcionc);
                programacionOpcioncRelNew = em.merge(programacionOpcioncRelNew);
            }
            if (programacionOpcionc1Old != null && !programacionOpcionc1Old.equals(programacionOpcionc1New)) {
                programacionOpcionc1Old.setProgramacionOpcionc(null);
                programacionOpcionc1Old = em.merge(programacionOpcionc1Old);
            }
            if (programacionOpcionc1New != null && !programacionOpcionc1New.equals(programacionOpcionc1Old)) {
                programacionOpcionc1New.setProgramacionOpcionc(programacionOpcionc);
                programacionOpcionc1New = em.merge(programacionOpcionc1New);
            }
            if (idCalendarioopcioncOld != null && !idCalendarioopcioncOld.equals(idCalendarioopcioncNew)) {
                idCalendarioopcioncOld.getProgramacionOpcioncList().remove(programacionOpcionc);
                idCalendarioopcioncOld = em.merge(idCalendarioopcioncOld);
            }
            if (idCalendarioopcioncNew != null && !idCalendarioopcioncNew.equals(idCalendarioopcioncOld)) {
                idCalendarioopcioncNew.getProgramacionOpcioncList().add(programacionOpcionc);
                idCalendarioopcioncNew = em.merge(idCalendarioopcioncNew);
            }
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListNewProgramacionOpcioncasesor : programacionOpcioncasesorListNew) {
                if (!programacionOpcioncasesorListOld.contains(programacionOpcioncasesorListNewProgramacionOpcioncasesor)) {
                    ProgramacionOpcionc oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor = programacionOpcioncasesorListNewProgramacionOpcioncasesor.getProgramacionOpcionc();
                    programacionOpcioncasesorListNewProgramacionOpcioncasesor.setProgramacionOpcionc(programacionOpcionc);
                    programacionOpcioncasesorListNewProgramacionOpcioncasesor = em.merge(programacionOpcioncasesorListNewProgramacionOpcioncasesor);
                    if (oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor != null && !oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor.equals(programacionOpcionc)) {
                        oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor.getProgramacionOpcioncasesorList().remove(programacionOpcioncasesorListNewProgramacionOpcioncasesor);
                        oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor = em.merge(oldProgramacionOpcioncOfProgramacionOpcioncasesorListNewProgramacionOpcioncasesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = programacionOpcionc.getIdProgramacionasignatura();
                if (findProgramacionOpcionc(id) == null) {
                    throw new NonexistentEntityException("The programacionOpcionc with id " + id + " no longer exists.");
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
            ProgramacionOpcionc programacionOpcionc;
            try {
                programacionOpcionc = em.getReference(ProgramacionOpcionc.class, id);
                programacionOpcionc.getIdProgramacionasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programacionOpcionc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            ProgramacionOpcionc programacionOpcioncOrphanCheck = programacionOpcionc.getProgramacionOpcionc();
            if (programacionOpcioncOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProgramacionOpcionc (" + programacionOpcionc + ") cannot be destroyed since the ProgramacionOpcionc " + programacionOpcioncOrphanCheck + " in its programacionOpcionc field has a non-nullable programacionOpcionc1 field.");
            }
            List<ProgramacionOpcioncasesor> programacionOpcioncasesorListOrphanCheck = programacionOpcionc.getProgramacionOpcioncasesorList();
            for (ProgramacionOpcioncasesor programacionOpcioncasesorListOrphanCheckProgramacionOpcioncasesor : programacionOpcioncasesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProgramacionOpcionc (" + programacionOpcionc + ") cannot be destroyed since the ProgramacionOpcioncasesor " + programacionOpcioncasesorListOrphanCheckProgramacionOpcioncasesor + " in its programacionOpcioncasesorList field has a non-nullable programacionOpcionc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProgramacionOpcionc programacionOpcionc1 = programacionOpcionc.getProgramacionOpcionc1();
            if (programacionOpcionc1 != null) {
                programacionOpcionc1.setProgramacionOpcionc(null);
                programacionOpcionc1 = em.merge(programacionOpcionc1);
            }
            AsignaturaOpcionc idCalendarioopcionc = programacionOpcionc.getIdCalendarioopcionc();
            if (idCalendarioopcionc != null) {
                idCalendarioopcionc.getProgramacionOpcioncList().remove(programacionOpcionc);
                idCalendarioopcionc = em.merge(idCalendarioopcionc);
            }
            em.remove(programacionOpcionc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProgramacionOpcionc> findProgramacionOpcioncEntities() {
        return findProgramacionOpcioncEntities(true, -1, -1);
    }

    public List<ProgramacionOpcionc> findProgramacionOpcioncEntities(int maxResults, int firstResult) {
        return findProgramacionOpcioncEntities(false, maxResults, firstResult);
    }

    private List<ProgramacionOpcionc> findProgramacionOpcioncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from ProgramacionOpcionc as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProgramacionOpcionc findProgramacionOpcionc(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProgramacionOpcionc.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramacionOpcioncCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from ProgramacionOpcionc as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
