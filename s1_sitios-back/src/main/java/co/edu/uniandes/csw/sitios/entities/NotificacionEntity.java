/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;


/**
 *
 * @author Albert Molano
 */
@Entity
public class NotificacionEntity extends BaseEntity implements Serializable {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
     /**
     * Persona a la cual se debe notificar
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    private AdministradorEntity notificado;
    
    /**
     * nuevo estado al cual se cambio el sitio web
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST) 
    private EstadoWebEntity cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private SitioWebEntity sitioWeb;
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
    /**
     * ontiene el administrador que es notificado 
     * @return notificado
     */
    public AdministradorEntity getNotificado() {
        return notificado;
    }

    /**
     * asigna el administrador que se notificara
     * @param notificado 
     */
    public void setNotificado(AdministradorEntity notificado) {
        this.notificado = notificado;
    }

    /**
     * obtiene el cambio de estado que hubo en el sitio al cual corresponde
     * la notificacion
     * @return cambioSitio
     */
    public EstadoWebEntity getCambioSitio() {
        return cambioSitio;
    }

    /**
     * asigna el cambio de estado que hubo en el sitio al cual corresponde
     * la notificacion
     * @param cambioSitio 
     */
    public void setCambioSitio(EstadoWebEntity cambioSitio) {
        this.cambioSitio = cambioSitio;
    }

    /**
     * obtiene el sitioWeb al cual se le hizo el cambio
     * @return sitioWeb
     */
    public SitioWebEntity getSitioWeb() {
        return sitioWeb;
    }

    /**
     * asigna el sitioWeb al cual se le hizo el cambio
     * @param sitioWeb 
     */
    public void setSitioWeb(SitioWebEntity sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
   
}