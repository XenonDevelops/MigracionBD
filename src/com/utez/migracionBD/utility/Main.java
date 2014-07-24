/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import com.utez.evaluacion.dao.AsesorJpaController;
import com.utez.evaluacion.dao.ExamenJpaController;
import com.utez.evaluacion.entityMan.EntityManEvaluacion;
import com.utez.integracion.dao.AsesorCalificacionJpaController;
import com.utez.integracion.entityMan.EntityManIntegracion;
//import com.utez.secAcademica.dao.AsesorJpaController;
import com.utez.secAcademica.entityMan.EntityManSecAcademica;
import com.utez.servEscolares.dao.AlumnosJpaController;
import com.utez.servEscolares.dao.AsesoresJpaController;
import com.utez.servEscolares.entity.Alumnos;
import com.utez.servEscolares.entity.Aspirantes;
import com.utez.servEscolares.entityMan.EntityManServEscolares;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class Main {

    public static void main(String[] args) {
        //////////////EVALUACION

//        ArrayList<Alumnos> alumnos = (ArrayList<Alumnos>) UtilityServiciosEscolares.getAlumnosJpaController().findAlumnosEntities();
//        ArrayList<Aspirantes> aspirantes = (ArrayList<Aspirantes>) UtilityServiciosEscolares.getAspirantesJpaController().findAspirantesEntities();
//        ArrayList<String[]> arrayMatches = new ArrayList<String[]>();
//        int i = 0;
//        for (Alumnos alumno : alumnos) {
//            String[] match = new String[2];
//            String nombreAlumno = alumno.getNombreAlumno() + " " + alumno.getAPaterno() + " " + alumno.getAMaterno();
//            for (Aspirantes aspirante : aspirantes) {
//                String nombreAspirante = aspirante.getNombreAspirante();
//                if (nombreAlumno.equalsIgnoreCase(nombreAspirante)) {
//                    match[0] = alumno.getMatricula();
//                    match[1] = aspirante.getClaveAspirante() + "";
//                    arrayMatches.add(match);
//                    System.out.println(i);
//                    i++;
//                }
//
//            }
//
//        }
//        System.out.println(arrayMatches.size());
            ConsultarCurp consultarCurp= new ConsultarCurp();
            consultarCurp.consultar();
    }
}
