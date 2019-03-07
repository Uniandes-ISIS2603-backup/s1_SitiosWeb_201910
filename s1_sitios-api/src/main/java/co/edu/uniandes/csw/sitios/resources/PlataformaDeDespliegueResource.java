/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.PlataformaDeDespliegueDTO;
import co.edu.uniandes.csw.sitios.ejb.PlataformaDeDespliegueLogic;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
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

@Path("platforms")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlataformaDeDespliegueResource {
    
    
    
    @Inject
    private PlataformaDeDespliegueLogic logica;
    
    @POST
    public PlataformaDeDespliegueDTO createPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataformaDto)throws BusinessLogicException{
        
        PlataformaDeDespliegueEntity plataformaEntity = plataformaDto.toEntity();
        plataformaEntity = logica.createPlataformaDeDespliegue(plataformaEntity);
        
        return new PlataformaDeDespliegueDTO(plataformaEntity);
    }
    
    
}
