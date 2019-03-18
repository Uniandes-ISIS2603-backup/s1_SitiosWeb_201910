/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

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
    
    
}
