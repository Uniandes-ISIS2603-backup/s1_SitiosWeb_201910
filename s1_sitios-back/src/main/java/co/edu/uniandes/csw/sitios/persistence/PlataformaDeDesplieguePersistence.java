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
        LOGGER.log(Level.INFO, "Plataforma Creada");
        return plataformaDeDespliegueEntity;
    }

    public PlataformaDeDespliegueEntity find(Long plataformaDeDespliegueId) {
        LOGGER.log(Level.INFO, "Consultando la plataforma con id={0}", plataformaDeDespliegueId);
        return em.find(PlataformaDeDespliegueEntity.class, plataformaDeDespliegueId);
    }

    // Busque en la tabla ----Entity un e(objeto o registro) donde el nombre es igual a una ip pasada por parametro
    public PlataformaDeDespliegueEntity findByIp(String ip){
            TypedQuery<PlataformaDeDespliegueEntity> query = em.createQuery("Select e from PlataformaDeDespliegueEntity e where e.name = :name",PlataformaDeDespliegueEntity.class);
            // Aca le asigno que significa param param=ip
            query = query.setParameter("name", ip);
            //Hago una lista que me devuelve la lista del resultado
            List<PlataformaDeDespliegueEntity> sameParam = query.getResultList();
           
            PlataformaDeDespliegueEntity result ;
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
    public List<PlataformaDeDespliegueEntity> findAll() {
       
        LOGGER.log(Level.INFO, "Consultando todos las PlataformasDeDespliegue");
        
        TypedQuery<PlataformaDeDespliegueEntity> query = em.createQuery("select u from PlataformaDeDespliegueEntity u",PlataformaDeDespliegueEntity.class);
        return query.getResultList();
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
