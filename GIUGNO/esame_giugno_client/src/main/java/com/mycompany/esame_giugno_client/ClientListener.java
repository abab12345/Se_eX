/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esame_giugno_client;

import com.mycompany.esame_giugno_server.Professor;
import com.mycompany.esame_giugno_server.ServiceImplService;
import com.mycompany.esame_giugno_server.ServiceInterface;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientListener implements MessageListener {

    Properties properties = null;
    Context jndiContex = null;
    ConnectionFactory connectionFactory = null;
    TopicConnection connection = null;
    Destination destination = null;
    String destinationName = "dynamicTopics/professors";

    public ClientListener() throws NamingException, NamingException, JMSException, JMSException {
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

        connection = (TopicConnection) connectionFactory.createConnection();
        TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = session.createSubscriber((Topic) destination);
        subscriber.setMessageListener(this);
    }

    void start() {
        try {
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stop() {
        try {
            connection.stop();
        } catch (JMSException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message msg) {
        try {
            String id = msg.getStringProperty("id");
            float rank = msg.getFloatProperty("rank");
            System.out.println("New message: " + id +" " + rank);
            
            ServiceImplService service = new ServiceImplService();
            ServiceInterface port = service.getServiceImplPort();
            
            Professor res = port.getDetails(id);
            
            if(res == null) {
                System.err.println("Professor not found...");
            } else {
                System.out.println("Professor " + id + " is: " + res.getName() + " " + res.getSurname() + " - rank: "+ rank);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
