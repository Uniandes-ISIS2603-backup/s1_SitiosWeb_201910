/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import co.edu.uniandes.csw.sitios.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class UsuarioTicketLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioTicketLogic.class.getName());

    @Inject
    private TicketPersistence ticketPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Asocia un Book existente a un Author
     *
     * @param authorsId Identificador de la instancia de Author
     * @param booksId Identificador de la instancia de Book
     * @return Instancia de BookEntity que fue asociada a Author
     */
    public TicketEntity addTicket(Long usersId, Long ticketsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un ticket al usuario con id = {0}", usersId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usersId);
        TicketEntity ticketEntity = ticketPersistence.find(ticketsId);
        //ticketEntity.getUsuarios().add(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un ticket al usuario con id = {0}", usersId);
        return ticketPersistence.find(ticketsId);
    }
}
