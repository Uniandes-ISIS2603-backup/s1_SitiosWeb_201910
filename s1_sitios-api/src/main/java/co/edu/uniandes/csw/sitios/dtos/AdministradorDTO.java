/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;

/**
 * AdministradorDTO implementa Serializable
 * @author estudiante
 */
public class AdministradorDTO implements Serializable{
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * Nivel que tiene un administrador, 
     * nivel = {1, 2, 3, 4, 5}
     */
    private int nivel;
    
    /**
     * Nombre del cargo del administrador.
     */
    private String nombreCargo;
    
    /**
     * Nombre de la dependencia.
     */
    private DependenciaDTO dependencia;
    
    /**
     * Bitacora que se usa para guardar eventos.
     */
    private EventoDTO bitacora;

    /**
     * Constructor AdministradorDTO vacio
     */
    public AdministradorDTO() {
    }

    
    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
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
    public DependenciaDTO getDependencia() {
        return dependencia;
    }

    /**
     * @param dependencia the dependencia to set
     */
    public void setDependencia(DependenciaDTO dependencia) {
        this.dependencia = dependencia;
    }

    /**
     * @return the evento
     */
    public EventoDTO getEvento() {
        return bitacora;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(EventoDTO evento) {
        this.bitacora = evento;
    }
    
}
