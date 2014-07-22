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
import com.utez.integracion.entity.AlumnoConvenio;
import com.utez.integracion.entity.Convenio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ConvenioJpaController implements Serializable {

    public ConvenioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenio convenio) {
        if (convenio.getAlumnoConvenioList() == null) {
            convenio.setAlumnoConvenioList(new ArrayList<AlumnoConvenio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AlumnoConvenio> attachedAlumnoConvenioList = new ArrayList<AlumnoConvenio>();
            for (AlumnoConvenio alumnoConvenioListAlumnoConvenioToAttach : convenio.getAlumnoConvenioList()) {
                alumnoConvenioListAlumnoConvenioToAttach = em.getReference(alumnoConvenioListAlumnoConvenioToAttach.getClass(), alumnoConvenioListAlumnoConvenioToAttach.getAlumnoConvenioPK());
                attachedAlumnoConvenioList.add(alumnoConvenioListAlumnoConvenioToAttach);
            }
            convenio.setAlumnoConvenioList(attachedAlumnoConvenioList);
            em.persist(convenio);
            for (AlumnoConvenio alumnoConvenioListAlumnoConvenio : convenio.getAlumnoConvenioList()) {
                Convenio oldConvenioOfAlumnoConvenioListAlumnoConvenio = alumnoConvenioListAlumnoConvenio.getConvenio();
                alumnoConvenioListAlumnoConvenio.setConvenio(convenio);
                alumnoConvenioListAlumnoConvenio = em.merge(alumnoConvenioListAlumnoConvenio);
                if (oldConvenioOfAlumnoConvenioListAlumnoConvenio != null) {
                    oldConvenioOfAlumnoConvenioListAlumnoConvenio.getAlumnoConvenioList().remove(alumnoConvenioListAlumnoConvenio);
                    oldConvenioOfAlumnoConvenioListAlumnoConvenio = em.merge(oldConvenioOfAlumnoConvenioListAlumnoConvenio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Convenio convenio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenio persistentConvenio = em.find(Convenio.class, convenio.getIdConvenio());
            List<AlumnoConvenio> alumnoConvenioListOld = persistentConvenio.getAlumnoConvenioList();
            List<AlumnoConvenio> alumnoConvenioListNew = convenio.getAlumnoConvenioList();
            List<String> illegalOrphanMessages = null;
            for (AlumnoConvenio alumnoConvenioListOldAlumnoConvenio : alumnoConvenioListOld) {
                if (!alumnoConvenioListNew.contains(alumnoConvenioListOldAlumnoConvenio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AlumnoConvenio " + alumnoConvenioListOldAlumnoConvenio + " since its convenio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AlumnoConvenio> attachedAlumnoConvenioListNew = new ArrayList<AlumnoConvenio>();
            for (AlumnoConvenio alumnoConvenioListNewAlumnoConvenioToAttach : alumnoConvenioListNew) {
                alumnoConvenioListNewAlumnoConvenioToAttach = em.getReference(alumnoConvenioListNewAlumnoConvenioToAttach.getClass(), alumnoConvenioListNewAlumnoConvenioToAttach.getAlumnoConvenioPK());
                attachedAlumnoConvenioListNew.add(alumnoConvenioListNewAlumnoConvenioToAttach);
            }
            alumnoConvenioListNew = attachedAlumnoConvenioListNew;
            convenio.setAlumnoConvenioList(alumnoConvenioListNew);
            convenio = em.merge(convenio);
            for (AlumnoConvenio alumnoConvenioListNewAlumnoConvenio : alumnoConvenioListNew) {
                if (!alumnoConvenioListOld.contains(alumnoConvenioListNewAlumnoConvenio)) {
                    Convenio oldConvenioOfAlumnoConvenioListNewAlumnoConvenio = alumnoConvenioListNewAlumnoConvenio.getConvenio();
                    alumnoConvenioListNewAlumnoConvenio.setConvenio(convenio);
                    alumnoConvenioListNewAlumnoConvenio = em.merge(alumnoConvenioListNewAlumnoConvenio);
                    if (oldConvenioOfAlumnoConvenioListNewAlumnoConvenio != null && !oldConvenioOfAlumnoConvenioListNewAlumnoConvenio.equals(convenio)) {
                        oldConvenioOfAlumnoConvenioListNewAlumnoConvenio.getAlumnoConvenioList().remove(alumnoConvenioListNewAlumnoConvenio);
                        oldConvenioOfAlumnoConvenioListNewAlumnoConvenio = em.merge(oldConvenioOfAlumnoConvenioListNewAlumnoConvenio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = convenio.getIdConvenio();
                if (findConvenio(id) == null) {
                    throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.");
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
            Convenio convenio;
            try {
                convenio = em.getReference(Convenio.class, id);
                convenio.getIdConvenio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AlumnoConvenio> alumnoConvenioListOrphanCheck = convenio.getAlumnoConvenioList();
            for (AlumnoConvenio alumnoConvenioListOrphanCheckAlumnoConvenio : alumnoConvenioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Convenio (" + convenio + ") cannot be destroyed since the AlumnoConvenio " + alumnoConvenioListOrphanCheckAlumnoConvenio + " in its alumnoConvenioList field has a non-nullable convenio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(convenio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Convenio> findConvenioEntities() {
        return findConvenioEntities(true, -1, -1);
    }

    public List<Convenio> findConvenioEntities(int maxResults, int firstResult) {
        return findConvenioEntities(false, maxResults, firstResult);
    }

    private List<Convenio> findConvenioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Convenio as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Convenio findConvenio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Convenio.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Convenio as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
