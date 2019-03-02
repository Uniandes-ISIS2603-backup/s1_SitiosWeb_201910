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
        // Se crea un query para buscar todas las dependencias en la base de datos.
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from DependenciaEntity u left join FETCH u.administrador p", DependenciaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de dependencias.
        return query.getResultList();
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
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from DependenciaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from DependenciaEntity u left join FETCH u.admninistrador p where u.id =:id", DependenciaEntity.class);
        query = query.setParameter("id", dependenciaID);
        List<DependenciaEntity> dependencias = query.getResultList();
        DependenciaEntity result = null;
        if (!(dependencias == null || dependencias.isEmpty())) {
            result = dependencias.get(0);
        }
        return result;
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
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la dependencia con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
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
        // Se hace uso DependenciaEntity de mismo método que esta explicado en public DependenciaEntity find(Long id) para obtener la dependencia a borrar.
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from DependenciaEntity u left join FETCH u.admninistrador p where u.id =:id", DependenciaEntity.class);
        query = query.setParameter("id", dependenciaId);
        DependenciaEntity dependenciaEntity = query.getSingleResult();
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from DependenciaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(dependenciaEntity);
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
