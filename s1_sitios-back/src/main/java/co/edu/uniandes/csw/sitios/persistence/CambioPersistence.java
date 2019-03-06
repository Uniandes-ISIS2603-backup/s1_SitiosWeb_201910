/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
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
public class CambioPersistence {
    
       private static final Logger LOGGER = Logger.getLogger(CambioPersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    public CambioEntity create(CambioEntity plataformaDeDespliegueEntity) {
        LOGGER.log(Level.INFO, "Creando un Cambio nuevo");
        em.persist(plataformaDeDespliegueEntity);
        LOGGER.log(Level.INFO, "Cambio Creado");
        return plataformaDeDespliegueEntity;
    }

    public CambioEntity find(Long cambioId) {
        LOGGER.log(Level.INFO, "Consultando el cambio con id={0}", cambioId);
        return em.find(CambioEntity.class, cambioId);
    }

    // Busque en la tabla ----Entity un e(objeto o registro) donde el nombre es igual a una ip pasada por parametro
    public CambioEntity findByIp(String ip){
            TypedQuery<CambioEntity> query = em.createQuery("Select e from CambioEntity e where e.name = :name",CambioEntity.class);
            // Aca le asigno que significa param param=ip
            query = query.setParameter("name", ip);
            //Hago una lista que me devuelve la lista del resultado
            List<CambioEntity> sameParam = query.getResultList();
           
            CambioEntity result ;
            // LISTA NULA
            if(sameParam == null){
                result = null;
            } // LSITA VACIA
            else if(sameParam.isEmpty()){
                result = null;
            } //EL PRIMER ELEMENTO 
            else {
                result = sameParam.get(0);
            }
            return result;
    }
    public List<CambioEntity> findAll() {
       
        LOGGER.log(Level.INFO, "Consultando todos los Cambios");
        
        TypedQuery<CambioEntity> query = em.createQuery("select u from CambioEntity u",CambioEntity.class);
        return query.getResultList();
    }
    
     public void delete(Long cambioId) {
        LOGGER.log(Level.INFO, "Borrando el Cambio con id={0}", cambioId);
        CambioEntity plataformaDeDespliegueEntity = em.find(CambioEntity.class, cambioId);
        em.remove(plataformaDeDespliegueEntity);
    }
    
     public CambioEntity update(CambioEntity cambioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el Cambio con id={0}", cambioEntity.getId());
        return em.merge(cambioEntity);
    }
     
     
    
    
    
}
