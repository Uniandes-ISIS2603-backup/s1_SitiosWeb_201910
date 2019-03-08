/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
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
public class AdministradorCambiosLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorCambiosLogic.class.getName());

    @Inject
    private AdministradorPersistence adminPersistence;

    @Inject
    private CambioPersistence cambioPersistence;

    /**
     * Se encarga de crear un cambio relacionado con un administrador.
     *
     * @param adminsId Objeto de id con los datos
     * nuevos
     * @param cambioId
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID
     *
     * .
     */
    public CambioEntity addCambio(Long adminsId, Long cambioId) {
        AdministradorEntity ae = adminPersistence.find(adminsId);
        CambioEntity ne = cambioPersistence.find(cambioId);
        ae.getCambios().add(ne);
        return cambioPersistence.create(ne);
    }
    
    /**
     * Obtiene una colección de instancias de CambioEntity asociadas a una
     * instancia de admin
     *
     * @param adminsId Identificador de la instancia de admin
     * @return Colección de instancias de CambioEntity asociadas a la
     * instancia de admin
     */
    public List<CambioEntity> getCambios(Long adminsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los cambios del admin con id = {0}", adminsId);
        return adminPersistence.find(adminsId).getCambios();
    }
    
    /**
     * Obtiene una instancia de NotificacionEntity asociada a una instancia de
     * Administrador
     *
     * @param adminsId Identificador de la instancia de Administrador
     * @param cambioId Identificador de la instancia de Notificacion
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el cambio no está asociado al admin
     */
    public CambioEntity getCambio(Long adminsId, Long cambioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Empieza proceso de consultar el cambio con id = {0} del admin con id = " + adminsId, cambioId);
        List<CambioEntity> cambiosE = adminPersistence.find(adminsId).getCambios();
        CambioEntity cambioEntity = cambioPersistence.find(cambioId);
        int index = cambiosE.indexOf(cambioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cambio con id = {0} del admin con id = " + adminsId, cambioId);
        if (index >= 0) {
            return cambiosE.get(index);
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
    public List<CambioEntity> replaceNotificaciones(Long adminId, List<CambioEntity> cambios) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los cambios asocidos al administrador con id = {0}", adminId);
        AdministradorEntity adminEntity = adminPersistence.find(adminId);
        List<CambioEntity> cambiosE = cambioPersistence.findAll();
        adminEntity.setCambios(cambios);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los cambios asocidos al admin con id = {0}", adminId);
        return adminEntity.getCambios();
    }
    
    /**
     * Desasocia una Notificacion existente de un Administrador existente
     *
     * @param adminId Identificador de la instancia de Admin
     * @param cambioId Identificador de la instancia de Notificacion
     */
    public void removeNotificacion(Long adminId, Long cambioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un cambio del admin con id = {0}", adminId);
        AdministradorEntity adminEntity = adminPersistence.find(adminId);
        CambioEntity cambioEntity = cambioPersistence.find(cambioId);
        adminEntity.getCambios().remove(cambioEntity);
        //cambioPersistence.delete(notiEntity.getId());
        //TODO checkear si deberia eliminar eso.
        LOGGER.log(Level.INFO, "Termina proceso de borrar un cambio del administrador con id = {0}", adminId);
    }

}
