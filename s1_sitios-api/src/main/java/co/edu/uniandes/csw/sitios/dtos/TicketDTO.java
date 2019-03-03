/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * TicketDTO implementa Serializable
 * @author estudiante
 */
public class TicketDTO implements Serializable{

    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * descripcion de por que se saco
     * el ticket.
     */
    private String descripcion;
    
    /**
     * fecha que se saco el ticket,
     * diferente de null.
     */
    private Date fecha;
    
    /**
     * estado del ticket, tiene 3: 0, 1, 2.
     */
    private Integer estado; //Solo 3 estados
    
    /**
     * Constructor TicketDTO vacio
     */
    public TicketDTO() {
    }
    
    /**
     * Constructor que se usa para checkear la entidad
     * del DTO
     * @param entity != null
     */
    public TicketDTO( TicketEntity entity ){
        if(entity != null) {
            this.descripcion = entity.getDescripcion();
        }
    }
    
    /**
     * Chequeo de la implementacion toEntity del DTO
     * @return TicketEntity
     */
    public TicketEntity toEntity() {
        TicketEntity entity = new TicketEntity();
        entity.setDescripcion(this.descripcion);
        entity.setEstado(this.estado);
        entity.setFecha(this.fecha);
        return entity;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
