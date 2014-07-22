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
@Table(name = "cargo_tabulador")
@NamedQueries({
    @NamedQuery(name = "CargoTabulador.findAll", query = "SELECT c FROM CargoTabulador c")})
public class CargoTabulador implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CargoTabuladorPK cargoTabuladorPK;
    @JoinColumn(name = "id_tabulador", referencedColumnName = "id_tabulador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tabulador tabulador;
    @JoinColumn(name = "id_cargoarea", referencedColumnName = "id_cargoarea", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AreaCargo areaCargo;

    public CargoTabulador() {
    }

    public CargoTabulador(CargoTabuladorPK cargoTabuladorPK) {
        this.cargoTabuladorPK = cargoTabuladorPK;
    }

    public CargoTabulador(long idCargoarea, long idTabulador) {
        this.cargoTabuladorPK = new CargoTabuladorPK(idCargoarea, idTabulador);
    }

    public CargoTabuladorPK getCargoTabuladorPK() {
        return cargoTabuladorPK;
    }

    public void setCargoTabuladorPK(CargoTabuladorPK cargoTabuladorPK) {
        this.cargoTabuladorPK = cargoTabuladorPK;
    }

    public Tabulador getTabulador() {
        return tabulador;
    }

    public void setTabulador(Tabulador tabulador) {
        this.tabulador = tabulador;
    }

    public AreaCargo getAreaCargo() {
        return areaCargo;
    }

    public void setAreaCargo(AreaCargo areaCargo) {
        this.areaCargo = areaCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoTabuladorPK != null ? cargoTabuladorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoTabulador)) {
            return false;
        }
        CargoTabulador other = (CargoTabulador) object;
        if ((this.cargoTabuladorPK == null && other.cargoTabuladorPK != null) || (this.cargoTabuladorPK != null && !this.cargoTabuladorPK.equals(other.cargoTabuladorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.CargoTabulador[ cargoTabuladorPK=" + cargoTabuladorPK + " ]";
    }
    
}
