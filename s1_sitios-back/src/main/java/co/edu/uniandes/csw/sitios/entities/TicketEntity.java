/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class TicketEntity extends BaseEntity {

    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    /**
     * descripcion de por que se saco el ticket.
     */
    private String descripcion;

    /**
     * fecha que se saco el ticket, diferente de null.
     */
    private Date fecha;

    /**
     * estado del ticket, tiene 3: 0, 1, 2.
     */
    private Integer estado; //Solo 3 estados

    /**
     *
     */
    @javax.persistence.ManyToOne
    private UsuarioEntity usuarioEntity;

    /**
     * Constructor TicketEntity vacio
     */
    public TicketEntity() {
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
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    /**
     * @return the usuarioEntity
     */
    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    /**
     * @param usuarioEntity the usuarioEntity to set
     */
    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
}
