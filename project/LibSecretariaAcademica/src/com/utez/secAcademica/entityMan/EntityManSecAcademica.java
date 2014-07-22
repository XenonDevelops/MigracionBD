/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.entityMan;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Sergio
 */
public final class EntityManSecAcademica {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("SecretariaAcademicaPU");

    public EntityManSecAcademica(){
    
    }
    
    public static EntityManagerFactory getInstance() {
        return emf;
    }
}
