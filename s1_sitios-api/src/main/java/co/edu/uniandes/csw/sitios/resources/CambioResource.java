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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    public CambioDTO createNotification(CambioDTO cambioDto)throws BusinessLogicException {
    
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
    @Path("{cambioid: \\d+}")
    public CambioDTO getCambio( @PathParam("cambioid") Long cambioId )throws BusinessLogicException {

        LOGGER.log(Level.INFO, "CambioResource getCambio: input: {0}", cambioId);
        CambioEntity entity = cambioLogic.getCambio(cambioId);
        CambioDTO camDTO = new CambioDTO(entity);
        return  camDTO;
    }
    
    /**
     * Permite obtener todas las notificaciones del sistema
     * @return lista con todas las notificaciones presentes en el sistema
     */
    @GET
    public List<CambioDTO> getNotificacion()throws BusinessLogicException {
        LOGGER.info("BookResource getNotifications: input: void");
        List<CambioDTO> listaNotis = new ArrayList<>();
        for(CambioEntity siteEntity: cambioLogic.getCambios()) {
            listaNotis.add(new CambioDTO(siteEntity));
        }
        LOGGER.log(Level.INFO, "BookResource getSites: output: {0}", listaNotis.toString());
        return listaNotis;
    }
}