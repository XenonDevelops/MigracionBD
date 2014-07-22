/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.entityMan;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sergio
 */
public final class EntityManIntegracion {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegracionPU");

    public EntityManIntegracion() {
    }

    public static EntityManagerFactory getInstance() {
        return emf;
    }
}
