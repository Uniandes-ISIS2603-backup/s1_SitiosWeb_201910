/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.SitioWebDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.PlataformaDeDespliegueSitiosWebLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * @author s.ballesteros
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlataformaDeDespliegueSitiosWebResource {

    private static final Logger LOGGER = Logger.getLogger(PlataformaDeDespliegueSitiosWebResource.class.getName());

    @Inject
    private PlataformaDeDespliegueSitiosWebLogic plataformaDeDespliegueSitiosWebLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private SitioWebLogic sitiosWebLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una plataforma con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la plataforma.
     *
     * @param plataformasId Identificador de la plataforma que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param sitiosId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link SitioWebDTO} - El libro guardado en la plataforma.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{sitiosId: \\d+}")
    public SitioWebDTO addSitioWeb(@PathParam("plataformasId") Long plataformasId, @PathParam("sitiosId") Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource addSitioWeb: input: plataformasID: {0} , sitiosId: {1}", new Object[]{plataformasId, sitiosId});
        if (sitiosWebLogic.getWebSite(sitiosId) == null) {
            throw new WebApplicationException("El recurso /websites/" + sitiosId + " no existe.", 404);
        }
        SitioWebDTO sitiosWebDTO = new SitioWebDTO(plataformaDeDespliegueSitiosWebLogic.addSitioWeb(sitiosId, plataformasId));
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource addSitioWeb: output: {0}", sitiosWebDTO);
        return sitiosWebDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la plataforma.
     *
     * @param plataformasId Identificador de la plataforma que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link SitioWebDetailDTO} - Los libros encontrados en la
     * plataforma. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SitioWebDetailDTO> getSitiosWeb(@PathParam("plataformasId") Long plataformasId) {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource getSitiosWeb: input: {0}", plataformasId);
        List<SitioWebDetailDTO> listaDetailDTOs = sitiosWebListEntity2DTO(plataformaDeDespliegueSitiosWebLogic.getSitiosWeb(plataformasId));
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource getSitiosWeb: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la plataforma con id asociado.
     *
     * @param plataformasId Identificador de la plataforma que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param sitiosId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link SitioWebDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * plataforma.
     */
    @GET
    @Path("{sitiosId: \\d+}")
    public SitioWebDetailDTO getSitioWeb(@PathParam("plataformasId") Long plataformasId, @PathParam("sitiosId") Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource getSitioWeb: input: plataformasID: {0} , sitiosId: {1}", new Object[]{plataformasId, sitiosId});
        if (sitiosWebLogic.getWebSite(sitiosId) == null) {
            throw new WebApplicationException("El recurso /platforms/" + plataformasId + "/websites/" + sitiosId + " no existe.", 404);
        }
        SitioWebDetailDTO sitiosWebDetailDTO = new SitioWebDetailDTO(plataformaDeDespliegueSitiosWebLogic.getSitioWeb(plataformasId, sitiosId));
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource getSitioWeb: output: {0}", sitiosWebDetailDTO);
        return sitiosWebDetailDTO;
    }

    /**
     * Remplaza las instancias de SitioWeb asociadas a una instancia de Editorial
     *
     * @param plataformasId Identificador de la plataforma que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param sitiosWeb JSONArray {@link SitioWebDTO} El arreglo de libros nuevo para la
     * plataforma.
     * @return JSON {@link SitioWebDTO} - El arreglo de libros guardado en la
     * plataforma.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<SitioWebDetailDTO> replaceSitioWeb(@PathParam("plataformasId") Long plataformasId, List<SitioWebDetailDTO> sitiosWeb) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource replaceSitioWeb: input: plataformasId: {0} , sitiosWeb: {1}", new Object[]{plataformasId, sitiosWeb});
        for (SitioWebDetailDTO sitioWeb : sitiosWeb) {
            if (sitiosWebLogic.getWebSite(sitioWeb.getId()) == null) {
                throw new WebApplicationException("El recurso /websites/" + sitioWeb.getId() + " no existe.", 404);
            }
        }
        List<SitioWebDetailDTO> listaDetailDTOs = sitiosWebListEntity2DTO(plataformaDeDespliegueSitiosWebLogic.replaceSitiosWeb(plataformasId, sitiosWebListDTO2Entity(sitiosWeb)));
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueSitiosWebResource replaceSitioWeb: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de SitioWebEntity a una lista de SitioWebDetailDTO.
     *
     * @param entityList Lista de SitioWebEntity a convertir.
     * @return Lista de SitioWebDTO convertida.
     */
    private List<SitioWebDetailDTO> sitiosWebListEntity2DTO(List<SitioWebEntity> entityList) {
        List<SitioWebDetailDTO> list = new ArrayList();
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
