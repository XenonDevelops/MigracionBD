/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utez.actionSecretariaAcademica;

import com.utez.secAcademica.dao.AdeudoJpaController;
import com.utez.secAcademica.entityMan.EntityManSecAcademica;


/**
 *
 * @author Sergio
 */
public class UtilitySecretariaAcademica {
    private static AdeudoJpaController adeudoJpaController=new AdeudoJpaController(null,EntityManSecAcademica.getInstance());

    public static AdeudoJpaController getAdeudoJpaController() {
        return adeudoJpaController;
    }
    
}