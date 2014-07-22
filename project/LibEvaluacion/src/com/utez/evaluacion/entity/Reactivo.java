/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

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
@Table(name = "reactivo")
@NamedQueries({
    @NamedQuery(name = "Reactivo.findAll", query = "SELECT r FROM Reactivo r")})
public class Reactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveReactivo")
    private Integer claveReactivo;
    @Column(name = "claveGrupo")
    private Integer claveGrupo;
    @Column(name = "tipoReactivo")
    private String tipoReactivo;
    @Column(name = "ambito")
    private String ambito;
    @Column(name = "unidad")
    private Integer unidad;
    @Column(name = "reactivo")
    private String reactivo;
    @Column(name = "edoBaja")
    private Integer edoBaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveReactivo")
    private List<Respuestasreactivocol> respuestasreactivocolList;
    @OneToMany(mappedBy = "claveReactivo")
    private List<Imagenreactivo> imagenreactivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveReactivo")
    private List<Respuestasreactivo> respuestasreactivoList;
    @JoinColumn(name = "claveBanco", referencedColumnName = "claveBanco")
    @ManyToOne
    private Banco claveBanco;

    public Reactivo() {
    }

    public Reactivo(Integer claveReactivo) {
        this.claveReactivo = claveReactivo;
    }

    public Integer getClaveReactivo() {
        return claveReactivo;
    }

    public void setClaveReactivo(Integer claveReactivo) {
        this.claveReactivo = claveReactivo;
    }

    public Integer getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(Integer claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public String getTipoReactivo() {
        return tipoReactivo;
    }

    public void setTipoReactivo(String tipoReactivo) {
        this.tipoReactivo = tipoReactivo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getReactivo() {
        return reactivo;
    }

    public void setReactivo(String reactivo) {
        this.reactivo = reactivo;
    }

    public Integer getEdoBaja() {
        return edoBaja;
    }

    public void setEdoBaja(Integer edoBaja) {
        this.edoBaja = edoBaja;
    }

    public List<Respuestasreactivocol> getRespuestasreactivocolList() {
        return respuestasreactivocolList;
    }

    public void setRespuestasreactivocolList(List<Respuestasreactivocol> respuestasreactivocolList) {
        this.respuestasreactivocolList = respuestasreactivocolList;
    }

    public List<Imagenreactivo> getImagenreactivoList() {
        return imagenreactivoList;
    }

    public void setImagenreactivoList(List<Imagenreactivo> imagenreactivoList) {
        this.imagenreactivoList = imagenreactivoList;
    }

    public List<Respuestasreactivo> getRespuestasreactivoList() {
        return respuestasreactivoList;
    }

    public void setRespuestasreactivoList(List<Respuestasreactivo> respuestasreactivoList) {
        this.respuestasreactivoList = respuestasreactivoList;
    }

    public Banco getClaveBanco() {
        return claveBanco;
    }

    public void setClaveBanco(Banco claveBanco) {
        this.claveBanco = claveBanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveReactivo != null ? claveReactivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reactivo)) {
            return false;
        }
        Reactivo other = (Reactivo) object;
        if ((this.claveReactivo == null && other.claveReactivo != null) || (this.claveReactivo != null && !this.claveReactivo.equals(other.claveReactivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Reactivo[ claveReactivo=" + claveReactivo + " ]";
    }
    
}
