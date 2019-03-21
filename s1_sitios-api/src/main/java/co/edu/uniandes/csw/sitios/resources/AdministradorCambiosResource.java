/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.CambioDTO;
import co.edu.uniandes.csw.sitios.dtos.NotificacionDTO;
import co.edu.uniandes.csw.sitios.ejb.AdministradorCambiosLogic;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import java.util.List;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Path("admins/{adminsId: \\d+}/changes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorCambiosResource {
    private static final Logger LOGGER = Logger.getLogger(AdministradorCambiosResource.class.getName());

    @Inject
    private CambioLogic cambioLogic;
    
    @Inject
    private AdministradorCambiosLogic adminCambiosLogic;
    
    /**
     * Asocia una cambios existente con un administrador existente
     *
     * @param adminsId El ID del admin al cual se le va a asociar la
     * cambios
     * @param cambiosId El ID de el cambio que se asocia
     * @return JSON {@link CambioDetailDTO} - el cambio asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @POST
    @Path("{cambioId: \\d+}")
    public CambioDTO addCambio(@PathParam("adminsId") Long adminsId, @PathParam("cambiosId") Long cambiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorCambiosResource addCambio: input: adminsId {0} , cambiosId {1}", new Object[]{adminsId, cambiosId});
        if (cambioLogic.getCambio(cambiosId) == null) {
            throw new WebApplicationException("El recurso /changes/" + cambiosId + " no existe.", 404);
        }
        CambioDTO detailDTO = new CambioDTO(adminCambiosLogic.addCambio(adminsId, cambiosId));
        LOGGER.log(Level.INFO, "AdministradorCambiosResource addCambio: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todos los cambios que existen en un admin.
     *
     * @param adminsId El ID del admin del cual se buscan los cambios
     * @return JSONArray {@link CambioDTO} - Los cambios encontrados en el
     * admin. Si no hay ninguno retorna una lista vacía.
     */
   @GET
    public List<CambioDTO> getCambios(@PathParam("adminsId") Long adminsId) {
        LOGGER.log(Level.INFO, "AdministradorCambiosResource getCambios: input: {0}", adminsId);
        List<CambioEntity> list = adminCambiosLogic.getCambios(adminsId);
        List<CambioDTO> cambioDTOList = new ArrayList<>();
        for (CambioEntity te : list) {
            cambioDTOList.add(new CambioDTO(te));
        }
        LOGGER.log(Level.INFO, "AdministradorCambiosResource getCambios: output: {0}", cambioDTOList);
        return cambioDTOList;
    }
    
    /**
     * Busca y el cambio con el ID recibido en la URL, relativo a un
     * admin.
     *
     * @param adminsId El ID del admin del cual se busca el cambio
     * @param cambiosId El ID del notificacion que se busca
     * @return {@link CambioDTO} - el cambio encontrado en el admin.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el admin.
     */
    @GET
    @Path("{changesId: \\d+}")
    public CambioDTO getCambio(@PathParam("adminsId") Long adminsId, @PathParam("changesId") Long cambiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorCambioResource getCambio: input: adminsId {0} , cambiosId {1}", new Object[]{adminsId, cambiosId});
        if (cambioLogic.getCambio(cambiosId) == null) {
            throw new WebApplicationException("El recurso /changes/" + cambiosId + " no existe.", 404);
        }
        CambioDTO detailDTO = new CambioDTO(adminCambiosLogic.getCambio(adminsId, cambiosId));
        LOGGER.log(Level.INFO, "AdministradorCambioResource getCambio: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Actualiza la lista de cambios de un admin con la lista que se recibe en el
     * cuerpo
     *
     * @param adminId El ID del admin al cual se le va a asociar el cambio
     * @param cambios JSONArray {@link CambioDTO} - La lista de cambios que se
     * desea guardar.
     * @return JSONArray {@link CambioDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cambio.
     */
    @PUT
    public List<CambioDTO> replaceCambios(@PathParam("adminId") Long adminId, List<CambioDTO> cambios) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorCambiosResource replaceCambios: input: adminId {0} , notis{1}", new Object[]{adminId, cambios});
        List<CambioEntity> entities = new ArrayList<>();
        for (CambioDTO cambio : cambios) {
            if (cambioLogic.getCambio(cambio.toEntity().getId()) == null) {
                throw new WebApplicationException("El recurso /changes/" + cambio.toEntity().getId() + " no existe.", 404);
            }
            entities.add(cambio.toEntity());
        }

        List<CambioEntity> ne = adminCambiosLogic.replaceCambios(adminId, entities);
        List<CambioDTO> lista = cambios;
        for (int i = 0; i < ne.size(); i++) {
            CambioEntity entity = ne.get(i);
            lista.add(new CambioDTO(entity));
        }
        LOGGER.log(Level.INFO, "AdministradorCambiosResource replaceCambios: output: {0}", lista);
        return lista;
    }
    
    /**
     * Elimina la conexión entre el cambio y el admin recibidos en la URL.
     *
     * @param adminId El ID del admin al cual se le va a desasociar el cambio
     * @param changesId El ID de la noti que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cambio.
     */
    @DELETE
    @Path("{changesId: \\d+}")
    public void removeCambio(@PathParam("adminId") Long adminId, @PathParam("changesId") Long changesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorNotificacionesResource removeCambio: input: adminId {0} , notiId {1}", new Object[]{adminId, changesId});
        if (cambioLogic.getCambio(changesId) == null) {
            throw new WebApplicationException("El recurso /changes/" + changesId + " no existe.", 404);
        }
        adminCambiosLogic.removeCambio(adminId, changesId);
        LOGGER.info("AdministradorNotificacionesResource removeCambio: output: void");
    }
}