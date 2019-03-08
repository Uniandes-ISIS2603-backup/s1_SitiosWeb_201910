/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.resources;

import co.edu.uniandes.csw.sitios.dtos.UsuarioDTO;
import co.edu.uniandes.csw.sitios.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.sitios.ejb.UsuarioLogic;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
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

/**
 * Recurso Usuario
 *
 * @author Allan Roy Corinaldi.
 */
@Path("users")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    /*UsuarioResource*/
    public final static Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Crea un nuevo Usuario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     * @param usuario {@link UsuarioDTO} - EL Usuario que se desea guardar.
     * @return JSON {@link UsuarioDTO} - El Usuario guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioLogic.createUsuario(usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output: {0}", usuarioDTO);
        return usuarioDTO;
    }

    /**
     * Busca y devuelve todos los Usuarios que existen en la aplicacion.
     *
     * @return JSONArray {@link UsuarioDetailDTO} - Los Usuarios encontrados en
     * la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        LOGGER.info("UsuarioResource getUsuarios: input: void");
        List<UsuarioDetailDTO> listaUsuario = listEntity2DTO(usuarioLogic.getUsuarios());
        LOGGER.log(Level.INFO, "AuthorResource getUsuarios: output: {0}", listaUsuario);
        return listaUsuario;
    }

    /**
     * Busca el Usuario con el id asociado recibido en la URL y lo devuelve.
     *
     * @param usersId Identificador del Usuario que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link UsuarioDetailDTO} - El Usuario buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Usuario.
     */
    @GET
    @Path("{usersId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usersId") Long usersId) {
        LOGGER.log(Level.INFO, "UsuarioResource Usuario: input: {0}", usersId);
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usersId);
        if (usuarioEntity == null) {
            throw new WebApplicationException("El recurso /users/" + usersId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el Usuario con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param usersId Identificador del Usuario que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param usuario {@link UsuarioDetailDTO} El Usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO} - El Usuario guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Usuario a
     * actualizar.
     */
    @PUT
    @Path("{usersId: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("usersId") Long usersId, UsuarioDetailDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource Usuario: input: usersId: {0} , usuario: {1}", new Object[]{usersId, usuario});
        usuario.setId(usersId);
        if (usuarioLogic.getUsuario(usersId) == null) {
            throw new WebApplicationException("El recurso /users/" + usersId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioLogic.updateUsuario(usersId, usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource Usuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el Usuario con el id asociado recibido en la URL.
     *
     * @param usersId Identificador del autor que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el Usuario a borrar.
     */
    @DELETE
    @Path("{usersId: \\d+}")
    public void deleteUsuario(@PathParam("usersId") Long usersId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource Usuario: input: {0}", usersId);
        if (usuarioLogic.getUsuario(usersId) == null) {
            throw new WebApplicationException("El recurso /users/" + usersId + " no existe.", 404);
        }
        usuarioLogic.deleteUsuario(usersId);
        LOGGER.info("UsuarioResource deleteUsuario: output: void");
    }

    /**
     * Conexión con el servicio de libros para un autor.
     * {@link UsuarioTicketResource}
     *
     * Este método conecta la ruta de /users con las rutas de /tickets que
     * dependen del usuario, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los tickets.
     *
     * @param usersId El ID del usuario con respecto al cual se accede al
     * servicio.
     * @return El servicio de tickets para ese usuario en paricular.
     */
    @Path("{usersId: \\d+}/tickets")
    public Class<UsuarioTicketsResource> getUsuarioTicketResource(@PathParam("usersId") Long usersId) {
        if (usuarioLogic.getUsuario(usersId) == null) {
            throw new WebApplicationException("El recurso /users/" + usersId + " no existe.", 404);
        }
        return UsuarioTicketsResource.class;
    }

    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     */
    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

}
