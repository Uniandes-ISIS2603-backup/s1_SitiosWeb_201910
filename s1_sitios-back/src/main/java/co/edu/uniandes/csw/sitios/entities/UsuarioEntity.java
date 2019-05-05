/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Entity
public class UsuarioEntity extends PersonaEntity {

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________

    /**
     * nombre de usuario, String != ("" y null)
     */
    private String userName;

    /**
     * numero de tickets, int >= 0
     */
    private Integer numeroTickets;

    /**
     * Collecion de tickets de un usuario
     */
    @PodamExclude
    @OneToMany(mappedBy = "usuarioAsociado", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TicketEntity> tickets;

    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Constructor UsuarioDTO vacio
     */
    public UsuarioEntity() {

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
    public List<TicketEntity> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

}
