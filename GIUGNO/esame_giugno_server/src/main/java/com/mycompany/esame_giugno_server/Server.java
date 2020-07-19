package com.mycompany.esame_giugno_server;

import javax.xml.ws.Endpoint;

public class Server {

    public static void main(String[] args) {
        Professor.insertProfessor(new Professor("Toro", "Cas"));
        Professor.insertProfessor(new Professor("San", "Ba"));
        Professor.insertProfessor(new Professor("Quinto", "Tordi"));
        Professor.insertProfessor(new Professor("Aschifo", "Javaf"));
        Professor.insertProfessor(new Professor("Na", "Bana"));

        ServiceImpl implementor = new ServiceImpl();
        String address = "http://localhost:8080/Service";
        Endpoint.publish(address, implementor);
        System.out.println("Server online...");
    }
}
