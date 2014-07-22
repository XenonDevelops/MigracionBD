/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.dao;

import com.utez.servEscolares.dao.exceptions.IllegalOrphanException;
import com.utez.servEscolares.dao.exceptions.NonexistentEntityException;
import com.utez.servEscolares.entity.Cuadernoprogramacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.servEscolares.entity.Programacionopcionc;
import java.util.ArrayList;
import java.util.List;
import com.utez.servEscolares.entity.Programacionrecursada;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class CuadernoprogramacionJpaController implements Serializable {

    public CuadernoprogramacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuadernoprogramacion cuadernoprogramacion) {
        if (cuadernoprogramacion.getProgramacionopcioncList() == null) {
            cuadernoprogramacion.setProgramacionopcioncList(new ArrayList<Programacionopcionc>());
        }
        if (cuadernoprogramacion.getProgramacionrecursadaList() == null) {
            cuadernoprogramacion.setProgramacionrecursadaList(new ArrayList<Programacionrecursada>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Programacionopcionc> attachedProgramacionopcioncList = new ArrayList<Programacionopcionc>();
            for (Programacionopcionc programacionopcioncListProgramacionopcioncToAttach : cuadernoprogramacion.getProgramacionopcioncList()) {
                programacionopcioncListProgramacionopcioncToAttach = em.getReference(programacionopcioncListProgramacionopcioncToAttach.getClass(), programacionopcioncListProgramacionopcioncToAttach.getClaveProgramacionopcionc());
                attachedProgramacionopcioncList.add(programacionopcioncListProgramacionopcioncToAttach);
            }
            cuadernoprogramacion.setProgramacionopcioncList(attachedProgramacionopcioncList);
            List<Programacionrecursada> attachedProgramacionrecursadaList = new ArrayList<Programacionrecursada>();
            for (Programacionrecursada programacionrecursadaListProgramacionrecursadaToAttach : cuadernoprogramacion.getProgramacionrecursadaList()) {
                programacionrecursadaListProgramacionrecursadaToAttach = em.getReference(programacionrecursadaListProgramacionrecursadaToAttach.getClass(), programacionrecursadaListProgramacionrecursadaToAttach.getClaveProgramacionrecursada());
                attachedProgramacionrecursadaList.add(programacionrecursadaListProgramacionrecursadaToAttach);
            }
            cuadernoprogramacion.setProgramacionrecursadaList(attachedProgramacionrecursadaList);
            em.persist(cuadernoprogramacion);
            for (Programacionopcionc programacionopcioncListProgramacionopcionc : cuadernoprogramacion.getProgramacionopcioncList()) {
                Cuadernoprogramacion oldClaveCuadernoOfProgramacionopcioncListProgramacionopcionc = programacionopcioncListProgramacionopcionc.getClaveCuaderno();
                programacionopcioncListProgramacionopcionc.setClaveCuaderno(cuadernoprogramacion);
                programacionopcioncListProgramacionopcionc = em.merge(programacionopcioncListProgramacionopcionc);
                if (oldClaveCuadernoOfProgramacionopcioncListProgramacionopcionc != null) {
                    oldClaveCuadernoOfProgramacionopcioncListProgramacionopcionc.getProgramacionopcioncList().remove(programacionopcioncListProgramacionopcionc);
                    oldClaveCuadernoOfProgramacionopcioncListProgramacionopcionc = em.merge(oldClaveCuadernoOfProgramacionopcioncListProgramacionopcionc);
                }
            }
            for (Programacionrecursada programacionrecursadaListProgramacionrecursada : cuadernoprogramacion.getProgramacionrecursadaList()) {
                Cuadernoprogramacion oldClaveCuadernoOfProgramacionrecursadaListProgramacionrecursada = programacionrecursadaListProgramacionrecursada.getClaveCuaderno();
                programacionrecursadaListProgramacionrecursada.setClaveCuaderno(cuadernoprogramacion);
                programacionrecursadaListProgramacionrecursada = em.merge(programacionrecursadaListProgramacionrecursada);
                if (oldClaveCuadernoOfProgramacionrecursadaListProgramacionrecursada != null) {
                    oldClaveCuadernoOfProgramacionrecursadaListProgramacionrecursada.getProgramacionrecursadaList().remove(programacionrecursadaListProgramacionrecursada);
                    oldClaveCuadernoOfProgramacionrecursadaListProgramacionrecursada = em.merge(oldClaveCuadernoOfProgramacionrecursadaListProgramacionrecursada);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuadernoprogramacion cuadernoprogramacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuadernoprogramacion persistentCuadernoprogramacion = em.find(Cuadernoprogramacion.class, cuadernoprogramacion.getClaveCuaderno());
            List<Programacionopcionc> programacionopcioncListOld = persistentCuadernoprogramacion.getProgramacionopcioncList();
            List<Programacionopcionc> programacionopcioncListNew = cuadernoprogramacion.getProgramacionopcioncList();
            List<Programacionrecursada> programacionrecursadaListOld = persistentCuadernoprogramacion.getProgramacionrecursadaList();
            List<Programacionrecursada> programacionrecursadaListNew = cuadernoprogramacion.getProgramacionrecursadaList();
            List<String> illegalOrphanMessages = null;
            for (Programacionopcionc programacionopcioncListOldProgramacionopcionc : programacionopcioncListOld) {
                if (!programacionopcioncListNew.contains(programacionopcioncListOldProgramacionopcionc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacionopcionc " + programacionopcioncListOldProgramacionopcionc + " since its claveCuaderno field is not nullable.");
                }
            }
            for (Programacionrecursada programacionrecursadaListOldProgramacionrecursada : programacionrecursadaListOld) {
                if (!programacionrecursadaListNew.contains(programacionrecursadaListOldProgramacionrecursada)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Programacionrecursada " + programacionrecursadaListOldProgramacionrecursada + " since its claveCuaderno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Programacionopcionc> attachedProgramacionopcioncListNew = new ArrayList<Programacionopcionc>();
            for (Programacionopcionc programacionopcioncListNewProgramacionopcioncToAttach : programacionopcioncListNew) {
                programacionopcioncListNewProgramacionopcioncToAttach = em.getReference(programacionopcioncListNewProgramacionopcioncToAttach.getClass(), programacionopcioncListNewProgramacionopcioncToAttach.getClaveProgramacionopcionc());
                attachedProgramacionopcioncListNew.add(programacionopcioncListNewProgramacionopcioncToAttach);
            }
            programacionopcioncListNew = attachedProgramacionopcioncListNew;
            cuadernoprogramacion.setProgramacionopcioncList(programacionopcioncListNew);
            List<Programacionrecursada> attachedProgramacionrecursadaListNew = new ArrayList<Programacionrecursada>();
            for (Programacionrecursada programacionrecursadaListNewProgramacionrecursadaToAttach : programacionrecursadaListNew) {
                programacionrecursadaListNewProgramacionrecursadaToAttach = em.getReference(programacionrecursadaListNewProgramacionrecursadaToAttach.getClass(), programacionrecursadaListNewProgramacionrecursadaToAttach.getClaveProgramacionrecursada());
                attachedProgramacionrecursadaListNew.add(programacionrecursadaListNewProgramacionrecursadaToAttach);
            }
            programacionrecursadaListNew = attachedProgramacionrecursadaListNew;
            cuadernoprogramacion.setProgramacionrecursadaList(programacionrecursadaListNew);
            cuadernoprogramacion = em.merge(cuadernoprogramacion);
            for (Programacionopcionc programacionopcioncListNewProgramacionopcionc : programacionopcioncListNew) {
                if (!programacionopcioncListOld.contains(programacionopcioncListNewProgramacionopcionc)) {
                    Cuadernoprogramacion oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc = programacionopcioncListNewProgramacionopcionc.getClaveCuaderno();
                    programacionopcioncListNewProgramacionopcionc.setClaveCuaderno(cuadernoprogramacion);
                    programacionopcioncListNewProgramacionopcionc = em.merge(programacionopcioncListNewProgramacionopcionc);
                    if (oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc != null && !oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc.equals(cuadernoprogramacion)) {
                        oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc.getProgramacionopcioncList().remove(programacionopcioncListNewProgramacionopcionc);
                        oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc = em.merge(oldClaveCuadernoOfProgramacionopcioncListNewProgramacionopcionc);
                    }
                }
            }
            for (Programacionrecursada programacionrecursadaListNewProgramacionrecursada : programacionrecursadaListNew) {
                if (!programacionrecursadaListOld.contains(programacionrecursadaListNewProgramacionrecursada)) {
                    Cuadernoprogramacion oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada = programacionrecursadaListNewProgramacionrecursada.getClaveCuaderno();
                    programacionrecursadaListNewProgramacionrecursada.setClaveCuaderno(cuadernoprogramacion);
                    programacionrecursadaListNewProgramacionrecursada = em.merge(programacionrecursadaListNewProgramacionrecursada);
                    if (oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada != null && !oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada.equals(cuadernoprogramacion)) {
                        oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada.getProgramacionrecursadaList().remove(programacionrecursadaListNewProgramacionrecursada);
                        oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada = em.merge(oldClaveCuadernoOfProgramacionrecursadaListNewProgramacionrecursada);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuadernoprogramacion.getClaveCuaderno();
                if (findCuadernoprogramacion(id) == null) {
                    throw new NonexistentEntityException("The cuadernoprogramacion with id " + id + " no longer exists.");
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
            Cuadernoprogramacion cuadernoprogramacion;
            try {
                cuadernoprogramacion = em.getReference(Cuadernoprogramacion.class, id);
                cuadernoprogramacion.getClaveCuaderno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuadernoprogramacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Programacionopcionc> programacionopcioncListOrphanCheck = cuadernoprogramacion.getProgramacionopcioncList();
            for (Programacionopcionc programacionopcioncListOrphanCheckProgramacionopcionc : programacionopcioncListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuadernoprogramacion (" + cuadernoprogramacion + ") cannot be destroyed since the Programacionopcionc " + programacionopcioncListOrphanCheckProgramacionopcionc + " in its programacionopcioncList field has a non-nullable claveCuaderno field.");
            }
            List<Programacionrecursada> programacionrecursadaListOrphanCheck = cuadernoprogramacion.getProgramacionrecursadaList();
            for (Programacionrecursada programacionrecursadaListOrphanCheckProgramacionrecursada : programacionrecursadaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuadernoprogramacion (" + cuadernoprogramacion + ") cannot be destroyed since the Programacionrecursada " + programacionrecursadaListOrphanCheckProgramacionrecursada + " in its programacionrecursadaList field has a non-nullable claveCuaderno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cuadernoprogramacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuadernoprogramacion> findCuadernoprogramacionEntities() {
        return findCuadernoprogramacionEntities(true, -1, -1);
    }

    public List<Cuadernoprogramacion> findCuadernoprogramacionEntities(int maxResults, int firstResult) {
        return findCuadernoprogramacionEntities(false, maxResults, firstResult);
    }

    private List<Cuadernoprogramacion> findCuadernoprogramacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cuadernoprogramacion as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuadernoprogramacion findCuadernoprogramacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuadernoprogramacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuadernoprogramacionCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Cuadernoprogramacion as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
