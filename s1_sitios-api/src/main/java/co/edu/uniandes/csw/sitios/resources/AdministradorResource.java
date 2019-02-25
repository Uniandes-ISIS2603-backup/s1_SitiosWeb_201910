/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.AdministradorDTO;
import co.edu.uniandes.csw.sitios.dtos.UsuarioDTO;
import co.edu.uniandes.csw.sitios.ejb.AdministradorLogic;
import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Recurso administrador
 *
 * @author estudiante
 */
@Path("admins")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {

    public final static Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

    /**
     * Permite dar un administador segun su Id.
     *
     * @param id : Numero entero, id con el cual se identifica un admin
     * @return AdministradorDTO.
     */
    @GET
   @Path("{id: \\d+}")
    public AdministradorDTO getAdministrador(@PathParam("id") int id) {
       return null;
    }

    /**
     * Actualiza un administrador dado un id, y se cambia por el administrador
     * del parametro.
     *
     * @param id del amnisitrador que se actualizara
     * @param administrador, datos del admin que se cambiaran
     * @return El administrador actualizado.
     */
    @PUT
    @Path("{id: \\d+}")
    public AdministradorDTO updateAdministrador(@PathParam("id") int id, AdministradorDTO administrador) {
        return null;
    }

    /**
     * Elimina el Admin siempre que no se requieran de sus servicios.
     *
     * @param id, identificador del admin
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAdministrador(@PathParam("id") int id) {

    }

    /**
     * Crea un administrador mediante una peticion POST
     *
     * @param admin != null, admin a crear
     * @return Retorna el AdministradorDTO creado
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) {
        AdministradorDTO nuevoAdministradorDTO = new AdministradorDTO(administrador.toEntity());
        LOGGER.log(Level.INFO, "AministradorResource createAministrador");
        
        return nuevoAdministradorDTO;
    }
}
