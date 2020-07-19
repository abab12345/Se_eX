/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.settembre2019_producer_01;

import java.util.Properties;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author signorautoma
 */
public class Producer {

    Properties properties = null;
    Context jndiContex = null;
    ConnectionFactory connectionFactory = null;
    Connection connection = null;
    Session session = null;
    Destination destination = null;
    String destinationName = "dynamicTopics/Flights";
    private MessageProducer producer = null;

    Integer id = 0;
    
    private String flight;
    private Boolean status;
    char c1;
    char c2;
    String number;
    private Random random = new Random();

    public String getId() {
        id ++;
        return Integer.toString(this.id);
    }

    public String getFlight() {
        StringBuilder buff = new StringBuilder(1);

        c1 = (char) (random.nextInt(26) + 'a');
        c2 = (char) (random.nextInt(26) + 'a');

        number = Integer.toString(random.nextInt(899) + 100);
        buff.append(c1);
        buff.append(c2);
        buff.append(number);

        return buff.toString().toUpperCase();
    }

    public Boolean getStatus() {
        return random.nextBoolean();
    }

    Properties props = new Properties();

    public void start() throws NamingException, JMSException {
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            jndiContex = new InitialContext(properties);

        } catch (NamingException e) {
            System.out.println(e.toString());
            System.exit(1);
        }

        connectionFactory = (ConnectionFactory) jndiContex.lookup("ConnectionFactory");
        destination = (Destination) jndiContex.lookup(destinationName);

        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);

        String id;
        String flight;
        Boolean status;
        String text;
        TextMessage message = session.createTextMessage();

        while (true) {
            id = getId();
            flight = getFlight();
            status = getStatus();

            message.setStringProperty("flight", flight);
            message.setBooleanProperty("status", status);

            text = "Item: " + id + ": " + flight + " has landed : " + status;
            message.setText(text);

            this.producer.send(message);
            System.err.println(text);

            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
