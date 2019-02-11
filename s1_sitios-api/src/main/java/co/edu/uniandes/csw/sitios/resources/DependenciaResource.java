/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.DependenciaDTO;
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
 *
 * @author estudiante
 */
@Path("Dependencies")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DependenciaResource {
    public final static Logger LOGGER = Logger.getLogger(DependenciaResource.class.getName());

        
        /**
     * Permite dar un Dependencia segun su Id.
     * @param id : Numero entero, id con
     * el cual se identifica una Dependencia
     * @return DependenciaDTO. 
     */
    @GET
        @Path("{id: \\d+}")
    public DependenciaDTO getDependencia( @PathParam("id") int id ){
        return null;
    }
    
    /**
     * Actualiza un Dependencia dado un id,
     * y se cambia por el administrador del parametro.
     * @param id del Dependencia que se actualizara
     * @param dependencia
     * @return Dependencia actualizado.
     */
    @PUT
        @Path("{id: \\d+}")
    public DependenciaDTO updateDependencia(  @PathParam("id") int id, DependenciaDTO dependencia ){
        return null;
    }
    
    /**
     * Elimina la dependencia siempre que no se
     * requieran de sus servicios.
     * @param id, identificador del admin
     */
    @DELETE
        @Path("{id: \\d+}")
    public void deleteDependencia( @PathParam("id") int id ){
        
    }
    
    /**
     * Crea una Dependencia mediante una peticion POST
     * @param Dependencia != null, Dependencia a crear
     * @return Retorna el DependenciaDTO creado
     */
    @POST
    public DependenciaDTO createDependencia( DependenciaDTO dependencia ){
        return dependencia;
    }
}