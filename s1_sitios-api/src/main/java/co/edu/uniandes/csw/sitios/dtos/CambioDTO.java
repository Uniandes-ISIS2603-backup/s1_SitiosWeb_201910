/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author s.ballesteros
 */
public class CambioDTO implements Serializable{
     
        /**
     * Lugar de donde se registra el Cambio
     */    
    private Long id;
    
            /**
     * Lugar de donde se registra el Cambio
     */    
    private Long idAsociado;
    
    /**
     * Lugar de donde se registra el Cambio
     */    
    private String lugarCambio;

    /**
     * Descripciòn del cambio que se esta haciendo
     */    
    private String descripcion;
    
    /**
     * Fecha en la cual se hizo el cambio en Bitacora
     */    
    private Date fechaCambio;
    
    /**
     * Cambio previo que se hizo
     */    
    private String previo;
    
    /**
     * Cambio nuevo que se va a realizar
     */    
    private String nuevo;
    
    
       public CambioDTO(CambioEntity entity)
    {
        if(entity!=null)
        {
        this.id = entity.getId();
        this.descripcion=entity.getDescripcion();
        this.fechaCambio=entity.getFechaCambio();
        this.lugarCambio=entity.getLugarCambio();
        this.nuevo=entity.getNuevo();
        this.previo=entity.getPrevio();
        this.idAsociado=entity.getIdAsociado();
        }
    }
    
    public CambioEntity toEntity()
    {
        CambioEntity entity= new CambioEntity();
        entity.setId(this.id);
        entity.setDescripcion(this.descripcion);
        entity.setFechaCambio(this.fechaCambio);
        entity.setIdAsociado(this.idAsociado);
        entity.setNuevo(this.nuevo);
        entity.setPrevio(this.previo);
        entity.setLugarCambio(this.lugarCambio);
        return entity;
    }
    /**
     * @return the lugarCambio
     */
    public String getLugarCambio() {
        return lugarCambio;
    }

    /**
     * @param lugarCambio the lugarCambio to set
     */
    public void setLugarCambio(String lugarCambio) {
        this.lugarCambio = lugarCambio;
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
     * @return the fechaCambio
     */
    public Date getFechaCambio() {
        return fechaCambio;
    }

    /**
     * @param fechaCambio the fechaCambio to set
     */
    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    /**
     * @return the previo
     */
    public String getPrevio() {
        return previo;
    }

    /**
     * @param previo the previo to set
     */
    public void setPrevio(String previo) {
        this.previo = previo;
    }

    /**
     * @return the nuevo
     */
    public String getNuevo() {
        return nuevo;
    }

    /**
     * @param nuevo the nuevo to set
     */
    public void setNuevo(String nuevo) {
        this.nuevo = nuevo;
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
     * @return the idAsociado
     */
    public Long getIdAsociado() {
        return idAsociado;
    }

    /**
     * @param idAsociado the idAsociado to set
     */
    public void setIdAsociado(Long idAsociado) {
        this.idAsociado = idAsociado;
    }
    
    
}
