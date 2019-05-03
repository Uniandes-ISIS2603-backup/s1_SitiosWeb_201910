package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class SitioTecnologiaLogic {

    private static final Logger LOGGER = Logger.getLogger(SitioTecnologiaLogic.class.getName());

    @Inject
    private SitioWebPersistence sitioWebPersistence;

    @Inject
    TecnologiaPersistence tecnologiaPersistence;

  /**
     * Asocia un Sitio web existente a un Tecnologia
     *
     * @param websiteId Identificador de la instancia de Tecnologia
     * @param technologyId Identificador de la instancia de Sitio web
     * @return Instancia de TecnologiaEntity que fue asociada a Tecnologia
     */
    public TecnologiaEntity addTechnology(Long websiteId, Long technologyId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al sitio web con id = {0}", websiteId);
        SitioWebEntity websiteEntity = sitioWebPersistence.find(websiteId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(technologyId);
        List<SitioWebEntity> sites = tecnologiaEntity.getSitiosWeb();
        if (!sites.contains(websiteEntity)) sites.add(websiteEntity);
        tecnologiaEntity.setSitiosWeb(sites);
        List<TecnologiaEntity> techs = websiteEntity.getTechnologies();
        if (!techs.contains(tecnologiaEntity))techs.add(tecnologiaEntity);
        websiteEntity.setTechnologies(techs);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una tecnologia al sitio web con id = {0}", websiteId);
        return tecnologiaPersistence.find(technologyId);
    }

     /**
     * Obtiene una colección de instancias de TecnologiaEntity asociadas a una
     * instancia de WebSite
     *
     * @param websiteId Identificador de la instancia de Tecnologia
     * @return Colección de instancias de TecnologiaEntity asociadas a la instancia de
     * Tecnologia
     */
    public List<TecnologiaEntity> getTechnologies(Long websiteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los tecnologias del sitio web con id = {0}", websiteId);
        return (sitioWebPersistence.find(websiteId)).getTechnologies();
    }

    /**
     * Obtiene una instancia de TecnologiaEntity asociada a una instancia de Tecnologia
     *
     * @param websiteId Identificador de la instancia de Tecnologia
     * @param technologyId Identificador de la instancia de Sitio web
     * @return La entidadd de Tecnologia del sitio web
     * @throws BusinessLogicException Si la tecnologia no está asociado al sitio web
     */
    public TecnologiaEntity getTechnology(Long websiteId, Long technologyId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tecnologia con id = {0} del sitio web con id = " + websiteId, technologyId);
        List<TecnologiaEntity> technologies = sitioWebPersistence.find(websiteId).getTechnologies();
        TecnologiaEntity bookEntity = tecnologiaPersistence.find(technologyId);
        int index = technologies.indexOf(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la tecnologia con id = {0} del sitio web con id = " + websiteId, technologyId);
        if (index >= 0) {
            return technologies.get(index);
        }
        throw new BusinessLogicException("La tecnologia no está asociado al sitio web");
    }

    /**
     * Remplaza las instancias de Tecnologia asociadas a una instancia de SitioWeb
     *
     * @param authorId Identificador de la instancia de SitioWeb
     * @param books Colección de instancias de TecnologiaEntity a asociar a instancia
     * de Tecnologia
     * @return Nueva colección de TecnologiaEntity asociada a la instancia de Tecnologia
     */
    public List<TecnologiaEntity> replaceTechnologies(Long websiteId, List<TecnologiaEntity> newTechs) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los tecnologias asocidos al sitio web con id = {0}", websiteId);
        SitioWebEntity webSiteEntity = sitioWebPersistence.find(websiteId);
        List<TecnologiaEntity> technologies = tecnologiaPersistence.findAll();
        for (TecnologiaEntity tech : newTechs) {
            addTechnology(webSiteEntity.getId(),tech.getId());
        }
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los tecnologias asociadas al sitio web con id = {0}", websiteId);
        return webSiteEntity.getTechnologies();
    }

    /**
     * Desasocia un Sitio web existente de un Tecnologia existente
     *
     * @param websiteId Identificador de la instancia de Sitio web
     * @param technologyId Identificador de la instancia de Tecnologia
     */
    public void removeTechnology(Long websiteId, Long technologyId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un tecnologia del sitio web con id = {0}", websiteId);
        SitioWebEntity websiteEntity = sitioWebPersistence.find(websiteId);
        TecnologiaEntity technologyEntity = tecnologiaPersistence.find(technologyId);
        websiteEntity.getTechnologies().remove(technologyEntity);
        technologyEntity.getSitiosWeb().remove(websiteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un tecnologia del sitio web con id = {0}", websiteId);
    }
}