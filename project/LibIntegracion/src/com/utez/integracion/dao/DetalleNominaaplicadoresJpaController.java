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
import com.utez.integracion.entity.NominaAplicadores;
import com.utez.integracion.entity.AsignacionAplicador;
import com.utez.integracion.entity.DetalleNominaaplicadores;
import com.utez.integracion.entity.DetalleNominaaplicadoresPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class DetalleNominaaplicadoresJpaController implements Serializable {

    public DetalleNominaaplicadoresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleNominaaplicadores detalleNominaaplicadores) throws PreexistingEntityException, Exception {
        if (detalleNominaaplicadores.getDetalleNominaaplicadoresPK() == null) {
            detalleNominaaplicadores.setDetalleNominaaplicadoresPK(new DetalleNominaaplicadoresPK());
        }
        detalleNominaaplicadores.getDetalleNominaaplicadoresPK().setIdNominaaplicadores(detalleNominaaplicadores.getNominaAplicadores().getIdNominaaplicadores());
        detalleNominaaplicadores.getDetalleNominaaplicadoresPK().setIdAsignacionaplicador(detalleNominaaplicadores.getAsignacionAplicador().getIdAsignacionaplicador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NominaAplicadores nominaAplicadores = detalleNominaaplicadores.getNominaAplicadores();
            if (nominaAplicadores != null) {
                nominaAplicadores = em.getReference(nominaAplicadores.getClass(), nominaAplicadores.getIdNominaaplicadores());
                detalleNominaaplicadores.setNominaAplicadores(nominaAplicadores);
            }
            AsignacionAplicador asignacionAplicador = detalleNominaaplicadores.getAsignacionAplicador();
            if (asignacionAplicador != null) {
                asignacionAplicador = em.getReference(asignacionAplicador.getClass(), asignacionAplicador.getIdAsignacionaplicador());
                detalleNominaaplicadores.setAsignacionAplicador(asignacionAplicador);
            }
            em.persist(detalleNominaaplicadores);
            if (nominaAplicadores != null) {
                nominaAplicadores.getDetalleNominaaplicadoresList().add(detalleNominaaplicadores);
                nominaAplicadores = em.merge(nominaAplicadores);
            }
            if (asignacionAplicador != null) {
                asignacionAplicador.getDetalleNominaaplicadoresList().add(detalleNominaaplicadores);
                asignacionAplicador = em.merge(asignacionAplicador);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleNominaaplicadores(detalleNominaaplicadores.getDetalleNominaaplicadoresPK()) != null) {
                throw new PreexistingEntityException("DetalleNominaaplicadores " + detalleNominaaplicadores + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleNominaaplicadores detalleNominaaplicadores) throws NonexistentEntityException, Exception {
        detalleNominaaplicadores.getDetalleNominaaplicadoresPK().setIdNominaaplicadores(detalleNominaaplicadores.getNominaAplicadores().getIdNominaaplicadores());
        detalleNominaaplicadores.getDetalleNominaaplicadoresPK().setIdAsignacionaplicador(detalleNominaaplicadores.getAsignacionAplicador().getIdAsignacionaplicador());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleNominaaplicadores persistentDetalleNominaaplicadores = em.find(DetalleNominaaplicadores.class, detalleNominaaplicadores.getDetalleNominaaplicadoresPK());
            NominaAplicadores nominaAplicadoresOld = persistentDetalleNominaaplicadores.getNominaAplicadores();
            NominaAplicadores nominaAplicadoresNew = detalleNominaaplicadores.getNominaAplicadores();
            AsignacionAplicador asignacionAplicadorOld = persistentDetalleNominaaplicadores.getAsignacionAplicador();
            AsignacionAplicador asignacionAplicadorNew = detalleNominaaplicadores.getAsignacionAplicador();
            if (nominaAplicadoresNew != null) {
                nominaAplicadoresNew = em.getReference(nominaAplicadoresNew.getClass(), nominaAplicadoresNew.getIdNominaaplicadores());
                detalleNominaaplicadores.setNominaAplicadores(nominaAplicadoresNew);
            }
            if (asignacionAplicadorNew != null) {
                asignacionAplicadorNew = em.getReference(asignacionAplicadorNew.getClass(), asignacionAplicadorNew.getIdAsignacionaplicador());
                detalleNominaaplicadores.setAsignacionAplicador(asignacionAplicadorNew);
            }
            detalleNominaaplicadores = em.merge(detalleNominaaplicadores);
            if (nominaAplicadoresOld != null && !nominaAplicadoresOld.equals(nominaAplicadoresNew)) {
                nominaAplicadoresOld.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadores);
                nominaAplicadoresOld = em.merge(nominaAplicadoresOld);
            }
            if (nominaAplicadoresNew != null && !nominaAplicadoresNew.equals(nominaAplicadoresOld)) {
                nominaAplicadoresNew.getDetalleNominaaplicadoresList().add(detalleNominaaplicadores);
                nominaAplicadoresNew = em.merge(nominaAplicadoresNew);
            }
            if (asignacionAplicadorOld != null && !asignacionAplicadorOld.equals(asignacionAplicadorNew)) {
                asignacionAplicadorOld.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadores);
                asignacionAplicadorOld = em.merge(asignacionAplicadorOld);
            }
            if (asignacionAplicadorNew != null && !asignacionAplicadorNew.equals(asignacionAplicadorOld)) {
                asignacionAplicadorNew.getDetalleNominaaplicadoresList().add(detalleNominaaplicadores);
                asignacionAplicadorNew = em.merge(asignacionAplicadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleNominaaplicadoresPK id = detalleNominaaplicadores.getDetalleNominaaplicadoresPK();
                if (findDetalleNominaaplicadores(id) == null) {
                    throw new NonexistentEntityException("The detalleNominaaplicadores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleNominaaplicadoresPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleNominaaplicadores detalleNominaaplicadores;
            try {
                detalleNominaaplicadores = em.getReference(DetalleNominaaplicadores.class, id);
                detalleNominaaplicadores.getDetalleNominaaplicadoresPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleNominaaplicadores with id " + id + " no longer exists.", enfe);
            }
            NominaAplicadores nominaAplicadores = detalleNominaaplicadores.getNominaAplicadores();
            if (nominaAplicadores != null) {
                nominaAplicadores.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadores);
                nominaAplicadores = em.merge(nominaAplicadores);
            }
            AsignacionAplicador asignacionAplicador = detalleNominaaplicadores.getAsignacionAplicador();
            if (asignacionAplicador != null) {
                asignacionAplicador.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadores);
                asignacionAplicador = em.merge(asignacionAplicador);
            }
            em.remove(detalleNominaaplicadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleNominaaplicadores> findDetalleNominaaplicadoresEntities() {
        return findDetalleNominaaplicadoresEntities(true, -1, -1);
    }

    public List<DetalleNominaaplicadores> findDetalleNominaaplicadoresEntities(int maxResults, int firstResult) {
        return findDetalleNominaaplicadoresEntities(false, maxResults, firstResult);
    }

    private List<DetalleNominaaplicadores> findDetalleNominaaplicadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DetalleNominaaplicadores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleNominaaplicadores findDetalleNominaaplicadores(DetalleNominaaplicadoresPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleNominaaplicadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleNominaaplicadoresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DetalleNominaaplicadores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
