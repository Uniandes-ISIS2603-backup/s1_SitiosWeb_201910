/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 *
 * @author amezq
 */ 
@Entity
public class TecnologiaEntity extends BaseEntity implements Serializable{

    /*
    * Tipos de tecnología
    */
    public enum TipoDeTecnologia
    {
        LENGUAJEDEPROGRAMACION,
        FRAMEWORK,
        SERVIDORDEAPLICACION,
        LIBRERIA
    }
       
    /*
    *Name of the technology
    */
    @PodamStringValue()
    private String name;
    /*
    *Version of the technology
    */
    private String version;
    /*
    *Technology description
    */
    @PodamStringValue(length = 21)
    private String description;
    /*
    *Technology official website
    */
    private String url;
    /*
     *Technology Type
    */
    private TipoDeTecnologia techCategory;
    
    @ManyToMany(mappedBy = "technologies")
    @PodamExclude
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

    public TipoDeTecnologia getTechCategory() {
        return techCategory;
    }

    public void setTechCategory(TipoDeTecnologia techCategory) {
        this.techCategory = techCategory;
    }

    public List<SitioWebEntity> getSitiosWeb() {
        return sitiosWeb;
    }

    public void setSitiosWeb(List<SitioWebEntity> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }
    
}
