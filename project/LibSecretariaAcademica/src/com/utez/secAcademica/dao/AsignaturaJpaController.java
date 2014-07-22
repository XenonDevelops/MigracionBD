/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.dao;

import com.utez.secAcademica.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.secAcademica.entity.Planestudios;
import com.utez.secAcademica.entity.Asignatura;
import com.utez.secAcademica.entity.Cuadernoprogramacion;
import java.util.ArrayList;
import java.util.List;
import com.utez.secAcademica.entity.Asesoriaasignatura;
import com.utez.secAcademica.entity.Bloqueasignatura;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getCuadernoprogramacionList() == null) {
            asignatura.setCuadernoprogramacionList(new ArrayList<Cuadernoprogramacion>());
        }
        if (asignatura.getAsignaturaList() == null) {
            asignatura.setAsignaturaList(new ArrayList<Asignatura>());
        }
        if (asignatura.getAsesoriaasignaturaList() == null) {
            asignatura.setAsesoriaasignaturaList(new ArrayList<Asesoriaasignatura>());
        }
        if (asignatura.getBloqueasignaturaList() == null) {
            asignatura.setBloqueasignaturaList(new ArrayList<Bloqueasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planestudios rvoe = asignatura.getRvoe();
            if (rvoe != null) {
                rvoe = em.getReference(rvoe.getClass(), rvoe.getRvoe());
                asignatura.setRvoe(rvoe);
            }
            Asignatura seriadacon = asignatura.getSeriadacon();
            if (seriadacon != null) {
                seriadacon = em.getReference(seriadacon.getClass(), seriadacon.getIdasignatura());
                asignatura.setSeriadacon(seriadacon);
            }
            List<Cuadernoprogramacion> attachedCuadernoprogramacionList = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacionToAttach : asignatura.getCuadernoprogramacionList()) {
                cuadernoprogramacionListCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionList.add(cuadernoprogramacionListCuadernoprogramacionToAttach);
            }
            asignatura.setCuadernoprogramacionList(attachedCuadernoprogramacionList);
            List<Asignatura> attachedAsignaturaList = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListAsignaturaToAttach : asignatura.getAsignaturaList()) {
                asignaturaListAsignaturaToAttach = em.getReference(asignaturaListAsignaturaToAttach.getClass(), asignaturaListAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaList.add(asignaturaListAsignaturaToAttach);
            }
            asignatura.setAsignaturaList(attachedAsignaturaList);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaList = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignaturaToAttach : asignatura.getAsesoriaasignaturaList()) {
                asesoriaasignaturaListAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaList.add(asesoriaasignaturaListAsesoriaasignaturaToAttach);
            }
            asignatura.setAsesoriaasignaturaList(attachedAsesoriaasignaturaList);
            List<Bloqueasignatura> attachedBloqueasignaturaList = new ArrayList<Bloqueasignatura>();
            for (Bloqueasignatura bloqueasignaturaListBloqueasignaturaToAttach : asignatura.getBloqueasignaturaList()) {
                bloqueasignaturaListBloqueasignaturaToAttach = em.getReference(bloqueasignaturaListBloqueasignaturaToAttach.getClass(), bloqueasignaturaListBloqueasignaturaToAttach.getIdbloqueasignatura());
                attachedBloqueasignaturaList.add(bloqueasignaturaListBloqueasignaturaToAttach);
            }
            asignatura.setBloqueasignaturaList(attachedBloqueasignaturaList);
            em.persist(asignatura);
            if (rvoe != null) {
                rvoe.getAsignaturaList().add(asignatura);
                rvoe = em.merge(rvoe);
            }
            if (seriadacon != null) {
                seriadacon.getAsignaturaList().add(asignatura);
                seriadacon = em.merge(seriadacon);
            }
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : asignatura.getCuadernoprogramacionList()) {
                Asignatura oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion = cuadernoprogramacionListCuadernoprogramacion.getIdasignatura();
                cuadernoprogramacionListCuadernoprogramacion.setIdasignatura(asignatura);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
                if (oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion != null) {
                    oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListCuadernoprogramacion);
                    oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion = em.merge(oldIdasignaturaOfCuadernoprogramacionListCuadernoprogramacion);
                }
            }
            for (Asignatura asignaturaListAsignatura : asignatura.getAsignaturaList()) {
                Asignatura oldSeriadaconOfAsignaturaListAsignatura = asignaturaListAsignatura.getSeriadacon();
                asignaturaListAsignatura.setSeriadacon(asignatura);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
                if (oldSeriadaconOfAsignaturaListAsignatura != null) {
                    oldSeriadaconOfAsignaturaListAsignatura.getAsignaturaList().remove(asignaturaListAsignatura);
                    oldSeriadaconOfAsignaturaListAsignatura = em.merge(oldSeriadaconOfAsignaturaListAsignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asignatura.getAsesoriaasignaturaList()) {
                Asignatura oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura = asesoriaasignaturaListAsesoriaasignatura.getIdasignatura();
                asesoriaasignaturaListAsesoriaasignatura.setIdasignatura(asignatura);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
                if (oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura != null) {
                    oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListAsesoriaasignatura);
                    oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura = em.merge(oldIdasignaturaOfAsesoriaasignaturaListAsesoriaasignatura);
                }
            }
            for (Bloqueasignatura bloqueasignaturaListBloqueasignatura : asignatura.getBloqueasignaturaList()) {
                Asignatura oldIdasignaturaOfBloqueasignaturaListBloqueasignatura = bloqueasignaturaListBloqueasignatura.getIdasignatura();
                bloqueasignaturaListBloqueasignatura.setIdasignatura(asignatura);
                bloqueasignaturaListBloqueasignatura = em.merge(bloqueasignaturaListBloqueasignatura);
                if (oldIdasignaturaOfBloqueasignaturaListBloqueasignatura != null) {
                    oldIdasignaturaOfBloqueasignaturaListBloqueasignatura.getBloqueasignaturaList().remove(bloqueasignaturaListBloqueasignatura);
                    oldIdasignaturaOfBloqueasignaturaListBloqueasignatura = em.merge(oldIdasignaturaOfBloqueasignaturaListBloqueasignatura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getIdasignatura());
            Planestudios rvoeOld = persistentAsignatura.getRvoe();
            Planestudios rvoeNew = asignatura.getRvoe();
            Asignatura seriadaconOld = persistentAsignatura.getSeriadacon();
            Asignatura seriadaconNew = asignatura.getSeriadacon();
            List<Cuadernoprogramacion> cuadernoprogramacionListOld = persistentAsignatura.getCuadernoprogramacionList();
            List<Cuadernoprogramacion> cuadernoprogramacionListNew = asignatura.getCuadernoprogramacionList();
            List<Asignatura> asignaturaListOld = persistentAsignatura.getAsignaturaList();
            List<Asignatura> asignaturaListNew = asignatura.getAsignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListOld = persistentAsignatura.getAsesoriaasignaturaList();
            List<Asesoriaasignatura> asesoriaasignaturaListNew = asignatura.getAsesoriaasignaturaList();
            List<Bloqueasignatura> bloqueasignaturaListOld = persistentAsignatura.getBloqueasignaturaList();
            List<Bloqueasignatura> bloqueasignaturaListNew = asignatura.getBloqueasignaturaList();
            if (rvoeNew != null) {
                rvoeNew = em.getReference(rvoeNew.getClass(), rvoeNew.getRvoe());
                asignatura.setRvoe(rvoeNew);
            }
            if (seriadaconNew != null) {
                seriadaconNew = em.getReference(seriadaconNew.getClass(), seriadaconNew.getIdasignatura());
                asignatura.setSeriadacon(seriadaconNew);
            }
            List<Cuadernoprogramacion> attachedCuadernoprogramacionListNew = new ArrayList<Cuadernoprogramacion>();
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacionToAttach : cuadernoprogramacionListNew) {
                cuadernoprogramacionListNewCuadernoprogramacionToAttach = em.getReference(cuadernoprogramacionListNewCuadernoprogramacionToAttach.getClass(), cuadernoprogramacionListNewCuadernoprogramacionToAttach.getIdcuadernoprogramacion());
                attachedCuadernoprogramacionListNew.add(cuadernoprogramacionListNewCuadernoprogramacionToAttach);
            }
            cuadernoprogramacionListNew = attachedCuadernoprogramacionListNew;
            asignatura.setCuadernoprogramacionList(cuadernoprogramacionListNew);
            List<Asignatura> attachedAsignaturaListNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaListNewAsignaturaToAttach : asignaturaListNew) {
                asignaturaListNewAsignaturaToAttach = em.getReference(asignaturaListNewAsignaturaToAttach.getClass(), asignaturaListNewAsignaturaToAttach.getIdasignatura());
                attachedAsignaturaListNew.add(asignaturaListNewAsignaturaToAttach);
            }
            asignaturaListNew = attachedAsignaturaListNew;
            asignatura.setAsignaturaList(asignaturaListNew);
            List<Asesoriaasignatura> attachedAsesoriaasignaturaListNew = new ArrayList<Asesoriaasignatura>();
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignaturaToAttach : asesoriaasignaturaListNew) {
                asesoriaasignaturaListNewAsesoriaasignaturaToAttach = em.getReference(asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getClass(), asesoriaasignaturaListNewAsesoriaasignaturaToAttach.getIdasesoriaasignatura());
                attachedAsesoriaasignaturaListNew.add(asesoriaasignaturaListNewAsesoriaasignaturaToAttach);
            }
            asesoriaasignaturaListNew = attachedAsesoriaasignaturaListNew;
            asignatura.setAsesoriaasignaturaList(asesoriaasignaturaListNew);
            List<Bloqueasignatura> attachedBloqueasignaturaListNew = new ArrayList<Bloqueasignatura>();
            for (Bloqueasignatura bloqueasignaturaListNewBloqueasignaturaToAttach : bloqueasignaturaListNew) {
                bloqueasignaturaListNewBloqueasignaturaToAttach = em.getReference(bloqueasignaturaListNewBloqueasignaturaToAttach.getClass(), bloqueasignaturaListNewBloqueasignaturaToAttach.getIdbloqueasignatura());
                attachedBloqueasignaturaListNew.add(bloqueasignaturaListNewBloqueasignaturaToAttach);
            }
            bloqueasignaturaListNew = attachedBloqueasignaturaListNew;
            asignatura.setBloqueasignaturaList(bloqueasignaturaListNew);
            asignatura = em.merge(asignatura);
            if (rvoeOld != null && !rvoeOld.equals(rvoeNew)) {
                rvoeOld.getAsignaturaList().remove(asignatura);
                rvoeOld = em.merge(rvoeOld);
            }
            if (rvoeNew != null && !rvoeNew.equals(rvoeOld)) {
                rvoeNew.getAsignaturaList().add(asignatura);
                rvoeNew = em.merge(rvoeNew);
            }
            if (seriadaconOld != null && !seriadaconOld.equals(seriadaconNew)) {
                seriadaconOld.getAsignaturaList().remove(asignatura);
                seriadaconOld = em.merge(seriadaconOld);
            }
            if (seriadaconNew != null && !seriadaconNew.equals(seriadaconOld)) {
                seriadaconNew.getAsignaturaList().add(asignatura);
                seriadaconNew = em.merge(seriadaconNew);
            }
            for (Cuadernoprogramacion cuadernoprogramacionListOldCuadernoprogramacion : cuadernoprogramacionListOld) {
                if (!cuadernoprogramacionListNew.contains(cuadernoprogramacionListOldCuadernoprogramacion)) {
                    cuadernoprogramacionListOldCuadernoprogramacion.setIdasignatura(null);
                    cuadernoprogramacionListOldCuadernoprogramacion = em.merge(cuadernoprogramacionListOldCuadernoprogramacion);
                }
            }
            for (Cuadernoprogramacion cuadernoprogramacionListNewCuadernoprogramacion : cuadernoprogramacionListNew) {
                if (!cuadernoprogramacionListOld.contains(cuadernoprogramacionListNewCuadernoprogramacion)) {
                    Asignatura oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion = cuadernoprogramacionListNewCuadernoprogramacion.getIdasignatura();
                    cuadernoprogramacionListNewCuadernoprogramacion.setIdasignatura(asignatura);
                    cuadernoprogramacionListNewCuadernoprogramacion = em.merge(cuadernoprogramacionListNewCuadernoprogramacion);
                    if (oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion != null && !oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion.equals(asignatura)) {
                        oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion.getCuadernoprogramacionList().remove(cuadernoprogramacionListNewCuadernoprogramacion);
                        oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion = em.merge(oldIdasignaturaOfCuadernoprogramacionListNewCuadernoprogramacion);
                    }
                }
            }
            for (Asignatura asignaturaListOldAsignatura : asignaturaListOld) {
                if (!asignaturaListNew.contains(asignaturaListOldAsignatura)) {
                    asignaturaListOldAsignatura.setSeriadacon(null);
                    asignaturaListOldAsignatura = em.merge(asignaturaListOldAsignatura);
                }
            }
            for (Asignatura asignaturaListNewAsignatura : asignaturaListNew) {
                if (!asignaturaListOld.contains(asignaturaListNewAsignatura)) {
                    Asignatura oldSeriadaconOfAsignaturaListNewAsignatura = asignaturaListNewAsignatura.getSeriadacon();
                    asignaturaListNewAsignatura.setSeriadacon(asignatura);
                    asignaturaListNewAsignatura = em.merge(asignaturaListNewAsignatura);
                    if (oldSeriadaconOfAsignaturaListNewAsignatura != null && !oldSeriadaconOfAsignaturaListNewAsignatura.equals(asignatura)) {
                        oldSeriadaconOfAsignaturaListNewAsignatura.getAsignaturaList().remove(asignaturaListNewAsignatura);
                        oldSeriadaconOfAsignaturaListNewAsignatura = em.merge(oldSeriadaconOfAsignaturaListNewAsignatura);
                    }
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListOldAsesoriaasignatura : asesoriaasignaturaListOld) {
                if (!asesoriaasignaturaListNew.contains(asesoriaasignaturaListOldAsesoriaasignatura)) {
                    asesoriaasignaturaListOldAsesoriaasignatura.setIdasignatura(null);
                    asesoriaasignaturaListOldAsesoriaasignatura = em.merge(asesoriaasignaturaListOldAsesoriaasignatura);
                }
            }
            for (Asesoriaasignatura asesoriaasignaturaListNewAsesoriaasignatura : asesoriaasignaturaListNew) {
                if (!asesoriaasignaturaListOld.contains(asesoriaasignaturaListNewAsesoriaasignatura)) {
                    Asignatura oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura = asesoriaasignaturaListNewAsesoriaasignatura.getIdasignatura();
                    asesoriaasignaturaListNewAsesoriaasignatura.setIdasignatura(asignatura);
                    asesoriaasignaturaListNewAsesoriaasignatura = em.merge(asesoriaasignaturaListNewAsesoriaasignatura);
                    if (oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura != null && !oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura.equals(asignatura)) {
                        oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura.getAsesoriaasignaturaList().remove(asesoriaasignaturaListNewAsesoriaasignatura);
                        oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura = em.merge(oldIdasignaturaOfAsesoriaasignaturaListNewAsesoriaasignatura);
                    }
                }
            }
            for (Bloqueasignatura bloqueasignaturaListOldBloqueasignatura : bloqueasignaturaListOld) {
                if (!bloqueasignaturaListNew.contains(bloqueasignaturaListOldBloqueasignatura)) {
                    bloqueasignaturaListOldBloqueasignatura.setIdasignatura(null);
                    bloqueasignaturaListOldBloqueasignatura = em.merge(bloqueasignaturaListOldBloqueasignatura);
                }
            }
            for (Bloqueasignatura bloqueasignaturaListNewBloqueasignatura : bloqueasignaturaListNew) {
                if (!bloqueasignaturaListOld.contains(bloqueasignaturaListNewBloqueasignatura)) {
                    Asignatura oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura = bloqueasignaturaListNewBloqueasignatura.getIdasignatura();
                    bloqueasignaturaListNewBloqueasignatura.setIdasignatura(asignatura);
                    bloqueasignaturaListNewBloqueasignatura = em.merge(bloqueasignaturaListNewBloqueasignatura);
                    if (oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura != null && !oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura.equals(asignatura)) {
                        oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura.getBloqueasignaturaList().remove(bloqueasignaturaListNewBloqueasignatura);
                        oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura = em.merge(oldIdasignaturaOfBloqueasignaturaListNewBloqueasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getIdasignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
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
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getIdasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            Planestudios rvoe = asignatura.getRvoe();
            if (rvoe != null) {
                rvoe.getAsignaturaList().remove(asignatura);
                rvoe = em.merge(rvoe);
            }
            Asignatura seriadacon = asignatura.getSeriadacon();
            if (seriadacon != null) {
                seriadacon.getAsignaturaList().remove(asignatura);
                seriadacon = em.merge(seriadacon);
            }
            List<Cuadernoprogramacion> cuadernoprogramacionList = asignatura.getCuadernoprogramacionList();
            for (Cuadernoprogramacion cuadernoprogramacionListCuadernoprogramacion : cuadernoprogramacionList) {
                cuadernoprogramacionListCuadernoprogramacion.setIdasignatura(null);
                cuadernoprogramacionListCuadernoprogramacion = em.merge(cuadernoprogramacionListCuadernoprogramacion);
            }
            List<Asignatura> asignaturaList = asignatura.getAsignaturaList();
            for (Asignatura asignaturaListAsignatura : asignaturaList) {
                asignaturaListAsignatura.setSeriadacon(null);
                asignaturaListAsignatura = em.merge(asignaturaListAsignatura);
            }
            List<Asesoriaasignatura> asesoriaasignaturaList = asignatura.getAsesoriaasignaturaList();
            for (Asesoriaasignatura asesoriaasignaturaListAsesoriaasignatura : asesoriaasignaturaList) {
                asesoriaasignaturaListAsesoriaasignatura.setIdasignatura(null);
                asesoriaasignaturaListAsesoriaasignatura = em.merge(asesoriaasignaturaListAsesoriaasignatura);
            }
            List<Bloqueasignatura> bloqueasignaturaList = asignatura.getBloqueasignaturaList();
            for (Bloqueasignatura bloqueasignaturaListBloqueasignatura : bloqueasignaturaList) {
                bloqueasignaturaListBloqueasignatura.setIdasignatura(null);
                bloqueasignaturaListBloqueasignatura = em.merge(bloqueasignaturaListBloqueasignatura);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Asignatura as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Asignatura as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
