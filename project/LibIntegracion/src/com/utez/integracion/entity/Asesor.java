/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entity;

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
@Table(name = "asesor")
@NamedQueries({
    @NamedQuery(name = "Asesor.findAll", query = "SELECT a FROM Asesor a")})
public class Asesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asesor")
    private Long idAsesor;
    @Column(name = "estado_asesor")
    private String estadoAsesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asesor")
    private List<ProgramacionOpcioncasesor> programacionOpcioncasesorList;
    @OneToMany(mappedBy = "idAsesorcalificador")
    private List<ExamenExtemporaneo> examenExtemporaneoList;
    @OneToMany(mappedBy = "idAutor")
    private List<AsignacionAutorintegradora> asignacionAutorintegradoraList;
    @OneToMany(mappedBy = "idAsesor")
    private List<AsesorTitulartitulacion> asesorTitulartitulacionList;
    @JoinColumn(name = "id_recursohumano", referencedColumnName = "id_recursohumano")
    @ManyToOne
    private RecursoHumano idRecursohumano;
    @OneToMany(mappedBy = "idAsesor")
    private List<SinodoExamen> sinodoExamenList;
    @OneToMany(mappedBy = "idAutor")
    private List<AsignacionAutorbanco> asignacionAutorbancoList;
    @OneToMany(mappedBy = "responsable")
    private List<GrupoInduccion> grupoInduccionList;
    @OneToMany(mappedBy = "idAsesor")
    private List<AsignaturaAsesor> asignaturaAsesorList;
    @OneToMany(mappedBy = "idAsesor")
    private List<ExamenIndividual> examenIndividualList;
    @OneToMany(mappedBy = "idAsesor")
    private List<GrupoExamenextemporaneo> grupoExamenextemporaneoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asesor")
    private List<AsesorCalificacion> asesorCalificacionList;

    public Asesor() {
    }

    public Asesor(Long idAsesor) {
        this.idAsesor = idAsesor;
    }

    public Long getIdAsesor() {
        return idAsesor;
    }

    public void setIdAsesor(Long idAsesor) {
        this.idAsesor = idAsesor;
    }

    public String getEstadoAsesor() {
        return estadoAsesor;
    }

    public void setEstadoAsesor(String estadoAsesor) {
        this.estadoAsesor = estadoAsesor;
    }

    public List<ProgramacionOpcioncasesor> getProgramacionOpcioncasesorList() {
        return programacionOpcioncasesorList;
    }

    public void setProgramacionOpcioncasesorList(List<ProgramacionOpcioncasesor> programacionOpcioncasesorList) {
        this.programacionOpcioncasesorList = programacionOpcioncasesorList;
    }

    public List<ExamenExtemporaneo> getExamenExtemporaneoList() {
        return examenExtemporaneoList;
    }

    public void setExamenExtemporaneoList(List<ExamenExtemporaneo> examenExtemporaneoList) {
        this.examenExtemporaneoList = examenExtemporaneoList;
    }

    public List<AsignacionAutorintegradora> getAsignacionAutorintegradoraList() {
        return asignacionAutorintegradoraList;
    }

    public void setAsignacionAutorintegradoraList(List<AsignacionAutorintegradora> asignacionAutorintegradoraList) {
        this.asignacionAutorintegradoraList = asignacionAutorintegradoraList;
    }

    public List<AsesorTitulartitulacion> getAsesorTitulartitulacionList() {
        return asesorTitulartitulacionList;
    }

    public void setAsesorTitulartitulacionList(List<AsesorTitulartitulacion> asesorTitulartitulacionList) {
        this.asesorTitulartitulacionList = asesorTitulartitulacionList;
    }

    public RecursoHumano getIdRecursohumano() {
        return idRecursohumano;
    }

    public void setIdRecursohumano(RecursoHumano idRecursohumano) {
        this.idRecursohumano = idRecursohumano;
    }

    public List<SinodoExamen> getSinodoExamenList() {
        return sinodoExamenList;
    }

    public void setSinodoExamenList(List<SinodoExamen> sinodoExamenList) {
        this.sinodoExamenList = sinodoExamenList;
    }

    public List<AsignacionAutorbanco> getAsignacionAutorbancoList() {
        return asignacionAutorbancoList;
    }

    public void setAsignacionAutorbancoList(List<AsignacionAutorbanco> asignacionAutorbancoList) {
        this.asignacionAutorbancoList = asignacionAutorbancoList;
    }

    public List<GrupoInduccion> getGrupoInduccionList() {
        return grupoInduccionList;
    }

    public void setGrupoInduccionList(List<GrupoInduccion> grupoInduccionList) {
        this.grupoInduccionList = grupoInduccionList;
    }

    public List<AsignaturaAsesor> getAsignaturaAsesorList() {
        return asignaturaAsesorList;
    }

    public void setAsignaturaAsesorList(List<AsignaturaAsesor> asignaturaAsesorList) {
        this.asignaturaAsesorList = asignaturaAsesorList;
    }

    public List<ExamenIndividual> getExamenIndividualList() {
        return examenIndividualList;
    }

    public void setExamenIndividualList(List<ExamenIndividual> examenIndividualList) {
        this.examenIndividualList = examenIndividualList;
    }

    public List<GrupoExamenextemporaneo> getGrupoExamenextemporaneoList() {
        return grupoExamenextemporaneoList;
    }

    public void setGrupoExamenextemporaneoList(List<GrupoExamenextemporaneo> grupoExamenextemporaneoList) {
        this.grupoExamenextemporaneoList = grupoExamenextemporaneoList;
    }

    public List<AsesorCalificacion> getAsesorCalificacionList() {
        return asesorCalificacionList;
    }

    public void setAsesorCalificacionList(List<AsesorCalificacion> asesorCalificacionList) {
        this.asesorCalificacionList = asesorCalificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsesor != null ? idAsesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesor)) {
            return false;
        }
        Asesor other = (Asesor) object;
        if ((this.idAsesor == null && other.idAsesor != null) || (this.idAsesor != null && !this.idAsesor.equals(other.idAsesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.utez.integracion.entity.Asesor[ idAsesor=" + idAsesor + " ]";
    }
    
}
