/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class PersonaPersistence {

    private static final Logger LOGGER = Logger.getLogger(PersonaPersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param personaEntity objeto persona que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PersonaEntity create(PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Creando una persona nueva");
        em.persist(personaEntity);
        LOGGER.log(Level.INFO, "Persona creada");
        return personaEntity;
    }

    /**
     * Devuelve todas las personas de la base de datos.
     *
     * @return una lista con todas las personas que encuentre en la base de
     * datos, "select u from PersonaEntity u" es como un "select * from
     * BookEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PersonaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las personas");
        Query q = em.createQuery("select u from PersonaEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param id: id correspondiente de la persona buscado.
     * @return un libro.
     */
    public PersonaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando de la persona con id={0}", id);
        return em.find(PersonaEntity.class, id);
    }

    /**
     * Actualiza una persona.
     *
     * @param personaEntity: la persona que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return la persona con los cambios aplicados.
     */
    public PersonaEntity update(PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la persona con id={0}", personaEntity.getId());
        return em.merge(personaEntity);
    }

    /**
     * Borra una persona de la base de datos recibiendo como argumento el id de
     * la persona
     *
     * @param personaId: id correspondiente a la author a borrar.
     */
    public void delete(Long personaId) {

        LOGGER.log(Level.INFO, "Borrando el persona con id={0}", personaId);
        // Se hace uso de mismo método que esta explicado en public PersonaEntity find(Long id) para obtener la author a borrar.
        PersonaEntity personaEntity = em.find(PersonaEntity.class, personaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from PersonaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(personaEntity);
    }

}
