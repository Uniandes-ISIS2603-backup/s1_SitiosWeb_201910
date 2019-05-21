/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * there was a thing failing that makes no sence so im adding a comment
 */
package co.edu.uniandes.csw.sitios.resources;


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
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebResource.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * variable que sirve para acceder a la logica de un sitioWeb
     */
    @Inject
    private SitioWebLogic sitelogic;
    
    /**
     * variable que sirve para acceder la la logica de la relacion de 
     * un sitioWeb con sus estados
     */
    @Inject
    private SitioWebEstadosWebLogic sitioWebEstadosWebLogic; 
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
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

    /**
     * obtiene un siitio web
     * @param id del sitio web a buscar
     * @return el sitio web buscado
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{sitesId: \\d+}")
    public SitioWebDTO getWebSite(@PathParam("sitesId") Long id) throws  BusinessLogicException
    {
        LOGGER.log(Level.INFO, "SitioWebResource getWebSite: input: {0}", id);
        SitioWebEntity entity = sitelogic.getWebSite(id);
        SitioWebDTO obtenido= new SitioWebDTO(entity);
        return  obtenido;
    }

    /**
     * obtiene una lista con todos los sitios web en la DB
     * @return listaSites
     */
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
    
    /**
     *  obtiene una lista con todos los sitios relacionados al sitio dado por id
     * @param sitesId sitio al que se le quieres buscar los sitios relacionados
     * @return listaSites 
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{sitesId: \\d+}/related")
    public List<SitioWebDTO> getSitesRelated(@PathParam("sitesId") Long sitesId)throws  BusinessLogicException {
   
        LOGGER.info("BookResource getSites: input: void");
        List<SitioWebDTO> listaSites = new ArrayList<>();
        for(SitioWebEntity siteEntity: sitelogic.getWebSiteRelated(sitesId)) {
            listaSites.add(new SitioWebDTO(siteEntity));
        }
        LOGGER.log(Level.INFO, "BookResource getSites: output: {0}", listaSites.toString());
        return listaSites;
    }
    
    /**
     * actualiza la informacion de un sitioWeb
     * @param website to update
     * @throws BusinessLogicException 
     */
    @PUT
    public void updateSite(SitioWebDTO website)  throws  BusinessLogicException
    {
        LOGGER.log(Level.INFO, "SitioWebResource updateSite: input: {0}", website.getId());
        sitelogic.updateSitio(website.toEntity());
    }

    /**
     * Elimina un sitio web por el id dado como parametro
     * @param id del sitio a borrar
     * @return una confirmacion "deleted" de quel sitio se borro
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{sitesId: \\d+}")
    public String deleteSite(@PathParam("sitesId") Long id) throws  BusinessLogicException
    {
        LOGGER.log(Level.INFO, "SitioWebResource deleteSite: input: {0}", id);
        sitelogic.deleteSite(id);
        return  "deleted";
    }

    /**
     * 
     * @param sitesId
     * @return
     * @throws BusinessLogicException 
     */
    @Path("{sitesId: \\d+}/technologies")
    public Class<SitioWebTecnologiaResource> getSitioWebTecnologiaResource(@PathParam("sitesId") Long sitesId) throws  BusinessLogicException {
        if (sitelogic.getWebSite(sitesId) == null) {
            throw new WebApplicationException("El recurso /websites/" + sitesId + " no existe.", 404);
        }
        return SitioWebTecnologiaResource.class;
    }
    
    /**
     * 
     * @param sitioWebId
     * @return 
     */
    @Path("{sitioWebId: \\d+}/states/")
    public Class<SitioWebEstadosWebResource> getSiteStatesResource(@PathParam("sitioWebId") Long sitioWebId) {
            try {
            sitelogic.getWebSite(sitioWebId);
       
        } catch (Exception e) {
            throw new WebApplicationException("El recurso /websites/" + sitioWebId + " no existe.", 404);
        }
        
        return SitioWebEstadosWebResource.class;
    }   
}
