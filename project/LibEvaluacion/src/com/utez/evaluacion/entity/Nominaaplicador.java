/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "nominaaplicador")
@NamedQueries({
    @NamedQuery(name = "Nominaaplicador.findAll", query = "SELECT n FROM Nominaaplicador n")})
public class Nominaaplicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "folio")
    private Integer folio;
    @Column(name = "cancelada")
    private Integer cancelada;
    @Column(name = "fechaNomina")
    @Temporal(TemporalType.DATE)
    private Date fechaNomina;
    @Column(name = "totalGeneral")
    private Integer totalGeneral;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "folio")
    private List<Nominaconcepto> nominaconceptoList;

    public Nominaaplicador() {
    }

    public Nominaaplicador(Integer folio) {
        this.folio = folio;
    }

    public Nominaaplicador(Integer folio, Date fechaInicio, Date fechaFin) {
        this.folio = folio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Integer getCancelada() {
        return cancelada;
    }

    public void setCancelada(Integer cancelada) {
        this.cancelada = cancelada;
    }

    public Date getFechaNomina() {
        return fechaNomina;
    }

    public void setFechaNomina(Date fechaNomina) {
        this.fechaNomina = fechaNomina;
    }

    public Integer getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(Integer totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Nominaconcepto> getNominaconceptoList() {
        return nominaconceptoList;
    }

    public void setNominaconceptoList(List<Nominaconcepto> nominaconceptoList) {
        this.nominaconceptoList = nominaconceptoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folio != null ? folio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nominaaplicador)) {
            return false;
        }
        Nominaaplicador other = (Nominaaplicador) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Nominaaplicador[ folio=" + folio + " ]";
    }
    
}
