/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TicketDTO;
import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.UsuarioTicketsLogic;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Path("users/{usersId: \\d+}/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioTicketsResource {

    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, un especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketsResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de la relacion de un usario y
     * sus tickets
     */
    @Inject
    private UsuarioTicketsLogic usuarioTicketsLogic;

    /**
     * variable empleada para acceder a la logica de un ticket
     */
    @Inject
    private TicketLogic ticketLogic;
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Guarda un ticket dentro de un usuario con la informacion que recibe el la
     * URL. Se devuelve el ticket que se guarda en la usuario.
     *
     * @param usersId Identificador de la usuario que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param ticketsId Identificador del ticket que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El ticket guardado en el usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @POST
    @Path("{ticketsId: \\d+}")
    public TicketDTO addTicket(@PathParam("usersId") Long usersId, @PathParam("ticketsId") Long ticketsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioTicketsResource addTicket: input: usersId {0} , ticketsId {1}", new Object[]{usersId, ticketsId});
        if (ticketLogic.getTicket(ticketsId) == null) {
            throw new WebApplicationException("El recurso /tickets/" + ticketsId + " no existe.", 404);
        }
        TicketEntity te = usuarioTicketsLogic.addTicket(usersId, ticketsId);
        TicketDTO ticketDTO = new TicketDTO(te);
        LOGGER.log(Level.INFO, "UsuarioTicketsResource addTicket: output: {0}", ticketDTO);
        return ticketDTO;
    }

    /**
     * Busca y devuelve todos los tickets que existen en un usuario.
     *
     * @param usersId El ID del autor del cual se buscan los Ticket
     * @return JSONArray {@link ticketDTO} - Los tickets encontrados en el
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TicketDTO> getTickets(@PathParam("usersId") Long usersId) {
        LOGGER.log(Level.INFO, "UsuarioTicketsResource getTickets: input: {0}", usersId);
        List<TicketEntity> list = usuarioTicketsLogic.getTickets(usersId);
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (TicketEntity te : list) {
            ticketDTOList.add(new TicketDTO(te));
        }
        LOGGER.log(Level.INFO, "UsuarioTicketsResource getTickets: output: {0}", ticketDTOList);
        return ticketDTOList;
    }

    /**
     * Busca y devuelve el ticket con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param usersId El ID del usuario del cual se busca el ticket
     * @param ticketsId El ID del ticket que se busca
     * @return {@link TicketDTO} - El ticket encontrado en el usuario.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @GET
    @Path("{ticketsId: \\d+}")
    public TicketDTO getTicket(@PathParam("usersId") Long usersId, @PathParam("ticketsId") Long ticketsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioTicketsResource getTicket: input: usersId {0} , ticketssId {1}", new Object[]{usersId, ticketsId});
        if (ticketLogic.getTicket(ticketsId) == null) {
            throw new WebApplicationException("El recurso /tickets/" + ticketsId + " no existe.", 404);
        }
        TicketDTO detailDTO = new TicketDTO(usuarioTicketsLogic.getTicket(usersId, ticketsId));
        LOGGER.log(Level.INFO, "UsuarioTicketsResource getTicket: output: {0}", detailDTO);
        return detailDTO;
    }

}
