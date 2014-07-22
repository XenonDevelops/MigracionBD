/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "cuerpoacta")
@NamedQueries({
    @NamedQuery(name = "Cuerpoacta.findAll", query = "SELECT c FROM Cuerpoacta c")})
public class Cuerpoacta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "numLista")
    private Integer numLista;
    @Column(name = "matricula")
    private String matricula;
    @Column(name = "nombreCompleto")
    private String nombreCompleto;
    @Column(name = "califnum")
    private String califnum;
    @Column(name = "califLetra")
    private String califLetra;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "claveGrupo")
    private String claveGrupo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveCuerpoacta")
    private Integer claveCuerpoacta;

    public Cuerpoacta() {
    }

    public Cuerpoacta(Integer claveCuerpoacta) {
        this.claveCuerpoacta = claveCuerpoacta;
    }

    public Integer getNumLista() {
        return numLista;
    }

    public void setNumLista(Integer numLista) {
        this.numLista = numLista;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCalifnum() {
        return califnum;
    }

    public void setCalifnum(String califnum) {
        this.califnum = califnum;
    }

    public String getCalifLetra() {
        return califLetra;
    }

    public void setCalifLetra(String califLetra) {
        this.califLetra = califLetra;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getClaveGrupo() {
        return claveGrupo;
    }

    public void setClaveGrupo(String claveGrupo) {
        this.claveGrupo = claveGrupo;
    }

    public Integer getClaveCuerpoacta() {
        return claveCuerpoacta;
    }

    public void setClaveCuerpoacta(Integer claveCuerpoacta) {
        this.claveCuerpoacta = claveCuerpoacta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveCuerpoacta != null ? claveCuerpoacta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuerpoacta)) {
            return false;
        }
        Cuerpoacta other = (Cuerpoacta) object;
        if ((this.claveCuerpoacta == null && other.claveCuerpoacta != null) || (this.claveCuerpoacta != null && !this.claveCuerpoacta.equals(other.claveCuerpoacta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Cuerpoacta[ claveCuerpoacta=" + claveCuerpoacta + " ]";
    }
    
}
