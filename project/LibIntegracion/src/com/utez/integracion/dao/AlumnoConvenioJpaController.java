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
import com.utez.integracion.entity.Convenio;
import com.utez.integracion.entity.Alumno;
import com.utez.integracion.entity.AlumnoConvenio;
import com.utez.integracion.entity.AlumnoConvenioPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AlumnoConvenioJpaController implements Serializable {

    public AlumnoConvenioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlumnoConvenio alumnoConvenio) throws PreexistingEntityException, Exception {
        if (alumnoConvenio.getAlumnoConvenioPK() == null) {
            alumnoConvenio.setAlumnoConvenioPK(new AlumnoConvenioPK());
        }
        alumnoConvenio.getAlumnoConvenioPK().setIdConvenio(alumnoConvenio.getConvenio().getIdConvenio());
        alumnoConvenio.getAlumnoConvenioPK().setMatricula(alumnoConvenio.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenio convenio = alumnoConvenio.getConvenio();
            if (convenio != null) {
                convenio = em.getReference(convenio.getClass(), convenio.getIdConvenio());
                alumnoConvenio.setConvenio(convenio);
            }
            Alumno alumno = alumnoConvenio.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getMatricula());
                alumnoConvenio.setAlumno(alumno);
            }
            em.persist(alumnoConvenio);
            if (convenio != null) {
                convenio.getAlumnoConvenioList().add(alumnoConvenio);
                convenio = em.merge(convenio);
            }
            if (alumno != null) {
                alumno.getAlumnoConvenioList().add(alumnoConvenio);
                alumno = em.merge(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumnoConvenio(alumnoConvenio.getAlumnoConvenioPK()) != null) {
                throw new PreexistingEntityException("AlumnoConvenio " + alumnoConvenio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlumnoConvenio alumnoConvenio) throws NonexistentEntityException, Exception {
        alumnoConvenio.getAlumnoConvenioPK().setIdConvenio(alumnoConvenio.getConvenio().getIdConvenio());
        alumnoConvenio.getAlumnoConvenioPK().setMatricula(alumnoConvenio.getAlumno().getMatricula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoConvenio persistentAlumnoConvenio = em.find(AlumnoConvenio.class, alumnoConvenio.getAlumnoConvenioPK());
            Convenio convenioOld = persistentAlumnoConvenio.getConvenio();
            Convenio convenioNew = alumnoConvenio.getConvenio();
            Alumno alumnoOld = persistentAlumnoConvenio.getAlumno();
            Alumno alumnoNew = alumnoConvenio.getAlumno();
            if (convenioNew != null) {
                convenioNew = em.getReference(convenioNew.getClass(), convenioNew.getIdConvenio());
                alumnoConvenio.setConvenio(convenioNew);
            }
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getMatricula());
                alumnoConvenio.setAlumno(alumnoNew);
            }
            alumnoConvenio = em.merge(alumnoConvenio);
            if (convenioOld != null && !convenioOld.equals(convenioNew)) {
                convenioOld.getAlumnoConvenioList().remove(alumnoConvenio);
                convenioOld = em.merge(convenioOld);
            }
            if (convenioNew != null && !convenioNew.equals(convenioOld)) {
                convenioNew.getAlumnoConvenioList().add(alumnoConvenio);
                convenioNew = em.merge(convenioNew);
            }
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.getAlumnoConvenioList().remove(alumnoConvenio);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.getAlumnoConvenioList().add(alumnoConvenio);
                alumnoNew = em.merge(alumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AlumnoConvenioPK id = alumnoConvenio.getAlumnoConvenioPK();
                if (findAlumnoConvenio(id) == null) {
                    throw new NonexistentEntityException("The alumnoConvenio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AlumnoConvenioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoConvenio alumnoConvenio;
            try {
                alumnoConvenio = em.getReference(AlumnoConvenio.class, id);
                alumnoConvenio.getAlumnoConvenioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumnoConvenio with id " + id + " no longer exists.", enfe);
            }
            Convenio convenio = alumnoConvenio.getConvenio();
            if (convenio != null) {
                convenio.getAlumnoConvenioList().remove(alumnoConvenio);
                convenio = em.merge(convenio);
            }
            Alumno alumno = alumnoConvenio.getAlumno();
            if (alumno != null) {
                alumno.getAlumnoConvenioList().remove(alumnoConvenio);
                alumno = em.merge(alumno);
            }
            em.remove(alumnoConvenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlumnoConvenio> findAlumnoConvenioEntities() {
        return findAlumnoConvenioEntities(true, -1, -1);
    }

    public List<AlumnoConvenio> findAlumnoConvenioEntities(int maxResults, int firstResult) {
        return findAlumnoConvenioEntities(false, maxResults, firstResult);
    }

    private List<AlumnoConvenio> findAlumnoConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from AlumnoConvenio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AlumnoConvenio findAlumnoConvenio(AlumnoConvenioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlumnoConvenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from AlumnoConvenio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
