/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.SitioWebDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.AdministradorSitiosWebLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdministradorSitiosWebResource {

    private static final Logger LOGGER = Logger.getLogger(AdministradorSitiosWebResource.class.getName());

    @Inject
    private AdministradorSitiosWebLogic administradorSitiosWebLogic;

    @Inject
    private SitioWebLogic sitioWebLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un sitio web existente con un administrador existente
     *
     * @param adminsId El ID del admin al cual se le va a asociar el libro
     * @param websitesId El ID del sitio web que se asocia
     * @return JSON {@link SitioWebDetailDTO} - El sitio web asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @POST
    @Path("{websitesId: \\d+}")
    public SitioWebDetailDTO addSitioWeb(@PathParam("adminsId") Long adminsId, @PathParam("websitesId") Long websitesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource addSitioWeb: input: adminsId {0} , booksId {1}", new Object[]{adminsId, websitesId});
        if (sitioWebLogic.getWebSite(websitesId) == null) {
            throw new WebApplicationException("El recurso /books/" + websitesId + " no existe.", 404);
        }
        SitioWebDetailDTO detailDTO = new SitioWebDetailDTO(administradorSitiosWebLogic.addSitioWeb(adminsId, websitesId));
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource addSitioWeb: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los sitios web que existen en un admin.
     *
     * @param adminsId El ID del admin del cual se buscan los sitios web
     * @return JSONArray {@link BookDetailDTO} - Los sitios web encontrados en
     * el admin. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SitioWebDetailDTO> getSitiosWeb(@PathParam("adminsId") Long adminsId) {
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource getSitiosWeb: input: {0}", adminsId);
        List<SitioWebDetailDTO> lista = sitiosWebListEntity2DTO(administradorSitiosWebLogic.getSitiosWeb(adminsId));
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource getSitiosWeb: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el sitio web con el ID recibido en la URL, relativo a un
     * admin.
     *
     * @param adminsId El ID del admin del cual se busca el sitio web
     * @param websitesId El ID del sitio web que se busca
     * @return {@link SitioWebDetailDTO} - El sitio web encontrado en el admin.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el sitio web no está asociado al admin
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @GET
    @Path("{websitesId: \\d+}")
    public SitioWebDTO getSitioWeb(@PathParam("adminsId") Long adminsId, @PathParam("websitesId") Long websitesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource getSitioWeb: input: adminsId {0} , websitesId {1}", new Object[]{adminsId, websitesId});
        if (sitioWebLogic.getWebSite(websitesId) == null) {
            throw new WebApplicationException("El recurso /websites/" + websitesId + " no existe.", 404);
        }
        SitioWebDetailDTO detailDTO = new SitioWebDetailDTO(administradorSitiosWebLogic.getSitioWeb(adminsId, websitesId));
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource getSitioWeb: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de sitios web de un admin con la lista que se recibe
     * en el cuerpo
     *
     * @param adminsId El ID del autor al cual se le va a asociar el libro
     * @param websites JSONArray {@link SitioWebDetailDTO} - La lista de sitios
     * web que se desea guardar.
     * @return JSONArray {@link SitioWebDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @PUT
    public List<SitioWebDetailDTO> replaceSitiosWeb(@PathParam("adminsId") Long adminsId, List<SitioWebDetailDTO> sitiosWeb) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource replaceSitiosWeb: input: adminsId {0} , sitiosWeb {1}", new Object[]{adminsId, sitiosWeb});
        for (SitioWebDetailDTO sitio : sitiosWeb) {
            if (sitioWebLogic.getWebSite(sitio.getId()) == null) {
                throw new WebApplicationException("El recurso /books/" + sitio.getId() + " no existe.", 404);
            }
        }
        List<SitioWebDetailDTO> lista = sitiosWebListEntity2DTO(administradorSitiosWebLogic.replaceSitiosWeb(adminsId, sitiosWebListDTO2Entity(sitiosWeb)));
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource replaceSitiosWeb: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el sitio web y el admin recibidos en la URL.
     *
     * @param adminsId El ID del admin al cual se le va a desasociar el sitio
     * web
     * @param websitesId El ID del sitio web que se desasocia
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @DELETE
    @Path("{websitesId: \\d+}")
    public void removeBook(@PathParam("adminsId") Long adminsId, @PathParam("websitesId") Long websitesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorSitiosWebResource deleteSitioWeb: input: adminsId {0} , websitesId {1}", new Object[]{adminsId, websitesId});
        if (sitioWebLogic.getWebSite(websitesId) == null) {
            throw new WebApplicationException("El recurso /websites/" + websitesId + " no existe.", 404);
        }
        administradorSitiosWebLogic.removeSitioWeb(adminsId, websitesId);
        LOGGER.info("AdministradorSitiosWebResource deleteSitioWeb: output: void");
    }

    //----------------------------------------------------
    // Metodos utiles ------------------------------------
    //----------------------------------------------------
    /**
     * Convierte una lista de SitioWebEntity a una lista de SitioWebDetailDTO.
     *
     * @param entityList Lista de SitioWebEntity a convertir.
     * @return Lista de SitioWebDetailDTO convertida.
     */
    private List<SitioWebDetailDTO> sitiosWebListEntity2DTO(List<SitioWebEntity> entityList) {
        List<SitioWebDetailDTO> list = new ArrayList<>();
        for (SitioWebEntity entity : entityList) {
            list.add(new SitioWebDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de SitioWebDetailDTO a una lista de SitioWebEntity.
     *
     * @param dtos Lista de SitioWebDetailDTO a convertir.
     * @return Lista de SitioWebEntity convertida.
     */
    private List<SitioWebEntity> sitiosWebListDTO2Entity(List<SitioWebDetailDTO> dtos) {
        List<SitioWebEntity> list = new ArrayList<>();
        for (SitioWebDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
