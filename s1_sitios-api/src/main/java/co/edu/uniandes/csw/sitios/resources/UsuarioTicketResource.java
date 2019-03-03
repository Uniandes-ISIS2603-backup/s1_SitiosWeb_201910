/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioTicketResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketResource.class.getName());

    @Inject
    private UsuarioTicketResource authorBookLogic;

    @Inject
    private TicketLogic ticketLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

}
