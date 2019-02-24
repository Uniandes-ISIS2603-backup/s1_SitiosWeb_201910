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
import java.util.logging.Level;
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
        if (entity.getCambioSitio()== null)
        {
            throw  new BusinessLogicException("Cambio no existente");
        }
        if (entity.getSitioWeb()==null)
        {
              throw  new BusinessLogicException("Sitio no existente");
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
  
  
  public void deleteNotificacion(Long notID) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar La notificaion con id = {0}", notID);
        persistence.delete(notID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la notificacion con id = {0}", notID);
    }
  
   public NotificacionEntity updateNotificacion(Long booksId, NotificacionEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el libro con id = {0}", booksId);
        NotificacionEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el libro con id = {0}", entity.getId());
        return newEntity;
    }

}
