/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esame_giugno_server;

import javax.jws.WebService;

/**
 *
 * @author signorautoma
 */

@WebService(endpointInterface = "com.mycompany.esame_giugno_server.ServiceInterface")
public class ServiceImpl implements ServiceInterface {
    public Professor getDetails(String id) {
        return Professor.getProfessor(id);
    }
}
