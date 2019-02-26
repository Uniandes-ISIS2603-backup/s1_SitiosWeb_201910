/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import java.io.Serializable;

/**
 * AdministradorDTO implementa Serializable
 * @author estudiante
 */
public class AdministradorDTO extends PersonaDTO implements Serializable {

    
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * Nivel que tiene un administrador, 
     * nivel = {1, 2, 3, 4, 5}
     */
    private Integer nivel;
    
    /**
     * Nombre del cargo del administrador.
     */
    private String nombreCargo;
    
    /**
     * Nombre de la dependencia.
     */
    private DependenciaDTO dependencia;

    /**
     * Constructor AdministradorDTO vacio
     */
    public AdministradorDTO() {
    }

    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public AdministradorDTO( AdministradorEntity entity ){
        if(entity != null) {
            this.id = entity.id;
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return AdministradorEntity
     */
    public AdministradorEntity toEntity() {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setDependencia(this.dependencia.toEntity()); 
        entity.setNivel(this.nivel);
        entity.setNombreCargo(this.nombreCargo);
        entity.setId(this.id);
        return entity;
    }
    
    /**
     * @return the nivel
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCargo() {
        return nombreCargo;
    }

    /**
     * @param nombreCargo the nombreCargo to set
     */
    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    /**
     * @return the dependencia
     */
    public DependenciaDTO getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(DependenciaDTO dependencia) {
        this.dependencia = dependencia;
    }
   
}
