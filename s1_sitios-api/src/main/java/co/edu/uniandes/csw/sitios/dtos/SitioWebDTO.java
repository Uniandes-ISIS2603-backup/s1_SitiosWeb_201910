/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity.Categoria;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Albert Adolfo Molano Cubillos
 */
public class SitioWebDTO implements Serializable {

    //__________________________________________________________________________
    // Atributos
    //__________________________________________________________________________
    
    /**
     * identificador unico.
     */
    private Long id;

    /**
     * nombre del sitio web.
     */
    private String nombre;

    /**
     * descripcion del sitio.
     */
    private String descripcion;

    /**
     * proposito del sitio.
     */
    private String proposito;

    /**
     * audiencia esperada del sitio web.
     */
    private Long audienciaEsperada;

    /**
     * fecha de publicacion del sitio web.
     */
    private Date fechaLanzamiento;

    /**
     * Categoria a la cual pertenece el sitio.
     */
    private Categoria categoriaSitio;

    /**
     * ruta a la imagen representativa del sitio.
     */
    private String imagen;

    /**
     * Lugar donde se encuentra desplegado el sitio web.
     */
    private PlataformaDeDespliegueDTO plataformaDeDespliegue;

    /**
     * url correspondiente al sitio web.
     */
    private String url;
    
    //__________________________________________________________________________
    // Constructores
    //__________________________________________________________________________

    /**
     * Constructor por defecto
     */
    public SitioWebDTO() {
    }
    
    /**
     * Convertir de Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     * 
     * @param entity: Es la entidad que se va a convertir a DTO 
     */
    public SitioWebDTO(SitioWebEntity entity) {
        if (entity != null) 
        {

            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.descripcion = entity.getDescripcion();
            this.proposito = entity.getProposito();
            this.audienciaEsperada = entity.getAudienciaEsperada();
            this.fechaLanzamiento = entity.getFechaLanzamiento();
            this.categoriaSitio = entity.getCategoriaSitio();
            this.imagen = entity.getImagen();
            this.url = entity.getUrl();
            
            if (entity.getPlataformaDeDespliegue() != null) 
            {
                this.plataformaDeDespliegue = new PlataformaDeDespliegueDTO(entity.getPlataformaDeDespliegue());
            } 
            else 
            {
                this.plataformaDeDespliegue = null;
            }

        } 
    }
    
    //__________________________________________________________________________
    // Metodos
    //__________________________________________________________________________
    
    /**
     * obtiene la url del sitioWeb
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * asigna la url del sitioWeb
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * id del sitioWeb 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * asigna el id para el sitioWeb
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * obtiene el nombre del sitioWeb
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * asigna el nombre del sitioWeb 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * obtiene la descripcion del sitioWeb
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * asigna la descripcion al sitioWeb
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * obtiene el proposito del sitioWeb
     * @return proposito
     */
    public String getProposito() {
        return proposito;
    }

    /**
     * asigna un proposito al sitioWeb
     * @param proposito 
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * obtiene la audiencia esperada para el sitioWeb
     * @return audienciaEsperada
     */
    public Long getAudienciaEsperada() {
        return audienciaEsperada;
    }

    /**
     * asigna la audiencia esperada en el sitioWeb
     * @param audienciaEsperada 
     */
    public void setAudienciaEsperada(Long audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    /**
     * obtiene la fecha de lanzamiento del sitioWeb
     * @return fechaLanzamiento
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * asigna la fecha de lanzamiento de un sitioWeb
     * @param fechaLanzamiento 
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * obtiene la categoria a la que pertenece el sitioWeb
     * @return categoriaSitio
     */
    public Categoria getCategoriaSitio() {
        return categoriaSitio;
    }

    /**
     *  asigna la categoria a la que pertenece el sitioWeb
     * @param categoriaSitio 
     */
    public void setCategoriaSitio(Categoria categoriaSitio) {
        this.categoriaSitio = categoriaSitio;
    }

    /**
     * obtiene la imagen representativa de un sitioWeb
     * @return imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * asigna la imagen representativa de un sitioWeb 
     * @param imagen 
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * obtiene la plataforma de despliegue del sitioWeb
     * @return plataformaDeDespliegue
     */
    public PlataformaDeDespliegueDTO getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    /**
     * asigna la plataforma de despliegue del sitioWeb
     * @param plataformaDeDespliegue 
     */
    public void setPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }
    
    /**
     * convierte el objeto DTO a entity
     * @return un sitioWebEntity con los valores del DTO
     */
    public SitioWebEntity toEntity() {
        SitioWebEntity entity = new SitioWebEntity();
        
        entity.setId(id);
        entity.setAudienciaEsperada(audienciaEsperada);
        entity.setDescripcion(descripcion);
        entity.setImagen(imagen);
        entity.setProposito(proposito);
        entity.setNombre(nombre);
        entity.setFechaLanzamiento(fechaLanzamiento);
        entity.setUrl(url);
        entity.setCategoriaSitio(this.categoriaSitio);
        
        if (this.plataformaDeDespliegue != null) 
        {
            entity.setPlataformaDeDespliegue(this.plataformaDeDespliegue.toEntity());
        }
        return entity;
    }

    /**
     * se sobreescribe el to string
     * 
     * @return 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
