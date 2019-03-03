/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.persistence.AdministradorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Stateless
public class AdministradorLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence;

    /**
     * Se encarga de crear un Administrador en la base de datos.
     *
     * @param AdministradorEntity Objeto de AdministradorEntity con los datos
     * nuevos
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        AdministradorEntity newAdministradorEntity = persistence.create(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del administrador");
        return newAdministradorEntity;
    }

    /**
     * Obtiene la lista de los registros de Administrador.
     *
     * @return Colección de objetos de AdministradorEntity.
     */
    public List<AdministradorEntity> getAdministradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradores");
        List<AdministradorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID.
     *
     * @param AdministradoresId Identificador de la instancia a consultar
     * @return Instancia de AdministradorEntity con los datos del Author
     * consultado.
     */
    public AdministradorEntity getAdministrador(Long administradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", administradorId);
        AdministradorEntity administradorEntity = persistence.find(administradorId);
        if (administradorEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", administradorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el administrador con id = {0}", administradorId);
        return administradorEntity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param administradorId Identificador de la instancia a actualizar
     * @param administradorEntity Instancia de AdministradorEntity con los
     * nuevos datos.
     * @return Instancia de AdministradorEntity con los datos actualizados.
     */
    public AdministradorEntity updateAdministrador(Long administradorId, AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el administrador con id = {0}", administradorId);
        AdministradorEntity newAdministradorEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Administrador con id = {0}", administradorId);
        return newAdministradorEntity;
    }

    /**
     * Elimina una instancia de administrador de la base de datos.
     *
     * @param administradoresId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteAdministrador(Long administradoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", administradoresId);
        //List<BookEntity> books = getAuthor(administradoresId).getBooks();
        //if (books != null && !books.isEmpty()) {
        //throw new BusinessLogicException("No se puede borrar el autor con id = " + authorsId + " porque tiene books asociados");
        //}
        // List<PrizeEntity> prizes = getAuthor(administradoresId).getPrizes();
        //if (prizes != null && !prizes.isEmpty()) {
        // throw new BusinessLogicException("No se puede borrar el autor con id = " + authorsId + " porque tiene premios asociados");
        //}
        persistence.delete(administradoresId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradoresId);
    }
}
