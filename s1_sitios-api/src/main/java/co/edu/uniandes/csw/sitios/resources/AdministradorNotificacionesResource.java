/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.NotificacionDTO;
import co.edu.uniandes.csw.sitios.ejb.AdministradorNotificacionesLogic;
import co.edu.uniandes.csw.sitios.ejb.NotificacionLogic;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Path("admins/{adminsId: \\d+}/notification")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorNotificacionesResource {

    private static final Logger LOGGER = Logger.getLogger(AdministradorSitiosWebResource.class.getName());

    @Inject
    private AdministradorNotificacionesLogic administradorNotificacionesLogic;

    @Inject
    private NotificacionLogic notificacionLogic;

    /**
     * Asocia una notificacion existente con un administrador existente
     *
     * @param adminsId El ID del admin al cual se le va a asociar la
     * notificacion
     * @param notificationsId El ID de la notificacionque se asocia
     * @return JSON {@link NotificacionbDetailDTO} - La notificacion asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @POST
    @Path("{notificationsId: \\d+}")
    public NotificacionDTO addNotificacion(@PathParam("adminsId") Long adminsId, @PathParam("notificationsId") Long notificationsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource addNotificacion: input: adminsId {0} , notificationsId {1}", new Object[]{adminsId, notificationsId});
        if (notificacionLogic.getNotificacion(notificationsId) == null) {
            throw new WebApplicationException("El recurso /books/" + notificationsId + " no existe.", 404);
        }
        NotificacionDTO detailDTO = new NotificacionDTO(administradorNotificacionesLogic.addNotificacion(adminsId, notificationsId));
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource addNotificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos las notificaciones que existen en un admin.
     *
     * @param adminsId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link NotificacionDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<NotificacionDTO> getNotificaciones(@PathParam("adminsId") Long adminsId) {
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource getNotificaciones: input: {0}", adminsId);
        List<NotificacionEntity> list = administradorNotificacionesLogic.getNotificaciones(adminsId);
        List<NotificacionDTO> notificacionDTOList = new ArrayList<>();
        for (NotificacionEntity te : list) {
            notificacionDTOList.add(new NotificacionDTO(te));
        }
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource getNotificaciones: output: {0}", notificacionDTOList);
        return notificacionDTOList;
    }

    /**
     * Busca y devuelve el ticket con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param adminsId El ID del admin del cual se busca la notificacion
     * @param notificationsId El ID del notificacion que se busca
     * @return {@link NotificacionDTO} - El notificacion encontrado en el admin.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el admin.
     */
    @GET
    @Path("{notificationsId: \\d+}")
    public NotificacionDTO getNotificacion(@PathParam("adminsId") Long adminsId, @PathParam("notificationsId") Long notificationsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorNotificacionResource getNotificacion: input: adminsId {0} , notificationsId {1}", new Object[]{adminsId, notificationsId});
        if (notificacionLogic.getNotificacion(notificationsId) == null) {
            throw new WebApplicationException("El recurso /notifications/" + notificationsId + " no existe.", 404);
        }
        NotificacionDTO detailDTO = new NotificacionDTO(administradorNotificacionesLogic.getNotificacion(adminsId, notificationsId));
        LOGGER.log(Level.INFO, "AdministradorNotificacionResource getNotificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de notificaciones de un admin con la lista que se recibe en el
     * cuerpo
     *
     * @param adminId El ID del admin al cual se le va a asociar el notificacion
     * @param notis JSONArray {@link NotificacionDTO} - La lista de Notificaciones que se
     * desea guardar.
     * @return JSONArray {@link NotificacionDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el noti.
     */
    @PUT
    public List<NotificacionDTO> replaceNotificaciones(@PathParam("adminId") Long adminId, List<NotificacionDTO> notis) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource replaceNotificaciones: input: adminId {0} , notis{1}", new Object[]{adminId, notis});
        List<NotificacionEntity> entities = new ArrayList<>();
        for (NotificacionDTO noti : notis) {
            if (notificacionLogic.getNotificacion(noti.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + noti.getId() + " no existe.", 404);
            }
            entities.add(noti.toEntity());
        }

        List<NotificacionEntity> ne = administradorNotificacionesLogic.replaceNotificaciones(adminId, entities);
        List<NotificacionDTO> lista = notis;
        for (int i = 0; i < ne.size(); i++) {
            NotificacionEntity entity = ne.get(i);
            lista.add(new NotificacionDTO(entity));
        }
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource replaceNotificaciones: output: {0}", lista);
        return lista;
    }
    
    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param adminId El ID del autor al cual se le va a desasociar el libro
     * @param notiId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{notificationId: \\d+}")
    public void removeNotificacion(@PathParam("adminId") Long adminId, @PathParam("notificationId") Long notiId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource deleteNotificacion: input: adminId {0} , notiId {1}", new Object[]{adminId, notiId});
        if (notificacionLogic.getNotificacion(notiId) == null) {
            throw new WebApplicationException("El recurso /notifications/" + notiId + " no existe.", 404);
        }
        administradorNotificacionesLogic.removeNotificacion(adminId, notiId);
        LOGGER.info("AdministradorNotificacionesResource deleteNotificacion: output: void");
    }

}
