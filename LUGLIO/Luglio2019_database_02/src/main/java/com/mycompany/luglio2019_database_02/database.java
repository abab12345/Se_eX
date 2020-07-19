/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luglio2019_database_02;

/**
 *
 * @author signorautoma
 */
import java.sql.*;

public class database {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/signorautoma/Database/luglio2019_02_database.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS DIRECTORS;");
            statement.executeUpdate("DROP TABLE IF EXISTS MOVIES;");
            statement.executeUpdate("CREATE TABLE DIRECTORS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name STRING,"
                    + "yearOfBirth STRING);");

            statement.executeUpdate("CREATE TABLE MOVIES ("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "directorID INTEGER,"
                    + "title STRING,"
                    + "year STRING,"
                    + "FOREIGN KEY(directorID) REFERENCES DIRECTORS(ID));");

            statement.executeUpdate("INSERT INTO DIRECTORS VALUES(0, 'Kathryn Bigelow', '1951');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Point Break', '1991');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'K-19: The Widowmaker', '2002');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'The Hurt Locker', '2008');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Zero Dark Thirty', '2012');");
            statement.executeUpdate("INSERT INTO MOVIES(directorID, title, year) VALUES(0, 'Detroit', '2017');");

            ResultSet rs1 = statement.executeQuery("SELECT * FROM DIRECTORS");
            while (rs1.next()) {
                System.out.println("Director " + rs1.getInt("ID") + " is " + rs1.getString("name") + " and was born in " + rs1.getString("yearOfBirth"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
