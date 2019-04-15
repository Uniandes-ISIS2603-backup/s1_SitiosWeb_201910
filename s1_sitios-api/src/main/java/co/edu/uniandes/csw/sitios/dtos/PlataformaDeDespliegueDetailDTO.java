/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author s.ballesteros
 */
public class PlataformaDeDespliegueDetailDTO extends PlataformaDeDespliegueDTO implements Serializable {

    /*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
     */
    private List<SitioWebDTO> sitiosWeb;

    /*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
     */
    private Long id;

    /*
* Constructor vacio de plataformaDeDespliegueDetailDTO
     */
    public PlataformaDeDespliegueDetailDTO() {
    }

    /*
* Constructor vacio de plataformaDeDespliegueDetailDTO
     */
    public PlataformaDeDespliegueDetailDTO(PlataformaDeDespliegueEntity plataformaEntity) {
        super(plataformaEntity);
        if (plataformaEntity != null && plataformaEntity.getSitiosWeb() != null) {
            sitiosWeb = new ArrayList<>();
            for (SitioWebEntity entitySitio : plataformaEntity.getSitiosWeb()) {
                sitiosWeb.add(new SitioWebDTO(entitySitio));
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la plataforma para transformar a Entity
     */
    @Override
    public PlataformaDeDespliegueEntity toEntity() {
        PlataformaDeDespliegueEntity plataformaEntity = super.toEntity();
        if (sitiosWeb != null) {
            List<SitioWebEntity> sitiosEntity = new ArrayList<>();
            for (SitioWebDTO dtoSitioWeb : sitiosWeb) {
                sitiosEntity.add(dtoSitioWeb.toEntity());
            }
            plataformaEntity.setSitiosWeb(sitiosEntity);
        }
        return plataformaEntity;
    }

    /**
     * @return the sitiosWeb
     */
    public List<SitioWebDTO> getSitiosWeb() {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb the sitiosWeb to set
     */
    public void setSitiosWeb(List<SitioWebDTO> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
