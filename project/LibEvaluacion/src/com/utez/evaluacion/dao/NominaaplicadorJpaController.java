/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.IllegalOrphanException;
import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import com.utez.evaluacion.entity.Nominaaplicador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Nominaconcepto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class NominaaplicadorJpaController implements Serializable {

    public NominaaplicadorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nominaaplicador nominaaplicador) {
        if (nominaaplicador.getNominaconceptoList() == null) {
            nominaaplicador.setNominaconceptoList(new ArrayList<Nominaconcepto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nominaconcepto> attachedNominaconceptoList = new ArrayList<Nominaconcepto>();
            for (Nominaconcepto nominaconceptoListNominaconceptoToAttach : nominaaplicador.getNominaconceptoList()) {
                nominaconceptoListNominaconceptoToAttach = em.getReference(nominaconceptoListNominaconceptoToAttach.getClass(), nominaconceptoListNominaconceptoToAttach.getClaveConcepto());
                attachedNominaconceptoList.add(nominaconceptoListNominaconceptoToAttach);
            }
            nominaaplicador.setNominaconceptoList(attachedNominaconceptoList);
            em.persist(nominaaplicador);
            for (Nominaconcepto nominaconceptoListNominaconcepto : nominaaplicador.getNominaconceptoList()) {
                Nominaaplicador oldFolioOfNominaconceptoListNominaconcepto = nominaconceptoListNominaconcepto.getFolio();
                nominaconceptoListNominaconcepto.setFolio(nominaaplicador);
                nominaconceptoListNominaconcepto = em.merge(nominaconceptoListNominaconcepto);
                if (oldFolioOfNominaconceptoListNominaconcepto != null) {
                    oldFolioOfNominaconceptoListNominaconcepto.getNominaconceptoList().remove(nominaconceptoListNominaconcepto);
                    oldFolioOfNominaconceptoListNominaconcepto = em.merge(oldFolioOfNominaconceptoListNominaconcepto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nominaaplicador nominaaplicador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nominaaplicador persistentNominaaplicador = em.find(Nominaaplicador.class, nominaaplicador.getFolio());
            List<Nominaconcepto> nominaconceptoListOld = persistentNominaaplicador.getNominaconceptoList();
            List<Nominaconcepto> nominaconceptoListNew = nominaaplicador.getNominaconceptoList();
            List<String> illegalOrphanMessages = null;
            for (Nominaconcepto nominaconceptoListOldNominaconcepto : nominaconceptoListOld) {
                if (!nominaconceptoListNew.contains(nominaconceptoListOldNominaconcepto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nominaconcepto " + nominaconceptoListOldNominaconcepto + " since its folio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Nominaconcepto> attachedNominaconceptoListNew = new ArrayList<Nominaconcepto>();
            for (Nominaconcepto nominaconceptoListNewNominaconceptoToAttach : nominaconceptoListNew) {
                nominaconceptoListNewNominaconceptoToAttach = em.getReference(nominaconceptoListNewNominaconceptoToAttach.getClass(), nominaconceptoListNewNominaconceptoToAttach.getClaveConcepto());
                attachedNominaconceptoListNew.add(nominaconceptoListNewNominaconceptoToAttach);
            }
            nominaconceptoListNew = attachedNominaconceptoListNew;
            nominaaplicador.setNominaconceptoList(nominaconceptoListNew);
            nominaaplicador = em.merge(nominaaplicador);
            for (Nominaconcepto nominaconceptoListNewNominaconcepto : nominaconceptoListNew) {
                if (!nominaconceptoListOld.contains(nominaconceptoListNewNominaconcepto)) {
                    Nominaaplicador oldFolioOfNominaconceptoListNewNominaconcepto = nominaconceptoListNewNominaconcepto.getFolio();
                    nominaconceptoListNewNominaconcepto.setFolio(nominaaplicador);
                    nominaconceptoListNewNominaconcepto = em.merge(nominaconceptoListNewNominaconcepto);
                    if (oldFolioOfNominaconceptoListNewNominaconcepto != null && !oldFolioOfNominaconceptoListNewNominaconcepto.equals(nominaaplicador)) {
                        oldFolioOfNominaconceptoListNewNominaconcepto.getNominaconceptoList().remove(nominaconceptoListNewNominaconcepto);
                        oldFolioOfNominaconceptoListNewNominaconcepto = em.merge(oldFolioOfNominaconceptoListNewNominaconcepto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nominaaplicador.getFolio();
                if (findNominaaplicador(id) == null) {
                    throw new NonexistentEntityException("The nominaaplicador with id " + id + " no longer exists.");
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
            Nominaaplicador nominaaplicador;
            try {
                nominaaplicador = em.getReference(Nominaaplicador.class, id);
                nominaaplicador.getFolio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominaaplicador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Nominaconcepto> nominaconceptoListOrphanCheck = nominaaplicador.getNominaconceptoList();
            for (Nominaconcepto nominaconceptoListOrphanCheckNominaconcepto : nominaconceptoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Nominaaplicador (" + nominaaplicador + ") cannot be destroyed since the Nominaconcepto " + nominaconceptoListOrphanCheckNominaconcepto + " in its nominaconceptoList field has a non-nullable folio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nominaaplicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nominaaplicador> findNominaaplicadorEntities() {
        return findNominaaplicadorEntities(true, -1, -1);
    }

    public List<Nominaaplicador> findNominaaplicadorEntities(int maxResults, int firstResult) {
        return findNominaaplicadorEntities(false, maxResults, firstResult);
    }

    private List<Nominaaplicador> findNominaaplicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Nominaaplicador as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nominaaplicador findNominaaplicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nominaaplicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaaplicadorCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Nominaaplicador as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
