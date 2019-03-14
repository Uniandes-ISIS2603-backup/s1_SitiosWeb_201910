/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.TicketPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad ticket y sitioWeb.
 * 
 * @author Daniel Preciado
 */
@Stateless
public class TicketSitioWebLogic {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(TicketSitioWebLogic.class.getName());
    
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

    
    /**
     * Remplazar el sitio de un ticket.
     *
     * @param ticketId id del ticket que se quiere actualizar.
     * @param sitioWebId El id de el sitioWeb al que pertenece el ticket.
     * @return el nuevo ticket.
     */
    public TicketEntity replaceSitioAsociado(Long ticketId, Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ticket con id = {0}", ticketId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        TicketEntity ticketEntity = ticketPersistence.find(ticketId);
        ticketEntity.setSitioAsociado(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ticket con id = {0}", ticketEntity.getId());
        return ticketEntity;
    }

    /**
     * Borrar un ticket de un sitioWeb. Este metodo se utiliza para borrar la
     * relacion de un ticket a sitio web.
     *
     * @param ticketId El ticket que se desea borrar de el sitioWeb.
     */
    public void removeSitioWeb(Long ticketId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el SitioWeb del ticket con id = {0}", ticketId);
        TicketEntity ticketEntity = ticketPersistence.find(ticketId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(ticketEntity.getSitioAsociado().getId());
        ticketEntity.setSitioAsociado(null);
        sitioWebEntity.getTicketsSitio().remove(ticketEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el SitioWeb del ticket con id = {0}", ticketEntity.getId());
    }
    
}
