/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Banco;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Reactivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class BancoJpaController implements Serializable {

    public BancoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Banco banco) {
        if (banco.getReactivoList() == null) {
            banco.setReactivoList(new ArrayList<Reactivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reactivo> attachedReactivoList = new ArrayList<Reactivo>();
            for (Reactivo reactivoListReactivoToAttach : banco.getReactivoList()) {
                reactivoListReactivoToAttach = em.getReference(reactivoListReactivoToAttach.getClass(), reactivoListReactivoToAttach.getClaveReactivo());
                attachedReactivoList.add(reactivoListReactivoToAttach);
            }
            banco.setReactivoList(attachedReactivoList);
            em.persist(banco);
            for (Reactivo reactivoListReactivo : banco.getReactivoList()) {
                Banco oldClaveBancoOfReactivoListReactivo = reactivoListReactivo.getClaveBanco();
                reactivoListReactivo.setClaveBanco(banco);
                reactivoListReactivo = em.merge(reactivoListReactivo);
                if (oldClaveBancoOfReactivoListReactivo != null) {
                    oldClaveBancoOfReactivoListReactivo.getReactivoList().remove(reactivoListReactivo);
                    oldClaveBancoOfReactivoListReactivo = em.merge(oldClaveBancoOfReactivoListReactivo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banco banco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco persistentBanco = em.find(Banco.class, banco.getClaveBanco());
            List<Reactivo> reactivoListOld = persistentBanco.getReactivoList();
            List<Reactivo> reactivoListNew = banco.getReactivoList();
            List<Reactivo> attachedReactivoListNew = new ArrayList<Reactivo>();
            for (Reactivo reactivoListNewReactivoToAttach : reactivoListNew) {
                reactivoListNewReactivoToAttach = em.getReference(reactivoListNewReactivoToAttach.getClass(), reactivoListNewReactivoToAttach.getClaveReactivo());
                attachedReactivoListNew.add(reactivoListNewReactivoToAttach);
            }
            reactivoListNew = attachedReactivoListNew;
            banco.setReactivoList(reactivoListNew);
            banco = em.merge(banco);
            for (Reactivo reactivoListOldReactivo : reactivoListOld) {
                if (!reactivoListNew.contains(reactivoListOldReactivo)) {
                    reactivoListOldReactivo.setClaveBanco(null);
                    reactivoListOldReactivo = em.merge(reactivoListOldReactivo);
                }
            }
            for (Reactivo reactivoListNewReactivo : reactivoListNew) {
                if (!reactivoListOld.contains(reactivoListNewReactivo)) {
                    Banco oldClaveBancoOfReactivoListNewReactivo = reactivoListNewReactivo.getClaveBanco();
                    reactivoListNewReactivo.setClaveBanco(banco);
                    reactivoListNewReactivo = em.merge(reactivoListNewReactivo);
                    if (oldClaveBancoOfReactivoListNewReactivo != null && !oldClaveBancoOfReactivoListNewReactivo.equals(banco)) {
                        oldClaveBancoOfReactivoListNewReactivo.getReactivoList().remove(reactivoListNewReactivo);
                        oldClaveBancoOfReactivoListNewReactivo = em.merge(oldClaveBancoOfReactivoListNewReactivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = banco.getClaveBanco();
                if (findBanco(id) == null) {
                    throw new NonexistentEntityException("The banco with id " + id + " no longer exists.");
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
            Banco banco;
            try {
                banco = em.getReference(Banco.class, id);
                banco.getClaveBanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banco with id " + id + " no longer exists.", enfe);
            }
            List<Reactivo> reactivoList = banco.getReactivoList();
            for (Reactivo reactivoListReactivo : reactivoList) {
                reactivoListReactivo.setClaveBanco(null);
                reactivoListReactivo = em.merge(reactivoListReactivo);
            }
            em.remove(banco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banco> findBancoEntities() {
        return findBancoEntities(true, -1, -1);
    }

    public List<Banco> findBancoEntities(int maxResults, int firstResult) {
        return findBancoEntities(false, maxResults, firstResult);
    }

    private List<Banco> findBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Banco as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Banco findBanco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banco.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Banco as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
