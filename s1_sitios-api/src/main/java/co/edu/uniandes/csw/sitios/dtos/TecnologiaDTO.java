/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.TecnologiaEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class TecnologiaDTO implements Serializable{
       
    /*
    *Name of the technology
    */
    private String name;
    /*
    *Version of the technology
    */
    private String version;
    /*
    *Technology description
    */
    private String descripcion;
    /*
    *Technology official website
    */
    private String url;
    /*
    *Technology Type
    */
    private String techCategory;    
    /*
    *Technology Id
    */
    private Long id;
    
    /*
    *Constructor
    */
    public TecnologiaDTO()
    {
        
    }
    public TecnologiaDTO(TecnologiaEntity entity)
    {
      if(entity != null) {
     
        this.name=entity.getName();
        this.version=entity.getVersion();
        this.url=entity.getUrl();
        this.techCategory=entity.getTechCategory();
        this.descripcion=entity.getDescription();
        this.id=entity.getId();
      }
    }
    public TecnologiaEntity toEntity()
    {
        TecnologiaEntity entity = new TecnologiaEntity();
        entity.setName(name);
        entity.setVersion(version);
        entity.setDescription(descripcion);
        entity.setUrl(url);
        entity.setTechCategory(techCategory);
        return entity;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
}
