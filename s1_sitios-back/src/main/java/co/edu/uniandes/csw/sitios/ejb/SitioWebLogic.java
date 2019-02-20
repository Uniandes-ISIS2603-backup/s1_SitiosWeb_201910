/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;

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
public class SitioWebLogic {
       private static final Logger LOGGER = Logger.getLogger(SitioWebPersistence.class.getName());
       
       @Inject
       private SitioWebPersistence persistence;
       
       public SitioWebEntity createWebSite(SitioWebEntity entity) throws BusinessLogicException
       {
           if(entity.getNombre()==null)
           {
               throw new BusinessLogicException("Nombre inexistente");
           }
           if(entity.getNombre().equals(""))
           {
               throw new BusinessLogicException("Nombre vacio");
           }
            if(entity.getDescripcion()==null)
           {
               throw new BusinessLogicException("Descripcion inexistente");
           }
           if(entity.getDescripcion().equals(""))
           {
               throw new BusinessLogicException("Descripcion vacio");
           } 
           if(entity.getDescripcion().length()<20)
           {
               throw new BusinessLogicException("Descripcion demaciado corta");
           }
           
           return persistence.create(entity);

       }

       public SitioWebEntity getWebSite(Long id) throws  BusinessLogicException
       {
           SitioWebEntity entity = persistence.find(id);
           if(entity==null)
           {
               throw  new BusinessLogicException("Sitio Web no encontrado");
           }
           
           return  entity;

       }

    public List<SitioWebEntity> getSites() {
           List<SitioWebEntity> sites =persistence.findAll();
           return sites;
    }
    
     public void deleteNotificacion(Long notID) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el sitio con id = {0}", notID);
        persistence.delete(notID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el sitio con id = {0}", notID);
    }
     
       
   public SitioWebEntity updateSitio(Long booksId, SitioWebEntity siteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el sitio con id = {0}", booksId);
        SitioWebEntity newEntity = persistence.update(siteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el sitio con id = {0}", siteEntity.getId());
        return newEntity;
    }
     
     
}
