/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sergio
 */
@Entity
@Table(name = "asesoriascalendario")
@NamedQueries({
    @NamedQuery(name = "Asesoriascalendario.findAll", query = "SELECT a FROM Asesoriascalendario a")})
public class Asesoriascalendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveAsesoria")
    private Integer claveAsesoria;
    @Column(name = "horario")
    private String horario;
    @Column(name = "as1")
    @Temporal(TemporalType.DATE)
    private Date as1;
    @Column(name = "as2")
    @Temporal(TemporalType.DATE)
    private Date as2;
    @Column(name = "as3")
    @Temporal(TemporalType.DATE)
    private Date as3;
    @Column(name = "as4")
    @Temporal(TemporalType.DATE)
    private Date as4;
    @Column(name = "as5")
    @Temporal(TemporalType.DATE)
    private Date as5;
    @Column(name = "as6")
    @Temporal(TemporalType.DATE)
    private Date as6;
    @Column(name = "claveAsesor")
    private Integer claveAsesor;
    @Column(name = "claveMateria")
    private Integer claveMateria;
    @OneToMany(mappedBy = "claveAsesoria")
    private List<Fechasexam> fechasexamList;
    @JoinColumn(name = "claveCalendario", referencedColumnName = "claveCalendario")
    @ManyToOne
    private Calendarioescolar claveCalendario;

    public Asesoriascalendario() {
    }

    public Asesoriascalendario(Integer claveAsesoria) {
        this.claveAsesoria = claveAsesoria;
    }

    public Integer getClaveAsesoria() {
        return claveAsesoria;
    }

    public void setClaveAsesoria(Integer claveAsesoria) {
        this.claveAsesoria = claveAsesoria;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getAs1() {
        return as1;
    }

    public void setAs1(Date as1) {
        this.as1 = as1;
    }

    public Date getAs2() {
        return as2;
    }

    public void setAs2(Date as2) {
        this.as2 = as2;
    }

    public Date getAs3() {
        return as3;
    }

    public void setAs3(Date as3) {
        this.as3 = as3;
    }

    public Date getAs4() {
        return as4;
    }

    public void setAs4(Date as4) {
        this.as4 = as4;
    }

    public Date getAs5() {
        return as5;
    }

    public void setAs5(Date as5) {
        this.as5 = as5;
    }

    public Date getAs6() {
        return as6;
    }

    public void setAs6(Date as6) {
        this.as6 = as6;
    }

    public Integer getClaveAsesor() {
        return claveAsesor;
    }

    public void setClaveAsesor(Integer claveAsesor) {
        this.claveAsesor = claveAsesor;
    }

    public Integer getClaveMateria() {
        return claveMateria;
    }

    public void setClaveMateria(Integer claveMateria) {
        this.claveMateria = claveMateria;
    }

    public List<Fechasexam> getFechasexamList() {
        return fechasexamList;
    }

    public void setFechasexamList(List<Fechasexam> fechasexamList) {
        this.fechasexamList = fechasexamList;
    }

    public Calendarioescolar getClaveCalendario() {
        return claveCalendario;
    }

    public void setClaveCalendario(Calendarioescolar claveCalendario) {
        this.claveCalendario = claveCalendario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveAsesoria != null ? claveAsesoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesoriascalendario)) {
            return false;
        }
        Asesoriascalendario other = (Asesoriascalendario) object;
        if ((this.claveAsesoria == null && other.claveAsesoria != null) || (this.claveAsesoria != null && !this.claveAsesoria.equals(other.claveAsesoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.evaluacion.entity.Asesoriascalendario[ claveAsesoria=" + claveAsesoria + " ]";
    }
    
}
