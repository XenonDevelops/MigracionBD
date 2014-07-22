/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "plandeestudios")
@NamedQueries({
    @NamedQuery(name = "Plandeestudios.findAll", query = "SELECT p FROM Plandeestudios p")})
public class Plandeestudios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "revoe")
    private String revoe;
    @Column(name = "gradoAcademico")
    private String gradoAcademico;
    @Column(name = "nombrePlan")
    private String nombrePlan;
    @Column(name = "estado")
    private String estado;
    @Column(name = "axo")
    private Integer axo;
    @OneToMany(mappedBy = "revoe")
    private List<Asignacionplan> asignacionplanList;
    @OneToMany(mappedBy = "revoe")
    private List<Materiasdeplan> materiasdeplanList;

    public Plandeestudios() {
    }

    public Plandeestudios(String revoe) {
        this.revoe = revoe;
    }

    public String getRevoe() {
        return revoe;
    }

    public void setRevoe(String revoe) {
        this.revoe = revoe;
    }

    public String getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(String gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAxo() {
        return axo;
    }

    public void setAxo(Integer axo) {
        this.axo = axo;
    }

    public List<Asignacionplan> getAsignacionplanList() {
        return asignacionplanList;
    }

    public void setAsignacionplanList(List<Asignacionplan> asignacionplanList) {
        this.asignacionplanList = asignacionplanList;
    }

    public List<Materiasdeplan> getMateriasdeplanList() {
        return materiasdeplanList;
    }

    public void setMateriasdeplanList(List<Materiasdeplan> materiasdeplanList) {
        this.materiasdeplanList = materiasdeplanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (revoe != null ? revoe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plandeestudios)) {
            return false;
        }
        Plandeestudios other = (Plandeestudios) object;
        if ((this.revoe == null && other.revoe != null) || (this.revoe != null && !this.revoe.equals(other.revoe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Plandeestudios[ revoe=" + revoe + " ]";
    }
    
}
