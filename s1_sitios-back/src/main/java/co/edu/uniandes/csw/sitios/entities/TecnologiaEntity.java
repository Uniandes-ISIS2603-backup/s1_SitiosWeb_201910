/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author amezq
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
     *Technology Type
    */
    private String techCategory;
    
    /*
    *List of websites associated to this technology
    */
   // private List<SitioWebEntity> sitiosWebList;
    
    //TODO
    //@javax.persistence.ManyToOne(
    //)
    //SitioWeb sitiosWeb;
    
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

    /**
     * @return the techCategory
     */
    public String getTipoDeTecnologia() {
        return techCategory;
    }

    /**
     * @param techCategory the techCategory to set
     */
    public void setTipoDeTecnologia(String techCategory) {
        this.techCategory = techCategory;
    }
    
    /**
    *@return List of websites associated with this technology
    */
    //public List<SitioWebEntity> getSitiosWeb()
    //{
    //    return sitiosWebList
    //}
    
    /**
    *@return the number of websites associated with this technology
    */
    //public Integer getCantidadSitiosWeb()
    //{
    //    return sitiosWebList.size();
    //}
    
    /**
     * @param newSitioWeb: Website to be added
     * @return true or false if the website was succesfully added.
     */
    
    //public boolean agregarSitioWeb(SitioWebEntity sitioWeb)
    //{
    //    sitiosWebList.add(sitioWeb);
    //    return true;
    //}
    
        /**
     * @param deleteSitioWeb: Website to be deleted
     * @return true or false if the website was succesfully deleted.
     */
    
   // public boolean borrarSitioWeb(SitioWebEntity deleteSitioWeb)
   // {
   //     sitiosWebList.remove(sitioWeb);
   //     return true;
   //}
}
