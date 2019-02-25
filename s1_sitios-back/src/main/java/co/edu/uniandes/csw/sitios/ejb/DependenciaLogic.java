/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.DependenciaPersistence;
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
public class DependenciaLogic {
     
     private static final Logger LOGGER = Logger.getLogger(TecnologiaLogic.class.getName());
     
    @Inject 
    private DependenciaPersistence persistence;

    /**
     * Se encarga de crear una dependencia en la base de datos.
     *
     * @param dependenciaEntity Objeto de DependenciaEntity con los datos nuevos
     * @return Objeto de DependenciaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si se viola una regla de negocio.
     */
    public DependenciaEntity createDependency(DependenciaEntity dependenciaEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la dependencia");
        DependenciaEntity existe = getDependency(dependenciaEntity.getId());
        if(existe!=null)
        {
            throw new BusinessLogicException("Ya existe una dependencia con este ID");
        }
        verificarReglasDeNegocio(dependenciaEntity);
        DependenciaEntity newDependencyEntity = persistence.create(dependenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la dependencia");
        return newDependencyEntity;
    }

    /**
     * Obtiene la lista de los registros de Dependencia.
     *
     * @return Colección de objetos de DependenciaEntity.
     */
    public List<DependenciaEntity> getDependencies() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las dependencias");
        List<DependenciaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las dependencias");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Dependencia a partir de su ID.
     *
     * @param dependenciaID Identificador de la instancia a consultar
     * @return Instancia de DependenciaEntity con los datos de la Dependencia consultada.
     */
    public DependenciaEntity getDependency(Long dependenciaID) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la dependencia con id = {0}", dependenciaID);
        DependenciaEntity dependenciaEntity = persistence.find(dependenciaID);
        if (dependenciaEntity == null) {
            LOGGER.log(Level.SEVERE, "La dependencia con el id = {0} no existe", dependenciaID);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar dependencia con id = {0}", dependenciaID);
        return dependenciaEntity;
    }

    /**
     * Actualiza la información de una instancia de Dependencia.
     *
     * @param dependenciaID Identificador de la instancia a actualizar
     * @param dependenciaEntity Instancia de DependenciaEntity con los nuevos datos.
     * @return Instancia de DependenciaEntity con los datos actualizados.
     * @throws BusinessLogicException si se viola una regla de negocio.
     */
    public DependenciaEntity updateDependency(Long dependenciaID, DependenciaEntity dependenciaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la dependencia con id = {0}", dependenciaID);
        DependenciaEntity existe = getDependency(dependenciaEntity.getId());
        if(existe!=null&&!Objects.equals(dependenciaID, existe.getId()))
        {
            throw new BusinessLogicException("Ya existe una dependencia con este ID");
        }
        verificarReglasDeNegocio(dependenciaEntity);
        DependenciaEntity newDependencyEntity = persistence.create(dependenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de la dependencia con id = {0}", dependenciaID);
        return newDependencyEntity;
    }

    /**
     * Elimina una instancia de Dependencia de la base de datos.
     *
     * @param dependenciaId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si la dependencia tiene un Administrador asociado.
     */
    public void deleteDependency(Long dependenciaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la dependencia con id = {0}", dependenciaId);
        AdministradorEntity administrador = getDependency(dependenciaId).getAdministrador();
        if (administrador != null) {
            throw new BusinessLogicException("No se puede borrar la dependencia con id = " + dependenciaId + " porque tiene un Administrador asociado");
        }
        persistence.delete(dependenciaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la dependencia con id = {0}", dependenciaId);
    }
    public void verificarReglasDeNegocio(DependenciaEntity dependenciaEntity) throws BusinessLogicException
    {
        if(dependenciaEntity.getNombreDependencia()==null||dependenciaEntity.getNombreDependencia().equals(""))
        {
             throw new BusinessLogicException("El nombre no puede estar vacio");
        }
        if(dependenciaEntity.getEmail()==null||dependenciaEntity.getEmail().equals(""))
        {
             throw new BusinessLogicException("El email no puede estar vacio");
        } 
        if(!dependenciaEntity.getEmail().matches("^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$"))
        {
             throw new BusinessLogicException("Email invalido");
        } 
        int cantidadDigitos = ((Integer)dependenciaEntity.getTelefono()).toString().length();
        if(cantidadDigitos<7||cantidadDigitos>11)
        {
             throw new BusinessLogicException("El numero de telefono no es valido");
        }
    }
}

