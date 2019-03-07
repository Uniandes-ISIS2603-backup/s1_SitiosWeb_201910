/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Entity
public class AdministradorEntity extends PersonaEntity implements Serializable {

    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    /**
     * Nivel que tiene un administrador, nivel = {1, 2, 3, 4, 5}
     */
    private Integer nivel;

    /**
     * Nombre del cargo del administrador.
     */
    private String nombreCargo;

    /**
     * Nombre de la dependencia.
     */
    @PodamExclude
    @OneToOne
    private DependenciaEntity dependencia;

    /**
     * Colleccion de cambios.
     */
    @PodamExclude
    @OneToMany
    private List<CambioEntity> cambios;

    /**
     * Collecion de notificaciones
     */
    @PodamExclude
    @OneToMany
    private List<NotificacionEntity> notificaciones;

    @PodamExclude
    @ManyToOne
    private SitioWebEntity sitioWeb;

    /**
     * Constructor AdministradorEntity vacio
     */
    public AdministradorEntity() {
    }

    /**
     * @return the nivel
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the nombreCargo
     */
    public String getNombreCargo() {
        return nombreCargo;
    }

    /**
     * @param nombreCargo the nombreCargo to set
     */
    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    /**
     * @return the dependencia
     */
    public DependenciaEntity getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(DependenciaEntity dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * @return the notificaciones
     */
    public List<NotificacionEntity> getNotificaciones() {
        return notificaciones;
    }

    /**
     * @param notificaciones the notificaciones to set
     */
    public void setNotificaciones(List<NotificacionEntity> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * @return the cambios
     */
    public List<CambioEntity> getCambios() {
        return cambios;
    }

    /**
     * @param cambios the cambios to set
     */
    public void setCambios(List<CambioEntity> cambios) {
        this.cambios = cambios;
    }

    /**
     * @return the sitioWebEntity
     */
    public SitioWebEntity getSitioWeb() {
        return sitioWeb;
    }

    /**
     * @param sitioWebEntity the sitioWebEntity to set
     */
    public void setSitioWeb(SitioWebEntity sitioWebEntity) {
        this.sitioWeb = sitioWebEntity;
    }
}
