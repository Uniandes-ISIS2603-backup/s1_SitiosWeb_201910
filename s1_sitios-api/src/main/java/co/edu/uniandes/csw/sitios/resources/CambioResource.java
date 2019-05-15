/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.CambioDTO;
import co.edu.uniandes.csw.sitios.ejb.CambioLogic;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import java.io.Serializable;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Path("changes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CambioResource {
      private static final Logger LOGGER = Logger.getLogger(CambioResource.class.getName());

    @Inject
    private CambioLogic cambioLogic;
    
     /**
     * Crea un cambio con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param cambioDto
     * @param cambio {@link CambioDTO} - el cambio que se desea
     * guardar.
     * @return JSON {@link CambioDTO} - el sitio Web guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException
     * Error de lógica que se genera cuando ya existe el cambio.
     */
    @POST
    public CambioDTO createCambio(CambioDTO cambioDto)throws BusinessLogicException {
    
        CambioEntity cambioEntity = cambioDto.toEntity();
        cambioEntity = cambioLogic.createCambio(cambioEntity);
        return  new CambioDTO(cambioEntity);
    }
    
     /**
     * Permite dar un cambio segun su Id.
     * @param cambioId : Numero entero, id con
     * el cual se identifica un cambio
     * @return CambioDTO. 
     */
    @GET
    @Consumes("application/json")
    @Path("{cambioid: \\d+}")
    public CambioDTO getCambio( @PathParam("cambioid") Long cambioId )throws BusinessLogicException {

        LOGGER.log(Level.INFO, "CambioResource getCambio: input: {0}", cambioId);
        CambioEntity entity = cambioLogic.getCambio(cambioId);
        CambioDTO camDTO = new CambioDTO(entity);
        return  camDTO;
    }
    
    /**
     * Trae todos los cambios asociados a una entidad del sitio web
     * @return lista con todas las notificaciones presentes en el sistema
     */
    @GET
    public List<CambioDTO> getCambios()throws BusinessLogicException {
       // LOGGER.info("SitioWebResource getCambios: input: void");
        List<CambioDTO> cambios = new ArrayList<>();
        for(CambioEntity siteEntity: cambioLogic.getCambios()) {
            cambios.add(new CambioDTO(siteEntity));
        }
       // LOGGER.log(Level.INFO, "SitioWebResource getCambios: output: {0}", cambios.toString());
        return cambios;
    }
    
        /**
     * Trae todos los cambios asociados a una entidad del sitio web filtrados por un atributo
     * @return lista con todas las notificaciones presentes en el sistema filtrados por un atributo
     */
     /**
     * Permite dar un cambio segun su Id.
     * @param filter
     * @return CambioDTO. 
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException 
     */
    @GET
    @Path("/filter")
    public List<CambioDTO> getCambiosFiltro(@QueryParam("atribute")String atribute, @QueryParam("param") String param)throws BusinessLogicException {
        
        List<CambioDTO> cambios = new ArrayList<>();
        for(CambioEntity siteEntity: cambioLogic.getCambiosFiltro(atribute, param)) {
            cambios.add(new CambioDTO(siteEntity));
        }
       // LOGGER.log(Level.INFO, "SitioWebResource getCambios: output: {0}", cambios.toString());
        return cambios;
    }
    
    /**
     * Actualiza el cambio con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param cambiosId Identificador de el cambio que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param cambio {@link CambioDTO} La cambio que se desea
     * guardar.
     * @return JSON {@link CambioDTO} - La cambio guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cambio a
     * actualizar.
     */
    @PUT
    @Path("{cambiosId: \\d+}")
    public CambioDTO updateCambio(@PathParam("cambiosId") Long cambiosId, CambioDTO cambio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CambioResource updateCambio: input: id: {0} , cambio: {1}", new Object[]{cambiosId, cambio});
        cambio.setId(cambiosId);
        if (cambioLogic.getCambio(cambiosId) == null) {
            throw new WebApplicationException("El recurso /changes/" + cambiosId + " no existe.", 404);
        }
        CambioDTO cambioDTO = new CambioDTO(cambioLogic.updateCambio(cambiosId, cambio.toEntity()));
        LOGGER.log(Level.INFO, "CambioResource updateCambio: output: {0}", cambioDTO);
        return cambioDTO;
    }

    /**
     * Borra el cambio con el id asociado recibido en la URL.
     *
     * @param cambiosId Identificador de el cambio que se desea
     * borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la
     * cambio.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando la organizacion tiene un premio
     * asociado.
     */
    @DELETE
    @Path("{cambiosId: \\d+}")
    public void deleteCambio(@PathParam("cambiosId") Long cambiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CambioResource deleteCambio: input: {0}", cambiosId);
        if (cambioLogic.getCambio(cambiosId) == null) {
            throw new WebApplicationException("El recurso /changes/" + cambiosId + " no existe.", 404);
        }
        cambioLogic.deleteCambio(cambiosId);
        LOGGER.info("CambioResource deleteCambio: output: void");
    }
    
    public class Filtro implements Serializable
    {
        private String atributo;
        private String param;
        public Filtro(String pAtr, String pParam)
        {
            atributo = pAtr;
            param = pParam;
        }

        /**
         * @return the atributo
         */
        public String getAtributo() {
            return atributo;
        }

        /**
         * @param atributo the atributo to set
         */
        public void setAtributo(String atributo) {
            this.atributo = atributo;
        }

        /**
         * @return the param
         */
        public String getParam() {
            return param;
        }

        /**
         * @param param the param to set
         */
        public void setParam(String param) {
            this.param = param;
        }
        
    }

}