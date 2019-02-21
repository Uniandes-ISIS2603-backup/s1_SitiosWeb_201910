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
@Stateless //Ud es un EJB. Ud no va a manejar estados.
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
     * Devuelve todas las organizaciones de la base de datos.
     *
     * @return una lista con todas las organizaciones que encuentre en la base
     * de datos, "select u from OrganizationEntity u" es como un "select * from
     * OrganizationEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DependenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las organizaciones");
        // Se crea un query para buscar todas las organizaciones en la base de datos.
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from OrganizationEntity u left join FETCH u.prize p", DependenciaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de organizaciones.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna organizacion con el id que se envía de argumento
     *
     *
     * @param organizationId id correspondiente a la organizacion a buscar.
     * @return una organizacion.
     */
    public DependenciaEntity find(Long organizationId) {
        LOGGER.log(Level.INFO, "Consultando organizacion con id={0}", organizationId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from OrganizationEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from OrganizationEntity u left join FETCH u.prize p where u.id =:id", DependenciaEntity.class);
        query = query.setParameter("id", organizationId);
        List<DependenciaEntity> organizations = query.getResultList();
        DependenciaEntity result = null;
        if (!(organizations == null || organizations.isEmpty())) {
            result = organizations.get(0);
        }
        return result;
    }

    /**
     * Actualiza una organizacion.
     *
     * @param organizationEntity: la organizacion que viene con los nuevos
     * cambios. Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso
     * del método update.
     * @return una organizacion con los cambios aplicados.
     */
    public DependenciaEntity update(DependenciaEntity organizationEntity) {
        LOGGER.log(Level.INFO, "Actualizando organizacion con id = {0}", organizationEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la organizacion con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(organizationEntity);
    }

    /**
     * Borra una organizacion de la base de datos recibiendo como argumento el
     * id de la organizacion
     *
     * @param organizationId: id correspondiente a la organizacion a borrar.
     */
    public void delete(Long organizationId) {
        LOGGER.log(Level.INFO, "Borrando organizacion con id={0}", organizationId);
        // Se hace uso OrganizationEntity de mismo método que esta explicado en public OrganizationEntity find(Long id) para obtener la organizacion a borrar.
        TypedQuery<DependenciaEntity> query = em.createQuery("select u from OrganizationEntity u left join FETCH u.prize p where u.id =:id", DependenciaEntity.class);
        query = query.setParameter("id", organizationId);
        DependenciaEntity organizationEntity = query.getSingleResult();
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from OrganizationEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(organizationEntity);
    }

    /**
     * Busca si hay alguna organizacion con el nombre que se envía de argumento
     *
     * @param name: Nombre de la organizacion que se está buscando
     * @return null si no existe ninguna organizacion con el nombre del
     * argumento. Si existe alguna devuelve la primera.
     */
    public DependenciaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando organizacion por nombre ", name);

        // Se crea un query para buscar organizaciones con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery<DependenciaEntity> query = em.createQuery("Select e From OrganizationEntity e where e.name = :name", DependenciaEntity.class);
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
