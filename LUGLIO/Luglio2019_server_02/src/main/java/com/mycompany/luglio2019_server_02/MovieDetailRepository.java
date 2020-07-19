/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luglio2019_server_02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;

/**
 *
 * @author signorautoma
 */
@Path("/movieDetail")
public class MovieDetailRepository {

    private Connection con;
    private Statement statement;

    public MovieDetailRepository() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:/home/signorautoma/Database/luglio2019_02_database.db");
            statement = con.createStatement();
            statement.setQueryTimeout(30);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GET
    @Path("/movies")
    @Produces("application/json")
    public List<MovieDetail> getMovieDetails() throws SQLException {

        List<MovieDetail> movies = new ArrayList<>();

        ResultSet rs1 = statement.executeQuery("SELECT * FROM MOVIES JOIN DIRECTORS ON MOVIES.directorID == DIRECTORS.ID");

        while (rs1.next()) {
            MovieDetail movie = new MovieDetail();
            movie.setDirectorName(rs1.getString("name"));
            movie.setDirectorYearOfBirth(rs1.getString("yearOfBirth"));
            movie.setMovieTitle(rs1.getString("title"));
            movie.setMovieYear(rs1.getString("year"));

            System.out.println("Movie: "
                    + rs1.getString("title")
                    + " Director "
                    + rs1.getInt("ID")
                    + "is "
                    + rs1.getString("name") 
                    + " and was born in " 
                    + rs1.getString("yearOfBirth"));
            
            movies.add(movie);
        }

        return movies;
    }

}
