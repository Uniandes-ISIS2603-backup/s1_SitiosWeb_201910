/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;

/**
 * DependenciaDTO implementa Serializable
 * @author estudiante
 */
public class DependenciaDTO implements Serializable{
    
    //-------------------------------------
    // Atributos---------------------------
    //-------------------------------------
    
    /**
     * id de la dependencia, id>0.
     */
    private int id;
    
    /**
     * nombre de la dependencia.
     */
    private String nombreDependencia;
    
    /**
     * email de la dependencia
     */
    private String email;
    
    /**
     * telefono de la dependencia
     */
    private int telefono;
    
    /**
     * Persona a cargo de la dependencia
     */
    private AdministradorDTO encargadoDependencia;

    /**
     * Constructor DependenciDTO vacio.
     */
    public DependenciaDTO() {
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombreDependencia
     */
    public String getNombreDependencia() {
        return nombreDependencia;
    }

    /**
     * @param nombreDependencia the nombreDependencia to set
     */
    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the encargadoDependencia
     */
    public AdministradorDTO getEncargadoDependencia() {
        return encargadoDependencia;
    }

    /**
     * @param encargadoDependencia the encargadoDependencia to set
     */
    public void setEncargadoDependencia(AdministradorDTO encargadoDependencia) {
        this.encargadoDependencia = encargadoDependencia;
    }
}

    
    
