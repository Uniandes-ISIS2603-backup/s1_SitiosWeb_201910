/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.EstadoWebDTO;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso "EstadoWeb".
 * 
 * @author Daniel Preciado
 */
@Path("estadosWeb")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class EstadoWebResource {
    
    private static final Logger LOGGER = Logger.getLogger(EstadoWebResource.class.getName());

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
    public EstadoWebDTO createEstadoWeb(EstadoWebDTO estadoWeb) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EstadoWebResource createEstadoWeb: input: {0}", estadoWeb.toString());
        EstadoWebDTO nuevoEstadoWebDTO = null;
        return nuevoEstadoWebDTO;
    }

    /**
     * Borra el estado web con el id asociado recibido en la URL.
     *
     * @param estadoWebId Identificador del estado Web que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{estadoWebId: \\d+}")
    public void deleteEstadoWebId(@PathParam("estadoWebId") Long estadoWebId) {
        LOGGER.log(Level.INFO, "EstadoWebResource deleteEstadoWeb : input: {0}", estadoWebId);

    }
    
    /**
     *  devuleve un estado web solicitado
     * @return EstadoWeb
     */
    @GET
    public EstadoWebDTO getEstadoWeb()
    {
        EstadoWebDTO nuevoEstadoWebDTO = null;
        return nuevoEstadoWebDTO;
    }
    
}
