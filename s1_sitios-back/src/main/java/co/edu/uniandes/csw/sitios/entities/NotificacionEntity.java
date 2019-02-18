/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 *
 * @author Albert Molano
 */
public class NotificacionEntity extends BaseEntity implements Serializable {

    public PersonaEntity getNotificado() {
        return notificado;
    }

    public void setNotificado(PersonaEntity notificado) {
        this.notificado = notificado;
    }

    public EstadoWebEntity getCambioSitio() {
        return cambioSitio;
    }

    public void setCambioSitio(EstadoWebEntity cambioSitio) {
        this.cambioSitio = cambioSitio;
    }

    public SitioWebEntity getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(SitioWebEntity sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
    
  
  
    /**
     * Persona a la cual se debe notificar
     */
    @PodamExclude
    @ManyToMany
    private PersonaEntity notificado;
    
    /**
     * nuevo estado al cual se cambio el sitio web
     */
    @PodamExclude
    @ManyToMany
    //TODO CERTIFICATE THIS
    private EstadoWebEntity cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio
     */
    @PodamExclude
    @ManyToMany
    private SitioWebEntity sitioWeb;
   
}
