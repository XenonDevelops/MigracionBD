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
import com.utez.evaluacion.entity.Banco;
import com.utez.evaluacion.entity.Respuestasreactivocol;
import java.util.ArrayList;
import java.util.List;
import com.utez.evaluacion.entity.Imagenreactivo;
import com.utez.evaluacion.entity.Reactivo;
import com.utez.evaluacion.entity.Respuestasreactivo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class ReactivoJpaController implements Serializable {

    public ReactivoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reactivo reactivo) {
        if (reactivo.getRespuestasreactivocolList() == null) {
            reactivo.setRespuestasreactivocolList(new ArrayList<Respuestasreactivocol>());
        }
        if (reactivo.getImagenreactivoList() == null) {
            reactivo.setImagenreactivoList(new ArrayList<Imagenreactivo>());
        }
        if (reactivo.getRespuestasreactivoList() == null) {
            reactivo.setRespuestasreactivoList(new ArrayList<Respuestasreactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco claveBanco = reactivo.getClaveBanco();
            if (claveBanco != null) {
                claveBanco = em.getReference(claveBanco.getClass(), claveBanco.getClaveBanco());
                reactivo.setClaveBanco(claveBanco);
            }
            List<Respuestasreactivocol> attachedRespuestasreactivocolList = new ArrayList<Respuestasreactivocol>();
            for (Respuestasreactivocol respuestasreactivocolListRespuestasreactivocolToAttach : reactivo.getRespuestasreactivocolList()) {
                respuestasreactivocolListRespuestasreactivocolToAttach = em.getReference(respuestasreactivocolListRespuestasreactivocolToAttach.getClass(), respuestasreactivocolListRespuestasreactivocolToAttach.getClaveRespuestaCol());
                attachedRespuestasreactivocolList.add(respuestasreactivocolListRespuestasreactivocolToAttach);
            }
            reactivo.setRespuestasreactivocolList(attachedRespuestasreactivocolList);
            List<Imagenreactivo> attachedImagenreactivoList = new ArrayList<Imagenreactivo>();
            for (Imagenreactivo imagenreactivoListImagenreactivoToAttach : reactivo.getImagenreactivoList()) {
                imagenreactivoListImagenreactivoToAttach = em.getReference(imagenreactivoListImagenreactivoToAttach.getClass(), imagenreactivoListImagenreactivoToAttach.getClaveImagen());
                attachedImagenreactivoList.add(imagenreactivoListImagenreactivoToAttach);
            }
            reactivo.setImagenreactivoList(attachedImagenreactivoList);
            List<Respuestasreactivo> attachedRespuestasreactivoList = new ArrayList<Respuestasreactivo>();
            for (Respuestasreactivo respuestasreactivoListRespuestasreactivoToAttach : reactivo.getRespuestasreactivoList()) {
                respuestasreactivoListRespuestasreactivoToAttach = em.getReference(respuestasreactivoListRespuestasreactivoToAttach.getClass(), respuestasreactivoListRespuestasreactivoToAttach.getClaveRespuesta());
                attachedRespuestasreactivoList.add(respuestasreactivoListRespuestasreactivoToAttach);
            }
            reactivo.setRespuestasreactivoList(attachedRespuestasreactivoList);
            em.persist(reactivo);
            if (claveBanco != null) {
                claveBanco.getReactivoList().add(reactivo);
                claveBanco = em.merge(claveBanco);
            }
            for (Respuestasreactivocol respuestasreactivocolListRespuestasreactivocol : reactivo.getRespuestasreactivocolList()) {
                Reactivo oldClaveReactivoOfRespuestasreactivocolListRespuestasreactivocol = respuestasreactivocolListRespuestasreactivocol.getClaveReactivo();
                respuestasreactivocolListRespuestasreactivocol.setClaveReactivo(reactivo);
                respuestasreactivocolListRespuestasreactivocol = em.merge(respuestasreactivocolListRespuestasreactivocol);
                if (oldClaveReactivoOfRespuestasreactivocolListRespuestasreactivocol != null) {
                    oldClaveReactivoOfRespuestasreactivocolListRespuestasreactivocol.getRespuestasreactivocolList().remove(respuestasreactivocolListRespuestasreactivocol);
                    oldClaveReactivoOfRespuestasreactivocolListRespuestasreactivocol = em.merge(oldClaveReactivoOfRespuestasreactivocolListRespuestasreactivocol);
                }
            }
            for (Imagenreactivo imagenreactivoListImagenreactivo : reactivo.getImagenreactivoList()) {
                Reactivo oldClaveReactivoOfImagenreactivoListImagenreactivo = imagenreactivoListImagenreactivo.getClaveReactivo();
                imagenreactivoListImagenreactivo.setClaveReactivo(reactivo);
                imagenreactivoListImagenreactivo = em.merge(imagenreactivoListImagenreactivo);
                if (oldClaveReactivoOfImagenreactivoListImagenreactivo != null) {
                    oldClaveReactivoOfImagenreactivoListImagenreactivo.getImagenreactivoList().remove(imagenreactivoListImagenreactivo);
                    oldClaveReactivoOfImagenreactivoListImagenreactivo = em.merge(oldClaveReactivoOfImagenreactivoListImagenreactivo);
                }
            }
            for (Respuestasreactivo respuestasreactivoListRespuestasreactivo : reactivo.getRespuestasreactivoList()) {
                Reactivo oldClaveReactivoOfRespuestasreactivoListRespuestasreactivo = respuestasreactivoListRespuestasreactivo.getClaveReactivo();
                respuestasreactivoListRespuestasreactivo.setClaveReactivo(reactivo);
                respuestasreactivoListRespuestasreactivo = em.merge(respuestasreactivoListRespuestasreactivo);
                if (oldClaveReactivoOfRespuestasreactivoListRespuestasreactivo != null) {
                    oldClaveReactivoOfRespuestasreactivoListRespuestasreactivo.getRespuestasreactivoList().remove(respuestasreactivoListRespuestasreactivo);
                    oldClaveReactivoOfRespuestasreactivoListRespuestasreactivo = em.merge(oldClaveReactivoOfRespuestasreactivoListRespuestasreactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reactivo reactivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reactivo persistentReactivo = em.find(Reactivo.class, reactivo.getClaveReactivo());
            Banco claveBancoOld = persistentReactivo.getClaveBanco();
            Banco claveBancoNew = reactivo.getClaveBanco();
            List<Respuestasreactivocol> respuestasreactivocolListOld = persistentReactivo.getRespuestasreactivocolList();
            List<Respuestasreactivocol> respuestasreactivocolListNew = reactivo.getRespuestasreactivocolList();
            List<Imagenreactivo> imagenreactivoListOld = persistentReactivo.getImagenreactivoList();
            List<Imagenreactivo> imagenreactivoListNew = reactivo.getImagenreactivoList();
            List<Respuestasreactivo> respuestasreactivoListOld = persistentReactivo.getRespuestasreactivoList();
            List<Respuestasreactivo> respuestasreactivoListNew = reactivo.getRespuestasreactivoList();
            List<String> illegalOrphanMessages = null;
            for (Respuestasreactivocol respuestasreactivocolListOldRespuestasreactivocol : respuestasreactivocolListOld) {
                if (!respuestasreactivocolListNew.contains(respuestasreactivocolListOldRespuestasreactivocol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestasreactivocol " + respuestasreactivocolListOldRespuestasreactivocol + " since its claveReactivo field is not nullable.");
                }
            }
            for (Respuestasreactivo respuestasreactivoListOldRespuestasreactivo : respuestasreactivoListOld) {
                if (!respuestasreactivoListNew.contains(respuestasreactivoListOldRespuestasreactivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestasreactivo " + respuestasreactivoListOldRespuestasreactivo + " since its claveReactivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (claveBancoNew != null) {
                claveBancoNew = em.getReference(claveBancoNew.getClass(), claveBancoNew.getClaveBanco());
                reactivo.setClaveBanco(claveBancoNew);
            }
            List<Respuestasreactivocol> attachedRespuestasreactivocolListNew = new ArrayList<Respuestasreactivocol>();
            for (Respuestasreactivocol respuestasreactivocolListNewRespuestasreactivocolToAttach : respuestasreactivocolListNew) {
                respuestasreactivocolListNewRespuestasreactivocolToAttach = em.getReference(respuestasreactivocolListNewRespuestasreactivocolToAttach.getClass(), respuestasreactivocolListNewRespuestasreactivocolToAttach.getClaveRespuestaCol());
                attachedRespuestasreactivocolListNew.add(respuestasreactivocolListNewRespuestasreactivocolToAttach);
            }
            respuestasreactivocolListNew = attachedRespuestasreactivocolListNew;
            reactivo.setRespuestasreactivocolList(respuestasreactivocolListNew);
            List<Imagenreactivo> attachedImagenreactivoListNew = new ArrayList<Imagenreactivo>();
            for (Imagenreactivo imagenreactivoListNewImagenreactivoToAttach : imagenreactivoListNew) {
                imagenreactivoListNewImagenreactivoToAttach = em.getReference(imagenreactivoListNewImagenreactivoToAttach.getClass(), imagenreactivoListNewImagenreactivoToAttach.getClaveImagen());
                attachedImagenreactivoListNew.add(imagenreactivoListNewImagenreactivoToAttach);
            }
            imagenreactivoListNew = attachedImagenreactivoListNew;
            reactivo.setImagenreactivoList(imagenreactivoListNew);
            List<Respuestasreactivo> attachedRespuestasreactivoListNew = new ArrayList<Respuestasreactivo>();
            for (Respuestasreactivo respuestasreactivoListNewRespuestasreactivoToAttach : respuestasreactivoListNew) {
                respuestasreactivoListNewRespuestasreactivoToAttach = em.getReference(respuestasreactivoListNewRespuestasreactivoToAttach.getClass(), respuestasreactivoListNewRespuestasreactivoToAttach.getClaveRespuesta());
                attachedRespuestasreactivoListNew.add(respuestasreactivoListNewRespuestasreactivoToAttach);
            }
            respuestasreactivoListNew = attachedRespuestasreactivoListNew;
            reactivo.setRespuestasreactivoList(respuestasreactivoListNew);
            reactivo = em.merge(reactivo);
            if (claveBancoOld != null && !claveBancoOld.equals(claveBancoNew)) {
                claveBancoOld.getReactivoList().remove(reactivo);
                claveBancoOld = em.merge(claveBancoOld);
            }
            if (claveBancoNew != null && !claveBancoNew.equals(claveBancoOld)) {
                claveBancoNew.getReactivoList().add(reactivo);
                claveBancoNew = em.merge(claveBancoNew);
            }
            for (Respuestasreactivocol respuestasreactivocolListNewRespuestasreactivocol : respuestasreactivocolListNew) {
                if (!respuestasreactivocolListOld.contains(respuestasreactivocolListNewRespuestasreactivocol)) {
                    Reactivo oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol = respuestasreactivocolListNewRespuestasreactivocol.getClaveReactivo();
                    respuestasreactivocolListNewRespuestasreactivocol.setClaveReactivo(reactivo);
                    respuestasreactivocolListNewRespuestasreactivocol = em.merge(respuestasreactivocolListNewRespuestasreactivocol);
                    if (oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol != null && !oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol.equals(reactivo)) {
                        oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol.getRespuestasreactivocolList().remove(respuestasreactivocolListNewRespuestasreactivocol);
                        oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol = em.merge(oldClaveReactivoOfRespuestasreactivocolListNewRespuestasreactivocol);
                    }
                }
            }
            for (Imagenreactivo imagenreactivoListOldImagenreactivo : imagenreactivoListOld) {
                if (!imagenreactivoListNew.contains(imagenreactivoListOldImagenreactivo)) {
                    imagenreactivoListOldImagenreactivo.setClaveReactivo(null);
                    imagenreactivoListOldImagenreactivo = em.merge(imagenreactivoListOldImagenreactivo);
                }
            }
            for (Imagenreactivo imagenreactivoListNewImagenreactivo : imagenreactivoListNew) {
                if (!imagenreactivoListOld.contains(imagenreactivoListNewImagenreactivo)) {
                    Reactivo oldClaveReactivoOfImagenreactivoListNewImagenreactivo = imagenreactivoListNewImagenreactivo.getClaveReactivo();
                    imagenreactivoListNewImagenreactivo.setClaveReactivo(reactivo);
                    imagenreactivoListNewImagenreactivo = em.merge(imagenreactivoListNewImagenreactivo);
                    if (oldClaveReactivoOfImagenreactivoListNewImagenreactivo != null && !oldClaveReactivoOfImagenreactivoListNewImagenreactivo.equals(reactivo)) {
                        oldClaveReactivoOfImagenreactivoListNewImagenreactivo.getImagenreactivoList().remove(imagenreactivoListNewImagenreactivo);
                        oldClaveReactivoOfImagenreactivoListNewImagenreactivo = em.merge(oldClaveReactivoOfImagenreactivoListNewImagenreactivo);
                    }
                }
            }
            for (Respuestasreactivo respuestasreactivoListNewRespuestasreactivo : respuestasreactivoListNew) {
                if (!respuestasreactivoListOld.contains(respuestasreactivoListNewRespuestasreactivo)) {
                    Reactivo oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo = respuestasreactivoListNewRespuestasreactivo.getClaveReactivo();
                    respuestasreactivoListNewRespuestasreactivo.setClaveReactivo(reactivo);
                    respuestasreactivoListNewRespuestasreactivo = em.merge(respuestasreactivoListNewRespuestasreactivo);
                    if (oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo != null && !oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo.equals(reactivo)) {
                        oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo.getRespuestasreactivoList().remove(respuestasreactivoListNewRespuestasreactivo);
                        oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo = em.merge(oldClaveReactivoOfRespuestasreactivoListNewRespuestasreactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reactivo.getClaveReactivo();
                if (findReactivo(id) == null) {
                    throw new NonexistentEntityException("The reactivo with id " + id + " no longer exists.");
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
            Reactivo reactivo;
            try {
                reactivo = em.getReference(Reactivo.class, id);
                reactivo.getClaveReactivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reactivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Respuestasreactivocol> respuestasreactivocolListOrphanCheck = reactivo.getRespuestasreactivocolList();
            for (Respuestasreactivocol respuestasreactivocolListOrphanCheckRespuestasreactivocol : respuestasreactivocolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reactivo (" + reactivo + ") cannot be destroyed since the Respuestasreactivocol " + respuestasreactivocolListOrphanCheckRespuestasreactivocol + " in its respuestasreactivocolList field has a non-nullable claveReactivo field.");
            }
            List<Respuestasreactivo> respuestasreactivoListOrphanCheck = reactivo.getRespuestasreactivoList();
            for (Respuestasreactivo respuestasreactivoListOrphanCheckRespuestasreactivo : respuestasreactivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reactivo (" + reactivo + ") cannot be destroyed since the Respuestasreactivo " + respuestasreactivoListOrphanCheckRespuestasreactivo + " in its respuestasreactivoList field has a non-nullable claveReactivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Banco claveBanco = reactivo.getClaveBanco();
            if (claveBanco != null) {
                claveBanco.getReactivoList().remove(reactivo);
                claveBanco = em.merge(claveBanco);
            }
            List<Imagenreactivo> imagenreactivoList = reactivo.getImagenreactivoList();
            for (Imagenreactivo imagenreactivoListImagenreactivo : imagenreactivoList) {
                imagenreactivoListImagenreactivo.setClaveReactivo(null);
                imagenreactivoListImagenreactivo = em.merge(imagenreactivoListImagenreactivo);
            }
            em.remove(reactivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reactivo> findReactivoEntities() {
        return findReactivoEntities(true, -1, -1);
    }

    public List<Reactivo> findReactivoEntities(int maxResults, int firstResult) {
        return findReactivoEntities(false, maxResults, firstResult);
    }

    private List<Reactivo> findReactivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Reactivo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reactivo findReactivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reactivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getReactivoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Reactivo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
