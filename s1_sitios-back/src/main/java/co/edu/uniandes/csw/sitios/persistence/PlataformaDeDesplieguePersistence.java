/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.ballesteros
 */
@Stateless
public class PlataformaDeDesplieguePersistence {
    
        private static final Logger LOGGER = Logger.getLogger(PlataformaDeDesplieguePersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    public PlataformaDeDespliegueEntity create(PlataformaDeDespliegueEntity plataformaDeDespliegueEntity) {
        LOGGER.log(Level.INFO, "Creando una PlataformaDeDespliegue nueva");
        em.persist(plataformaDeDespliegueEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return plataformaDeDespliegueEntity;
    }

    public PlataformaDeDespliegueEntity find(Long plataformaDeDespliegueId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", plataformaDeDespliegueId);
        return em.find(PlataformaDeDespliegueEntity.class, plataformaDeDespliegueId);
    }

    public List<PlataformaDeDespliegueEntity> findAll() {
       
        LOGGER.log(Level.INFO, "Consultando todos las PlataformasDeDespliegue");
        
        Query q;
            q = em.createQuery("select u from PlataformaDeDespliegueEntity u");
        return q.getResultList();
    }
    
     public void delete(Long plataformaDeDespliegueId) {
        LOGGER.log(Level.INFO, "Borrando la PlataformaDeDespliegue con id={0}", plataformaDeDespliegueId);
        PlataformaDeDespliegueEntity plataformaDeDespliegueEntity = em.find(PlataformaDeDespliegueEntity.class, plataformaDeDespliegueId);
        em.remove(plataformaDeDespliegueEntity);
    }
    
     public PlataformaDeDespliegueEntity update(PlataformaDeDespliegueEntity plataformaDeDespliegueEntity) {
        LOGGER.log(Level.INFO, "Actualizando la PlataformaDeDespliegue con id={0}", plataformaDeDespliegueEntity.getId());
        return em.merge(plataformaDeDespliegueEntity);
    }
     
     
}
