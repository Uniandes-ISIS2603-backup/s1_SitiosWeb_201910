/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.util.Collection;

/**
 *
 * @author estudiante
 */
public class UsuarioEntity extends PersonaEntity{
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
    private Integer numeroTickets;
    
    /**
     * Constructor UsuarioDTO vacio
     */
    public UsuarioEntity(){
         
    }
    
     /**
     * Collecion de tickets
     */
    @javax.persistence.OneToMany(
        mappedBy = "usuario", //verificar.
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<TicketEntity> tickets;

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
    public Integer getNumeroTickets() {
        return numeroTickets;
    }

    /**
     * @param numeroTickets the numeroTickets to set
     */
    public void setNumeroTickets(Integer numeroTickets) {
        this.numeroTickets = numeroTickets;
    }

    /**
     * @return the tickets
     */
    public Collection<TicketEntity> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(Collection<TicketEntity> tickets) {
        this.tickets = tickets;
    }
    
}
