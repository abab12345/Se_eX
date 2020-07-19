/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.settembre2019_producer_01;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author signorautoma
 */
public class Server {

    public static void main(String[] args) throws NamingException, JMSException {
        Producer p = new Producer();
        p.start();
    }
}
