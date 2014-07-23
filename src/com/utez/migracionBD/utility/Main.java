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
import com.utez.servEscolares.dao.AsesoresJpaController;
import com.utez.servEscolares.entityMan.EntityManServEscolares;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        //////////////EVALUACION
        AsesorJpaController asesorEvaluacion = new AsesorJpaController(null, EntityManEvaluacion.getInstance());
        System.out.println("asesor en la bd Evaluacion: "+asesorEvaluacion.getAsesorCount());
        ExamenJpaController examenJpaController=new ExamenJpaController(null, EntityManEvaluacion.getInstance());
        System.out.println("examen en la bd Evaluacion "+examenJpaController.getExamenCount());
        /////////////INTEGRACION
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        AsesorCalificacionJpaController asesorIntegracion=new AsesorCalificacionJpaController(null, EntityManIntegracion.getInstance());
        System.out.println("asesor en la bd Integracion"+asesorIntegracion.getAsesorCalificacionCount());
        /////////////SECRETARIA ACADEMICA
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        com.utez.secAcademica.dao.AsesorJpaController asesorSecretaria=new com.utez.secAcademica.dao.AsesorJpaController(null, EntityManSecAcademica.getInstance());
        System.out.println("Asesor en la bd Secretaria Academica: "+asesorSecretaria.getAsesorCount());
        /////////////SERVICIOS ESCOLARES
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        AsesoresJpaController asesoresServicos=new AsesoresJpaController(null, EntityManServEscolares.getInstance());
        System.out.println("Asesores en la bd Servicios: "+asesoresServicos.getAsesoresCount());
    }
}