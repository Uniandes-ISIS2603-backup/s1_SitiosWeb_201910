package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.TecnologiaPersistence;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class TecnologiaSitioLogic {

    private static final Logger LOGGER = Logger.getLogger(TecnologiaSitioLogic.class.getName());

    @Inject
    private SitioWebPersistence sitioWebPersistence;

    @Inject
    TecnologiaPersistence tecnologiaPersistence;
     /**
     * Asocia un SitioWeb existente a un Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param sitioWebsId Identificador de la instancia de SitioWeb
     * @return Instancia de SitioWebEntity que fue asociada a Tecnologia
     */
    public SitioWebEntity addWebSite(Long tecnologiasId, Long sitioWebsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un sitioWeb al tecnologia con id = {0}", tecnologiasId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebsId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        tecnologiaEntity.getSitiosWeb().add(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un sitioWeb al tecnologia con id = {0}", tecnologiasId);
        return sitioWebPersistence.find(sitioWebsId);
    }

    /**
     * Obtiene una colección de instancias de SitioWebEntity asociadas a una
     * instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @return Colección de instancias de SitioWebEntity asociadas a la instancia
     * de Tecnologia
     */
    public List<SitioWebEntity> getWebSites(Long tecnologiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los sitioWebes del tecnologia con id = {0}", tecnologiasId);
        return tecnologiaPersistence.find(tecnologiasId).getSitiosWeb();
    }

    /**
     * Obtiene una instancia de SitioWebEntity asociada a una instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param sitioWebsId Identificador de la instancia de SitioWeb
     * @return La entidad del Autor asociada al tecnologia
     */
    public SitioWebEntity getWebSite(Long tecnologiasId, Long sitioWebsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un sitioWeb del tecnologia con id = {0}", tecnologiasId);
        List<SitioWebEntity> sitioWebs = tecnologiaPersistence.find(tecnologiasId).getSitiosWeb();
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebsId);
        int index = sitioWebs.indexOf(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un sitioWeb del tecnologia con id = {0}", tecnologiasId);
        if (index >= 0) {
            return sitioWebs.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de SitioWeb asociadas a una instancia de Tecnologia
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param list Colección de instancias de SitioWebEntity a asociar a instancia
     * de Tecnologia
     * @return Nueva colección de SitioWebEntity asociada a la instancia de Tecnologia
     */
    public List<SitioWebEntity> replaceWebSites(Long tecnologiasId, List<SitioWebEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los sitioWebs del tecnologia con id = {0}", tecnologiasId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        tecnologiaEntity.setSitiosWeb(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los sitioWebs del tecnologia con id = {0}", tecnologiasId);
        return tecnologiaPersistence.find(tecnologiasId).getSitiosWeb();
    }

    /**
     * Desasocia un SitioWeb existente de un Tecnologia existente
     *
     * @param tecnologiasId Identificador de la instancia de Tecnologia
     * @param sitioWebsId Identificador de la instancia de SitioWeb
     */
    public void removeWebSite(Long tecnologiasId, Long sitioWebsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un sitioWeb del tecnologia con id = {0}", tecnologiasId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebsId);
        TecnologiaEntity tecnologiaEntity = tecnologiaPersistence.find(tecnologiasId);
        tecnologiaEntity.getSitiosWeb().remove(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un sitioWeb del tecnologia con id = {0}", tecnologiasId);
    }
}
