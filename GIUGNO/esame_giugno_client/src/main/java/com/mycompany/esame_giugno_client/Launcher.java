/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esame_giugno_client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author signorautoma
 */
public class Launcher {
    public static void main(String[] args) throws NamingException, JMSException {
        System.out.println("Starting consumer");
        try {
            new ClientListener().start();
        } catch (JMSException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
