/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TicketEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AdministradorDetailDTO extends AdministradorDTO{
    
    /**
     * Notificaciones.
     */
    private List<NotificacionDTO> notificaciones;
    
    /**
     * Sitios web.
     */
    private List<SitioWebDTO> sitiosWeb;

    public AdministradorDetailDTO() {
        super();
    }
    
     /**
     * Crea un objeto AdministradorDetailDTO a partir de un objeto AdministradorEntity
     * incluyendo los atributos de AdministradorDTO.
     *
     * @param administradorEntity Entidad AdministradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public AdministradorDetailDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            notificaciones = new ArrayList<>();
            for (NotificacionEntity entityNotificacion : administradorEntity.getNotificaciones()) {
                notificaciones.add(new NotificacionDTO(entityNotificacion));
            }
            sitiosWeb = new ArrayList();
            for (SitioWebEntity entitySitioWeb : administradorEntity.getSitioWebEntity()) {
                sitiosWeb.add(new SitioWebDTO(entitySitioWeb));
            }
        }
    }
    
    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity incluyendo los
     * atributos de AdministradorDTO.
     *
     * @return Nueva objeto AdministradorEntity.
     *
     */
    @Override
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = super.toEntity();
        if (notificaciones != null) {
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificaciones : notificaciones) {
                notificacionEntity.add(dtoNotificaciones.toEntity());
            }
            administradorEntity.setNotificaciones(notificacionEntity);
        }
        if (sitiosWeb != null) {
            List<SitioWebEntity> sitiosWebEntity = new ArrayList<>();
            for (SitioWebDTO dtoPrize : sitiosWeb) {
                sitiosWebEntity.add(dtoPrize.toEntity());
            }
            administradorEntity.setSitioWebEntity(sitiosWebEntity);
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
     * @return the sitiosWeb
     */
    public List<SitioWebDTO> getSitiosWeb() {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb the sitiosWeb to set
     */
    public void setSitiosWeb(List<SitioWebDTO> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }
    
    
}
