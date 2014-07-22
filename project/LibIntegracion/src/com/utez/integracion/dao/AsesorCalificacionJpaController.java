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
import com.utez.integracion.entity.EsquemaAlumnoasignatura;
import com.utez.integracion.entity.Asesor;
import com.utez.integracion.entity.AsesorCalificacion;
import com.utez.integracion.entity.AsesorCalificacionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsesorCalificacionJpaController implements Serializable {

    public AsesorCalificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsesorCalificacion asesorCalificacion) throws PreexistingEntityException, Exception {
        if (asesorCalificacion.getAsesorCalificacionPK() == null) {
            asesorCalificacion.setAsesorCalificacionPK(new AsesorCalificacionPK());
        }
        asesorCalificacion.getAsesorCalificacionPK().setIdAsesor(asesorCalificacion.getAsesor().getIdAsesor());
        asesorCalificacion.getAsesorCalificacionPK().setIdAlumnoasignaturaesquema(asesorCalificacion.getEsquemaAlumnoasignatura().getIdEsquemaalumnoasignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = asesorCalificacion.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura = em.getReference(esquemaAlumnoasignatura.getClass(), esquemaAlumnoasignatura.getIdEsquemaalumnoasignatura());
                asesorCalificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignatura);
            }
            Asesor asesor = asesorCalificacion.getAsesor();
            if (asesor != null) {
                asesor = em.getReference(asesor.getClass(), asesor.getIdAsesor());
                asesorCalificacion.setAsesor(asesor);
            }
            em.persist(asesorCalificacion);
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.getAsesorCalificacionList().add(asesorCalificacion);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            if (asesor != null) {
                asesor.getAsesorCalificacionList().add(asesorCalificacion);
                asesor = em.merge(asesor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsesorCalificacion(asesorCalificacion.getAsesorCalificacionPK()) != null) {
                throw new PreexistingEntityException("AsesorCalificacion " + asesorCalificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsesorCalificacion asesorCalificacion) throws NonexistentEntityException, Exception {
        asesorCalificacion.getAsesorCalificacionPK().setIdAsesor(asesorCalificacion.getAsesor().getIdAsesor());
        asesorCalificacion.getAsesorCalificacionPK().setIdAlumnoasignaturaesquema(asesorCalificacion.getEsquemaAlumnoasignatura().getIdEsquemaalumnoasignatura());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsesorCalificacion persistentAsesorCalificacion = em.find(AsesorCalificacion.class, asesorCalificacion.getAsesorCalificacionPK());
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaOld = persistentAsesorCalificacion.getEsquemaAlumnoasignatura();
            EsquemaAlumnoasignatura esquemaAlumnoasignaturaNew = asesorCalificacion.getEsquemaAlumnoasignatura();
            Asesor asesorOld = persistentAsesorCalificacion.getAsesor();
            Asesor asesorNew = asesorCalificacion.getAsesor();
            if (esquemaAlumnoasignaturaNew != null) {
                esquemaAlumnoasignaturaNew = em.getReference(esquemaAlumnoasignaturaNew.getClass(), esquemaAlumnoasignaturaNew.getIdEsquemaalumnoasignatura());
                asesorCalificacion.setEsquemaAlumnoasignatura(esquemaAlumnoasignaturaNew);
            }
            if (asesorNew != null) {
                asesorNew = em.getReference(asesorNew.getClass(), asesorNew.getIdAsesor());
                asesorCalificacion.setAsesor(asesorNew);
            }
            asesorCalificacion = em.merge(asesorCalificacion);
            if (esquemaAlumnoasignaturaOld != null && !esquemaAlumnoasignaturaOld.equals(esquemaAlumnoasignaturaNew)) {
                esquemaAlumnoasignaturaOld.getAsesorCalificacionList().remove(asesorCalificacion);
                esquemaAlumnoasignaturaOld = em.merge(esquemaAlumnoasignaturaOld);
            }
            if (esquemaAlumnoasignaturaNew != null && !esquemaAlumnoasignaturaNew.equals(esquemaAlumnoasignaturaOld)) {
                esquemaAlumnoasignaturaNew.getAsesorCalificacionList().add(asesorCalificacion);
                esquemaAlumnoasignaturaNew = em.merge(esquemaAlumnoasignaturaNew);
            }
            if (asesorOld != null && !asesorOld.equals(asesorNew)) {
                asesorOld.getAsesorCalificacionList().remove(asesorCalificacion);
                asesorOld = em.merge(asesorOld);
            }
            if (asesorNew != null && !asesorNew.equals(asesorOld)) {
                asesorNew.getAsesorCalificacionList().add(asesorCalificacion);
                asesorNew = em.merge(asesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsesorCalificacionPK id = asesorCalificacion.getAsesorCalificacionPK();
                if (findAsesorCalificacion(id) == null) {
                    throw new NonexistentEntityException("The asesorCalificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsesorCalificacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsesorCalificacion asesorCalificacion;
            try {
                asesorCalificacion = em.getReference(AsesorCalificacion.class, id);
                asesorCalificacion.getAsesorCalificacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asesorCalificacion with id " + id + " no longer exists.", enfe);
            }
            EsquemaAlumnoasignatura esquemaAlumnoasignatura = asesorCalificacion.getEsquemaAlumnoasignatura();
            if (esquemaAlumnoasignatura != null) {
                esquemaAlumnoasignatura.getAsesorCalificacionList().remove(asesorCalificacion);
                esquemaAlumnoasignatura = em.merge(esquemaAlumnoasignatura);
            }
            Asesor asesor = asesorCalificacion.getAsesor();
            if (asesor != null) {
                asesor.getAsesorCalificacionList().remove(asesorCalificacion);
                asesor = em.merge(asesor);
            }
            em.remove(asesorCalificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsesorCalificacion> findAsesorCalificacionEntities() {
        return findAsesorCalificacionEntities(true, -1, -1);
    }

    public List<AsesorCalificacion> findAsesorCalificacionEntities(int maxResults, int firstResult) {
        return findAsesorCalificacionEntities(false, maxResults, firstResult);
    }

    private List<AsesorCalificacion> findAsesorCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AsesorCalificacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsesorCalificacion findAsesorCalificacion(AsesorCalificacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsesorCalificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsesorCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AsesorCalificacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
