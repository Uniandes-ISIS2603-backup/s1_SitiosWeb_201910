/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.SitioWebDTO;
import co.edu.uniandes.csw.sitios.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaSitioLogic;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
@Path("technologies/{siteID: \\d+}/site")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TecnologiaSitioWebResourse {


    private static final Logger LOGGER = Logger.getLogger(TecnologiaSitioWebResourse.class.getName());

    @Inject
    private SitioWebLogic sitioWebLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @Inject
    private TecnologiaSitioLogic tecnologiaSitioLogic;

    @PUT
    public TecnologiaDTO replaceEditorial(@PathParam("siteID") Long siteID, SitioWebDTO site) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: input: booksId{0} , Editorial:{1}", new Object[]{siteID, site.toString()});
        if (sitioWebLogic.getWebSite(siteID) == null) {
            throw new WebApplicationException("El recurso /sites/" + siteID + " no existe.", 404);
        }
       // TecnologiaDTO tecDTO = new TecnologiaDTO(tecnologiaSitioLogic.replace(siteID, site.getId()));
       // LOGGER.log(Level.INFO, "BookEditorialResource replaceEditorial: output: {0}", tecDTO.toString());
        return null;
    }
    
}
