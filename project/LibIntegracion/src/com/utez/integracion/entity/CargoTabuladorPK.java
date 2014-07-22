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
public class CargoTabuladorPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id_cargoarea")
    private long idCargoarea;
    @Basic(optional = false)
    @Column(name = "id_tabulador")
    private long idTabulador;

    public CargoTabuladorPK() {
    }

    public CargoTabuladorPK(long idCargoarea, long idTabulador) {
        this.idCargoarea = idCargoarea;
        this.idTabulador = idTabulador;
    }

    public long getIdCargoarea() {
        return idCargoarea;
    }

    public void setIdCargoarea(long idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public long getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(long idTabulador) {
        this.idTabulador = idTabulador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCargoarea;
        hash += (int) idTabulador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoTabuladorPK)) {
            return false;
        }
        CargoTabuladorPK other = (CargoTabuladorPK) object;
        if (this.idCargoarea != other.idCargoarea) {
            return false;
        }
        if (this.idTabulador != other.idTabulador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CargoTabuladorPK[ idCargoarea=" + idCargoarea + ", idTabulador=" + idTabulador + " ]";
    }
    
}
