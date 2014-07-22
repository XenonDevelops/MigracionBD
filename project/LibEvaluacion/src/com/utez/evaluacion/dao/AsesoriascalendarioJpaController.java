/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Asesoriascalendario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Calendarioescolar;
import com.utez.evaluacion.entity.Fechasexam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesoriascalendarioJpaController implements Serializable {

    public AsesoriascalendarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asesoriascalendario asesoriascalendario) {
        if (asesoriascalendario.getFechasexamList() == null) {
            asesoriascalendario.setFechasexamList(new ArrayList<Fechasexam>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calendarioescolar claveCalendario = asesoriascalendario.getClaveCalendario();
            if (claveCalendario != null) {
                claveCalendario = em.getReference(claveCalendario.getClass(), claveCalendario.getClaveCalendario());
                asesoriascalendario.setClaveCalendario(claveCalendario);
            }
            List<Fechasexam> attachedFechasexamList = new ArrayList<Fechasexam>();
            for (Fechasexam fechasexamListFechasexamToAttach : asesoriascalendario.getFechasexamList()) {
                fechasexamListFechasexamToAttach = em.getReference(fechasexamListFechasexamToAttach.getClass(), fechasexamListFechasexamToAttach.getClaveFechasExam());
                attachedFechasexamList.add(fechasexamListFechasexamToAttach);
            }
            asesoriascalendario.setFechasexamList(attachedFechasexamList);
            em.persist(asesoriascalendario);
            if (claveCalendario != null) {
                claveCalendario.getAsesoriascalendarioList().add(asesoriascalendario);
                claveCalendario = em.merge(claveCalendario);
            }
            for (Fechasexam fechasexamListFechasexam : asesoriascalendario.getFechasexamList()) {
                Asesoriascalendario oldClaveAsesoriaOfFechasexamListFechasexam = fechasexamListFechasexam.getClaveAsesoria();
                fechasexamListFechasexam.setClaveAsesoria(asesoriascalendario);
                fechasexamListFechasexam = em.merge(fechasexamListFechasexam);
                if (oldClaveAsesoriaOfFechasexamListFechasexam != null) {
                    oldClaveAsesoriaOfFechasexamListFechasexam.getFechasexamList().remove(fechasexamListFechasexam);
                    oldClaveAsesoriaOfFechasexamListFechasexam = em.merge(oldClaveAsesoriaOfFechasexamListFechasexam);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asesoriascalendario asesoriascalendario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoriascalendario persistentAsesoriascalendario = em.find(Asesoriascalendario.class, asesoriascalendario.getClaveAsesoria());
            Calendarioescolar claveCalendarioOld = persistentAsesoriascalendario.getClaveCalendario();
            Calendarioescolar claveCalendarioNew = asesoriascalendario.getClaveCalendario();
            List<Fechasexam> fechasexamListOld = persistentAsesoriascalendario.getFechasexamList();
            List<Fechasexam> fechasexamListNew = asesoriascalendario.getFechasexamList();
            if (claveCalendarioNew != null) {
                claveCalendarioNew = em.getReference(claveCalendarioNew.getClass(), claveCalendarioNew.getClaveCalendario());
                asesoriascalendario.setClaveCalendario(claveCalendarioNew);
            }
            List<Fechasexam> attachedFechasexamListNew = new ArrayList<Fechasexam>();
            for (Fechasexam fechasexamListNewFechasexamToAttach : fechasexamListNew) {
                fechasexamListNewFechasexamToAttach = em.getReference(fechasexamListNewFechasexamToAttach.getClass(), fechasexamListNewFechasexamToAttach.getClaveFechasExam());
                attachedFechasexamListNew.add(fechasexamListNewFechasexamToAttach);
            }
            fechasexamListNew = attachedFechasexamListNew;
            asesoriascalendario.setFechasexamList(fechasexamListNew);
            asesoriascalendario = em.merge(asesoriascalendario);
            if (claveCalendarioOld != null && !claveCalendarioOld.equals(claveCalendarioNew)) {
                claveCalendarioOld.getAsesoriascalendarioList().remove(asesoriascalendario);
                claveCalendarioOld = em.merge(claveCalendarioOld);
            }
            if (claveCalendarioNew != null && !claveCalendarioNew.equals(claveCalendarioOld)) {
                claveCalendarioNew.getAsesoriascalendarioList().add(asesoriascalendario);
                claveCalendarioNew = em.merge(claveCalendarioNew);
            }
            for (Fechasexam fechasexamListOldFechasexam : fechasexamListOld) {
                if (!fechasexamListNew.contains(fechasexamListOldFechasexam)) {
                    fechasexamListOldFechasexam.setClaveAsesoria(null);
                    fechasexamListOldFechasexam = em.merge(fechasexamListOldFechasexam);
                }
            }
            for (Fechasexam fechasexamListNewFechasexam : fechasexamListNew) {
                if (!fechasexamListOld.contains(fechasexamListNewFechasexam)) {
                    Asesoriascalendario oldClaveAsesoriaOfFechasexamListNewFechasexam = fechasexamListNewFechasexam.getClaveAsesoria();
                    fechasexamListNewFechasexam.setClaveAsesoria(asesoriascalendario);
                    fechasexamListNewFechasexam = em.merge(fechasexamListNewFechasexam);
                    if (oldClaveAsesoriaOfFechasexamListNewFechasexam != null && !oldClaveAsesoriaOfFechasexamListNewFechasexam.equals(asesoriascalendario)) {
                        oldClaveAsesoriaOfFechasexamListNewFechasexam.getFechasexamList().remove(fechasexamListNewFechasexam);
                        oldClaveAsesoriaOfFechasexamListNewFechasexam = em.merge(oldClaveAsesoriaOfFechasexamListNewFechasexam);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asesoriascalendario.getClaveAsesoria();
                if (findAsesoriascalendario(id) == null) {
                    throw new NonexistentEntityException("The asesoriascalendario with id " + id + " no longer exists.");
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
            Asesoriascalendario asesoriascalendario;
            try {
                asesoriascalendario = em.getReference(Asesoriascalendario.class, id);
                asesoriascalendario.getClaveAsesoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesoriascalendario with id " + id + " no longer exists.", enfe);
            }
            Calendarioescolar claveCalendario = asesoriascalendario.getClaveCalendario();
            if (claveCalendario != null) {
                claveCalendario.getAsesoriascalendarioList().remove(asesoriascalendario);
                claveCalendario = em.merge(claveCalendario);
            }
            List<Fechasexam> fechasexamList = asesoriascalendario.getFechasexamList();
            for (Fechasexam fechasexamListFechasexam : fechasexamList) {
                fechasexamListFechasexam.setClaveAsesoria(null);
                fechasexamListFechasexam = em.merge(fechasexamListFechasexam);
            }
            em.remove(asesoriascalendario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asesoriascalendario> findAsesoriascalendarioEntities() {
        return findAsesoriascalendarioEntities(true, -1, -1);
    }

    public List<Asesoriascalendario> findAsesoriascalendarioEntities(int maxResults, int firstResult) {
        return findAsesoriascalendarioEntities(false, maxResults, firstResult);
    }

    private List<Asesoriascalendario> findAsesoriascalendarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asesoriascalendario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asesoriascalendario findAsesoriascalendario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asesoriascalendario.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesoriascalendarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asesoriascalendario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
