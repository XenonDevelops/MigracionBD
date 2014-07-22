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
@Table(name = "respuestasresultado")
@NamedQueries({
    @NamedQuery(name = "Respuestasresultado.findAll", query = "SELECT r FROM Respuestasresultado r")})
public class Respuestasresultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRespuestaR")
    private Integer claveRespuestaR;
    @Column(name = "claveReactivo")
    private Integer claveReactivo;
    @Column(name = "claveRespuestaSel")
    private Integer claveRespuestaSel;
    @JoinColumn(name = "claveResultadoAuto", referencedColumnName = "claveResultadoAuto")
    @ManyToOne
    private Resultadoautoeval claveResultadoAuto;

    public Respuestasresultado() {
    }

    public Respuestasresultado(Integer claveRespuestaR) {
        this.claveRespuestaR = claveRespuestaR;
    }

    public Integer getClaveRespuestaR() {
        return claveRespuestaR;
    }

    public void setClaveRespuestaR(Integer claveRespuestaR) {
        this.claveRespuestaR = claveRespuestaR;
    }

    public Integer getClaveReactivo() {
        return claveReactivo;
    }

    public void setClaveReactivo(Integer claveReactivo) {
        this.claveReactivo = claveReactivo;
    }

    public Integer getClaveRespuestaSel() {
        return claveRespuestaSel;
    }

    public void setClaveRespuestaSel(Integer claveRespuestaSel) {
        this.claveRespuestaSel = claveRespuestaSel;
    }

    public Resultadoautoeval getClaveResultadoAuto() {
        return claveResultadoAuto;
    }

    public void setClaveResultadoAuto(Resultadoautoeval claveResultadoAuto) {
        this.claveResultadoAuto = claveResultadoAuto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveRespuestaR != null ? claveRespuestaR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestasresultado)) {
            return false;
        }
        Respuestasresultado other = (Respuestasresultado) object;
        if ((this.claveRespuestaR == null && other.claveRespuestaR != null) || (this.claveRespuestaR != null && !this.claveRespuestaR.equals(other.claveRespuestaR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Respuestasresultado[ claveRespuestaR=" + claveRespuestaR + " ]";
    }
    
}
