/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.NotificacionPersistence;

import java.util.List;
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
    
    
    
    public NotificacionEntity createNotification(NotificacionEntity entity)throws  BusinessLogicException{
        if (entity.getNotificado() == null)
        {
            throw  new BusinessLogicException("Notificado no existente");
        }

        return persistence.create(entity);
    }

    public NotificacionEntity getNotificacion(Long id) throws  BusinessLogicException{

        NotificacionEntity entity = persistence.find(id);
        if(entity==null)
        {
            throw  new BusinessLogicException("No encontrado");
        }
        return  entity;
    }

    public List<NotificacionEntity> getAll()throws  BusinessLogicException
    {
        List<NotificacionEntity> sites =persistence.findAll();
        return sites;
    }

}
