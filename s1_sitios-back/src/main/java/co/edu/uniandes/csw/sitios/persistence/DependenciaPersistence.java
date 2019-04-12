/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
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
 * @author estudiante
 */
@Stateless
public class DependenciaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DependenciaPersistence.class.getName());
    
    @PersistenceContext(unitName = "sitiosPU") 
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param dependenciaEntity objeto organizacion que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DependenciaEntity create(DependenciaEntity dependenciaEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva dependencia");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la organizacion en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(dependenciaEntity);
        LOGGER.log(Level.INFO, "Creando una nueva dependencia");
        return dependenciaEntity;
    }

    /**
     * Devuelve todas las dependencias de la base de datos.
     *
     * @return una lista con todas las dependencias que encuentre en la base
     * de datos, "select u from DependenciaEntity u" es como un "select * from
     * DependenciaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DependenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las dependencias");
        Query q = em.createQuery("select u from DependenciaEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay alguna dependencia con el id que se envía de argumento
     *
     *
     * @param dependenciaID id correspondiente a la organizacion a buscar.
     * @return una dependencia.
     */
    public DependenciaEntity find(Long dependenciaID) {
        LOGGER.log(Level.INFO, "Consultando dependencia con id={0}", dependenciaID);
        return em.find(DependenciaEntity.class, dependenciaID);
    }

    /**
     * Actualiza una dependencia.
     *
     * @param dependenciaEntity: la dependencia que viene con los nuevos
     * cambios. Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso
     * del método update.
     * @return una dependencia con los cambios aplicados.
     */
    public DependenciaEntity update(DependenciaEntity dependenciaEntity) {
        LOGGER.log(Level.INFO, "Actualizando dependencia con id = {0}", dependenciaEntity.getId());
        return em.merge(dependenciaEntity);
    }

    /**
     * Borra una dependencia de la base de datos recibiendo como argumento el
     * id de la organizacion
     *
     * @param dependenciaId: id correspondiente a la organizacion a borrar.
     */
    public void delete(Long dependenciaId) {
        LOGGER.log(Level.INFO, "Borrando dependencia con id={0}", dependenciaId);
        DependenciaEntity entity = em.find(DependenciaEntity.class, dependenciaId);
        em.remove(entity);
    }

    /**
     * Busca si hay alguna dependencia con el nombre que se envía de argumento
     *
     * @param name: Nombre de la dependencia que se está buscando
     * @return null si no existe ninguna dependencia con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public DependenciaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando dependencia por nombre ", name);

        // Se crea un query para buscar dependencias con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<DependenciaEntity> query = em.createQuery("Select e From DependenciaEntity e where e.name = :name", DependenciaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<DependenciaEntity> sameName = query.getResultList();
        DependenciaEntity result = null;
        if (!(sameName == null || sameName.isEmpty())) {
            result = sameName.get(0);
        }
        return result;
    }
}
