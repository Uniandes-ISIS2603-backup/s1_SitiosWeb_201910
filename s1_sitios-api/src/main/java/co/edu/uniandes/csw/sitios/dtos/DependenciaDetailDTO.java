/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;
import co.edu.uniandes.csw.sitios.entities.DependenciaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class DependenciaDetailDTO extends DependenciaDTO implements Serializable {

    private AdministradorDTO admin;

    public DependenciaDetailDTO() {
        super();
        

    }

    /**
     * Crea un objeto DependenciaDetailDTO a partir de un objeto
     * DependenciaEntity incluyendo los atributos de DependenciaDTO.
     *
     * @param dependenciaEntity Entidad DependenciaEntity desde la cual se va
     * a crear el nuevo objeto.
     *
     */
    public DependenciaDetailDTO(DependenciaEntity dependenciaEntity) {
        super(dependenciaEntity);
        if (dependenciaEntity.getEncargadoDependencia() != null) {
            this.admin = new AdministradorDTO(dependenciaEntity.getEncargadoDependencia());
        }
    }

    /**
     * Convierte un objeto DependenciaDetailDTO a DependenciaEntity incluyendo
     * los atributos de DependenciaDTO.
     *
     * @return Nueva objeto DependenciaEntity.
     *
     */
    @Override
    public DependenciaEntity toEntity() {
        DependenciaEntity entity = super.toEntity();
        if (getEncargadoDependencia() != null) {
            entity.setEncargadoDependencia(getEncargadoDependencia().toEntity());
        }
        return entity;
    }

    /**
     * Devuelve el Administrador asociado a la dependencia
     *
     * @return DTO del administrador
     */
    public AdministradorDTO getEncargadoDependencia() {
        return admin;
    }

    /**
     * Modifica el premio asociado a esta organizacion.
     *
     * @param prize the author to set
     */
    public void setEncargadoDependencia (AdministradorDTO administrador) {
        this.admin = administrador;
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

