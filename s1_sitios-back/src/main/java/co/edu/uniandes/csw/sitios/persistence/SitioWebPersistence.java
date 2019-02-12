/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class SitioWebPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(SitioWebPersistence.class.getName());
    
     @PersistenceContext(unitName = "sitesPU")
    protected EntityManager em;
     
    public SitioWebEntity create(SitioWebEntity editorialEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo sitio web");
      em.persist(editorialEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo sitio web");
        return editorialEntity;
    }

}
