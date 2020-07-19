package com.mycompany.esame_giugno_server;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author signorautoma
 */
@XmlRootElement(name = "Professor")
public class Professor {

    private String name;
    private String surname;
    private static Integer id = -1;
    private static Map<String, Professor> professors = new HashMap<String, Professor>();

    static void insertProfessor(Professor professor) {
        System.out.println("Insert new Professor");
        id ++;
        professors.put(id.toString(), professor);
    }

    public Professor() {
    }

    public Professor(String nome, String cognome) {
        this.name = nome;
        this.surname = cognome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        Professor.id = id;
    }

    public static Map<String, Professor> getProfessors() {
        return professors;
    }

    public static void setProfessors(Map<String, Professor> professors) {
        Professor.professors = professors;
    }

    static Professor getProfessor(String id) {
        System.out.println("Get professor: " + id + " that is "+ professors.get(id));
        return professors.get(id);
    }

    @Override
    public String toString() {
        return "Professor{" + "name=" + name + ", surname=" + surname + '}';
    }

}
