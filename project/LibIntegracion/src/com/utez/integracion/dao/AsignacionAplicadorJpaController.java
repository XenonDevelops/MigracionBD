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
import com.utez.integracion.entity.FechaExamenprogramado;
import com.utez.integracion.entity.Aplicador;
import com.utez.integracion.entity.AsignacionAplicador;
import com.utez.integracion.entity.DetalleNominaaplicadores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignacionAplicadorJpaController implements Serializable {

    public AsignacionAplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionAplicador asignacionAplicador) {
        if (asignacionAplicador.getDetalleNominaaplicadoresList() == null) {
            asignacionAplicador.setDetalleNominaaplicadoresList(new ArrayList<DetalleNominaaplicadores>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FechaExamenprogramado idFechaexamenprogramado = asignacionAplicador.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado = em.getReference(idFechaexamenprogramado.getClass(), idFechaexamenprogramado.getIdFechaExamen());
                asignacionAplicador.setIdFechaexamenprogramado(idFechaexamenprogramado);
            }
            Aplicador idAplicador = asignacionAplicador.getIdAplicador();
            if (idAplicador != null) {
                idAplicador = em.getReference(idAplicador.getClass(), idAplicador.getIdAplicador());
                asignacionAplicador.setIdAplicador(idAplicador);
            }
            List<DetalleNominaaplicadores> attachedDetalleNominaaplicadoresList = new ArrayList<DetalleNominaaplicadores>();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach : asignacionAplicador.getDetalleNominaaplicadoresList()) {
                detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach = em.getReference(detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach.getClass(), detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach.getDetalleNominaaplicadoresPK());
                attachedDetalleNominaaplicadoresList.add(detalleNominaaplicadoresListDetalleNominaaplicadoresToAttach);
            }
            asignacionAplicador.setDetalleNominaaplicadoresList(attachedDetalleNominaaplicadoresList);
            em.persist(asignacionAplicador);
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getAsignacionAplicadorList().add(asignacionAplicador);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            if (idAplicador != null) {
                idAplicador.getAsignacionAplicadorList().add(asignacionAplicador);
                idAplicador = em.merge(idAplicador);
            }
            for (DetalleNominaaplicadores detalleNominaaplicadoresListDetalleNominaaplicadores : asignacionAplicador.getDetalleNominaaplicadoresList()) {
                AsignacionAplicador oldAsignacionAplicadorOfDetalleNominaaplicadoresListDetalleNominaaplicadores = detalleNominaaplicadoresListDetalleNominaaplicadores.getAsignacionAplicador();
                detalleNominaaplicadoresListDetalleNominaaplicadores.setAsignacionAplicador(asignacionAplicador);
                detalleNominaaplicadoresListDetalleNominaaplicadores = em.merge(detalleNominaaplicadoresListDetalleNominaaplicadores);
                if (oldAsignacionAplicadorOfDetalleNominaaplicadoresListDetalleNominaaplicadores != null) {
                    oldAsignacionAplicadorOfDetalleNominaaplicadoresListDetalleNominaaplicadores.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadoresListDetalleNominaaplicadores);
                    oldAsignacionAplicadorOfDetalleNominaaplicadoresListDetalleNominaaplicadores = em.merge(oldAsignacionAplicadorOfDetalleNominaaplicadoresListDetalleNominaaplicadores);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionAplicador asignacionAplicador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionAplicador persistentAsignacionAplicador = em.find(AsignacionAplicador.class, asignacionAplicador.getIdAsignacionaplicador());
            FechaExamenprogramado idFechaexamenprogramadoOld = persistentAsignacionAplicador.getIdFechaexamenprogramado();
            FechaExamenprogramado idFechaexamenprogramadoNew = asignacionAplicador.getIdFechaexamenprogramado();
            Aplicador idAplicadorOld = persistentAsignacionAplicador.getIdAplicador();
            Aplicador idAplicadorNew = asignacionAplicador.getIdAplicador();
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListOld = persistentAsignacionAplicador.getDetalleNominaaplicadoresList();
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListNew = asignacionAplicador.getDetalleNominaaplicadoresList();
            List<String> illegalOrphanMessages = null;
            for (DetalleNominaaplicadores detalleNominaaplicadoresListOldDetalleNominaaplicadores : detalleNominaaplicadoresListOld) {
                if (!detalleNominaaplicadoresListNew.contains(detalleNominaaplicadoresListOldDetalleNominaaplicadores)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleNominaaplicadores " + detalleNominaaplicadoresListOldDetalleNominaaplicadores + " since its asignacionAplicador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idFechaexamenprogramadoNew != null) {
                idFechaexamenprogramadoNew = em.getReference(idFechaexamenprogramadoNew.getClass(), idFechaexamenprogramadoNew.getIdFechaExamen());
                asignacionAplicador.setIdFechaexamenprogramado(idFechaexamenprogramadoNew);
            }
            if (idAplicadorNew != null) {
                idAplicadorNew = em.getReference(idAplicadorNew.getClass(), idAplicadorNew.getIdAplicador());
                asignacionAplicador.setIdAplicador(idAplicadorNew);
            }
            List<DetalleNominaaplicadores> attachedDetalleNominaaplicadoresListNew = new ArrayList<DetalleNominaaplicadores>();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach : detalleNominaaplicadoresListNew) {
                detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach = em.getReference(detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach.getClass(), detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach.getDetalleNominaaplicadoresPK());
                attachedDetalleNominaaplicadoresListNew.add(detalleNominaaplicadoresListNewDetalleNominaaplicadoresToAttach);
            }
            detalleNominaaplicadoresListNew = attachedDetalleNominaaplicadoresListNew;
            asignacionAplicador.setDetalleNominaaplicadoresList(detalleNominaaplicadoresListNew);
            asignacionAplicador = em.merge(asignacionAplicador);
            if (idFechaexamenprogramadoOld != null && !idFechaexamenprogramadoOld.equals(idFechaexamenprogramadoNew)) {
                idFechaexamenprogramadoOld.getAsignacionAplicadorList().remove(asignacionAplicador);
                idFechaexamenprogramadoOld = em.merge(idFechaexamenprogramadoOld);
            }
            if (idFechaexamenprogramadoNew != null && !idFechaexamenprogramadoNew.equals(idFechaexamenprogramadoOld)) {
                idFechaexamenprogramadoNew.getAsignacionAplicadorList().add(asignacionAplicador);
                idFechaexamenprogramadoNew = em.merge(idFechaexamenprogramadoNew);
            }
            if (idAplicadorOld != null && !idAplicadorOld.equals(idAplicadorNew)) {
                idAplicadorOld.getAsignacionAplicadorList().remove(asignacionAplicador);
                idAplicadorOld = em.merge(idAplicadorOld);
            }
            if (idAplicadorNew != null && !idAplicadorNew.equals(idAplicadorOld)) {
                idAplicadorNew.getAsignacionAplicadorList().add(asignacionAplicador);
                idAplicadorNew = em.merge(idAplicadorNew);
            }
            for (DetalleNominaaplicadores detalleNominaaplicadoresListNewDetalleNominaaplicadores : detalleNominaaplicadoresListNew) {
                if (!detalleNominaaplicadoresListOld.contains(detalleNominaaplicadoresListNewDetalleNominaaplicadores)) {
                    AsignacionAplicador oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores = detalleNominaaplicadoresListNewDetalleNominaaplicadores.getAsignacionAplicador();
                    detalleNominaaplicadoresListNewDetalleNominaaplicadores.setAsignacionAplicador(asignacionAplicador);
                    detalleNominaaplicadoresListNewDetalleNominaaplicadores = em.merge(detalleNominaaplicadoresListNewDetalleNominaaplicadores);
                    if (oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores != null && !oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores.equals(asignacionAplicador)) {
                        oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores.getDetalleNominaaplicadoresList().remove(detalleNominaaplicadoresListNewDetalleNominaaplicadores);
                        oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores = em.merge(oldAsignacionAplicadorOfDetalleNominaaplicadoresListNewDetalleNominaaplicadores);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionAplicador.getIdAsignacionaplicador();
                if (findAsignacionAplicador(id) == null) {
                    throw new NonexistentEntityException("The asignacionAplicador with id " + id + " no longer exists.");
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
            AsignacionAplicador asignacionAplicador;
            try {
                asignacionAplicador = em.getReference(AsignacionAplicador.class, id);
                asignacionAplicador.getIdAsignacionaplicador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionAplicador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleNominaaplicadores> detalleNominaaplicadoresListOrphanCheck = asignacionAplicador.getDetalleNominaaplicadoresList();
            for (DetalleNominaaplicadores detalleNominaaplicadoresListOrphanCheckDetalleNominaaplicadores : detalleNominaaplicadoresListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AsignacionAplicador (" + asignacionAplicador + ") cannot be destroyed since the DetalleNominaaplicadores " + detalleNominaaplicadoresListOrphanCheckDetalleNominaaplicadores + " in its detalleNominaaplicadoresList field has a non-nullable asignacionAplicador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FechaExamenprogramado idFechaexamenprogramado = asignacionAplicador.getIdFechaexamenprogramado();
            if (idFechaexamenprogramado != null) {
                idFechaexamenprogramado.getAsignacionAplicadorList().remove(asignacionAplicador);
                idFechaexamenprogramado = em.merge(idFechaexamenprogramado);
            }
            Aplicador idAplicador = asignacionAplicador.getIdAplicador();
            if (idAplicador != null) {
                idAplicador.getAsignacionAplicadorList().remove(asignacionAplicador);
                idAplicador = em.merge(idAplicador);
            }
            em.remove(asignacionAplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionAplicador> findAsignacionAplicadorEntities() {
        return findAsignacionAplicadorEntities(true, -1, -1);
    }

    public List<AsignacionAplicador> findAsignacionAplicadorEntities(int maxResults, int firstResult) {
        return findAsignacionAplicadorEntities(false, maxResults, firstResult);
    }

    private List<AsignacionAplicador> findAsignacionAplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsignacionAplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionAplicador findAsignacionAplicador(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionAplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionAplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsignacionAplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
