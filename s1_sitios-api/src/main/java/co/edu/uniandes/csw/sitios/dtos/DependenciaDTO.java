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
    private String nombreDependencia;
    
    /**
     * email de la dependencia
     */
    private String email;
    
    /**
     * telefono de la dependencia
     */
    private String telefono;
    
    /**
     * Persona a cargo de la dependencia
     */
    private AdministradorDTO encargadoDependencia;

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
            this.nombreDependencia = entity.getNombreDependencia();
            this.telefono = entity.getTelefono();
            if( entity.getEncargadoDependencia() != null ){
                this.encargadoDependencia = new AdministradorDTO(entity.getEncargadoDependencia());
            }
            else{
                encargadoDependencia = null;
            }
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
        entity.setNombreDependencia(this.nombreDependencia);
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
        return nombreDependencia;
    }

    /**
     * @param nombreDependencia the nombreDependencia to set
     */
    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
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

    /**
     * @return the encargadoDependencia
     */
    public AdministradorDTO getEncargadoDependencia() {
        return encargadoDependencia;
    }

    /**
     * @param encargadoDependencia the encargadoDependencia to set
     */
    public void setEncargadoDependencia(AdministradorDTO encargadoDependencia) {
        this.encargadoDependencia = encargadoDependencia;
    }
}

    
    
