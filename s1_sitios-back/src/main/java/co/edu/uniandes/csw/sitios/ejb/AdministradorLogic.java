/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Stateless
public class AdministradorLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence;
    
    @Inject
    private NotificacionPersistence notiPer;

    /**
     * Se encarga de crear un Administrador en la base de datos.
     *
     * @param administradorEntity Objeto de AdministradorEntity con los datos
     * nuevos
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        AdministradorEntity newAdministradorEntity = persistence.create(administradorEntity);
        verificarReglasDeNegocio(newAdministradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del administrador");
        return newAdministradorEntity;
    }

    /**
     * Obtiene la lista de los registros de Administrador.
     *
     * @return Colección de objetos de AdministradorEntity.
     */
    public List<AdministradorEntity> getAdministradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradores");
        List<AdministradorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID.
     *
     * @param AdministradoresId Identificador de la instancia a consultar
     * @return Instancia de AdministradorEntity con los datos del Author
     * consultado.
     */
    public AdministradorEntity getAdministrador(Long administradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", administradorId);
        AdministradorEntity administradorEntity = persistence.find(administradorId);
        if (administradorEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", administradorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el administrador con id = {0}", administradorId);
        return administradorEntity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param administradorId Identificador de la instancia a actualizar
     * @param administradorEntity Instancia de AdministradorEntity con los
     * nuevos datos.
     * @return Instancia de AdministradorEntity con los datos actualizados.
     */
    public AdministradorEntity updateAdministrador(Long administradorId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el administrador con id = {0}", administradorId);
        AdministradorEntity newAdministradorEntity = persistence.update(administradorEntity);
        verificarReglasDeNegocio(newAdministradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Administrador con id = {0}", administradorId);
        return newAdministradorEntity;
    }

    /**
     * Elimina una instancia de administrador de la base de datos.
     *
     * @param administradoresId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteAdministrador(Long administradoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", administradoresId);
        List<NotificacionEntity> notis = getAdministrador(administradoresId).getNotificaciones();
        for( NotificacionEntity noti : notis ){
            notiPer.delete(noti.getId());
        }
        persistence.delete(administradoresId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradoresId);
    }

    private void verificarReglasDeNegocio(AdministradorEntity entity) throws BusinessLogicException {
        if (entity.getId() < 0 ) {
            throw new BusinessLogicException("Id no puede ser negativo");
        }
        if (entity.getPassword().length() <= 15) {
            throw new BusinessLogicException("La contrasenia debe contener más de 15 caracteres");
        }
        if (entity.getEmail()== null || entity.getEmail().equals("")) {
            throw new BusinessLogicException("El email no puede estar vacio");
        }
        if (entity.getNombre()== null || entity.getNombre().equals("")) {
            throw new BusinessLogicException("El nombre no puede estar vacio");
        }
        if (entity.getNombreCargo()== null || entity.getNombreCargo().equals("")) {
            throw new BusinessLogicException("El nombre del cargo no puede estar vacia");
        }
        if(!entity.getEmail().matches("^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$")){
            throw new BusinessLogicException("Email invalida");
        }
        if(entity.getNivel() <= 0 ){
            throw new BusinessLogicException("Nivel no puede ser negativo ni cero");
        }
        if(entity.getTelefono().trim().length() < 7 && entity.getTelefono().trim().length()> 11 ){
            throw new BusinessLogicException("Numero de telefono demasiado largo");
        }
        if(entity.getNotificaciones()== null ){
            throw new BusinessLogicException("No existen notificaciones");
        }
    }
}
