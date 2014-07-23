/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class MI {

    public ArrayList result;
    private ArrayList resultadoRegistro;
    private int count;
    private boolean success;

    public MI() {
        success = false;
        count = 0;
        result = new ArrayList();
        resultadoRegistro = new ArrayList();
    }

    public void addErrorRow(String id, String error) {
//        resultadoRegistro.add("id registro");//por cada registro
//        resultadoRegistro.add("error o exception");//por cada registro
        //count++
    }

    public void closeMI() {
//        result.add("error, exceptio or success");//errores de hibernate 
//        result.add("cuantos de cuantos");//cuantos resgitros guardados de un total
//        result.add(resultadoRegistro);//lista resultadoRegistro
    }
}
