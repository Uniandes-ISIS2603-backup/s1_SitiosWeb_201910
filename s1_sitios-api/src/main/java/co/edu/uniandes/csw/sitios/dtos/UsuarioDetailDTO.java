/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * UsuarioDetailDTO implementa UsuarioDTO
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
    /**
     * Lista de tickets que puede tener un usuario.
     */
    private List<TicketDTO> tickets;

    /**
     *Constructor de UsuarioDetailDTO vacio
     */
    public UsuarioDetailDTO() {
    }
    
    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public UsuarioDetailDTO( UsuarioEntity entity ){
        super(entity);
        if( entity != null ){
            setTickets(entity.getTickets()); 
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return UsuarioEntity
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setTickets(tickets);
        return entity;
    }
    
    /**
     * @return the tickets
     */
    public List<TicketDTO> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
    
    
    
}
