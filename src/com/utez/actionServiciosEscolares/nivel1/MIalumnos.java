/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.actionServiciosEscolares.nivel1;

import com.utez.integracion.entity.Alumno;
import com.utez.integracion.entity.Asentamiento;
import com.utez.integracion.entity.Aspirante;
import com.utez.integracion.entity.EntidadFederativa;
import com.utez.integracion.entity.Municipio;
import com.utez.integracion.entity.Periodo;
import com.utez.integracion.entity.Persona;
import com.utez.integracion.entity.PlanEstudio;
import com.utez.integracion.entity.TipoAlumno;
import com.utez.integracion.entity.TipoEstadocivil;
import com.utez.integracion.entity.TipoSexo;
import com.utez.migracionBD.utility.MIInheritance;
import com.utez.migracionBD.utility.MiInterface;
import com.utez.servEscolares.entity.Alumnos;
import com.utez.servEscolares.entity.Complementoalumno;
import java.util.ArrayList;

/**
 *
 * @author Becario-CDS
 */
public class MIalumnos extends MIInheritance implements MiInterface {

    Alumnos alumnoServicios;
    Complementoalumno  complementoalumno;
    Alumno alumnoIntegracion;
    TipoAlumno tipoAlumno;
    Aspirante aspirante;
    PlanEstudio planEstudio;
    Periodo periodo;
    Persona persona;
    TipoEstadocivil tipoEstadocivil;
    TipoSexo tipoSexo;
    Asentamiento asentamiento;
    Municipio municipio;
    EntidadFederativa entidadFederativa;

    public MIalumnos() {
        alumnoIntegracion = new Alumno();
        alumnoServicios = new Alumnos();
        tipoAlumno = new TipoAlumno();
        aspirante = new Aspirante();
        planEstudio = new PlanEstudio();
        periodo = new Periodo();
        persona = new Persona();
        tipoEstadocivil = new TipoEstadocivil();
        tipoSexo = new TipoSexo();
        asentamiento = new Asentamiento();
        municipio = new Municipio();
        entidadFederativa = new EntidadFederativa();

    }

    @Override
    public ArrayList migrate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
