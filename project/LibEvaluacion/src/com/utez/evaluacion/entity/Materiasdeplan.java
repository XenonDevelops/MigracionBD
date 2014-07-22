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
@Table(name = "materiasdeplan")
@NamedQueries({
    @NamedQuery(name = "Materiasdeplan.findAll", query = "SELECT m FROM Materiasdeplan m")})
public class Materiasdeplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @Column(name = "nombreMateria")
    private String nombreMateria;
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "claveMat")
    private String claveMat;
    @OneToMany(mappedBy = "claveMateria")
    private List<Correspondencia> correspondenciaList;
    @OneToMany(mappedBy = "seriadaCon")
    private List<Materiasdeplan> materiasdeplanList;
    @JoinColumn(name = "seriadaCon", referencedColumnName = "claveMateria")
    @ManyToOne
    private Materiasdeplan seriadaCon;
    @JoinColumn(name = "revoe", referencedColumnName = "revoe")
    @ManyToOne
    private Plandeestudios revoe;

    public Materiasdeplan() {
    }

    public Materiasdeplan(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getClaveMat() {
        return claveMat;
    }

    public void setClaveMat(String claveMat) {
        this.claveMat = claveMat;
    }

    public List<Correspondencia> getCorrespondenciaList() {
        return correspondenciaList;
    }

    public void setCorrespondenciaList(List<Correspondencia> correspondenciaList) {
        this.correspondenciaList = correspondenciaList;
    }

    public List<Materiasdeplan> getMateriasdeplanList() {
        return materiasdeplanList;
    }

    public void setMateriasdeplanList(List<Materiasdeplan> materiasdeplanList) {
        this.materiasdeplanList = materiasdeplanList;
    }

    public Materiasdeplan getSeriadaCon() {
        return seriadaCon;
    }

    public void setSeriadaCon(Materiasdeplan seriadaCon) {
        this.seriadaCon = seriadaCon;
    }

    public Plandeestudios getRevoe() {
        return revoe;
    }

    public void setRevoe(Plandeestudios revoe) {
        this.revoe = revoe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveMateria != null ? claveMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiasdeplan)) {
            return false;
        }
        Materiasdeplan other = (Materiasdeplan) object;
        if ((this.claveMateria == null && other.claveMateria != null) || (this.claveMateria != null && !this.claveMateria.equals(other.claveMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Materiasdeplan[ claveMateria=" + claveMateria + " ]";
    }
    
}
