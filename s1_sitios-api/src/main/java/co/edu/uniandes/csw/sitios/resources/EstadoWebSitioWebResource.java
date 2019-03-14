/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.EstadoWebDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.EstadoWebLogic;
import co.edu.uniandes.csw.sitios.ejb.EstadoWebSitioWebLogic;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "sitioWeb/{id}/EstadosWeb".
 * 
 * @author Daniel Preciado
 */

@Path("sitiosWeb/{sitiosWebId: \\d+}/estadoWeb")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoWebSitioWebResource {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(EstadoWebSitioWebResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable empleada para acceder a la logica de un EstadoWeb, esto se logra
     * mediante inyeccion de dependencias
     */

    @Inject
    private EstadoWebLogic estadoWebLogic; 

    /**
     * variable empleada para acceder a la logica de un EstadoWebSitioWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private EstadoWebSitioWebLogic estadoWebSitioWebLogic; 

    /**
     * variable empleada para acceder a la logica de un EstadoWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebLogic sitioWebLogic; 
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Remplaza la instancia de SitioWeb asociada a un EstadoWeb.
     *
     * @param estadoWebId Identificador del estadoWeb que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param sitioWeb el  sitioWeb que del que será el estadoWeb.
     * @return JSON {@link EstadoWebDTO} - El arreglo de EstadosWeb guardado en la
     * sitioWeb.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el sitioWeb o el
     * estadoWeb.
     */
    @PUT
    public EstadoWebDTO replaceSitioWeb (@PathParam("estadoWebId") Long estadoWebId, SitioWebDTO sitioWeb) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EstadoWebSitioWebResource replaceSitioWeb: input: estadoWebId{0} , SitioWeb:{1}", new Object[]{estadoWebId, sitioWeb});
        if (estadoWebLogic.getEstadoWeb(estadoWebId) == null) 
        {
            throw new WebApplicationException("El recurso /EstadosWeb/" + estadoWebId + " no existe.", 404);
        }
        if (sitioWebLogic.getWebSite(sitioWeb.getId()) == null)
        {
            throw new WebApplicationException("El recurso /sitioWeb/" + sitioWeb.getId() + " no existe.", 404);
        }
        EstadoWebDTO estadoWebDTO = new EstadoWebDTO(estadoWebSitioWebLogic.replaceSitioAsociado(estadoWebId, sitioWeb.getId()));
        LOGGER.log(Level.INFO, "EstadoWebSitioWebResource replaceSitioWeb: output: {0}", estadoWebDTO);
        return estadoWebDTO;
    }
    
}
