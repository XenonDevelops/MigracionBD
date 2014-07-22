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
import com.utez.integracion.entity.AsignacionTipomovimientorecurso;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.AsignacionAmbitomovimientotipo;
import com.utez.integracion.entity.FolioCambioscalendario;
import com.utez.integracion.entity.BitacoraMovimiento;
import com.utez.integracion.entity.TipoMovimiento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class TipoMovimientoJpaController implements Serializable {

    public TipoMovimientoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoMovimiento tipoMovimiento) {
        if (tipoMovimiento.getAsignacionTipomovimientorecursoList() == null) {
            tipoMovimiento.setAsignacionTipomovimientorecursoList(new ArrayList<AsignacionTipomovimientorecurso>());
        }
        if (tipoMovimiento.getAsignacionAmbitomovimientotipoList() == null) {
            tipoMovimiento.setAsignacionAmbitomovimientotipoList(new ArrayList<AsignacionAmbitomovimientotipo>());
        }
        if (tipoMovimiento.getFolioCambioscalendarioList() == null) {
            tipoMovimiento.setFolioCambioscalendarioList(new ArrayList<FolioCambioscalendario>());
        }
        if (tipoMovimiento.getBitacoraMovimientoList() == null) {
            tipoMovimiento.setBitacoraMovimientoList(new ArrayList<BitacoraMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AsignacionTipomovimientorecurso> attachedAsignacionTipomovimientorecursoList = new ArrayList<AsignacionTipomovimientorecurso>();
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListAsignacionTipomovimientorecursoToAttach : tipoMovimiento.getAsignacionTipomovimientorecursoList()) {
                asignacionTipomovimientorecursoListAsignacionTipomovimientorecursoToAttach = em.getReference(asignacionTipomovimientorecursoListAsignacionTipomovimientorecursoToAttach.getClass(), asignacionTipomovimientorecursoListAsignacionTipomovimientorecursoToAttach.getAsignacionTipomovimientorecursoPK());
                attachedAsignacionTipomovimientorecursoList.add(asignacionTipomovimientorecursoListAsignacionTipomovimientorecursoToAttach);
            }
            tipoMovimiento.setAsignacionTipomovimientorecursoList(attachedAsignacionTipomovimientorecursoList);
            List<AsignacionAmbitomovimientotipo> attachedAsignacionAmbitomovimientotipoList = new ArrayList<AsignacionAmbitomovimientotipo>();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach : tipoMovimiento.getAsignacionAmbitomovimientotipoList()) {
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach = em.getReference(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach.getClass(), asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach.getAsignacionAmbitomovimientotipoPK());
                attachedAsignacionAmbitomovimientotipoList.add(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipoToAttach);
            }
            tipoMovimiento.setAsignacionAmbitomovimientotipoList(attachedAsignacionAmbitomovimientotipoList);
            List<FolioCambioscalendario> attachedFolioCambioscalendarioList = new ArrayList<FolioCambioscalendario>();
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendarioToAttach : tipoMovimiento.getFolioCambioscalendarioList()) {
                folioCambioscalendarioListFolioCambioscalendarioToAttach = em.getReference(folioCambioscalendarioListFolioCambioscalendarioToAttach.getClass(), folioCambioscalendarioListFolioCambioscalendarioToAttach.getFolioCambioscalendarioPK());
                attachedFolioCambioscalendarioList.add(folioCambioscalendarioListFolioCambioscalendarioToAttach);
            }
            tipoMovimiento.setFolioCambioscalendarioList(attachedFolioCambioscalendarioList);
            List<BitacoraMovimiento> attachedBitacoraMovimientoList = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimientoToAttach : tipoMovimiento.getBitacoraMovimientoList()) {
                bitacoraMovimientoListBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoList.add(bitacoraMovimientoListBitacoraMovimientoToAttach);
            }
            tipoMovimiento.setBitacoraMovimientoList(attachedBitacoraMovimientoList);
            em.persist(tipoMovimiento);
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso : tipoMovimiento.getAsignacionTipomovimientorecursoList()) {
                TipoMovimiento oldTipoMovimientoOfAsignacionTipomovimientorecursoListAsignacionTipomovimientorecurso = asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso.getTipoMovimiento();
                asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso.setTipoMovimiento(tipoMovimiento);
                asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso = em.merge(asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso);
                if (oldTipoMovimientoOfAsignacionTipomovimientorecursoListAsignacionTipomovimientorecurso != null) {
                    oldTipoMovimientoOfAsignacionTipomovimientorecursoListAsignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecursoListAsignacionTipomovimientorecurso);
                    oldTipoMovimientoOfAsignacionTipomovimientorecursoListAsignacionTipomovimientorecurso = em.merge(oldTipoMovimientoOfAsignacionTipomovimientorecursoListAsignacionTipomovimientorecurso);
                }
            }
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo : tipoMovimiento.getAsignacionAmbitomovimientotipoList()) {
                TipoMovimiento oldTipoMovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.getTipoMovimiento();
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.setTipoMovimiento(tipoMovimiento);
                asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = em.merge(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                if (oldTipoMovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo != null) {
                    oldTipoMovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                    oldTipoMovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo = em.merge(oldTipoMovimientoOfAsignacionAmbitomovimientotipoListAsignacionAmbitomovimientotipo);
                }
            }
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendario : tipoMovimiento.getFolioCambioscalendarioList()) {
                TipoMovimiento oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario = folioCambioscalendarioListFolioCambioscalendario.getIdMovimiento();
                folioCambioscalendarioListFolioCambioscalendario.setIdMovimiento(tipoMovimiento);
                folioCambioscalendarioListFolioCambioscalendario = em.merge(folioCambioscalendarioListFolioCambioscalendario);
                if (oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario != null) {
                    oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario.getFolioCambioscalendarioList().remove(folioCambioscalendarioListFolioCambioscalendario);
                    oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario = em.merge(oldIdMovimientoOfFolioCambioscalendarioListFolioCambioscalendario);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : tipoMovimiento.getBitacoraMovimientoList()) {
                TipoMovimiento oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento = bitacoraMovimientoListBitacoraMovimiento.getIdTipomovimiento();
                bitacoraMovimientoListBitacoraMovimiento.setIdTipomovimiento(tipoMovimiento);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
                if (oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento != null) {
                    oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListBitacoraMovimiento);
                    oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento = em.merge(oldIdTipomovimientoOfBitacoraMovimientoListBitacoraMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoMovimiento tipoMovimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoMovimiento persistentTipoMovimiento = em.find(TipoMovimiento.class, tipoMovimiento.getIdTipomovimiento());
            List<AsignacionTipomovimientorecurso> asignacionTipomovimientorecursoListOld = persistentTipoMovimiento.getAsignacionTipomovimientorecursoList();
            List<AsignacionTipomovimientorecurso> asignacionTipomovimientorecursoListNew = tipoMovimiento.getAsignacionTipomovimientorecursoList();
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListOld = persistentTipoMovimiento.getAsignacionAmbitomovimientotipoList();
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListNew = tipoMovimiento.getAsignacionAmbitomovimientotipoList();
            List<FolioCambioscalendario> folioCambioscalendarioListOld = persistentTipoMovimiento.getFolioCambioscalendarioList();
            List<FolioCambioscalendario> folioCambioscalendarioListNew = tipoMovimiento.getFolioCambioscalendarioList();
            List<BitacoraMovimiento> bitacoraMovimientoListOld = persistentTipoMovimiento.getBitacoraMovimientoList();
            List<BitacoraMovimiento> bitacoraMovimientoListNew = tipoMovimiento.getBitacoraMovimientoList();
            List<String> illegalOrphanMessages = null;
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListOldAsignacionTipomovimientorecurso : asignacionTipomovimientorecursoListOld) {
                if (!asignacionTipomovimientorecursoListNew.contains(asignacionTipomovimientorecursoListOldAsignacionTipomovimientorecurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionTipomovimientorecurso " + asignacionTipomovimientorecursoListOldAsignacionTipomovimientorecurso + " since its tipoMovimiento field is not nullable.");
                }
            }
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListOld) {
                if (!asignacionAmbitomovimientotipoListNew.contains(asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionAmbitomovimientotipo " + asignacionAmbitomovimientotipoListOldAsignacionAmbitomovimientotipo + " since its tipoMovimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AsignacionTipomovimientorecurso> attachedAsignacionTipomovimientorecursoListNew = new ArrayList<AsignacionTipomovimientorecurso>();
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecursoToAttach : asignacionTipomovimientorecursoListNew) {
                asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecursoToAttach = em.getReference(asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecursoToAttach.getClass(), asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecursoToAttach.getAsignacionTipomovimientorecursoPK());
                attachedAsignacionTipomovimientorecursoListNew.add(asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecursoToAttach);
            }
            asignacionTipomovimientorecursoListNew = attachedAsignacionTipomovimientorecursoListNew;
            tipoMovimiento.setAsignacionTipomovimientorecursoList(asignacionTipomovimientorecursoListNew);
            List<AsignacionAmbitomovimientotipo> attachedAsignacionAmbitomovimientotipoListNew = new ArrayList<AsignacionAmbitomovimientotipo>();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach : asignacionAmbitomovimientotipoListNew) {
                asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach = em.getReference(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach.getClass(), asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach.getAsignacionAmbitomovimientotipoPK());
                attachedAsignacionAmbitomovimientotipoListNew.add(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipoToAttach);
            }
            asignacionAmbitomovimientotipoListNew = attachedAsignacionAmbitomovimientotipoListNew;
            tipoMovimiento.setAsignacionAmbitomovimientotipoList(asignacionAmbitomovimientotipoListNew);
            List<FolioCambioscalendario> attachedFolioCambioscalendarioListNew = new ArrayList<FolioCambioscalendario>();
            for (FolioCambioscalendario folioCambioscalendarioListNewFolioCambioscalendarioToAttach : folioCambioscalendarioListNew) {
                folioCambioscalendarioListNewFolioCambioscalendarioToAttach = em.getReference(folioCambioscalendarioListNewFolioCambioscalendarioToAttach.getClass(), folioCambioscalendarioListNewFolioCambioscalendarioToAttach.getFolioCambioscalendarioPK());
                attachedFolioCambioscalendarioListNew.add(folioCambioscalendarioListNewFolioCambioscalendarioToAttach);
            }
            folioCambioscalendarioListNew = attachedFolioCambioscalendarioListNew;
            tipoMovimiento.setFolioCambioscalendarioList(folioCambioscalendarioListNew);
            List<BitacoraMovimiento> attachedBitacoraMovimientoListNew = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimientoToAttach : bitacoraMovimientoListNew) {
                bitacoraMovimientoListNewBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListNewBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListNewBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoListNew.add(bitacoraMovimientoListNewBitacoraMovimientoToAttach);
            }
            bitacoraMovimientoListNew = attachedBitacoraMovimientoListNew;
            tipoMovimiento.setBitacoraMovimientoList(bitacoraMovimientoListNew);
            tipoMovimiento = em.merge(tipoMovimiento);
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso : asignacionTipomovimientorecursoListNew) {
                if (!asignacionTipomovimientorecursoListOld.contains(asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso)) {
                    TipoMovimiento oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso = asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso.getTipoMovimiento();
                    asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso.setTipoMovimiento(tipoMovimiento);
                    asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso = em.merge(asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso);
                    if (oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso != null && !oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso.equals(tipoMovimiento)) {
                        oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso.getAsignacionTipomovimientorecursoList().remove(asignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso);
                        oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso = em.merge(oldTipoMovimientoOfAsignacionTipomovimientorecursoListNewAsignacionTipomovimientorecurso);
                    }
                }
            }
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListNew) {
                if (!asignacionAmbitomovimientotipoListOld.contains(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo)) {
                    TipoMovimiento oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.getTipoMovimiento();
                    asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.setTipoMovimiento(tipoMovimiento);
                    asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = em.merge(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                    if (oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo != null && !oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.equals(tipoMovimiento)) {
                        oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo.getAsignacionAmbitomovimientotipoList().remove(asignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                        oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo = em.merge(oldTipoMovimientoOfAsignacionAmbitomovimientotipoListNewAsignacionAmbitomovimientotipo);
                    }
                }
            }
            for (FolioCambioscalendario folioCambioscalendarioListOldFolioCambioscalendario : folioCambioscalendarioListOld) {
                if (!folioCambioscalendarioListNew.contains(folioCambioscalendarioListOldFolioCambioscalendario)) {
                    folioCambioscalendarioListOldFolioCambioscalendario.setIdMovimiento(null);
                    folioCambioscalendarioListOldFolioCambioscalendario = em.merge(folioCambioscalendarioListOldFolioCambioscalendario);
                }
            }
            for (FolioCambioscalendario folioCambioscalendarioListNewFolioCambioscalendario : folioCambioscalendarioListNew) {
                if (!folioCambioscalendarioListOld.contains(folioCambioscalendarioListNewFolioCambioscalendario)) {
                    TipoMovimiento oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario = folioCambioscalendarioListNewFolioCambioscalendario.getIdMovimiento();
                    folioCambioscalendarioListNewFolioCambioscalendario.setIdMovimiento(tipoMovimiento);
                    folioCambioscalendarioListNewFolioCambioscalendario = em.merge(folioCambioscalendarioListNewFolioCambioscalendario);
                    if (oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario != null && !oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario.equals(tipoMovimiento)) {
                        oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario.getFolioCambioscalendarioList().remove(folioCambioscalendarioListNewFolioCambioscalendario);
                        oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario = em.merge(oldIdMovimientoOfFolioCambioscalendarioListNewFolioCambioscalendario);
                    }
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListOldBitacoraMovimiento : bitacoraMovimientoListOld) {
                if (!bitacoraMovimientoListNew.contains(bitacoraMovimientoListOldBitacoraMovimiento)) {
                    bitacoraMovimientoListOldBitacoraMovimiento.setIdTipomovimiento(null);
                    bitacoraMovimientoListOldBitacoraMovimiento = em.merge(bitacoraMovimientoListOldBitacoraMovimiento);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimiento : bitacoraMovimientoListNew) {
                if (!bitacoraMovimientoListOld.contains(bitacoraMovimientoListNewBitacoraMovimiento)) {
                    TipoMovimiento oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento = bitacoraMovimientoListNewBitacoraMovimiento.getIdTipomovimiento();
                    bitacoraMovimientoListNewBitacoraMovimiento.setIdTipomovimiento(tipoMovimiento);
                    bitacoraMovimientoListNewBitacoraMovimiento = em.merge(bitacoraMovimientoListNewBitacoraMovimiento);
                    if (oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento != null && !oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento.equals(tipoMovimiento)) {
                        oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListNewBitacoraMovimiento);
                        oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento = em.merge(oldIdTipomovimientoOfBitacoraMovimientoListNewBitacoraMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoMovimiento.getIdTipomovimiento();
                if (findTipoMovimiento(id) == null) {
                    throw new NonexistentEntityException("The tipoMovimiento with id " + id + " no longer exists.");
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
            TipoMovimiento tipoMovimiento;
            try {
                tipoMovimiento = em.getReference(TipoMovimiento.class, id);
                tipoMovimiento.getIdTipomovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoMovimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AsignacionTipomovimientorecurso> asignacionTipomovimientorecursoListOrphanCheck = tipoMovimiento.getAsignacionTipomovimientorecursoList();
            for (AsignacionTipomovimientorecurso asignacionTipomovimientorecursoListOrphanCheckAsignacionTipomovimientorecurso : asignacionTipomovimientorecursoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMovimiento (" + tipoMovimiento + ") cannot be destroyed since the AsignacionTipomovimientorecurso " + asignacionTipomovimientorecursoListOrphanCheckAsignacionTipomovimientorecurso + " in its asignacionTipomovimientorecursoList field has a non-nullable tipoMovimiento field.");
            }
            List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoListOrphanCheck = tipoMovimiento.getAsignacionAmbitomovimientotipoList();
            for (AsignacionAmbitomovimientotipo asignacionAmbitomovimientotipoListOrphanCheckAsignacionAmbitomovimientotipo : asignacionAmbitomovimientotipoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoMovimiento (" + tipoMovimiento + ") cannot be destroyed since the AsignacionAmbitomovimientotipo " + asignacionAmbitomovimientotipoListOrphanCheckAsignacionAmbitomovimientotipo + " in its asignacionAmbitomovimientotipoList field has a non-nullable tipoMovimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<FolioCambioscalendario> folioCambioscalendarioList = tipoMovimiento.getFolioCambioscalendarioList();
            for (FolioCambioscalendario folioCambioscalendarioListFolioCambioscalendario : folioCambioscalendarioList) {
                folioCambioscalendarioListFolioCambioscalendario.setIdMovimiento(null);
                folioCambioscalendarioListFolioCambioscalendario = em.merge(folioCambioscalendarioListFolioCambioscalendario);
            }
            List<BitacoraMovimiento> bitacoraMovimientoList = tipoMovimiento.getBitacoraMovimientoList();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : bitacoraMovimientoList) {
                bitacoraMovimientoListBitacoraMovimiento.setIdTipomovimiento(null);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
            }
            em.remove(tipoMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoMovimiento> findTipoMovimientoEntities() {
        return findTipoMovimientoEntities(true, -1, -1);
    }

    public List<TipoMovimiento> findTipoMovimientoEntities(int maxResults, int firstResult) {
        return findTipoMovimientoEntities(false, maxResults, firstResult);
    }

    private List<TipoMovimiento> findTipoMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from TipoMovimiento as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TipoMovimiento findTipoMovimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from TipoMovimiento as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
