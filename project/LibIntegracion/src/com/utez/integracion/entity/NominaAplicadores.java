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
@Table(name = "nomina_aplicadores")
@NamedQueries({
    @NamedQuery(name = "NominaAplicadores.findAll", query = "SELECT n FROM NominaAplicadores n")})
public class NominaAplicadores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nominaaplicadores")
    private Integer idNominaaplicadores;
    @Column(name = "fecha_generacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "estado_nomina")
    private String estadoNomina;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nominaAplicadores")
    private List<DetalleNominaaplicadores> detalleNominaaplicadoresList;

    public NominaAplicadores() {
    }

    public NominaAplicadores(Integer idNominaaplicadores) {
        this.idNominaaplicadores = idNominaaplicadores;
    }

    public Integer getIdNominaaplicadores() {
        return idNominaaplicadores;
    }

    public void setIdNominaaplicadores(Integer idNominaaplicadores) {
        this.idNominaaplicadores = idNominaaplicadores;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstadoNomina() {
        return estadoNomina;
    }

    public void setEstadoNomina(String estadoNomina) {
        this.estadoNomina = estadoNomina;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<DetalleNominaaplicadores> getDetalleNominaaplicadoresList() {
        return detalleNominaaplicadoresList;
    }

    public void setDetalleNominaaplicadoresList(List<DetalleNominaaplicadores> detalleNominaaplicadoresList) {
        this.detalleNominaaplicadoresList = detalleNominaaplicadoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNominaaplicadores != null ? idNominaaplicadores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NominaAplicadores)) {
            return false;
        }
        NominaAplicadores other = (NominaAplicadores) object;
        if ((this.idNominaaplicadores == null && other.idNominaaplicadores != null) || (this.idNominaaplicadores != null && !this.idNominaaplicadores.equals(other.idNominaaplicadores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.NominaAplicadores[ idNominaaplicadores=" + idNominaaplicadores + " ]";
    }
    
}
