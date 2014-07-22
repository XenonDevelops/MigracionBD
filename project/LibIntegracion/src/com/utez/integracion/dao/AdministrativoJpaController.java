/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.entity.Administrativo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.RecursoHumano;
import com.utez.integracion.entity.AdministrativoPlantel;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.HistorialCargo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AdministrativoJpaController implements Serializable {

    public AdministrativoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrativo administrativo) {
        if (administrativo.getAdministrativoPlantelList() == null) {
            administrativo.setAdministrativoPlantelList(new ArrayList<AdministrativoPlantel>());
        }
        if (administrativo.getHistorialCargoList() == null) {
            administrativo.setHistorialCargoList(new ArrayList<HistorialCargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecursoHumano idRecursohumano = administrativo.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano = em.getReference(idRecursohumano.getClass(), idRecursohumano.getIdRecursohumano());
                administrativo.setIdRecursohumano(idRecursohumano);
            }
            List<AdministrativoPlantel> attachedAdministrativoPlantelList = new ArrayList<AdministrativoPlantel>();
            for (AdministrativoPlantel administrativoPlantelListAdministrativoPlantelToAttach : administrativo.getAdministrativoPlantelList()) {
                administrativoPlantelListAdministrativoPlantelToAttach = em.getReference(administrativoPlantelListAdministrativoPlantelToAttach.getClass(), administrativoPlantelListAdministrativoPlantelToAttach.getAdministrativoPlantelPK());
                attachedAdministrativoPlantelList.add(administrativoPlantelListAdministrativoPlantelToAttach);
            }
            administrativo.setAdministrativoPlantelList(attachedAdministrativoPlantelList);
            List<HistorialCargo> attachedHistorialCargoList = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListHistorialCargoToAttach : administrativo.getHistorialCargoList()) {
                historialCargoListHistorialCargoToAttach = em.getReference(historialCargoListHistorialCargoToAttach.getClass(), historialCargoListHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoList.add(historialCargoListHistorialCargoToAttach);
            }
            administrativo.setHistorialCargoList(attachedHistorialCargoList);
            em.persist(administrativo);
            if (idRecursohumano != null) {
                idRecursohumano.getAdministrativoList().add(administrativo);
                idRecursohumano = em.merge(idRecursohumano);
            }
            for (AdministrativoPlantel administrativoPlantelListAdministrativoPlantel : administrativo.getAdministrativoPlantelList()) {
                Administrativo oldAdministrativoOfAdministrativoPlantelListAdministrativoPlantel = administrativoPlantelListAdministrativoPlantel.getAdministrativo();
                administrativoPlantelListAdministrativoPlantel.setAdministrativo(administrativo);
                administrativoPlantelListAdministrativoPlantel = em.merge(administrativoPlantelListAdministrativoPlantel);
                if (oldAdministrativoOfAdministrativoPlantelListAdministrativoPlantel != null) {
                    oldAdministrativoOfAdministrativoPlantelListAdministrativoPlantel.getAdministrativoPlantelList().remove(administrativoPlantelListAdministrativoPlantel);
                    oldAdministrativoOfAdministrativoPlantelListAdministrativoPlantel = em.merge(oldAdministrativoOfAdministrativoPlantelListAdministrativoPlantel);
                }
            }
            for (HistorialCargo historialCargoListHistorialCargo : administrativo.getHistorialCargoList()) {
                Administrativo oldIdAdministrativoOfHistorialCargoListHistorialCargo = historialCargoListHistorialCargo.getIdAdministrativo();
                historialCargoListHistorialCargo.setIdAdministrativo(administrativo);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
                if (oldIdAdministrativoOfHistorialCargoListHistorialCargo != null) {
                    oldIdAdministrativoOfHistorialCargoListHistorialCargo.getHistorialCargoList().remove(historialCargoListHistorialCargo);
                    oldIdAdministrativoOfHistorialCargoListHistorialCargo = em.merge(oldIdAdministrativoOfHistorialCargoListHistorialCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrativo administrativo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo persistentAdministrativo = em.find(Administrativo.class, administrativo.getIdAdministrativo());
            RecursoHumano idRecursohumanoOld = persistentAdministrativo.getIdRecursohumano();
            RecursoHumano idRecursohumanoNew = administrativo.getIdRecursohumano();
            List<AdministrativoPlantel> administrativoPlantelListOld = persistentAdministrativo.getAdministrativoPlantelList();
            List<AdministrativoPlantel> administrativoPlantelListNew = administrativo.getAdministrativoPlantelList();
            List<HistorialCargo> historialCargoListOld = persistentAdministrativo.getHistorialCargoList();
            List<HistorialCargo> historialCargoListNew = administrativo.getHistorialCargoList();
            List<String> illegalOrphanMessages = null;
            for (AdministrativoPlantel administrativoPlantelListOldAdministrativoPlantel : administrativoPlantelListOld) {
                if (!administrativoPlantelListNew.contains(administrativoPlantelListOldAdministrativoPlantel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AdministrativoPlantel " + administrativoPlantelListOldAdministrativoPlantel + " since its administrativo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRecursohumanoNew != null) {
                idRecursohumanoNew = em.getReference(idRecursohumanoNew.getClass(), idRecursohumanoNew.getIdRecursohumano());
                administrativo.setIdRecursohumano(idRecursohumanoNew);
            }
            List<AdministrativoPlantel> attachedAdministrativoPlantelListNew = new ArrayList<AdministrativoPlantel>();
            for (AdministrativoPlantel administrativoPlantelListNewAdministrativoPlantelToAttach : administrativoPlantelListNew) {
                administrativoPlantelListNewAdministrativoPlantelToAttach = em.getReference(administrativoPlantelListNewAdministrativoPlantelToAttach.getClass(), administrativoPlantelListNewAdministrativoPlantelToAttach.getAdministrativoPlantelPK());
                attachedAdministrativoPlantelListNew.add(administrativoPlantelListNewAdministrativoPlantelToAttach);
            }
            administrativoPlantelListNew = attachedAdministrativoPlantelListNew;
            administrativo.setAdministrativoPlantelList(administrativoPlantelListNew);
            List<HistorialCargo> attachedHistorialCargoListNew = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListNewHistorialCargoToAttach : historialCargoListNew) {
                historialCargoListNewHistorialCargoToAttach = em.getReference(historialCargoListNewHistorialCargoToAttach.getClass(), historialCargoListNewHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoListNew.add(historialCargoListNewHistorialCargoToAttach);
            }
            historialCargoListNew = attachedHistorialCargoListNew;
            administrativo.setHistorialCargoList(historialCargoListNew);
            administrativo = em.merge(administrativo);
            if (idRecursohumanoOld != null && !idRecursohumanoOld.equals(idRecursohumanoNew)) {
                idRecursohumanoOld.getAdministrativoList().remove(administrativo);
                idRecursohumanoOld = em.merge(idRecursohumanoOld);
            }
            if (idRecursohumanoNew != null && !idRecursohumanoNew.equals(idRecursohumanoOld)) {
                idRecursohumanoNew.getAdministrativoList().add(administrativo);
                idRecursohumanoNew = em.merge(idRecursohumanoNew);
            }
            for (AdministrativoPlantel administrativoPlantelListNewAdministrativoPlantel : administrativoPlantelListNew) {
                if (!administrativoPlantelListOld.contains(administrativoPlantelListNewAdministrativoPlantel)) {
                    Administrativo oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel = administrativoPlantelListNewAdministrativoPlantel.getAdministrativo();
                    administrativoPlantelListNewAdministrativoPlantel.setAdministrativo(administrativo);
                    administrativoPlantelListNewAdministrativoPlantel = em.merge(administrativoPlantelListNewAdministrativoPlantel);
                    if (oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel != null && !oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel.equals(administrativo)) {
                        oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel.getAdministrativoPlantelList().remove(administrativoPlantelListNewAdministrativoPlantel);
                        oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel = em.merge(oldAdministrativoOfAdministrativoPlantelListNewAdministrativoPlantel);
                    }
                }
            }
            for (HistorialCargo historialCargoListOldHistorialCargo : historialCargoListOld) {
                if (!historialCargoListNew.contains(historialCargoListOldHistorialCargo)) {
                    historialCargoListOldHistorialCargo.setIdAdministrativo(null);
                    historialCargoListOldHistorialCargo = em.merge(historialCargoListOldHistorialCargo);
                }
            }
            for (HistorialCargo historialCargoListNewHistorialCargo : historialCargoListNew) {
                if (!historialCargoListOld.contains(historialCargoListNewHistorialCargo)) {
                    Administrativo oldIdAdministrativoOfHistorialCargoListNewHistorialCargo = historialCargoListNewHistorialCargo.getIdAdministrativo();
                    historialCargoListNewHistorialCargo.setIdAdministrativo(administrativo);
                    historialCargoListNewHistorialCargo = em.merge(historialCargoListNewHistorialCargo);
                    if (oldIdAdministrativoOfHistorialCargoListNewHistorialCargo != null && !oldIdAdministrativoOfHistorialCargoListNewHistorialCargo.equals(administrativo)) {
                        oldIdAdministrativoOfHistorialCargoListNewHistorialCargo.getHistorialCargoList().remove(historialCargoListNewHistorialCargo);
                        oldIdAdministrativoOfHistorialCargoListNewHistorialCargo = em.merge(oldIdAdministrativoOfHistorialCargoListNewHistorialCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = administrativo.getIdAdministrativo();
                if (findAdministrativo(id) == null) {
                    throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.");
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
            Administrativo administrativo;
            try {
                administrativo = em.getReference(Administrativo.class, id);
                administrativo.getIdAdministrativo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrativo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AdministrativoPlantel> administrativoPlantelListOrphanCheck = administrativo.getAdministrativoPlantelList();
            for (AdministrativoPlantel administrativoPlantelListOrphanCheckAdministrativoPlantel : administrativoPlantelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Administrativo (" + administrativo + ") cannot be destroyed since the AdministrativoPlantel " + administrativoPlantelListOrphanCheckAdministrativoPlantel + " in its administrativoPlantelList field has a non-nullable administrativo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            RecursoHumano idRecursohumano = administrativo.getIdRecursohumano();
            if (idRecursohumano != null) {
                idRecursohumano.getAdministrativoList().remove(administrativo);
                idRecursohumano = em.merge(idRecursohumano);
            }
            List<HistorialCargo> historialCargoList = administrativo.getHistorialCargoList();
            for (HistorialCargo historialCargoListHistorialCargo : historialCargoList) {
                historialCargoListHistorialCargo.setIdAdministrativo(null);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
            }
            em.remove(administrativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrativo> findAdministrativoEntities() {
        return findAdministrativoEntities(true, -1, -1);
    }

    public List<Administrativo> findAdministrativoEntities(int maxResults, int firstResult) {
        return findAdministrativoEntities(false, maxResults, firstResult);
    }

    private List<Administrativo> findAdministrativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Administrativo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Administrativo findAdministrativo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministrativoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Administrativo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
