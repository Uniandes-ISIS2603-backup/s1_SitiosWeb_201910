/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.EstadoWebDTO;
import co.edu.uniandes.csw.sitios.ejb.EstadoWebLogic;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
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
 * Clase que implementa el recurso para un "EstadoWeb".
 * 
 * @author Daniel Preciado
 */
@Path("estadosWeb")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstadoWebResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    
    private static final Logger LOGGER = Logger.getLogger(EstadoWebResource.class.getName());
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un EstadoWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    EstadoWebLogic estadoWebLogic;
    
      
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Crea un estado Web con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param estadoWeb {@link EstadoWebDTO} - el estadoWeb que se desea
     * guardar.
     * @return JSON {@link EdstadoWebDTO} - el estado Web guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el estado web.
     */
    @POST
    public EstadoWebDTO createEstadoWeb(EstadoWebDTO estadoWeb) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EstadoWebResource createEstadoWeb: input: {0}", estadoWeb.toString());
        EstadoWebEntity estadoWebEntity = estadoWeb.toEntity();
        EstadoWebEntity pEstadoWebEntity = estadoWebLogic.createEstadoWeb(estadoWebEntity);
        EstadoWebDTO nuevoEstadoWebDTO = new EstadoWebDTO(pEstadoWebEntity);
        LOGGER.log(Level.INFO, "EstadoWebResource createEstadoWeb: output: {0}", nuevoEstadoWebDTO.toString());
        return nuevoEstadoWebDTO;
    }

    /**
     * Busca y devuelve todos los estados web que existen en la app.
     *
     * @return JSONArray {@link EstadoWebDTO} - los estados web encontrados en
     * la app. Si no hay ninguno, entonces retorna una lista vacía.
     */
    @GET
    public List<EstadoWebDTO> getEstadosWeb() 
    {
        LOGGER.info("EstadosWebResource getEstadosWeb: input: void");
        List<EstadoWebDTO> estadosWebRetorno = listEntity2DetailDTO(estadoWebLogic.getEstadosWeb());
        LOGGER.log(Level.INFO, "EstadosWebResource getEstadosWeb: output: {0}", estadosWebRetorno.toString());
        return estadosWebRetorno;
    }

    /**
     * Busca el estado Web con el id asociado recibido en la URL y lo devuelve.
     *
     * @param estadosWebId Identificador de el estado Web que se esta buscando.
     * @return JSON {@link EstadoWebDTO} - el estado Web buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estado Web.
     */
    @GET
    @Path("{estadosWebId: \\d+}")
    public EstadoWebDTO getEstadoWeb(@PathParam("estadosWebId") Long estadosWebId) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "EstadoWebResource getEstadoWeb: input: {0}", estadosWebId);
        EstadoWebEntity estadoWebEntity = estadoWebLogic.getEstadoWeb(estadosWebId);
        if (estadoWebEntity == null) 
        {
            throw new WebApplicationException("El recurso /estadosWeb/" + estadosWebId + " no existe.", 404);
        }
        EstadoWebDTO detailDTO = new EstadoWebDTO(estadoWebEntity);
        LOGGER.log(Level.INFO, "EstadoWebResource getEstadoWeb: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza el estado Web con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param estadosWebId Identificador de el estado Web que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param estadoWeb {@link EstadoWebDTO} el estado Web que se desea guardar.
     * @return JSON {@link EstadoWebDTO} - el estado Web guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estado Web a
     * actualizar.
     */
    @PUT
    @Path("{estadosWebId: \\d+}")
    public EstadoWebDTO updateEstadoWeb(@PathParam("estadosWebId") Long estadosWebId, EstadoWebDTO estadoWeb) throws WebApplicationException 
    {
        LOGGER.log(Level.INFO, "EstadoWebResource updateEstadoWeb: input: id:{0} , estadoWeb: {1}", new Object[]{estadosWebId, estadoWeb.toString()});
        estadoWeb.setId(estadosWebId);
        if (estadoWebLogic.getEstadoWeb(estadosWebId) == null) 
        {
            throw new WebApplicationException("El recurso /estadosWeb/" + estadosWebId + " no existe.", 404);
        }
        EstadoWebDTO detailDTO = new EstadoWebDTO(estadoWebLogic.updateEstadoWeb(estadosWebId, estadoWeb.toEntity()));
        LOGGER.log(Level.INFO, "EstadoWebResource updateEstadoWeb: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el estado Web con el id asociado recibido en la URL.
     *
     * @param estadosWebId Identificador de el estado Web que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el estado Web.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estado Web.
     */
    @DELETE
    @Path("{estadosWebId: \\d+}")
    public void deleteEstadoWeb(@PathParam("estadosWebId") Long estadosWebId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EstadoWebResource deleteEstadoWeb: input: {0}", estadosWebId);
        if (estadoWebLogic.getEstadoWeb(estadosWebId) == null) 
        {
            throw new WebApplicationException("El recurso /estadosWeb/" + estadosWebId + " no existe.", 404);
        }
        estadoWebLogic.deleteEstadoWeb(estadosWebId);
        LOGGER.info("EstadoWebResource deleteEstadoWeb: output: void");
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDTO (json)
     *
     * @param entityList corresponde a la lista de estadoWebes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de estadoWebes en forma DTO (json)
     */
    private List<EstadoWebDTO> listEntity2DetailDTO(List<EstadoWebEntity> entityList) {
        List<EstadoWebDTO> list = new ArrayList<>();
        for (EstadoWebEntity entity : entityList) {
            list.add(new EstadoWebDTO(entity));
        }
        return list;
    }
    
}
