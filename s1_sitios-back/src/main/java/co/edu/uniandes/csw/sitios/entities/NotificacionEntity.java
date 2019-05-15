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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Albert Molano
 */
@Entity
public class NotificacionEntity extends BaseEntity implements Serializable {

    public AdministradorEntity getNotificado() {
        return notificado;
    }

    public void setNotificado(AdministradorEntity notificado) {
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
    @ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    private AdministradorEntity notificado;
    
    /**
     * nuevo estado al cual se cambio el sitio web
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST) 
    private EstadoWebEntity cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio
     */
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private SitioWebEntity sitioWeb;
   
}