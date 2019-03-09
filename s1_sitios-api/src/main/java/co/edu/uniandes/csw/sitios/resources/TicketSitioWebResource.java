/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.TicketDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.TicketSitioWebLogic;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "sitioWeb/{id}/tickets".
 * 
 * @author Daniel Preciado
 */

@Path("sitiosWeb/{sitiosWebId: \\d+}/ticket")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TicketSitioWebResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(TicketSitioWebResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un Ticket, esto se logra
     * mediante inyeccion de dependencias
     */

    @Inject
    private TicketLogic ticketLogic; 

    /**
     * variable empleada para acceder a la logica de un TicketSitioWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private TicketSitioWebLogic ticketSitioWebLogic; 

    /**
     * variable empleada para acceder a la logica de un Ticket, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebLogic sitioWebLogic; 
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Remplaza la instancia de SitioWeb asociada a un Ticket.
     *
     * @param ticketId Identificador del ticket que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param sitioWeb el  sitioWeb que del que será el ticket.
     * @return JSON {@link TicketDTO} - El arreglo de tickets guardado en la
     * sitioWeb.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitioWeb o el
     * ticket.
     */
    @PUT
    public TicketDTO replaceSitioWeb (@PathParam("ticketId") Long ticketId, SitioWebDTO sitioWeb) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TicketSitioWebResource replaceSitioWeb: input: ticketId{0} , SitioWeb:{1}", new Object[]{ticketId, sitioWeb});
        if (ticketLogic.getTicket(ticketId) == null) 
        {
            throw new WebApplicationException("El recurso /tickets/" + ticketId + " no existe.", 404);
        }
        if (sitioWebLogic.getWebSite(sitioWeb.getId()) == null)
        {
            throw new WebApplicationException("El recurso /sitioWeb/" + sitioWeb.getId() + " no existe.", 404);
        }
        TicketDTO ticketDTO = new TicketDTO(ticketSitioWebLogic.replaceSitioAsociado(ticketId, sitioWeb.getId()));
        LOGGER.log(Level.INFO, "TicketSitioWebResource replaceSitioWeb: output: {0}", ticketDTO);
        return ticketDTO;
    }
    
}
