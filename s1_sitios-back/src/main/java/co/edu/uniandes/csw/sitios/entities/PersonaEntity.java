/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class PersonaEntity extends BaseEntity {
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * Atributo de identidad de la persona
     */
    public Long id;
    
    /**
     * Atributo de nombre de la persona
     */
    protected String nombre;
    
    /**
     * Atributo de email de la persona
     */
    private String email;
    
    /**
     * Atributo de la contrasenia de la persona
     */
    protected String password;
    
    /**
     * Atributo del telefono de la persona
     */
    protected Long telefono;

    /**
     * Constructor PersonaEntity vacio
     */
    public PersonaEntity() {
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the telefono
     */
    public Long getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    
    
}