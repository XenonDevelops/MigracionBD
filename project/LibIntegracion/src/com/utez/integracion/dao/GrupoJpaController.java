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
import com.utez.integracion.entity.OpcionEstudio;
import com.utez.integracion.entity.Generacion;
import com.utez.integracion.entity.CalendarioEscolar;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.Acta;
import com.utez.integracion.entity.AsignacionEvaluacion;
import com.utez.integracion.entity.BitacoraNotificacion;
import com.utez.integracion.entity.AsignacionGrupoencuesta;
import com.utez.integracion.entity.GrupoAlumnoesquema;
import com.utez.integracion.entity.AsignacionGrupo;
import com.utez.integracion.entity.Grupo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getCalendarioEscolarList() == null) {
            grupo.setCalendarioEscolarList(new ArrayList<CalendarioEscolar>());
        }
        if (grupo.getActaList() == null) {
            grupo.setActaList(new ArrayList<Acta>());
        }
        if (grupo.getAsignacionEvaluacionList() == null) {
            grupo.setAsignacionEvaluacionList(new ArrayList<AsignacionEvaluacion>());
        }
        if (grupo.getBitacoraNotificacionList() == null) {
            grupo.setBitacoraNotificacionList(new ArrayList<BitacoraNotificacion>());
        }
        if (grupo.getAsignacionGrupoencuestaList() == null) {
            grupo.setAsignacionGrupoencuestaList(new ArrayList<AsignacionGrupoencuesta>());
        }
        if (grupo.getGrupoAlumnoesquemaList() == null) {
            grupo.setGrupoAlumnoesquemaList(new ArrayList<GrupoAlumnoesquema>());
        }
        if (grupo.getAsignacionGrupoList() == null) {
            grupo.setAsignacionGrupoList(new ArrayList<AsignacionGrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpcionEstudio idOpcionestudio = grupo.getIdOpcionestudio();
            if (idOpcionestudio != null) {
                idOpcionestudio = em.getReference(idOpcionestudio.getClass(), idOpcionestudio.getIdOpcionestudio());
                grupo.setIdOpcionestudio(idOpcionestudio);
            }
            Generacion idGeneracion = grupo.getIdGeneracion();
            if (idGeneracion != null) {
                idGeneracion = em.getReference(idGeneracion.getClass(), idGeneracion.getIdGeneracion());
                grupo.setIdGeneracion(idGeneracion);
            }
            List<CalendarioEscolar> attachedCalendarioEscolarList = new ArrayList<CalendarioEscolar>();
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolarToAttach : grupo.getCalendarioEscolarList()) {
                calendarioEscolarListCalendarioEscolarToAttach = em.getReference(calendarioEscolarListCalendarioEscolarToAttach.getClass(), calendarioEscolarListCalendarioEscolarToAttach.getIdCalendarioescolar());
                attachedCalendarioEscolarList.add(calendarioEscolarListCalendarioEscolarToAttach);
            }
            grupo.setCalendarioEscolarList(attachedCalendarioEscolarList);
            List<Acta> attachedActaList = new ArrayList<Acta>();
            for (Acta actaListActaToAttach : grupo.getActaList()) {
                actaListActaToAttach = em.getReference(actaListActaToAttach.getClass(), actaListActaToAttach.getIdActa());
                attachedActaList.add(actaListActaToAttach);
            }
            grupo.setActaList(attachedActaList);
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionList = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacionToAttach : grupo.getAsignacionEvaluacionList()) {
                asignacionEvaluacionListAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionList.add(asignacionEvaluacionListAsignacionEvaluacionToAttach);
            }
            grupo.setAsignacionEvaluacionList(attachedAsignacionEvaluacionList);
            List<BitacoraNotificacion> attachedBitacoraNotificacionList = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacionToAttach : grupo.getBitacoraNotificacionList()) {
                bitacoraNotificacionListBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionList.add(bitacoraNotificacionListBitacoraNotificacionToAttach);
            }
            grupo.setBitacoraNotificacionList(attachedBitacoraNotificacionList);
            List<AsignacionGrupoencuesta> attachedAsignacionGrupoencuestaList = new ArrayList<AsignacionGrupoencuesta>();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach : grupo.getAsignacionGrupoencuestaList()) {
                asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach = em.getReference(asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach.getClass(), asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach.getIdAsignaciongrupoencuesta());
                attachedAsignacionGrupoencuestaList.add(asignacionGrupoencuestaListAsignacionGrupoencuestaToAttach);
            }
            grupo.setAsignacionGrupoencuestaList(attachedAsignacionGrupoencuestaList);
            List<GrupoAlumnoesquema> attachedGrupoAlumnoesquemaList = new ArrayList<GrupoAlumnoesquema>();
            for (GrupoAlumnoesquema grupoAlumnoesquemaListGrupoAlumnoesquemaToAttach : grupo.getGrupoAlumnoesquemaList()) {
                grupoAlumnoesquemaListGrupoAlumnoesquemaToAttach = em.getReference(grupoAlumnoesquemaListGrupoAlumnoesquemaToAttach.getClass(), grupoAlumnoesquemaListGrupoAlumnoesquemaToAttach.getIdEsquemaalumnoasignatura());
                attachedGrupoAlumnoesquemaList.add(grupoAlumnoesquemaListGrupoAlumnoesquemaToAttach);
            }
            grupo.setGrupoAlumnoesquemaList(attachedGrupoAlumnoesquemaList);
            List<AsignacionGrupo> attachedAsignacionGrupoList = new ArrayList<AsignacionGrupo>();
            for (AsignacionGrupo asignacionGrupoListAsignacionGrupoToAttach : grupo.getAsignacionGrupoList()) {
                asignacionGrupoListAsignacionGrupoToAttach = em.getReference(asignacionGrupoListAsignacionGrupoToAttach.getClass(), asignacionGrupoListAsignacionGrupoToAttach.getIdAsignaciongrupo());
                attachedAsignacionGrupoList.add(asignacionGrupoListAsignacionGrupoToAttach);
            }
            grupo.setAsignacionGrupoList(attachedAsignacionGrupoList);
            em.persist(grupo);
            if (idOpcionestudio != null) {
                idOpcionestudio.getGrupoList().add(grupo);
                idOpcionestudio = em.merge(idOpcionestudio);
            }
            if (idGeneracion != null) {
                idGeneracion.getGrupoList().add(grupo);
                idGeneracion = em.merge(idGeneracion);
            }
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolar : grupo.getCalendarioEscolarList()) {
                Grupo oldIdGrupoOfCalendarioEscolarListCalendarioEscolar = calendarioEscolarListCalendarioEscolar.getIdGrupo();
                calendarioEscolarListCalendarioEscolar.setIdGrupo(grupo);
                calendarioEscolarListCalendarioEscolar = em.merge(calendarioEscolarListCalendarioEscolar);
                if (oldIdGrupoOfCalendarioEscolarListCalendarioEscolar != null) {
                    oldIdGrupoOfCalendarioEscolarListCalendarioEscolar.getCalendarioEscolarList().remove(calendarioEscolarListCalendarioEscolar);
                    oldIdGrupoOfCalendarioEscolarListCalendarioEscolar = em.merge(oldIdGrupoOfCalendarioEscolarListCalendarioEscolar);
                }
            }
            for (Acta actaListActa : grupo.getActaList()) {
                Grupo oldIdGrupoOfActaListActa = actaListActa.getIdGrupo();
                actaListActa.setIdGrupo(grupo);
                actaListActa = em.merge(actaListActa);
                if (oldIdGrupoOfActaListActa != null) {
                    oldIdGrupoOfActaListActa.getActaList().remove(actaListActa);
                    oldIdGrupoOfActaListActa = em.merge(oldIdGrupoOfActaListActa);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : grupo.getAsignacionEvaluacionList()) {
                Grupo oldIdGrupoOfAsignacionEvaluacionListAsignacionEvaluacion = asignacionEvaluacionListAsignacionEvaluacion.getIdGrupo();
                asignacionEvaluacionListAsignacionEvaluacion.setIdGrupo(grupo);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
                if (oldIdGrupoOfAsignacionEvaluacionListAsignacionEvaluacion != null) {
                    oldIdGrupoOfAsignacionEvaluacionListAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListAsignacionEvaluacion);
                    oldIdGrupoOfAsignacionEvaluacionListAsignacionEvaluacion = em.merge(oldIdGrupoOfAsignacionEvaluacionListAsignacionEvaluacion);
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : grupo.getBitacoraNotificacionList()) {
                Grupo oldIdGrupoOfBitacoraNotificacionListBitacoraNotificacion = bitacoraNotificacionListBitacoraNotificacion.getIdGrupo();
                bitacoraNotificacionListBitacoraNotificacion.setIdGrupo(grupo);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
                if (oldIdGrupoOfBitacoraNotificacionListBitacoraNotificacion != null) {
                    oldIdGrupoOfBitacoraNotificacionListBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListBitacoraNotificacion);
                    oldIdGrupoOfBitacoraNotificacionListBitacoraNotificacion = em.merge(oldIdGrupoOfBitacoraNotificacionListBitacoraNotificacion);
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuesta : grupo.getAsignacionGrupoencuestaList()) {
                Grupo oldIdGrupoOfAsignacionGrupoencuestaListAsignacionGrupoencuesta = asignacionGrupoencuestaListAsignacionGrupoencuesta.getIdGrupo();
                asignacionGrupoencuestaListAsignacionGrupoencuesta.setIdGrupo(grupo);
                asignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListAsignacionGrupoencuesta);
                if (oldIdGrupoOfAsignacionGrupoencuestaListAsignacionGrupoencuesta != null) {
                    oldIdGrupoOfAsignacionGrupoencuestaListAsignacionGrupoencuesta.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuestaListAsignacionGrupoencuesta);
                    oldIdGrupoOfAsignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(oldIdGrupoOfAsignacionGrupoencuestaListAsignacionGrupoencuesta);
                }
            }
            for (GrupoAlumnoesquema grupoAlumnoesquemaListGrupoAlumnoesquema : grupo.getGrupoAlumnoesquemaList()) {
                Grupo oldIdGrupoOfGrupoAlumnoesquemaListGrupoAlumnoesquema = grupoAlumnoesquemaListGrupoAlumnoesquema.getIdGrupo();
                grupoAlumnoesquemaListGrupoAlumnoesquema.setIdGrupo(grupo);
                grupoAlumnoesquemaListGrupoAlumnoesquema = em.merge(grupoAlumnoesquemaListGrupoAlumnoesquema);
                if (oldIdGrupoOfGrupoAlumnoesquemaListGrupoAlumnoesquema != null) {
                    oldIdGrupoOfGrupoAlumnoesquemaListGrupoAlumnoesquema.getGrupoAlumnoesquemaList().remove(grupoAlumnoesquemaListGrupoAlumnoesquema);
                    oldIdGrupoOfGrupoAlumnoesquemaListGrupoAlumnoesquema = em.merge(oldIdGrupoOfGrupoAlumnoesquemaListGrupoAlumnoesquema);
                }
            }
            for (AsignacionGrupo asignacionGrupoListAsignacionGrupo : grupo.getAsignacionGrupoList()) {
                Grupo oldIdGrupoOfAsignacionGrupoListAsignacionGrupo = asignacionGrupoListAsignacionGrupo.getIdGrupo();
                asignacionGrupoListAsignacionGrupo.setIdGrupo(grupo);
                asignacionGrupoListAsignacionGrupo = em.merge(asignacionGrupoListAsignacionGrupo);
                if (oldIdGrupoOfAsignacionGrupoListAsignacionGrupo != null) {
                    oldIdGrupoOfAsignacionGrupoListAsignacionGrupo.getAsignacionGrupoList().remove(asignacionGrupoListAsignacionGrupo);
                    oldIdGrupoOfAsignacionGrupoListAsignacionGrupo = em.merge(oldIdGrupoOfAsignacionGrupoListAsignacionGrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdGrupo());
            OpcionEstudio idOpcionestudioOld = persistentGrupo.getIdOpcionestudio();
            OpcionEstudio idOpcionestudioNew = grupo.getIdOpcionestudio();
            Generacion idGeneracionOld = persistentGrupo.getIdGeneracion();
            Generacion idGeneracionNew = grupo.getIdGeneracion();
            List<CalendarioEscolar> calendarioEscolarListOld = persistentGrupo.getCalendarioEscolarList();
            List<CalendarioEscolar> calendarioEscolarListNew = grupo.getCalendarioEscolarList();
            List<Acta> actaListOld = persistentGrupo.getActaList();
            List<Acta> actaListNew = grupo.getActaList();
            List<AsignacionEvaluacion> asignacionEvaluacionListOld = persistentGrupo.getAsignacionEvaluacionList();
            List<AsignacionEvaluacion> asignacionEvaluacionListNew = grupo.getAsignacionEvaluacionList();
            List<BitacoraNotificacion> bitacoraNotificacionListOld = persistentGrupo.getBitacoraNotificacionList();
            List<BitacoraNotificacion> bitacoraNotificacionListNew = grupo.getBitacoraNotificacionList();
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaListOld = persistentGrupo.getAsignacionGrupoencuestaList();
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaListNew = grupo.getAsignacionGrupoencuestaList();
            List<GrupoAlumnoesquema> grupoAlumnoesquemaListOld = persistentGrupo.getGrupoAlumnoesquemaList();
            List<GrupoAlumnoesquema> grupoAlumnoesquemaListNew = grupo.getGrupoAlumnoesquemaList();
            List<AsignacionGrupo> asignacionGrupoListOld = persistentGrupo.getAsignacionGrupoList();
            List<AsignacionGrupo> asignacionGrupoListNew = grupo.getAsignacionGrupoList();
            List<String> illegalOrphanMessages = null;
            for (AsignacionGrupo asignacionGrupoListOldAsignacionGrupo : asignacionGrupoListOld) {
                if (!asignacionGrupoListNew.contains(asignacionGrupoListOldAsignacionGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsignacionGrupo " + asignacionGrupoListOldAsignacionGrupo + " since its idGrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idOpcionestudioNew != null) {
                idOpcionestudioNew = em.getReference(idOpcionestudioNew.getClass(), idOpcionestudioNew.getIdOpcionestudio());
                grupo.setIdOpcionestudio(idOpcionestudioNew);
            }
            if (idGeneracionNew != null) {
                idGeneracionNew = em.getReference(idGeneracionNew.getClass(), idGeneracionNew.getIdGeneracion());
                grupo.setIdGeneracion(idGeneracionNew);
            }
            List<CalendarioEscolar> attachedCalendarioEscolarListNew = new ArrayList<CalendarioEscolar>();
            for (CalendarioEscolar calendarioEscolarListNewCalendarioEscolarToAttach : calendarioEscolarListNew) {
                calendarioEscolarListNewCalendarioEscolarToAttach = em.getReference(calendarioEscolarListNewCalendarioEscolarToAttach.getClass(), calendarioEscolarListNewCalendarioEscolarToAttach.getIdCalendarioescolar());
                attachedCalendarioEscolarListNew.add(calendarioEscolarListNewCalendarioEscolarToAttach);
            }
            calendarioEscolarListNew = attachedCalendarioEscolarListNew;
            grupo.setCalendarioEscolarList(calendarioEscolarListNew);
            List<Acta> attachedActaListNew = new ArrayList<Acta>();
            for (Acta actaListNewActaToAttach : actaListNew) {
                actaListNewActaToAttach = em.getReference(actaListNewActaToAttach.getClass(), actaListNewActaToAttach.getIdActa());
                attachedActaListNew.add(actaListNewActaToAttach);
            }
            actaListNew = attachedActaListNew;
            grupo.setActaList(actaListNew);
            List<AsignacionEvaluacion> attachedAsignacionEvaluacionListNew = new ArrayList<AsignacionEvaluacion>();
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacionToAttach : asignacionEvaluacionListNew) {
                asignacionEvaluacionListNewAsignacionEvaluacionToAttach = em.getReference(asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getClass(), asignacionEvaluacionListNewAsignacionEvaluacionToAttach.getIdAsignacionevaluacion());
                attachedAsignacionEvaluacionListNew.add(asignacionEvaluacionListNewAsignacionEvaluacionToAttach);
            }
            asignacionEvaluacionListNew = attachedAsignacionEvaluacionListNew;
            grupo.setAsignacionEvaluacionList(asignacionEvaluacionListNew);
            List<BitacoraNotificacion> attachedBitacoraNotificacionListNew = new ArrayList<BitacoraNotificacion>();
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacionToAttach : bitacoraNotificacionListNew) {
                bitacoraNotificacionListNewBitacoraNotificacionToAttach = em.getReference(bitacoraNotificacionListNewBitacoraNotificacionToAttach.getClass(), bitacoraNotificacionListNewBitacoraNotificacionToAttach.getIdBitacoranotificacion());
                attachedBitacoraNotificacionListNew.add(bitacoraNotificacionListNewBitacoraNotificacionToAttach);
            }
            bitacoraNotificacionListNew = attachedBitacoraNotificacionListNew;
            grupo.setBitacoraNotificacionList(bitacoraNotificacionListNew);
            List<AsignacionGrupoencuesta> attachedAsignacionGrupoencuestaListNew = new ArrayList<AsignacionGrupoencuesta>();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach : asignacionGrupoencuestaListNew) {
                asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach = em.getReference(asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach.getClass(), asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach.getIdAsignaciongrupoencuesta());
                attachedAsignacionGrupoencuestaListNew.add(asignacionGrupoencuestaListNewAsignacionGrupoencuestaToAttach);
            }
            asignacionGrupoencuestaListNew = attachedAsignacionGrupoencuestaListNew;
            grupo.setAsignacionGrupoencuestaList(asignacionGrupoencuestaListNew);
            List<GrupoAlumnoesquema> attachedGrupoAlumnoesquemaListNew = new ArrayList<GrupoAlumnoesquema>();
            for (GrupoAlumnoesquema grupoAlumnoesquemaListNewGrupoAlumnoesquemaToAttach : grupoAlumnoesquemaListNew) {
                grupoAlumnoesquemaListNewGrupoAlumnoesquemaToAttach = em.getReference(grupoAlumnoesquemaListNewGrupoAlumnoesquemaToAttach.getClass(), grupoAlumnoesquemaListNewGrupoAlumnoesquemaToAttach.getIdEsquemaalumnoasignatura());
                attachedGrupoAlumnoesquemaListNew.add(grupoAlumnoesquemaListNewGrupoAlumnoesquemaToAttach);
            }
            grupoAlumnoesquemaListNew = attachedGrupoAlumnoesquemaListNew;
            grupo.setGrupoAlumnoesquemaList(grupoAlumnoesquemaListNew);
            List<AsignacionGrupo> attachedAsignacionGrupoListNew = new ArrayList<AsignacionGrupo>();
            for (AsignacionGrupo asignacionGrupoListNewAsignacionGrupoToAttach : asignacionGrupoListNew) {
                asignacionGrupoListNewAsignacionGrupoToAttach = em.getReference(asignacionGrupoListNewAsignacionGrupoToAttach.getClass(), asignacionGrupoListNewAsignacionGrupoToAttach.getIdAsignaciongrupo());
                attachedAsignacionGrupoListNew.add(asignacionGrupoListNewAsignacionGrupoToAttach);
            }
            asignacionGrupoListNew = attachedAsignacionGrupoListNew;
            grupo.setAsignacionGrupoList(asignacionGrupoListNew);
            grupo = em.merge(grupo);
            if (idOpcionestudioOld != null && !idOpcionestudioOld.equals(idOpcionestudioNew)) {
                idOpcionestudioOld.getGrupoList().remove(grupo);
                idOpcionestudioOld = em.merge(idOpcionestudioOld);
            }
            if (idOpcionestudioNew != null && !idOpcionestudioNew.equals(idOpcionestudioOld)) {
                idOpcionestudioNew.getGrupoList().add(grupo);
                idOpcionestudioNew = em.merge(idOpcionestudioNew);
            }
            if (idGeneracionOld != null && !idGeneracionOld.equals(idGeneracionNew)) {
                idGeneracionOld.getGrupoList().remove(grupo);
                idGeneracionOld = em.merge(idGeneracionOld);
            }
            if (idGeneracionNew != null && !idGeneracionNew.equals(idGeneracionOld)) {
                idGeneracionNew.getGrupoList().add(grupo);
                idGeneracionNew = em.merge(idGeneracionNew);
            }
            for (CalendarioEscolar calendarioEscolarListOldCalendarioEscolar : calendarioEscolarListOld) {
                if (!calendarioEscolarListNew.contains(calendarioEscolarListOldCalendarioEscolar)) {
                    calendarioEscolarListOldCalendarioEscolar.setIdGrupo(null);
                    calendarioEscolarListOldCalendarioEscolar = em.merge(calendarioEscolarListOldCalendarioEscolar);
                }
            }
            for (CalendarioEscolar calendarioEscolarListNewCalendarioEscolar : calendarioEscolarListNew) {
                if (!calendarioEscolarListOld.contains(calendarioEscolarListNewCalendarioEscolar)) {
                    Grupo oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar = calendarioEscolarListNewCalendarioEscolar.getIdGrupo();
                    calendarioEscolarListNewCalendarioEscolar.setIdGrupo(grupo);
                    calendarioEscolarListNewCalendarioEscolar = em.merge(calendarioEscolarListNewCalendarioEscolar);
                    if (oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar != null && !oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar.equals(grupo)) {
                        oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar.getCalendarioEscolarList().remove(calendarioEscolarListNewCalendarioEscolar);
                        oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar = em.merge(oldIdGrupoOfCalendarioEscolarListNewCalendarioEscolar);
                    }
                }
            }
            for (Acta actaListOldActa : actaListOld) {
                if (!actaListNew.contains(actaListOldActa)) {
                    actaListOldActa.setIdGrupo(null);
                    actaListOldActa = em.merge(actaListOldActa);
                }
            }
            for (Acta actaListNewActa : actaListNew) {
                if (!actaListOld.contains(actaListNewActa)) {
                    Grupo oldIdGrupoOfActaListNewActa = actaListNewActa.getIdGrupo();
                    actaListNewActa.setIdGrupo(grupo);
                    actaListNewActa = em.merge(actaListNewActa);
                    if (oldIdGrupoOfActaListNewActa != null && !oldIdGrupoOfActaListNewActa.equals(grupo)) {
                        oldIdGrupoOfActaListNewActa.getActaList().remove(actaListNewActa);
                        oldIdGrupoOfActaListNewActa = em.merge(oldIdGrupoOfActaListNewActa);
                    }
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListOldAsignacionEvaluacion : asignacionEvaluacionListOld) {
                if (!asignacionEvaluacionListNew.contains(asignacionEvaluacionListOldAsignacionEvaluacion)) {
                    asignacionEvaluacionListOldAsignacionEvaluacion.setIdGrupo(null);
                    asignacionEvaluacionListOldAsignacionEvaluacion = em.merge(asignacionEvaluacionListOldAsignacionEvaluacion);
                }
            }
            for (AsignacionEvaluacion asignacionEvaluacionListNewAsignacionEvaluacion : asignacionEvaluacionListNew) {
                if (!asignacionEvaluacionListOld.contains(asignacionEvaluacionListNewAsignacionEvaluacion)) {
                    Grupo oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion = asignacionEvaluacionListNewAsignacionEvaluacion.getIdGrupo();
                    asignacionEvaluacionListNewAsignacionEvaluacion.setIdGrupo(grupo);
                    asignacionEvaluacionListNewAsignacionEvaluacion = em.merge(asignacionEvaluacionListNewAsignacionEvaluacion);
                    if (oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion != null && !oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion.equals(grupo)) {
                        oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion.getAsignacionEvaluacionList().remove(asignacionEvaluacionListNewAsignacionEvaluacion);
                        oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion = em.merge(oldIdGrupoOfAsignacionEvaluacionListNewAsignacionEvaluacion);
                    }
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListOldBitacoraNotificacion : bitacoraNotificacionListOld) {
                if (!bitacoraNotificacionListNew.contains(bitacoraNotificacionListOldBitacoraNotificacion)) {
                    bitacoraNotificacionListOldBitacoraNotificacion.setIdGrupo(null);
                    bitacoraNotificacionListOldBitacoraNotificacion = em.merge(bitacoraNotificacionListOldBitacoraNotificacion);
                }
            }
            for (BitacoraNotificacion bitacoraNotificacionListNewBitacoraNotificacion : bitacoraNotificacionListNew) {
                if (!bitacoraNotificacionListOld.contains(bitacoraNotificacionListNewBitacoraNotificacion)) {
                    Grupo oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion = bitacoraNotificacionListNewBitacoraNotificacion.getIdGrupo();
                    bitacoraNotificacionListNewBitacoraNotificacion.setIdGrupo(grupo);
                    bitacoraNotificacionListNewBitacoraNotificacion = em.merge(bitacoraNotificacionListNewBitacoraNotificacion);
                    if (oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion != null && !oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion.equals(grupo)) {
                        oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion.getBitacoraNotificacionList().remove(bitacoraNotificacionListNewBitacoraNotificacion);
                        oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion = em.merge(oldIdGrupoOfBitacoraNotificacionListNewBitacoraNotificacion);
                    }
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListOldAsignacionGrupoencuesta : asignacionGrupoencuestaListOld) {
                if (!asignacionGrupoencuestaListNew.contains(asignacionGrupoencuestaListOldAsignacionGrupoencuesta)) {
                    asignacionGrupoencuestaListOldAsignacionGrupoencuesta.setIdGrupo(null);
                    asignacionGrupoencuestaListOldAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListOldAsignacionGrupoencuesta);
                }
            }
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListNewAsignacionGrupoencuesta : asignacionGrupoencuestaListNew) {
                if (!asignacionGrupoencuestaListOld.contains(asignacionGrupoencuestaListNewAsignacionGrupoencuesta)) {
                    Grupo oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta = asignacionGrupoencuestaListNewAsignacionGrupoencuesta.getIdGrupo();
                    asignacionGrupoencuestaListNewAsignacionGrupoencuesta.setIdGrupo(grupo);
                    asignacionGrupoencuestaListNewAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                    if (oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta != null && !oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta.equals(grupo)) {
                        oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta.getAsignacionGrupoencuestaList().remove(asignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                        oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta = em.merge(oldIdGrupoOfAsignacionGrupoencuestaListNewAsignacionGrupoencuesta);
                    }
                }
            }
            for (GrupoAlumnoesquema grupoAlumnoesquemaListOldGrupoAlumnoesquema : grupoAlumnoesquemaListOld) {
                if (!grupoAlumnoesquemaListNew.contains(grupoAlumnoesquemaListOldGrupoAlumnoesquema)) {
                    grupoAlumnoesquemaListOldGrupoAlumnoesquema.setIdGrupo(null);
                    grupoAlumnoesquemaListOldGrupoAlumnoesquema = em.merge(grupoAlumnoesquemaListOldGrupoAlumnoesquema);
                }
            }
            for (GrupoAlumnoesquema grupoAlumnoesquemaListNewGrupoAlumnoesquema : grupoAlumnoesquemaListNew) {
                if (!grupoAlumnoesquemaListOld.contains(grupoAlumnoesquemaListNewGrupoAlumnoesquema)) {
                    Grupo oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema = grupoAlumnoesquemaListNewGrupoAlumnoesquema.getIdGrupo();
                    grupoAlumnoesquemaListNewGrupoAlumnoesquema.setIdGrupo(grupo);
                    grupoAlumnoesquemaListNewGrupoAlumnoesquema = em.merge(grupoAlumnoesquemaListNewGrupoAlumnoesquema);
                    if (oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema != null && !oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema.equals(grupo)) {
                        oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema.getGrupoAlumnoesquemaList().remove(grupoAlumnoesquemaListNewGrupoAlumnoesquema);
                        oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema = em.merge(oldIdGrupoOfGrupoAlumnoesquemaListNewGrupoAlumnoesquema);
                    }
                }
            }
            for (AsignacionGrupo asignacionGrupoListNewAsignacionGrupo : asignacionGrupoListNew) {
                if (!asignacionGrupoListOld.contains(asignacionGrupoListNewAsignacionGrupo)) {
                    Grupo oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo = asignacionGrupoListNewAsignacionGrupo.getIdGrupo();
                    asignacionGrupoListNewAsignacionGrupo.setIdGrupo(grupo);
                    asignacionGrupoListNewAsignacionGrupo = em.merge(asignacionGrupoListNewAsignacionGrupo);
                    if (oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo != null && !oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo.equals(grupo)) {
                        oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo.getAsignacionGrupoList().remove(asignacionGrupoListNewAsignacionGrupo);
                        oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo = em.merge(oldIdGrupoOfAsignacionGrupoListNewAsignacionGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupo.getIdGrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AsignacionGrupo> asignacionGrupoListOrphanCheck = grupo.getAsignacionGrupoList();
            for (AsignacionGrupo asignacionGrupoListOrphanCheckAsignacionGrupo : asignacionGrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the AsignacionGrupo " + asignacionGrupoListOrphanCheckAsignacionGrupo + " in its asignacionGrupoList field has a non-nullable idGrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpcionEstudio idOpcionestudio = grupo.getIdOpcionestudio();
            if (idOpcionestudio != null) {
                idOpcionestudio.getGrupoList().remove(grupo);
                idOpcionestudio = em.merge(idOpcionestudio);
            }
            Generacion idGeneracion = grupo.getIdGeneracion();
            if (idGeneracion != null) {
                idGeneracion.getGrupoList().remove(grupo);
                idGeneracion = em.merge(idGeneracion);
            }
            List<CalendarioEscolar> calendarioEscolarList = grupo.getCalendarioEscolarList();
            for (CalendarioEscolar calendarioEscolarListCalendarioEscolar : calendarioEscolarList) {
                calendarioEscolarListCalendarioEscolar.setIdGrupo(null);
                calendarioEscolarListCalendarioEscolar = em.merge(calendarioEscolarListCalendarioEscolar);
            }
            List<Acta> actaList = grupo.getActaList();
            for (Acta actaListActa : actaList) {
                actaListActa.setIdGrupo(null);
                actaListActa = em.merge(actaListActa);
            }
            List<AsignacionEvaluacion> asignacionEvaluacionList = grupo.getAsignacionEvaluacionList();
            for (AsignacionEvaluacion asignacionEvaluacionListAsignacionEvaluacion : asignacionEvaluacionList) {
                asignacionEvaluacionListAsignacionEvaluacion.setIdGrupo(null);
                asignacionEvaluacionListAsignacionEvaluacion = em.merge(asignacionEvaluacionListAsignacionEvaluacion);
            }
            List<BitacoraNotificacion> bitacoraNotificacionList = grupo.getBitacoraNotificacionList();
            for (BitacoraNotificacion bitacoraNotificacionListBitacoraNotificacion : bitacoraNotificacionList) {
                bitacoraNotificacionListBitacoraNotificacion.setIdGrupo(null);
                bitacoraNotificacionListBitacoraNotificacion = em.merge(bitacoraNotificacionListBitacoraNotificacion);
            }
            List<AsignacionGrupoencuesta> asignacionGrupoencuestaList = grupo.getAsignacionGrupoencuestaList();
            for (AsignacionGrupoencuesta asignacionGrupoencuestaListAsignacionGrupoencuesta : asignacionGrupoencuestaList) {
                asignacionGrupoencuestaListAsignacionGrupoencuesta.setIdGrupo(null);
                asignacionGrupoencuestaListAsignacionGrupoencuesta = em.merge(asignacionGrupoencuestaListAsignacionGrupoencuesta);
            }
            List<GrupoAlumnoesquema> grupoAlumnoesquemaList = grupo.getGrupoAlumnoesquemaList();
            for (GrupoAlumnoesquema grupoAlumnoesquemaListGrupoAlumnoesquema : grupoAlumnoesquemaList) {
                grupoAlumnoesquemaListGrupoAlumnoesquema.setIdGrupo(null);
                grupoAlumnoesquemaListGrupoAlumnoesquema = em.merge(grupoAlumnoesquemaListGrupoAlumnoesquema);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Grupo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupo findGrupo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Grupo as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
