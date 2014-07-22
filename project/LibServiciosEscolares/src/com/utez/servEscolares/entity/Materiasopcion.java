/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "materiasopcion")
@NamedQueries({
    @NamedQuery(name = "Materiasopcion.findAll", query = "SELECT m FROM Materiasopcion m")})
public class Materiasopcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "caso")
    private String caso;
    @Lob
    @Column(name = "solucion")
    private String solucion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveMateriasopcion")
    private Integer claveMateriasopcion;

    public Materiasopcion() {
    }

    public Materiasopcion(Integer claveMateriasopcion) {
        this.claveMateriasopcion = claveMateriasopcion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public Integer getClaveMateriasopcion() {
        return claveMateriasopcion;
    }

    public void setClaveMateriasopcion(Integer claveMateriasopcion) {
        this.claveMateriasopcion = claveMateriasopcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveMateriasopcion != null ? claveMateriasopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiasopcion)) {
            return false;
        }
        Materiasopcion other = (Materiasopcion) object;
        if ((this.claveMateriasopcion == null && other.claveMateriasopcion != null) || (this.claveMateriasopcion != null && !this.claveMateriasopcion.equals(other.claveMateriasopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Materiasopcion[ claveMateriasopcion=" + claveMateriasopcion + " ]";
    }
    
}
