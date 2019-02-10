/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TicketDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("tickets")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TicketResource {
    public final static Logger LOGGER = Logger.getLogger(TicketResource.class.getName());
    
    /**
     * @return Lista de Tickets, null en caso de
     * que no halla.
     */
    @GET
    public List<TicketDTO> getTickets(){
        return null;
    }
    
}
