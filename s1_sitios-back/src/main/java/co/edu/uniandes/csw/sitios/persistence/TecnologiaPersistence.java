/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.persistence;

import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author amezq
 */
@Stateless
public class TecnologiaPersistence {

    private static final Logger LOGGER = Logger.getLogger(TecnologiaPersistence.class.getName());

    @PersistenceContext(unitName = "sitiosPU")
    protected EntityManager em;

    /**
     * Method to persist the Entity in the Database.
     *
     * @param tecnologiaEntity object to be created
     * @return created entity with an ID given by the database.
     */
    public TecnologiaEntity create(TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Creando una tecnología");
        em.persist(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Tecnología creada");
        return tecnologiaEntity;
    }

    /**
     * Returns all the technologies from a website.
     * @return a list with all the technologies from a website
     */
    public List<TecnologiaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las tecnologías");
        Query q = em.createQuery("select u from TecnologiaEntity u");
        return q.getResultList();
    }

    /**
     * Searchs a technology by the given Id.
     *
     * @param technologysId: the technologyId
     * @return un libro.
     */
    public TecnologiaEntity find(Long technologysId) {
        LOGGER.log(Level.INFO, "Consultando la tecnologóa con id={0}", technologysId);
        return em.find(TecnologiaEntity.class, technologysId);
    }

    /**
     * Updates a technology.
     *
     * @param tecnologiaEntity: The technology with all the changes to be updated
     * @return the technology with the assigned changes.
     */
    public TecnologiaEntity update(TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", tecnologiaEntity.getId());
        return em.merge(tecnologiaEntity);
    }

    /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param technologysId: id correspondiente al libro a borrar.
     */
    public void delete(Long technologysId) {
        LOGGER.log(Level.INFO, "Borrando la tecnología con el id={0}", technologysId);
        TecnologiaEntity tecnologiaEntity = em.find(TecnologiaEntity.class, technologysId);
        em.remove(tecnologiaEntity);
    }

}
