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
@Path("sitiosWeb/{sitiosWebId: \\d+}/tecnologias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class SitioWebTecnologiaResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebTecnologiaResource.class.getName());
      
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable usada para acceder a la logica de un sitioWeb
     */
    @Inject
    private SitioWebLogic sitioWebLogic;

    /**
     * variable para acceder a la logica de una tecnologia
     */
    @Inject
    private TecnologiaLogic tecnologiaLogic;

    /**
     * variable para acceder a la logica de la relacion entre sitio y tecnologia
     */
    @Inject
    private SitioTecnologiaLogic sitioTecnologiaLogic;

    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
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
     * @param technologysId El ID de la tecnologia que se asocia
     * @return JSON {@link TecnologiaDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{technologysId: \\d+}")
    public TecnologiaDetailDTO addTechnology(@PathParam("sitesId") Long websiteId, @PathParam("technologysId") Long technologyId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource addTecnologia: input: sitesId {0} , technologyId {1}", new Object[]{websiteId, technologyId});
        if (tecnologiaLogic.getTechnology(technologyId) == null) {
            throw new WebApplicationException("El recurso /technologies/" + technologyId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(sitioTecnologiaLogic.addTechnology(websiteId, technologyId));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource addTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos las tecnologias que existen en un sitio web.
     *
     * @param websiteId El ID del sitio web del cual se buscan las tecnologias
     * @return JSONArray {@link TecnologiaDetailDTO} - Los libros encontrados en el
     * sitio web. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTechnologies(@PathParam("sitesId") Long websiteId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologias: input: {0}", websiteId);
        List<TecnologiaDetailDTO> lista = technologysListEntity2DTO(sitioTecnologiaLogic.getTechnologies(websiteId));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologias: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve la tecnologia con el ID recibido en la URL, relativo a un
     * sitio web.
     *
     * @param websiteId El ID del sitio web del cual se busca la tecnologia
     * @param technologysId El ID del libro que se busca
     * @return {@link TecnologiaDetailDTO} - El libro encontrado en el sitio web.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{technologysId: \\d+}")
    public TecnologiaDetailDTO getTechnology(@PathParam("sitesId") Long websiteId, @PathParam("technologysId") Long technologysId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource getTecnologia: input: sitesId {0} , technologysId {1}", new Object[]{websiteId, technologysId});
        if (tecnologiaLogic.getTechnology(technologysId) == null) {
            throw new WebApplicationException("El recurso /technologies/" + technologysId + " no existe.", 404);
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
    public List<TecnologiaDetailDTO> replaceTechnologies(@PathParam("sitesId") Long websiteId, List<TecnologiaDetailDTO> technologies) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource replaceTechnologies: input: sitesId {0} , technologies {1}", new Object[]{websiteId, technologies});
        for (TecnologiaDetailDTO technology : technologies) {
            if (tecnologiaLogic.getTechnology(technology.getId()) == null) {
                throw new WebApplicationException("El recurso /technologies/" + technology.getId() + " no existe.", 404);
            }
        }
        List<TecnologiaDetailDTO> lista = technologysListEntity2DTO(sitioTecnologiaLogic.replaceTechnologies(websiteId, technologysListDTO2Entity(technologies)));
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource replaceTechnologies: output: {0}", lista);
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
    public void removeTechnology(@PathParam("sitesId") Long websiteId, @PathParam("technologysId") Long technologysId) {
        LOGGER.log(Level.INFO, "SitioWebTecnologiasResource removeTechnology: input: sitesId {0} , technologysId {1}", new Object[]{websiteId, technologysId});
        if (tecnologiaLogic.getTechnology(technologysId) == null) {
            throw new WebApplicationException("El recurso /technologies/" + technologysId + " no existe.", 404);
        }
        sitioTecnologiaLogic.removeTechnology(websiteId, technologysId);
        LOGGER.info("SitioWebTecnologiasResource removeTechnology: output: void");
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
