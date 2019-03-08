/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Esta clase es la que implementa la conexion con la persistencia de la
 * entidad de Ticket.
 * 
 * @author Daniel Preciado
 */
@Stateless
public class TicketLogic {
    
     //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(EstadoWebLogic.class.getName());
    
     
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * Variable empleada para acceder a la persistencia de la aplicación (DB), 
     * esto se hace mediante el uso de inyeccion de dependencias.
     */
     @Inject
    private TicketPersistence DB; 
     
     
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
         
     /**
     * Crea un ticket en la DB.
     *
     * @param pTicket un entity que representa el objeto a persistir.
     * @return un entity de el ticket luego de persistirlo.
     * @throws BusinessLogicException Si el ticket a persistir esta incompleto.
     */
    public TicketEntity createTicket(TicketEntity pTicket) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de un ticket");

        if (pTicket.getDescripcion().equals("")) 
        {
            throw new BusinessLogicException("no puede crear un ticket sin descripcion \"" );
        }
        if (pTicket.getEstado() != 1 | pTicket.getEstado() != 2  | pTicket.getEstado() != 3) 
        {
            throw new BusinessLogicException("no puede crear un ticket sin un tipo de estado valido o vacio \"" );
        }
        if (pTicket.getFecha() != null) 
        {
            throw new BusinessLogicException("no puede crear un ticket sin fecha \"" );
        }
        // Invoca la DB para crear un ticket
        DB.create(pTicket);
        LOGGER.log(Level.INFO, "Termina proceso de creación de un ticket");
        return pTicket;
    }

    /**
     * Obtener todos los tickets almacenados en la DB
     *
     * @return una lista de tickets.
     */
    public List<TicketEntity> getTickets() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los tickets");
        List<TicketEntity> estadosWeb = DB.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los tickets en la DB");
        return estadosWeb;
    }

    /**
     * Obtiene un ticket mediante´su id.
     *
     * @param ticketId: id del ticket buscado.
     * @return el ticket buscado mediante su id.
     */
    public TicketEntity getTicket(Long ticketId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un ticket con id = {0}", ticketId);
        TicketEntity ticketEntity = DB.find(ticketId);
        if (ticketEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "el ticket con el id = {0} no existe", ticketId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un ticket con id = {0}", ticketId);
        return ticketEntity;
    }

    /**
     * Actualizar un ticket.
     *
     * @param ticketId: id de un ticket a buscar en la DB
     * @param pTicket: ticket con los cambios para ser actualizada en la DB
     * @return un ticket con los cambios actualizados en la base de datos.
     */
    public TicketEntity updateTicket(Long ticketId, TicketEntity pTicket) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un ticket con id = {0}", ticketId);
        TicketEntity updateEntity = DB.update(pTicket);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar un ticket con id = {0}", pTicket.getId());
        return updateEntity;
    }

    /**
     * Borrar un ticket de la DB.
     *
     * @param ticketId: id del ticket a eliminar
     */
    public void deleteTicket(Long ticketId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un ticket con id = {0}", ticketId);
        DB.delete(ticketId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un ticket con id = {0}", ticketId);
    }
    
}
