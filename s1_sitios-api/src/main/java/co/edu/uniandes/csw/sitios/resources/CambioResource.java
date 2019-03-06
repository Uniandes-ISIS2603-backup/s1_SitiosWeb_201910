/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.CambioDTO;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author s.ballesteros
 */

@Path("change")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CambioResource {
    @Inject
    private CambioLogic logica;
    
    private static final Logger LOGGER = Logger.getLogger(PlataformaDeDespliegueResource.class.getName());
    
    @POST
    public CambioDTO createPlataformaDeDespliegue(CambioDTO cambioDto)throws BusinessLogicException{
        
        CambioEntity cambioEntity = cambioDto.toEntity();
        cambioEntity = logica.createCambio(cambioEntity);
        
        return new CambioDTO(cambioEntity);
    } 
}
