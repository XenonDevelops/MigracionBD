/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.IllegalOrphanException;
import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.UsuarioRol;
import java.util.ArrayList;
import java.util.List;
import com.utez.integracion.entity.BitacoraMovimiento;
import com.utez.integracion.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (usuario.getUsuarioRolList() == null) {
            usuario.setUsuarioRolList(new ArrayList<UsuarioRol>());
        }
        if (usuario.getBitacoraMovimientoList() == null) {
            usuario.setBitacoraMovimientoList(new ArrayList<BitacoraMovimiento>());
        }
        List<String> illegalOrphanMessages = null;
        Persona personaOrphanCheck = usuario.getPersona();
        if (personaOrphanCheck != null) {
            Usuario oldUsuarioOfPersona = personaOrphanCheck.getUsuario();
            if (oldUsuarioOfPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Persona " + personaOrphanCheck + " already has an item of type Usuario whose persona column cannot be null. Please make another selection for the persona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona = usuario.getPersona();
            if (persona != null) {
                persona = em.getReference(persona.getClass(), persona.getCurp());
                usuario.setPersona(persona);
            }
            List<UsuarioRol> attachedUsuarioRolList = new ArrayList<UsuarioRol>();
            for (UsuarioRol usuarioRolListUsuarioRolToAttach : usuario.getUsuarioRolList()) {
                usuarioRolListUsuarioRolToAttach = em.getReference(usuarioRolListUsuarioRolToAttach.getClass(), usuarioRolListUsuarioRolToAttach.getUsuarioRolPK());
                attachedUsuarioRolList.add(usuarioRolListUsuarioRolToAttach);
            }
            usuario.setUsuarioRolList(attachedUsuarioRolList);
            List<BitacoraMovimiento> attachedBitacoraMovimientoList = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimientoToAttach : usuario.getBitacoraMovimientoList()) {
                bitacoraMovimientoListBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoList.add(bitacoraMovimientoListBitacoraMovimientoToAttach);
            }
            usuario.setBitacoraMovimientoList(attachedBitacoraMovimientoList);
            em.persist(usuario);
            if (persona != null) {
                persona.setUsuario(usuario);
                persona = em.merge(persona);
            }
            for (UsuarioRol usuarioRolListUsuarioRol : usuario.getUsuarioRolList()) {
                Usuario oldUsuario1OfUsuarioRolListUsuarioRol = usuarioRolListUsuarioRol.getUsuario1();
                usuarioRolListUsuarioRol.setUsuario1(usuario);
                usuarioRolListUsuarioRol = em.merge(usuarioRolListUsuarioRol);
                if (oldUsuario1OfUsuarioRolListUsuarioRol != null) {
                    oldUsuario1OfUsuarioRolListUsuarioRol.getUsuarioRolList().remove(usuarioRolListUsuarioRol);
                    oldUsuario1OfUsuarioRolListUsuarioRol = em.merge(oldUsuario1OfUsuarioRolListUsuarioRol);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : usuario.getBitacoraMovimientoList()) {
                Usuario oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento = bitacoraMovimientoListBitacoraMovimiento.getIdUsuario();
                bitacoraMovimientoListBitacoraMovimiento.setIdUsuario(usuario);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
                if (oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento != null) {
                    oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListBitacoraMovimiento);
                    oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento = em.merge(oldIdUsuarioOfBitacoraMovimientoListBitacoraMovimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCurp()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCurp());
            Persona personaOld = persistentUsuario.getPersona();
            Persona personaNew = usuario.getPersona();
            List<UsuarioRol> usuarioRolListOld = persistentUsuario.getUsuarioRolList();
            List<UsuarioRol> usuarioRolListNew = usuario.getUsuarioRolList();
            List<BitacoraMovimiento> bitacoraMovimientoListOld = persistentUsuario.getBitacoraMovimientoList();
            List<BitacoraMovimiento> bitacoraMovimientoListNew = usuario.getBitacoraMovimientoList();
            List<String> illegalOrphanMessages = null;
            if (personaNew != null && !personaNew.equals(personaOld)) {
                Usuario oldUsuarioOfPersona = personaNew.getUsuario();
                if (oldUsuarioOfPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Persona " + personaNew + " already has an item of type Usuario whose persona column cannot be null. Please make another selection for the persona field.");
                }
            }
            for (UsuarioRol usuarioRolListOldUsuarioRol : usuarioRolListOld) {
                if (!usuarioRolListNew.contains(usuarioRolListOldUsuarioRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioRol " + usuarioRolListOldUsuarioRol + " since its usuario1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (personaNew != null) {
                personaNew = em.getReference(personaNew.getClass(), personaNew.getCurp());
                usuario.setPersona(personaNew);
            }
            List<UsuarioRol> attachedUsuarioRolListNew = new ArrayList<UsuarioRol>();
            for (UsuarioRol usuarioRolListNewUsuarioRolToAttach : usuarioRolListNew) {
                usuarioRolListNewUsuarioRolToAttach = em.getReference(usuarioRolListNewUsuarioRolToAttach.getClass(), usuarioRolListNewUsuarioRolToAttach.getUsuarioRolPK());
                attachedUsuarioRolListNew.add(usuarioRolListNewUsuarioRolToAttach);
            }
            usuarioRolListNew = attachedUsuarioRolListNew;
            usuario.setUsuarioRolList(usuarioRolListNew);
            List<BitacoraMovimiento> attachedBitacoraMovimientoListNew = new ArrayList<BitacoraMovimiento>();
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimientoToAttach : bitacoraMovimientoListNew) {
                bitacoraMovimientoListNewBitacoraMovimientoToAttach = em.getReference(bitacoraMovimientoListNewBitacoraMovimientoToAttach.getClass(), bitacoraMovimientoListNewBitacoraMovimientoToAttach.getIdBitacoramovimiento());
                attachedBitacoraMovimientoListNew.add(bitacoraMovimientoListNewBitacoraMovimientoToAttach);
            }
            bitacoraMovimientoListNew = attachedBitacoraMovimientoListNew;
            usuario.setBitacoraMovimientoList(bitacoraMovimientoListNew);
            usuario = em.merge(usuario);
            if (personaOld != null && !personaOld.equals(personaNew)) {
                personaOld.setUsuario(null);
                personaOld = em.merge(personaOld);
            }
            if (personaNew != null && !personaNew.equals(personaOld)) {
                personaNew.setUsuario(usuario);
                personaNew = em.merge(personaNew);
            }
            for (UsuarioRol usuarioRolListNewUsuarioRol : usuarioRolListNew) {
                if (!usuarioRolListOld.contains(usuarioRolListNewUsuarioRol)) {
                    Usuario oldUsuario1OfUsuarioRolListNewUsuarioRol = usuarioRolListNewUsuarioRol.getUsuario1();
                    usuarioRolListNewUsuarioRol.setUsuario1(usuario);
                    usuarioRolListNewUsuarioRol = em.merge(usuarioRolListNewUsuarioRol);
                    if (oldUsuario1OfUsuarioRolListNewUsuarioRol != null && !oldUsuario1OfUsuarioRolListNewUsuarioRol.equals(usuario)) {
                        oldUsuario1OfUsuarioRolListNewUsuarioRol.getUsuarioRolList().remove(usuarioRolListNewUsuarioRol);
                        oldUsuario1OfUsuarioRolListNewUsuarioRol = em.merge(oldUsuario1OfUsuarioRolListNewUsuarioRol);
                    }
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListOldBitacoraMovimiento : bitacoraMovimientoListOld) {
                if (!bitacoraMovimientoListNew.contains(bitacoraMovimientoListOldBitacoraMovimiento)) {
                    bitacoraMovimientoListOldBitacoraMovimiento.setIdUsuario(null);
                    bitacoraMovimientoListOldBitacoraMovimiento = em.merge(bitacoraMovimientoListOldBitacoraMovimiento);
                }
            }
            for (BitacoraMovimiento bitacoraMovimientoListNewBitacoraMovimiento : bitacoraMovimientoListNew) {
                if (!bitacoraMovimientoListOld.contains(bitacoraMovimientoListNewBitacoraMovimiento)) {
                    Usuario oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento = bitacoraMovimientoListNewBitacoraMovimiento.getIdUsuario();
                    bitacoraMovimientoListNewBitacoraMovimiento.setIdUsuario(usuario);
                    bitacoraMovimientoListNewBitacoraMovimiento = em.merge(bitacoraMovimientoListNewBitacoraMovimiento);
                    if (oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento != null && !oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento.equals(usuario)) {
                        oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento.getBitacoraMovimientoList().remove(bitacoraMovimientoListNewBitacoraMovimiento);
                        oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento = em.merge(oldIdUsuarioOfBitacoraMovimientoListNewBitacoraMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCurp();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCurp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UsuarioRol> usuarioRolListOrphanCheck = usuario.getUsuarioRolList();
            for (UsuarioRol usuarioRolListOrphanCheckUsuarioRol : usuarioRolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the UsuarioRol " + usuarioRolListOrphanCheckUsuarioRol + " in its usuarioRolList field has a non-nullable usuario1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona persona = usuario.getPersona();
            if (persona != null) {
                persona.setUsuario(null);
                persona = em.merge(persona);
            }
            List<BitacoraMovimiento> bitacoraMovimientoList = usuario.getBitacoraMovimientoList();
            for (BitacoraMovimiento bitacoraMovimientoListBitacoraMovimiento : bitacoraMovimientoList) {
                bitacoraMovimientoListBitacoraMovimiento.setIdUsuario(null);
                bitacoraMovimientoListBitacoraMovimiento = em.merge(bitacoraMovimientoListBitacoraMovimiento);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Usuario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Usuario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
