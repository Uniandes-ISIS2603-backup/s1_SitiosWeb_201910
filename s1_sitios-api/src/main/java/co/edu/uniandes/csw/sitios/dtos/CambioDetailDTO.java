/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.CambioEntity;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class CambioDetailDTO extends CambioDTO implements Serializable{
    
      private SitioWebDTO sitioWeb;

    public CambioDetailDTO(CambioEntity cambioEntity){
   super(cambioEntity);
       if(cambioEntity != null && cambioEntity.getSitiosWeb()!= null ){
           sitioWeb = new SitioWebDTO();
       }
}

    /**
     * Convierte un objeto OrganizationDetailDTO a OrganizationEntity incluyendo
     * los atributos de OrganizationDTO.
     *
     * @return Nueva objeto OrganizationEntity.
     *
     */
    @Override
    public CambioEntity toEntity() {
        CambioEntity entity = super.toEntity();
        if (getSitioWeb() != null) {
            entity.setSitiosWeb(getSitioWeb().toEntity());
        }
        return entity;
    }

    /**
     * Devuelve el premio asociado a esta organizacion
     *
     * @return DTO del premio
     */
    public SitioWebDTO getSitioWeb() {
        return sitioWeb;
    }

    /**
     * Modifica el premio asociado a esta organizacion.
     *
     * @param prize the author to set
     */
    public void setSitioWeb(SitioWebDTO prize) {
        this.sitioWeb = prize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
