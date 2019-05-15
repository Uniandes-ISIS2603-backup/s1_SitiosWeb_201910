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
import java.util.Objects;
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
     * Se encarga de crear una tecnologia en la base de datos.
     *
     * @param tecnologiaEntity Objeto de TecnologiaEntity con los datos nuevos
     * @return Objeto de TecnologiaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException en caso de que se viole una regla de negocio.
     */
    public TecnologiaEntity createTechnology(TecnologiaEntity tecnologiaEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la tecnologia");
        TecnologiaEntity entity = persistence.find(tecnologiaEntity.getId());
        if(entity!=null){
            throw new BusinessLogicException("Ya existe una tecnologia con ese ID");
        }
        verificarReglasDeNegocio(tecnologiaEntity);
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
        TecnologiaEntity tecnologiaEntity = persistence.find(tecnologiaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar tecnología con id = {0}", tecnologiaId);
        return tecnologiaEntity;
    }

    /**
     * Actualiza la información de una instancia de Tecnologia.
     *
     * @param tecnologiaId Identificador de la instancia a actualizar
     * @param tecnologiaEntity Instancia de TecnologiaEntity con los nuevos datos.
     * @return Instancia de TecnologiaEntity con los datos actualizados.
     * @throws BusinessLogicException si se viola una regla de negocio.
     */
    public TecnologiaEntity updateTechnology(Long tecnologiaId, TecnologiaEntity tecnologiaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la tecnología con id = {0}", tecnologiaId);
        String category = tecnologiaEntity.getTechCategory();
        if(tecnologiaId != tecnologiaEntity.getId())
        {
            throw new BusinessLogicException("Los ID no coinciden con la tecnología a actualizar");
        }
        verificarReglasDeNegocio(tecnologiaEntity);
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
        TecnologiaEntity tecnologiaEntity = persistence.find(tecnologiaId);
        List<SitioWebEntity> sitios = tecnologiaEntity.getSitiosWeb();
        if (sitios != null && !sitios.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la tecnologia con id = " + tecnologiaId + " porque tiene sitiosWeb asociados");
        }
        persistence.delete(tecnologiaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la tecnologia con id = {0}", tecnologiaId);
    }
    private void verificarReglasDeNegocio(TecnologiaEntity tecnologiaEntity) throws BusinessLogicException
    {
        String category = tecnologiaEntity.getTechCategory();
         if(tecnologiaEntity.getDescription()==null||tecnologiaEntity.getDescription().equals(""))
        {
             throw new BusinessLogicException("La descripción no puede estar vacia");
        }
        if(tecnologiaEntity.getDescription().length()<20)
        {
             throw new BusinessLogicException("La descripción debe contener más de 20 caracteres");
        }
        if(tecnologiaEntity.getName()==null||tecnologiaEntity.getName().equals(""))
        {
             throw new BusinessLogicException("El nombre no puede estar vacio");
        }
        if(tecnologiaEntity.getUrl()==null||tecnologiaEntity.getUrl().equals(""))
        {
             throw new BusinessLogicException("La url no puede estar vacia");
        } 
        if(!tecnologiaEntity.getUrl().matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))
        {
             throw new BusinessLogicException("Url invalida");
        } 
        if(tecnologiaEntity.getVersion()==null||tecnologiaEntity.getVersion().equals(""))
        {
             throw new BusinessLogicException("La version no puede estar vacia");
        } 
        if(category==null||category.equals(""))
        {
             throw new BusinessLogicException("La categoria no puede estar vacia");
        } 
        if(!category.equalsIgnoreCase("LenguajeDeProgramacion")&&!category.equalsIgnoreCase("FrameWork")&&!category.equalsIgnoreCase("ServidorDeAplicacion")&&!category.equalsIgnoreCase("Libreria"))
        {
             throw new BusinessLogicException("Categoria invalida");
        }
    }
}
