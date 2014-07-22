/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secAcademica.entity.Asesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Recursohumano;
import com.utez.secAcademica.entity.Planestudios;
import java.util.ArrayList;
import java.util.List;
import com.utez.secAcademica.entity.Asesoriaasignatura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesorJpaController implements Serializable {

    public AsesorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesor asesor) {
        if (asesor.getPlanestudiosList() == null) {
            asesor.setPlanestudiosList(new ArrayList<Planestudios>());
        }
        if (asesor.getAsesoriaasignaturaList() == null) {
            asesor.setAsesoriaasignaturaList(new ArrayList<Asesoriaasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recursohumano idrecursohumano = asesor.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano = em.getReference(idrecursohumano.getClass(), idrecursohumano.getIdrecursohumano());
                asesor.setIdrecursohumano(idrecursohumano);
            }
            List<Planestudios> attachedPlanestudiosList = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListPlanestudiosToAttach : asesor.getPlanestudiosList()) {
                planestudiosListPlanestudiosToAttach = em.getReference(planestudiosListPlanestudiosToAttach.getClass(), planestudiosListPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosList.add(planestudiosListPlanestudiosToAttach);
            }
            asesor.setPlanestudiosList(attachedPlanestudiosList);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaList = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignaturaToAttach : asesor.getAsesoriaasignaturaList()) {
                asesoriaasignaturaListAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaList.add(asesoriaasignaturaListAsesoriaasignaturaToAttach);
            }
            asesor.setAsesoriaasignaturaList(attachedAsesoriaasignaturaList);
            em.persist(asesor);
            if (idrecursohumano != null) {
                idrecursohumano.getAsesorList().add(asesor);
                idrecursohumano = em.merge(idrecursohumano);
            }
            for (Planestudios planestudiosListPlanestudios : asesor.getPlanestudiosList()) {
                planestudiosListPlanestudios.getAsesorList().add(asesor);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
            }
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesor.getAsesoriaasignaturaList()) {
                Asesor oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura = asesoriaasignaturaListAsesoriaasignatura.getIdasesor();
                asesoriaasignaturaListAsesoriaasignatura.setIdasesor(asesor);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
                if (oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura != null) {
                    oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListAsesoriaasignatura);
                    oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura = em.merge(oldIdasesorOfAsesoriaasignaturaListAsesoriaasignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesor asesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesor persistentAsesor = em.find(Asesor.class, asesor.getIdasesor());
            Recursohumano idrecursohumanoOld = persistentAsesor.getIdrecursohumano();
            Recursohumano idrecursohumanoNew = asesor.getIdrecursohumano();
            List<Planestudios> planestudiosListOld = persistentAsesor.getPlanestudiosList();
            List<Planestudios> planestudiosListNew = asesor.getPlanestudiosList();
            List<Asesoriaasignatura> asesoriaasignaturaListOld = persistentAsesor.getAsesoriaasignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListNew = asesor.getAsesoriaasignaturaList();
            if (idrecursohumanoNew != null) {
                idrecursohumanoNew = em.getReference(idrecursohumanoNew.getClass(), idrecursohumanoNew.getIdrecursohumano());
                asesor.setIdrecursohumano(idrecursohumanoNew);
            }
            List<Planestudios> attachedPlanestudiosListNew = new ArrayList<Planestudios>();
            for (Planestudios planestudiosListNewPlanestudiosToAttach : planestudiosListNew) {
                planestudiosListNewPlanestudiosToAttach = em.getReference(planestudiosListNewPlanestudiosToAttach.getClass(), planestudiosListNewPlanestudiosToAttach.getRvoe());
                attachedPlanestudiosListNew.add(planestudiosListNewPlanestudiosToAttach);
            }
            planestudiosListNew = attachedPlanestudiosListNew;
            asesor.setPlanestudiosList(planestudiosListNew);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaListNew = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignaturaToAttach : asesoriaasignaturaListNew) {
                asesoriaasignaturaListNewAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaListNew.add(asesoriaasignaturaListNewAsesoriaasignaturaToAttach);
            }
            asesoriaasignaturaListNew = attachedAsesoriaasignaturaListNew;
            asesor.setAsesoriaasignaturaList(asesoriaasignaturaListNew);
            asesor = em.merge(asesor);
            if (idrecursohumanoOld != null && !idrecursohumanoOld.equals(idrecursohumanoNew)) {
                idrecursohumanoOld.getAsesorList().remove(asesor);
                idrecursohumanoOld = em.merge(idrecursohumanoOld);
            }
            if (idrecursohumanoNew != null && !idrecursohumanoNew.equals(idrecursohumanoOld)) {
                idrecursohumanoNew.getAsesorList().add(asesor);
                idrecursohumanoNew = em.merge(idrecursohumanoNew);
            }
            for (Planestudios planestudiosListOldPlanestudios : planestudiosListOld) {
                if (!planestudiosListNew.contains(planestudiosListOldPlanestudios)) {
                    planestudiosListOldPlanestudios.getAsesorList().remove(asesor);
                    planestudiosListOldPlanestudios = em.merge(planestudiosListOldPlanestudios);
                }
            }
            for (Planestudios planestudiosListNewPlanestudios : planestudiosListNew) {
                if (!planestudiosListOld.contains(planestudiosListNewPlanestudios)) {
                    planestudiosListNewPlanestudios.getAsesorList().add(asesor);
                    planestudiosListNewPlanestudios = em.merge(planestudiosListNewPlanestudios);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListOldAsesoriaasignatura : asesoriaasignaturaListOld) {
                if (!asesoriaasignaturaListNew.contains(asesoriaasignaturaListOldAsesoriaasignatura)) {
                    asesoriaasignaturaListOldAsesoriaasignatura.setIdasesor(null);
                    asesoriaasignaturaListOldAsesoriaasignatura = em.merge(asesoriaasignaturaListOldAsesoriaasignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignatura : asesoriaasignaturaListNew) {
                if (!asesoriaasignaturaListOld.contains(asesoriaasignaturaListNewAsesoriaasignatura)) {
                    Asesor oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura = asesoriaasignaturaListNewAsesoriaasignatura.getIdasesor();
                    asesoriaasignaturaListNewAsesoriaasignatura.setIdasesor(asesor);
                    asesoriaasignaturaListNewAsesoriaasignatura = em.merge(asesoriaasignaturaListNewAsesoriaasignatura);
                    if (oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura != null && !oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura.equals(asesor)) {
                        oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListNewAsesoriaasignatura);
                        oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura = em.merge(oldIdasesorOfAsesoriaasignaturaListNewAsesoriaasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesor.getIdasesor();
                if (findAsesor(id) == null) {
                    throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.");
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
            Asesor asesor;
            try {
                asesor = em.getReference(Asesor.class, id);
                asesor.getIdasesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesor with id " + id + " no longer exists.", enfe);
            }
            Recursohumano idrecursohumano = asesor.getIdrecursohumano();
            if (idrecursohumano != null) {
                idrecursohumano.getAsesorList().remove(asesor);
                idrecursohumano = em.merge(idrecursohumano);
            }
            List<Planestudios> planestudiosList = asesor.getPlanestudiosList();
            for (Planestudios planestudiosListPlanestudios : planestudiosList) {
                planestudiosListPlanestudios.getAsesorList().remove(asesor);
                planestudiosListPlanestudios = em.merge(planestudiosListPlanestudios);
            }
            List<Asesoriaasignatura> asesoriaasignaturaList = asesor.getAsesoriaasignaturaList();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesoriaasignaturaList) {
                asesoriaasignaturaListAsesoriaasignatura.setIdasesor(null);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
            }
            em.remove(asesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesor> findAsesorEntities() {
        return findAsesorEntities(true, -1, -1);
    }

    public List<Asesor> findAsesorEntities(int maxResults, int firstResult) {
        return findAsesorEntities(false, maxResults, firstResult);
    }

    private List<Asesor> findAsesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesor as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesor findAsesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesor as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
