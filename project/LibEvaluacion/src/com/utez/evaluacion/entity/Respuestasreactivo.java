/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "respuestasreactivo")
@NamedQueries({
    @NamedQuery(name = "Respuestasreactivo.findAll", query = "SELECT r FROM Respuestasreactivo r")})
public class Respuestasreactivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRespuesta")
    private Integer claveRespuesta;
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "valorRespuesta")
    private Integer valorRespuesta;
    @JoinColumn(name = "claveReactivo", referencedColumnName = "claveReactivo")
    @ManyToOne(optional = false)
    private Reactivo claveReactivo;

    public Respuestasreactivo() {
    }

    public Respuestasreactivo(Integer claveRespuesta) {
        this.claveRespuesta = claveRespuesta;
    }

    public Integer getClaveRespuesta() {
        return claveRespuesta;
    }

    public void setClaveRespuesta(Integer claveRespuesta) {
        this.claveRespuesta = claveRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getValorRespuesta() {
        return valorRespuesta;
    }

    public void setValorRespuesta(Integer valorRespuesta) {
        this.valorRespuesta = valorRespuesta;
    }

    public Reactivo getClaveReactivo() {
        return claveReactivo;
    }

    public void setClaveReactivo(Reactivo claveReactivo) {
        this.claveReactivo = claveReactivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveRespuesta != null ? claveRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestasreactivo)) {
            return false;
        }
        Respuestasreactivo other = (Respuestasreactivo) object;
        if ((this.claveRespuesta == null && other.claveRespuesta != null) || (this.claveRespuesta != null && !this.claveRespuesta.equals(other.claveRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Respuestasreactivo[ claveRespuesta=" + claveRespuesta + " ]";
    }
    
}
