/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class SitioWebPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(SitioWebPersistence.class.getName());
    
    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;
     
    public SitioWebEntity create(SitioWebEntity websiteEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo sitio web");
        em.persist(websiteEntity);//error ocurriendo aca?
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo sitio web");
        return websiteEntity;
    }

    public List<SitioWebEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los sitios web");
        TypedQuery query = em.createQuery("select u from SitioWebEntity u",SitioWebEntity.class);
        return query.getResultList();
    }

    public SitioWebEntity find(Long websiteID)
    {
        LOGGER.log(Level.INFO, "Consultando el sitioweb con id={0}", websiteID);
        return em.find(SitioWebEntity.class,websiteID);
    }

    public SitioWebEntity update(SitioWebEntity sitioWebEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando el sitio web con id={0}", sitioWebEntity.getId());

        return em.merge(sitioWebEntity);

    }

    public void delete(Long websiteid)
    {
        LOGGER.log(Level.INFO, "Borrando el sitio web con id={0}", websiteid);
        SitioWebEntity website= em.find(SitioWebEntity.class,websiteid);
        em.remove(website);
      
    }


}
