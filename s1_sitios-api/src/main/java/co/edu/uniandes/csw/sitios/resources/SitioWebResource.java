/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * there was a thing failing that makes no sence so im adding a comment
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.EstadoWebDTO;
import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebEstadosWebLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

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
    
    @Inject
    private SitioWebLogic sitelogic;
    
    @Inject
    private SitioWebEstadosWebLogic sitioWebEstadosWebLogic; 
     /**
     * Crea un sitio web con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param website {@link SitioWebDTO} - el sitioWeb que se desea
     * guardar.
     * @return JSON {@link SitioWebDTO} - el sitio Web guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException
     * Error de lógica que se genera cuando ya existe el sitio web.
     */
    @POST
    public SitioWebDTO createWebSite(SitioWebDTO website)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SitioWebResource createWebsite: input: {0}", website.toString());
        SitioWebEntity entity = website.toEntity();
        SitioWebEntity nuevoSitioEntity = sitelogic.createWebSite(entity);
        SitioWebDTO nuevoSitioDTO= new SitioWebDTO(entity);
        return nuevoSitioDTO;
    }

    @GET
    @Path("{sitesId: \\d+}")
    public SitioWebDTO getWebSite(@PathParam("sitesId") Long id) throws  BusinessLogicException
    {
        LOGGER.log(Level.INFO, "SitioWebResource getWebSite: input: {0}", id);
        SitioWebEntity entity = sitelogic.getWebSite(id);
        SitioWebDTO obtenido= new SitioWebDTO(entity);
        return  obtenido;
    }


    @GET
    public List<SitioWebDTO> getSites()
    {
        LOGGER.info("BookResource getSites: input: void");
        List<SitioWebDTO> listaSites = new ArrayList<>();
        for(SitioWebEntity siteEntity: sitelogic.getSites()) {
            listaSites.add(new SitioWebDTO(siteEntity));
        }
        LOGGER.log(Level.INFO, "BookResource getSites: output: {0}", listaSites.toString());
        return listaSites;
    }

    @Path("{sitesId: \\d+}/technologies")
    public Class<SitioWebTecnologiaResourse> getSitioWebTecnologiaResourse(@PathParam("sitesId") Long sitesId) throws  BusinessLogicException {
        if (sitelogic.getWebSite(sitesId) == null) {
            throw new WebApplicationException("El recurso /books/" + sitesId + " no existe.", 404);
        }
        return SitioWebTecnologiaResourse.class;
    }
    
    @Path("{sitioWebId: \\d+}/states/")
    public Class<SitioWebEstadosWebResource> getSiteStatesResource(@PathParam("sitioWebId") Long sitioWebId) {
            try {
            sitelogic.getWebSite(sitioWebId);
       
        } catch (Exception e) {
            throw new WebApplicationException("El recurso /sites/" + sitioWebId + " no existe.", 404);
        }
        
        return SitioWebEstadosWebResource.class;
    }
    
   
    
  
    
    
}
