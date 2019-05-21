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
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author Allan Roy Corinaldi
 */
@Entity
public class AdministradorEntity extends PersonaEntity implements Serializable {

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * Nivel que tiene un administrador, nivel = {1, 2, 3, 4, 5}.
     */
    @PodamIntValue(minValue = 1,maxValue = 5)
    private Integer nivel;

    /**
     * Nombre del cargo del administrador.
     */
    @PodamStringValue(length = 6)
    private String nombreCargo;

    /**
     * Nombre de la dependencia.
     */
    @PodamExclude 
    @ManyToOne
    private DependenciaEntity dependencia;
    
    /**
     * Sitio Web que administra.
     */
    @PodamExclude
    @ManyToMany
    private List<SitioWebEntity> sitiosWeb;

     /**
     * Collecion de cambios en la bitacora de un administrador.
     */
    @PodamExclude
    @OneToMany(mappedBy = "administrador")
    private List<CambioEntity> cambios;

    /**
     * Collecion de notificaciones.
     */
    @PodamExclude
    @OneToMany(mappedBy = "notificado")
    private List<NotificacionEntity> notificaciones;
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________    

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
     * @return the sitiosWebEntity
     */
    public List<SitioWebEntity> getSitiosWeb() {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb the sitiosWebEntity to set
     */
    public void setSitiosWeb(List<SitioWebEntity> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
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
}
