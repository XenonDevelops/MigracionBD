/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Programacionautoeval;
import com.utez.evaluacion.entity.Respuestasresultado;
import com.utez.evaluacion.entity.Resultadoautoeval;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ResultadoautoevalJpaController implements Serializable {

    public ResultadoautoevalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resultadoautoeval resultadoautoeval) {
        if (resultadoautoeval.getRespuestasresultadoList() == null) {
            resultadoautoeval.setRespuestasresultadoList(new ArrayList<Respuestasresultado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programacionautoeval claveProgAuto = resultadoautoeval.getClaveProgAuto();
            if (claveProgAuto != null) {
                claveProgAuto = em.getReference(claveProgAuto.getClass(), claveProgAuto.getClaveProgAuto());
                resultadoautoeval.setClaveProgAuto(claveProgAuto);
            }
            List<Respuestasresultado> attachedRespuestasresultadoList = new ArrayList<Respuestasresultado>();
            for (Respuestasresultado respuestasresultadoListRespuestasresultadoToAttach : resultadoautoeval.getRespuestasresultadoList()) {
                respuestasresultadoListRespuestasresultadoToAttach = em.getReference(respuestasresultadoListRespuestasresultadoToAttach.getClass(), respuestasresultadoListRespuestasresultadoToAttach.getClaveRespuestaR());
                attachedRespuestasresultadoList.add(respuestasresultadoListRespuestasresultadoToAttach);
            }
            resultadoautoeval.setRespuestasresultadoList(attachedRespuestasresultadoList);
            em.persist(resultadoautoeval);
            if (claveProgAuto != null) {
                claveProgAuto.getResultadoautoevalList().add(resultadoautoeval);
                claveProgAuto = em.merge(claveProgAuto);
            }
            for (Respuestasresultado respuestasresultadoListRespuestasresultado : resultadoautoeval.getRespuestasresultadoList()) {
                Resultadoautoeval oldClaveResultadoAutoOfRespuestasresultadoListRespuestasresultado = respuestasresultadoListRespuestasresultado.getClaveResultadoAuto();
                respuestasresultadoListRespuestasresultado.setClaveResultadoAuto(resultadoautoeval);
                respuestasresultadoListRespuestasresultado = em.merge(respuestasresultadoListRespuestasresultado);
                if (oldClaveResultadoAutoOfRespuestasresultadoListRespuestasresultado != null) {
                    oldClaveResultadoAutoOfRespuestasresultadoListRespuestasresultado.getRespuestasresultadoList().remove(respuestasresultadoListRespuestasresultado);
                    oldClaveResultadoAutoOfRespuestasresultadoListRespuestasresultado = em.merge(oldClaveResultadoAutoOfRespuestasresultadoListRespuestasresultado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resultadoautoeval resultadoautoeval) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resultadoautoeval persistentResultadoautoeval = em.find(Resultadoautoeval.class, resultadoautoeval.getClaveResultadoAuto());
            Programacionautoeval claveProgAutoOld = persistentResultadoautoeval.getClaveProgAuto();
            Programacionautoeval claveProgAutoNew = resultadoautoeval.getClaveProgAuto();
            List<Respuestasresultado> respuestasresultadoListOld = persistentResultadoautoeval.getRespuestasresultadoList();
            List<Respuestasresultado> respuestasresultadoListNew = resultadoautoeval.getRespuestasresultadoList();
            if (claveProgAutoNew != null) {
                claveProgAutoNew = em.getReference(claveProgAutoNew.getClass(), claveProgAutoNew.getClaveProgAuto());
                resultadoautoeval.setClaveProgAuto(claveProgAutoNew);
            }
            List<Respuestasresultado> attachedRespuestasresultadoListNew = new ArrayList<Respuestasresultado>();
            for (Respuestasresultado respuestasresultadoListNewRespuestasresultadoToAttach : respuestasresultadoListNew) {
                respuestasresultadoListNewRespuestasresultadoToAttach = em.getReference(respuestasresultadoListNewRespuestasresultadoToAttach.getClass(), respuestasresultadoListNewRespuestasresultadoToAttach.getClaveRespuestaR());
                attachedRespuestasresultadoListNew.add(respuestasresultadoListNewRespuestasresultadoToAttach);
            }
            respuestasresultadoListNew = attachedRespuestasresultadoListNew;
            resultadoautoeval.setRespuestasresultadoList(respuestasresultadoListNew);
            resultadoautoeval = em.merge(resultadoautoeval);
            if (claveProgAutoOld != null && !claveProgAutoOld.equals(claveProgAutoNew)) {
                claveProgAutoOld.getResultadoautoevalList().remove(resultadoautoeval);
                claveProgAutoOld = em.merge(claveProgAutoOld);
            }
            if (claveProgAutoNew != null && !claveProgAutoNew.equals(claveProgAutoOld)) {
                claveProgAutoNew.getResultadoautoevalList().add(resultadoautoeval);
                claveProgAutoNew = em.merge(claveProgAutoNew);
            }
            for (Respuestasresultado respuestasresultadoListOldRespuestasresultado : respuestasresultadoListOld) {
                if (!respuestasresultadoListNew.contains(respuestasresultadoListOldRespuestasresultado)) {
                    respuestasresultadoListOldRespuestasresultado.setClaveResultadoAuto(null);
                    respuestasresultadoListOldRespuestasresultado = em.merge(respuestasresultadoListOldRespuestasresultado);
                }
            }
            for (Respuestasresultado respuestasresultadoListNewRespuestasresultado : respuestasresultadoListNew) {
                if (!respuestasresultadoListOld.contains(respuestasresultadoListNewRespuestasresultado)) {
                    Resultadoautoeval oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado = respuestasresultadoListNewRespuestasresultado.getClaveResultadoAuto();
                    respuestasresultadoListNewRespuestasresultado.setClaveResultadoAuto(resultadoautoeval);
                    respuestasresultadoListNewRespuestasresultado = em.merge(respuestasresultadoListNewRespuestasresultado);
                    if (oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado != null && !oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado.equals(resultadoautoeval)) {
                        oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado.getRespuestasresultadoList().remove(respuestasresultadoListNewRespuestasresultado);
                        oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado = em.merge(oldClaveResultadoAutoOfRespuestasresultadoListNewRespuestasresultado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resultadoautoeval.getClaveResultadoAuto();
                if (findResultadoautoeval(id) == null) {
                    throw new NonexistentEntityException("The resultadoautoeval with id " + id + " no longer exists.");
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
            Resultadoautoeval resultadoautoeval;
            try {
                resultadoautoeval = em.getReference(Resultadoautoeval.class, id);
                resultadoautoeval.getClaveResultadoAuto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resultadoautoeval with id " + id + " no longer exists.", enfe);
            }
            Programacionautoeval claveProgAuto = resultadoautoeval.getClaveProgAuto();
            if (claveProgAuto != null) {
                claveProgAuto.getResultadoautoevalList().remove(resultadoautoeval);
                claveProgAuto = em.merge(claveProgAuto);
            }
            List<Respuestasresultado> respuestasresultadoList = resultadoautoeval.getRespuestasresultadoList();
            for (Respuestasresultado respuestasresultadoListRespuestasresultado : respuestasresultadoList) {
                respuestasresultadoListRespuestasresultado.setClaveResultadoAuto(null);
                respuestasresultadoListRespuestasresultado = em.merge(respuestasresultadoListRespuestasresultado);
            }
            em.remove(resultadoautoeval);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resultadoautoeval> findResultadoautoevalEntities() {
        return findResultadoautoevalEntities(true, -1, -1);
    }

    public List<Resultadoautoeval> findResultadoautoevalEntities(int maxResults, int firstResult) {
        return findResultadoautoevalEntities(false, maxResults, firstResult);
    }

    private List<Resultadoautoeval> findResultadoautoevalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Resultadoautoeval as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Resultadoautoeval findResultadoautoeval(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resultadoautoeval.class, id);
        } finally {
            em.close();
        }
    }

    public int getResultadoautoevalCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Resultadoautoeval as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
