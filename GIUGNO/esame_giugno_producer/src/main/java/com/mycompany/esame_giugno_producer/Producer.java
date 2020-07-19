/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esame_giugno_producer;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class Producer {

    Properties properties = null;
    Context jndiContex = null;
    ConnectionFactory connectionFactory = null;
    Connection connection = null;
    Session session = null;
    Destination destination = null;
    String destinationName = "dynamicTopics/professors";
    private MessageProducer producer = null;

    private String id;
    private float rank;
    private Random seed = new Random();

    private String getRandomID() {
        return Integer.toString(seed.nextInt(4 + 1));
    }

    private float getRandomRank() {
        return seed.nextFloat() * 100;
    }

    public void start() throws NamingException, JMSException {
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContex = new InitialContext(properties);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        connectionFactory = (ConnectionFactory) jndiContex.lookup("ConnectionFactory");
        destination = (Destination) jndiContex.lookup(destinationName);
        
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);

        id = getRandomID();
        rank = getRandomRank();
        String text;
        TextMessage message = session.createTextMessage();

        while (true) {
            id = getRandomID();
            rank = getRandomRank();
            text = "Professor " + id + " has rank: " + rank;
            message.setFloatProperty("rank", rank);
            message.setStringProperty("id", id);
            message.setText(text);
            
            this.producer.send(message);
            System.out.println(text);
            
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
