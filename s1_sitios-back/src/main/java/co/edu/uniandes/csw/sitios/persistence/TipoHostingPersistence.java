/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.TipoHostingEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.ballesteros
 */
@Stateless
public class TipoHostingPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TipoHostingPersistence.class.getName());
    
    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    public TipoHostingEntity create(TipoHostingEntity tipoHostingEntity) {
        LOGGER.log(Level.INFO, "Creando un tipo hosting nuevo");
        em.persist(tipoHostingEntity);
        LOGGER.log(Level.INFO, "Tipo Hosting creado");
        return tipoHostingEntity;
    }

    public TipoHostingEntity find(Long tipoHostingId) {
        LOGGER.log(Level.INFO, "Consultando el TipoHosting con id={0}", tipoHostingId);
        return em.find(TipoHostingEntity.class, tipoHostingId);
    }

    public List<TipoHostingEntity> findAll() {
         LOGGER.log(Level.INFO, "Consultando todos los TipoHosting");
        TypedQuery<TipoHostingEntity> query = em.createQuery("select u from TipoHostingEntity u", TipoHostingEntity.class);
        return query.getResultList();
    }
    
    public TipoHostingEntity update(TipoHostingEntity tipoHostingEntity) {
        LOGGER.log(Level.INFO, "Actualizando el tipoHosting con id={0}", tipoHostingEntity.getId());
        return em.merge(tipoHostingEntity);
    }
    
        public void delete(Long tipoHostingId) {
        LOGGER.log(Level.INFO, "Borrando el TipoHosting con id={0}", tipoHostingId);
        TipoHostingEntity tipoHostingEntity = em.find(TipoHostingEntity.class, tipoHostingId);
        em.remove(tipoHostingEntity);
    }
    
    
    
}
