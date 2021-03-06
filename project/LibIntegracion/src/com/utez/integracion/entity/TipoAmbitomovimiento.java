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
@Table(name = "tipo_ambitomovimiento")
@NamedQueries({
    @NamedQuery(name = "TipoAmbitomovimiento.findAll", query = "SELECT t FROM TipoAmbitomovimiento t")})
public class TipoAmbitomovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoambitomovimiento")
    private Long idTipoambitomovimiento;
    @Column(name = "descripcion_ambitomovimiento")
    private String descripcionAmbitomovimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAmbitomovimiento")
    private List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoList;

    public TipoAmbitomovimiento() {
    }

    public TipoAmbitomovimiento(Long idTipoambitomovimiento) {
        this.idTipoambitomovimiento = idTipoambitomovimiento;
    }

    public Long getIdTipoambitomovimiento() {
        return idTipoambitomovimiento;
    }

    public void setIdTipoambitomovimiento(Long idTipoambitomovimiento) {
        this.idTipoambitomovimiento = idTipoambitomovimiento;
    }

    public String getDescripcionAmbitomovimiento() {
        return descripcionAmbitomovimiento;
    }

    public void setDescripcionAmbitomovimiento(String descripcionAmbitomovimiento) {
        this.descripcionAmbitomovimiento = descripcionAmbitomovimiento;
    }

    public List<AsignacionAmbitomovimientotipo> getAsignacionAmbitomovimientotipoList() {
        return asignacionAmbitomovimientotipoList;
    }

    public void setAsignacionAmbitomovimientotipoList(List<AsignacionAmbitomovimientotipo> asignacionAmbitomovimientotipoList) {
        this.asignacionAmbitomovimientotipoList = asignacionAmbitomovimientotipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoambitomovimiento != null ? idTipoambitomovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAmbitomovimiento)) {
            return false;
        }
        TipoAmbitomovimiento other = (TipoAmbitomovimiento) object;
        if ((this.idTipoambitomovimiento == null && other.idTipoambitomovimiento != null) || (this.idTipoambitomovimiento != null && !this.idTipoambitomovimiento.equals(other.idTipoambitomovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.TipoAmbitomovimiento[ idTipoambitomovimiento=" + idTipoambitomovimiento + " ]";
    }
    
}
