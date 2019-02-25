/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PersonaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Persona.
 *
 * @author Allan Roy Corinaldi.
 */
@Stateless
public class PersonaLogic {

    private static final Logger LOGGER = Logger.getLogger(PersonaLogic.class.getName());

    @Inject
    private PersonaPersistence persistence;

    /**
     * Se encarga de crear un Persona en la base de datos.
     *
     * @param PersonaEntity Objeto de PersonaEntity con los datos nuevos
     * @return Objeto de PersonaEntity con los datos nuevos y su ID.
     */
    public PersonaEntity createPersona(PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la persona");
        PersonaEntity newPersonaEntity = persistence.create(personaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del autor");
        return newPersonaEntity;
    }

    /**
     * Obtiene la lista de los registros de Persona.
     *
     * @return Colección de objetos de PersonaEntity.
     */
    public List<PersonaEntity> getPersonas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las personas");
        List<PersonaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las personas");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Persona a partir de su ID.
     *
     * @param personasId Identificador de la instancia a consultar
     * @return Instancia de PersonaEntity con los datos del Persona consultado.
     */
    public PersonaEntity getPersona(Long personasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la persona con id = {0}", personasId);
        PersonaEntity personaEntity = persistence.find(personasId);
        if (personaEntity == null) {
            LOGGER.log(Level.SEVERE, "El sitio web con una persona de id= {0} no existe", personasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor con id = {0}", personasId);
        return personaEntity;
    }

    /**
     * Actualiza la información de una instancia de Persona.
     *
     * @param personasId Identificador de la instancia a actualizar
     * @param personaEntity Instancia de PersonaEntity con los nuevos datos.
     * @return Instancia de PersonaEntity con los datos actualizados.
     */
    public PersonaEntity updatePersona(Long personasId, PersonaEntity personaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", personasId);
        PersonaEntity newPersonaEntity = persistence.update(personaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la persona con id = {0}", personasId);
        return newPersonaEntity;
    }

    /**
     * Elimina una instancia de Persona de la base de datos.
     *
     * @param personasId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si la persona //------------tiene libros
     * asociados.
     */
    public void deletePersona(Long personasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la persona con id = {0}", personasId);
        //List<BookEntity> books = getAuthor(personasId).getBooks();
        //if (books != null && !books.isEmpty()) {
        //throw new BusinessLogicException("No se puede borrar la persona con id = " + personasId + " porque tiene books asociados");
        //}
        //List<PrizeEntity> prizes = getPersona(personasId).getPrizes();
        //if (prizes != null && !prizes.isEmpty()) {
        //throw new BusinessLogicException("No se puede borrar el autor con id = " + personasId + " porque tiene premios asociados");
        //}
        persistence.delete(personasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la persona con id = {0}", personasId);
    }
}
