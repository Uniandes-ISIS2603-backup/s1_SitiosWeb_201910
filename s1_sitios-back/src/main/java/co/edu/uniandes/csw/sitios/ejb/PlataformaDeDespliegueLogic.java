/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
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
         
        // 1.No pueden haber plataformas repetidas
        if(persistence.findByIp(plataforma.getIp())!=null){
            throw new BusinessLogicException("Ya existe una plataforma de despliegue con la ip identificada por \'"+plataforma.getIp()+"\'");
        }
        
        //Invoco a la persistencia para crear a la plataforma
        persistence.create(plataforma);
        return plataforma;
    }
    
}
