/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Albert Adolfo Molano Cubillos
 */
public class NotificacionDTO  implements Serializable{
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________

    
    /**
     * identificador unico de la notificacion dentro del sitio web.
     */
    private Long id;
            
    /**
     * Persona a la cual se debe notificar.
     */
    private AdministradorDTO notificado;

   
    /**
     * nuevo estado al cual se cambio el sitio web.
     */
    private EstadoWebDTO cambioSitio;
    
    /**
     * Sitio en el cual se presento el cambio.
     */
    private SitioWebDTO sitioWeb;
    
    //__________________________________________________________________________
    //Constructores
    //__________________________________________________________________________
    
    /**
     * Constructor vacio.
     */
    public NotificacionDTO()
    {
    }
    
    /**
     * Crea un objeto NotificacionDTO a partir de un objeto NotificacionEntity.
     * 
     * @param entity NotificacionEntity desde la cual se va a crear el 
     * nuevo objeto
     */
    public NotificacionDTO(NotificacionEntity entity)
    {
        if(entity!=null)
        {
            this.id=entity.getId();
            if (entity.getNotificado() != null) 
            {
                this.notificado = new AdministradorDTO(entity.getNotificado());
            } 
            else 
            {
                this.notificado = null;
            }
            if (entity.getCambioSitio()!= null) 
            {
                this.cambioSitio = new EstadoWebDTO(entity.getCambioSitio());
            } 
            else 
            {
                this.cambioSitio = null;
            }
            if (entity.getSitioWeb()!= null) 
            {
                this.sitioWeb = new SitioWebDTO(entity.getSitioWeb());
            } 
            else 
            {
                this.sitioWeb = null;
            }
        }
    }
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * metodo que convierte un DTO a entidad.
     * 
     * @return entity la entidad de la notificacion
     */
    public NotificacionEntity toEntity()
    {
        NotificacionEntity entity= new NotificacionEntity();
        
        entity.setId(this.id);
        if (this.getNotificado() != null) 
        {
            entity.setNotificado(this.notificado.toEntity());
        }
        if (this.getCambioSitio()!= null) 
        {
            entity.setCambioSitio(this.cambioSitio.toEntity());
        }
        if (this.getSitioWeb()!= null) 
        {
            entity.setSitioWeb(this.sitioWeb.toEntity());
        }
        
        return entity;
    }
    
    /**
     * devuelve el id de la notifiacion.
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a la notificacion
     * 
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * obtiene el nuevo estado al que cambio el sitio web
     * @return cambioSitio
     */
    public EstadoWebDTO getCambioSitio() {
        return cambioSitio;
    }

    /**
     * asigna el esrtado al cual va a cambiar el sitio web
     * @param cambioSitio 
     */
    public void setCambioSitio(EstadoWebDTO cambioSitio) {
        this.cambioSitio = cambioSitio;
    }

    /**
     * obtiene el  sitio web al cual se le hace el cambio
     * @return sitioWeb
     */
    public SitioWebDTO getSitioWeb() {
        return sitioWeb;
    }

    /**
     * asigna el sitio web al cual se le hace el cambio
     * @param sitioWeb 
     */
    public void setSitioWeb(SitioWebDTO sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    /**
     * obtiene la persona que administra el sitio y a quien por ende
     * se le notificara del cambio
     * @return notificado
     */
    public AdministradorDTO getNotificado() {
        return notificado;
    }

    /**
     * asigna la persona que administra el sitio y a quien por ende
     * se le notificara del cambio
     * @param notificado 
     */
    public void setNotificado(AdministradorDTO notificado) {
        this.notificado = notificado;
    }
    
    
    /**
     * se sobreescribe el to string
     * 
     * @return 
     */
     @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
}
