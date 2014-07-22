/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "examen")
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e")})
public class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveExamen")
    private Integer claveExamen;
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveBanco")
    private Integer claveBanco;
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "version")
    private Integer version;
    @Column(name = "archivoPDF")
    private String archivoPDF;
    @Column(name = "numReactivos")
    private Integer numReactivos;
    @OneToMany(mappedBy = "claveExamen")
    private List<Programacionexamen> programacionexamenList;

    public Examen() {
    }

    public Examen(Integer claveExamen) {
        this.claveExamen = claveExamen;
    }

    public Integer getClaveExamen() {
        return claveExamen;
    }

    public void setClaveExamen(Integer claveExamen) {
        this.claveExamen = claveExamen;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Integer getClaveBanco() {
        return claveBanco;
    }

    public void setClaveBanco(Integer claveBanco) {
        this.claveBanco = claveBanco;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(String archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public Integer getNumReactivos() {
        return numReactivos;
    }

    public void setNumReactivos(Integer numReactivos) {
        this.numReactivos = numReactivos;
    }

    public List<Programacionexamen> getProgramacionexamenList() {
        return programacionexamenList;
    }

    public void setProgramacionexamenList(List<Programacionexamen> programacionexamenList) {
        this.programacionexamenList = programacionexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveExamen != null ? claveExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.claveExamen == null && other.claveExamen != null) || (this.claveExamen != null && !this.claveExamen.equals(other.claveExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Examen[ claveExamen=" + claveExamen + " ]";
    }
    
}
