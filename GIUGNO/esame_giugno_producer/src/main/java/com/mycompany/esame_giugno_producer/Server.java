/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esame_giugno_producer;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author signorautoma
 */
public class Server {
    public static void main(String[] args) throws NamingException, JMSException {
        Producer producer = new Producer();
        producer.start();
    }
}
