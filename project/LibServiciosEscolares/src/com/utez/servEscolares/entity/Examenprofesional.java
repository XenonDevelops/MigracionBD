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
@Table(name = "examenprofesional")
@NamedQueries({
    @NamedQuery(name = "Examenprofesional.findAll", query = "SELECT e FROM Examenprofesional e")})
public class Examenprofesional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "claveTramite")
    private Integer claveTramite;
    @Column(name = "asesorTitular")
    private Integer asesorTitular;
    @Column(name = "sinodalTitular")
    private Integer sinodalTitular;
    @Column(name = "sinodalSecretario")
    private Integer sinodalSecretario;
    @Column(name = "sinodalVocal")
    private Integer sinodalVocal;
    @Column(name = "suplente1")
    private Integer suplente1;
    @Column(name = "suplente2")
    private Integer suplente2;
    @Basic(optional = false)
    @Column(name = "fechaHoraExamen")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraExamen;
    @Column(name = "aula")
    private String aula;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "constanciaTerminacion")
    private String constanciaTerminacion;
    @Column(name = "veredicto")
    private String veredicto;
    @Column(name = "ordenTrabajo")
    private String ordenTrabajo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Double monto;
    @Column(name = "foja")
    private String foja;
    @Column(name = "libro")
    private String libro;
    @Column(name = "numeroActa")
    private String numeroActa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveExamenprofesional")
    private Integer claveExamenprofesional;

    public Examenprofesional() {
    }

    public Examenprofesional(Integer claveExamenprofesional) {
        this.claveExamenprofesional = claveExamenprofesional;
    }

    public Examenprofesional(Integer claveExamenprofesional, Date fechaHoraExamen, Date fecha) {
        this.claveExamenprofesional = claveExamenprofesional;
        this.fechaHoraExamen = fechaHoraExamen;
        this.fecha = fecha;
    }

    public Integer getClaveTramite() {
        return claveTramite;
    }

    public void setClaveTramite(Integer claveTramite) {
        this.claveTramite = claveTramite;
    }

    public Integer getAsesorTitular() {
        return asesorTitular;
    }

    public void setAsesorTitular(Integer asesorTitular) {
        this.asesorTitular = asesorTitular;
    }

    public Integer getSinodalTitular() {
        return sinodalTitular;
    }

    public void setSinodalTitular(Integer sinodalTitular) {
        this.sinodalTitular = sinodalTitular;
    }

    public Integer getSinodalSecretario() {
        return sinodalSecretario;
    }

    public void setSinodalSecretario(Integer sinodalSecretario) {
        this.sinodalSecretario = sinodalSecretario;
    }

    public Integer getSinodalVocal() {
        return sinodalVocal;
    }

    public void setSinodalVocal(Integer sinodalVocal) {
        this.sinodalVocal = sinodalVocal;
    }

    public Integer getSuplente1() {
        return suplente1;
    }

    public void setSuplente1(Integer suplente1) {
        this.suplente1 = suplente1;
    }

    public Integer getSuplente2() {
        return suplente2;
    }

    public void setSuplente2(Integer suplente2) {
        this.suplente2 = suplente2;
    }

    public Date getFechaHoraExamen() {
        return fechaHoraExamen;
    }

    public void setFechaHoraExamen(Date fechaHoraExamen) {
        this.fechaHoraExamen = fechaHoraExamen;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getConstanciaTerminacion() {
        return constanciaTerminacion;
    }

    public void setConstanciaTerminacion(String constanciaTerminacion) {
        this.constanciaTerminacion = constanciaTerminacion;
    }

    public String getVeredicto() {
        return veredicto;
    }

    public void setVeredicto(String veredicto) {
        this.veredicto = veredicto;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFoja() {
        return foja;
    }

    public void setFoja(String foja) {
        this.foja = foja;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(String numeroActa) {
        this.numeroActa = numeroActa;
    }

    public Integer getClaveExamenprofesional() {
        return claveExamenprofesional;
    }

    public void setClaveExamenprofesional(Integer claveExamenprofesional) {
        this.claveExamenprofesional = claveExamenprofesional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveExamenprofesional != null ? claveExamenprofesional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examenprofesional)) {
            return false;
        }
        Examenprofesional other = (Examenprofesional) object;
        if ((this.claveExamenprofesional == null && other.claveExamenprofesional != null) || (this.claveExamenprofesional != null && !this.claveExamenprofesional.equals(other.claveExamenprofesional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Examenprofesional[ claveExamenprofesional=" + claveExamenprofesional + " ]";
    }
    
}
