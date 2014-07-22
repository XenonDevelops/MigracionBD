/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.IllegalOrphanException;
import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Resultadoasesor;
import java.util.ArrayList;
import java.util.List;
import com.utez.evaluacion.entity.Resultadomaterial;
import com.utez.evaluacion.entity.Resultadodirecciones;
import com.utez.evaluacion.entity.Resultadoencuesta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ResultadoencuestaJpaController implements Serializable {

    public ResultadoencuestaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadoencuesta resultadoencuesta) {
        if (resultadoencuesta.getResultadoasesorList() == null) {
            resultadoencuesta.setResultadoasesorList(new ArrayList<Resultadoasesor>());
        }
        if (resultadoencuesta.getResultadomaterialList() == null) {
            resultadoencuesta.setResultadomaterialList(new ArrayList<Resultadomaterial>());
        }
        if (resultadoencuesta.getResultadodireccionesList() == null) {
            resultadoencuesta.setResultadodireccionesList(new ArrayList<Resultadodirecciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resultadoasesor> attachedResultadoasesorList = new ArrayList<Resultadoasesor>();
            for (Resultadoasesor resultadoasesorListResultadoasesorToAttach : resultadoencuesta.getResultadoasesorList()) {
                resultadoasesorListResultadoasesorToAttach = em.getReference(resultadoasesorListResultadoasesorToAttach.getClass(), resultadoasesorListResultadoasesorToAttach.getClaveResultadoA());
                attachedResultadoasesorList.add(resultadoasesorListResultadoasesorToAttach);
            }
            resultadoencuesta.setResultadoasesorList(attachedResultadoasesorList);
            List<Resultadomaterial> attachedResultadomaterialList = new ArrayList<Resultadomaterial>();
            for (Resultadomaterial resultadomaterialListResultadomaterialToAttach : resultadoencuesta.getResultadomaterialList()) {
                resultadomaterialListResultadomaterialToAttach = em.getReference(resultadomaterialListResultadomaterialToAttach.getClass(), resultadomaterialListResultadomaterialToAttach.getClaveResultadoM());
                attachedResultadomaterialList.add(resultadomaterialListResultadomaterialToAttach);
            }
            resultadoencuesta.setResultadomaterialList(attachedResultadomaterialList);
            List<Resultadodirecciones> attachedResultadodireccionesList = new ArrayList<Resultadodirecciones>();
            for (Resultadodirecciones resultadodireccionesListResultadodireccionesToAttach : resultadoencuesta.getResultadodireccionesList()) {
                resultadodireccionesListResultadodireccionesToAttach = em.getReference(resultadodireccionesListResultadodireccionesToAttach.getClass(), resultadodireccionesListResultadodireccionesToAttach.getClaveResultadoD());
                attachedResultadodireccionesList.add(resultadodireccionesListResultadodireccionesToAttach);
            }
            resultadoencuesta.setResultadodireccionesList(attachedResultadodireccionesList);
            em.persist(resultadoencuesta);
            for (Resultadoasesor resultadoasesorListResultadoasesor : resultadoencuesta.getResultadoasesorList()) {
                Resultadoencuesta oldClaveResultadoEOfResultadoasesorListResultadoasesor = resultadoasesorListResultadoasesor.getClaveResultadoE();
                resultadoasesorListResultadoasesor.setClaveResultadoE(resultadoencuesta);
                resultadoasesorListResultadoasesor = em.merge(resultadoasesorListResultadoasesor);
                if (oldClaveResultadoEOfResultadoasesorListResultadoasesor != null) {
                    oldClaveResultadoEOfResultadoasesorListResultadoasesor.getResultadoasesorList().remove(resultadoasesorListResultadoasesor);
                    oldClaveResultadoEOfResultadoasesorListResultadoasesor = em.merge(oldClaveResultadoEOfResultadoasesorListResultadoasesor);
                }
            }
            for (Resultadomaterial resultadomaterialListResultadomaterial : resultadoencuesta.getResultadomaterialList()) {
                Resultadoencuesta oldClaveResultadoEOfResultadomaterialListResultadomaterial = resultadomaterialListResultadomaterial.getClaveResultadoE();
                resultadomaterialListResultadomaterial.setClaveResultadoE(resultadoencuesta);
                resultadomaterialListResultadomaterial = em.merge(resultadomaterialListResultadomaterial);
                if (oldClaveResultadoEOfResultadomaterialListResultadomaterial != null) {
                    oldClaveResultadoEOfResultadomaterialListResultadomaterial.getResultadomaterialList().remove(resultadomaterialListResultadomaterial);
                    oldClaveResultadoEOfResultadomaterialListResultadomaterial = em.merge(oldClaveResultadoEOfResultadomaterialListResultadomaterial);
                }
            }
            for (Resultadodirecciones resultadodireccionesListResultadodirecciones : resultadoencuesta.getResultadodireccionesList()) {
                Resultadoencuesta oldClaveResultadoEOfResultadodireccionesListResultadodirecciones = resultadodireccionesListResultadodirecciones.getClaveResultadoE();
                resultadodireccionesListResultadodirecciones.setClaveResultadoE(resultadoencuesta);
                resultadodireccionesListResultadodirecciones = em.merge(resultadodireccionesListResultadodirecciones);
                if (oldClaveResultadoEOfResultadodireccionesListResultadodirecciones != null) {
                    oldClaveResultadoEOfResultadodireccionesListResultadodirecciones.getResultadodireccionesList().remove(resultadodireccionesListResultadodirecciones);
                    oldClaveResultadoEOfResultadodireccionesListResultadodirecciones = em.merge(oldClaveResultadoEOfResultadodireccionesListResultadodirecciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadoencuesta resultadoencuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoencuesta persistentResultadoencuesta = em.find(Resultadoencuesta.class, resultadoencuesta.getClaveResultadoE());
            List<Resultadoasesor> resultadoasesorListOld = persistentResultadoencuesta.getResultadoasesorList();
            List<Resultadoasesor> resultadoasesorListNew = resultadoencuesta.getResultadoasesorList();
            List<Resultadomaterial> resultadomaterialListOld = persistentResultadoencuesta.getResultadomaterialList();
            List<Resultadomaterial> resultadomaterialListNew = resultadoencuesta.getResultadomaterialList();
            List<Resultadodirecciones> resultadodireccionesListOld = persistentResultadoencuesta.getResultadodireccionesList();
            List<Resultadodirecciones> resultadodireccionesListNew = resultadoencuesta.getResultadodireccionesList();
            List<String> illegalOrphanMessages = null;
            for (Resultadomaterial resultadomaterialListOldResultadomaterial : resultadomaterialListOld) {
                if (!resultadomaterialListNew.contains(resultadomaterialListOldResultadomaterial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Resultadomaterial " + resultadomaterialListOldResultadomaterial + " since its claveResultadoE field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Resultadoasesor> attachedResultadoasesorListNew = new ArrayList<Resultadoasesor>();
            for (Resultadoasesor resultadoasesorListNewResultadoasesorToAttach : resultadoasesorListNew) {
                resultadoasesorListNewResultadoasesorToAttach = em.getReference(resultadoasesorListNewResultadoasesorToAttach.getClass(), resultadoasesorListNewResultadoasesorToAttach.getClaveResultadoA());
                attachedResultadoasesorListNew.add(resultadoasesorListNewResultadoasesorToAttach);
            }
            resultadoasesorListNew = attachedResultadoasesorListNew;
            resultadoencuesta.setResultadoasesorList(resultadoasesorListNew);
            List<Resultadomaterial> attachedResultadomaterialListNew = new ArrayList<Resultadomaterial>();
            for (Resultadomaterial resultadomaterialListNewResultadomaterialToAttach : resultadomaterialListNew) {
                resultadomaterialListNewResultadomaterialToAttach = em.getReference(resultadomaterialListNewResultadomaterialToAttach.getClass(), resultadomaterialListNewResultadomaterialToAttach.getClaveResultadoM());
                attachedResultadomaterialListNew.add(resultadomaterialListNewResultadomaterialToAttach);
            }
            resultadomaterialListNew = attachedResultadomaterialListNew;
            resultadoencuesta.setResultadomaterialList(resultadomaterialListNew);
            List<Resultadodirecciones> attachedResultadodireccionesListNew = new ArrayList<Resultadodirecciones>();
            for (Resultadodirecciones resultadodireccionesListNewResultadodireccionesToAttach : resultadodireccionesListNew) {
                resultadodireccionesListNewResultadodireccionesToAttach = em.getReference(resultadodireccionesListNewResultadodireccionesToAttach.getClass(), resultadodireccionesListNewResultadodireccionesToAttach.getClaveResultadoD());
                attachedResultadodireccionesListNew.add(resultadodireccionesListNewResultadodireccionesToAttach);
            }
            resultadodireccionesListNew = attachedResultadodireccionesListNew;
            resultadoencuesta.setResultadodireccionesList(resultadodireccionesListNew);
            resultadoencuesta = em.merge(resultadoencuesta);
            for (Resultadoasesor resultadoasesorListOldResultadoasesor : resultadoasesorListOld) {
                if (!resultadoasesorListNew.contains(resultadoasesorListOldResultadoasesor)) {
                    resultadoasesorListOldResultadoasesor.setClaveResultadoE(null);
                    resultadoasesorListOldResultadoasesor = em.merge(resultadoasesorListOldResultadoasesor);
                }
            }
            for (Resultadoasesor resultadoasesorListNewResultadoasesor : resultadoasesorListNew) {
                if (!resultadoasesorListOld.contains(resultadoasesorListNewResultadoasesor)) {
                    Resultadoencuesta oldClaveResultadoEOfResultadoasesorListNewResultadoasesor = resultadoasesorListNewResultadoasesor.getClaveResultadoE();
                    resultadoasesorListNewResultadoasesor.setClaveResultadoE(resultadoencuesta);
                    resultadoasesorListNewResultadoasesor = em.merge(resultadoasesorListNewResultadoasesor);
                    if (oldClaveResultadoEOfResultadoasesorListNewResultadoasesor != null && !oldClaveResultadoEOfResultadoasesorListNewResultadoasesor.equals(resultadoencuesta)) {
                        oldClaveResultadoEOfResultadoasesorListNewResultadoasesor.getResultadoasesorList().remove(resultadoasesorListNewResultadoasesor);
                        oldClaveResultadoEOfResultadoasesorListNewResultadoasesor = em.merge(oldClaveResultadoEOfResultadoasesorListNewResultadoasesor);
                    }
                }
            }
            for (Resultadomaterial resultadomaterialListNewResultadomaterial : resultadomaterialListNew) {
                if (!resultadomaterialListOld.contains(resultadomaterialListNewResultadomaterial)) {
                    Resultadoencuesta oldClaveResultadoEOfResultadomaterialListNewResultadomaterial = resultadomaterialListNewResultadomaterial.getClaveResultadoE();
                    resultadomaterialListNewResultadomaterial.setClaveResultadoE(resultadoencuesta);
                    resultadomaterialListNewResultadomaterial = em.merge(resultadomaterialListNewResultadomaterial);
                    if (oldClaveResultadoEOfResultadomaterialListNewResultadomaterial != null && !oldClaveResultadoEOfResultadomaterialListNewResultadomaterial.equals(resultadoencuesta)) {
                        oldClaveResultadoEOfResultadomaterialListNewResultadomaterial.getResultadomaterialList().remove(resultadomaterialListNewResultadomaterial);
                        oldClaveResultadoEOfResultadomaterialListNewResultadomaterial = em.merge(oldClaveResultadoEOfResultadomaterialListNewResultadomaterial);
                    }
                }
            }
            for (Resultadodirecciones resultadodireccionesListOldResultadodirecciones : resultadodireccionesListOld) {
                if (!resultadodireccionesListNew.contains(resultadodireccionesListOldResultadodirecciones)) {
                    resultadodireccionesListOldResultadodirecciones.setClaveResultadoE(null);
                    resultadodireccionesListOldResultadodirecciones = em.merge(resultadodireccionesListOldResultadodirecciones);
                }
            }
            for (Resultadodirecciones resultadodireccionesListNewResultadodirecciones : resultadodireccionesListNew) {
                if (!resultadodireccionesListOld.contains(resultadodireccionesListNewResultadodirecciones)) {
                    Resultadoencuesta oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones = resultadodireccionesListNewResultadodirecciones.getClaveResultadoE();
                    resultadodireccionesListNewResultadodirecciones.setClaveResultadoE(resultadoencuesta);
                    resultadodireccionesListNewResultadodirecciones = em.merge(resultadodireccionesListNewResultadodirecciones);
                    if (oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones != null && !oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones.equals(resultadoencuesta)) {
                        oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones.getResultadodireccionesList().remove(resultadodireccionesListNewResultadodirecciones);
                        oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones = em.merge(oldClaveResultadoEOfResultadodireccionesListNewResultadodirecciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoencuesta.getClaveResultadoE();
                if (findResultadoencuesta(id) == null) {
                    throw new NonexistentEntityException("The resultadoencuesta with id " + id + " no longer exists.");
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
            Resultadoencuesta resultadoencuesta;
            try {
                resultadoencuesta = em.getReference(Resultadoencuesta.class, id);
                resultadoencuesta.getClaveResultadoE();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoencuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Resultadomaterial> resultadomaterialListOrphanCheck = resultadoencuesta.getResultadomaterialList();
            for (Resultadomaterial resultadomaterialListOrphanCheckResultadomaterial : resultadomaterialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Resultadoencuesta (" + resultadoencuesta + ") cannot be destroyed since the Resultadomaterial " + resultadomaterialListOrphanCheckResultadomaterial + " in its resultadomaterialList field has a non-nullable claveResultadoE field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Resultadoasesor> resultadoasesorList = resultadoencuesta.getResultadoasesorList();
            for (Resultadoasesor resultadoasesorListResultadoasesor : resultadoasesorList) {
                resultadoasesorListResultadoasesor.setClaveResultadoE(null);
                resultadoasesorListResultadoasesor = em.merge(resultadoasesorListResultadoasesor);
            }
            List<Resultadodirecciones> resultadodireccionesList = resultadoencuesta.getResultadodireccionesList();
            for (Resultadodirecciones resultadodireccionesListResultadodirecciones : resultadodireccionesList) {
                resultadodireccionesListResultadodirecciones.setClaveResultadoE(null);
                resultadodireccionesListResultadodirecciones = em.merge(resultadodireccionesListResultadodirecciones);
            }
            em.remove(resultadoencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadoencuesta> findResultadoencuestaEntities() {
        return findResultadoencuestaEntities(true, -1, -1);
    }

    public List<Resultadoencuesta> findResultadoencuestaEntities(int maxResults, int firstResult) {
        return findResultadoencuestaEntities(false, maxResults, firstResult);
    }

    private List<Resultadoencuesta> findResultadoencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Resultadoencuesta as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resultadoencuesta findResultadoencuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadoencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Resultadoencuesta as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
