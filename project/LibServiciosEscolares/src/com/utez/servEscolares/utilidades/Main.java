/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.servEscolares.utilidades;

import com.utez.servEscolares.dao.RequisitostramitetitulacionJpaController;
import com.utez.servEscolares.entityMan.EntityManServEscolares;

/**
 *
 * @author Sergio
 */
public class Main {
    public static void main(String[] args) {
        RequisitostramitetitulacionJpaController requisitostramitetitulacionJpaController=new RequisitostramitetitulacionJpaController(null, EntityManServEscolares.getInstance());
        System.out.println("Cantidad Requisitos: "+requisitostramitetitulacionJpaController.getRequisitostramitetitulacionCount());
    }
}
