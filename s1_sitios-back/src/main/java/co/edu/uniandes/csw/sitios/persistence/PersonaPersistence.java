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
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<PersonaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los libros");
        Query q = em.createQuery("select u from PersonaEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param booksId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public PersonaEntity find(Long booksId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", booksId);
        return em.find(PersonaEntity.class, booksId);
    }

    /**
     * Actualiza un libro.
     *
     * @param bookEntity: el libro que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public PersonaEntity update(PersonaEntity bookEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", bookEntity.getId());
        return em.merge(bookEntity);
    }
    
}