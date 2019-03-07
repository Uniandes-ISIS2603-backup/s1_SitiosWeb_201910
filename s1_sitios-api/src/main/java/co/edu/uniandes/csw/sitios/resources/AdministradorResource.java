/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.AdministradorDTO;
import co.edu.uniandes.csw.sitios.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.AdministradorLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
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
 * Recurso administrador
 *
 * @author Allan Roy Corinaldi.
 */
@Path("/admins")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {

    public final static Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

    @Inject
    private AdministradorLogic administradorLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param administrador {@link AdministradorDTO} - EL Administrador que se
     * desea guardar.
     * @return JSON {@link AdministradorDTO} - El Administrador guardado con el
     * atributo id autogenerado.
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administrador);
        AdministradorDTO administradorDTO = new AdministradorDTO(administradorLogic.createAdministrador(administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", administrador);
        return administradorDTO;
    }

    /**
     * Busca y devuelve todos los Administradores que existen en la aplicacion.
     *
     * @return JSONArray {@link AdministradorDetailDTO} - Los Administradores
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AdministradorDetailDTO> getAdministradores() {
        LOGGER.info("AdministradorResource getAdministradores: input: void");
        List<AdministradorDetailDTO> listaAdministradores = listEntity2DTO(administradorLogic.getAdministradores());
        LOGGER.log(Level.INFO, "AdministradorResource getAdministradores: output: {0}", listaAdministradores);
        return listaAdministradores;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param adminsId Identificador del Administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link AdministradorDetailDTO} - El Administrador buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Administrador.
     */
    @GET
    @Path("{adminsId: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("adminsId") Long adminsId) {
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: input: {0}", adminsId);
        AdministradorEntity administradorEntity = administradorLogic.getAdministrador(adminsId);
        if (administradorEntity == null) {
            throw new WebApplicationException("El recurso /authors/" + adminsId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param adminsId Identificador del Administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param administrador {@link AdministradorDetailDTO} El Administrador que
     * se desea guardar.
     * @return JSON {@link AdministradorDetailDTO} - El Administrador guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Administrador a
     * actualizar.
     */
    @PUT
    @Path("{adminsId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("adminsId") Long adminsId, AdministradorDetailDTO administrador) {
        LOGGER.log(Level.INFO, "adminsIdResource updateAdministrador: input: adminsId: {0} , administrador: {1}", new Object[]{adminsId, administrador});
        administrador.setId(adminsId);
        if (administradorLogic.getAdministrador(adminsId) == null) {
            throw new WebApplicationException("El recurso /admins/" + adminsId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorLogic.updateAdministrador(adminsId, administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el Administrador con el id asociado recibido en la URL.
     *
     * @param adminsId Identificador del Administrador que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el Administrador tiene notificaciones asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{adminsId: \\d+}")
    public void deleteAdministrador(@PathParam("adminsId") Long adminsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource deleteAdministrador: input: {0}", adminsId);
        if (administradorLogic.getAdministrador(adminsId) == null) {
            throw new WebApplicationException("El recurso /admins/" + adminsId + " no existe.", 404);
        }
        administradorLogic.deleteAdministrador(adminsId);
        LOGGER.info("AdministradorResource deleteAdministrador: output: void");
    }

    /**
     * Conexión con el servicio de notificaciones para un admin.
     * {@link AdministradorNotificacionesResource}
     *
     * Este método conecta la ruta de /admins con las rutas de /notifications que
     * dependen del admin, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las notificaciones.
     *
     * @param adminId El ID del admin con respecto al cual se accede al
     * servicio.
     * @return El servicio de Notificacion para ese admin en paricular.
     */
    @Path("{adminId: \\d+}/notifications")
    public Class<AdministradorNotificacionesResource> getAdministradorNotificacionesResource(@PathParam("adminId") Long adminId) {
        if (administradorLogic.getAdministrador(adminId) == null) {
            throw new WebApplicationException("El recurso /admins/" + adminId + " no existe.", 404);
        }
        return AdministradorNotificacionesResource.class;
    }
    
    /**
     * Conexión con el servicio de cambios para un admin.
     * {@link AdministradorCambiosResource}
     *
     * Este método conecta la ruta de /admins con las rutas de /changes que
     * dependen del admin, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las cambios.
     *
     * @param adminId El ID del admin con respecto al cual se accede al
     * servicio.
     * @return El servicio de cambio para ese admin en paricular.
     */
    @Path("{adminId: \\d+}/changes")
    public Class<AdministradorCambiosResource> getAdministradorCambiosResource(@PathParam("adminId") Long adminId) {
        if (administradorLogic.getAdministrador(adminId) == null) {
            throw new WebApplicationException("El recurso /admins/" + adminId + " no existe.", 404);
        }
        return AdministradorCambiosResource.class;
    }
    
    /**
     * Convierte una lista de AdministradorEntity a una lista de
     * AdministradorDetailDTO.
     *
     * @param entityList Lista de AdministradorEntity a convertir.
     * @return Lista de AdministradorDetailDTO convertida.
     */
    private List<AdministradorDetailDTO> listEntity2DTO(List<AdministradorEntity> entityList) {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        for (AdministradorEntity entity : entityList) {
            list.add(new AdministradorDetailDTO(entity));
        }
        return list;
    }
}
