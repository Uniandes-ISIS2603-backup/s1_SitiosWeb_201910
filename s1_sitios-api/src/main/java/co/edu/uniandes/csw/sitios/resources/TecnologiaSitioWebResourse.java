/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.SitioWebDetailDTO;
import co.edu.uniandes.csw.sitios.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaSitioLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
@Path("technologies/{siteID: \\d+}/site")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TecnologiaSitioWebResourse {


    private static final Logger LOGGER = Logger.getLogger(TecnologiaSitioWebResourse.class.getName());

    @Inject
    private SitioWebLogic sitioWebLogic;

    @Inject
    private TecnologiaSitioLogic tecnologiaSitioLogic;
     /**
     * Asocia un sitio web existente con un tecnologia existente
     *
     * @param webSiteId El ID del sitio web que se va a Asociar
     * @param technologyId El ID del tecnologia al cual se le va a Asociar el sitio web
     * @return JSON {@link SitioWebDetailDTO} - El sitio web Asociado.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @POST
    @Path("{webSiteId: \\d+}")
    public SitioWebDetailDTO addWebSite(@PathParam("technologyId") Long technologyId, @PathParam("webSiteId") Long webSiteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource addWebSite: input: technologyId {0} , webSiteId {1}", new Object[]{technologyId, webSiteId});
        if (sitioWebLogic.getWebSite(webSiteId) == null) {
            throw new WebApplicationException("El recurso /websites/" + webSiteId + " no existe.", 404);
        }
        SitioWebDetailDTO detailDTO = new SitioWebDetailDTO(tecnologiaSitioLogic.addWebSite(technologyId, webSiteId));
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource addWebSite: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los sitio webs que existen en un tecnologia.
     *
     * @param technologyId El ID del tecnologia del cual se buscan los sitio webs
     * @return JSONArray {@link SitioWebDetailDTO} - Los sitio webs encontrados en el
     * tecnologia. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SitioWebDetailDTO> getWebSites(@PathParam("technologyId") Long technologyId) {
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource getWebSites: input: {0}", technologyId);
        List<SitioWebDetailDTO> lista = websitesListEntity2DTO(tecnologiaSitioLogic.getWebSites(technologyId));
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource getWebSites: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el sitio web con el ID recibido en la URL, relativo a un
     * tecnologia.
     *
     * @param webSiteId El ID del sitio web que se busca
     * @param technologyId El ID del tecnologia del cual se busca el sitio web
     * @return {@link SitioWebDetailDTO} - El sitio web encontrado en el tecnologia.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @GET
    @Path("{webSiteId: \\d+}")
    public SitioWebDetailDTO getWebSite(@PathParam("technologyId") Long technologyId, @PathParam("webSiteId") Long webSiteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource getWebSite: input: technologyId {0} , webSiteId {1}", new Object[]{technologyId, webSiteId});
        if (sitioWebLogic.getWebSite(webSiteId) == null) {
            throw new WebApplicationException("El recurso /websites/" + webSiteId + " no existe.", 404);
        }
        SitioWebDetailDTO detailDTO = new SitioWebDetailDTO(tecnologiaSitioLogic.getWebSite(technologyId, webSiteId));
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource getWebSite: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de sitio webs de un tecnologia con la lista que se recibe en
     * el cuerpo.
     *
     * @param technologyId El ID del tecnologia al cual se le va a Asociar la lista de
     * sitio webs
     * @param websites JSONArray {@link SitioWebDetailDTO} - La lista de sitio webs
     * que se desea guardar.
     * @return JSONArray {@link SitioWebDetailDTO} - La lista actualizada.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @PUT
    public List<SitioWebDetailDTO> replaceWebSites(@PathParam("technologyId") Long technologyId, List<SitioWebDetailDTO> websites) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource replaceWebSites: input: technologyId {0} , websites {1}", new Object[]{technologyId, websites});
        for (SitioWebDetailDTO website : websites) {
            if (sitioWebLogic.getWebSite(website.getId()) == null) {
                throw new WebApplicationException("El recurso /websites/" + website.getId() + " no existe.", 404);
            }
        }
        List<SitioWebDetailDTO> lista = websitesListEntity2DTO(tecnologiaSitioLogic.replaceWebSites(technologyId, websitesListDTO2Entity(websites)));
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource replaceWebSites: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el sitio web y el tecnologia recibidos en la URL.
     *
     * @param technologyId El ID del tecnologia al cual se le va a desAsociar el sitio web
     * @param webSiteId El ID del sitio web que se desAsocia
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el sitio web.
     */
    @DELETE
    @Path("{webSiteId: \\d+}")
    public void removeWebSite(@PathParam("technologyId") Long technologyId, @PathParam("webSiteId") Long webSiteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaSitioWebResource removeWebSite: input: technologyId {0} , webSiteId {1}", new Object[]{technologyId, webSiteId});
        if (sitioWebLogic.getWebSite(webSiteId) == null) {
            throw new WebApplicationException("El recurso /websites/" + webSiteId + " no existe.", 404);
        }
        tecnologiaSitioLogic.removeWebSite(technologyId, webSiteId);
        LOGGER.info("TecnologiaSitioWebResource removeWebSite: output: void");
    }

    /**
     * Convierte una lista de SitioWebEntity a una lista de SitioWebDetailDTO.
     *
     * @param entityList Lista de SitioWebEntity a convertir.
     * @return Lista de SitioWebDetailDTO convertida.
     */
    private List<SitioWebDetailDTO> websitesListEntity2DTO(List<SitioWebEntity> entityList) {
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
    private List<SitioWebEntity> websitesListDTO2Entity(List<SitioWebDetailDTO> dtos) {
        List<SitioWebEntity> list = new ArrayList<>();
        for (SitioWebDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
