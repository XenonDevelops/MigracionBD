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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "area_cargo")
@NamedQueries({
    @NamedQuery(name = "AreaCargo.findAll", query = "SELECT a FROM AreaCargo a")})
public class AreaCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cargoarea")
    private Long idCargoarea;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne
    private Cargo idCargo;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Area idArea;
    @OneToMany(mappedBy = "idCargoarea")
    private List<HistorialCargo> historialCargoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaCargo")
    private List<CargoTabulador> cargoTabuladorList;

    public AreaCargo() {
    }

    public AreaCargo(Long idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public Long getIdCargoarea() {
        return idCargoarea;
    }

    public void setIdCargoarea(Long idCargoarea) {
        this.idCargoarea = idCargoarea;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public List<HistorialCargo> getHistorialCargoList() {
        return historialCargoList;
    }

    public void setHistorialCargoList(List<HistorialCargo> historialCargoList) {
        this.historialCargoList = historialCargoList;
    }

    public List<CargoTabulador> getCargoTabuladorList() {
        return cargoTabuladorList;
    }

    public void setCargoTabuladorList(List<CargoTabulador> cargoTabuladorList) {
        this.cargoTabuladorList = cargoTabuladorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargoarea != null ? idCargoarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaCargo)) {
            return false;
        }
        AreaCargo other = (AreaCargo) object;
        if ((this.idCargoarea == null && other.idCargoarea != null) || (this.idCargoarea != null && !this.idCargoarea.equals(other.idCargoarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AreaCargo[ idCargoarea=" + idCargoarea + " ]";
    }
    
}
