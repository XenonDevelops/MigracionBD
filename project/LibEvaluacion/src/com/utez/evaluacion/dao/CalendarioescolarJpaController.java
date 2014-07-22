/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Asesoriascalendario;
import com.utez.evaluacion.entity.Calendarioescolar;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CalendarioescolarJpaController implements Serializable {

    public CalendarioescolarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calendarioescolar calendarioescolar) {
        if (calendarioescolar.getAsesoriascalendarioList() == null) {
            calendarioescolar.setAsesoriascalendarioList(new ArrayList<Asesoriascalendario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asesoriascalendario> attachedAsesoriascalendarioList = new ArrayList<Asesoriascalendario>();
            for (Asesoriascalendario asesoriascalendarioListAsesoriascalendarioToAttach : calendarioescolar.getAsesoriascalendarioList()) {
                asesoriascalendarioListAsesoriascalendarioToAttach = em.getReference(asesoriascalendarioListAsesoriascalendarioToAttach.getClass(), asesoriascalendarioListAsesoriascalendarioToAttach.getClaveAsesoria());
                attachedAsesoriascalendarioList.add(asesoriascalendarioListAsesoriascalendarioToAttach);
            }
            calendarioescolar.setAsesoriascalendarioList(attachedAsesoriascalendarioList);
            em.persist(calendarioescolar);
            for (Asesoriascalendario asesoriascalendarioListAsesoriascalendario : calendarioescolar.getAsesoriascalendarioList()) {
                Calendarioescolar oldClaveCalendarioOfAsesoriascalendarioListAsesoriascalendario = asesoriascalendarioListAsesoriascalendario.getClaveCalendario();
                asesoriascalendarioListAsesoriascalendario.setClaveCalendario(calendarioescolar);
                asesoriascalendarioListAsesoriascalendario = em.merge(asesoriascalendarioListAsesoriascalendario);
                if (oldClaveCalendarioOfAsesoriascalendarioListAsesoriascalendario != null) {
                    oldClaveCalendarioOfAsesoriascalendarioListAsesoriascalendario.getAsesoriascalendarioList().remove(asesoriascalendarioListAsesoriascalendario);
                    oldClaveCalendarioOfAsesoriascalendarioListAsesoriascalendario = em.merge(oldClaveCalendarioOfAsesoriascalendarioListAsesoriascalendario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calendarioescolar calendarioescolar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendarioescolar persistentCalendarioescolar = em.find(Calendarioescolar.class, calendarioescolar.getClaveCalendario());
            List<Asesoriascalendario> asesoriascalendarioListOld = persistentCalendarioescolar.getAsesoriascalendarioList();
            List<Asesoriascalendario> asesoriascalendarioListNew = calendarioescolar.getAsesoriascalendarioList();
            List<Asesoriascalendario> attachedAsesoriascalendarioListNew = new ArrayList<Asesoriascalendario>();
            for (Asesoriascalendario asesoriascalendarioListNewAsesoriascalendarioToAttach : asesoriascalendarioListNew) {
                asesoriascalendarioListNewAsesoriascalendarioToAttach = em.getReference(asesoriascalendarioListNewAsesoriascalendarioToAttach.getClass(), asesoriascalendarioListNewAsesoriascalendarioToAttach.getClaveAsesoria());
                attachedAsesoriascalendarioListNew.add(asesoriascalendarioListNewAsesoriascalendarioToAttach);
            }
            asesoriascalendarioListNew = attachedAsesoriascalendarioListNew;
            calendarioescolar.setAsesoriascalendarioList(asesoriascalendarioListNew);
            calendarioescolar = em.merge(calendarioescolar);
            for (Asesoriascalendario asesoriascalendarioListOldAsesoriascalendario : asesoriascalendarioListOld) {
                if (!asesoriascalendarioListNew.contains(asesoriascalendarioListOldAsesoriascalendario)) {
                    asesoriascalendarioListOldAsesoriascalendario.setClaveCalendario(null);
                    asesoriascalendarioListOldAsesoriascalendario = em.merge(asesoriascalendarioListOldAsesoriascalendario);
                }
            }
            for (Asesoriascalendario asesoriascalendarioListNewAsesoriascalendario : asesoriascalendarioListNew) {
                if (!asesoriascalendarioListOld.contains(asesoriascalendarioListNewAsesoriascalendario)) {
                    Calendarioescolar oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario = asesoriascalendarioListNewAsesoriascalendario.getClaveCalendario();
                    asesoriascalendarioListNewAsesoriascalendario.setClaveCalendario(calendarioescolar);
                    asesoriascalendarioListNewAsesoriascalendario = em.merge(asesoriascalendarioListNewAsesoriascalendario);
                    if (oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario != null && !oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario.equals(calendarioescolar)) {
                        oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario.getAsesoriascalendarioList().remove(asesoriascalendarioListNewAsesoriascalendario);
                        oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario = em.merge(oldClaveCalendarioOfAsesoriascalendarioListNewAsesoriascalendario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = calendarioescolar.getClaveCalendario();
                if (findCalendarioescolar(id) == null) {
                    throw new NonexistentEntityException("The calendarioescolar with id " + id + " no longer exists.");
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
            Calendarioescolar calendarioescolar;
            try {
                calendarioescolar = em.getReference(Calendarioescolar.class, id);
                calendarioescolar.getClaveCalendario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calendarioescolar with id " + id + " no longer exists.", enfe);
            }
            List<Asesoriascalendario> asesoriascalendarioList = calendarioescolar.getAsesoriascalendarioList();
            for (Asesoriascalendario asesoriascalendarioListAsesoriascalendario : asesoriascalendarioList) {
                asesoriascalendarioListAsesoriascalendario.setClaveCalendario(null);
                asesoriascalendarioListAsesoriascalendario = em.merge(asesoriascalendarioListAsesoriascalendario);
            }
            em.remove(calendarioescolar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calendarioescolar> findCalendarioescolarEntities() {
        return findCalendarioescolarEntities(true, -1, -1);
    }

    public List<Calendarioescolar> findCalendarioescolarEntities(int maxResults, int firstResult) {
        return findCalendarioescolarEntities(false, maxResults, firstResult);
    }

    private List<Calendarioescolar> findCalendarioescolarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Calendarioescolar as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calendarioescolar findCalendarioescolar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calendarioescolar.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalendarioescolarCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Calendarioescolar as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
