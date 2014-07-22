/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.secAcademica.utilidades;

import com.utez.secAcademica.dao.AsesorJpaController;
import com.utez.secAcademica.entityMan.EntityManSecAcademica;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        AsesorJpaController asesorJpaController=new AsesorJpaController(null, EntityManSecAcademica.getInstance());
        System.out.println("Cantidad de asesores: "+asesorJpaController.getAsesorCount());
    }
}
