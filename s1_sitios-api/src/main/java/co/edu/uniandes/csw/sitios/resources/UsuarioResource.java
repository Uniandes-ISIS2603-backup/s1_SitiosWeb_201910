/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.UsuarioDTO;
import java.util.List;
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

/**
 * Recurso Usuario
 * @author estudiante
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    /*UsuarioResource*/
    public final static Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    /**
     * Crea un usuario mediante una peticion POST
     * @param user != null, usuario a crear
     * @return Retorna el UsuarioDTO creado
     */
    @POST
    public UsuarioDTO createUser( UsuarioDTO user ){
        return user;
    }
    
    /**
     * @return Lista de usuarios, null en caso de
     * que no halla.
     */
    @GET
    public List<UsuarioDTO> getUsuarios(){
        return null;
    }
    
    /**
     * Toma el usuario segun el id pasado por
     * parametro
     * @param id, identificador del usuario
     * @return Usuario del id.
     */
    @GET
        @Path("{id: \\d+}")
    public UsuarioDTO getUsuario( @PathParam("id") int id ){
        return new UsuarioDTO();
    }
    
    /**
     * Elimina un usuario segun su id.
     * @param id, identificador del usuario
     */
    @DELETE
        @Path("{id: \\d+}")
    public void deleteUser( @PathParam("id") int id ){
        
    }
    
}