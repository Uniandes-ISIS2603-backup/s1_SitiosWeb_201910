/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author estudiante
 */
@Stateless
public class AdministradorPersistence {
    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());
    
    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param administradorEntity objeto administrador que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Creando un administrador nuevo");
        em.persist(administradorEntity);
        LOGGER.log(Level.INFO, "Administrador creado");
        
        return administradorEntity;
    }

    /**
     * Busca si hay algun administrador con el id que se envía de argumento
     *
     * @param administradorId: id correspondiente al administrador buscado.
     * @return un libro.
     */
    public AdministradorEntity find(Long administradorId) {
        LOGGER.log(Level.INFO, "Consultando el administrador con id={0}", administradorId);
        return em.find(AdministradorEntity.class, administradorId);
    }
    
    /**
     * Devuelve todos los administradores de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from AdministradorEntity u" es como un "select * from AdministradorEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los administradores");
        Query q = em.createQuery("select u from AdministradorEntity u");
        return q.getResultList();
    }

    /**
     * Actualiza un administrador.
     *
     * @param administradorEntity: el administrador que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un administrador con los cambios aplicados.
     */
    public AdministradorEntity update(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el administrador con id={0}", administradorEntity.getId());
        return em.merge(administradorEntity);
    }

    /**
     *
     * Borra un administrador de la base de datos recibiendo como argumento el id del
     * administrador
     *
     * @param administradorId: id correspondiente al administrador a borrar.
     */
    public void delete(Long administradorId) {
        LOGGER.log(Level.INFO, "Borrando el administrador con id={0}", administradorId);
        AdministradorEntity administradorEntity = em.find(AdministradorEntity.class, administradorId);
        em.remove(administradorEntity);
    }
    
}
