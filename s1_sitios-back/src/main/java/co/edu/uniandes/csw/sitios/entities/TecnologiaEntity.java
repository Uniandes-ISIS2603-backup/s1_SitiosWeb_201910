/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author amezq
 */ 
@Entity
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
     *Technology Type
    */
    private String techCategory;
    
    @javax.persistence.ManyToMany(
        mappedBy = "technologies",
        fetch = javax.persistence.FetchType.LAZY
    )
    private List<SitioWebEntity> sitiosWeb;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTechCategory() {
        return techCategory;
    }

    public void setTechCategory(String techCategory) {
        this.techCategory = techCategory;
    }

    public List<SitioWebEntity> getSitiosWeb() {
        return sitiosWeb;
    }

    public void setSitiosWeb(List<SitioWebEntity> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }
}
