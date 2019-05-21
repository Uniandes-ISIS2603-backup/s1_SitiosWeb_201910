 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.EstadoWebDTO;
import co.edu.uniandes.csw.sitios.ejb.EstadoWebLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebEstadosWebLogic;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "sitioWeb/{id}/estadosWeb".
 *
 * @author Daniel Preciado
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitioWebEstadosWebResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, un especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebEstadosWebResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un EstadoWebLogic, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private EstadoWebLogic estadoWebLogic;
    
    /**
     * variable empleada para acceder a la logica de un SitioWebEstadosWebLogic, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebEstadosWebLogic sitioWebEstadosWebLogic; 


    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Guarda un estadoWeb dentro de un sitioWeb con la informacion que recibe 
     * por la URL. Se devuelve el estadoWeb que se guarda en la sitioWeb.
     *
     * @param sitioWebId Identificador de la sitioWeb que se esta
     * actualizando. Este debe ser un cadena de dígitos.
     * @param estadosWebId Identificador del estadoWeb que se desea guardar. Este debe
     * ser un cadena de dígitos.
     * @return JSON {@link EstadoWebDTO} - El estadoWeb guardado en la sitioWeb.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estadoWeb.
     */
    @POST
    @Path("{estadosWebId: \\d+}")
    public EstadoWebDTO addEstadoWeb(@PathParam("sitioWebId") Long sitioWebId, @PathParam("estadosWebId") Long estadosWebId) 
    {
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource addEstadoWeb: input: sitioWebsID: {0} , estadosWebId: {1}", new Object[]{sitioWebId, estadosWebId});
        if (estadoWebLogic.getEstadoWeb(estadosWebId) == null) 
        {
            throw new WebApplicationException("El recurso /estadosWeb/" + estadosWebId + " no existe.", 404);
        }
        EstadoWebDTO estadoWebDTO = new EstadoWebDTO(sitioWebEstadosWebLogic.addEstadoWeb(estadosWebId, sitioWebId));
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource addEstadoWeb: output: {0}", estadoWebDTO);
        return estadoWebDTO;
    }

    /**
     * Busca y devuelve todos los estadosWeb que existen en el sitioWeb.
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta buscando.
     * Este debe ser un cadena de dígitos.
     * @return JSONArray {@link EstadoWebDTO} - Los estadosWeb encontrados en el
     * sitioWeb. Si no hay ninguno retorna un lista vacía.
     */
    @GET
    public List<EstadoWebDTO> getEstadosWeb(@PathParam("sitioWebId") Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadosWeb: input: {0}", sitioWebId);
        List<EstadoWebDTO> listaEstadosWebDTOs = estadosWebListEntity2DTO(sitioWebEstadosWebLogic.getEstadosWeb(sitioWebId));
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadosWeb: output: {0}", listaEstadosWebDTOs);
        return listaEstadosWebDTOs;
    }
    
    @GET
    @Path("/last")
    public EstadoWebDTO getCurrentState(@PathParam("sitioWebId") Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadosWeb: input: {0}", sitioWebId);
        List<EstadoWebDTO> listaEstadosWebDTOs = estadosWebListEntity2DTO(sitioWebEstadosWebLogic.getEstadosWeb(sitioWebId));
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadosWeb: output: {0}", listaEstadosWebDTOs);
        return listaEstadosWebDTOs.get(0);
    }
    
    
    /**
     * Busca el estadoWeb con el id asociado dentro de la sitioWeb con id asociado.
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta buscando.
     * Este debe ser un cadena de dígitos.
     * @param estadosWebId Identificador del estadoWeb que se esta buscando. Este debe
     * ser un cadena de dígitos.
     * @return JSON {@link EstadoWebDTO} - El estadoWeb buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estadoWeb.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estadoWeb en la
     * sitioWeb.
     */
    @GET
    @Path("{estadosWebId: \\d+}")
    public EstadoWebDTO getEstadoWeb(@PathParam("sitioWebId") Long sitioWebId, @PathParam("estadosWebId") Long estadosWebId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadoWeb: input: sitioWebsID: {0} , estadosWebId: {1}", new Object[]{sitioWebId, estadosWebId});
        if (estadoWebLogic.getEstadoWeb(estadosWebId) == null) 
        {
            throw new WebApplicationException("El recurso /sitioWebs/" + sitioWebId + "/estadosWeb/" + estadosWebId + " no existe.", 404);
        }
        EstadoWebDTO estadoWebDTO = new EstadoWebDTO(sitioWebEstadosWebLogic.getEstadoWeb(sitioWebId, estadosWebId));
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource getEstadoWeb: output: {0}", estadoWebDTO);
        return estadoWebDTO;
    }

    /**
     * Remplaza las instancias de EstadoWeb asociadas a una instancia de Editorial
     *
     * @param sitioWebId Identificador de el sitioWeb que se esta
     * remplazando. Este debe ser un cadena de dígitos.
     * @param estadosWeb JSONArray {@link EstadoWebDTO} El arreglo de estadosWeb nuevo para la
     * sitioWeb.
     * @return JSON {@link EstadoWebDTO} - El arreglo de estadosWeb guardado en la
     * sitioWeb.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el estadoWeb.
     */
    @PUT
    public List<EstadoWebDTO> replaceEstadosWeb(@PathParam("sitioWebId") Long sitioWebId, List<EstadoWebDTO> estadosWeb) 
    {
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource replaceEstadosWeb: input: sitioWebId: {0} , estadosWeb: {1}", new Object[]{sitioWebId, estadosWeb});
        for (EstadoWebDTO estadoWeb : estadosWeb) 
        {
            if (estadoWebLogic.getEstadoWeb(estadoWeb.getId()) == null) 
            {
                throw new WebApplicationException("El recurso /estadosWeb/" + estadoWeb.getId() + " no existe.", 404);
            }
        }
        List<EstadoWebDTO> listaEstadosWebDTOs = estadosWebListEntity2DTO(sitioWebEstadosWebLogic.replaceEstadosWeb(sitioWebId, estadosWebListDTO2Entity(estadosWeb)));
        LOGGER.log(Level.INFO, "SitioWebEstadosWebResource replaceEstadosWeb: output: {0}", listaEstadosWebDTOs);
        return listaEstadosWebDTOs;
    }

    /**
     * Convierte un lista de EstadoWebEntity a una lista de EstadoWebDTO.
     *
     * @param entityList Lista de EstadoWebEntity a convertir.
     * @return Lista de EstadoWebDTO convertida.
     */
    private List<EstadoWebDTO> estadosWebListEntity2DTO(List<EstadoWebEntity> entityList) 
    {
        List<EstadoWebDTO> list = new ArrayList();
        for (EstadoWebEntity entity : entityList) 
        {
            list.add(new EstadoWebDTO(entity));
        }
        return list;
    }

    /**
     * Convierte un lista de EstadoWebDTO a un lista de EstadoWebEntity.
     *
     * @param dtos Lista de EstadoWebDTO a convertir.
     * @return Lista de EstadoWebEntity convertida.
     */
    private List<EstadoWebEntity> estadosWebListDTO2Entity(List<EstadoWebDTO> dtos) 
    {
        List<EstadoWebEntity> list = new ArrayList<>();
        for (EstadoWebDTO dto : dtos) 
        {
            list.add(dto.toEntity());
        }
        return list;
    }
}

