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
import com.utez.evaluacion.entity.Fechasexam;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class FechasexamJpaController implements Serializable {

    public FechasexamJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fechasexam fechasexam) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asesoriascalendario claveAsesoria = fechasexam.getClaveAsesoria();
            if (claveAsesoria != null) {
                claveAsesoria = em.getReference(claveAsesoria.getClass(), claveAsesoria.getClaveAsesoria());
                fechasexam.setClaveAsesoria(claveAsesoria);
            }
            em.persist(fechasexam);
            if (claveAsesoria != null) {
                claveAsesoria.getFechasexamList().add(fechasexam);
                claveAsesoria = em.merge(claveAsesoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fechasexam fechasexam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fechasexam persistentFechasexam = em.find(Fechasexam.class, fechasexam.getClaveFechasExam());
            Asesoriascalendario claveAsesoriaOld = persistentFechasexam.getClaveAsesoria();
            Asesoriascalendario claveAsesoriaNew = fechasexam.getClaveAsesoria();
            if (claveAsesoriaNew != null) {
                claveAsesoriaNew = em.getReference(claveAsesoriaNew.getClass(), claveAsesoriaNew.getClaveAsesoria());
                fechasexam.setClaveAsesoria(claveAsesoriaNew);
            }
            fechasexam = em.merge(fechasexam);
            if (claveAsesoriaOld != null && !claveAsesoriaOld.equals(claveAsesoriaNew)) {
                claveAsesoriaOld.getFechasexamList().remove(fechasexam);
                claveAsesoriaOld = em.merge(claveAsesoriaOld);
            }
            if (claveAsesoriaNew != null && !claveAsesoriaNew.equals(claveAsesoriaOld)) {
                claveAsesoriaNew.getFechasexamList().add(fechasexam);
                claveAsesoriaNew = em.merge(claveAsesoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fechasexam.getClaveFechasExam();
                if (findFechasexam(id) == null) {
                    throw new NonexistentEntityException("The fechasexam with id " + id + " no longer exists.");
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
            Fechasexam fechasexam;
            try {
                fechasexam = em.getReference(Fechasexam.class, id);
                fechasexam.getClaveFechasExam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fechasexam with id " + id + " no longer exists.", enfe);
            }
            Asesoriascalendario claveAsesoria = fechasexam.getClaveAsesoria();
            if (claveAsesoria != null) {
                claveAsesoria.getFechasexamList().remove(fechasexam);
                claveAsesoria = em.merge(claveAsesoria);
            }
            em.remove(fechasexam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fechasexam> findFechasexamEntities() {
        return findFechasexamEntities(true, -1, -1);
    }

    public List<Fechasexam> findFechasexamEntities(int maxResults, int firstResult) {
        return findFechasexamEntities(false, maxResults, firstResult);
    }

    private List<Fechasexam> findFechasexamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Fechasexam as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fechasexam findFechasexam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fechasexam.class, id);
        } finally {
            em.close();
        }
    }

    public int getFechasexamCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Fechasexam as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
