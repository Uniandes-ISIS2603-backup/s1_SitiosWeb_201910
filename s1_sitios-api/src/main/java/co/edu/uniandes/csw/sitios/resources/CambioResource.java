/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.CambioDTO;
import co.edu.uniandes.csw.sitios.dtos.CambioDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author s.ballesteros
 */

@Path("changes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CambioResource {
    
    private static final Logger LOGGER = Logger.getLogger(CambioResource.class.getName());
    
        @Inject
    private CambioLogic cambioLogic;
    
    @POST
    public CambioDTO createCambio(CambioDTO cambioDto)throws BusinessLogicException{
        
       CambioEntity cambioEntity = cambioDto.toEntity();
       cambioEntity = cambioLogic.createCambio(cambioEntity);
        
        return cambioDto;
    } 
    
    @GET
    @Path("{plataformasId: \\d+}")
    public CambioDTO getCambio(@PathParam("plataformasId") Long plataformaId) throws  BusinessLogicException
    {
        LOGGER.log(Level.INFO, "PlataformaDeDespliegueResource getCambio: input: {0}", plataformaId);
        CambioEntity entity = cambioLogic.getCambio(plataformaId);
        CambioDTO obtenido= new CambioDTO(entity);
        return  obtenido;
    }

    @Path("{plataformasId: \\d+}/websites")
    public Class<SitioWebTecnologiaResourse> getPlataformaSitioWebResourse(@PathParam("plataformasId") Long plataformasId) throws  BusinessLogicException {
        if (cambioLogic.getCambio(plataformasId) == null) {
            throw new WebApplicationException("El recurso /websites/" + plataformasId + " no existe.", 404);
        }
        return SitioWebTecnologiaResourse.class;
    }
    
     @DELETE
    @Path("{organizationsId: \\d+}")
    public void deleteCambio(@PathParam("organizationsId") Long organizationsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "OrganizationResource deleteOrganization: input: {0}", organizationsId);
        if (cambioLogic.getCambio(organizationsId) == null) {
            throw new WebApplicationException("El recurso /organizations/" + organizationsId + " no existe.", 404);
        }
        cambioLogic.deleteCambio(organizationsId);
        LOGGER.info("CambioResource deleteCambio: output: void");
    }

    private List<CambioDetailDTO> listOrganizationEntity2DetailDTO(List<CambioEntity> entityList) {
        List<CambioDetailDTO> list = new ArrayList<>();
        for (CambioEntity entity : entityList) {
            list.add(new CambioDetailDTO(entity));
        }
        return list;
    }
    }

