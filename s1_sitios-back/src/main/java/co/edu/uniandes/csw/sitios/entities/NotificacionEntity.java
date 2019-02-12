/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;

/**
 *
 * @author Albert Molano
 */
public class NotificacionEntity extends BaseEntity implements Serializable {

    public Persona getNotificado() {
        return notificado;
    }

    public void setNotificado(Persona notificado) {
        this.notificado = notificado;
    }

    public EstadoWeb getCambioSitio() {
        return cambioSitio;
    }

    public void setCambioSitio(EstadoWeb cambioSitio) {
        this.cambioSitio = cambioSitio;
    }

    public SitioWeb getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(SitioWeb sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
    
  
  
    /**
     * Persona a la cual se debe notificar
     */
    private Persona notificado;
    
    /**
     * nuevo estado al cual se cambio el sitio web
     */
    private EstadoWeb cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio
     */
    private SitioWeb sitioWeb;
   
}
