/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.integracion.utilidades;

import com.utez.integracion.dao.AspiranteJpaController;
import com.utez.integracion.entityMan.EntityManIntegracion;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        AspiranteJpaController aspiranteJpaController=new AspiranteJpaController(null, EntityManIntegracion.getInstance());
        System.out.println("Cantidad de aspirantes: "+aspiranteJpaController.getAspiranteCount());
    }
}
