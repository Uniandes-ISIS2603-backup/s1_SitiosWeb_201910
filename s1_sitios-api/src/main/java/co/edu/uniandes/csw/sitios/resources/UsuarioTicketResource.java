/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.ejb.TicketLogic;
import co.edu.uniandes.csw.sitios.ejb.UsuarioLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Path("users/{usersId: \\d+}/tickets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioTicketResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketResource.class.getName());

    @Inject
    private UsuarioLogic usuarioLogic;

    @Inject
    private TicketLogic ticketLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

}
