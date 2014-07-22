/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.utilidades;

import com.utez.evaluacion.dao.FechasexamJpaController;
import com.utez.evaluacion.entityMan.EntityManEvaluacion;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        FechasexamJpaController fechasexamJpaController=new FechasexamJpaController(null, EntityManEvaluacion.getInstance());
        System.out.println("Cantidad fechas examen: "+fechasexamJpaController.getFechasexamCount());
    }
}
