/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.PersonaDTO;
import co.edu.uniandes.csw.sitios.ejb.PersonaLogic;
import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allan Roy Corinaldi.
 */
@Path("/people")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PersonaResource {

    private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

    @Inject
    private PersonaLogic personaLogic;

    /**
     * Crea un nuevo Persona con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param Persona {@link PersonaDTO} - EL Persona que se desea guardar.
     * @return JSON {@link PersonaDTO} - El Persona guardado con el atributo id
     * autogenerado.
     */
    @POST
    public PersonaDTO createPersona(PersonaDTO persona) {
        LOGGER.log(Level.INFO, "PersonaResource createPersona: input: {0}", persona);
        PersonaDTO personaDTO = new PersonaDTO(personaLogic.createPersona(persona.toEntity()));
        LOGGER.log(Level.INFO, "PersonaResource createPersona: output: {0}", personaDTO);
        return personaDTO;
    }

    /**
     * Busca la persona con el id asociado recibido en la URL y lo devuelve.
     *
     * @param peopleId Identificador del persona que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PersonaDetailDTO} - La persona buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el persona.
     */
    @GET
    @Path("{peopleId: \\d+}")
    public PersonaDTO getPersona(@PathParam("peopleId") Long peopleId) {
        LOGGER.log(Level.INFO, "PersonaResource getPersona: input: {0}", peopleId);
        PersonaEntity personaEntity = personaLogic.getPersona(peopleId);
        if (personaEntity == null) {
            throw new WebApplicationException("El recurso /people/" + peopleId + " no existe.", 404);
        }
        PersonaDTO detailDTO = new PersonaDTO(personaEntity);
        LOGGER.log(Level.INFO, "PersonaResource getPersona: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra el persona con el id asociado recibido en la URL.
     *
     * @param peopleId Identificador del persona que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el persona a borrar.
     */
    @DELETE
    @Path("{peopleId: \\d+}")
    public void deletePersona(@PathParam("peopleId") Long peopleId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PersonaResource deletePersona: input: {0}", peopleId);
        if (personaLogic.getPersona(peopleId) == null) {
            throw new WebApplicationException("El recurso /people/" + peopleId + " no existe.", 404);
        }
        personaLogic.deletePersona(peopleId);
        LOGGER.info("PersonaResource deletePersona: output: void");
    }

}
