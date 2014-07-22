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
@Table(name = "asignacion_aplicador")
@NamedQueries({
    @NamedQuery(name = "AsignacionAplicador.findAll", query = "SELECT a FROM AsignacionAplicador a")})
public class AsignacionAplicador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacionaplicador")
    private Long idAsignacionaplicador;
    @Column(name = "salon")
    private Integer salon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionAplicador")
    private List<DetalleNominaaplicadores> detalleNominaaplicadoresList;
    @JoinColumn(name = "id_fechaexamenprogramado", referencedColumnName = "id_fecha_examen")
    @ManyToOne
    private FechaExamenprogramado idFechaexamenprogramado;
    @JoinColumn(name = "id_aplicador", referencedColumnName = "id_aplicador")
    @ManyToOne
    private Aplicador idAplicador;

    public AsignacionAplicador() {
    }

    public AsignacionAplicador(Long idAsignacionaplicador) {
        this.idAsignacionaplicador = idAsignacionaplicador;
    }

    public Long getIdAsignacionaplicador() {
        return idAsignacionaplicador;
    }

    public void setIdAsignacionaplicador(Long idAsignacionaplicador) {
        this.idAsignacionaplicador = idAsignacionaplicador;
    }

    public Integer getSalon() {
        return salon;
    }

    public void setSalon(Integer salon) {
        this.salon = salon;
    }

    public List<DetalleNominaaplicadores> getDetalleNominaaplicadoresList() {
        return detalleNominaaplicadoresList;
    }

    public void setDetalleNominaaplicadoresList(List<DetalleNominaaplicadores> detalleNominaaplicadoresList) {
        this.detalleNominaaplicadoresList = detalleNominaaplicadoresList;
    }

    public FechaExamenprogramado getIdFechaexamenprogramado() {
        return idFechaexamenprogramado;
    }

    public void setIdFechaexamenprogramado(FechaExamenprogramado idFechaexamenprogramado) {
        this.idFechaexamenprogramado = idFechaexamenprogramado;
    }

    public Aplicador getIdAplicador() {
        return idAplicador;
    }

    public void setIdAplicador(Aplicador idAplicador) {
        this.idAplicador = idAplicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionaplicador != null ? idAsignacionaplicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionAplicador)) {
            return false;
        }
        AsignacionAplicador other = (AsignacionAplicador) object;
        if ((this.idAsignacionaplicador == null && other.idAsignacionaplicador != null) || (this.idAsignacionaplicador != null && !this.idAsignacionaplicador.equals(other.idAsignacionaplicador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.AsignacionAplicador[ idAsignacionaplicador=" + idAsignacionaplicador + " ]";
    }
    
}
