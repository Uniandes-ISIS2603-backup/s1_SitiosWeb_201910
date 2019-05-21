/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para Usuario.
 *
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;

    /**
     * Se encarga de crear un Usuario en la base de datos.
     *
     * @param usuarioEntity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        UsuarioEntity newUsuarioEntity = persistence.create(usuarioEntity);
        verificarReglasDeNegocio(newUsuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return newUsuarioEntity;
    }

    /**
     * Obtiene la lista de los registros de Usuario.
     *
     * @return Colección de objetos de UsuarioEntity.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los usuarios");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID.
     *
     * @param usuariosId Identificador de la instancia a consultar
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     */
    public UsuarioEntity getUsuario(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = persistence.find(usuariosId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El sitio web con el usuario de id = {0} no existe", usuariosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", usuariosId);
        return usuarioEntity;
    }

    /**
     * Actualiza la información de una instancia de Usuario.
     *
     * @param usuariosId Identificador de la instancia a actualizar
     * @param usuarioEntity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de UsuarioEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException
     */
    public UsuarioEntity updateUsuario(Long usuariosId, UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuariosId);
        UsuarioEntity newUsuarioEntity = persistence.update(usuarioEntity);
        verificarReglasDeNegocio(newUsuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuariosId);
        return newUsuarioEntity;
    }

    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param usuariosId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el usuario tiene libros asociados.
     * --------------------------
     */
    public void deleteUsuario(Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario con id = {0}", usuariosId);
        List<TicketEntity> tickets = getUsuario(usuariosId).getTickets();
        if (tickets != null && !tickets.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el autor con id = " + usuariosId + " porque tiene books asociados");
        }
        persistence.delete(usuariosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el usuario con id = {0}", usuariosId);
    }

    private void verificarReglasDeNegocio(UsuarioEntity entity) throws BusinessLogicException {
        if (entity.getId() < 0) {
            throw new BusinessLogicException("Id no puede ser negativo");
        }
        if (entity.getPassword().length() > 15 && entity.getPassword().length() < 7 ) {
            throw new BusinessLogicException("La contrasenia no debe contener más de 15 caracteres");
        }
        if (entity.getEmail() == null || entity.getEmail().equals("")) {
            throw new BusinessLogicException("El email no puede estar vacio");
        }
        if (entity.getNombre() == null || entity.getNombre().equals("")) {
            throw new BusinessLogicException("El nombre no puede estar vacio");
        }
        if (!entity.getEmail().matches("^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$")) {
            throw new BusinessLogicException("Email invalida");
        }
        if (entity.getTelefono().trim().length() < 7 && entity.getTelefono().trim().length() > 11) {
            throw new BusinessLogicException("Numero de telefono demasiado largo");
        }
        if (entity.getNumeroTickets() < 0 ) {
            throw new BusinessLogicException("Invalido el numero de tickets");
        }
    }
}
