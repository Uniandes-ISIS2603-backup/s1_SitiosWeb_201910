/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para los EstadosWeb, se conecta mediante 
 * entity manager de javax.persistence con la base de datos
 * 
 * @author Daniel Preciado
 */
@Stateless
public class EstadoWebPersistence {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    
    private static final Logger LOGGER = Logger.getLogger(EstadoWebPersistence.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    /**
     * manejador de entidades
     */
     @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;
     
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
     
     /**
     * Método para persisitir un estado web en la DB
     *
     * @param pEstadoWebEntity objeto de tipo estado web que se persistira en la DB
     * @return pEstadoWebEntity con un id generado por la DB
     */
    public EstadoWebEntity create(EstadoWebEntity pEstadoWebEntity) 
    {
        LOGGER.log(Level.INFO, "Creando un estadoWeb nuevo");
        em.persist(pEstadoWebEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un estadoWeb nuevo");
        return pEstadoWebEntity;
    }

    /**
     * Retorna todos los estados web que hay en la DB
     *
     * @return una lista con los estados web  que encuentre en la DB
     */
    public List<EstadoWebEntity> findAll() 
    {
        LOGGER.log(Level.INFO, "Consultando todos los estados web");
        // Se crea un query para buscar todas los estados web en la DB.
        TypedQuery query = em.createQuery("select u from EstadoWebEntity u", EstadoWebEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun estadoWeb con el id que se envía por parametro
     *
     * @param estadoWebId: id que corresponde al estado web buscado.
     * @return un estado Web.
     */
    public EstadoWebEntity find(Long estadoWebId) 
    {
        LOGGER.log(Level.INFO, "Consultando estadoWeb con id={0}", estadoWebId);
        return em.find(EstadoWebEntity.class, estadoWebId);
    }

    /**
     * Actualiza un estado Web en la DB.
     *
     * @param pEstadoWebEntity: el estado web con sus respectivos updates.
     * @return un estado Web con los updates realizados.
     */
    public EstadoWebEntity update(EstadoWebEntity pEstadoWebEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando un estado Web con id = {0}", pEstadoWebEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar un estado Web con id = {0}", pEstadoWebEntity.getId());
        return em.merge(pEstadoWebEntity);
    }

    /**
     * Borra un estado Web de la DB identificandolo mediante su id
     *
     * @param estadoWebId: id correspondiente al estado Web a borrar.
     */
    public void delete(Long estadoWebId) 
    {
        LOGGER.log(Level.INFO, "Borrando un estado Web con id = {0}", estadoWebId);
        // Se hace uso de mismo método que esta explicado en public EstadoWebEntity find(Long id) para obtener la editorial a borrar.
        EstadoWebEntity entity = em.find(EstadoWebEntity.class, estadoWebId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar un estado Web con id = {0}", estadoWebId);
    }
    
}
