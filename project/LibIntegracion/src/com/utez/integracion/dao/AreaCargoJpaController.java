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
import com.utez.integracion.entity.Cargo;
import com.utez.integracion.entity.Area;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.HistorialCargo;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.CargoTabulador;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AreaCargoJpaController implements Serializable {

    public AreaCargoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaCargo areaCargo) {
        if (areaCargo.getHistorialCargoList() == null) {
            areaCargo.setHistorialCargoList(new ArrayList<HistorialCargo>());
        }
        if (areaCargo.getCargoTabuladorList() == null) {
            areaCargo.setCargoTabuladorList(new ArrayList<CargoTabulador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo idCargo = areaCargo.getIdCargo();
            if (idCargo != null) {
                idCargo = em.getReference(idCargo.getClass(), idCargo.getIdCargo());
                areaCargo.setIdCargo(idCargo);
            }
            Area idArea = areaCargo.getIdArea();
            if (idArea != null) {
                idArea = em.getReference(idArea.getClass(), idArea.getIdArea());
                areaCargo.setIdArea(idArea);
            }
            List<HistorialCargo> attachedHistorialCargoList = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListHistorialCargoToAttach : areaCargo.getHistorialCargoList()) {
                historialCargoListHistorialCargoToAttach = em.getReference(historialCargoListHistorialCargoToAttach.getClass(), historialCargoListHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoList.add(historialCargoListHistorialCargoToAttach);
            }
            areaCargo.setHistorialCargoList(attachedHistorialCargoList);
            List<CargoTabulador> attachedCargoTabuladorList = new ArrayList<CargoTabulador>();
            for (CargoTabulador cargoTabuladorListCargoTabuladorToAttach : areaCargo.getCargoTabuladorList()) {
                cargoTabuladorListCargoTabuladorToAttach = em.getReference(cargoTabuladorListCargoTabuladorToAttach.getClass(), cargoTabuladorListCargoTabuladorToAttach.getCargoTabuladorPK());
                attachedCargoTabuladorList.add(cargoTabuladorListCargoTabuladorToAttach);
            }
            areaCargo.setCargoTabuladorList(attachedCargoTabuladorList);
            em.persist(areaCargo);
            if (idCargo != null) {
                idCargo.getAreaCargoList().add(areaCargo);
                idCargo = em.merge(idCargo);
            }
            if (idArea != null) {
                idArea.getAreaCargoList().add(areaCargo);
                idArea = em.merge(idArea);
            }
            for (HistorialCargo historialCargoListHistorialCargo : areaCargo.getHistorialCargoList()) {
                AreaCargo oldIdCargoareaOfHistorialCargoListHistorialCargo = historialCargoListHistorialCargo.getIdCargoarea();
                historialCargoListHistorialCargo.setIdCargoarea(areaCargo);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
                if (oldIdCargoareaOfHistorialCargoListHistorialCargo != null) {
                    oldIdCargoareaOfHistorialCargoListHistorialCargo.getHistorialCargoList().remove(historialCargoListHistorialCargo);
                    oldIdCargoareaOfHistorialCargoListHistorialCargo = em.merge(oldIdCargoareaOfHistorialCargoListHistorialCargo);
                }
            }
            for (CargoTabulador cargoTabuladorListCargoTabulador : areaCargo.getCargoTabuladorList()) {
                AreaCargo oldAreaCargoOfCargoTabuladorListCargoTabulador = cargoTabuladorListCargoTabulador.getAreaCargo();
                cargoTabuladorListCargoTabulador.setAreaCargo(areaCargo);
                cargoTabuladorListCargoTabulador = em.merge(cargoTabuladorListCargoTabulador);
                if (oldAreaCargoOfCargoTabuladorListCargoTabulador != null) {
                    oldAreaCargoOfCargoTabuladorListCargoTabulador.getCargoTabuladorList().remove(cargoTabuladorListCargoTabulador);
                    oldAreaCargoOfCargoTabuladorListCargoTabulador = em.merge(oldAreaCargoOfCargoTabuladorListCargoTabulador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaCargo areaCargo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaCargo persistentAreaCargo = em.find(AreaCargo.class, areaCargo.getIdCargoarea());
            Cargo idCargoOld = persistentAreaCargo.getIdCargo();
            Cargo idCargoNew = areaCargo.getIdCargo();
            Area idAreaOld = persistentAreaCargo.getIdArea();
            Area idAreaNew = areaCargo.getIdArea();
            List<HistorialCargo> historialCargoListOld = persistentAreaCargo.getHistorialCargoList();
            List<HistorialCargo> historialCargoListNew = areaCargo.getHistorialCargoList();
            List<CargoTabulador> cargoTabuladorListOld = persistentAreaCargo.getCargoTabuladorList();
            List<CargoTabulador> cargoTabuladorListNew = areaCargo.getCargoTabuladorList();
            List<String> illegalOrphanMessages = null;
            for (CargoTabulador cargoTabuladorListOldCargoTabulador : cargoTabuladorListOld) {
                if (!cargoTabuladorListNew.contains(cargoTabuladorListOldCargoTabulador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CargoTabulador " + cargoTabuladorListOldCargoTabulador + " since its areaCargo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCargoNew != null) {
                idCargoNew = em.getReference(idCargoNew.getClass(), idCargoNew.getIdCargo());
                areaCargo.setIdCargo(idCargoNew);
            }
            if (idAreaNew != null) {
                idAreaNew = em.getReference(idAreaNew.getClass(), idAreaNew.getIdArea());
                areaCargo.setIdArea(idAreaNew);
            }
            List<HistorialCargo> attachedHistorialCargoListNew = new ArrayList<HistorialCargo>();
            for (HistorialCargo historialCargoListNewHistorialCargoToAttach : historialCargoListNew) {
                historialCargoListNewHistorialCargoToAttach = em.getReference(historialCargoListNewHistorialCargoToAttach.getClass(), historialCargoListNewHistorialCargoToAttach.getIdHistorialcargo());
                attachedHistorialCargoListNew.add(historialCargoListNewHistorialCargoToAttach);
            }
            historialCargoListNew = attachedHistorialCargoListNew;
            areaCargo.setHistorialCargoList(historialCargoListNew);
            List<CargoTabulador> attachedCargoTabuladorListNew = new ArrayList<CargoTabulador>();
            for (CargoTabulador cargoTabuladorListNewCargoTabuladorToAttach : cargoTabuladorListNew) {
                cargoTabuladorListNewCargoTabuladorToAttach = em.getReference(cargoTabuladorListNewCargoTabuladorToAttach.getClass(), cargoTabuladorListNewCargoTabuladorToAttach.getCargoTabuladorPK());
                attachedCargoTabuladorListNew.add(cargoTabuladorListNewCargoTabuladorToAttach);
            }
            cargoTabuladorListNew = attachedCargoTabuladorListNew;
            areaCargo.setCargoTabuladorList(cargoTabuladorListNew);
            areaCargo = em.merge(areaCargo);
            if (idCargoOld != null && !idCargoOld.equals(idCargoNew)) {
                idCargoOld.getAreaCargoList().remove(areaCargo);
                idCargoOld = em.merge(idCargoOld);
            }
            if (idCargoNew != null && !idCargoNew.equals(idCargoOld)) {
                idCargoNew.getAreaCargoList().add(areaCargo);
                idCargoNew = em.merge(idCargoNew);
            }
            if (idAreaOld != null && !idAreaOld.equals(idAreaNew)) {
                idAreaOld.getAreaCargoList().remove(areaCargo);
                idAreaOld = em.merge(idAreaOld);
            }
            if (idAreaNew != null && !idAreaNew.equals(idAreaOld)) {
                idAreaNew.getAreaCargoList().add(areaCargo);
                idAreaNew = em.merge(idAreaNew);
            }
            for (HistorialCargo historialCargoListOldHistorialCargo : historialCargoListOld) {
                if (!historialCargoListNew.contains(historialCargoListOldHistorialCargo)) {
                    historialCargoListOldHistorialCargo.setIdCargoarea(null);
                    historialCargoListOldHistorialCargo = em.merge(historialCargoListOldHistorialCargo);
                }
            }
            for (HistorialCargo historialCargoListNewHistorialCargo : historialCargoListNew) {
                if (!historialCargoListOld.contains(historialCargoListNewHistorialCargo)) {
                    AreaCargo oldIdCargoareaOfHistorialCargoListNewHistorialCargo = historialCargoListNewHistorialCargo.getIdCargoarea();
                    historialCargoListNewHistorialCargo.setIdCargoarea(areaCargo);
                    historialCargoListNewHistorialCargo = em.merge(historialCargoListNewHistorialCargo);
                    if (oldIdCargoareaOfHistorialCargoListNewHistorialCargo != null && !oldIdCargoareaOfHistorialCargoListNewHistorialCargo.equals(areaCargo)) {
                        oldIdCargoareaOfHistorialCargoListNewHistorialCargo.getHistorialCargoList().remove(historialCargoListNewHistorialCargo);
                        oldIdCargoareaOfHistorialCargoListNewHistorialCargo = em.merge(oldIdCargoareaOfHistorialCargoListNewHistorialCargo);
                    }
                }
            }
            for (CargoTabulador cargoTabuladorListNewCargoTabulador : cargoTabuladorListNew) {
                if (!cargoTabuladorListOld.contains(cargoTabuladorListNewCargoTabulador)) {
                    AreaCargo oldAreaCargoOfCargoTabuladorListNewCargoTabulador = cargoTabuladorListNewCargoTabulador.getAreaCargo();
                    cargoTabuladorListNewCargoTabulador.setAreaCargo(areaCargo);
                    cargoTabuladorListNewCargoTabulador = em.merge(cargoTabuladorListNewCargoTabulador);
                    if (oldAreaCargoOfCargoTabuladorListNewCargoTabulador != null && !oldAreaCargoOfCargoTabuladorListNewCargoTabulador.equals(areaCargo)) {
                        oldAreaCargoOfCargoTabuladorListNewCargoTabulador.getCargoTabuladorList().remove(cargoTabuladorListNewCargoTabulador);
                        oldAreaCargoOfCargoTabuladorListNewCargoTabulador = em.merge(oldAreaCargoOfCargoTabuladorListNewCargoTabulador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = areaCargo.getIdCargoarea();
                if (findAreaCargo(id) == null) {
                    throw new NonexistentEntityException("The areaCargo with id " + id + " no longer exists.");
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
            AreaCargo areaCargo;
            try {
                areaCargo = em.getReference(AreaCargo.class, id);
                areaCargo.getIdCargoarea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaCargo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CargoTabulador> cargoTabuladorListOrphanCheck = areaCargo.getCargoTabuladorList();
            for (CargoTabulador cargoTabuladorListOrphanCheckCargoTabulador : cargoTabuladorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AreaCargo (" + areaCargo + ") cannot be destroyed since the CargoTabulador " + cargoTabuladorListOrphanCheckCargoTabulador + " in its cargoTabuladorList field has a non-nullable areaCargo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo idCargo = areaCargo.getIdCargo();
            if (idCargo != null) {
                idCargo.getAreaCargoList().remove(areaCargo);
                idCargo = em.merge(idCargo);
            }
            Area idArea = areaCargo.getIdArea();
            if (idArea != null) {
                idArea.getAreaCargoList().remove(areaCargo);
                idArea = em.merge(idArea);
            }
            List<HistorialCargo> historialCargoList = areaCargo.getHistorialCargoList();
            for (HistorialCargo historialCargoListHistorialCargo : historialCargoList) {
                historialCargoListHistorialCargo.setIdCargoarea(null);
                historialCargoListHistorialCargo = em.merge(historialCargoListHistorialCargo);
            }
            em.remove(areaCargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaCargo> findAreaCargoEntities() {
        return findAreaCargoEntities(true, -1, -1);
    }

    public List<AreaCargo> findAreaCargoEntities(int maxResults, int firstResult) {
        return findAreaCargoEntities(false, maxResults, firstResult);
    }

    private List<AreaCargo> findAreaCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AreaCargo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AreaCargo findAreaCargo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaCargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCargoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AreaCargo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
