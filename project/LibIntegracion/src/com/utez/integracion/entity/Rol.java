/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")})
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol")
    private Long idRol;
    @Column(name = "nombre_rol")
    private String nombreRol;
    @Column(name = "descripcion_rol")
    private String descripcionRol;
    @Column(name = "tiempo_sesion")
    private Integer tiempoSesion;
    @Column(name = "estado_rol")
    private Boolean estadoRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<RolRecursopermiso> rolRecursopermisoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private List<UsuarioRol> usuarioRolList;
    @OneToMany(mappedBy = "idRol")
    private List<BitacoraMovimiento> bitacoraMovimientoList;

    public Rol() {
    }

    public Rol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Integer getTiempoSesion() {
        return tiempoSesion;
    }

    public void setTiempoSesion(Integer tiempoSesion) {
        this.tiempoSesion = tiempoSesion;
    }

    public Boolean getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(Boolean estadoRol) {
        this.estadoRol = estadoRol;
    }

    public List<RolRecursopermiso> getRolRecursopermisoList() {
        return rolRecursopermisoList;
    }

    public void setRolRecursopermisoList(List<RolRecursopermiso> rolRecursopermisoList) {
        this.rolRecursopermisoList = rolRecursopermisoList;
    }

    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    public List<BitacoraMovimiento> getBitacoraMovimientoList() {
        return bitacoraMovimientoList;
    }

    public void setBitacoraMovimientoList(List<BitacoraMovimiento> bitacoraMovimientoList) {
        this.bitacoraMovimientoList = bitacoraMovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Rol[ idRol=" + idRol + " ]";
    }
    
}
