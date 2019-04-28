/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TecnologiaDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioTecnologiaLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitioWebTecnologiaResource {
    @Inject
    private SitioWebLogic sitioWebLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @Inject
    private SitioTecnologiaLogic sitioTecnologiaLogic;
    
    private static final Logger LOGGER = Logger.getLogger(SitioWebTecnologiaResource.class.getName());

    
   /**
     * Asocia un libro existente con un sitio web existente
     *
     * @param websiteId El ID del sitio web al cual se le va a asociar el libro
     * @param booksId El ID del libro que se asocia
     * @return JSON {@link TecnologiaDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
   /**
     * Asocia un libro existente con un sitio web existente
     *
     * @param websiteId El ID del sitio web al cual se le va a asociar el libro
     * @param technologysId El ID del libro que se asocia
     * @return JSON {@link TecnologiaDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{technologysId: \\d+}")
    public TecnologiaDetailDTO addTecnologia(@PathParam("websiteId") Long websiteId, @PathParam("technologysId") Long technologyId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource addTecnologia: input: websiteId {0} , technologyId {1}", new Object[]{websiteId, technologyId});
        if (tecnologiaLogic.getTechnology(technologyId) == null) {
            throw new WebApplicationException("El recurso /technologys/" + technologyId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(sitioTecnologiaLogic.addTechnology(websiteId, technologyId));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource addTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un sitio web.
     *
     * @param websiteId El ID del sitio web del cual se buscan los libros
     * @return JSONArray {@link TecnologiaDetailDTO} - Los libros encontrados en el
     * sitio web. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTecnologias(@PathParam("websiteId") Long websiteId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologias: input: {0}", websiteId);
        List<TecnologiaDetailDTO> lista = technologysListEntity2DTO(sitioTecnologiaLogic.getTechnologies(websiteId));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * sitio web.
     *
     * @param websiteId El ID del sitio web del cual se busca el libro
     * @param technologysId El ID del libro que se busca
     * @return {@link TecnologiaDetailDTO} - El libro encontrado en el sitio web.
     * @throws co.edu.uniandes.csw.technologystore.exceptions.BusinessLogicException
     * si el libro no está asociado al sitio web
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{technologysId: \\d+}")
    public TecnologiaDetailDTO getTecnologia(@PathParam("websiteId") Long websiteId, @PathParam("technologysId") Long technologysId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologia: input: websiteId {0} , technologysId {1}", new Object[]{websiteId, technologysId});
        if (tecnologiaLogic.getTechnology(technologysId) == null) {
            throw new WebApplicationException("El recurso /technologys/" + technologysId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(sitioTecnologiaLogic.getTechnology(websiteId, technologysId));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de libros de un sitio web con la lista que se recibe en el
     * cuerpo
     *
     * @param websiteId El ID del sitio web al cual se le va a asociar el libro
     * @param technologys JSONArray {@link TecnologiaDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link TecnologiaDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<TecnologiaDetailDTO> replaceTecnologias(@PathParam("websiteId") Long websiteId, List<TecnologiaDetailDTO> technologys) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource replaceTecnologias: input: websiteId {0} , technologys {1}", new Object[]{websiteId, technologys});
        for (TecnologiaDetailDTO technology : technologys) {
            if (tecnologiaLogic.getTechnology(technology.getId()) == null) {
                throw new WebApplicationException("El recurso /technologys/" + technology.getId() + " no existe.", 404);
            }
        }
        List<TecnologiaDetailDTO> lista = technologysListEntity2DTO(sitioTecnologiaLogic.replaceTechnologies(websiteId, technologysListDTO2Entity(technologys)));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource replaceTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e sitio web recibidos en la URL.
     *
     * @param websiteId El ID del sitio web al cual se le va a desasociar el libro
     * @param technologysId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{technologysId: \\d+}")
    public void removeTecnologia(@PathParam("websiteId") Long websiteId, @PathParam("technologysId") Long technologysId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource deleteTecnologia: input: websiteId {0} , technologysId {1}", new Object[]{websiteId, technologysId});
        if (tecnologiaLogic.getTechnology(technologysId) == null) {
            throw new WebApplicationException("El recurso /technologys/" + technologysId + " no existe.", 404);
        }
        sitioTecnologiaLogic.removeTechnology(websiteId, technologysId);
        LOGGER.info("SitioWebTecnologiasResource deleteTecnologia: output: void");
    }

    /**
     * Convierte una lista de TecnologiaEntity a una lista de TecnologiaDetailDTO.
     *
     * @param entityList Lista de TecnologiaEntity a convertir.
     * @return Lista de TecnologiaDetailDTO convertida.
     */
    private List<TecnologiaDetailDTO> technologysListEntity2DTO(List<TecnologiaEntity> entityList) {
        List<TecnologiaDetailDTO> list = new ArrayList<>();
        for (TecnologiaEntity entity : entityList) {
            list.add(new TecnologiaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TecnologiaDetailDTO a una lista de TecnologiaEntity.
     *
     * @param dtos Lista de TecnologiaDetailDTO a convertir.
     * @return Lista de TecnologiaEntity convertida.
     */
    private List<TecnologiaEntity> technologysListDTO2Entity(List<TecnologiaDetailDTO> dtos) {
        List<TecnologiaEntity> list = new ArrayList<>();
        for (TecnologiaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
