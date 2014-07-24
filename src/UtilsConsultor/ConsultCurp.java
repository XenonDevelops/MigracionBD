/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsConsultor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omr
 */
public class ConsultCurp {

    public static void main(String[] args) {
            SendRequestHttpUtils sendRequest = new SendRequestHttpUtils();
        try {
            sendRequest.doPost();
        } catch (IOException ex) {
            Logger.getLogger(ConsultCurp.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

}
