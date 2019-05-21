/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
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
    @PodamStringValue(length = 10)
    private String nombreDependencia;
    
    /**
     * email de la dependencia
     */
    @PodamStringValue(strValue = "holasoycorreo@extension.com")
    private String email;
    
    /**
     * telefono de la dependencia
     */
    @PodamStringValue(length = 8)
    private String telefono;
    
    @PodamExclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "dependencia",fetch = FetchType.LAZY)
    private List<AdministradorEntity> encargadosDependencia;
    
    
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
    public List<AdministradorEntity> getEncargadosDependencia() {
        return encargadosDependencia;
    }


    /**
     * @param encargadosDependencia
     */
    public void setEncargadosDependencia(List<AdministradorEntity> encargadosDependencia) {
        this.encargadosDependencia = encargadosDependencia;
    }
    
}
