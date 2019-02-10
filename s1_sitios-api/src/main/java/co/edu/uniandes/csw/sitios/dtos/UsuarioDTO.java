/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;

/**
 * UsuarioDTO implementa Serializable
 * @author estudiante
 */
public class UsuarioDTO implements Serializable{
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * nombre de usuario, String != ("" y null)
     */
    private String userName;
    
    /**
     * numero de tickets, int >= 0
     */
    private int numeroTickets;
    
    /**
     * Constructor UsuarioDTO vacio
     */
    public UsuarioDTO(){
         
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the numeroTickets
     */
    public int getNumeroTickets() {
        return numeroTickets;
    }

    /**
     * @param numeroTickets the numeroTickets to set
     */
    public void setNumeroTickets(int numeroTickets) {
        this.numeroTickets = numeroTickets;
    }
  
}
