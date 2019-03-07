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
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class AdministradorNotificacionesLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorNotificacionesLogic.class.getName());

    @Inject
    private AdministradorPersistence adminPersistence;

    @Inject
    private NotificacionPersistence notificacionPersistence;

    /**
     * 
     * @param adminsId
     * @param notificationsId
     * @return 
     */
    public NotificacionEntity addNotificacion(Long adminsId, Long notificationsId) {
        AdministradorEntity ae = adminPersistence.find(adminsId);
        NotificacionEntity ne = notificacionPersistence.find(notificationsId);
        ae.getNotificaciones().add(ne);
        return notificacionPersistence.create(ne);
    }

    /**
     * Obtiene una colección de instancias de NotificacionEntity asociadas a una
     * instancia de admin
     *
     * @param adminsId Identificador de la instancia de admin
     * @return Colección de instancias de NotificacionEntity asociadas a la
     * instancia de admin
     */
    public List<NotificacionEntity> getNotificaciones(Long adminsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las notificaciones del admin con id = {0}", adminsId);
        return adminPersistence.find(adminsId).getNotificaciones();
    }

    /**
     * Obtiene una instancia de NotificacionEntity asociada a una instancia de
     * Administrador
     *
     * @param adminsId Identificador de la instancia de Administrador
     * @param notificationsId Identificador de la instancia de Notificacion
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si la notificacion no está asociado al admin
     */
    public NotificacionEntity getNotificacion(Long adminsId, Long notificationsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Empieza proceso de consultar la notificacion con id = {0} del admin con id = " + adminsId, notificationsId);
        List<NotificacionEntity> notificacions = adminPersistence.find(adminsId).getNotificaciones();
        NotificacionEntity notificacionEntity = notificacionPersistence.find(notificationsId);
        int index = notificacions.indexOf(notificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la notificacion con id = {0} del admin con id = " + adminsId, notificationsId);
        if (index >= 0) {
            return notificacions.get(index);
        }
        throw new BusinessLogicException("La notificacion no está asociado al admin");
    }
    
    /**
     * Remplaza las instancias de notificacion asociadas a una instancia de admin
     *
     * @param adminId Identificador de la instancia de admin
     * @param notis Colección de instancias de BookEntity a asociar a instancia
     * de admin
     * @return Nueva colección de NotificacionEntity asociada a la instancia de Admin
     */
    public List<NotificacionEntity> replaceNotificaciones(Long adminId, List<NotificacionEntity> notis) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar las notificaciones asocidos al administrador con id = {0}", adminId);
        AdministradorEntity adminEntity = adminPersistence.find(adminId);
        List<NotificacionEntity> notiList = notificacionPersistence.findAll();
        adminEntity.setNotificaciones(notis);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las notificaciones asocidos al admin con id = {0}", adminId);
        return adminEntity.getNotificaciones();
    }
    
    /**
     * Desasocia una Notificacion existente de un Administrador existente
     *
     * @param adminId Identificador de la instancia de Admin
     * @param notiId Identificador de la instancia de Notificacion
     */
    public void removeNotificacion(Long adminId, Long notiId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una notificacion del admin con id = {0}", adminId);
        AdministradorEntity adminEntity = adminPersistence.find(adminId);
        NotificacionEntity notiEntity = notificacionPersistence.find(notiId);
        adminEntity.getNotificaciones().remove(notiEntity);
        //notificacionPersistence.remove(notiEntity.getId());
        //TODO checkear si deberia eliminar eso.
        LOGGER.log(Level.INFO, "Termina proceso de borrar una notificacion del administrador con id = {0}", adminId);
    }

}
