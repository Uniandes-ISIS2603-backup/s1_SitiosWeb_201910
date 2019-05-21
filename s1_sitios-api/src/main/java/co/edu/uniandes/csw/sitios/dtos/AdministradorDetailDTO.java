/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allan Roy Corinaldi
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {
    
    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________

    /**
     * Relacion de 0 a muchos Cambios.
     */
    private List<CambioDTO> cambios;
    
    /**
     * Relacion de 0 a muchas Notificaciones.
     */
    private List<NotificacionDTO> notificaciones;  
    
    /**
     * Sitios web que administra.
     */
    private List <SitioWebDTO> sitiosWeb;
    
    
    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________

    /**
     * 
     */
    public AdministradorDetailDTO() {
        super();
    }

    /**
     * Crea un objeto AdministradorDetailDTO a partir de un objeto
     * AdministradorEntity incluyendo los atributos de AdministradorDTO.
     *
     * @param administradorEntity Entidad AdministradorEntity desde la cual se
     * va a crear el nuevo objeto.
     *
     */
    public AdministradorDetailDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null)
        {
            notificaciones = new ArrayList<>();
            for (NotificacionEntity entityNotificacion : administradorEntity.getNotificaciones()) {
                notificaciones.add(new NotificacionDTO(entityNotificacion));
             }
             cambios = new ArrayList<>();
            for (CambioEntity cambio : administradorEntity.getCambios()) {
                cambios.add(new CambioDTO(cambio));
             }
            
            sitiosWeb = new ArrayList<>();
            for (SitioWebEntity sitio : administradorEntity.getSitiosWeb()) {
                sitiosWeb.add(new SitioWebDTO(sitio));
             }
            
        }
    }
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________

    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity
     * incluyendo los atributos de AdministradorDTO.
     *
     * @return Nueva objeto AdministradorEntity.
     *
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
        
        if (getNotificaciones() != null) 
        {
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificaciones : getNotificaciones())
            {
                notificacionEntity.add(dtoNotificaciones.toEntity());
            }
            administradorEntity.setNotificaciones(notificacionEntity);
        }
        
        if (getCambios() != null) 
        {
            List<CambioEntity> cambiosEntity = new ArrayList<>();
            for (CambioDTO cambioDTO : getCambios()) 
            {
                cambiosEntity.add(cambioDTO.toEntity());
            }
            administradorEntity.setCambios(cambiosEntity);
        }
        
        if (getSitiosWeb()!= null) 
        {
            List<SitioWebEntity> sitiosWebEntity = new ArrayList<>();
            for (SitioWebDTO sitioDTO : getSitiosWeb()) 
            {
                sitiosWebEntity.add(sitioDTO.toEntity());
            }
            administradorEntity.setSitiosWeb(sitiosWebEntity);
        }
        
        return administradorEntity;
    }

    /**
     * @return the notificaciones
     */
    public List<NotificacionDTO> getNotificaciones() {
        return notificaciones;
    }

    /**
     * @param notificaciones the notificaciones to set
     */
    public void setNotificaciones(List<NotificacionDTO> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * @return the cambios
     */
    public List<CambioDTO> getCambios() {
        return cambios;
    }

    /**
     * @param cambios the cambios to set
     */
    public void setCambios(List<CambioDTO> cambios) {
        this.cambios = cambios;
    }
    
    
    /**
     * @return sitiosWeb of Administrador
     */
    public List<SitioWebDTO> getSitiosWeb() 
    {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb to assign
     */
    public void setSitiosWeb(List<SitioWebDTO> sitiosWeb) {    
        this.sitiosWeb = sitiosWeb;
    }

    

}
