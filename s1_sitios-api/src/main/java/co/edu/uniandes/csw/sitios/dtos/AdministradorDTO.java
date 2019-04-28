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
 *
 * @author Allan Roy Corinaldi.
 */
public class AdministradorDTO implements Serializable {

    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    /**
     * Id del usuario
     */
    private Long id;

    /**
     * Nombre del usuario
     */
    private String nombre;

    /**
     * Email del usuario
     */
    private String email;

    /**
     * Contraseñ¡nia del usuario
     */
    private String password;

    /**
     * Telefono del usuario
     */
    private String telefono;

    /**
     * Nivel que tiene un administrador, nivel = {1, 2, 3, 4, 5}
     */
    private Integer nivel;

    /**
     * Nombre del cargo del administrador.
     */
    private String nombreCargo;

    /**
     * Sitio web.
     */
    private SitioWebDTO sitioWeb;

    /**
     * Constructor AdministradorDTO vacio
     */
    public AdministradorDTO() {
    }

    /**
     * Constructor que se usa para checkear la entidad del DTO
     *
     * @param entity != null
     */
    public AdministradorDTO(AdministradorEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombreCargo = entity.getNombreCargo();
            this.nivel = entity.getNivel();
            this.email = entity.getEmail();
            this.nombre = entity.getNombre();
            this.password = entity.getPassword();
            this.telefono = entity.getTelefono();
            if(entity.getSitioWeb() != null){
                this.sitioWeb = new SitioWebDTO(entity.getSitioWeb());
            } else {
                this.sitioWeb = null;
            }
        }
    }

    /**
     * Chequeo de la implementacion toEntity del DTO
     *
     * @return AdministradorEntity
     */
    public AdministradorEntity toEntity() {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setNivel(this.getNivel());
        entity.setNombreCargo(this.getNombreCargo());
        entity.setId(this.getId());
        entity.setEmail(this.getEmail());
        entity.setNombre(this.getNombre());
        entity.setPassword(this.getPassword());
        entity.setTelefono(this.getTelefono());
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
    public void setNivel(Integer nivel) {
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
