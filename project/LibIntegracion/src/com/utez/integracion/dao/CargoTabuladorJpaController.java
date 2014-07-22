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
import com.utez.integracion.entity.Tabulador;
import com.utez.integracion.entity.AreaCargo;
import com.utez.integracion.entity.CargoTabulador;
import com.utez.integracion.entity.CargoTabuladorPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CargoTabuladorJpaController implements Serializable {

    public CargoTabuladorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargoTabulador cargoTabulador) throws PreexistingEntityException, Exception {
        if (cargoTabulador.getCargoTabuladorPK() == null) {
            cargoTabulador.setCargoTabuladorPK(new CargoTabuladorPK());
        }
        cargoTabulador.getCargoTabuladorPK().setIdCargoarea(cargoTabulador.getAreaCargo().getIdCargoarea());
        cargoTabulador.getCargoTabuladorPK().setIdTabulador(cargoTabulador.getTabulador().getIdTabulador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabulador tabulador = cargoTabulador.getTabulador();
            if (tabulador != null) {
                tabulador = em.getReference(tabulador.getClass(), tabulador.getIdTabulador());
                cargoTabulador.setTabulador(tabulador);
            }
            AreaCargo areaCargo = cargoTabulador.getAreaCargo();
            if (areaCargo != null) {
                areaCargo = em.getReference(areaCargo.getClass(), areaCargo.getIdCargoarea());
                cargoTabulador.setAreaCargo(areaCargo);
            }
            em.persist(cargoTabulador);
            if (tabulador != null) {
                tabulador.getCargoTabuladorList().add(cargoTabulador);
                tabulador = em.merge(tabulador);
            }
            if (areaCargo != null) {
                areaCargo.getCargoTabuladorList().add(cargoTabulador);
                areaCargo = em.merge(areaCargo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCargoTabulador(cargoTabulador.getCargoTabuladorPK()) != null) {
                throw new PreexistingEntityException("CargoTabulador " + cargoTabulador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargoTabulador cargoTabulador) throws NonexistentEntityException, Exception {
        cargoTabulador.getCargoTabuladorPK().setIdCargoarea(cargoTabulador.getAreaCargo().getIdCargoarea());
        cargoTabulador.getCargoTabuladorPK().setIdTabulador(cargoTabulador.getTabulador().getIdTabulador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoTabulador persistentCargoTabulador = em.find(CargoTabulador.class, cargoTabulador.getCargoTabuladorPK());
            Tabulador tabuladorOld = persistentCargoTabulador.getTabulador();
            Tabulador tabuladorNew = cargoTabulador.getTabulador();
            AreaCargo areaCargoOld = persistentCargoTabulador.getAreaCargo();
            AreaCargo areaCargoNew = cargoTabulador.getAreaCargo();
            if (tabuladorNew != null) {
                tabuladorNew = em.getReference(tabuladorNew.getClass(), tabuladorNew.getIdTabulador());
                cargoTabulador.setTabulador(tabuladorNew);
            }
            if (areaCargoNew != null) {
                areaCargoNew = em.getReference(areaCargoNew.getClass(), areaCargoNew.getIdCargoarea());
                cargoTabulador.setAreaCargo(areaCargoNew);
            }
            cargoTabulador = em.merge(cargoTabulador);
            if (tabuladorOld != null && !tabuladorOld.equals(tabuladorNew)) {
                tabuladorOld.getCargoTabuladorList().remove(cargoTabulador);
                tabuladorOld = em.merge(tabuladorOld);
            }
            if (tabuladorNew != null && !tabuladorNew.equals(tabuladorOld)) {
                tabuladorNew.getCargoTabuladorList().add(cargoTabulador);
                tabuladorNew = em.merge(tabuladorNew);
            }
            if (areaCargoOld != null && !areaCargoOld.equals(areaCargoNew)) {
                areaCargoOld.getCargoTabuladorList().remove(cargoTabulador);
                areaCargoOld = em.merge(areaCargoOld);
            }
            if (areaCargoNew != null && !areaCargoNew.equals(areaCargoOld)) {
                areaCargoNew.getCargoTabuladorList().add(cargoTabulador);
                areaCargoNew = em.merge(areaCargoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CargoTabuladorPK id = cargoTabulador.getCargoTabuladorPK();
                if (findCargoTabulador(id) == null) {
                    throw new NonexistentEntityException("The cargoTabulador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CargoTabuladorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoTabulador cargoTabulador;
            try {
                cargoTabulador = em.getReference(CargoTabulador.class, id);
                cargoTabulador.getCargoTabuladorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargoTabulador with id " + id + " no longer exists.", enfe);
            }
            Tabulador tabulador = cargoTabulador.getTabulador();
            if (tabulador != null) {
                tabulador.getCargoTabuladorList().remove(cargoTabulador);
                tabulador = em.merge(tabulador);
            }
            AreaCargo areaCargo = cargoTabulador.getAreaCargo();
            if (areaCargo != null) {
                areaCargo.getCargoTabuladorList().remove(cargoTabulador);
                areaCargo = em.merge(areaCargo);
            }
            em.remove(cargoTabulador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargoTabulador> findCargoTabuladorEntities() {
        return findCargoTabuladorEntities(true, -1, -1);
    }

    public List<CargoTabulador> findCargoTabuladorEntities(int maxResults, int firstResult) {
        return findCargoTabuladorEntities(false, maxResults, firstResult);
    }

    private List<CargoTabulador> findCargoTabuladorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CargoTabulador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CargoTabulador findCargoTabulador(CargoTabuladorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargoTabulador.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoTabuladorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CargoTabulador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
