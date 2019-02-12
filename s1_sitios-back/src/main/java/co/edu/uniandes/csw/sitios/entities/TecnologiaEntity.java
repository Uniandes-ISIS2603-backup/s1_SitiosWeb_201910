/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class TecnologiaEntity extends BaseEntity implements Serializable{
    
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
    private String description;
    /*
    *Technology official website
    */
    private String url;
    
    /*
    *Constructor
    */
    public TecnologiaEntity()
    {
        
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
        return description;
    }

    /**
     * @param description the descripcion to set
     */
    public void setDescription(String description) {
        this.description = description;
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
}
