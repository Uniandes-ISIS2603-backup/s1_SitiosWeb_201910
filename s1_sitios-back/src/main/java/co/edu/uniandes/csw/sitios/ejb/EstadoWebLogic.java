/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.EstadoWebEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.EstadoWebPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Esta clase es la que implementa la conexion con la persistencia de la
 * entidad de EstadoWeb.
 * 
 * @author Daniel Preciado
 */
@Stateless
public class EstadoWebLogic {
    
    //__________________________________________________________________________
    // Constantes
    //__________________________________________________________________________
    
    /**
     * constante empleada para dejar registro, una especie de huella
     */
    private static final Logger LOGGER = Logger.getLogger(EstadoWebLogic.class.getName());
    
     
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * Variable empleada para acceder a la persistencia de la aplicación (DB), 
     * esto se hace mediante el uso de inyeccion de dependencias.
     */
     @Inject
    private EstadoWebPersistence DB; 
     
     
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
         
     /**
     * Crea un estado Web en la DB.
     *
     * @param pEstadoWebEntity un entity que representa el objeto a persistir.
     * @return un entity de el estado Web luego de persistirlo.
     * @throws BusinessLogicException Si el estado web a persistir esta incompleto.
     */
    public EstadoWebEntity createEstadoWeb(EstadoWebEntity pEstadoWebEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de un estado Web");

        if (pEstadoWebEntity.getDescripcion().equals("")) 
        {
            throw new BusinessLogicException("no puede crear un estado Web sin descripcion \"" );
        }
        if (!pEstadoWebEntity.getEstado().equals("")) 
        {
            throw new BusinessLogicException("no puede crear un estado Web sin un tipo de estado valido o vacio \"" );
        }
        if (pEstadoWebEntity.getFechaCambio() != null) 
        {
            throw new BusinessLogicException("no puede crear un estado Web sin fecha \"" );
        }
        // Invoca la DB para crear un estado Web
        DB.create(pEstadoWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de un estado Web");
        return pEstadoWebEntity;
    }

    /**
     * Obtener todos los estados web almacenados en la DB
     *
     * @return una lista de estados web.
     */
    public List<EstadoWebEntity> getEstadosWeb() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los estados web");
        List<EstadoWebEntity> estadosWeb = DB.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los estados web en la DB");
        return estadosWeb;
    }

    /**
     * Obtiene un estado Web mediante´su id.
     *
     * @param estadoWebId: id del estado Web buscado.
     * @return el estado Web buscado mediante su id.
     */
    public EstadoWebEntity getEstadoWeb(Long estadoWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un estado Web con id = {0}", estadoWebId);
        EstadoWebEntity estadoWebEntity = DB.find(estadoWebId);
        if (estadoWebEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "el estado Web con el id = {0} no existe", estadoWebId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un estado Web con id = {0}", estadoWebId);
        return estadoWebEntity;
    }

    /**
     * Actualizar un estadoWeb.
     *
     * @param estadoWebId: id de un estado Web a buscar en la DB
     * @param pEstadoWebEntity: estado Web con los cambios para ser actualizada en la DB
     * @return un estado Web con los cambios actualizados en la base de datos.
     */
    public EstadoWebEntity updateEstadoWeb(Long estadoWebId, EstadoWebEntity pEstadoWebEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un estado Web con id = {0}", estadoWebId);
        EstadoWebEntity updateEntity = DB.update(pEstadoWebEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar un estado Web con id = {0}", pEstadoWebEntity.getId());
        return updateEntity;
    }

    /**
     * Borrar un estado Web de la DB.
     *
     * @param estadoWebId: id del estado Web a eliminar
     */
    public void deleteEstadoWeb(Long estadoWebId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un estado Web con id = {0}", estadoWebId);
        DB.delete(estadoWebId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un estado Web con id = {0}", estadoWebId);
    }
    
}
