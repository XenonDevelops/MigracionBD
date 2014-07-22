/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tabulador")
@NamedQueries({
    @NamedQuery(name = "Tabulador.findAll", query = "SELECT t FROM Tabulador t")})
public class Tabulador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tabulador")
    private Long idTabulador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "vigenciainicio")
    @Temporal(TemporalType.DATE)
    private Date vigenciainicio;
    @Column(name = "vigenciafin")
    @Temporal(TemporalType.DATE)
    private Date vigenciafin;
    @JoinColumn(name = "id_tabuladorconcepto", referencedColumnName = "id_tabuladorconcepto")
    @ManyToOne
    private TabuladorConcepto idTabuladorconcepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tabulador")
    private List<CargoTabulador> cargoTabuladorList;

    public Tabulador() {
    }

    public Tabulador(Long idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Long getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Long idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Date getVigenciainicio() {
        return vigenciainicio;
    }

    public void setVigenciainicio(Date vigenciainicio) {
        this.vigenciainicio = vigenciainicio;
    }

    public Date getVigenciafin() {
        return vigenciafin;
    }

    public void setVigenciafin(Date vigenciafin) {
        this.vigenciafin = vigenciafin;
    }

    public TabuladorConcepto getIdTabuladorconcepto() {
        return idTabuladorconcepto;
    }

    public void setIdTabuladorconcepto(TabuladorConcepto idTabuladorconcepto) {
        this.idTabuladorconcepto = idTabuladorconcepto;
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
        hash += (idTabulador != null ? idTabulador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabulador)) {
            return false;
        }
        Tabulador other = (Tabulador) object;
        if ((this.idTabulador == null && other.idTabulador != null) || (this.idTabulador != null && !this.idTabulador.equals(other.idTabulador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Tabulador[ idTabulador=" + idTabulador + " ]";
    }
    
}
