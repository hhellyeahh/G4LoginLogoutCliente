/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import classes.LoginLogout;
import interfaces.ClientImplementation;

/**
 *
 * @author Leire&Zulu
 */
public class FactoryClient {

    private static LoginLogout data;

    /**
     * Load the data variable, if it is not previously loaded
     *
     * @return data LoginLogout
     */
    public static LoginLogout getLoginLogout() {

        data = new ClientImplementation();

        return data;
    }
}
