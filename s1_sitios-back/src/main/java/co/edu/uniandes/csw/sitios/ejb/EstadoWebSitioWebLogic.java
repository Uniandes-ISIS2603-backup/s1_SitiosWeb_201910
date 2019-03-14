/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.EstadoWebPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad estadoWeb y sitioWeb.
 * 
 * @author Daniel Preciado
 */
@Stateless
public class EstadoWebSitioWebLogic {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(EstadoWebSitioWebLogic.class.getName());
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________

    /**
     * variable empleada para acceder a la persistencia de un EstadoWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private EstadoWebPersistence estadoWebPersistence;

    /**
     * variable empleada para acceder a la persistencia de un sitioWeb, esto se logra
     * mediante inyeccion de dependencias
     */
    @Inject
    private SitioWebPersistence sitioWebPersistence;

    
    /**
     * Remplazar el sitio de un estadoWeb.
     *
     * @param estadoWebId id del estadoWeb que se quiere actualizar.
     * @param sitioWebId El id de el sitioWeb al que pertenece el estadoWeb.
     * @return el nuevo estadoWeb.
     */
    public EstadoWebEntity replaceSitioAsociado(Long estadoWebId, Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar estadoWeb con id = {0}", estadoWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        EstadoWebEntity estadoWebEntity = estadoWebPersistence.find(estadoWebId);
        estadoWebEntity.setSitioAsociado(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar estadoWeb con id = {0}", estadoWebEntity.getId());
        return estadoWebEntity;
    }

    /**
     * Borrar un estadoWeb de un sitioWeb. Este metodo se utiliza para borrar la
     * relacion de un estadoWeb a sitio web.
     *
     * @param estadoWebId El estadoWeb que se desea borrar de el sitioWeb.
     */
    public void removeSitioWeb(Long estadoWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el SitioWeb del estadoWeb con id = {0}", estadoWebId);
        EstadoWebEntity estadoWebEntity = estadoWebPersistence.find(estadoWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(estadoWebEntity.getSitioAsociado().getId());
        estadoWebEntity.setSitioAsociado(null);
        sitioWebEntity.getEstadosWeb().remove(estadoWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el SitioWeb del estadoWeb con id = {0}", estadoWebEntity.getId());
    }
    
}
