/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class TecnologiaDetailDTO extends TecnologiaDTO implements Serializable {
    
        // relación  cero o muchos sitiosWeb
    private List<SitioWebDTO> sitios;

    public TecnologiaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto TecnologiaDetailDTO a partir de un objeto TecnologiaEntity
     * incluyendo los atributos de TecnologiaDTO.
     *
     * @param tecnologiaEntity Entidad TecnologiaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public TecnologiaDetailDTO(TecnologiaEntity tecnologiaEntity) {
        super(tecnologiaEntity);
        if (tecnologiaEntity != null) {
            sitios = new ArrayList<>();
            for (SitioWebEntity entityWebSites : tecnologiaEntity.getSitiosWeb()) {
                sitios.add(new SitioWebDTO(entityWebSites));

            }
        }
    }

    /**
     * Convierte un objeto TecnologiaDetailDTO a TecnologiaEntity incluyendo los
     * atributos de TecnologiaDTO.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    @Override
    public TecnologiaEntity toEntity() {
        TecnologiaEntity tecnologiaEntity = super.toEntity();
        if (sitios != null) {
            List<SitioWebEntity> sitiosEntity = new ArrayList<>();
            for (SitioWebDTO dtoSitios : sitios) {
                sitiosEntity.add(dtoSitios.toEntity());
            }
            tecnologiaEntity.setSitiosWeb(sitiosEntity);
        }
        return tecnologiaEntity;
    }

    /**
     * Obtiene la lista de sitios de la tecnologia
     *
     * @return the websites
     */
    public List<SitioWebDTO> getSitiosWeb() {
        return sitios;
    }

    /**
     * Modifica la lista de sitios para la tecnologia
     *
     * @param sitios the websites to set
     */
    public void setSitiosWeb(List<SitioWebDTO> sitios) {
        this.sitios = sitios;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
