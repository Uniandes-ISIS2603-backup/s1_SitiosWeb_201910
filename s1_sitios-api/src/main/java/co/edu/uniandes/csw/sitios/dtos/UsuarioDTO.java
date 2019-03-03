/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.UsuarioEntity;
import java.io.Serializable;

/**
 * UsuarioDTO implementa Serializable
 * @author estudiante
 */
public class UsuarioDTO extends PersonaDTO implements Serializable{
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    /**
     * nombre de usuario, String != ("" y null)
     */
    private String userName;
    
    /**
     * numero de tickets, int >= 0
     */
    private Integer numeroTickets;
    
    /**
     * Constructor UsuarioDTO vacio
     */
    public UsuarioDTO(){
         
    }
    
    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public UsuarioDTO( UsuarioEntity entity ){
        if(entity != null) {
            this.id = entity.id;
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return UsuarioEntity
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNumeroTickets(this.numeroTickets);
        entity.setUserName(this.userName);
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
  
    
}
