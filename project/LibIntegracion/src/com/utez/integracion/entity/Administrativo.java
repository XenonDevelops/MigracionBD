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
@Table(name = "administrativo")
@NamedQueries({
    @NamedQuery(name = "Administrativo.findAll", query = "SELECT a FROM Administrativo a")})
public class Administrativo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_administrativo")
    private Long idAdministrativo;
    @Column(name = "estado_administrativo")
    private String estadoAdministrativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administrativo")
    private List<AdministrativoPlantel> administrativoPlantelList;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @OneToMany(mappedBy = "idAdministrativo")
    private List<HistorialCargo> historialCargoList;

    public Administrativo() {
    }

    public Administrativo(Long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public Long getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(Long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public String getEstadoAdministrativo() {
        return estadoAdministrativo;
    }

    public void setEstadoAdministrativo(String estadoAdministrativo) {
        this.estadoAdministrativo = estadoAdministrativo;
    }

    public List<AdministrativoPlantel> getAdministrativoPlantelList() {
        return administrativoPlantelList;
    }

    public void setAdministrativoPlantelList(List<AdministrativoPlantel> administrativoPlantelList) {
        this.administrativoPlantelList = administrativoPlantelList;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public List<HistorialCargo> getHistorialCargoList() {
        return historialCargoList;
    }

    public void setHistorialCargoList(List<HistorialCargo> historialCargoList) {
        this.historialCargoList = historialCargoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdministrativo != null ? idAdministrativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrativo)) {
            return false;
        }
        Administrativo other = (Administrativo) object;
        if ((this.idAdministrativo == null && other.idAdministrativo != null) || (this.idAdministrativo != null && !this.idAdministrativo.equals(other.idAdministrativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Administrativo[ idAdministrativo=" + idAdministrativo + " ]";
    }
    
}
