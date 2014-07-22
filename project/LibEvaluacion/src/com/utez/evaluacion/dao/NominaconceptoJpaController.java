/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Nominaaplicador;
import com.utez.evaluacion.entity.Nominaconcepto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class NominaconceptoJpaController implements Serializable {

    public NominaconceptoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nominaconcepto nominaconcepto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominaaplicador folio = nominaconcepto.getFolio();
            if (folio != null) {
                folio = em.getReference(folio.getClass(), folio.getFolio());
                nominaconcepto.setFolio(folio);
            }
            em.persist(nominaconcepto);
            if (folio != null) {
                folio.getNominaconceptoList().add(nominaconcepto);
                folio = em.merge(folio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nominaconcepto nominaconcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominaconcepto persistentNominaconcepto = em.find(Nominaconcepto.class, nominaconcepto.getClaveConcepto());
            Nominaaplicador folioOld = persistentNominaconcepto.getFolio();
            Nominaaplicador folioNew = nominaconcepto.getFolio();
            if (folioNew != null) {
                folioNew = em.getReference(folioNew.getClass(), folioNew.getFolio());
                nominaconcepto.setFolio(folioNew);
            }
            nominaconcepto = em.merge(nominaconcepto);
            if (folioOld != null && !folioOld.equals(folioNew)) {
                folioOld.getNominaconceptoList().remove(nominaconcepto);
                folioOld = em.merge(folioOld);
            }
            if (folioNew != null && !folioNew.equals(folioOld)) {
                folioNew.getNominaconceptoList().add(nominaconcepto);
                folioNew = em.merge(folioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nominaconcepto.getClaveConcepto();
                if (findNominaconcepto(id) == null) {
                    throw new NonexistentEntityException("The nominaconcepto with id " + id + " no longer exists.");
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
            Nominaconcepto nominaconcepto;
            try {
                nominaconcepto = em.getReference(Nominaconcepto.class, id);
                nominaconcepto.getClaveConcepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominaconcepto with id " + id + " no longer exists.", enfe);
            }
            Nominaaplicador folio = nominaconcepto.getFolio();
            if (folio != null) {
                folio.getNominaconceptoList().remove(nominaconcepto);
                folio = em.merge(folio);
            }
            em.remove(nominaconcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nominaconcepto> findNominaconceptoEntities() {
        return findNominaconceptoEntities(true, -1, -1);
    }

    public List<Nominaconcepto> findNominaconceptoEntities(int maxResults, int firstResult) {
        return findNominaconceptoEntities(false, maxResults, firstResult);
    }

    private List<Nominaconcepto> findNominaconceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Nominaconcepto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nominaconcepto findNominaconcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nominaconcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaconceptoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Nominaconcepto as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
