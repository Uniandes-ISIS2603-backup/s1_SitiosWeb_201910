/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import co.edu.uniandes.csw.sitios.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class UsuarioTicketsLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketsLogic.class.getName());

    @Inject
    private TicketPersistence ticketPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Asocia un ticket existente a un Usuario
     *
     * @param usersId Identificador de la instancia de Usuario
     * @param ticketsId Identificador de la instancia de Ticket
     * @return Instancia de TicketEntity que fue asociada a Usuario
     */
    public TicketEntity addTicket(Long usersId, Long ticketsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un ticket al usuario con id = {0}", usersId);
        UsuarioEntity ue = usuarioPersistence.find(usersId);
        TicketEntity te = ticketPersistence.find(ticketsId);
        ue.getTickets().add(te);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un ticket al usuario con id = {0}", usersId);
        return te;
    }

    /**
     * Obtiene una colección de instancias de TicketEntity asociadas a una
     * instancia de Usuario
     *
     * @param usersId Identificador de la instancia de Usuario
     * @return Colección de instancias de TicketEntity asociadas a la instancia
     * de Usuario
     */
    public List<TicketEntity> getTickets(Long usersId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los tickets del usuario con id = {0}", usersId);
        return usuarioPersistence.find(usersId).getTickets();
    }

    /**
     * Obtiene una instancia de TicketEntity asociada a una instancia de Usuario
     *
     * @param usersId Identificador de la instancia de Usuario
     * @param ticketsId Identificador de la instancia de Ticket
     * @return La entidadd de Ticket del usuario
     * @throws BusinessLogicException Si el ticket no está asociado al usuario
     */
    public TicketEntity getTicket(Long usersId, Long ticketsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ticket con id = {0} del usuario con id = " + usersId, ticketsId);
        UsuarioEntity ue = usuarioPersistence.find(usersId);
        TicketEntity te = ticketPersistence.find(ticketsId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ticket con id = {0} del usuario con id = " + usersId, ticketsId);
        if (!ue.getTickets().contains(te)) {
            throw new BusinessLogicException("El ticket " + te.getId() + " no esta asociado al usuario" + ue.getId());
        }
        return te;
    }

}
