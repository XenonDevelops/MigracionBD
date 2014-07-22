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
@Table(name = "documento_tipotitulacion")
@NamedQueries({
    @NamedQuery(name = "DocumentoTipotitulacion.findAll", query = "SELECT d FROM DocumentoTipotitulacion d")})
public class DocumentoTipotitulacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DocumentoTipotitulacionPK documentoTipotitulacionPK;
    @JoinColumn(name = "id_tipotitulacion", referencedColumnName = "id_tipotitulacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoTitulacion tipoTitulacion;
    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documento documento;

    public DocumentoTipotitulacion() {
    }

    public DocumentoTipotitulacion(DocumentoTipotitulacionPK documentoTipotitulacionPK) {
        this.documentoTipotitulacionPK = documentoTipotitulacionPK;
    }

    public DocumentoTipotitulacion(long idTipotitulacion, long idDocumento) {
        this.documentoTipotitulacionPK = new DocumentoTipotitulacionPK(idTipotitulacion, idDocumento);
    }

    public DocumentoTipotitulacionPK getDocumentoTipotitulacionPK() {
        return documentoTipotitulacionPK;
    }

    public void setDocumentoTipotitulacionPK(DocumentoTipotitulacionPK documentoTipotitulacionPK) {
        this.documentoTipotitulacionPK = documentoTipotitulacionPK;
    }

    public TipoTitulacion getTipoTitulacion() {
        return tipoTitulacion;
    }

    public void setTipoTitulacion(TipoTitulacion tipoTitulacion) {
        this.tipoTitulacion = tipoTitulacion;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentoTipotitulacionPK != null ? documentoTipotitulacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoTipotitulacion)) {
            return false;
        }
        DocumentoTipotitulacion other = (DocumentoTipotitulacion) object;
        if ((this.documentoTipotitulacionPK == null && other.documentoTipotitulacionPK != null) || (this.documentoTipotitulacionPK != null && !this.documentoTipotitulacionPK.equals(other.documentoTipotitulacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.DocumentoTipotitulacion[ documentoTipotitulacionPK=" + documentoTipotitulacionPK + " ]";
    }
    
}
