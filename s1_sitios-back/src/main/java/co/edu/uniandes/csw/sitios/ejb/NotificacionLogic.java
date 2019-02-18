/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class NotificacionLogic {
    private static final Logger LOGGER = Logger.getLogger(NotificacionPersistence.class.getName());
    
    @Inject
    private NotificacionPersistence persistence;
    
    
    
    public NotificacionEntity createNotification(NotificacionEntity entity)
    {
        return entity;
    }
}
