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
import com.utez.integracion.entity.TabuladorConcepto;
import com.utez.integracion.entity.CargoTabulador;
import com.utez.integracion.entity.Tabulador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TabuladorJpaController implements Serializable {

    public TabuladorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabulador tabulador) {
        if (tabulador.getCargoTabuladorList() == null) {
            tabulador.setCargoTabuladorList(new ArrayList<CargoTabulador>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TabuladorConcepto idTabuladorconcepto = tabulador.getIdTabuladorconcepto();
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto = em.getReference(idTabuladorconcepto.getClass(), idTabuladorconcepto.getIdTabuladorconcepto());
                tabulador.setIdTabuladorconcepto(idTabuladorconcepto);
            }
            List<CargoTabulador> attachedCargoTabuladorList = new ArrayList<CargoTabulador>();
            for (CargoTabulador cargoTabuladorListCargoTabuladorToAttach : tabulador.getCargoTabuladorList()) {
                cargoTabuladorListCargoTabuladorToAttach = em.getReference(cargoTabuladorListCargoTabuladorToAttach.getClass(), cargoTabuladorListCargoTabuladorToAttach.getCargoTabuladorPK());
                attachedCargoTabuladorList.add(cargoTabuladorListCargoTabuladorToAttach);
            }
            tabulador.setCargoTabuladorList(attachedCargoTabuladorList);
            em.persist(tabulador);
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto.getTabuladorList().add(tabulador);
                idTabuladorconcepto = em.merge(idTabuladorconcepto);
            }
            for (CargoTabulador cargoTabuladorListCargoTabulador : tabulador.getCargoTabuladorList()) {
                Tabulador oldTabuladorOfCargoTabuladorListCargoTabulador = cargoTabuladorListCargoTabulador.getTabulador();
                cargoTabuladorListCargoTabulador.setTabulador(tabulador);
                cargoTabuladorListCargoTabulador = em.merge(cargoTabuladorListCargoTabulador);
                if (oldTabuladorOfCargoTabuladorListCargoTabulador != null) {
                    oldTabuladorOfCargoTabuladorListCargoTabulador.getCargoTabuladorList().remove(cargoTabuladorListCargoTabulador);
                    oldTabuladorOfCargoTabuladorListCargoTabulador = em.merge(oldTabuladorOfCargoTabuladorListCargoTabulador);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabulador tabulador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabulador persistentTabulador = em.find(Tabulador.class, tabulador.getIdTabulador());
            TabuladorConcepto idTabuladorconceptoOld = persistentTabulador.getIdTabuladorconcepto();
            TabuladorConcepto idTabuladorconceptoNew = tabulador.getIdTabuladorconcepto();
            List<CargoTabulador> cargoTabuladorListOld = persistentTabulador.getCargoTabuladorList();
            List<CargoTabulador> cargoTabuladorListNew = tabulador.getCargoTabuladorList();
            List<String> illegalOrphanMessages = null;
            for (CargoTabulador cargoTabuladorListOldCargoTabulador : cargoTabuladorListOld) {
                if (!cargoTabuladorListNew.contains(cargoTabuladorListOldCargoTabulador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CargoTabulador " + cargoTabuladorListOldCargoTabulador + " since its tabulador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTabuladorconceptoNew != null) {
                idTabuladorconceptoNew = em.getReference(idTabuladorconceptoNew.getClass(), idTabuladorconceptoNew.getIdTabuladorconcepto());
                tabulador.setIdTabuladorconcepto(idTabuladorconceptoNew);
            }
            List<CargoTabulador> attachedCargoTabuladorListNew = new ArrayList<CargoTabulador>();
            for (CargoTabulador cargoTabuladorListNewCargoTabuladorToAttach : cargoTabuladorListNew) {
                cargoTabuladorListNewCargoTabuladorToAttach = em.getReference(cargoTabuladorListNewCargoTabuladorToAttach.getClass(), cargoTabuladorListNewCargoTabuladorToAttach.getCargoTabuladorPK());
                attachedCargoTabuladorListNew.add(cargoTabuladorListNewCargoTabuladorToAttach);
            }
            cargoTabuladorListNew = attachedCargoTabuladorListNew;
            tabulador.setCargoTabuladorList(cargoTabuladorListNew);
            tabulador = em.merge(tabulador);
            if (idTabuladorconceptoOld != null && !idTabuladorconceptoOld.equals(idTabuladorconceptoNew)) {
                idTabuladorconceptoOld.getTabuladorList().remove(tabulador);
                idTabuladorconceptoOld = em.merge(idTabuladorconceptoOld);
            }
            if (idTabuladorconceptoNew != null && !idTabuladorconceptoNew.equals(idTabuladorconceptoOld)) {
                idTabuladorconceptoNew.getTabuladorList().add(tabulador);
                idTabuladorconceptoNew = em.merge(idTabuladorconceptoNew);
            }
            for (CargoTabulador cargoTabuladorListNewCargoTabulador : cargoTabuladorListNew) {
                if (!cargoTabuladorListOld.contains(cargoTabuladorListNewCargoTabulador)) {
                    Tabulador oldTabuladorOfCargoTabuladorListNewCargoTabulador = cargoTabuladorListNewCargoTabulador.getTabulador();
                    cargoTabuladorListNewCargoTabulador.setTabulador(tabulador);
                    cargoTabuladorListNewCargoTabulador = em.merge(cargoTabuladorListNewCargoTabulador);
                    if (oldTabuladorOfCargoTabuladorListNewCargoTabulador != null && !oldTabuladorOfCargoTabuladorListNewCargoTabulador.equals(tabulador)) {
                        oldTabuladorOfCargoTabuladorListNewCargoTabulador.getCargoTabuladorList().remove(cargoTabuladorListNewCargoTabulador);
                        oldTabuladorOfCargoTabuladorListNewCargoTabulador = em.merge(oldTabuladorOfCargoTabuladorListNewCargoTabulador);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tabulador.getIdTabulador();
                if (findTabulador(id) == null) {
                    throw new NonexistentEntityException("The tabulador with id " + id + " no longer exists.");
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
            Tabulador tabulador;
            try {
                tabulador = em.getReference(Tabulador.class, id);
                tabulador.getIdTabulador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabulador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CargoTabulador> cargoTabuladorListOrphanCheck = tabulador.getCargoTabuladorList();
            for (CargoTabulador cargoTabuladorListOrphanCheckCargoTabulador : cargoTabuladorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tabulador (" + tabulador + ") cannot be destroyed since the CargoTabulador " + cargoTabuladorListOrphanCheckCargoTabulador + " in its cargoTabuladorList field has a non-nullable tabulador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TabuladorConcepto idTabuladorconcepto = tabulador.getIdTabuladorconcepto();
            if (idTabuladorconcepto != null) {
                idTabuladorconcepto.getTabuladorList().remove(tabulador);
                idTabuladorconcepto = em.merge(idTabuladorconcepto);
            }
            em.remove(tabulador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabulador> findTabuladorEntities() {
        return findTabuladorEntities(true, -1, -1);
    }

    public List<Tabulador> findTabuladorEntities(int maxResults, int firstResult) {
        return findTabuladorEntities(false, maxResults, firstResult);
    }

    private List<Tabulador> findTabuladorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Tabulador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tabulador findTabulador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabulador.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabuladorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Tabulador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
