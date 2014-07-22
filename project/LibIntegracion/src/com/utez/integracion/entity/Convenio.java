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
@Table(name = "convenio")
@NamedQueries({
    @NamedQuery(name = "Convenio.findAll", query = "SELECT c FROM Convenio c")})
public class Convenio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_convenio")
    private Long idConvenio;
    @Column(name = "descripcion_convenio")
    private String descripcionConvenio;
    @Column(name = "abreviatura_convenio")
    private String abreviaturaConvenio;
    @Column(name = "institucion")
    private String institucion;
    @Column(name = "clave")
    private String clave;
    @Column(name = "fecha_convenio")
    @Temporal(TemporalType.DATE)
    private Date fechaConvenio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convenio")
    private List<AlumnoConvenio> alumnoConvenioList;

    public Convenio() {
    }

    public Convenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getDescripcionConvenio() {
        return descripcionConvenio;
    }

    public void setDescripcionConvenio(String descripcionConvenio) {
        this.descripcionConvenio = descripcionConvenio;
    }

    public String getAbreviaturaConvenio() {
        return abreviaturaConvenio;
    }

    public void setAbreviaturaConvenio(String abreviaturaConvenio) {
        this.abreviaturaConvenio = abreviaturaConvenio;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaConvenio() {
        return fechaConvenio;
    }

    public void setFechaConvenio(Date fechaConvenio) {
        this.fechaConvenio = fechaConvenio;
    }

    public List<AlumnoConvenio> getAlumnoConvenioList() {
        return alumnoConvenioList;
    }

    public void setAlumnoConvenioList(List<AlumnoConvenio> alumnoConvenioList) {
        this.alumnoConvenioList = alumnoConvenioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConvenio != null ? idConvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convenio)) {
            return false;
        }
        Convenio other = (Convenio) object;
        if ((this.idConvenio == null && other.idConvenio != null) || (this.idConvenio != null && !this.idConvenio.equals(other.idConvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Convenio[ idConvenio=" + idConvenio + " ]";
    }
    
}
