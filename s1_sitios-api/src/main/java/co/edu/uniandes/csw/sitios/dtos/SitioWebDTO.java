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
import java.util.List;

/**
 *
 * @author Albert Adolfo Molano Cubillos
 */
public class SitioWebDTO  implements Serializable{

    	/**
	 * identificador unico
	 */
	private Long id;
	 
	private String nombre;

	/**
	 * descripcion del sitio
         */
	private String descripcion;

	/**
	 * proposito del sitio
	 */
	
	private String proposito;

	/**
	 * audiencia esperada del sitio web
	 */
	private Long audienciaEsperada;

	/**
	 * fecha de publicacion del sitio web
	 */
	private Date fechaLanzamiento;

	/**
	 * Categoria a la cual pertenece el sitio
	 */
	private Categoria categoriaSitio;

	/**
	 * ruta a la imagen representativa del sitio
	 */
	
	private String imagen;

	/**
	 * Lugar donde se encuentra desplegado el sitio web
	 */
	private PlataformaDeDespliegueDTO plataformaDeDespliegue;
        
        private String url;

    
        
        
        /**
         * Constructor por defecto
         */
        public SitioWebDTO()
        {
        }
        
        public SitioWebDTO(SitioWebEntity entity){
            if(entity != null) {
      
                this.id=entity.getId();
                this.audienciaEsperada=entity.getAudienciaEsperada();
                this.descripcion=entity.getDescripcion();
                this.imagen=entity.getImagen();
                this.proposito=entity.getProposito();
                this.nombre=entity.getNombre();
                this.fechaLanzamiento=entity.getFechaLanzamiento();
//                this.url=entity.getUrl();
                if(entity.getPlataformaDeDespliegue()!=null)
                {
                    this.plataformaDeDespliegue=new PlataformaDeDespliegueDTO(entity.getPlataformaDeDespliegue());
                }
                else
                {
                    this.plataformaDeDespliegue=null;
                }
                
            }
            else
            {
                this.plataformaDeDespliegue=null;
            }
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
        
        
        
        public SitioWebEntity toEntity()
        {
            SitioWebEntity entity= new SitioWebEntity();
            entity.setId(id);
            entity.setAudienciaEsperada(audienciaEsperada);
            entity.setDescripcion(descripcion);
            entity.setImagen(imagen);
            entity.setProposito(proposito);
            entity.setNombre(nombre);
            entity.setFechaLanzamiento(fechaLanzamiento);
            entity.setUrl(url);
            entity.setCategoriaSitio(this.categoriaSitio);
            return entity;
        }

   

        
        
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public Long getAudienciaEsperada() {
        return audienciaEsperada;
    }

    public void setAudienciaEsperada(Long audienciaEsperada) {
        this.audienciaEsperada = audienciaEsperada;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Categoria getCategoriaSitio() {
        return categoriaSitio;
    }

    public void setCategoriaSitio(Categoria categoriaSitio) {
        this.categoriaSitio = categoriaSitio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public PlataformaDeDespliegueDTO getPlataformaDeDespliegue() {
        return plataformaDeDespliegue;
    }

    public void setPlataformaDeDespliegue(PlataformaDeDespliegueDTO plataformaDeDespliegue) {
        this.plataformaDeDespliegue = plataformaDeDespliegue;
    }
  
        
}
