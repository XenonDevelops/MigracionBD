/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import com.utez.secAcademica.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Adeudo;
import com.utez.secAcademica.entity.Alumno;
import java.util.ArrayList;
import java.util.List;
import com.utez.secAcademica.entity.Movimientocie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) throws PreexistingEntityException, Exception {
        if (alumno.getAdeudoList() == null) {
            alumno.setAdeudoList(new ArrayList<Adeudo>());
        }
        if (alumno.getMovimientocieList() == null) {
            alumno.setMovimientocieList(new ArrayList<Movimientocie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Adeudo> attachedAdeudoList = new ArrayList<Adeudo>();
            for (Adeudo adeudoListAdeudoToAttach : alumno.getAdeudoList()) {
                adeudoListAdeudoToAttach = em.getReference(adeudoListAdeudoToAttach.getClass(), adeudoListAdeudoToAttach.getIdadeudo());
                attachedAdeudoList.add(adeudoListAdeudoToAttach);
            }
            alumno.setAdeudoList(attachedAdeudoList);
            List<Movimientocie> attachedMovimientocieList = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListMovimientocieToAttach : alumno.getMovimientocieList()) {
                movimientocieListMovimientocieToAttach = em.getReference(movimientocieListMovimientocieToAttach.getClass(), movimientocieListMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieList.add(movimientocieListMovimientocieToAttach);
            }
            alumno.setMovimientocieList(attachedMovimientocieList);
            em.persist(alumno);
            for (Adeudo adeudoListAdeudo : alumno.getAdeudoList()) {
                Alumno oldMatriculaOfAdeudoListAdeudo = adeudoListAdeudo.getMatricula();
                adeudoListAdeudo.setMatricula(alumno);
                adeudoListAdeudo = em.merge(adeudoListAdeudo);
                if (oldMatriculaOfAdeudoListAdeudo != null) {
                    oldMatriculaOfAdeudoListAdeudo.getAdeudoList().remove(adeudoListAdeudo);
                    oldMatriculaOfAdeudoListAdeudo = em.merge(oldMatriculaOfAdeudoListAdeudo);
                }
            }
            for (Movimientocie movimientocieListMovimientocie : alumno.getMovimientocieList()) {
                Alumno oldMatriculaOfMovimientocieListMovimientocie = movimientocieListMovimientocie.getMatricula();
                movimientocieListMovimientocie.setMatricula(alumno);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
                if (oldMatriculaOfMovimientocieListMovimientocie != null) {
                    oldMatriculaOfMovimientocieListMovimientocie.getMovimientocieList().remove(movimientocieListMovimientocie);
                    oldMatriculaOfMovimientocieListMovimientocie = em.merge(oldMatriculaOfMovimientocieListMovimientocie);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumno(alumno.getMatricula()) != null) {
                throw new PreexistingEntityException("Alumno " + alumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getMatricula());
            List<Adeudo> adeudoListOld = persistentAlumno.getAdeudoList();
            List<Adeudo> adeudoListNew = alumno.getAdeudoList();
            List<Movimientocie> movimientocieListOld = persistentAlumno.getMovimientocieList();
            List<Movimientocie> movimientocieListNew = alumno.getMovimientocieList();
            List<Adeudo> attachedAdeudoListNew = new ArrayList<Adeudo>();
            for (Adeudo adeudoListNewAdeudoToAttach : adeudoListNew) {
                adeudoListNewAdeudoToAttach = em.getReference(adeudoListNewAdeudoToAttach.getClass(), adeudoListNewAdeudoToAttach.getIdadeudo());
                attachedAdeudoListNew.add(adeudoListNewAdeudoToAttach);
            }
            adeudoListNew = attachedAdeudoListNew;
            alumno.setAdeudoList(adeudoListNew);
            List<Movimientocie> attachedMovimientocieListNew = new ArrayList<Movimientocie>();
            for (Movimientocie movimientocieListNewMovimientocieToAttach : movimientocieListNew) {
                movimientocieListNewMovimientocieToAttach = em.getReference(movimientocieListNewMovimientocieToAttach.getClass(), movimientocieListNewMovimientocieToAttach.getIdmovimientocie());
                attachedMovimientocieListNew.add(movimientocieListNewMovimientocieToAttach);
            }
            movimientocieListNew = attachedMovimientocieListNew;
            alumno.setMovimientocieList(movimientocieListNew);
            alumno = em.merge(alumno);
            for (Adeudo adeudoListOldAdeudo : adeudoListOld) {
                if (!adeudoListNew.contains(adeudoListOldAdeudo)) {
                    adeudoListOldAdeudo.setMatricula(null);
                    adeudoListOldAdeudo = em.merge(adeudoListOldAdeudo);
                }
            }
            for (Adeudo adeudoListNewAdeudo : adeudoListNew) {
                if (!adeudoListOld.contains(adeudoListNewAdeudo)) {
                    Alumno oldMatriculaOfAdeudoListNewAdeudo = adeudoListNewAdeudo.getMatricula();
                    adeudoListNewAdeudo.setMatricula(alumno);
                    adeudoListNewAdeudo = em.merge(adeudoListNewAdeudo);
                    if (oldMatriculaOfAdeudoListNewAdeudo != null && !oldMatriculaOfAdeudoListNewAdeudo.equals(alumno)) {
                        oldMatriculaOfAdeudoListNewAdeudo.getAdeudoList().remove(adeudoListNewAdeudo);
                        oldMatriculaOfAdeudoListNewAdeudo = em.merge(oldMatriculaOfAdeudoListNewAdeudo);
                    }
                }
            }
            for (Movimientocie movimientocieListOldMovimientocie : movimientocieListOld) {
                if (!movimientocieListNew.contains(movimientocieListOldMovimientocie)) {
                    movimientocieListOldMovimientocie.setMatricula(null);
                    movimientocieListOldMovimientocie = em.merge(movimientocieListOldMovimientocie);
                }
            }
            for (Movimientocie movimientocieListNewMovimientocie : movimientocieListNew) {
                if (!movimientocieListOld.contains(movimientocieListNewMovimientocie)) {
                    Alumno oldMatriculaOfMovimientocieListNewMovimientocie = movimientocieListNewMovimientocie.getMatricula();
                    movimientocieListNewMovimientocie.setMatricula(alumno);
                    movimientocieListNewMovimientocie = em.merge(movimientocieListNewMovimientocie);
                    if (oldMatriculaOfMovimientocieListNewMovimientocie != null && !oldMatriculaOfMovimientocieListNewMovimientocie.equals(alumno)) {
                        oldMatriculaOfMovimientocieListNewMovimientocie.getMovimientocieList().remove(movimientocieListNewMovimientocie);
                        oldMatriculaOfMovimientocieListNewMovimientocie = em.merge(oldMatriculaOfMovimientocieListNewMovimientocie);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = alumno.getMatricula();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<Adeudo> adeudoList = alumno.getAdeudoList();
            for (Adeudo adeudoListAdeudo : adeudoList) {
                adeudoListAdeudo.setMatricula(null);
                adeudoListAdeudo = em.merge(adeudoListAdeudo);
            }
            List<Movimientocie> movimientocieList = alumno.getMovimientocieList();
            for (Movimientocie movimientocieListMovimientocie : movimientocieList) {
                movimientocieListMovimientocie.setMatricula(null);
                movimientocieListMovimientocie = em.merge(movimientocieListMovimientocie);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Alumno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Alumno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
