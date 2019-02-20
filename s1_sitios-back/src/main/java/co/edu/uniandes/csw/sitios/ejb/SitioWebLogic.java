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
           if(entity.getImagen()==null)
           {
               throw new BusinessLogicException("Imagen invalida");
           }
           return persistence.create(entity);

       }

       public SitioWebEntity getWebSite(Long id) throws  BusinessLogicException
       {
           SitioWebEntity entity = persistence.find(id);
           if(entity==null)
           {
               throw  new BusinessLogicException("No encontrado");
           }
           return  entity;

       }

    public List<SitioWebEntity> getSites() {
           List<SitioWebEntity> sites =persistence.findAll();
           return sites;
    }
}
