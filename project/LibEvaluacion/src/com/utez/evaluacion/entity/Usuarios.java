/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveUsuario")
    private Integer claveUsuario;
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "email")
    private String email;
    @Column(name = "tipoUsuario")
    private String tipoUsuario;

    public Usuarios() {
    }

    public Usuarios(Integer claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public Integer getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(Integer claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveUsuario != null ? claveUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.claveUsuario == null && other.claveUsuario != null) || (this.claveUsuario != null && !this.claveUsuario.equals(other.claveUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Usuarios[ claveUsuario=" + claveUsuario + " ]";
    }
    
}
