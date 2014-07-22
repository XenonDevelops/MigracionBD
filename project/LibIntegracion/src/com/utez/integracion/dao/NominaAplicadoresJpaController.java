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
import com.utez.integracion.entity.DetalleNominaaplicadores;
import com.utez.integracion.entity.NominaAplicadores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class NominaAplicadoresJpaController implements Serializable {

    public NominaAplicadoresJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NominaAplicadores nominaAplicadores) {
        if (nominaAplicadores.getDetalleNominaaplicadoresList() == null) {
            nominaAplicadores.setDetalleNominaaplicadoresList(new ArrayList<DetalleNominaaplicadores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DetalleNominaaplicadores> attachedDetalleNominaaplicadoresList = new ArrayList<DetalleNominaaplicadores>();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach : nominaAplicadores.getDetalleNominaaplicadoresList()) {
                detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach = em.getReference(detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach.getClass(), detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach.getDetalleNominaaplicadoresPK());
                attachedDetalleNominaaplicadoresList.add(detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach);
            }
            nominaAplicadores.setDetalleNominaaplicadoresList(attachedDetalleNominaaplicadoresList);
            em.persist(nominaAplicadores);
            for (DetalleNominaaplicadores detalleNominaaplicadoresListDetalleNominaaplicadores : nominaAplicadores.getDetalleNominaaplicadoresList()) {
                NominaAplicadores oldNominaAplicadoresOfDetalleNominaaplicadoresListDetalleNominaaplicadores = detalleNominaaplicadoresListDetalleNominaaplicadores.getNominaAplicadores();
                detalleNominaaplicadoresListDetalleNominaaplicadores.setNominaAplicadores(nominaAplicadores);
                detalleNominaaplicadoresListDetalleNominaaplicadores = em.merge(detalleNominaaplicadoresListDetalleNominaaplicadores);
                if (oldNominaAplicadoresOfDetalleNominaaplicadoresListDetalleNominaaplicadores != null) {
                    oldNominaAplicadoresOfDetalleNominaaplicadoresListDetalleNominaaplicadores.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadoresListDetalleNominaaplicadores);
                    oldNominaAplicadoresOfDetalleNominaaplicadoresListDetalleNominaaplicadores = em.merge(oldNominaAplicadoresOfDetalleNominaaplicadoresListDetalleNominaaplicadores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NominaAplicadores nominaAplicadores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NominaAplicadores persistentNominaAplicadores = em.find(NominaAplicadores.class, nominaAplicadores.getIdNominaaplicadores());
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListOld = persistentNominaAplicadores.getDetalleNominaaplicadoresList();
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListNew = nominaAplicadores.getDetalleNominaaplicadoresList();
            List<String> illegalOrphanMessages = null;
            for (DetalleNominaaplicadores detalleNominaaplicadoresListOldDetalleNominaaplicadores : detalleNominaaplicadoresListOld) {
                if (!detalleNominaaplicadoresListNew.contains(detalleNominaaplicadoresListOldDetalleNominaaplicadores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleNominaaplicadores " + detalleNominaaplicadoresListOldDetalleNominaaplicadores + " since its nominaAplicadores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DetalleNominaaplicadores> attachedDetalleNominaaplicadoresListNew = new ArrayList<DetalleNominaaplicadores>();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach : detalleNominaaplicadoresListNew) {
                detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach = em.getReference(detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach.getClass(), detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach.getDetalleNominaaplicadoresPK());
                attachedDetalleNominaaplicadoresListNew.add(detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach);
            }
            detalleNominaaplicadoresListNew = attachedDetalleNominaaplicadoresListNew;
            nominaAplicadores.setDetalleNominaaplicadoresList(detalleNominaaplicadoresListNew);
            nominaAplicadores = em.merge(nominaAplicadores);
            for (DetalleNominaaplicadores detalleNominaaplicadoresListNewDetalleNominaaplicadores : detalleNominaaplicadoresListNew) {
                if (!detalleNominaaplicadoresListOld.contains(detalleNominaaplicadoresListNewDetalleNominaaplicadores)) {
                    NominaAplicadores oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores = detalleNominaaplicadoresListNewDetalleNominaaplicadores.getNominaAplicadores();
                    detalleNominaaplicadoresListNewDetalleNominaaplicadores.setNominaAplicadores(nominaAplicadores);
                    detalleNominaaplicadoresListNewDetalleNominaaplicadores = em.merge(detalleNominaaplicadoresListNewDetalleNominaaplicadores);
                    if (oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores != null && !oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores.equals(nominaAplicadores)) {
                        oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadoresListNewDetalleNominaaplicadores);
                        oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores = em.merge(oldNominaAplicadoresOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nominaAplicadores.getIdNominaaplicadores();
                if (findNominaAplicadores(id) == null) {
                    throw new NonexistentEntityException("The nominaAplicadores with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NominaAplicadores nominaAplicadores;
            try {
                nominaAplicadores = em.getReference(NominaAplicadores.class, id);
                nominaAplicadores.getIdNominaaplicadores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominaAplicadores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListOrphanCheck = nominaAplicadores.getDetalleNominaaplicadoresList();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListOrphanCheckDetalleNominaaplicadores : detalleNominaaplicadoresListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NominaAplicadores (" + nominaAplicadores + ") cannot be destroyed since the DetalleNominaaplicadores " + detalleNominaaplicadoresListOrphanCheckDetalleNominaaplicadores + " in its detalleNominaaplicadoresList field has a non-nullable nominaAplicadores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nominaAplicadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NominaAplicadores> findNominaAplicadoresEntities() {
        return findNominaAplicadoresEntities(true, -1, -1);
    }

    public List<NominaAplicadores> findNominaAplicadoresEntities(int maxResults, int firstResult) {
        return findNominaAplicadoresEntities(false, maxResults, firstResult);
    }

    private List<NominaAplicadores> findNominaAplicadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from NominaAplicadores as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NominaAplicadores findNominaAplicadores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NominaAplicadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaAplicadoresCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from NominaAplicadores as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
