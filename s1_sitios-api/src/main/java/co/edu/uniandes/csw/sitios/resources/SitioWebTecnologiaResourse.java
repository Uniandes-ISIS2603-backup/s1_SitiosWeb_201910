/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.ejb.SitioTecnologiaLogic;
import co.edu.uniandes.csw.sitios.ejb.SitioWebLogic;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("sites/{technologiesID: \\d+}/technologies")
@Path("sites/{siteID: \\d+}/technologie")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitioWebTecnologiaResourse {
    @Inject
    private SitioWebLogic sitioWebLogic;

    @Inject
    private TecnologiaLogic tecnologiaLogic;

    @Inject
    private SitioTecnologiaLogic sitioTecnologiaLogic;


}
