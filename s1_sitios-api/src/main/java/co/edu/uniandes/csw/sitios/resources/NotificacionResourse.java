/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.NotificacionDTO;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("notifications")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class NotificacionResourse {
      private static final Logger LOGGER = Logger.getLogger(NotificacionResourse.class.getName());
      
    
     /**
     * Crea una noticiacion con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param notification {@link NotificacionDTO} - la notificacion que se desea
     * guardar.
     * @return JSON {@link NotificacionDTO} - el sitio Web guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la notificacion.
     */
    @POST
    public NotificacionDTO createWebSite(NotificacionDTO notification)throws BusinessLogicException {
    
        LOGGER.log(Level.INFO, "SitioWebResource createWebsite: input: {0}", notification.toString());
        NotificacionDTO newNotification = null;
        return notification;
    }
    
     /**
     * Permite dar una notificacion segun su Id.
     * @param id : Numero entero, id con
     * el cual se identifica una notificacion
     * @return NotificacionDTO. 
     */
    @GET
        @Path("{id: \\d+}")
    public NotificacionDTO getNotificacion( @PathParam("id") int id ){
        return null;
    }
    
    /**
     * Permite obtener todas las notificaciones del sistema
     * @return lista con todas las notificaciones presentes en el sistema
     */
    @GET
    public List<NotificacionDTO> getNotificacion()
    {
        return null;
    }
    
}
