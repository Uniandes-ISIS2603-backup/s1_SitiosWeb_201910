/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
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
public class PlataformaDeDespliegueSitiosWebLogic {

    private static final Logger LOGGER = Logger.getLogger(PlataformaDeDespliegueSitiosWebLogic.class.getName());

    @Inject
    private SitioWebPersistence sitioPersistence;

    @Inject
    private PlataformaDeDesplieguePersistence plataformaPersistence;

    /**
     * Agregar un sitio a la plataforma
     *
     * @param sitiosId El id libro a guardar
     * @param plataformasId El id de la plataforma en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public SitioWebEntity addSitioWeb(Long sitiosId, Long plataformasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un sitioWeb a la plataforma con id = {0}", plataformasId);
        PlataformaDeDespliegueEntity plataformaEntity = plataformaPersistence.find(plataformasId);
        SitioWebEntity sitioEntity = sitioPersistence.find(sitiosId);
        sitioEntity.setPlataformaDeDespliegue(plataformaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un sitioWeb a la plataforma con id = {0}", plataformasId);
        return sitioEntity;
    }

    /**
     * Retorna todos los sitios asociados a una plataforma
     *
     * @param plataformasId El ID de la plataforma buscada
     * @return La lista de sitiosWeb de la plataforma
     */
    public List<SitioWebEntity> getSitiosWeb(Long plataformasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los sitiosWeb asociados a la plataforma con id = {0}", plataformasId);
        return plataformaPersistence.find(plataformasId).getSitiosWeb();
    }

    /**
     * Retorna un sitio asociado a una plataforma
     *
     * @param plataformasId El id de la plataforma a buscar.
     * @param sitiosId El id del libro a buscar
     * @return El libro encontrado dentro de la plataforma.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * plataforma
     */
    public SitioWebEntity getSitioWeb(Long plataformasId, Long sitiosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la plataforma con id = " + plataformasId, sitiosId);
        List<SitioWebEntity> sitios = plataformaPersistence.find(plataformasId).getSitiosWeb();
        SitioWebEntity sitioEntity = sitioPersistence.find(sitiosId);
        int index = sitios.indexOf(sitioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la plataforma con id = " + plataformasId, sitiosId);
        if (index >= 0) {
            return sitios.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la plataforma");
    }

    /**
     * Remplazar sitios de una plataforma
     *
     * @param sitios Lista de sitiosWeb que serán los de la plataforma.
     * @param plataformasId El id de la plataforma que se quiere actualizar.
     * @return La lista de sitiosWeb actualizada.
     */
    public List<SitioWebEntity> replaceSitiosWeb(Long plataformasId, List<SitioWebEntity> sitios) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la plataforma con id = {0}", plataformasId);
        PlataformaDeDespliegueEntity plataformaEntity = plataformaPersistence.find(plataformasId);
        List<SitioWebEntity> sitiosList = sitioPersistence.findAll();
        for (SitioWebEntity sitio : sitiosList) {
            if (sitios.contains(sitio)) {
                sitio.setPlataformaDeDespliegue(plataformaEntity);
            } else if (sitio.getPlataformaDeDespliegue() != null && sitio.getPlataformaDeDespliegue().equals(plataformaEntity)) {
                sitio.setPlataformaDeDespliegue(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la plataforma con id = {0}", plataformasId);
        return sitios;
    }
}