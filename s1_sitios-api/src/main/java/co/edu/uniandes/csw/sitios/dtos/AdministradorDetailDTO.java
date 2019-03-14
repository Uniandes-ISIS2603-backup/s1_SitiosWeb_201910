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
 * @author estudiante
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {

    /**
     * Notificaciones.
     */
    private List<NotificacionDTO> notificaciones;

    /**
     * Cambios
     */
    private List<CambioDTO> cambios;

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
        if (administradorEntity != null) {
            notificaciones = new ArrayList<>();
            for (NotificacionEntity entityNotificacion : administradorEntity.getNotificaciones()) {
                notificaciones.add(new NotificacionDTO(entityNotificacion));
            }
            cambios = new ArrayList<>();
            for (CambioEntity cambio : administradorEntity.getCambios()) {
                cambios.add(new CambioDTO(cambio));
            }
        }
    }

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
        if (notificaciones != null) {
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificaciones : notificaciones) {
                notificacionEntity.add(dtoNotificaciones.toEntity());
            }
            administradorEntity.setNotificaciones(notificacionEntity);
        }
        if (cambios != null) {
            List<CambioEntity> cambiosEntity = new ArrayList<>();
            for (CambioDTO cambioDTO : cambios) {
                cambiosEntity.add(cambioDTO.toEntity());
            }
            administradorEntity.setCambios(cambiosEntity);
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

}
