/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.evaluacion.entityMan;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sergio
 */
public final class EntityManEvaluacion {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EvaluacionPU");

    public EntityManEvaluacion() {
    }

    public static EntityManagerFactory getInstance() {
        return emf;
    }
}
