/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.AdministradorEntity;
import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.NotificacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allan Roy Corinaldi
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
    
    /**
     * Dependencia
     */
    private DependenciaDTO dependencia;

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
            if (administradorEntity.getDependencia() != null) {
            this.dependencia = new DependenciaDTO(administradorEntity.getDependencia());
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
        if (getNotificaciones() != null) {
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNotificaciones : getNotificaciones()) {
                notificacionEntity.add(dtoNotificaciones.toEntity());
            }
            administradorEntity.setNotificaciones(notificacionEntity);
        }
        if (getCambios() != null) {
            List<CambioEntity> cambiosEntity = new ArrayList<>();
            for (CambioDTO cambioDTO : getCambios()) {
                cambiosEntity.add(cambioDTO.toEntity());
            }
            administradorEntity.setCambios(cambiosEntity);
        }
        if (getDependencia() != null) {
            administradorEntity.setDependencia(getDependencia().toEntity());
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
     * @return Dependencia asociada al administrador
     */
    public DependenciaDTO getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia Dependencia por asociar al administrador
     */
    public void setDependencia(DependenciaDTO dependencia) {
        this.dependencia = dependencia;
    }

}
