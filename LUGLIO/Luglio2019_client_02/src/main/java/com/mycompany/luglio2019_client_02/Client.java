/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.luglio2019_client_02;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author signorautoma
 */
public class Client {

    private static final String BASE_URL = "http://localhost:8080/movieDetail/movies";
    private static CloseableHttpClient client;

    public static void main(String[] args) throws MalformedURLException, IOException {
        client = HttpClients.createDefault();

        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(BASE_URL);

        InputStream input = url.openStream();

        List<MovieDetail> movies = (ArrayList<MovieDetail>) mapper.readValue(input, ArrayList.class);
        System.out.println(movies);
       
        
    }
}
