 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TicketDTO;
import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebTicketsLogic;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "sitioWeb/{id}/tickets".
 *
 * @author Daniel Preciado
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitioWebTicketsResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, un especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebTicketsResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un TicketLogic, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private TicketLogic ticketLogic;
    
    /**
     * variable empleada para acceder a la logica de un SitioWebTicketsLogic, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebTicketsLogic sitioWebTicketsLogic; 


    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Guarda un ticket dentro de un sitioWeb con la informacion que recibe 
     * por la URL. Se devuelve el ticket que se guarda en la sitioWeb.
     *
     * @param sitioWebId Identificador de la sitioWeb que se esta
     * actualizando. Este debe ser un cadena de dígitos.
     * @param ticketsId Identificador del ticket que se desea guardar. Este debe
     * ser un cadena de dígitos.
     * @return JSON {@link TicketDTO} - El ticket guardado en la sitioWeb.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @POST
    @Path("{ticketsId: \\d+}")
    public TicketDTO addTicket(@PathParam("sitioWebId") Long sitioWebId, @PathParam("ticketsId") Long ticketsId) 
    {
        LOGGER.log(Level.INFO, "SitioWebTicketsResource addTicket: input: sitioWebsID: {0} , ticketsId: {1}", new Object[]{sitioWebId, ticketsId});
        if (ticketLogic.getTicket(ticketsId) == null) 
        {
            throw new WebApplicationException("El recurso /tickets/" + ticketsId + " no existe.", 404);
        }
        TicketDTO ticketDTO = new TicketDTO(sitioWebTicketsLogic.addTicket(ticketsId, sitioWebId));
        LOGGER.log(Level.INFO, "SitioWebTicketsResource addTicket: output: {0}", ticketDTO);
        return ticketDTO;
    }

    /**
     * Busca y devuelve todos los tickets que existen en el sitioWeb.
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta buscando.
     * Este debe ser un cadena de dígitos.
     * @return JSONArray {@link TicketDTO} - Los tickets encontrados en el
     * sitioWeb. Si no hay ninguno retorna un lista vacía.
     */
    @GET
    public List<TicketDTO> getTickets(@PathParam("sitioWebId") Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "SitioWebTicketsResource getTickets: input: {0}", sitioWebId);
        List<TicketDTO> listaTicketsDTOs = ticketsListEntity2DTO(sitioWebTicketsLogic.getTickets(sitioWebId));
        LOGGER.log(Level.INFO, "SitioWebTicketsResource getTickets: output: {0}", listaTicketsDTOs);
        return listaTicketsDTOs;
    }

    /**
     * Busca el ticket con el id asociado dentro de la sitioWeb con id asociado.
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta buscando.
     * Este debe ser un cadena de dígitos.
     * @param ticketsId Identificador del ticket que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @return JSON {@link TicketDTO} - El ticket buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket en la
     * sitioWeb.
     */
    @GET
    @Path("{ticketsId: \\d+}")
    public TicketDTO getTicket(@PathParam("sitioWebId") Long sitioWebId, @PathParam("ticketsId") Long ticketsId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "SitioWebTicketsResource getTicket: input: sitioWebsID: {0} , ticketsId: {1}", new Object[]{sitioWebId, ticketsId});
        if (ticketLogic.getTicket(ticketsId) == null) 
        {
            throw new WebApplicationException("El recurso /sitioWebs/" + sitioWebId + "/tickets/" + ticketsId + " no existe.", 404);
        }
        TicketDTO ticketDTO = new TicketDTO(sitioWebTicketsLogic.getTicket(sitioWebId, ticketsId));
        LOGGER.log(Level.INFO, "SitioWebTicketsResource getTicket: output: {0}", ticketDTO);
        return ticketDTO;
    }

    /**
     * Remplaza las instancias de Ticket asociadas a una instancia de Editorial
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta
     * remplazando. Este debe ser un cadena de dígitos.
     * @param tickets JSONArray {@link TicketDTO} El arreglo de tickets nuevo para la
     * sitioWeb.
     * @return JSON {@link TicketDTO} - El arreglo de tickets guardado en la
     * sitioWeb.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ticket.
     */
    @PUT
    public List<TicketDTO> replaceTickets(@PathParam("sitioWebId") Long sitioWebId, List<TicketDTO> tickets) 
    {
        LOGGER.log(Level.INFO, "SitioWebTicketsResource replaceTickets: input: sitioWebId: {0} , tickets: {1}", new Object[]{sitioWebId, tickets});
        for (TicketDTO ticket : tickets) 
        {
            if (ticketLogic.getTicket(ticket.getId()) == null) 
            {
                throw new WebApplicationException("El recurso /tickets/" + ticket.getId() + " no existe.", 404);
            }
        }
        List<TicketDTO> listaTicketsDTOs = ticketsListEntity2DTO(sitioWebTicketsLogic.replaceTickets(sitioWebId, ticketsListDTO2Entity(tickets)));
        LOGGER.log(Level.INFO, "SitioWebTicketsResource replaceTickets: output: {0}", listaTicketsDTOs);
        return listaTicketsDTOs;
    }

    /**
     * Convierte un lista de TicketEntity a una lista de TicketDTO.
     *
     * @param entityList Lista de TicketEntity a convertir.
     * @return Lista de TicketDTO convertida.
     */
    private List<TicketDTO> ticketsListEntity2DTO(List<TicketEntity> entityList) 
    {
        List<TicketDTO> list = new ArrayList();
        for (TicketEntity entity : entityList) 
        {
            list.add(new TicketDTO(entity));
        }
        return list;
    }

    /**
     * Convierte un lista de TicketDTO a un lista de TicketEntity.
     *
     * @param dtos Lista de TicketDTO a convertir.
     * @return Lista de TicketEntity convertida.
     */
    private List<TicketEntity> ticketsListDTO2Entity(List<TicketDTO> dtos) 
    {
        List<TicketEntity> list = new ArrayList<>();
        for (TicketDTO dto : dtos) 
        {
            list.add(dto.toEntity());
        }
        return list;
    }
}

