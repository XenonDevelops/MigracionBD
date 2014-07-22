/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.dao;

import com.utez.evaluacion.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.evaluacion.entity.Materiasdeplan;
import com.utez.evaluacion.entity.Plandeestudios;
import com.utez.evaluacion.entity.Correspondencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class MateriasdeplanJpaController implements Serializable {

    public MateriasdeplanJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materiasdeplan materiasdeplan) {
        if (materiasdeplan.getCorrespondenciaList() == null) {
            materiasdeplan.setCorrespondenciaList(new ArrayList<Correspondencia>());
        }
        if (materiasdeplan.getMateriasdeplanList() == null) {
            materiasdeplan.setMateriasdeplanList(new ArrayList<Materiasdeplan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiasdeplan seriadaCon = materiasdeplan.getSeriadaCon();
            if (seriadaCon != null) {
                seriadaCon = em.getReference(seriadaCon.getClass(), seriadaCon.getClaveMateria());
                materiasdeplan.setSeriadaCon(seriadaCon);
            }
            Plandeestudios revoe = materiasdeplan.getRevoe();
            if (revoe != null) {
                revoe = em.getReference(revoe.getClass(), revoe.getRevoe());
                materiasdeplan.setRevoe(revoe);
            }
            List<Correspondencia> attachedCorrespondenciaList = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaListCorrespondenciaToAttach : materiasdeplan.getCorrespondenciaList()) {
                correspondenciaListCorrespondenciaToAttach = em.getReference(correspondenciaListCorrespondenciaToAttach.getClass(), correspondenciaListCorrespondenciaToAttach.getClaveCorrespondencia());
                attachedCorrespondenciaList.add(correspondenciaListCorrespondenciaToAttach);
            }
            materiasdeplan.setCorrespondenciaList(attachedCorrespondenciaList);
            List<Materiasdeplan> attachedMateriasdeplanList = new ArrayList<Materiasdeplan>();
            for (Materiasdeplan materiasdeplanListMateriasdeplanToAttach : materiasdeplan.getMateriasdeplanList()) {
                materiasdeplanListMateriasdeplanToAttach = em.getReference(materiasdeplanListMateriasdeplanToAttach.getClass(), materiasdeplanListMateriasdeplanToAttach.getClaveMateria());
                attachedMateriasdeplanList.add(materiasdeplanListMateriasdeplanToAttach);
            }
            materiasdeplan.setMateriasdeplanList(attachedMateriasdeplanList);
            em.persist(materiasdeplan);
            if (seriadaCon != null) {
                seriadaCon.getMateriasdeplanList().add(materiasdeplan);
                seriadaCon = em.merge(seriadaCon);
            }
            if (revoe != null) {
                revoe.getMateriasdeplanList().add(materiasdeplan);
                revoe = em.merge(revoe);
            }
            for (Correspondencia correspondenciaListCorrespondencia : materiasdeplan.getCorrespondenciaList()) {
                Materiasdeplan oldClaveMateriaOfCorrespondenciaListCorrespondencia = correspondenciaListCorrespondencia.getClaveMateria();
                correspondenciaListCorrespondencia.setClaveMateria(materiasdeplan);
                correspondenciaListCorrespondencia = em.merge(correspondenciaListCorrespondencia);
                if (oldClaveMateriaOfCorrespondenciaListCorrespondencia != null) {
                    oldClaveMateriaOfCorrespondenciaListCorrespondencia.getCorrespondenciaList().remove(correspondenciaListCorrespondencia);
                    oldClaveMateriaOfCorrespondenciaListCorrespondencia = em.merge(oldClaveMateriaOfCorrespondenciaListCorrespondencia);
                }
            }
            for (Materiasdeplan materiasdeplanListMateriasdeplan : materiasdeplan.getMateriasdeplanList()) {
                Materiasdeplan oldSeriadaConOfMateriasdeplanListMateriasdeplan = materiasdeplanListMateriasdeplan.getSeriadaCon();
                materiasdeplanListMateriasdeplan.setSeriadaCon(materiasdeplan);
                materiasdeplanListMateriasdeplan = em.merge(materiasdeplanListMateriasdeplan);
                if (oldSeriadaConOfMateriasdeplanListMateriasdeplan != null) {
                    oldSeriadaConOfMateriasdeplanListMateriasdeplan.getMateriasdeplanList().remove(materiasdeplanListMateriasdeplan);
                    oldSeriadaConOfMateriasdeplanListMateriasdeplan = em.merge(oldSeriadaConOfMateriasdeplanListMateriasdeplan);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materiasdeplan materiasdeplan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiasdeplan persistentMateriasdeplan = em.find(Materiasdeplan.class, materiasdeplan.getClaveMateria());
            Materiasdeplan seriadaConOld = persistentMateriasdeplan.getSeriadaCon();
            Materiasdeplan seriadaConNew = materiasdeplan.getSeriadaCon();
            Plandeestudios revoeOld = persistentMateriasdeplan.getRevoe();
            Plandeestudios revoeNew = materiasdeplan.getRevoe();
            List<Correspondencia> correspondenciaListOld = persistentMateriasdeplan.getCorrespondenciaList();
            List<Correspondencia> correspondenciaListNew = materiasdeplan.getCorrespondenciaList();
            List<Materiasdeplan> materiasdeplanListOld = persistentMateriasdeplan.getMateriasdeplanList();
            List<Materiasdeplan> materiasdeplanListNew = materiasdeplan.getMateriasdeplanList();
            if (seriadaConNew != null) {
                seriadaConNew = em.getReference(seriadaConNew.getClass(), seriadaConNew.getClaveMateria());
                materiasdeplan.setSeriadaCon(seriadaConNew);
            }
            if (revoeNew != null) {
                revoeNew = em.getReference(revoeNew.getClass(), revoeNew.getRevoe());
                materiasdeplan.setRevoe(revoeNew);
            }
            List<Correspondencia> attachedCorrespondenciaListNew = new ArrayList<Correspondencia>();
            for (Correspondencia correspondenciaListNewCorrespondenciaToAttach : correspondenciaListNew) {
                correspondenciaListNewCorrespondenciaToAttach = em.getReference(correspondenciaListNewCorrespondenciaToAttach.getClass(), correspondenciaListNewCorrespondenciaToAttach.getClaveCorrespondencia());
                attachedCorrespondenciaListNew.add(correspondenciaListNewCorrespondenciaToAttach);
            }
            correspondenciaListNew = attachedCorrespondenciaListNew;
            materiasdeplan.setCorrespondenciaList(correspondenciaListNew);
            List<Materiasdeplan> attachedMateriasdeplanListNew = new ArrayList<Materiasdeplan>();
            for (Materiasdeplan materiasdeplanListNewMateriasdeplanToAttach : materiasdeplanListNew) {
                materiasdeplanListNewMateriasdeplanToAttach = em.getReference(materiasdeplanListNewMateriasdeplanToAttach.getClass(), materiasdeplanListNewMateriasdeplanToAttach.getClaveMateria());
                attachedMateriasdeplanListNew.add(materiasdeplanListNewMateriasdeplanToAttach);
            }
            materiasdeplanListNew = attachedMateriasdeplanListNew;
            materiasdeplan.setMateriasdeplanList(materiasdeplanListNew);
            materiasdeplan = em.merge(materiasdeplan);
            if (seriadaConOld != null && !seriadaConOld.equals(seriadaConNew)) {
                seriadaConOld.getMateriasdeplanList().remove(materiasdeplan);
                seriadaConOld = em.merge(seriadaConOld);
            }
            if (seriadaConNew != null && !seriadaConNew.equals(seriadaConOld)) {
                seriadaConNew.getMateriasdeplanList().add(materiasdeplan);
                seriadaConNew = em.merge(seriadaConNew);
            }
            if (revoeOld != null && !revoeOld.equals(revoeNew)) {
                revoeOld.getMateriasdeplanList().remove(materiasdeplan);
                revoeOld = em.merge(revoeOld);
            }
            if (revoeNew != null && !revoeNew.equals(revoeOld)) {
                revoeNew.getMateriasdeplanList().add(materiasdeplan);
                revoeNew = em.merge(revoeNew);
            }
            for (Correspondencia correspondenciaListOldCorrespondencia : correspondenciaListOld) {
                if (!correspondenciaListNew.contains(correspondenciaListOldCorrespondencia)) {
                    correspondenciaListOldCorrespondencia.setClaveMateria(null);
                    correspondenciaListOldCorrespondencia = em.merge(correspondenciaListOldCorrespondencia);
                }
            }
            for (Correspondencia correspondenciaListNewCorrespondencia : correspondenciaListNew) {
                if (!correspondenciaListOld.contains(correspondenciaListNewCorrespondencia)) {
                    Materiasdeplan oldClaveMateriaOfCorrespondenciaListNewCorrespondencia = correspondenciaListNewCorrespondencia.getClaveMateria();
                    correspondenciaListNewCorrespondencia.setClaveMateria(materiasdeplan);
                    correspondenciaListNewCorrespondencia = em.merge(correspondenciaListNewCorrespondencia);
                    if (oldClaveMateriaOfCorrespondenciaListNewCorrespondencia != null && !oldClaveMateriaOfCorrespondenciaListNewCorrespondencia.equals(materiasdeplan)) {
                        oldClaveMateriaOfCorrespondenciaListNewCorrespondencia.getCorrespondenciaList().remove(correspondenciaListNewCorrespondencia);
                        oldClaveMateriaOfCorrespondenciaListNewCorrespondencia = em.merge(oldClaveMateriaOfCorrespondenciaListNewCorrespondencia);
                    }
                }
            }
            for (Materiasdeplan materiasdeplanListOldMateriasdeplan : materiasdeplanListOld) {
                if (!materiasdeplanListNew.contains(materiasdeplanListOldMateriasdeplan)) {
                    materiasdeplanListOldMateriasdeplan.setSeriadaCon(null);
                    materiasdeplanListOldMateriasdeplan = em.merge(materiasdeplanListOldMateriasdeplan);
                }
            }
            for (Materiasdeplan materiasdeplanListNewMateriasdeplan : materiasdeplanListNew) {
                if (!materiasdeplanListOld.contains(materiasdeplanListNewMateriasdeplan)) {
                    Materiasdeplan oldSeriadaConOfMateriasdeplanListNewMateriasdeplan = materiasdeplanListNewMateriasdeplan.getSeriadaCon();
                    materiasdeplanListNewMateriasdeplan.setSeriadaCon(materiasdeplan);
                    materiasdeplanListNewMateriasdeplan = em.merge(materiasdeplanListNewMateriasdeplan);
                    if (oldSeriadaConOfMateriasdeplanListNewMateriasdeplan != null && !oldSeriadaConOfMateriasdeplanListNewMateriasdeplan.equals(materiasdeplan)) {
                        oldSeriadaConOfMateriasdeplanListNewMateriasdeplan.getMateriasdeplanList().remove(materiasdeplanListNewMateriasdeplan);
                        oldSeriadaConOfMateriasdeplanListNewMateriasdeplan = em.merge(oldSeriadaConOfMateriasdeplanListNewMateriasdeplan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materiasdeplan.getClaveMateria();
                if (findMateriasdeplan(id) == null) {
                    throw new NonexistentEntityException("The materiasdeplan with id " + id + " no longer exists.");
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
            Materiasdeplan materiasdeplan;
            try {
                materiasdeplan = em.getReference(Materiasdeplan.class, id);
                materiasdeplan.getClaveMateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiasdeplan with id " + id + " no longer exists.", enfe);
            }
            Materiasdeplan seriadaCon = materiasdeplan.getSeriadaCon();
            if (seriadaCon != null) {
                seriadaCon.getMateriasdeplanList().remove(materiasdeplan);
                seriadaCon = em.merge(seriadaCon);
            }
            Plandeestudios revoe = materiasdeplan.getRevoe();
            if (revoe != null) {
                revoe.getMateriasdeplanList().remove(materiasdeplan);
                revoe = em.merge(revoe);
            }
            List<Correspondencia> correspondenciaList = materiasdeplan.getCorrespondenciaList();
            for (Correspondencia correspondenciaListCorrespondencia : correspondenciaList) {
                correspondenciaListCorrespondencia.setClaveMateria(null);
                correspondenciaListCorrespondencia = em.merge(correspondenciaListCorrespondencia);
            }
            List<Materiasdeplan> materiasdeplanList = materiasdeplan.getMateriasdeplanList();
            for (Materiasdeplan materiasdeplanListMateriasdeplan : materiasdeplanList) {
                materiasdeplanListMateriasdeplan.setSeriadaCon(null);
                materiasdeplanListMateriasdeplan = em.merge(materiasdeplanListMateriasdeplan);
            }
            em.remove(materiasdeplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materiasdeplan> findMateriasdeplanEntities() {
        return findMateriasdeplanEntities(true, -1, -1);
    }

    public List<Materiasdeplan> findMateriasdeplanEntities(int maxResults, int firstResult) {
        return findMateriasdeplanEntities(false, maxResults, firstResult);
    }

    private List<Materiasdeplan> findMateriasdeplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Materiasdeplan as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materiasdeplan findMateriasdeplan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materiasdeplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriasdeplanCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Materiasdeplan as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
