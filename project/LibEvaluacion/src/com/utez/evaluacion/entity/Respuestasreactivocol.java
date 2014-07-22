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
@Table(name = "respuestasreactivocol")
@NamedQueries({
    @NamedQuery(name = "Respuestasreactivocol.findAll", query = "SELECT r FROM Respuestasreactivocol r")})
public class Respuestasreactivocol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveRespuestaCol")
    private Integer claveRespuestaCol;
    @Column(name = "claveGrupo")
    private Integer claveGrupo;
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "valorRespuesta")
    private Integer valorRespuesta;
    @JoinColumn(name = "claveReactivo", referencedColumnName = "claveReactivo")
    @ManyToOne(optional = false)
    private Reactivo claveReactivo;

    public Respuestasreactivocol() {
    }

    public Respuestasreactivocol(Integer claveRespuestaCol) {
        this.claveRespuestaCol = claveRespuestaCol;
    }

    public Integer getClaveRespuestaCol() {
        return claveRespuestaCol;
    }

    public void setClaveRespuestaCol(Integer claveRespuestaCol) {
        this.claveRespuestaCol = claveRespuestaCol;
    }

    public Integer getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(Integer claveGrupo) {
        this.claveGrupo = claveGrupo;
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
        hash += (claveRespuestaCol != null ? claveRespuestaCol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestasreactivocol)) {
            return false;
        }
        Respuestasreactivocol other = (Respuestasreactivocol) object;
        if ((this.claveRespuestaCol == null && other.claveRespuestaCol != null) || (this.claveRespuestaCol != null && !this.claveRespuestaCol.equals(other.claveRespuestaCol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Respuestasreactivocol[ claveRespuestaCol=" + claveRespuestaCol + " ]";
    }
    
}
