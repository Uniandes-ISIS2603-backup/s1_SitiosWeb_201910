/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.PersonaEntity;
import java.io.Serializable;

/**
 *
 * @author Albert Molano
 */
public class PersonaDTO implements Serializable{
    
    protected Long id;
    
    protected String nombre;
    
    protected String email;
    
    protected String password;
    
    protected Long telefono;

    public PersonaDTO()
    {
        
    }
    
    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public PersonaDTO( PersonaEntity entity ){
        if(entity != null) {
            this.nombre = entity.getNombre();
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return PersonaEntity
     */
    public PersonaEntity toEntity() {
        PersonaEntity entity = new PersonaEntity();
        entity.setNombre(this.nombre);
        entity.setEmail(this.email);
        entity.setId(this.id);
        entity.setPassword(this.password);
        entity.setTelefono(this.telefono);
        return entity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    
    
}
