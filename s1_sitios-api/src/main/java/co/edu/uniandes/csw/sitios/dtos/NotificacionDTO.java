/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import java.io.Serializable;

/**
 *
 * @author Albert Adolfo Molano Cubillos
 */
public class NotificacionDTO  implements Serializable{

    
    /**
     * identificador unico de la notificacion dentro del sitio web
     */
    private Long id;
            
    /**
     * Persona a la cual se debe notificar
     */
    private PersonaDTO notificado;

   
    /**
     * nuevo estado al cual se cambio el sitio web
     */
    private EstadoWebDTO cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio
     */
    private SitioWebDTO sitioWeb;
    
    public NotificacionDTO(NotificacionEntity entity)
    {
        if(entity!=null)
        {
        this.id=entity.getId();
        }
    }
    
    public NotificacionEntity toEntity()
    {
        NotificacionEntity entity= new NotificacionEntity();
        entity.setId(this.id);
        return entity;
    }
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EstadoWebDTO getCambioSitio() {
        return cambioSitio;
    }

    public void setCambioSitio(EstadoWebDTO cambioSitio) {
        this.cambioSitio = cambioSitio;
    }

    public SitioWebDTO getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(SitioWebDTO sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public PersonaDTO getNotificado() {
        return notificado;
    }

    public void setNotificado(PersonaDTO notificado) {
        this.notificado = notificado;
    }
    
    
    
}
