/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.util.List;

/**
 * UsuarioDetailDTO implementa UsuarioDTO
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO{
    
    /**
     * Lista de tickets que puede tener un usuario.
     */
    public List<TicketDTO> tickets;

    /**
     *Constructor de UsuarioDetailDTO vacio
     */
    public UsuarioDetailDTO() {
    }
    
    
}
