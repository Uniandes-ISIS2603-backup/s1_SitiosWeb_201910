/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.TecnologiaDTO;
import co.edu.uniandes.csw.sitios.ejb.TecnologiaLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("technologies")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TecnologiaResource {
    
    private static final Logger LOGGER = Logger.getLogger(TecnologiaResource.class.getName());
    
    @Inject
    private TecnologiaLogic technologyLogic;
    /**
    * Creates a new website technology.
    * @param tecnologia to be created. tecnologia!=null.
    * @return added technology. 
    */
    @POST
    public TecnologiaDTO createTechnology(TecnologiaDTO tecnologia)
    {
        LOGGER.log(Level.INFO, "TecnologiaResource createTechnology: input: {0}", tecnologia);
        TecnologiaDTO tecnologiaDTO = new TecnologiaDTO(technologyLogic.createTechnology(tecnologia.toEntity()));
        LOGGER.log(Level.INFO, "TecnologiaResource createTechnology: output: {0}", tecnologiaDTO);
        return tecnologiaDTO;
    }
    /**
     * Updates the information of a given technology.
     * @param tecnologia information to be updated.
     * @param id of the technology to be updated.
     * @return technology with updated information.
     */
    @PUT
    public TecnologiaDTO updateTechnology(TecnologiaDTO tecnologia)
    {
        return tecnologia;
    }
    /**
     * Deletes the information of a given technology.
     * @param id of the technology to be deleted.
     * @return String assuring the deletion of the technology.
     */
    @DELETE
    public String deleteTechnology(Long id)
    {
        return "Se borró la tecnología de id: "+id;
    }
}