/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Sergio
 */
@Embeddable
public class DocumentoTipotitulacionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_tipotitulacion")
    private long idTipotitulacion;
    @Basic(optional = false)
    @Column(name = "id_documento")
    private long idDocumento;

    public DocumentoTipotitulacionPK() {
    }

    public DocumentoTipotitulacionPK(long idTipotitulacion, long idDocumento) {
        this.idTipotitulacion = idTipotitulacion;
        this.idDocumento = idDocumento;
    }

    public long getIdTipotitulacion() {
        return idTipotitulacion;
    }

    public void setIdTipotitulacion(long idTipotitulacion) {
        this.idTipotitulacion = idTipotitulacion;
    }

    public long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipotitulacion;
        hash += (int) idDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoTipotitulacionPK)) {
            return false;
        }
        DocumentoTipotitulacionPK other = (DocumentoTipotitulacionPK) object;
        if (this.idTipotitulacion != other.idTipotitulacion) {
            return false;
        }
        if (this.idDocumento != other.idDocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.DocumentoTipotitulacionPK[ idTipotitulacion=" + idTipotitulacion + ", idDocumento=" + idDocumento + " ]";
    }
    
}
