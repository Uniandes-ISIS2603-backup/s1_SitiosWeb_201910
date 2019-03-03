/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
            if(entity.getTickets()!=null)
            {
                for(TicketEntity ticketEntity:entity.getTickets())
                {
                    tickets.add(new TicketDTO(ticketEntity));
                }
            }

        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return UsuarioEntity
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        if (tickets != null) {
            List<TicketEntity> ticket = new ArrayList<>();
            for (TicketDTO ticketDTO : tickets) {
                ticket.add(ticketDTO.toEntity());

                entity.setTickets(ticket);
            }

        }
        return entity;
    }
    

    
    
}
