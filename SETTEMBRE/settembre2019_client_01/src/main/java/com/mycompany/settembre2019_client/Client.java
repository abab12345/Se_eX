/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.settembre2019_client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.activemq.memory.list.MessageList;

/**
 *
 * @author signorautoma
 */
public class Client implements MessageListener {

    private TopicConnection connection;
    Connection connectionDB = null;

    public Client() {
        try {
            Class.forName("org.sqlite.JDBC");
            connectionDB = DriverManager.getConnection("jdbc:sqlite:/home/signorautoma/Database/settembre2019_database_01.db");

            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            Context ctx = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
            connection = (TopicConnection) connectionFactory.createConnection();

            TopicSession session = (TopicSession) connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = (Destination) ctx.lookup("dynamicTopics/Flights");
            TopicSubscriber subscriber = session.createSubscriber((Topic) destination);
            subscriber.setMessageListener(this);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void start() {
        try {
            connection.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void stop() {
        try {
            connection.stop();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void onMessage(Message msg) {
        Statement statement;
        try {
            statement = connectionDB.createStatement();
            statement.setQueryTimeout(30);

            String flight = msg.getStringProperty("flight");
            String status = msg.getStringProperty("status");
            System.out.println(flight + " " + status);

            statement.executeUpdate("INSERT INTO flights VALUES('" + flight + "', '" + status + "');");

        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
