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
@Table(name = "registroparticipante")
@NamedQueries({
    @NamedQuery(name = "Registroparticipante.findAll", query = "SELECT r FROM Registroparticipante r")})
public class Registroparticipante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numControl")
    private Integer numControl;
    @Column(name = "nombreParticipante")
    private String nombreParticipante;
    @Column(name = "matriculaUMED")
    private String matriculaUMED;
    @Column(name = "telefonoParticipante")
    private String telefonoParticipante;
    @Column(name = "emailparticipante")
    private String emailparticipante;
    @Column(name = "direcParticipante")
    private String direcParticipante;
    @Column(name = "generoParticipante")
    private String generoParticipante;
    @Column(name = "edadParticipante")
    private Integer edadParticipante;
    @Column(name = "numEvento")
    private Integer numEvento;

    public Registroparticipante() {
    }

    public Registroparticipante(Integer numControl) {
        this.numControl = numControl;
    }

    public Integer getNumControl() {
        return numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }

    public String getMatriculaUMED() {
        return matriculaUMED;
    }

    public void setMatriculaUMED(String matriculaUMED) {
        this.matriculaUMED = matriculaUMED;
    }

    public String getTelefonoParticipante() {
        return telefonoParticipante;
    }

    public void setTelefonoParticipante(String telefonoParticipante) {
        this.telefonoParticipante = telefonoParticipante;
    }

    public String getEmailparticipante() {
        return emailparticipante;
    }

    public void setEmailparticipante(String emailparticipante) {
        this.emailparticipante = emailparticipante;
    }

    public String getDirecParticipante() {
        return direcParticipante;
    }

    public void setDirecParticipante(String direcParticipante) {
        this.direcParticipante = direcParticipante;
    }

    public String getGeneroParticipante() {
        return generoParticipante;
    }

    public void setGeneroParticipante(String generoParticipante) {
        this.generoParticipante = generoParticipante;
    }

    public Integer getEdadParticipante() {
        return edadParticipante;
    }

    public void setEdadParticipante(Integer edadParticipante) {
        this.edadParticipante = edadParticipante;
    }

    public Integer getNumEvento() {
        return numEvento;
    }

    public void setNumEvento(Integer numEvento) {
        this.numEvento = numEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numControl != null ? numControl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registroparticipante)) {
            return false;
        }
        Registroparticipante other = (Registroparticipante) object;
        if ((this.numControl == null && other.numControl != null) || (this.numControl != null && !this.numControl.equals(other.numControl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.servEscolares.entity.Registroparticipante[ numControl=" + numControl + " ]";
    }
    
}
