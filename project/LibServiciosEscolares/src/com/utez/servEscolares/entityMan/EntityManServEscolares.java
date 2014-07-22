/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.entityMan;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sergio
 */
public final class EntityManServEscolares {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiciosEscolaresPU");

    public EntityManServEscolares() {
    }

    public static EntityManagerFactory getInstance() {
        return emf;
    }
}
