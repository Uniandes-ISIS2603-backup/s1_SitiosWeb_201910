/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless //Ud es un EJB. Ud no va a manejar estados.
public class DependenciaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DependenciaPersistence.class.getName());
    
    @PersistenceContext(unitName = "sitiosPU") //Cambiar
    protected EntityManager em; //Manda en la base de datos, decide que va o que no o que se elimina.
    
    public DependenciaEntity create( DependenciaEntity dependenciaEntity ){
        em.persist(dependenciaEntity);
        return dependenciaEntity;
    }
}
