/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "usuario_rol")
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRolPK usuarioRolPK;
    @JoinColumn(name = "usuario", referencedColumnName = "curp", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    public UsuarioRol() {
    }

    public UsuarioRol(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public UsuarioRol(String usuario, long idRol) {
        this.usuarioRolPK = new UsuarioRolPK(usuario, idRol);
    }

    public UsuarioRolPK getUsuarioRolPK() {
        return usuarioRolPK;
    }

    public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.usuarioRolPK == null && other.usuarioRolPK != null) || (this.usuarioRolPK != null && !this.usuarioRolPK.equals(other.usuarioRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.UsuarioRol[ usuarioRolPK=" + usuarioRolPK + " ]";
    }
    
}
