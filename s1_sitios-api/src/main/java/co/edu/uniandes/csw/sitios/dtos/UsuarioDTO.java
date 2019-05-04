/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * UsuarioDTO implementa Serializable
 *
 * @author Allan Roy Corinaldi
 */
public class UsuarioDTO implements Serializable {

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
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
     * nombre de usuario, String != ("" y null)
     */
    private String userName;

    /**
     * numero de tickets, int >= 0
     */
    private Integer numeroTickets;
    
    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________

    /**
     * Constructor UsuarioDTO vacio
     */
    public UsuarioDTO() {

    }

    /**
     * Constructor que se usa para checkear la entidad del DTO
     *
     * @param entity != null
     */
    public UsuarioDTO(UsuarioEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.userName = entity.getUserName();
            this.numeroTickets = entity.getNumeroTickets();
            this.email = entity.getEmail();
            this.nombre = entity.getNombre();
            this.password = entity.getPassword();
            this.telefono = entity.getTelefono();
        }
    }
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Chequeo de la implementacion toEntity del DTO
     *
     * @return UsuarioEntity
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNumeroTickets(this.getNumeroTickets());
        entity.setUserName(this.getUserName());
        entity.setEmail(this.getEmail());
        entity.setNombre(this.getNombre());
        entity.setPassword(this.getPassword());
        entity.setTelefono(this.getTelefono());
        return entity;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the numeroTickets
     */
    public Integer getNumeroTickets() {
        return numeroTickets;
    }

    /**
     * @param numeroTickets the numeroTickets to set
     */
    public void setNumeroTickets(Integer numeroTickets) {
        this.numeroTickets = numeroTickets;
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
    
    /**
     * se sobreescribe el to string
     * 
     * @return 
     */
     @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
