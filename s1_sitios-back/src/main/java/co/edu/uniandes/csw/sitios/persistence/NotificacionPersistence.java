/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class NotificacionPersistence {
     private static final Logger LOGGER = Logger.getLogger(NotificacionPersistence.class.getName());
     
     @PersistenceContext(unitName = "sitiosPU")
     protected EntityManager em;
     
     
     public NotificacionEntity create(NotificacionEntity notificacionEntity){
         em.persist(notificacionEntity);
         return notificacionEntity;
     }
     
     
    
}
