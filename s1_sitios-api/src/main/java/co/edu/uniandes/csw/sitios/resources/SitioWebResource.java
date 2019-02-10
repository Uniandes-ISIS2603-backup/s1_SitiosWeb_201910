/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Albert Molano
 */
@Path("websites")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SitioWebResource {
    
    private static final Logger LOGGER = Logger.getLogger(SitioWebResource.class.getName());
    
     /**
     * Crea un sitio web con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param website {@link SitioWebDTO} - el sitioWeb que se desea
     * guardar.
     * @return JSON {@link SitioWebDTO} - el sitio Web guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el sitio web.
     */
    @POST
    public SitioWebDTO createWebSite(SitioWebDTO website)throws BusinessLogicException {
    
        LOGGER.log(Level.INFO, "SitioWebResource createWebsite: input: {0}", website.toString());
        SitioWebDTO nuevoSitio = null;
        return website;
    }
    
}
