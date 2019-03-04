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
           if(entity.getHistorialDeEstados()==null)
           {
               throw new BusinessLogicException("Historial inexistente");
           }
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
           if(entity.getDescripcion().length()<20)
           {
               throw new BusinessLogicException("Descripcion demaciado corta");
           }
           if(entity.getAudienciaEsperada()<0)
           {
              throw new BusinessLogicException("Audiencia esperada no puede tener un valor negativo");
           }
           if(entity.getCategoriaSitio()==null)
           {
              throw new BusinessLogicException("Categoria de sitio no asignada");
           }
           if(entity.getSitiosRelacionados()==null)
           {
              throw new BusinessLogicException("Lista de sitios relacionados es inexistente");
           }
           
           if(entity.getAdministradores()==null)
           {
                throw new BusinessLogicException("La lista de solicitantes no se existe");
           }
           if(entity.getTechnologies()==null)
           {
               throw new BusinessLogicException("La lista de tecnologias de desarrollo no existe");
           }
           if(!entity.getImagen().matches("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)"))
            {
              throw new BusinessLogicException("La ruta de la imagen es incorrecta");
            }
           if(entity.getPlataformaDeDespliegue()==null)
           {
            throw new BusinessLogicException("No hay plataforma de despliegue asignada");
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
         LOGGER.log(Level.INFO, "Inicia proceso de obtencion de lista de sitios");
         List<SitioWebEntity> sites =persistence.findAll();
         LOGGER.log(Level.INFO, "Inicia proceso de obtencion de lista de sitios");
         return sites;
    }
    
     public void deleteSite(Long notID) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el sitio con id = {0}", notID);
        persistence.delete(notID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el sitio con id = {0}", notID);
    }
     
       
   public SitioWebEntity updateSitio(Long booksId, SitioWebEntity siteEntity){
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el sitio con id = {0}", booksId);
        SitioWebEntity newEntity = persistence.update(siteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el sitio con id = {0}", siteEntity.getId());
        return newEntity;
    }
     
     
}
