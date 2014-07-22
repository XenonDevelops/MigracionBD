/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import com.utez.secAcademica.entity.Calendario;
import com.utez.secAcademica.entity.Periodo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class PeriodoJpaController implements Serializable {

    public PeriodoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodo periodo) throws PreexistingEntityException, Exception {
        if (periodo.getGrupoList() == null) {
            periodo.setGrupoList(new ArrayList<Grupo>());
        }
        if (periodo.getCalendarioList() == null) {
            periodo.setCalendarioList(new ArrayList<Calendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : periodo.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdgrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            periodo.setGrupoList(attachedGrupoList);
            List<Calendario> attachedCalendarioList = new ArrayList<Calendario>();
            for (Calendario calendarioListCalendarioToAttach : periodo.getCalendarioList()) {
                calendarioListCalendarioToAttach = em.getReference(calendarioListCalendarioToAttach.getClass(), calendarioListCalendarioToAttach.getIdcalendario());
                attachedCalendarioList.add(calendarioListCalendarioToAttach);
            }
            periodo.setCalendarioList(attachedCalendarioList);
            em.persist(periodo);
            for (Grupo grupoListGrupo : periodo.getGrupoList()) {
                Periodo oldPeriodoingresoOfGrupoListGrupo = grupoListGrupo.getPeriodoingreso();
                grupoListGrupo.setPeriodoingreso(periodo);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldPeriodoingresoOfGrupoListGrupo != null) {
                    oldPeriodoingresoOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldPeriodoingresoOfGrupoListGrupo = em.merge(oldPeriodoingresoOfGrupoListGrupo);
                }
            }
            for (Calendario calendarioListCalendario : periodo.getCalendarioList()) {
                Periodo oldPeriodoOfCalendarioListCalendario = calendarioListCalendario.getPeriodo();
                calendarioListCalendario.setPeriodo(periodo);
                calendarioListCalendario = em.merge(calendarioListCalendario);
                if (oldPeriodoOfCalendarioListCalendario != null) {
                    oldPeriodoOfCalendarioListCalendario.getCalendarioList().remove(calendarioListCalendario);
                    oldPeriodoOfCalendarioListCalendario = em.merge(oldPeriodoOfCalendarioListCalendario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeriodo(periodo.getPeriodo()) != null) {
                throw new PreexistingEntityException("Periodo " + periodo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodo periodo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo persistentPeriodo = em.find(Periodo.class, periodo.getPeriodo());
            List<Grupo> grupoListOld = persistentPeriodo.getGrupoList();
            List<Grupo> grupoListNew = periodo.getGrupoList();
            List<Calendario> calendarioListOld = persistentPeriodo.getCalendarioList();
            List<Calendario> calendarioListNew = periodo.getCalendarioList();
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdgrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            periodo.setGrupoList(grupoListNew);
            List<Calendario> attachedCalendarioListNew = new ArrayList<Calendario>();
            for (Calendario calendarioListNewCalendarioToAttach : calendarioListNew) {
                calendarioListNewCalendarioToAttach = em.getReference(calendarioListNewCalendarioToAttach.getClass(), calendarioListNewCalendarioToAttach.getIdcalendario());
                attachedCalendarioListNew.add(calendarioListNewCalendarioToAttach);
            }
            calendarioListNew = attachedCalendarioListNew;
            periodo.setCalendarioList(calendarioListNew);
            periodo = em.merge(periodo);
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setPeriodoingreso(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Periodo oldPeriodoingresoOfGrupoListNewGrupo = grupoListNewGrupo.getPeriodoingreso();
                    grupoListNewGrupo.setPeriodoingreso(periodo);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldPeriodoingresoOfGrupoListNewGrupo != null && !oldPeriodoingresoOfGrupoListNewGrupo.equals(periodo)) {
                        oldPeriodoingresoOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldPeriodoingresoOfGrupoListNewGrupo = em.merge(oldPeriodoingresoOfGrupoListNewGrupo);
                    }
                }
            }
            for (Calendario calendarioListOldCalendario : calendarioListOld) {
                if (!calendarioListNew.contains(calendarioListOldCalendario)) {
                    calendarioListOldCalendario.setPeriodo(null);
                    calendarioListOldCalendario = em.merge(calendarioListOldCalendario);
                }
            }
            for (Calendario calendarioListNewCalendario : calendarioListNew) {
                if (!calendarioListOld.contains(calendarioListNewCalendario)) {
                    Periodo oldPeriodoOfCalendarioListNewCalendario = calendarioListNewCalendario.getPeriodo();
                    calendarioListNewCalendario.setPeriodo(periodo);
                    calendarioListNewCalendario = em.merge(calendarioListNewCalendario);
                    if (oldPeriodoOfCalendarioListNewCalendario != null && !oldPeriodoOfCalendarioListNewCalendario.equals(periodo)) {
                        oldPeriodoOfCalendarioListNewCalendario.getCalendarioList().remove(calendarioListNewCalendario);
                        oldPeriodoOfCalendarioListNewCalendario = em.merge(oldPeriodoOfCalendarioListNewCalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = periodo.getPeriodo();
                if (findPeriodo(id) == null) {
                    throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo periodo;
            try {
                periodo = em.getReference(Periodo.class, id);
                periodo.getPeriodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
            }
            List<Grupo> grupoList = periodo.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setPeriodoingreso(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            List<Calendario> calendarioList = periodo.getCalendarioList();
            for (Calendario calendarioListCalendario : calendarioList) {
                calendarioListCalendario.setPeriodo(null);
                calendarioListCalendario = em.merge(calendarioListCalendario);
            }
            em.remove(periodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodo> findPeriodoEntities() {
        return findPeriodoEntities(true, -1, -1);
    }

    public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
        return findPeriodoEntities(false, maxResults, firstResult);
    }

    private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Periodo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Periodo findPeriodo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Periodo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
