/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.CambioPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.ballesteros
 */
@Stateless
public class CambioLogic {
    
     @Inject
    private CambioPersistence persistence;
    
    // El metodo recibe editorial entity porque back no conoce a los DTOS
    public CambioEntity createCambio(CambioEntity cambioEntidad) throws BusinessLogicException{
        
         ///////                    Reglas De Negocio                  ////////
         
        String lugarCambio = cambioEntidad.getLugarCambio();
        
        //ip = no puede ser null
        if(lugarCambio==null){
            throw new BusinessLogicException("El lugar que se esta buscando no puede ser nulo");
        }
        
        
        //2.lugarCambio != ""
        if(lugarCambio.equals("")){
            throw new BusinessLogicException("El lugar es vacio");
        }
        
        
       String descripcion = cambioEntidad.getDescripcion();
       
        //4.descripcion != null
        if(descripcion==null){
            throw new BusinessLogicException("La descripcion es nula");
        }
        //5.descripcion != ""
        if(descripcion.equals("")){
            throw new BusinessLogicException("La descripcion es vacia");
        }
        
        Long idAsociado = cambioEntidad.getIdAsociado();
        
        //6.idAsociado != null
        if(idAsociado==null){
            throw new BusinessLogicException("El idAsociado es nulo");
        }
        /*
        //idAsociado != ""
        if(idAsociado==0){
            throw new BusinessLogicException("El idAsociado es vacia");
        }
        */
        Date fechaCambio = cambioEntidad.getFechaCambio();
        
        //9.fechaCambio != null
        if(fechaCambio==null){
            throw new BusinessLogicException("La fechaCambio no puede ser nula");
        }
        
        //10.fechaCambio != debe ser mayor a la fecha actual
        //Esto se verifica en el podam del atributo en entities
        
        String previo = cambioEntidad.getPrevio();
        
        //11.previo != null
        if(previo==null){
            throw new BusinessLogicException("Previo no puede ser nulo");
        }
        
        String nuevo = cambioEntidad.getNuevo();
        
        //12. nuevo != null
        if(nuevo==null){
            throw new BusinessLogicException("Nuevo no puede ser nulo");
        }
         
        //sitiosWeb = no puede ser null
       // SitioWebEntity sitios = cambioEntidad.getSitiosWeb();
       // if(sitios==null)
         //  {
         //   throw new BusinessLogicException("No hay sitioWeb asociado a la platafroma de Despliegue");
           //}
        
        //Invoco a la persistencia para crear a la plataforma
        persistence.create(cambioEntidad);
        return cambioEntidad;
        }
        
    
     public CambioEntity getCambio(Long id) throws  BusinessLogicException
       {
           CambioEntity entity = persistence.find(id);
           if(entity==null)
           {
               throw  new BusinessLogicException("El Cambio no encontrado");
           }
           
           return  entity;

       }
    
     public void deleteCambio(Long id) {
       
        persistence.delete(id);
    }
     
       
   public CambioEntity updateSitio(Long Id, CambioEntity cambioEntity){
        
        CambioEntity newEntity = persistence.update(cambioEntity);
        
        return newEntity;
    }
}
