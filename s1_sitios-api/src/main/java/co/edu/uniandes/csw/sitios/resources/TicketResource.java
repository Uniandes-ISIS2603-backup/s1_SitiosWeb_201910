/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TicketDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketSitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso para un "TicketResource".
 * 
 * @author Daniel Preciado
 */
@Path("tickets")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TicketResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    
    private static final Logger LOGGER = Logger.getLogger(TicketResource.class.getName());
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un Ticket, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    TicketLogic ticketLogic;
    
    /**
     * variable empleada para acceder a la logica de un SitioWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    SitioWebLogic sitioWebLogic;
    
    /**
     * variable empleada para acceder a la logica de un TicketSitioWebLogic, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    TicketSitioWebLogic ticketSitioWebLogic;
    
      
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Crea un ticket con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param ticket {@link TicketDTO} - el ticket que se desea
     * guardar.
     * @return JSON {@link TicketDTO} - el ticket guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el ticket.
     */
    @POST
    public TicketDTO createTicket(TicketDTO ticket) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TicketResource createTicket: input: {0}", ticket.toString());
        TicketEntity ticketEntity = ticket.toEntity();
        TicketEntity pTicketEntity = ticketLogic.createTicket(ticketEntity);
        TicketDTO nuevoTicketDTO = new TicketDTO(pTicketEntity);
        LOGGER.log(Level.INFO, "TicketResource createTicket: output: {0}", nuevoTicketDTO.toString());
        return nuevoTicketDTO;
    }

    /**
     * Busca y devuelve todos los tickets que existen en la app.
     *
     * @return JSONArray {@link TicketDTO} - los tickets encontrados en
     * la app. Si no hay ninguno, entonces retorna una lista vacía.
     */
    @GET
    public List<TicketDTO> getTickets() 
    {
        LOGGER.info("Ticketsresource getTickets: input: void");
        List<TicketDTO> ticketRetorno = listEntity2DetailDTO(ticketLogic.getTickets());
        LOGGER.log(Level.INFO, "Ticketsresource getTickets: output: {0}", ticketRetorno.toString());
        return ticketRetorno;
    }

    /**
     * Busca el ticket con el id asociado recibido en la URL y lo devuelve.
     *
     * @param ticketId Identificador de el ticket que se esta buscando.
     * @return JSON {@link TicketDTO} - el ticket buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @GET
    @Path("{ticketId: \\d+}")
    public TicketDTO getTicket(@PathParam("ticketId") Long ticketId) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "TicketResource getTicket: input: {0}", ticketId);
        TicketEntity ticketEntity = ticketLogic.getTicket(ticketId);
        if (ticketEntity == null) 
        {
            throw new WebApplicationException("El recurso /ticket/" + ticketId + " no existe.", 404);
        }
        TicketDTO detailDTO = new TicketDTO(ticketEntity);
        LOGGER.log(Level.INFO, "TicketResource getTicket: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza el ticket con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param ticketId Identificador de el ticket que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param ticket {@link TicketDTO} el ticket que se desea guardar.
     * @return JSON {@link TicketDTO} - el ticket guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket a
     * actualizar.
     */
    @PUT
    @Path("{ticketId: \\d+}")
    public TicketDTO updateTicket(@PathParam("ticketId") Long ticketId, TicketDTO ticket) throws WebApplicationException 
    {
        LOGGER.log(Level.INFO, "TicketResource updateTicket: input: id:{0} , ticket: {1}", new Object[]{ticketId, ticket.toString()});
        ticket.setId(ticketId);
        if (ticketLogic.getTicket(ticketId) == null) 
        {
            throw new WebApplicationException("El recurso /ticket/" + ticketId + " no existe.", 404);
        }
        TicketDTO detailDTO = new TicketDTO(ticketLogic.updateTicket(ticketId, ticket.toEntity()));
        LOGGER.log(Level.INFO, "TicketResource updateTicket: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el ticket con el id asociado recibido en la URL.
     *
     * @param ticketId Identificador de el ticket que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el ticket.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @DELETE
    @Path("{ticketId: \\d+}")
    public void deleteTicket(@PathParam("ticketId") Long ticketId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TicketResource deleteTicket: input: {0}", ticketId);
        if (ticketLogic.getTicket(ticketId) == null) 
        {
            throw new WebApplicationException("El recurso /ticket/" + ticketId + " no existe.", 404);
        }
        ticketSitioWebLogic.removeSitioWeb(ticketId);
        ticketLogic.deleteTicket(ticketId);
        LOGGER.info("TicketResource deleteTicket: output: void");
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDTO (json)
     *
     * @param entityList corresponde a la lista de ticketes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de ticketes en forma DTO (json)
     */
    private List<TicketDTO> listEntity2DetailDTO(List<TicketEntity> entityList) 
    {
        List<TicketDTO> list = new ArrayList<>();
        for (TicketEntity entity : entityList) {
            list.add(new TicketDTO(entity));
        }
        return list;
    }
    
}
