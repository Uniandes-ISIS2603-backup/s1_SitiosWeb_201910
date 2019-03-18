/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity.TipoHosting;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.ballesteros
 */

@Stateless
public class PlataformaDeDespliegueLogic {
    
    @Inject
    private PlataformaDeDesplieguePersistence persistence;
    
    // El metodo recibe editorial entity porque back no conoce a los DTOS
    public PlataformaDeDespliegueEntity createPlataformaDeDespliegue(PlataformaDeDespliegueEntity plataforma) throws BusinessLogicException{
        
         ///////                    Reglas De Negocio                  ////////
        
        // ip = cumplir con los rangos de ips determinado el tipo de sistema Paas, Iaas, Saas, OnPremise
        
        
        String ip = plataforma.getIp();
        
        //ip = no puede ser null
        if(ip==null){
            throw new BusinessLogicException("La secuencia es nula");
        }
        
        //ip = no puede ser vacío
        if(ip.equals("")){
            throw new BusinessLogicException("La secuencia es vacia");
        }
        
        
        //ip = cumplir con la estructura #.#.#.# 
       
       if(ip.codePointAt(3)!=('.')||ip.codePointAt(7)!=('.')||ip.codePointAt(11)!=('.')){
         throw new BusinessLogicException("No hay congruencia en la cantidad de puntos que separan una ip: #.#.#.#");
        }
        
        //ip = la longitud del numero no debe ser mayor a 12
        // regla futura
       
        //cpu = no puede ser nulo
        String cpu = plataforma.getCpu();
        if(cpu==null){
            throw new BusinessLogicException("La cpu es nula");
        }
        //cpu = no puede ser vacío
        if(cpu.equals("")){
            throw new BusinessLogicException("La cpu es vacia");
        }
        
        //cpu = no puede ser nulo
        String clock = plataforma.getClock();
        if(clock==null){
            throw new BusinessLogicException("El clock es nula");
        }
        //cpu = no puede ser vacío
        if(clock.equals("")){
            throw new BusinessLogicException("El clock es vacia");
        }
        //clock = mayor a 0 {"Hz","Hertz","KHz", "Kilo Hertz", "Mega Hertz", "GigaHertz", "Tera Hertz","Peta Hertz", "Hexa Hertz", "Zetta Hertz", "Yotta Hertz", "MHz", "THz","GHz","PHz","HHz","ZHz"};
        
        
        
        
        TipoHosting host = plataforma.getHosting();
        //hosting = no puede ser nulo
        if(host==null){
            throw new BusinessLogicException("El hosting no puede ser nulo");
        }
        
        //hosting = hosting contenido en las enumeraciones
        if(!host.equals(TipoHosting.IAAS)&&!host.equals(TipoHosting.ONPREMISE)&&!host.equals(TipoHosting.PAAS)&&!host.equals(TipoHosting.SAAS)){
            throw new BusinessLogicException("No pertenece a los tipo hosting permitidos");
        }
        
        //sitiosWeb = no puede ser null
        SitioWebEntity sitios =(SitioWebEntity) plataforma.getSitiosWeb() ;
        if(plataforma.getSitiosWeb()==null)
           {
            throw new BusinessLogicException("No hay sitioWeb asociado a la platafroma de Despliegue");
           }
        
        //Invoco a la persistencia para crear a la plataforma
        persistence.create(plataforma);
        return plataforma;
        }
        
    
     public PlataformaDeDespliegueEntity getPlataformaDeDespliegue(Long id) throws  BusinessLogicException
       {
           PlataformaDeDespliegueEntity entity = persistence.find(id);
           if(entity==null)
           {
               throw  new BusinessLogicException("la PlatafromaDeDespliegue no encontrado");
           }
           
           return  entity;

       }
}

