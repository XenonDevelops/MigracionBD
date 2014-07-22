/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Aplicacionexamen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asignaaplicador;
import java.util.ArrayList;
import java.util.List;
import com.utez.evaluacion.entity.Entregaexamen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AplicacionexamenJpaController implements Serializable {

    public AplicacionexamenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aplicacionexamen aplicacionexamen) {
        if (aplicacionexamen.getAsignaaplicadorList() == null) {
            aplicacionexamen.setAsignaaplicadorList(new ArrayList<Asignaaplicador>());
        }
        if (aplicacionexamen.getEntregaexamenList() == null) {
            aplicacionexamen.setEntregaexamenList(new ArrayList<Entregaexamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asignaaplicador> attachedAsignaaplicadorList = new ArrayList<Asignaaplicador>();
            for (Asignaaplicador asignaaplicadorListAsignaaplicadorToAttach : aplicacionexamen.getAsignaaplicadorList()) {
                asignaaplicadorListAsignaaplicadorToAttach = em.getReference(asignaaplicadorListAsignaaplicadorToAttach.getClass(), asignaaplicadorListAsignaaplicadorToAttach.getClaveAsigna());
                attachedAsignaaplicadorList.add(asignaaplicadorListAsignaaplicadorToAttach);
            }
            aplicacionexamen.setAsignaaplicadorList(attachedAsignaaplicadorList);
            List<Entregaexamen> attachedEntregaexamenList = new ArrayList<Entregaexamen>();
            for (Entregaexamen entregaexamenListEntregaexamenToAttach : aplicacionexamen.getEntregaexamenList()) {
                entregaexamenListEntregaexamenToAttach = em.getReference(entregaexamenListEntregaexamenToAttach.getClass(), entregaexamenListEntregaexamenToAttach.getClaveEntrega());
                attachedEntregaexamenList.add(entregaexamenListEntregaexamenToAttach);
            }
            aplicacionexamen.setEntregaexamenList(attachedEntregaexamenList);
            em.persist(aplicacionexamen);
            for (Asignaaplicador asignaaplicadorListAsignaaplicador : aplicacionexamen.getAsignaaplicadorList()) {
                Aplicacionexamen oldClaveAplicacionOfAsignaaplicadorListAsignaaplicador = asignaaplicadorListAsignaaplicador.getClaveAplicacion();
                asignaaplicadorListAsignaaplicador.setClaveAplicacion(aplicacionexamen);
                asignaaplicadorListAsignaaplicador = em.merge(asignaaplicadorListAsignaaplicador);
                if (oldClaveAplicacionOfAsignaaplicadorListAsignaaplicador != null) {
                    oldClaveAplicacionOfAsignaaplicadorListAsignaaplicador.getAsignaaplicadorList().remove(asignaaplicadorListAsignaaplicador);
                    oldClaveAplicacionOfAsignaaplicadorListAsignaaplicador = em.merge(oldClaveAplicacionOfAsignaaplicadorListAsignaaplicador);
                }
            }
            for (Entregaexamen entregaexamenListEntregaexamen : aplicacionexamen.getEntregaexamenList()) {
                Aplicacionexamen oldClaveAplicacionOfEntregaexamenListEntregaexamen = entregaexamenListEntregaexamen.getClaveAplicacion();
                entregaexamenListEntregaexamen.setClaveAplicacion(aplicacionexamen);
                entregaexamenListEntregaexamen = em.merge(entregaexamenListEntregaexamen);
                if (oldClaveAplicacionOfEntregaexamenListEntregaexamen != null) {
                    oldClaveAplicacionOfEntregaexamenListEntregaexamen.getEntregaexamenList().remove(entregaexamenListEntregaexamen);
                    oldClaveAplicacionOfEntregaexamenListEntregaexamen = em.merge(oldClaveAplicacionOfEntregaexamenListEntregaexamen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aplicacionexamen aplicacionexamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aplicacionexamen persistentAplicacionexamen = em.find(Aplicacionexamen.class, aplicacionexamen.getClaveAplicacion());
            List<Asignaaplicador> asignaaplicadorListOld = persistentAplicacionexamen.getAsignaaplicadorList();
            List<Asignaaplicador> asignaaplicadorListNew = aplicacionexamen.getAsignaaplicadorList();
            List<Entregaexamen> entregaexamenListOld = persistentAplicacionexamen.getEntregaexamenList();
            List<Entregaexamen> entregaexamenListNew = aplicacionexamen.getEntregaexamenList();
            List<Asignaaplicador> attachedAsignaaplicadorListNew = new ArrayList<Asignaaplicador>();
            for (Asignaaplicador asignaaplicadorListNewAsignaaplicadorToAttach : asignaaplicadorListNew) {
                asignaaplicadorListNewAsignaaplicadorToAttach = em.getReference(asignaaplicadorListNewAsignaaplicadorToAttach.getClass(), asignaaplicadorListNewAsignaaplicadorToAttach.getClaveAsigna());
                attachedAsignaaplicadorListNew.add(asignaaplicadorListNewAsignaaplicadorToAttach);
            }
            asignaaplicadorListNew = attachedAsignaaplicadorListNew;
            aplicacionexamen.setAsignaaplicadorList(asignaaplicadorListNew);
            List<Entregaexamen> attachedEntregaexamenListNew = new ArrayList<Entregaexamen>();
            for (Entregaexamen entregaexamenListNewEntregaexamenToAttach : entregaexamenListNew) {
                entregaexamenListNewEntregaexamenToAttach = em.getReference(entregaexamenListNewEntregaexamenToAttach.getClass(), entregaexamenListNewEntregaexamenToAttach.getClaveEntrega());
                attachedEntregaexamenListNew.add(entregaexamenListNewEntregaexamenToAttach);
            }
            entregaexamenListNew = attachedEntregaexamenListNew;
            aplicacionexamen.setEntregaexamenList(entregaexamenListNew);
            aplicacionexamen = em.merge(aplicacionexamen);
            for (Asignaaplicador asignaaplicadorListOldAsignaaplicador : asignaaplicadorListOld) {
                if (!asignaaplicadorListNew.contains(asignaaplicadorListOldAsignaaplicador)) {
                    asignaaplicadorListOldAsignaaplicador.setClaveAplicacion(null);
                    asignaaplicadorListOldAsignaaplicador = em.merge(asignaaplicadorListOldAsignaaplicador);
                }
            }
            for (Asignaaplicador asignaaplicadorListNewAsignaaplicador : asignaaplicadorListNew) {
                if (!asignaaplicadorListOld.contains(asignaaplicadorListNewAsignaaplicador)) {
                    Aplicacionexamen oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador = asignaaplicadorListNewAsignaaplicador.getClaveAplicacion();
                    asignaaplicadorListNewAsignaaplicador.setClaveAplicacion(aplicacionexamen);
                    asignaaplicadorListNewAsignaaplicador = em.merge(asignaaplicadorListNewAsignaaplicador);
                    if (oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador != null && !oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador.equals(aplicacionexamen)) {
                        oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador.getAsignaaplicadorList().remove(asignaaplicadorListNewAsignaaplicador);
                        oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador = em.merge(oldClaveAplicacionOfAsignaaplicadorListNewAsignaaplicador);
                    }
                }
            }
            for (Entregaexamen entregaexamenListOldEntregaexamen : entregaexamenListOld) {
                if (!entregaexamenListNew.contains(entregaexamenListOldEntregaexamen)) {
                    entregaexamenListOldEntregaexamen.setClaveAplicacion(null);
                    entregaexamenListOldEntregaexamen = em.merge(entregaexamenListOldEntregaexamen);
                }
            }
            for (Entregaexamen entregaexamenListNewEntregaexamen : entregaexamenListNew) {
                if (!entregaexamenListOld.contains(entregaexamenListNewEntregaexamen)) {
                    Aplicacionexamen oldClaveAplicacionOfEntregaexamenListNewEntregaexamen = entregaexamenListNewEntregaexamen.getClaveAplicacion();
                    entregaexamenListNewEntregaexamen.setClaveAplicacion(aplicacionexamen);
                    entregaexamenListNewEntregaexamen = em.merge(entregaexamenListNewEntregaexamen);
                    if (oldClaveAplicacionOfEntregaexamenListNewEntregaexamen != null && !oldClaveAplicacionOfEntregaexamenListNewEntregaexamen.equals(aplicacionexamen)) {
                        oldClaveAplicacionOfEntregaexamenListNewEntregaexamen.getEntregaexamenList().remove(entregaexamenListNewEntregaexamen);
                        oldClaveAplicacionOfEntregaexamenListNewEntregaexamen = em.merge(oldClaveAplicacionOfEntregaexamenListNewEntregaexamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aplicacionexamen.getClaveAplicacion();
                if (findAplicacionexamen(id) == null) {
                    throw new NonexistentEntityException("The aplicacionexamen with id " + id + " no longer exists.");
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
            Aplicacionexamen aplicacionexamen;
            try {
                aplicacionexamen = em.getReference(Aplicacionexamen.class, id);
                aplicacionexamen.getClaveAplicacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aplicacionexamen with id " + id + " no longer exists.", enfe);
            }
            List<Asignaaplicador> asignaaplicadorList = aplicacionexamen.getAsignaaplicadorList();
            for (Asignaaplicador asignaaplicadorListAsignaaplicador : asignaaplicadorList) {
                asignaaplicadorListAsignaaplicador.setClaveAplicacion(null);
                asignaaplicadorListAsignaaplicador = em.merge(asignaaplicadorListAsignaaplicador);
            }
            List<Entregaexamen> entregaexamenList = aplicacionexamen.getEntregaexamenList();
            for (Entregaexamen entregaexamenListEntregaexamen : entregaexamenList) {
                entregaexamenListEntregaexamen.setClaveAplicacion(null);
                entregaexamenListEntregaexamen = em.merge(entregaexamenListEntregaexamen);
            }
            em.remove(aplicacionexamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aplicacionexamen> findAplicacionexamenEntities() {
        return findAplicacionexamenEntities(true, -1, -1);
    }

    public List<Aplicacionexamen> findAplicacionexamenEntities(int maxResults, int firstResult) {
        return findAplicacionexamenEntities(false, maxResults, firstResult);
    }

    private List<Aplicacionexamen> findAplicacionexamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Aplicacionexamen as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Aplicacionexamen findAplicacionexamen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aplicacionexamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAplicacionexamenCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Aplicacionexamen as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
