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
@Table(name = "ordentrabajo_titulacion")
@NamedQueries({
    @NamedQuery(name = "OrdentrabajoTitulacion.findAll", query = "SELECT o FROM OrdentrabajoTitulacion o")})
public class OrdentrabajoTitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdentrabajoTitulacionPK ordentrabajoTitulacionPK;
    @JoinColumn(name = "id_tramitetitulacion", referencedColumnName = "id_tramitetitulacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TramiteTitulacion tramiteTitulacion;
    @JoinColumn(name = "id_ordentrabajo", referencedColumnName = "id_ordentrabajo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrdenTrabajo ordenTrabajo;

    public OrdentrabajoTitulacion() {
    }

    public OrdentrabajoTitulacion(OrdentrabajoTitulacionPK ordentrabajoTitulacionPK) {
        this.ordentrabajoTitulacionPK = ordentrabajoTitulacionPK;
    }

    public OrdentrabajoTitulacion(long idTramitetitulacion, long idOrdentrabajo) {
        this.ordentrabajoTitulacionPK = new OrdentrabajoTitulacionPK(idTramitetitulacion, idOrdentrabajo);
    }

    public OrdentrabajoTitulacionPK getOrdentrabajoTitulacionPK() {
        return ordentrabajoTitulacionPK;
    }

    public void setOrdentrabajoTitulacionPK(OrdentrabajoTitulacionPK ordentrabajoTitulacionPK) {
        this.ordentrabajoTitulacionPK = ordentrabajoTitulacionPK;
    }

    public TramiteTitulacion getTramiteTitulacion() {
        return tramiteTitulacion;
    }

    public void setTramiteTitulacion(TramiteTitulacion tramiteTitulacion) {
        this.tramiteTitulacion = tramiteTitulacion;
    }

    public OrdenTrabajo getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordentrabajoTitulacionPK != null ? ordentrabajoTitulacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdentrabajoTitulacion)) {
            return false;
        }
        OrdentrabajoTitulacion other = (OrdentrabajoTitulacion) object;
        if ((this.ordentrabajoTitulacionPK == null && other.ordentrabajoTitulacionPK != null) || (this.ordentrabajoTitulacionPK != null && !this.ordentrabajoTitulacionPK.equals(other.ordentrabajoTitulacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.OrdentrabajoTitulacion[ ordentrabajoTitulacionPK=" + ordentrabajoTitulacionPK + " ]";
    }
    
}
