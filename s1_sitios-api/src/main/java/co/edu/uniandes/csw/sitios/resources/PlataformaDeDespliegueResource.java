/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.PlataformaDeDespliegueDTO;
import co.edu.uniandes.csw.sitios.dtos.PlataformaDeDespliegueDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.PlataformaDeDespliegueLogic;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
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
 * @author s.ballesteros
 */

@Path("platforms")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlataformaDeDespliegueResource {
    
    private final static Logger LOGGER = Logger.getLogger(PlataformaDeDespliegueResource.class.getName());
    
    @Inject
    private PlataformaDeDespliegueLogic plataformaLogic;
    
    
    
     /**
     * Crea una nueva plataforma de despliegue con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param plataformaDto {@link PlataformaDeDespliegueDTO} - La plataforma que se desea
     * guardar.
     * @return JSON {@link PlataformaDeDespliegueDTO} - La plataforma guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la plataforma.
     */
    @POST
    public PlataformaDeDespliegueDTO createPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataforma) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueResource createPlataformaDeDespliegue: input: {0}", plataforma);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PlataformaDeDespliegueEntity plataformaEntity = plataforma.toEntity();
        // Invoca la lógica para crear la plataforma nueva
        PlataformaDeDespliegueEntity nuevaPlataformaEntity = plataformaLogic.createPlataformaDeDespliegue(plataformaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PlataformaDeDespliegueDTO nuevaPlataformaDTO = new PlataformaDeDespliegueDTO(plataformaEntity);
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueResource createPlataformaDeDespliegue: output: {0}", nuevaPlataformaDTO);
        return nuevaPlataformaDTO;
    }

    /**
     * Busca y devuelve todas las plataformasDeDespliegue que existen en la aplicacion.
     *
     * @return JSONArray {@link plataformasDeDespliegueDetailDTO} - Las plataformas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PlataformaDeDespliegueDetailDTO> getPlataformas() {
        LOGGER.info("PlataformaDeDespliegueResource getPlataformas: input: void");
        List<PlataformaDeDespliegueDetailDTO> listaPlataformas = listEntity2DetailDTO(plataformaLogic.getPlataformas());
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueResource getPlataformas: output: {0}", listaPlataformas);
        return listaPlataformas;
    }

    /**
     * Busca la plataforma con el id asociado recibido en la URL y la devuelve.
     *
     * @param plataformasId Identificador de la plataforma que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link PlataformaDeDespliegueDetailDTO} - La plataforma buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la plataforma.
     */
    @GET
    @Path("{plataformasId: \\d+}")
    public PlataformaDeDespliegueDetailDTO getPlataformaDeDespliegue(@PathParam("plataformasId") Long plataformasId) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDesplieguelResource getPlataformaDeDespliegue: input: {0}", plataformasId);
        PlataformaDeDespliegueEntity plataformaDeDespliegueEntity;
        plataformaDeDespliegueEntity = plataformaLogic.getPlataformaDeDespliegue(plataformasId);
        if (plataformaDeDespliegueEntity == null) {
            throw new WebApplicationException("El recurso /platforms/" + plataformasId + " no existe.", 404);
        }
        PlataformaDeDespliegueDetailDTO detailDTO = new PlataformaDeDespliegueDetailDTO(plataformaDeDespliegueEntity);
        LOGGER.log(Level.INFO, "PlataformaDeDesplieguelResource getPlataformaDeDespliegue: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la plataforma con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param plataformasId Identificador de la plataforma que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param plataforma {@link PlataformaDeDespliegueDetailDTO} La plataforma que se desea
     * guardar.
     * @return JSON {@link PlataformaDeDespliegueDetailDTO} - La plataforma guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la plataforma a
     * actualizar.
     */
    @PUT
    @Path("{plataformasId: \\d+}")
    public PlataformaDeDespliegueDetailDTO updatePlataformaDeDespliegue(@PathParam("plataformasId") Long plataformasId, PlataformaDeDespliegueDetailDTO plataforma) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDesplieguelResource updatePlataformaDeDespliegue: input: id:{0} , plataforma: {1}", new Object[]{plataformasId, plataforma});
        plataforma.setId(plataformasId);
        if (plataformaLogic.getPlataformaDeDespliegue(plataformasId) == null) {
            throw new WebApplicationException("El recurso /platforms/" + plataformasId + " no existe.", 404);
        }
        PlataformaDeDespliegueDetailDTO detailDTO = new PlataformaDeDespliegueDetailDTO(plataformaLogic.updatePlataforma(plataformasId, plataforma.toEntity()));
        LOGGER.log(Level.INFO, "PlataformaDeDesplieguelResource updatePlataformaDeDespliegue: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la plataforma con el id asociado recibido en la URL.
     *
     * @param plataformasId Identificador de la plataforma que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la plataforma.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la plataforma.
     */
    @DELETE
    @Path("{plataformasId: \\d+}")
    public void deletePlatformaDeDespliegue(@PathParam("plataformasId") Long plataformasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlataformaDeDesplieguelResource deletePlatformaDeDespliegue: input: {0}", plataformasId);
        if (plataformaLogic.getPlataformaDeDespliegue(plataformasId) == null) {
            throw new WebApplicationException("El recurso /platforms/" + plataformasId + " no existe.", 404);
        }
        plataformaLogic.deletePlataformaDeDespliegue(plataformasId);
        LOGGER.info("PlataformaDeDesplieguelResource deletePlatformaDeDespliegue: output: void");
    }
  /**
     * Conexión con el servicio de libros para una plataforma.
     * {@link PlataformaDeDespliegueSitiosWebResource}
     *
     * Este método conecta la ruta de /plataformas con las rutas de /books que
     * dependen de la plataforma, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los libros de una plataforma.
     *
     * @param plataformasId El ID de la plataforma con respecto a la cual se
     * accede al servicio.
     * @return El servicio de libros para esta plataforma en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la plataforma.
     */
    @Path("{plataformasId: \\d+}/books")
    public Class<PlataformaDeDespliegueSitiosWebResource> getPlataformaDeDespliegueSitiosWebResource(@PathParam("plataformasId") Long plataformasId) throws BusinessLogicException {
        if (plataformaLogic.getPlataformaDeDespliegue(plataformasId) == null) {
            throw new WebApplicationException("El recurso /platforms/" + plataformasId + " no existe.", 404);
        }
        return PlataformaDeDespliegueSitiosWebResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PlataformaDeDespliegueEntity a una lista de
     * objetos PlataformaDeDespliegueDetailDTO (json)
     *
     * @param entityList corresponde a la lista de plataformaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de plataforma es en forma DTO (json)
     */
    private List<PlataformaDeDespliegueDetailDTO> listEntity2DetailDTO(List<PlataformaDeDespliegueEntity> entityList) {
        List<PlataformaDeDespliegueDetailDTO> list = new ArrayList<>();
        for (PlataformaDeDespliegueEntity entity : entityList) {
            list.add(new PlataformaDeDespliegueDetailDTO(entity));
        }
        return list;
    }
}

