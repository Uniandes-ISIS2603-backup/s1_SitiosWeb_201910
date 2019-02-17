/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class NotificacionPersistence {
     private static final Logger LOGGER = Logger.getLogger(NotificacionPersistence.class.getName());
     
     @PersistenceContext(unitName = "sitiosPU")
     protected EntityManager em;


        public NotificacionEntity create(NotificacionEntity editorialEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo notificacion");
        em.persist(editorialEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo notificacion");
        return editorialEntity;
    }

    public List<NotificacionEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas las notificaciones");
        TypedQuery query = em.createQuery("select u from NotificacionEntity u",NotificacionEntity.class);
        return query.getResultList();
    }

    public NotificacionEntity find(Long notificationID)
    {
        LOGGER.log(Level.INFO, "Consultando el sitioweb con id={0}", notificationID);
        return em.find(NotificacionEntity.class,notificationID);
    }

    public NotificacionEntity update(NotificacionEntity notificationEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando el notificacion con id={0}", notificationEntity.getId());

        return     em.merge(notificationEntity);

    }

    public void delete(Long notificationID)
    {
        LOGGER.log(Level.INFO, "Borrando el notificacion con id={0}", notificationID);
        NotificacionEntity noti= em.find(NotificacionEntity.class,notificationID);
        em.remove(noti);
    }
    
}
