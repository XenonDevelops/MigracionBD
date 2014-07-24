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
import com.utez.servEscolares.entityMan.EntityManServEscolares;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        //////////////EVALUACION
        System.out.println(new AlumnosJpaController(null, EntityManServEscolares.getInstance()).getAlumnosCount());
    }
}