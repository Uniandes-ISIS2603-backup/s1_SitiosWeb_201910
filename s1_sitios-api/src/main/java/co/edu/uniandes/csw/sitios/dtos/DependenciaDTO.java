/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import java.io.Serializable;

/**
 * DependenciaDTO implementa Serializable
 * @author estudiante
 */
public class DependenciaDTO implements Serializable{
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * id de la dependencia, id>0.
     */
    private Long id;
    
    /**
     * nombre de la dependencia.
     */
    private String nombre;
    
    /**
     * email de la dependencia
     */
    private String email;
    
    /**
     * telefono de la dependencia
     */
    private String telefono;
    
    /**
     * Constructor DependenciDTO vacio.
     */
    public DependenciaDTO() {
    }

    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public DependenciaDTO( DependenciaEntity entity ){
        if(entity != null) {
            this.id = entity.id;
            this.email = entity.getEmail();
            this.nombre = entity.getNombreDependencia();
            this.telefono = entity.getTelefono();
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return DependenciaEntity
     */
    public DependenciaEntity toEntity() {
        DependenciaEntity entity = new DependenciaEntity();
        entity.setEmail(this.email);
        entity.setId(this.id);
        entity.setNombreDependencia(this.nombre);
        entity.setTelefono(this.telefono);
        return entity;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombreDependencia
     */
    public String getNombreDependencia() {
        return nombre;
    }

    /**
     * @param nombreDependencia the nombreDependencia to set
     */
    public void setNombreDependencia(String nombreDependencia) {
        this.nombre = nombreDependencia;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

    
    
