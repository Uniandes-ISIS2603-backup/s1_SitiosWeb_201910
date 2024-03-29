/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import co.edu.uniandes.csw.sitios.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author s.ballesteros
 */
@Entity
public class CambioEntity extends BaseEntity implements Serializable {

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
    @PodamStrategyValue(DateStrategy.class)
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
     * Cambio nuevo que se va a realizar
     */
    private Long idAsociado;

    /**
     * Sitio en el cual se presento el cambio
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private SitioWebEntity sitioWeb;

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

    /**
     * @return the sitioWeb
     */
    public SitioWebEntity getSitioWeb() {
        return sitioWeb;
    }

    /**
     * @param sitioWeb the sitioWeb to set
     */
    public void setSitioWeb(SitioWebEntity sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
}
