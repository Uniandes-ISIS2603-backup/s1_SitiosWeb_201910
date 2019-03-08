/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de SitioWeb y Tickets.
 * 
 * @author Daniel preciado
 */
@Stateless
public class SitioWebTicketsLogic {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebTicketsLogic.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________

    /**
     * variable empleada para acceder a la persistencia de un Ticket, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private TicketPersistence ticketPersistence;

    /**
     * variable empleada para acceder a la persistencia de un sitioWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebPersistence sitioWebPersistence;
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Agrega un ticket a el sitioWeb
     *
     * @param ticketId El id ticket a guardar
     * @param sitioWebId El id de el sitioWeb en el cual se va a guardar el
     * ticket.
     * @return El ticket creado.
     */
    public TicketEntity addTicket(Long ticketId, Long sitioWebId) 
    {
        
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un ticket a el sitioWeb con id = {0}", sitioWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        TicketEntity ticketEntity = ticketPersistence.find(ticketId);
        ticketEntity.setSitioAsociado(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un ticket a el sitioWeb con id = {0}", sitioWebId);
        return ticketEntity;
    }

    /**
     * Retorna todos los estadosWeb asociados a un sitioWeb
     *
     * @param sitioWebId El ID de el sitioWeb buscado
     * @return La lista de estadosWeb de el sitioWeb
     */
    public List<TicketEntity> getTickets(Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los estadosWeb asociados a el sitioWeb con id = {0}", sitioWebId);
        return sitioWebPersistence.find(sitioWebId).getTicketsSitio();
    }

    /**
     * Retorna un ticket asociado a un sitioWeb
     *
     * @param sitioWebId El id de la sitioWeb a buscar.
     * @param ticketId El id del ticket a buscar
     * @return El ticket encontrado dentro de el sitioWeb.
     * @throws BusinessLogicException Si el ticket no se encuentra en la
     * sitioWeb
     */
    public TicketEntity getTicket(Long sitioWebId, Long ticketId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ticket con id = {0} de la sitioWeb con id = " + sitioWebId, ticketId);
        List<TicketEntity> estadosWeb = sitioWebPersistence.find(sitioWebId).getTicketsSitio();
        TicketEntity ticketEntity = ticketPersistence.find(ticketId);
        int index = estadosWeb.indexOf(ticketEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ticket con id = {0} de la sitioWeb con id = " + sitioWebId, ticketId);
        if (index >= 0) 
        {
            return estadosWeb.get(index);
        }
        throw new BusinessLogicException("El ticket no está asociado a la sitioWeb");
    }

    /**
     * Remplaza los estadosWeb de un sitioWeb
     *
     * @param estadosWeb Lista de estadosWeb que serán los nuevos de el sitioWeb.
     * @param sitioWebId El id de la sitioWeb que se quiere actualizar.
     * @return La lista de estadosWeb actualizada.
     */
    public List<TicketEntity> replaceTickets(Long sitioWebId, List<TicketEntity> estadosWeb) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el sitioWeb con id = {0}", sitioWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        List<TicketEntity> ticketList = ticketPersistence.findAll();
        for (TicketEntity ticket : ticketList) 
        {
            if (estadosWeb.contains(ticket)) 
            {
                ticket.setSitioAsociado(sitioWebEntity);
            } 
            else if (ticket.getSitioAsociado()!= null && ticket.getSitioAsociado().equals(sitioWebEntity)) 
            {
                ticket.setSitioAsociado(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el sitioWeb con id = {0}", sitioWebId);
        return estadosWeb;
    }
    
}