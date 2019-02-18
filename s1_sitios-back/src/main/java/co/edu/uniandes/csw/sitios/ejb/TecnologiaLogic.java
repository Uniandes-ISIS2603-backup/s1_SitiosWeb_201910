/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
 @Stateless
public class TecnologiaLogic {
     
     private static final Logger LOGGER = Logger.getLogger(TecnologiaLogic.class.getName());
     
       @Inject
       private TecnologiaPersistence persistence;
     
     public TecnologiaEntity createTechnology(TecnologiaEntity technologyEntity)
     {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la tecnología");
        TecnologiaEntity newTecnologiaEntity = persistence.create(technologyEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la tecnología");
        return newTecnologiaEntity;
     }
    
}
