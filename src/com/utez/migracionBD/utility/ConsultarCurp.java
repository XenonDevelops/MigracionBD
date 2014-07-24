/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.migracionBD.utility;

import UtilsConsultor.ConsultCurp;
import UtilsConsultor.SendRequestHttpUtils;
import com.utez.servEscolares.entity.Alumnos;
import com.utez.servEscolares.entity.Complementoalumno;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Becario-CDS
 */
public class ConsultarCurp {

    private static ArrayList<String> CODENTIDAD;
    private static ArrayList<String> NOMBREENTIDAD;

    public ConsultarCurp() {
        CODENTIDAD = new ArrayList();
        NOMBREENTIDAD = new ArrayList();

        CODENTIDAD.add("AS");
        NOMBREENTIDAD.add("Aguascalientes");
        CODENTIDAD.add("BC");
        NOMBREENTIDAD.add("Baja California");
        CODENTIDAD.add("BS");
        NOMBREENTIDAD.add("Baja California Sur");
        CODENTIDAD.add("CC");
        NOMBREENTIDAD.add("Campeche");
        CODENTIDAD.add("CS");
        NOMBREENTIDAD.add("Chiapas");
        CODENTIDAD.add("CH");
        NOMBREENTIDAD.add("Chihuahua");
        CODENTIDAD.add("CL");
        NOMBREENTIDAD.add("Coahuila");
        CODENTIDAD.add("CM");
        NOMBREENTIDAD.add("Colima");
        CODENTIDAD.add("DF");
        NOMBREENTIDAD.add("Distrito Federal");
        CODENTIDAD.add("DG");
        NOMBREENTIDAD.add("Durango");
        CODENTIDAD.add("GT");
        NOMBREENTIDAD.add("Guanajuato");
        CODENTIDAD.add("GR");
        NOMBREENTIDAD.add("Guerrero");
        CODENTIDAD.add("HG");
        NOMBREENTIDAD.add("Hidalgo");
        CODENTIDAD.add("JC");
        NOMBREENTIDAD.add("Jalisco");
        CODENTIDAD.add("MC");
        NOMBREENTIDAD.add("Mexico");
        CODENTIDAD.add("MS");
        NOMBREENTIDAD.add("Morelos");
        CODENTIDAD.add("MN");
        NOMBREENTIDAD.add("Michoacan");
        CODENTIDAD.add("NT");
        NOMBREENTIDAD.add("Nayarit");
        CODENTIDAD.add("NL");
        NOMBREENTIDAD.add("Nuevo Leon");
        CODENTIDAD.add("OC");
        NOMBREENTIDAD.add("Oaxaca");
        CODENTIDAD.add("PL");
        NOMBREENTIDAD.add("Puebla");
        CODENTIDAD.add("QT");
        NOMBREENTIDAD.add("Queretaro");
        CODENTIDAD.add("QR");
        NOMBREENTIDAD.add("Quintana Roo");
        CODENTIDAD.add("SP");
        NOMBREENTIDAD.add("San Luis Potosi");
        CODENTIDAD.add("SL");
        NOMBREENTIDAD.add("Sinaloa");
        CODENTIDAD.add("SR");
        NOMBREENTIDAD.add("Sonora");
        CODENTIDAD.add("TC");
        NOMBREENTIDAD.add("Tabasco");
        CODENTIDAD.add("TS");
        NOMBREENTIDAD.add("Tamaulipas");
        CODENTIDAD.add("TL");
        NOMBREENTIDAD.add("Tlaxcala");
        CODENTIDAD.add("VZ");
        NOMBREENTIDAD.add("Veracruz");
        CODENTIDAD.add("YN");
        NOMBREENTIDAD.add("Yucatan");
        CODENTIDAD.add("ZS");
        NOMBREENTIDAD.add("Zacatecas");
        CODENTIDAD.add("NE");
        NOMBREENTIDAD.add("Extranjero");
    }

    private String[] formatoFecha(String[] fechaNac) {

        if (fechaNac[0].charAt(0) == '0') {
            fechaNac[0] = fechaNac[0].substring(1);
        }
        if (fechaNac[1].charAt(0) == '0') {
            fechaNac[1] = fechaNac[1].substring(1);
        }
        return fechaNac;
    }

    private String formatoEntidadFed(String entidadFed) {
        String cod = "";
        int cont = 0;
        for (String nombreEntidad : NOMBREENTIDAD) {

            if (entidadFed.contains(nombreEntidad)) {
                cod = CODENTIDAD.get(cont);
            }
            cont++;
        }

        return cod;
    }

    private char formatGender(String genderAlumno) {
        char gender = ' ';
        if (genderAlumno.equalsIgnoreCase("masculino")) {
            gender = 'H';
        } else {
            gender = 'M';
        }
        return gender;
    }

    public ArrayList consultar() {
        ArrayList<Alumnos> lAlumnos = (ArrayList<Alumnos>) UtilityServiciosEscolares.getAlumnosJpaController().findAlumnosEntities();
        ArrayList<Complementoalumno> complemento = (ArrayList<Complementoalumno>) UtilityServiciosEscolares.getComplementoalumnoJpaController().findComplementoalumnoEntities();
        String[] arregloPersona = null;
        ArrayList curpConsulta = new ArrayList();
        SendRequestHttpUtils sendRequest = new SendRequestHttpUtils();
        for (Alumnos alumno : lAlumnos) {
            arregloPersona = new String[8];
            for (Complementoalumno complementoAlumno : complemento) {
                if (complementoAlumno.getLugarNacimiento().equals("") || complementoAlumno.getFechaNacimiento().toString().equals("")) {
                    break;
                }
                String[] fechaNac = (complementoAlumno.getFechaNacimiento() + "").split("-");
                fechaNac = formatoFecha(fechaNac);
                String dia = fechaNac[2];
                String mes = fechaNac[1];
                String anno = fechaNac[0];
                arregloPersona[0] = alumno.getAPaterno();
                arregloPersona[1] = alumno.getAPaterno();
                arregloPersona[2] = alumno.getNombreAlumno();
                arregloPersona[3] = dia;
                arregloPersona[4] = formatGender(complementoAlumno.getSexo()) + "";
                arregloPersona[5] = formatoEntidadFed(complementoAlumno.getLugarNacimiento());
                System.out.println("");
                arregloPersona[6] = mes;
                arregloPersona[7] = anno;
                try {
                    sendRequest.doPost(arregloPersona);
                } catch (IOException ex) {
                    Logger.getLogger(ConsultCurp.class.getName()).log(Level.SEVERE, null, ex);
                }
                //LAGA940529HMSGRN07
                //LAGA940529HMSGRN07
                //TETR741220HNN      Y08
            }
        }

        return curpConsulta;
    }
}
