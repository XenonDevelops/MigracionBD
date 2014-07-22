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
@Table(name = "examen_impreso")
@NamedQueries({
    @NamedQuery(name = "ExamenImpreso.findAll", query = "SELECT e FROM ExamenImpreso e")})
public class ExamenImpreso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examenimpreso")
    private Long idExamenimpreso;
    @Column(name = "unidades")
    private String unidades;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "numero_reactivos")
    private Integer numeroReactivos;
    @Column(name = "archivo_pdf")
    private String archivoPdf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examenImpreso")
    private List<ReactivoExamenimpreso> reactivoExamenimpresoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examenImpreso")
    private List<AsignacionExamenimpreso> asignacionExamenimpresoList;
    @JoinColumn(name = "id_tipoexamen", referencedColumnName = "id_tipoexamen")
    @ManyToOne
    private TipoExamen idTipoexamen;
    @JoinColumn(name = "id_asignacionasignaturabanco", referencedColumnName = "id_asignacionasignaturabanco")
    @ManyToOne
    private AsignacionAsignaturabanco idAsignacionasignaturabanco;

    public ExamenImpreso() {
    }

    public ExamenImpreso(Long idExamenimpreso) {
        this.idExamenimpreso = idExamenimpreso;
    }

    public Long getIdExamenimpreso() {
        return idExamenimpreso;
    }

    public void setIdExamenimpreso(Long idExamenimpreso) {
        this.idExamenimpreso = idExamenimpreso;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getNumeroReactivos() {
        return numeroReactivos;
    }

    public void setNumeroReactivos(Integer numeroReactivos) {
        this.numeroReactivos = numeroReactivos;
    }

    public String getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(String archivoPdf) {
        this.archivoPdf = archivoPdf;
    }

    public List<ReactivoExamenimpreso> getReactivoExamenimpresoList() {
        return reactivoExamenimpresoList;
    }

    public void setReactivoExamenimpresoList(List<ReactivoExamenimpreso> reactivoExamenimpresoList) {
        this.reactivoExamenimpresoList = reactivoExamenimpresoList;
    }

    public List<AsignacionExamenimpreso> getAsignacionExamenimpresoList() {
        return asignacionExamenimpresoList;
    }

    public void setAsignacionExamenimpresoList(List<AsignacionExamenimpreso> asignacionExamenimpresoList) {
        this.asignacionExamenimpresoList = asignacionExamenimpresoList;
    }

    public TipoExamen getIdTipoexamen() {
        return idTipoexamen;
    }

    public void setIdTipoexamen(TipoExamen idTipoexamen) {
        this.idTipoexamen = idTipoexamen;
    }

    public AsignacionAsignaturabanco getIdAsignacionasignaturabanco() {
        return idAsignacionasignaturabanco;
    }

    public void setIdAsignacionasignaturabanco(AsignacionAsignaturabanco idAsignacionasignaturabanco) {
        this.idAsignacionasignaturabanco = idAsignacionasignaturabanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenimpreso != null ? idExamenimpreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenImpreso)) {
            return false;
        }
        ExamenImpreso other = (ExamenImpreso) object;
        if ((this.idExamenimpreso == null && other.idExamenimpreso != null) || (this.idExamenimpreso != null && !this.idExamenimpreso.equals(other.idExamenimpreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.ExamenImpreso[ idExamenimpreso=" + idExamenimpreso + " ]";
    }
    
}
