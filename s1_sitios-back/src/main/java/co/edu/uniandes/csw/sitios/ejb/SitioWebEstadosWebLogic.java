/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;

import co.edu.uniandes.csw.sitios.persistence.EstadoWebPersistence;
import co.edu.uniandes.csw.sitios.persistence.SitioWebPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de SitioWeb y EstadosWeb.
 * 
 * @author Daniel preciado
 */
@Stateless
public class SitioWebEstadosWebLogic {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(SitioWebEstadosWebLogic.class.getName());
    
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
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Agrega un estadoWeb a el sitioWeb
     *
     * @param estadoWebId El id estadoWeb a guardar
     * @param sitioWebId El id de el sitioWeb en el cual se va a guardar el
     * estadoWeb.
     * @return El estadoWeb creado.
     */
    public EstadoWebEntity addEstadoWeb(Long estadoWebId, Long sitioWebId) 
    {
        
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un estadoWeb a el sitioWeb con id = {0}", sitioWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        EstadoWebEntity estadoWebEntity = estadoWebPersistence.find(estadoWebId);
        estadoWebEntity.setSitioAsociado(sitioWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un estadoWeb a el sitioWeb con id = {0}", sitioWebId);
        return estadoWebEntity;
    }

    /**
     * Retorna todos los estadosWeb asociados a un sitioWeb
     *
     * @param sitioWebId El ID de el sitioWeb buscado
     * @return La lista de estadosWeb de el sitioWeb
     */
    public List<EstadoWebEntity> getEstadosWeb(Long sitioWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los estadosWeb asociados a el sitioWeb con id = {0}", sitioWebId);
        return sitioWebPersistence.find(sitioWebId).getEstadosWeb();
    }

    /**
     * Retorna un estadoWeb asociado a un sitioWeb
     *
     * @param sitioWebId El id de la sitioWeb a buscar.
     * @param estadoWebId El id del estadoWeb a buscar
     * @return El estadoWeb encontrado dentro de el sitioWeb.
     * @throws BusinessLogicException Si el estadoWeb no se encuentra en la
     * sitioWeb
     */
    public EstadoWebEntity getEstadoWeb(Long sitioWebId, Long estadoWebId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el estadoWeb con id = {0} de la sitioWeb con id = " + sitioWebId, estadoWebId);
        List<EstadoWebEntity> estadosWeb = sitioWebPersistence.find(sitioWebId).getEstadosWeb();
        EstadoWebEntity estadoWebEntity = estadoWebPersistence.find(estadoWebId);
        int index = estadosWeb.indexOf(estadoWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el estadoWeb con id = {0} de la sitioWeb con id = " + sitioWebId, estadoWebId);
        if (index >= 0) 
        {
            return estadosWeb.get(index);
        }
        throw new BusinessLogicException("El estadoWeb no está asociado a la sitioWeb");
    }

    /**
     * Remplaza los estadosWeb de un sitioWeb
     *
     * @param estadosWeb Lista de estadosWeb que serán los nuevos de el sitioWeb.
     * @param sitioWebId El id de la sitioWeb que se quiere actualizar.
     * @return La lista de estadosWeb actualizada.
     */
    public List<EstadoWebEntity> replaceEstadosWeb(Long sitioWebId, List<EstadoWebEntity> estadosWeb) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el sitioWeb con id = {0}", sitioWebId);
        SitioWebEntity sitioWebEntity = sitioWebPersistence.find(sitioWebId);
        List<EstadoWebEntity> estadoWebList = estadoWebPersistence.findAll();
        for (EstadoWebEntity estadoWeb : estadoWebList) 
        {
            if (estadosWeb.contains(estadoWeb)) 
            {
                estadoWeb.setSitioAsociado(sitioWebEntity);
            } 
            else if (estadoWeb.getSitioAsociado()!= null && estadoWeb.getSitioAsociado().equals(sitioWebEntity)) 
            {
                estadoWeb.setSitioAsociado(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el sitioWeb con id = {0}", sitioWebId);
        return estadosWeb;
    }
    
}
