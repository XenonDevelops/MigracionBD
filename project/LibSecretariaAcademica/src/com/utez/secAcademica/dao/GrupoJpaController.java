/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Plantel;
import com.utez.secAcademica.entity.Planestudios;
import com.utez.secAcademica.entity.Periodo;
import com.utez.secAcademica.entity.Opcionestudio;
import com.utez.secAcademica.entity.Calendario;
import com.utez.secAcademica.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getCalendarioList() == null) {
            grupo.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Plantel idplantel = grupo.getIdplantel();
            if (idplantel != null) {
                idplantel = em.getReference(idplantel.getClass(), idplantel.getIdplantel());
                grupo.setIdplantel(idplantel);
            }
            Planestudios rvoe = grupo.getRvoe();
            if (rvoe != null) {
                rvoe = em.getReference(rvoe.getClass(), rvoe.getRvoe());
                grupo.setRvoe(rvoe);
            }
            Periodo periodoingreso = grupo.getPeriodoingreso();
            if (periodoingreso != null) {
                periodoingreso = em.getReference(periodoingreso.getClass(), periodoingreso.getPeriodo());
                grupo.setPeriodoingreso(periodoingreso);
            }
            Opcionestudio opcionestudio = grupo.getOpcionestudio();
            if (opcionestudio != null) {
                opcionestudio = em.getReference(opcionestudio.getClass(), opcionestudio.getOpcionestudio());
                grupo.setOpcionestudio(opcionestudio);
            }
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : grupo.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdcalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            grupo.setCalendarioList(attachedCalendarioList);
            em.persist(grupo);
            if (idplantel != null) {
                idplantel.getGrupoList().add(grupo);
                idplantel = em.merge(idplantel);
            }
            if (rvoe != null) {
                rvoe.getGrupoList().add(grupo);
                rvoe = em.merge(rvoe);
            }
            if (periodoingreso != null) {
                periodoingreso.getGrupoList().add(grupo);
                periodoingreso = em.merge(periodoingreso);
            }
            if (opcionestudio != null) {
                opcionestudio.getGrupoList().add(grupo);
                opcionestudio = em.merge(opcionestudio);
            }
            for (Calendario calendarioListCalendario : grupo.getCalendarioList()) {
                Grupo oldIdgrupoOfCalendarioListCalendario = calendarioListCalendario.getIdgrupo();
                calendarioListCalendario.setIdgrupo(grupo);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldIdgrupoOfCalendarioListCalendario != null) {
                    oldIdgrupoOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldIdgrupoOfCalendarioListCalendario = em.merge(oldIdgrupoOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdgrupo());
            Plantel idplantelOld = persistentGrupo.getIdplantel();
            Plantel idplantelNew = grupo.getIdplantel();
            Planestudios rvoeOld = persistentGrupo.getRvoe();
            Planestudios rvoeNew = grupo.getRvoe();
            Periodo periodoingresoOld = persistentGrupo.getPeriodoingreso();
            Periodo periodoingresoNew = grupo.getPeriodoingreso();
            Opcionestudio opcionestudioOld = persistentGrupo.getOpcionestudio();
            Opcionestudio opcionestudioNew = grupo.getOpcionestudio();
            List<Calendario> calendarioListOld = persistentGrupo.getCalendarioList();
            List<Calendario> calendarioListNew = grupo.getCalendarioList();
            if (idplantelNew != null) {
                idplantelNew = em.getReference(idplantelNew.getClass(), idplantelNew.getIdplantel());
                grupo.setIdplantel(idplantelNew);
            }
            if (rvoeNew != null) {
                rvoeNew = em.getReference(rvoeNew.getClass(), rvoeNew.getRvoe());
                grupo.setRvoe(rvoeNew);
            }
            if (periodoingresoNew != null) {
                periodoingresoNew = em.getReference(periodoingresoNew.getClass(), periodoingresoNew.getPeriodo());
                grupo.setPeriodoingreso(periodoingresoNew);
            }
            if (opcionestudioNew != null) {
                opcionestudioNew = em.getReference(opcionestudioNew.getClass(), opcionestudioNew.getOpcionestudio());
                grupo.setOpcionestudio(opcionestudioNew);
            }
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdcalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            grupo.setCalendarioList(calendarioListNew);
            grupo = em.merge(grupo);
            if (idplantelOld != null && !idplantelOld.equals(idplantelNew)) {
                idplantelOld.getGrupoList().remove(grupo);
                idplantelOld = em.merge(idplantelOld);
            }
            if (idplantelNew != null && !idplantelNew.equals(idplantelOld)) {
                idplantelNew.getGrupoList().add(grupo);
                idplantelNew = em.merge(idplantelNew);
            }
            if (rvoeOld != null && !rvoeOld.equals(rvoeNew)) {
                rvoeOld.getGrupoList().remove(grupo);
                rvoeOld = em.merge(rvoeOld);
            }
            if (rvoeNew != null && !rvoeNew.equals(rvoeOld)) {
                rvoeNew.getGrupoList().add(grupo);
                rvoeNew = em.merge(rvoeNew);
            }
            if (periodoingresoOld != null && !periodoingresoOld.equals(periodoingresoNew)) {
                periodoingresoOld.getGrupoList().remove(grupo);
                periodoingresoOld = em.merge(periodoingresoOld);
            }
            if (periodoingresoNew != null && !periodoingresoNew.equals(periodoingresoOld)) {
                periodoingresoNew.getGrupoList().add(grupo);
                periodoingresoNew = em.merge(periodoingresoNew);
            }
            if (opcionestudioOld != null && !opcionestudioOld.equals(opcionestudioNew)) {
                opcionestudioOld.getGrupoList().remove(grupo);
                opcionestudioOld = em.merge(opcionestudioOld);
            }
            if (opcionestudioNew != null && !opcionestudioNew.equals(opcionestudioOld)) {
                opcionestudioNew.getGrupoList().add(grupo);
                opcionestudioNew = em.merge(opcionestudioNew);
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    calendarioListOldCalendario.setIdgrupo(null);
                    calendarioListOldCalendario = em.merge(calendarioListOldCalendario);
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Grupo oldIdgrupoOfCalendarioListNewCalendario = calendarioListNewCalendario.getIdgrupo();
                    calendarioListNewCalendario.setIdgrupo(grupo);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldIdgrupoOfCalendarioListNewCalendario != null && !oldIdgrupoOfCalendarioListNewCalendario.equals(grupo)) {
                        oldIdgrupoOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldIdgrupoOfCalendarioListNewCalendario = em.merge(oldIdgrupoOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getIdgrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdgrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            Plantel idplantel = grupo.getIdplantel();
            if (idplantel != null) {
                idplantel.getGrupoList().remove(grupo);
                idplantel = em.merge(idplantel);
            }
            Planestudios rvoe = grupo.getRvoe();
            if (rvoe != null) {
                rvoe.getGrupoList().remove(grupo);
                rvoe = em.merge(rvoe);
            }
            Periodo periodoingreso = grupo.getPeriodoingreso();
            if (periodoingreso != null) {
                periodoingreso.getGrupoList().remove(grupo);
                periodoingreso = em.merge(periodoingreso);
            }
            Opcionestudio opcionestudio = grupo.getOpcionestudio();
            if (opcionestudio != null) {
                opcionestudio.getGrupoList().remove(grupo);
                opcionestudio = em.merge(opcionestudio);
            }
            List<Calendario> calendarioList = grupo.getCalendarioList();
            for (Calendario calendarioListCalendario : calendarioList) {
                calendarioListCalendario.setIdgrupo(null);
                calendarioListCalendario = em.merge(calendarioListCalendario);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Grupo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Grupo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
