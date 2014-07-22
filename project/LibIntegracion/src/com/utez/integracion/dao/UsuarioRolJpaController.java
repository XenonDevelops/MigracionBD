/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.dao;

import com.utez.integracion.dao.exceptions.NonexistentEntityException;
import com.utez.integracion.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.utez.integracion.entity.Usuario;
import com.utez.integracion.entity.Rol;
import com.utez.integracion.entity.UsuarioRol;
import com.utez.integracion.entity.UsuarioRolPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Sergio
 */
public class UsuarioRolJpaController implements Serializable {

    public UsuarioRolJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioRol usuarioRol) throws PreexistingEntityException, Exception {
        if (usuarioRol.getUsuarioRolPK() == null) {
            usuarioRol.setUsuarioRolPK(new UsuarioRolPK());
        }
        usuarioRol.getUsuarioRolPK().setUsuario(usuarioRol.getUsuario1().getCurp());
        usuarioRol.getUsuarioRolPK().setIdRol(usuarioRol.getRol().getIdRol());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario1 = usuarioRol.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getCurp());
                usuarioRol.setUsuario1(usuario1);
            }
            Rol rol = usuarioRol.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getIdRol());
                usuarioRol.setRol(rol);
            }
            em.persist(usuarioRol);
            if (usuario1 != null) {
                usuario1.getUsuarioRolList().add(usuarioRol);
                usuario1 = em.merge(usuario1);
            }
            if (rol != null) {
                rol.getUsuarioRolList().add(usuarioRol);
                rol = em.merge(rol);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioRol(usuarioRol.getUsuarioRolPK()) != null) {
                throw new PreexistingEntityException("UsuarioRol " + usuarioRol + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioRol usuarioRol) throws NonexistentEntityException, Exception {
        usuarioRol.getUsuarioRolPK().setUsuario(usuarioRol.getUsuario1().getCurp());
        usuarioRol.getUsuarioRolPK().setIdRol(usuarioRol.getRol().getIdRol());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioRol persistentUsuarioRol = em.find(UsuarioRol.class, usuarioRol.getUsuarioRolPK());
            Usuario usuario1Old = persistentUsuarioRol.getUsuario1();
            Usuario usuario1New = usuarioRol.getUsuario1();
            Rol rolOld = persistentUsuarioRol.getRol();
            Rol rolNew = usuarioRol.getRol();
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getCurp());
                usuarioRol.setUsuario1(usuario1New);
            }
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getIdRol());
                usuarioRol.setRol(rolNew);
            }
            usuarioRol = em.merge(usuarioRol);
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getUsuarioRolList().remove(usuarioRol);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getUsuarioRolList().add(usuarioRol);
                usuario1New = em.merge(usuario1New);
            }
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getUsuarioRolList().remove(usuarioRol);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getUsuarioRolList().add(usuarioRol);
                rolNew = em.merge(rolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioRolPK id = usuarioRol.getUsuarioRolPK();
                if (findUsuarioRol(id) == null) {
                    throw new NonexistentEntityException("The usuarioRol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioRolPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioRol usuarioRol;
            try {
                usuarioRol = em.getReference(UsuarioRol.class, id);
                usuarioRol.getUsuarioRolPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioRol with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario1 = usuarioRol.getUsuario1();
            if (usuario1 != null) {
                usuario1.getUsuarioRolList().remove(usuarioRol);
                usuario1 = em.merge(usuario1);
            }
            Rol rol = usuarioRol.getRol();
            if (rol != null) {
                rol.getUsuarioRolList().remove(usuarioRol);
                rol = em.merge(rol);
            }
            em.remove(usuarioRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioRol> findUsuarioRolEntities() {
        return findUsuarioRolEntities(true, -1, -1);
    }

    public List<UsuarioRol> findUsuarioRolEntities(int maxResults, int firstResult) {
        return findUsuarioRolEntities(false, maxResults, firstResult);
    }

    private List<UsuarioRol> findUsuarioRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from UsuarioRol as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UsuarioRol findUsuarioRol(UsuarioRolPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioRolCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from UsuarioRol as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
