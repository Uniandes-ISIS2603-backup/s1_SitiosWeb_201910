/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TipoDeTecnologiaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("technologyTypes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TipoTecnologiaResource {
    private static final Logger LOGGER = Logger.getLogger(TipoTecnologiaResource.class.getName());
    
    /**
    * @param tipoTecnologia to be assigned
    * @return Technology type asigned
    */
    @POST
    public TipoDeTecnologiaDTO createTechnologyType(TipoDeTecnologiaDTO tipoTecnologia)
    {
        return tipoTecnologia;
    }
    
}
