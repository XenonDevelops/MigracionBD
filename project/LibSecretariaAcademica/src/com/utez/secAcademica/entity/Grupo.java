/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entity;

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
@Table(name = "grupo")
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdgrupo", query = "SELECT g FROM Grupo g WHERE g.idgrupo = :idgrupo"),
    @NamedQuery(name = "Grupo.findByGrupo", query = "SELECT g FROM Grupo g WHERE g.grupo = :grupo"),
    @NamedQuery(name = "Grupo.findByGeneracion", query = "SELECT g FROM Grupo g WHERE g.generacion = :generacion"),
    @NamedQuery(name = "Grupo.findByEstado", query = "SELECT g FROM Grupo g WHERE g.estado = :estado")})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupo")
    private Integer idgrupo;
    @Column(name = "grupo")
    private String grupo;
    @Column(name = "generacion")
    private Integer generacion;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idplantel", referencedColumnName = "idplantel")
    @ManyToOne
    private Plantel idplantel;
    @JoinColumn(name = "rvoe", referencedColumnName = "rvoe")
    @ManyToOne
    private Planestudios rvoe;
    @JoinColumn(name = "periodoingreso", referencedColumnName = "periodo")
    @ManyToOne
    private Periodo periodoingreso;
    @JoinColumn(name = "opcionestudio", referencedColumnName = "opcionestudio")
    @ManyToOne
    private Opcionestudio opcionestudio;
    @OneToMany(mappedBy = "idgrupo")
    private List<Calendario> calendarioList;

    public Grupo() {
    }

    public Grupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getGeneracion() {
        return generacion;
    }

    public void setGeneracion(Integer generacion) {
        this.generacion = generacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Plantel getIdplantel() {
        return idplantel;
    }

    public void setIdplantel(Plantel idplantel) {
        this.idplantel = idplantel;
    }

    public Planestudios getRvoe() {
        return rvoe;
    }

    public void setRvoe(Planestudios rvoe) {
        this.rvoe = rvoe;
    }

    public Periodo getPeriodoingreso() {
        return periodoingreso;
    }

    public void setPeriodoingreso(Periodo periodoingreso) {
        this.periodoingreso = periodoingreso;
    }

    public Opcionestudio getOpcionestudio() {
        return opcionestudio;
    }

    public void setOpcionestudio(Opcionestudio opcionestudio) {
        this.opcionestudio = opcionestudio;
    }

    public List<Calendario> getCalendarioList() {
        return calendarioList;
    }

    public void setCalendarioList(List<Calendario> calendarioList) {
        this.calendarioList = calendarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.secAcademica.entity.Grupo[ idgrupo=" + idgrupo + " ]";
    }
    
}
