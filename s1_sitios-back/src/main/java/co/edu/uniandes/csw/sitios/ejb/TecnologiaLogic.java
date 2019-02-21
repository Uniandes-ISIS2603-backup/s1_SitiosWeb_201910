/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
 @Stateless
public class TecnologiaLogic {
     
     private static final Logger LOGGER = Logger.getLogger(TecnologiaLogic.class.getName());
     
    @Inject 
    private TecnologiaPersistence persistence;

    /**
     * Se encarga de crear un Author en la base de datos.
     *
     * @param tecnologiaEntity Objeto de TecnologiaEntity con los datos nuevos
     * @return Objeto de TecnologiaEntity con los datos nuevos y su ID.
     */
    public TecnologiaEntity createTechnology(TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la tecnologia");
        TecnologiaEntity newTecnologiaEntity = persistence.create(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la tecnologia");
        return newTecnologiaEntity;
    }

    /**
     * Obtiene la lista de los registros de Tecnologia.
     *
     * @return Colección de objetos de TecnologiaEntity.
     */
    public List<TecnologiaEntity> getTechnologies() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las tecnologías");
        List<TecnologiaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las tecnologías");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Tecnologia a partir de su ID.
     *
     * @param tecnologiaId Identificador de la instancia a consultar
     * @return Instancia de TecnologiaEntity con los datos de la Tecnologia consultada.
     */
    public TecnologiaEntity getTechnology(Long tecnologiaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tecnología con id = {0}", tecnologiaId);
        TecnologiaEntity authorEntity = persistence.find(tecnologiaId);
        if (authorEntity == null) {
            LOGGER.log(Level.SEVERE, "La tecnología con el id = {0} no existe", tecnologiaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar tecnología con id = {0}", tecnologiaId);
        return authorEntity;
    }

    /**
     * Actualiza la información de una instancia de Tecnologia.
     *
     * @param tecnologiaId Identificador de la instancia a actualizar
     * @param tecnologiaEntity Instancia de TecnologiaEntity con los nuevos datos.
     * @return Instancia de TecnologiaEntity con los datos actualizados.
     */
    public TecnologiaEntity updateTechnology(Long tecnologiaId, TecnologiaEntity tecnologiaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tecnología con id = {0}", tecnologiaId);
        TecnologiaEntity newTecnologiaEntity = persistence.update(tecnologiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la tecnología con id = {0}", tecnologiaId);
        return newTecnologiaEntity;
    }

    /**
     * Elimina una instancia de Tecnologia de la base de datos.
     *
     * @param tecnologiaId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si la tecnología tiene libros sitiosWeb asociados.
     */
    public void deleteTechnology(Long tecnologiaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la tecnologia con id = {0}", tecnologiaId);
        List<SitioWebEntity> sitios = getTechnology(tecnologiaId).getSitiosWeb();
        if (sitios != null && !sitios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la tecnologia con id = " + tecnologiaId + " porque tiene sitiosWeb asociados");
        }
        persistence.delete(tecnologiaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la tecnologia con id = {0}", tecnologiaId);
    }
}
