/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.sitios.dtos.TecnologiaDetailDTO;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("technologies")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TecnologiaResource {
    
    private static final Logger LOGGER = Logger.getLogger(TecnologiaResource.class.getName());
    
    @Inject
    private TecnologiaLogic technologyLogic;
    /**
    * Creates a new website technology.
    * @param tecnologia to be created. tecnologia!=null.
    * @return added technology. 
    * @throws BusinessLogicException
    */
    @POST
    public TecnologiaDTO createTechnology(TecnologiaDTO tecnologia) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TecnologiaResource createTechnology: input: {0}", tecnologia);
        TecnologiaDTO tecnologiaDTO = new TecnologiaDTO(technologyLogic.createTechnology(tecnologia.toEntity()));
        LOGGER.log(Level.INFO, "TecnologiaResource createTechnology: output: {0}", tecnologiaDTO);
        return tecnologiaDTO;
    }
    /**
     * Busca y devuelve todas las tecnologias que existen en la aplicacion.
     *
     * @return JSONArray {@link TecnologiaDetailDTO} - Las tecnologias encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TecnologiaDetailDTO> getTechnologies() {
        LOGGER.info("TecnologiaResource getTechnologies: input: void");
        List<TecnologiaDetailDTO> listaTechnologies = listEntity2DTO(technologyLogic.getTechnologies());
        LOGGER.log(Level.INFO, "TecnologiaResource getTechnologies: output: {0}", listaTechnologies);
        return listaTechnologies;
    }

    /**
     * Busca la tecnologia con el id asociado recibido en la URL y lo devuelve.
     *
     * @param technologyId Identificador de la tecnologia que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TecnologiaDetailDTO} - La tecnologia buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tecnologia.
     */
    @GET
    @Path("{technologiesId: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public TecnologiaDetailDTO getTechnology(@PathParam("technologiesId") Long technologyId) {
        LOGGER.log(Level.INFO, "TecnologiaResource getTechnology: input: {0}", technologyId);
        TecnologiaEntity tecnologiaEntity = technologyLogic.getTechnology(technologyId);
        if (tecnologiaEntity == null) {
            throw new WebApplicationException("El recurso /technologies/" + technologyId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(tecnologiaEntity);
        LOGGER.log(Level.INFO, "TecnologiaResource getTechnology: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la tecnologia con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param technologyId Identificador de la tecnologia que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param technology {@link TecnologiaDetailDTO} la tecnologia que se desea guardar.
     * @return JSON {@link TecnologiaDetailDTO} - La tecnologia guardada.
     * @throws BusinessLogicException si se viola una regla de negocio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tecnologia a
     * actualizar.
     */
    @PUT
    @Path("{technologiesId: \\d+}")
    public TecnologiaDetailDTO updateAuthor(@PathParam("technologiesId") Long technologyId, TecnologiaDetailDTO technology) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaResource updateTecnologia: input: technologysId: {0} , technology: {1}", new Object[]{technologyId, technology});
        technology.setId(technologyId);
        if (technologyLogic.getTechnology(technologyId) == null) {
            throw new WebApplicationException("El recurso /authors/" + technologyId + " no existe.", 404);
        }
        TecnologiaDetailDTO detailDTO = new TecnologiaDetailDTO(technologyLogic.updateTechnology(technologyId, technology.toEntity()));
        LOGGER.log(Level.INFO, "TecnologiaResource updateTecnologia: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra la tecnologia con el id asociado recibido en la URL.
     *
     * @param technologyId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws BusinessLogicException si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra la tecnologia a borrar.
     */
    @DELETE
    @Path("{technologiesId: \\d+}")
    public void deleteAuthor(@PathParam("technologiesId") Long technologyId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TecnologiaResource deleteTechnology: input: {0}", technologyId);
        if (technologyLogic.getTechnology(technologyId) == null) {
            throw new WebApplicationException("El recurso /technologies/" + technologyId + " no existe.", 404);
        }
        technologyLogic.deleteTechnology(technologyId);
        LOGGER.info("TecnologiaResource deleteTechnology: output: void");
    }

    /**
     * Convierte una lista de TecnologiaEntity a una lista de TecnologiaDetailDTO.
     *
     * @param entityList Lista de TecnologiaEntity a convertir.
     * @return Lista de TecnologiaDetailDTO convertida.
     */
    private List<TecnologiaDetailDTO> listEntity2DTO(List<TecnologiaEntity> entityList) {
        List<TecnologiaDetailDTO> list = new ArrayList<>();
        for (TecnologiaEntity entity : entityList) {
            list.add(new TecnologiaDetailDTO(entity));
        }
        return list;
    }
}