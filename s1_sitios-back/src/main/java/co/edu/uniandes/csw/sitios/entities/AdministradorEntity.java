/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.util.Collection;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class AdministradorEntity extends PersonaEntity{
   
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * Nivel que tiene un administrador, 
     * nivel = {1, 2, 3, 4, 5}
     */
    private Integer nivel;
    
    /**
     * Nombre del cargo del administrador.
     */
    private String nombreCargo; 
    
    /**
     * Nombre de la dependencia.
     */
    private DependenciaEntity dependencia;
    
   
    
    /**
     * Collecion de notificaciones
     */
    @javax.persistence.OneToOne(
        mappedBy = "administrador", //verificar.
        fetch = javax.persistence.FetchType.LAZY
    )
    private Collection<NotificacionEntity> notificaciones;
    
     @javax.persistence.ManyToOne(
    )
     
    
    private SitioWebEntity sitioWebEntity; //Verificar
    
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
    public Collection<NotificacionEntity> getNotificaciones() {
        return notificaciones;
    }

    /**
     * @param notificaciones the notificaciones to set
     */
    public void setNotificaciones(Collection<NotificacionEntity> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * @return the sitioWebEntity
     */
    public SitioWebEntity getSitioWebEntity() {
        return sitioWebEntity;
    }

    /**
     * @param sitioWebEntity the sitioWebEntity to set
     */
    public void setSitioWebEntity(SitioWebEntity sitioWebEntity) {
        this.sitioWebEntity = sitioWebEntity;
    }

}
