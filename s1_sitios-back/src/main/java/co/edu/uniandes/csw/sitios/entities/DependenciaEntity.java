/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author estudiante
 */
@Entity //Le damos el superpoder de que pueda persistir
public class DependenciaEntity extends BaseEntity {
    
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
    @PodamStringValue(length = 9)
    private String telefono;
    
    
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @javax.persistence.OneToOne(
        mappedBy = "dependencia"
    )
    private AdministradorEntity encargadoDependencia;
    
    
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
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the administrador
     */
    public AdministradorEntity getEncargadoDependencia() {
        return encargadoDependencia;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setEncargadoDependencia(AdministradorEntity encargadoDependencia) {
        this.encargadoDependencia = encargadoDependencia;
    }
    
}
